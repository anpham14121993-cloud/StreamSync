package androidx.media3.exoplayer.video.spherical;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.SurfaceTexture;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.WindowManager;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import androidx.media3.exoplayer.video.spherical.OrientationListener;
import androidx.media3.exoplayer.video.spherical.TouchTracker;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes7.dex */
public final class SphericalGLSurfaceView extends GLSurfaceView {
    private static final int FIELD_OF_VIEW_DEGREES = 90;
    private static final float PX_PER_DEGREES = 25.0f;
    static final float UPRIGHT_ROLL = 3.1415927f;
    private static final float Z_FAR = 100.0f;
    private static final float Z_NEAR = 0.1f;
    private boolean isOrientationListenerRegistered;
    private boolean isStarted;
    private final Handler mainHandler;
    private final OrientationListener orientationListener;
    private final Sensor orientationSensor;
    private final SceneRenderer scene;
    private final SensorManager sensorManager;
    private Surface surface;
    private SurfaceTexture surfaceTexture;
    private final TouchTracker touchTracker;
    private boolean useSensorRotation;
    private final CopyOnWriteArrayList<VideoSurfaceListener> videoSurfaceListeners;

    /* loaded from: classes7.dex */
    public interface VideoSurfaceListener {
        void onVideoSurfaceCreated(Surface surface);

        void onVideoSurfaceDestroyed(Surface surface);
    }

    public SphericalGLSurfaceView(Context context) {
        this(context, null);
    }

    public SphericalGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.videoSurfaceListeners = new CopyOnWriteArrayList<>();
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.sensorManager = (SensorManager) Assertions.checkNotNull(context.getSystemService("sensor"));
        Sensor orientationSensor = this.sensorManager.getDefaultSensor(15);
        this.orientationSensor = orientationSensor == null ? this.sensorManager.getDefaultSensor(11) : orientationSensor;
        this.scene = new SceneRenderer();
        Renderer renderer = new Renderer(this.scene);
        this.touchTracker = new TouchTracker(context, renderer, PX_PER_DEGREES);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Display display = ((WindowManager) Assertions.checkNotNull(windowManager)).getDefaultDisplay();
        this.orientationListener = new OrientationListener(display, this.touchTracker, renderer);
        this.useSensorRotation = true;
        setEGLContextClientVersion(2);
        setRenderer(renderer);
        setOnTouchListener(this.touchTracker);
    }

    public void addVideoSurfaceListener(VideoSurfaceListener listener) {
        this.videoSurfaceListeners.add(listener);
    }

    public void removeVideoSurfaceListener(VideoSurfaceListener listener) {
        this.videoSurfaceListeners.remove(listener);
    }

    public Surface getVideoSurface() {
        return this.surface;
    }

    public VideoFrameMetadataListener getVideoFrameMetadataListener() {
        return this.scene;
    }

    public CameraMotionListener getCameraMotionListener() {
        return this.scene;
    }

    public void setDefaultStereoMode(int stereoMode) {
        this.scene.setDefaultStereoMode(stereoMode);
    }

    public void setUseSensorRotation(boolean useSensorRotation) {
        this.useSensorRotation = useSensorRotation;
        updateOrientationListenerRegistration();
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        super.onResume();
        this.isStarted = true;
        updateOrientationListenerRegistration();
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        this.isStarted = false;
        updateOrientationListenerRegistration();
        super.onPause();
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mainHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.video.spherical.SphericalGLSurfaceView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SphericalGLSurfaceView.this.lambda$onDetachedFromWindow$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onDetachedFromWindow$0() {
        Surface oldSurface = this.surface;
        if (oldSurface != null) {
            Iterator<VideoSurfaceListener> it = this.videoSurfaceListeners.iterator();
            while (it.hasNext()) {
                VideoSurfaceListener videoSurfaceListener = it.next();
                videoSurfaceListener.onVideoSurfaceDestroyed(oldSurface);
            }
        }
        releaseSurface(this.surfaceTexture, oldSurface);
        this.surfaceTexture = null;
        this.surface = null;
    }

    private void updateOrientationListenerRegistration() {
        boolean enabled = this.useSensorRotation && this.isStarted;
        if (this.orientationSensor == null || enabled == this.isOrientationListenerRegistered) {
            return;
        }
        if (enabled) {
            this.sensorManager.registerListener(this.orientationListener, this.orientationSensor, 0);
        } else {
            this.sensorManager.unregisterListener(this.orientationListener);
        }
        this.isOrientationListenerRegistered = enabled;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSurfaceTextureAvailable(final SurfaceTexture newSurfaceTexture) {
        this.mainHandler.post(new Runnable() { // from class: androidx.media3.exoplayer.video.spherical.SphericalGLSurfaceView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SphericalGLSurfaceView.this.lambda$onSurfaceTextureAvailable$1(newSurfaceTexture);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSurfaceTextureAvailable$1(SurfaceTexture newSurfaceTexture) {
        SurfaceTexture oldSurfaceTexture = this.surfaceTexture;
        Surface oldSurface = this.surface;
        Surface newSurface = new Surface(newSurfaceTexture);
        this.surfaceTexture = newSurfaceTexture;
        this.surface = newSurface;
        Iterator<VideoSurfaceListener> it = this.videoSurfaceListeners.iterator();
        while (it.hasNext()) {
            VideoSurfaceListener videoSurfaceListener = it.next();
            videoSurfaceListener.onVideoSurfaceCreated(newSurface);
        }
        releaseSurface(oldSurfaceTexture, oldSurface);
    }

    private static void releaseSurface(SurfaceTexture oldSurfaceTexture, Surface oldSurface) {
        if (oldSurfaceTexture != null) {
            oldSurfaceTexture.release();
        }
        if (oldSurface != null) {
            oldSurface.release();
        }
    }

    /* loaded from: classes7.dex */
    final class Renderer implements GLSurfaceView.Renderer, TouchTracker.Listener, OrientationListener.Listener {
        private float deviceRoll;
        private final SceneRenderer scene;
        private float touchPitch;
        private final float[] projectionMatrix = new float[16];
        private final float[] viewProjectionMatrix = new float[16];
        private final float[] deviceOrientationMatrix = new float[16];
        private final float[] touchPitchMatrix = new float[16];
        private final float[] touchYawMatrix = new float[16];
        private final float[] viewMatrix = new float[16];
        private final float[] tempMatrix = new float[16];

        public Renderer(SceneRenderer scene) {
            this.scene = scene;
            GlUtil.setToIdentity(this.deviceOrientationMatrix);
            GlUtil.setToIdentity(this.touchPitchMatrix);
            GlUtil.setToIdentity(this.touchYawMatrix);
            this.deviceRoll = SphericalGLSurfaceView.UPRIGHT_ROLL;
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public synchronized void onSurfaceCreated(GL10 gl, EGLConfig config) {
            SphericalGLSurfaceView.this.onSurfaceTextureAvailable(this.scene.init());
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);
            float aspect = width / height;
            float fovY = calculateFieldOfViewInYDirection(aspect);
            Matrix.perspectiveM(this.projectionMatrix, 0, fovY, aspect, 0.1f, 100.0f);
        }

        @Override // android.opengl.GLSurfaceView.Renderer
        public void onDrawFrame(GL10 gl) {
            synchronized (this) {
                Matrix.multiplyMM(this.tempMatrix, 0, this.deviceOrientationMatrix, 0, this.touchYawMatrix, 0);
                Matrix.multiplyMM(this.viewMatrix, 0, this.touchPitchMatrix, 0, this.tempMatrix, 0);
            }
            Matrix.multiplyMM(this.viewProjectionMatrix, 0, this.projectionMatrix, 0, this.viewMatrix, 0);
            this.scene.drawFrame(this.viewProjectionMatrix, false);
        }

        @Override // androidx.media3.exoplayer.video.spherical.OrientationListener.Listener
        public synchronized void onOrientationChange(float[] matrix, float deviceRoll) {
            System.arraycopy(matrix, 0, this.deviceOrientationMatrix, 0, this.deviceOrientationMatrix.length);
            this.deviceRoll = -deviceRoll;
            updatePitchMatrix();
        }

        private void updatePitchMatrix() {
            Matrix.setRotateM(this.touchPitchMatrix, 0, -this.touchPitch, (float) Math.cos(this.deviceRoll), (float) Math.sin(this.deviceRoll), 0.0f);
        }

        @Override // androidx.media3.exoplayer.video.spherical.TouchTracker.Listener
        public synchronized void onScrollChange(PointF scrollOffsetDegrees) {
            this.touchPitch = scrollOffsetDegrees.y;
            updatePitchMatrix();
            Matrix.setRotateM(this.touchYawMatrix, 0, -scrollOffsetDegrees.x, 0.0f, 1.0f, 0.0f);
        }

        @Override // androidx.media3.exoplayer.video.spherical.TouchTracker.Listener
        public boolean onSingleTapUp(MotionEvent event) {
            return SphericalGLSurfaceView.this.performClick();
        }

        private float calculateFieldOfViewInYDirection(float aspect) {
            boolean landscapeMode = aspect > 1.0f;
            if (landscapeMode) {
                double tanY = Math.tan(Math.toRadians(45.0d)) / aspect;
                double halfFovY = Math.toDegrees(Math.atan(tanY));
                return (float) (2.0d * halfFovY);
            }
            return 90.0f;
        }
    }
}
