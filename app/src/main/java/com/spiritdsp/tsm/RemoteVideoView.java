package com.spiritdsp.tsm;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.DisplayMetrics;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: Render */
class RemoteVideoView extends GLSurfaceView implements Renderer {
    private final String fragmentShaderCode = "precision mediump float;uniform sampler2D Ytex;uniform sampler2D Utex,Vtex;varying vec2 vTextureCoord;void main(void) {  float nx,ny,r,g,b,y,u,v;  nx=vTextureCoord[0];  ny=vTextureCoord[1];  y=texture2D(Ytex,vec2(nx,ny)).r;  u=texture2D(Utex,vec2(nx,ny)).r;  v=texture2D(Vtex,vec2(nx,ny)).r;  u=u-0.5;  v=v-0.5;  r=y+1.5958*v;  g=y-0.39173*u-0.81290*v;  b=y+2.017*u;  gl_FragColor=vec4(r,g,b,1.0);}";
    private final String fragmentShaderCodeFill = "precision mediump float;uniform vec4 aColor;void main() {  gl_FragColor = aColor;}";
    private final String fragmentShaderCodeText = "precision mediump float;uniform sampler2D tex;varying vec2 vTextureCoord;void main(void) {  gl_FragColor=texture2D(tex,vTextureCoord);}";
    private boolean isAttached = false;
    private boolean isDebug = true;
    private int isInitNeeded = 0;
    private DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    private int mGraphicsContext = 0;
    private int mHeight;
    private int mProgram = 0;
    private int mProgramFill = 0;
    private int mProgramText = 0;
    private int mWidth;
    private int mtype;
    private final String vertexShaderCode = "attribute vec4 aPosition;attribute vec2 aTextureCoord;varying vec2 vTextureCoord;void main() {  gl_Position = aPosition;  vTextureCoord = aTextureCoord;}";
    private final String vertexShaderCodeFill = "attribute vec4 aPosition;void main() {  gl_Position = aPosition;}";

    private native void _register_viewport(DisplayMetrics displayMetrics, int i);

    private native void _unregister_viewport(int i);

    private native void nativeDraw(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

    public RemoteVideoView(Context ctx, int Atype) {
        super(ctx);
        this.mtype = Atype;
        Logging.LogDebugPrint(this.isDebug, "RVV:<init> ", new Object[0]);
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(this.mDisplayMetrics);
        Logging.LogDebugPrint(this.isDebug, "RVV:create. tid %d", Integer.valueOf(Process.myTid()));
        setVisibility(0);
        setBackgroundColor(0);
        setDrawingCacheEnabled(false);
        setZOrderMediaOverlay(true);
        setEGLContextClientVersion(2);
        setRenderer(this);
        setRenderMode(0);
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        Logging.LogDebugPrint(this.isDebug, "RVV:onSurfaceCreated", new Object[0]);
        this.mProgram = loadProgramm("attribute vec4 aPosition;attribute vec2 aTextureCoord;varying vec2 vTextureCoord;void main() {  gl_Position = aPosition;  vTextureCoord = aTextureCoord;}", "precision mediump float;uniform sampler2D Ytex;uniform sampler2D Utex,Vtex;varying vec2 vTextureCoord;void main(void) {  float nx,ny,r,g,b,y,u,v;  nx=vTextureCoord[0];  ny=vTextureCoord[1];  y=texture2D(Ytex,vec2(nx,ny)).r;  u=texture2D(Utex,vec2(nx,ny)).r;  v=texture2D(Vtex,vec2(nx,ny)).r;  u=u-0.5;  v=v-0.5;  r=y+1.5958*v;  g=y-0.39173*u-0.81290*v;  b=y+2.017*u;  gl_FragColor=vec4(r,g,b,1.0);}");
        this.mProgramFill = loadProgramm("attribute vec4 aPosition;void main() {  gl_Position = aPosition;}", "precision mediump float;uniform vec4 aColor;void main() {  gl_FragColor = aColor;}");
        this.mProgramText = loadProgramm("attribute vec4 aPosition;attribute vec2 aTextureCoord;varying vec2 vTextureCoord;void main() {  gl_Position = aPosition;  vTextureCoord = aTextureCoord;}", "precision mediump float;uniform sampler2D tex;varying vec2 vTextureCoord;void main(void) {  gl_FragColor=texture2D(tex,vTextureCoord);}");
        this.isInitNeeded = 1;
    }

    static boolean IsInvertedOpenGL() {
        return (TSM_impl.mIsGalaxyS2 || TSM_impl.mIsGalaxyNote || TSM_impl.mIsGalaxyAdvance) && VERSION.SDK_INT <= 10;
    }

    public void onSurfaceChanged(GL10 unused, int w, int h) {
        Logging.LogDebugPrint(this.isDebug, "RVV:onSurfaceChanged %dx%d", Integer.valueOf(w), Integer.valueOf(h));
        this.mWidth = w;
        this.mHeight = h;
        GLES20.glViewport(0, 0, w, h);
    }

    protected void onWindowVisibilityChanged(int visibility) {
        Logging.LogDebugPrint(this.isDebug, "RVV:onWindowVisibilityChanged %d", Integer.valueOf(visibility));
        if (visibility == 0) {
            onResume();
        } else {
            onPause();
        }
        super.onWindowVisibilityChanged(visibility);
    }

    public void onDrawFrame(GL10 unused) {
        synchronized (this) {
            if (!this.isAttached || this.mGraphicsContext == 0) {
                GLES20.glClear(16384);
                return;
            }
            nativeDraw(this.mGraphicsContext, this.mProgram, this.mProgramFill, this.mProgramText, this.mWidth, this.mHeight, this.isInitNeeded, this.mtype);
            this.isInitNeeded = 0;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean z = this.isDebug;
        String str = "RVV:onAttachedToWindow %d";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(this.isAttached ? 1 : 0);
        Logging.LogDebugPrint(z, str, objArr);
        if (!this.isAttached) {
            _register_viewport(this.mDisplayMetrics, this.mtype);
            this.isAttached = true;
        }
    }

    protected void onDetachedFromWindow() {
        int i = 1;
        boolean z = this.isDebug;
        String str = "RVV:onDetachedFromWindow %d";
        Object[] objArr = new Object[1];
        if (!this.isAttached) {
            i = 0;
        }
        objArr[0] = Integer.valueOf(i);
        Logging.LogDebugPrint(z, str, objArr);
        if (this.isAttached) {
            this.isAttached = false;
            _unregister_viewport(this.mtype);
        }
        super.onDetachedFromWindow();
    }

    public synchronized void externalSetContext(int GraphicsContext) {
        this.mGraphicsContext = GraphicsContext;
    }

    public synchronized void externalUpdateFrame() {
        if (this.isAttached) {
            requestRender();
        }
    }

    public synchronized void externalResize(int w, int h) {
    }

    private static void CheckOpenGLError() {
        if (GLES20.glGetError() != 0) {
            Logging.LogDebugPrint(true, "ViewPort : OpenGL problem %d in line %d", Integer.valueOf(GLES20.glGetError()), Integer.valueOf(Thread.currentThread().getStackTrace()[3].getLineNumber()));
        }
    }

    private static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        CheckOpenGLError();
        GLES20.glShaderSource(shader, shaderCode);
        CheckOpenGLError();
        GLES20.glCompileShader(shader);
        CheckOpenGLError();
        int[] compiled = new int[1];
        GLES20.glGetShaderiv(shader, 35713, compiled, 0);
        CheckOpenGLError();
        if (compiled[0] != 0) {
            return shader;
        }
        Logging.LogDebugPrint(true, "Could not compile shader:", new Object[0]);
        Logging.LogDebugPrint(true, GLES20.glGetShaderInfoLog(shader), new Object[0]);
        GLES20.glDeleteShader(shader);
        return 0;
    }

    private static int loadProgramm(String vShader, String fShader) {
        int vertexShader = loadShader(35633, vShader);
        int fragmentShader = loadShader(35632, fShader);
        int program = GLES20.glCreateProgram();
        CheckOpenGLError();
        GLES20.glAttachShader(program, vertexShader);
        CheckOpenGLError();
        GLES20.glAttachShader(program, fragmentShader);
        CheckOpenGLError();
        GLES20.glLinkProgram(program);
        CheckOpenGLError();
        return program;
    }
}
