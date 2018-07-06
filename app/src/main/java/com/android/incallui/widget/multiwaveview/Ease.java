package com.android.incallui.widget.multiwaveview;

import android.view.animation.Interpolator;

class Ease {
    private static final float DOMAIN = 1.0f;
    private static final float DURATION = 1.0f;
    private static final float START = 0.0f;

    static class Cubic {
        public static final Interpolator easeIn = new Interpolator() {
            public float getInterpolation(float f) {
                float f2 = f / 1.0f;
                return (f2 * ((1.0f * f2) * f2)) + 0.0f;
            }
        };
        public static final Interpolator easeInOut = new Interpolator() {
            public float getInterpolation(float f) {
                float f2 = f / 0.5f;
                if (f2 < 1.0f) {
                    return (f2 * ((0.5f * f2) * f2)) + 0.0f;
                }
                f2 -= 2.0f;
                return (((f2 * (f2 * f2)) + 2.0f) * 0.5f) + 0.0f;
            }
        };
        public static final Interpolator easeOut = new Interpolator() {
            public float getInterpolation(float f) {
                float f2 = (f / 1.0f) - 1.0f;
                return (((f2 * (f2 * f2)) + 1.0f) * 1.0f) + 0.0f;
            }
        };

        Cubic() {
        }
    }

    static class Linear {
        public static final Interpolator easeNone = new Interpolator() {
            public float getInterpolation(float f) {
                return f;
            }
        };

        Linear() {
        }
    }

    static class Quad {
        public static final Interpolator easeIn = new Interpolator() {
            public float getInterpolation(float f) {
                float f2 = f / 1.0f;
                return (f2 * (1.0f * f2)) + 0.0f;
            }
        };
        public static final Interpolator easeInOut = new Interpolator() {
            public float getInterpolation(float f) {
                float f2 = f / 0.5f;
                if (f2 < 1.0f) {
                    return (f2 * (0.5f * f2)) + 0.0f;
                }
                f2 -= 1.0f;
                return (((f2 * (f2 - 2.0f)) - 1.0f) * -0.5f) + 0.0f;
            }
        };
        public static final Interpolator easeOut = new Interpolator() {
            public float getInterpolation(float f) {
                float f2 = f / 1.0f;
                return ((-1.0f * f2) * (f2 - 2.0f)) + 0.0f;
            }
        };

        Quad() {
        }
    }

    static class Quart {
        public static final Interpolator easeIn = new Interpolator() {
            public float getInterpolation(float f) {
                float f2 = f / 1.0f;
                return (f2 * (((1.0f * f2) * f2) * f2)) + 0.0f;
            }
        };
        public static final Interpolator easeInOut = new Interpolator() {
            public float getInterpolation(float f) {
                float f2 = f / 0.5f;
                if (f2 < 1.0f) {
                    return (f2 * (((0.5f * f2) * f2) * f2)) + 0.0f;
                }
                f2 -= 2.0f;
                return (((f2 * ((f2 * f2) * f2)) - 2.0f) * -0.5f) + 0.0f;
            }
        };
        public static final Interpolator easeOut = new Interpolator() {
            public float getInterpolation(float f) {
                float f2 = (f / 1.0f) - 1.0f;
                return (-1.0f * ((f2 * ((f2 * f2) * f2)) - 1.0f)) + 0.0f;
            }
        };

        Quart() {
        }
    }

    static class Quint {
        public static final Interpolator easeIn = new Interpolator() {
            public float getInterpolation(float f) {
                float f2 = f / 1.0f;
                return (f2 * ((((1.0f * f2) * f2) * f2) * f2)) + 0.0f;
            }
        };
        public static final Interpolator easeInOut = new Interpolator() {
            public float getInterpolation(float f) {
                float f2 = f / 0.5f;
                if (f2 < 1.0f) {
                    return (f2 * ((((0.5f * f2) * f2) * f2) * f2)) + 0.0f;
                }
                f2 -= 2.0f;
                return (((f2 * (((f2 * f2) * f2) * f2)) + 2.0f) * 0.5f) + 0.0f;
            }
        };
        public static final Interpolator easeOut = new Interpolator() {
            public float getInterpolation(float f) {
                float f2 = (f / 1.0f) - 1.0f;
                return (((f2 * (((f2 * f2) * f2) * f2)) + 1.0f) * 1.0f) + 0.0f;
            }
        };

        Quint() {
        }
    }

    static class Sine {
        public static final Interpolator easeIn = new Interpolator() {
            public float getInterpolation(float f) {
                return ((-1.0f * ((float) Math.cos(((double) (f / 1.0f)) * 1.5707963267948966d))) + 1.0f) + 0.0f;
            }
        };
        public static final Interpolator easeInOut = new Interpolator() {
            public float getInterpolation(float f) {
                return (-0.5f * (((float) Math.cos((3.141592653589793d * ((double) f)) / 1.0d)) - 1.0f)) + 0.0f;
            }
        };
        public static final Interpolator easeOut = new Interpolator() {
            public float getInterpolation(float f) {
                return (((float) Math.sin(((double) (f / 1.0f)) * 1.5707963267948966d)) * 1.0f) + 0.0f;
            }
        };

        Sine() {
        }
    }

    Ease() {
    }
}
