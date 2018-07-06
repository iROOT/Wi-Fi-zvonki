package com.spiritdsp.tsm;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Build.VERSION;

abstract class AudioRouter {
    static final int AUDIO_PATH_BT_HANDSET = 5;
    static final int AUDIO_PATH_DISABLED = 0;
    static final int AUDIO_PATH_HANDSET = 2;
    static final int AUDIO_PATH_HANDSFREE = 1;
    static final int AUDIO_PATH_UNDEFINED = -1;
    private static final boolean bEnableDebugPrint = true;
    private static int mAudioPath = -1;
    private static IRouter mRouter;
    static boolean mUpdateContextBT = true;

    interface IRouter {
        void AfterAudioRecordCreation(AudioManager audioManager, AudioRecord audioRecord);

        void AfterAudioTrackCreation(AudioManager audioManager, AudioTrack audioTrack);

        void BeforeAudioRecordCreation(AudioManager audioManager);

        void BeforeAudioTrackCreation(AudioManager audioManager);

        boolean IsAudioResetRequired(int i);

        boolean StartCaptureBT(Context context, AudioManager audioManager);

        boolean StopCaptureBT(Context context, AudioManager audioManager);

        void SwitchAudioPath(Context context, AudioManager audioManager, int i, AudioTrack audioTrack, AudioRecord audioRecord);

        boolean UseBtAudioDevice();
    }

    @TargetApi(23)
    private static class Router23Plus implements IRouter {
        private Router23Plus() {
        }

        public boolean IsAudioResetRequired(int newPath) {
            if ((AudioRouter.mAudioPath == 2 && newPath == 1) || (AudioRouter.mAudioPath == 1 && newPath == 2)) {
                return false;
            }
            return true;
        }

        public boolean UseBtAudioDevice() {
            return false;
        }

        public void SwitchAudioPath(Context context, AudioManager audioManager, int path, AudioTrack audioTrack, AudioRecord audioRecord) {
            if (audioManager != null) {
                Logging.LogDebugPrint(true, "Router23Plus.SwitchAudioPath: %d", Integer.valueOf(path));
                if (path == 5) {
                    audioManager.startBluetoothSco();
                    audioManager.setBluetoothScoOn(true);
                } else {
                    audioManager.setBluetoothScoOn(false);
                    audioManager.stopBluetoothSco();
                }
                if (audioTrack != null) {
                    SetAudioTrackDevice(audioManager, path, audioTrack);
                }
                if (audioRecord != null) {
                    SetAudioRecordDevice(audioManager, path, audioRecord);
                }
                AudioRouter.mAudioPath = path;
            }
        }

        public boolean StartCaptureBT(Context context, AudioManager audioManager) {
            return true;
        }

        public boolean StopCaptureBT(Context context, AudioManager audioManager) {
            return true;
        }

        public void BeforeAudioTrackCreation(AudioManager audioManager) {
        }

        public void BeforeAudioRecordCreation(AudioManager audioManager) {
        }

        public void AfterAudioTrackCreation(AudioManager audioManager, AudioTrack audioTrack) {
            SetAudioTrackDevice(audioManager, AudioRouter.mAudioPath, audioTrack);
        }

        public void AfterAudioRecordCreation(AudioManager audioManager, AudioRecord audioRecord) {
            SetAudioRecordDevice(audioManager, AudioRouter.mAudioPath, audioRecord);
        }

        private void SetAudioRecordDevice(AudioManager audioManager, int path, AudioRecord audioRecord) {
            AudioDeviceInfo newRecordDevice = null;
            for (AudioDeviceInfo deviceInfo : audioManager.getDevices(1)) {
                if (deviceInfo.getType() == 15 && (path == 2 || path == 1)) {
                    newRecordDevice = deviceInfo;
                }
                if (deviceInfo.getType() == 7 && path == 5) {
                    newRecordDevice = deviceInfo;
                }
                if (newRecordDevice != null) {
                    break;
                }
            }
            if (newRecordDevice != null) {
                Logging.LogDebugPrint(true, "Router23Plus: AudioRecord.setPreferredDevice: %d", Integer.valueOf(newRecordDevice.getType()));
                audioRecord.setPreferredDevice(newRecordDevice);
            }
        }

        private void SetAudioTrackDevice(AudioManager audioManager, int path, AudioTrack audioTrack) {
            AudioDeviceInfo newPlaybackDevice = null;
            for (AudioDeviceInfo deviceInfo : audioManager.getDevices(2)) {
                if (deviceInfo.getType() == 1 && path == 2) {
                    newPlaybackDevice = deviceInfo;
                } else if (deviceInfo.getType() == 2 && path == 1) {
                    newPlaybackDevice = deviceInfo;
                } else if (deviceInfo.getType() == 7 && path == 5) {
                    newPlaybackDevice = deviceInfo;
                } else if (deviceInfo.getType() == 8 && path == 5) {
                    newPlaybackDevice = deviceInfo;
                }
                if (newPlaybackDevice != null) {
                    break;
                }
            }
            if (newPlaybackDevice != null) {
                Logging.LogDebugPrint(true, "Router23Plus: AudioTrack.setPreferredDevice: %d", Integer.valueOf(newPlaybackDevice.getType()));
                audioTrack.setPreferredDevice(newPlaybackDevice);
            }
        }
    }

    private static class RouterOld implements IRouter {
        private BtAudioDevice btAudioDevice;
        private boolean mIsSpeakerphoneOn;
        private boolean mUseBtAudioDevice;

        private RouterOld() {
            this.mUseBtAudioDevice = false;
            this.mIsSpeakerphoneOn = false;
            this.btAudioDevice = null;
        }

        public boolean IsAudioResetRequired(int newPath) {
            return true;
        }

        public boolean UseBtAudioDevice() {
            return this.mUseBtAudioDevice;
        }

        public void SwitchAudioPath(Context context, AudioManager audioManager, int audioPath, AudioTrack audioTrack, AudioRecord audioRecord) {
            if (audioManager != null) {
                boolean hasA2DPEnabled = audioManager.isBluetoothA2dpOn();
                switch (audioPath) {
                    case 1:
                        if (hasA2DPEnabled) {
                            audioManager.setMode(0);
                            audioManager.setBluetoothScoOn(false);
                            audioManager.stopBluetoothSco();
                        }
                        this.mUseBtAudioDevice = false;
                        SetSpeakerphone(audioManager, true);
                        break;
                    case 5:
                        SetSpeakerphone(audioManager, false);
                        if (!hasA2DPEnabled) {
                            boolean isBTpresent = false;
                            if (!audioManager.isBluetoothScoOn() && audioManager.isBluetoothScoAvailableOffCall()) {
                                isBTpresent = true;
                            }
                            BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
                            if (bta != null) {
                                isBTpresent = bta.isEnabled();
                            }
                            this.mUseBtAudioDevice = isBTpresent;
                            break;
                        }
                        audioManager.setMode(0);
                        audioManager.startBluetoothSco();
                        audioManager.setBluetoothScoOn(true);
                        break;
                    default:
                        if (hasA2DPEnabled) {
                            audioManager.setMode(3);
                            audioManager.setBluetoothScoOn(false);
                            audioManager.stopBluetoothSco();
                        }
                        this.mUseBtAudioDevice = false;
                        SetSpeakerphone(audioManager, false);
                        break;
                }
                AudioRouter.mAudioPath = audioPath;
                Logging.LogDebugPrint(true, "RouterOld.SwitchAudioPath: hasA2DPEnabled=%s mUseBtAudioDevice=%s", String.valueOf(hasA2DPEnabled), String.valueOf(this.mUseBtAudioDevice));
            }
        }

        public boolean StartCaptureBT(Context context, AudioManager audioManager) {
            Logging.LogDebugPrint(true, "StartCaptureBT:mUseBtAudioDevice=%s", String.valueOf(this.mUseBtAudioDevice));
            if (!this.mUseBtAudioDevice) {
                return true;
            }
            Logging.LogDebugPrint(true, "StartCaptureBT", new Object[0]);
            BtAudioDevice d = getBtAudioDevice(context, audioManager);
            if (d != null) {
                d.Start();
                return true;
            }
            Logging.LogNativePrintErr("FAILED to get BT device", new Object[0]);
            return false;
        }

        public boolean StopCaptureBT(Context context, AudioManager audioManager) {
            Logging.LogDebugPrint(true, "StopCaptureBT:mUseBtAudioDevice=%s", String.valueOf(this.mUseBtAudioDevice));
            if (this.mUseBtAudioDevice) {
                Logging.LogDebugPrint(true, "StopCaptureBT", new Object[0]);
                BtAudioDevice d = getBtAudioDevice(context, audioManager);
                if (d != null) {
                    d.Stop();
                } else {
                    Logging.LogNativePrintErr("FAILED to get BT device", new Object[0]);
                }
            }
            return true;
        }

        public void BeforeAudioTrackCreation(AudioManager audioManager) {
        }

        public void BeforeAudioRecordCreation(AudioManager audioManager) {
            audioManager.setSpeakerphoneOn(false);
        }

        public void AfterAudioTrackCreation(AudioManager audioManager, AudioTrack audioTrack) {
        }

        public void AfterAudioRecordCreation(AudioManager audioManager, AudioRecord audioRecord) {
            Logging.LogDebugPrint(true, "AfterAudioRecordCreation:mUseBtAudioDevice=%s", String.valueOf(this.mUseBtAudioDevice));
            if (!this.mUseBtAudioDevice) {
                audioManager.setSpeakerphoneOn(this.mIsSpeakerphoneOn);
            }
        }

        private void SetSpeakerphone(AudioManager audioManager, boolean on) {
            String str = "SetSpeakerphone: %s";
            Object[] objArr = new Object[1];
            objArr[0] = on ? "on" : "off";
            Logging.LogDebugPrint(true, str, objArr);
            if (audioManager.isSpeakerphoneOn() == on) {
                Logging.LogDebugPrint(true, "SetSpeakerphone: The same value", new Object[0]);
                return;
            }
            audioManager.setSpeakerphoneOn(on);
            this.mIsSpeakerphoneOn = on;
        }

        private BtAudioDevice getBtAudioDevice(Context context, AudioManager audioManager) {
            if ((AudioRouter.mUpdateContextBT || this.btAudioDevice == null) && context != null) {
                synchronized (this) {
                    this.btAudioDevice = new BtAudioDevice(context, audioManager, new BtStateReceiver() {
                        public void OnScoService(eScoState state) {
                            Logging.LogDebugPrint(true, "BtAudioDevice: SCO state %d", Integer.valueOf(state.ordinal()));
                            if (state != eScoState.STARTED) {
                                RouterOld.this.mUseBtAudioDevice = false;
                                Logging.LogDebugPrint(true, "mUseBtAudioDevice = %s", String.valueOf(RouterOld.this.mUseBtAudioDevice));
                            }
                        }
                    });
                    AudioRouter.mUpdateContextBT = false;
                }
            }
            Logging.LogDebugPrint(true, "BtAudioDevice: %s", String.valueOf(this.btAudioDevice));
            return this.btAudioDevice;
        }
    }

    AudioRouter() {
    }

    static boolean IsValidAudioPath(int audioPath) {
        return audioPath == 2 || audioPath == 1 || audioPath == 5 || audioPath == 0;
    }

    static int GetAudioPath() {
        return mAudioPath;
    }

    static synchronized IRouter GetRouter(boolean isJavaIo) {
        IRouter iRouter;
        synchronized (AudioRouter.class) {
            if (mRouter == null) {
                if (!isJavaIo || VERSION.SDK_INT < 23) {
                    mRouter = new RouterOld();
                } else {
                    mRouter = new Router23Plus();
                }
            }
            iRouter = mRouter;
        }
        return iRouter;
    }
}
