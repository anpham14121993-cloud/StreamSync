package androidx.media3.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.AttachedSurfaceControl;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.SurfaceSyncGroup;
import androidx.media3.common.AdOverlayInfo;
import androidx.media3.common.AdViewProvider;
import androidx.media3.common.ErrorMessageProvider;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.ui.AspectRatioFrameLayout;
import androidx.media3.ui.PlayerControlView;
import androidx.media3.ui.PlayerView;
import com.google.common.collect.ImmutableList;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* loaded from: classes24.dex */
public class PlayerView extends FrameLayout implements AdViewProvider {
    public static final int ARTWORK_DISPLAY_MODE_FILL = 2;
    public static final int ARTWORK_DISPLAY_MODE_FIT = 1;
    public static final int ARTWORK_DISPLAY_MODE_OFF = 0;
    public static final int IMAGE_DISPLAY_MODE_FILL = 1;
    public static final int IMAGE_DISPLAY_MODE_FIT = 0;
    public static final int SHOW_BUFFERING_ALWAYS = 2;
    public static final int SHOW_BUFFERING_NEVER = 0;
    public static final int SHOW_BUFFERING_WHEN_PLAYING = 1;
    private static final int SURFACE_TYPE_NONE = 0;
    private static final int SURFACE_TYPE_SPHERICAL_GL_SURFACE_VIEW = 3;
    private static final int SURFACE_TYPE_SURFACE_VIEW = 1;
    private static final int SURFACE_TYPE_TEXTURE_VIEW = 2;
    private static final int SURFACE_TYPE_VIDEO_DECODER_GL_SURFACE_VIEW = 4;
    private final FrameLayout adOverlayFrameLayout;
    private int artworkDisplayMode;
    private final ImageView artworkView;
    private final View bufferingView;
    private final ComponentListener componentListener;
    private final AspectRatioFrameLayout contentFrame;
    private final PlayerControlView controller;
    private boolean controllerAutoShow;
    private boolean controllerHideDuringAds;
    private boolean controllerHideOnTouch;
    private int controllerShowTimeoutMs;
    private ControllerVisibilityListener controllerVisibilityListener;
    private CharSequence customErrorMessage;
    private Drawable defaultArtwork;
    private ErrorMessageProvider<? super PlaybackException> errorMessageProvider;
    private final TextView errorMessageView;
    private final Class<?> exoPlayerClazz;
    private FullscreenButtonClickListener fullscreenButtonClickListener;
    private int imageDisplayMode;
    private final Object imageOutput;
    private final ImageView imageView;
    private boolean keepContentOnPlayerReset;
    private PlayerControlView.VisibilityListener legacyControllerVisibilityListener;
    private final Handler mainLooperHandler;
    private final FrameLayout overlayFrameLayout;
    private Player player;
    private final Method setImageOutputMethod;
    private int showBuffering;
    private final View shutterView;
    private final SubtitleView subtitleView;
    private final SurfaceSyncGroupCompatV34 surfaceSyncGroupV34;
    private final View surfaceView;
    private final boolean surfaceViewIgnoresVideoAspectRatio;
    private int textureViewRotation;
    private boolean useController;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes24.dex */
    public @interface ArtworkDisplayMode {
    }

    /* loaded from: classes24.dex */
    public interface ControllerVisibilityListener {
        void onVisibilityChanged(int i);
    }

    /* loaded from: classes24.dex */
    public interface FullscreenButtonClickListener {
        void onFullscreenButtonClick(boolean z);
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes24.dex */
    public @interface ImageDisplayMode {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes24.dex */
    public @interface ShowBuffering {
    }

    public PlayerView(Context context) {
        this(context, null);
    }

    public PlayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x043f  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x044d  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x036c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0377  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x03b0  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0432  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public PlayerView(android.content.Context r30, android.util.AttributeSet r31, int r32) {
        /*
            Method dump skipped, instructions count: 1120
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.ui.PlayerView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-media3-ui-PlayerView, reason: not valid java name */
    public /* synthetic */ Object m6492lambda$new$0$androidxmedia3uiPlayerView(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("onImageAvailable")) {
            onImageAvailable((Bitmap) args[1]);
            return null;
        }
        return null;
    }

    public static void switchTargetView(Player player, PlayerView oldPlayerView, PlayerView newPlayerView) {
        if (oldPlayerView == newPlayerView) {
            return;
        }
        if (newPlayerView != null) {
            newPlayerView.setPlayer(player);
        }
        if (oldPlayerView != null) {
            oldPlayerView.setPlayer(null);
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        Assertions.checkArgument(player == null || player.getApplicationLooper() == Looper.getMainLooper());
        if (this.player == player) {
            return;
        }
        Player oldPlayer = this.player;
        if (oldPlayer != null) {
            oldPlayer.removeListener(this.componentListener);
            if (oldPlayer.isCommandAvailable(27)) {
                if (this.surfaceView instanceof TextureView) {
                    oldPlayer.clearVideoTextureView((TextureView) this.surfaceView);
                } else if (this.surfaceView instanceof SurfaceView) {
                    oldPlayer.clearVideoSurfaceView((SurfaceView) this.surfaceView);
                }
            }
            clearImageOutput(oldPlayer);
        }
        if (this.subtitleView != null) {
            this.subtitleView.setCues(null);
        }
        this.player = player;
        if (useController()) {
            this.controller.setPlayer(player);
        }
        updateBuffering();
        updateErrorMessage();
        updateForCurrentTrackSelections(true);
        if (player != null) {
            if (player.isCommandAvailable(27)) {
                if (this.surfaceView instanceof TextureView) {
                    player.setVideoTextureView((TextureView) this.surfaceView);
                } else if (this.surfaceView instanceof SurfaceView) {
                    player.setVideoSurfaceView((SurfaceView) this.surfaceView);
                }
                if (!player.isCommandAvailable(30) || player.getCurrentTracks().isTypeSupported(2)) {
                    updateAspectRatio();
                }
            }
            if (this.subtitleView != null && player.isCommandAvailable(28)) {
                this.subtitleView.setCues(player.getCurrentCues().cues);
            }
            player.addListener(this.componentListener);
            setImageOutput(player);
            maybeShowController(false);
            return;
        }
        hideController();
    }

    private void setImageOutput(Player player) {
        if (this.exoPlayerClazz != null && this.exoPlayerClazz.isAssignableFrom(player.getClass())) {
            try {
                ((Method) Assertions.checkNotNull(this.setImageOutputMethod)).invoke(player, Assertions.checkNotNull(this.imageOutput));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void clearImageOutput(Player player) {
        if (this.exoPlayerClazz != null && this.exoPlayerClazz.isAssignableFrom(player.getClass())) {
            try {
                ((Method) Assertions.checkNotNull(this.setImageOutputMethod)).invoke(player, null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override // android.view.View
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (this.surfaceView instanceof SurfaceView) {
            this.surfaceView.setVisibility(visibility);
        }
    }

    public void setResizeMode(int resizeMode) {
        Assertions.checkStateNotNull(this.contentFrame);
        this.contentFrame.setResizeMode(resizeMode);
    }

    public int getResizeMode() {
        Assertions.checkStateNotNull(this.contentFrame);
        return this.contentFrame.getResizeMode();
    }

    @Deprecated
    public boolean getUseArtwork() {
        return this.artworkDisplayMode != 0;
    }

    @Deprecated
    public void setUseArtwork(boolean z) {
        setArtworkDisplayMode(!z ? 1 : 0);
    }

    public void setArtworkDisplayMode(int artworkDisplayMode) {
        Assertions.checkState(artworkDisplayMode == 0 || this.artworkView != null);
        if (this.artworkDisplayMode != artworkDisplayMode) {
            this.artworkDisplayMode = artworkDisplayMode;
            updateForCurrentTrackSelections(false);
        }
    }

    public int getArtworkDisplayMode() {
        return this.artworkDisplayMode;
    }

    public Drawable getDefaultArtwork() {
        return this.defaultArtwork;
    }

    public void setDefaultArtwork(Drawable defaultArtwork) {
        if (this.defaultArtwork != defaultArtwork) {
            this.defaultArtwork = defaultArtwork;
            updateForCurrentTrackSelections(false);
        }
    }

    public void setImageDisplayMode(int imageDisplayMode) {
        Assertions.checkState(this.imageView != null);
        if (this.imageDisplayMode != imageDisplayMode) {
            this.imageDisplayMode = imageDisplayMode;
            updateImageViewAspectRatio();
        }
    }

    public int getImageDisplayMode() {
        return this.imageDisplayMode;
    }

    public boolean getUseController() {
        return this.useController;
    }

    public void setUseController(boolean useController) {
        Assertions.checkState((useController && this.controller == null) ? false : true);
        setClickable(useController || hasOnClickListeners());
        if (this.useController == useController) {
            return;
        }
        this.useController = useController;
        if (useController()) {
            this.controller.setPlayer(this.player);
        } else if (this.controller != null) {
            this.controller.hide();
            this.controller.setPlayer(null);
        }
        updateContentDescription();
    }

    public void setShutterBackgroundColor(int color) {
        if (this.shutterView != null) {
            this.shutterView.setBackgroundColor(color);
        }
    }

    public void setKeepContentOnPlayerReset(boolean keepContentOnPlayerReset) {
        if (this.keepContentOnPlayerReset != keepContentOnPlayerReset) {
            this.keepContentOnPlayerReset = keepContentOnPlayerReset;
            updateForCurrentTrackSelections(false);
        }
    }

    public void setShowBuffering(int showBuffering) {
        if (this.showBuffering != showBuffering) {
            this.showBuffering = showBuffering;
            updateBuffering();
        }
    }

    public void setErrorMessageProvider(ErrorMessageProvider<? super PlaybackException> errorMessageProvider) {
        if (this.errorMessageProvider != errorMessageProvider) {
            this.errorMessageProvider = errorMessageProvider;
            updateErrorMessage();
        }
    }

    public void setCustomErrorMessage(CharSequence message) {
        Assertions.checkState(this.errorMessageView != null);
        this.customErrorMessage = message;
        updateErrorMessage();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (this.player != null && this.player.isCommandAvailable(16) && this.player.isPlayingAd()) {
            return super.dispatchKeyEvent(event);
        }
        boolean isDpadKey = isDpadKey(event.getKeyCode());
        if (isDpadKey && useController() && !this.controller.isFullyVisible()) {
            maybeShowController(true);
            return true;
        }
        if (dispatchMediaKeyEvent(event) || super.dispatchKeyEvent(event)) {
            maybeShowController(true);
            return true;
        }
        if (!isDpadKey || !useController()) {
            return false;
        }
        maybeShowController(true);
        return false;
    }

    public boolean dispatchMediaKeyEvent(KeyEvent event) {
        return useController() && this.controller.dispatchMediaKeyEvent(event);
    }

    public boolean isControllerFullyVisible() {
        return this.controller != null && this.controller.isFullyVisible();
    }

    public void showController() {
        showController(shouldShowControllerIndefinitely());
    }

    public void hideController() {
        if (this.controller != null) {
            this.controller.hide();
        }
    }

    public int getControllerShowTimeoutMs() {
        return this.controllerShowTimeoutMs;
    }

    public void setControllerShowTimeoutMs(int controllerShowTimeoutMs) {
        Assertions.checkStateNotNull(this.controller);
        this.controllerShowTimeoutMs = controllerShowTimeoutMs;
        if (this.controller.isFullyVisible()) {
            showController();
        }
    }

    public boolean getControllerHideOnTouch() {
        return this.controllerHideOnTouch;
    }

    public void setControllerHideOnTouch(boolean controllerHideOnTouch) {
        Assertions.checkStateNotNull(this.controller);
        this.controllerHideOnTouch = controllerHideOnTouch;
        updateContentDescription();
    }

    public boolean getControllerAutoShow() {
        return this.controllerAutoShow;
    }

    public void setControllerAutoShow(boolean controllerAutoShow) {
        this.controllerAutoShow = controllerAutoShow;
    }

    public void setControllerHideDuringAds(boolean controllerHideDuringAds) {
        this.controllerHideDuringAds = controllerHideDuringAds;
    }

    public void setControllerVisibilityListener(ControllerVisibilityListener listener) {
        this.controllerVisibilityListener = listener;
        if (listener != null) {
            setControllerVisibilityListener((PlayerControlView.VisibilityListener) null);
        }
    }

    public void setControllerAnimationEnabled(boolean animationEnabled) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setAnimationEnabled(animationEnabled);
    }

    @Deprecated
    public void setControllerVisibilityListener(PlayerControlView.VisibilityListener listener) {
        Assertions.checkStateNotNull(this.controller);
        if (this.legacyControllerVisibilityListener == listener) {
            return;
        }
        if (this.legacyControllerVisibilityListener != null) {
            this.controller.removeVisibilityListener(this.legacyControllerVisibilityListener);
        }
        this.legacyControllerVisibilityListener = listener;
        if (listener != null) {
            this.controller.addVisibilityListener(listener);
            setControllerVisibilityListener((ControllerVisibilityListener) null);
        }
    }

    public void setFullscreenButtonClickListener(FullscreenButtonClickListener listener) {
        Assertions.checkStateNotNull(this.controller);
        this.fullscreenButtonClickListener = listener;
        this.controller.setOnFullScreenModeChangedListener(this.componentListener);
    }

    @Deprecated
    public void setControllerOnFullScreenModeChangedListener(PlayerControlView.OnFullScreenModeChangedListener listener) {
        Assertions.checkStateNotNull(this.controller);
        this.fullscreenButtonClickListener = null;
        this.controller.setOnFullScreenModeChangedListener(listener);
    }

    public void setShowRewindButton(boolean showRewindButton) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowRewindButton(showRewindButton);
    }

    public void setShowFastForwardButton(boolean showFastForwardButton) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowFastForwardButton(showFastForwardButton);
    }

    public void setShowPreviousButton(boolean showPreviousButton) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowPreviousButton(showPreviousButton);
    }

    public void setShowNextButton(boolean showNextButton) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowNextButton(showNextButton);
    }

    public void setRepeatToggleModes(int repeatToggleModes) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setRepeatToggleModes(repeatToggleModes);
    }

    public void setShowShuffleButton(boolean showShuffleButton) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowShuffleButton(showShuffleButton);
    }

    public void setShowSubtitleButton(boolean showSubtitleButton) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowSubtitleButton(showSubtitleButton);
    }

    public void setShowVrButton(boolean showVrButton) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowVrButton(showVrButton);
    }

    @Deprecated
    public void setShowMultiWindowTimeBar(boolean showMultiWindowTimeBar) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowMultiWindowTimeBar(showMultiWindowTimeBar);
    }

    public void setShowPlayButtonIfPlaybackIsSuppressed(boolean showPlayButtonIfSuppressed) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowPlayButtonIfPlaybackIsSuppressed(showPlayButtonIfSuppressed);
    }

    public void setExtraAdGroupMarkers(long[] extraAdGroupTimesMs, boolean[] extraPlayedAdGroups) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setExtraAdGroupMarkers(extraAdGroupTimesMs, extraPlayedAdGroups);
    }

    public void setAspectRatioListener(AspectRatioFrameLayout.AspectRatioListener listener) {
        Assertions.checkStateNotNull(this.contentFrame);
        this.contentFrame.setAspectRatioListener(listener);
    }

    public View getVideoSurfaceView() {
        return this.surfaceView;
    }

    public FrameLayout getOverlayFrameLayout() {
        return this.overlayFrameLayout;
    }

    public SubtitleView getSubtitleView() {
        return this.subtitleView;
    }

    @Override // android.view.View
    public boolean performClick() {
        toggleControllerVisibility();
        return super.performClick();
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent ev) {
        if (!useController() || this.player == null) {
            return false;
        }
        maybeShowController(true);
        return true;
    }

    public void onResume() {
        if (this.surfaceView instanceof GLSurfaceView) {
            ((GLSurfaceView) this.surfaceView).onResume();
        }
    }

    public void onPause() {
        if (this.surfaceView instanceof GLSurfaceView) {
            ((GLSurfaceView) this.surfaceView).onPause();
        }
    }

    protected void onContentAspectRatioChanged(AspectRatioFrameLayout contentFrame, float aspectRatio) {
        if (contentFrame != null) {
            contentFrame.setAspectRatio(aspectRatio);
        }
    }

    @Override // androidx.media3.common.AdViewProvider
    public ViewGroup getAdViewGroup() {
        return (ViewGroup) Assertions.checkStateNotNull(this.adOverlayFrameLayout, "exo_ad_overlay must be present for ad playback");
    }

    @Override // androidx.media3.common.AdViewProvider
    public List<AdOverlayInfo> getAdOverlayInfos() {
        List<AdOverlayInfo> overlayViews = new ArrayList<>();
        if (this.overlayFrameLayout != null) {
            overlayViews.add(new AdOverlayInfo.Builder(this.overlayFrameLayout, 4).setDetailedReason("Transparent overlay does not impact viewability").build());
        }
        if (this.controller != null) {
            overlayViews.add(new AdOverlayInfo.Builder(this.controller, 1).build());
        }
        return ImmutableList.copyOf((Collection) overlayViews);
    }

    @EnsuresNonNullIf(expression = {"controller"}, result = true)
    private boolean useController() {
        if (this.useController) {
            Assertions.checkStateNotNull(this.controller);
            return true;
        }
        return false;
    }

    private boolean useArtwork() {
        if (this.artworkDisplayMode != 0) {
            Assertions.checkStateNotNull(this.artworkView);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleControllerVisibility() {
        if (!useController() || this.player == null) {
            return;
        }
        if (!this.controller.isFullyVisible()) {
            maybeShowController(true);
        } else if (this.controllerHideOnTouch) {
            this.controller.hide();
        }
    }

    private void maybeShowController(boolean isForced) {
        if ((!isPlayingAd() || !this.controllerHideDuringAds) && useController()) {
            boolean wasShowingIndefinitely = this.controller.isFullyVisible() && this.controller.getShowTimeoutMs() <= 0;
            boolean shouldShowIndefinitely = shouldShowControllerIndefinitely();
            if (isForced || wasShowingIndefinitely || shouldShowIndefinitely) {
                showController(shouldShowIndefinitely);
            }
        }
    }

    private boolean shouldShowControllerIndefinitely() {
        if (this.player == null) {
            return true;
        }
        int playbackState = this.player.getPlaybackState();
        return this.controllerAutoShow && !(this.player.isCommandAvailable(17) && this.player.getCurrentTimeline().isEmpty()) && (playbackState == 1 || playbackState == 4 || !((Player) Assertions.checkNotNull(this.player)).getPlayWhenReady());
    }

    private void showController(boolean showIndefinitely) {
        if (!useController()) {
            return;
        }
        this.controller.setShowTimeoutMs(showIndefinitely ? 0 : this.controllerShowTimeoutMs);
        this.controller.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPlayingAd() {
        return this.player != null && this.player.isCommandAvailable(16) && this.player.isPlayingAd() && this.player.getPlayWhenReady();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateForCurrentTrackSelections(boolean isNewPlayer) {
        Player player = this.player;
        boolean hasTracks = (player == null || !player.isCommandAvailable(30) || player.getCurrentTracks().isEmpty()) ? false : true;
        if (!this.keepContentOnPlayerReset && (!hasTracks || isNewPlayer)) {
            hideArtwork();
            closeShutter();
            hideAndClearImage();
        }
        if (!hasTracks) {
            return;
        }
        boolean hasSelectedVideoTrack = hasSelectedVideoTrack();
        boolean hasSelectedImageTrack = hasSelectedImageTrack();
        if (!hasSelectedVideoTrack && !hasSelectedImageTrack) {
            closeShutter();
            hideAndClearImage();
        }
        boolean wasVideoAndImageSet = this.shutterView != null && this.shutterView.getVisibility() == 4 && isImageSet();
        if (hasSelectedImageTrack && !hasSelectedVideoTrack && wasVideoAndImageSet) {
            closeShutter();
            showImage();
        } else if (hasSelectedVideoTrack && !hasSelectedImageTrack && wasVideoAndImageSet) {
            hideAndClearImage();
        }
        boolean shouldShowArtwork = (hasSelectedVideoTrack || hasSelectedImageTrack || !useArtwork()) ? false : true;
        if (shouldShowArtwork && (setArtworkFromMediaMetadata(player) || setDrawableArtwork(this.defaultArtwork))) {
            return;
        }
        hideArtwork();
    }

    private boolean setArtworkFromMediaMetadata(Player player) {
        if (player == null || !player.isCommandAvailable(18)) {
            return false;
        }
        MediaMetadata mediaMetadata = player.getMediaMetadata();
        if (mediaMetadata.artworkData == null) {
            return false;
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(mediaMetadata.artworkData, 0, mediaMetadata.artworkData.length);
        return setDrawableArtwork(new BitmapDrawable(getResources(), bitmap));
    }

    private boolean setDrawableArtwork(Drawable drawable) {
        if (this.artworkView != null && drawable != null) {
            int drawableWidth = drawable.getIntrinsicWidth();
            int drawableHeight = drawable.getIntrinsicHeight();
            if (drawableWidth > 0 && drawableHeight > 0) {
                float artworkLayoutAspectRatio = drawableWidth / drawableHeight;
                ImageView.ScaleType scaleStyle = ImageView.ScaleType.FIT_XY;
                if (this.artworkDisplayMode == 2) {
                    artworkLayoutAspectRatio = getWidth() / getHeight();
                    scaleStyle = ImageView.ScaleType.CENTER_CROP;
                }
                onContentAspectRatioChanged(this.contentFrame, artworkLayoutAspectRatio);
                this.artworkView.setScaleType(scaleStyle);
                this.artworkView.setImageDrawable(drawable);
                this.artworkView.setVisibility(0);
                return true;
            }
        }
        return false;
    }

    private void hideArtwork() {
        if (this.artworkView != null) {
            this.artworkView.setImageResource(android.R.color.transparent);
            this.artworkView.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasSelectedImageTrack() {
        Player player = this.player;
        return player != null && this.imageOutput != null && player.isCommandAvailable(30) && player.getCurrentTracks().isTypeSelected(4);
    }

    private boolean hasSelectedVideoTrack() {
        Player player = this.player;
        return player != null && player.isCommandAvailable(30) && player.getCurrentTracks().isTypeSelected(2);
    }

    private boolean isImageSet() {
        Drawable drawable;
        return (this.imageView == null || (drawable = this.imageView.getDrawable()) == null || drawable.getAlpha() == 0) ? false : true;
    }

    private void setImage(Drawable drawable) {
        if (this.imageView == null) {
            return;
        }
        this.imageView.setImageDrawable(drawable);
        updateImageViewAspectRatio();
    }

    private void updateImageViewAspectRatio() {
        Drawable drawable;
        if (this.imageView == null || (drawable = this.imageView.getDrawable()) == null) {
            return;
        }
        int drawableWidth = drawable.getIntrinsicWidth();
        int drawableHeight = drawable.getIntrinsicHeight();
        if (drawableWidth <= 0 || drawableHeight <= 0) {
            return;
        }
        float drawableLayoutAspectRatio = drawableWidth / drawableHeight;
        ImageView.ScaleType scaleStyle = ImageView.ScaleType.FIT_XY;
        if (this.imageDisplayMode == 1) {
            drawableLayoutAspectRatio = getWidth() / getHeight();
            scaleStyle = ImageView.ScaleType.CENTER_CROP;
        }
        if (this.imageView.getVisibility() == 0) {
            onContentAspectRatioChanged(this.contentFrame, drawableLayoutAspectRatio);
        }
        this.imageView.setScaleType(scaleStyle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideAndClearImage() {
        hideImage();
        if (this.imageView != null) {
            this.imageView.setImageResource(android.R.color.transparent);
        }
    }

    private void showImage() {
        if (this.imageView != null) {
            this.imageView.setVisibility(0);
            updateImageViewAspectRatio();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideImage() {
        if (this.imageView != null) {
            this.imageView.setVisibility(4);
        }
    }

    private void onImageAvailable(final Bitmap bitmap) {
        this.mainLooperHandler.post(new Runnable() { // from class: androidx.media3.ui.PlayerView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                PlayerView.this.m6493lambda$onImageAvailable$1$androidxmedia3uiPlayerView(bitmap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onImageAvailable$1$androidx-media3-ui-PlayerView, reason: not valid java name */
    public /* synthetic */ void m6493lambda$onImageAvailable$1$androidxmedia3uiPlayerView(Bitmap bitmap) {
        setImage(new BitmapDrawable(getResources(), bitmap));
        if (!hasSelectedVideoTrack()) {
            showImage();
            closeShutter();
        }
    }

    private void closeShutter() {
        if (this.shutterView != null) {
            this.shutterView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0021, code lost:
    
        if (r4.player.getPlayWhenReady() == false) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateBuffering() {
        /*
            r4 = this;
            android.view.View r0 = r4.bufferingView
            if (r0 == 0) goto L30
            androidx.media3.common.Player r0 = r4.player
            r1 = 0
            if (r0 == 0) goto L24
            androidx.media3.common.Player r0 = r4.player
            int r0 = r0.getPlaybackState()
            r2 = 2
            if (r0 != r2) goto L24
            int r0 = r4.showBuffering
            r3 = 1
            if (r0 == r2) goto L23
            int r0 = r4.showBuffering
            if (r0 != r3) goto L24
            androidx.media3.common.Player r0 = r4.player
            boolean r0 = r0.getPlayWhenReady()
            if (r0 == 0) goto L24
        L23:
            goto L25
        L24:
            r3 = r1
        L25:
            r0 = r3
            android.view.View r2 = r4.bufferingView
            if (r0 == 0) goto L2b
            goto L2d
        L2b:
            r1 = 8
        L2d:
            r2.setVisibility(r1)
        L30:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.ui.PlayerView.updateBuffering():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateErrorMessage() {
        if (this.errorMessageView != null) {
            if (this.customErrorMessage != null) {
                this.errorMessageView.setText(this.customErrorMessage);
                this.errorMessageView.setVisibility(0);
                return;
            }
            PlaybackException error = this.player != null ? this.player.getPlayerError() : null;
            if (error != null && this.errorMessageProvider != null) {
                CharSequence errorMessage = (CharSequence) this.errorMessageProvider.getErrorMessage(error).second;
                this.errorMessageView.setText(errorMessage);
                this.errorMessageView.setVisibility(0);
                return;
            }
            this.errorMessageView.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateContentDescription() {
        if (this.controller == null || !this.useController) {
            setContentDescription(null);
        } else if (this.controller.isFullyVisible()) {
            setContentDescription(this.controllerHideOnTouch ? getResources().getString(R.string.exo_controls_hide) : null);
        } else {
            setContentDescription(getResources().getString(R.string.exo_controls_show));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateControllerVisibility() {
        if (isPlayingAd() && this.controllerHideDuringAds) {
            hideController();
        } else {
            maybeShowController(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAspectRatio() {
        VideoSize videoSize = this.player != null ? this.player.getVideoSize() : VideoSize.UNKNOWN;
        int width = videoSize.width;
        int height = videoSize.height;
        int unappliedRotationDegrees = videoSize.unappliedRotationDegrees;
        float videoAspectRatio = (height == 0 || width == 0) ? 0.0f : (width * videoSize.pixelWidthHeightRatio) / height;
        if (this.surfaceView instanceof TextureView) {
            if (videoAspectRatio > 0.0f && (unappliedRotationDegrees == 90 || unappliedRotationDegrees == 270)) {
                videoAspectRatio = 1.0f / videoAspectRatio;
            }
            if (this.textureViewRotation != 0) {
                this.surfaceView.removeOnLayoutChangeListener(this.componentListener);
            }
            this.textureViewRotation = unappliedRotationDegrees;
            if (this.textureViewRotation != 0) {
                this.surfaceView.addOnLayoutChangeListener(this.componentListener);
            }
            applyTextureViewRotation((TextureView) this.surfaceView, this.textureViewRotation);
        }
        onContentAspectRatioChanged(this.contentFrame, this.surfaceViewIgnoresVideoAspectRatio ? 0.0f : videoAspectRatio);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (Util.SDK_INT == 34 && this.surfaceSyncGroupV34 != null) {
            this.surfaceSyncGroupV34.maybeMarkSyncReadyAndClear();
        }
    }

    private static void configureEditModeLogoV23(Context context, Resources resources, ImageView logo) {
        logo.setImageDrawable(Util.getDrawable(context, resources, R.drawable.exo_edit_mode_logo));
        logo.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color, null));
    }

    private static void configureEditModeLogo(Context context, Resources resources, ImageView logo) {
        logo.setImageDrawable(Util.getDrawable(context, resources, R.drawable.exo_edit_mode_logo));
        logo.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color));
    }

    private static void setResizeModeRaw(AspectRatioFrameLayout aspectRatioFrame, int resizeMode) {
        aspectRatioFrame.setResizeMode(resizeMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void applyTextureViewRotation(TextureView textureView, int textureViewRotation) {
        Matrix transformMatrix = new Matrix();
        float textureViewWidth = textureView.getWidth();
        float textureViewHeight = textureView.getHeight();
        if (textureViewWidth != 0.0f && textureViewHeight != 0.0f && textureViewRotation != 0) {
            float pivotX = textureViewWidth / 2.0f;
            float pivotY = textureViewHeight / 2.0f;
            transformMatrix.postRotate(textureViewRotation, pivotX, pivotY);
            RectF originalTextureRect = new RectF(0.0f, 0.0f, textureViewWidth, textureViewHeight);
            RectF rotatedTextureRect = new RectF();
            transformMatrix.mapRect(rotatedTextureRect, originalTextureRect);
            transformMatrix.postScale(textureViewWidth / rotatedTextureRect.width(), textureViewHeight / rotatedTextureRect.height(), pivotX, pivotY);
        }
        textureView.setTransform(transformMatrix);
    }

    private boolean isDpadKey(int keyCode) {
        return keyCode == 19 || keyCode == 270 || keyCode == 22 || keyCode == 271 || keyCode == 20 || keyCode == 269 || keyCode == 21 || keyCode == 268 || keyCode == 23;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes24.dex */
    public final class ComponentListener implements Player.Listener, View.OnLayoutChangeListener, View.OnClickListener, PlayerControlView.VisibilityListener, PlayerControlView.OnFullScreenModeChangedListener {
        private Object lastPeriodUidWithTracks;
        private final Timeline.Period period = new Timeline.Period();

        public ComponentListener() {
        }

        @Override // androidx.media3.common.Player.Listener
        public void onCues(CueGroup cueGroup) {
            if (PlayerView.this.subtitleView != null) {
                PlayerView.this.subtitleView.setCues(cueGroup.cues);
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onVideoSizeChanged(VideoSize videoSize) {
            if (!videoSize.equals(VideoSize.UNKNOWN) && PlayerView.this.player != null && PlayerView.this.player.getPlaybackState() != 1) {
                PlayerView.this.updateAspectRatio();
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onSurfaceSizeChanged(int width, int height) {
            if (Util.SDK_INT == 34 && (PlayerView.this.surfaceView instanceof SurfaceView)) {
                SurfaceSyncGroupCompatV34 surfaceSyncGroupCompatV34 = (SurfaceSyncGroupCompatV34) Assertions.checkNotNull(PlayerView.this.surfaceSyncGroupV34);
                Handler handler = PlayerView.this.mainLooperHandler;
                SurfaceView surfaceView = (SurfaceView) PlayerView.this.surfaceView;
                final PlayerView playerView = PlayerView.this;
                surfaceSyncGroupCompatV34.postRegister(handler, surfaceView, new Runnable() { // from class: androidx.media3.ui.PlayerView$ComponentListener$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        PlayerView.this.invalidate();
                    }
                });
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onRenderedFirstFrame() {
            if (PlayerView.this.shutterView != null) {
                PlayerView.this.shutterView.setVisibility(4);
                if (PlayerView.this.hasSelectedImageTrack()) {
                    PlayerView.this.hideImage();
                } else {
                    PlayerView.this.hideAndClearImage();
                }
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTracksChanged(Tracks tracks) {
            Timeline timeline;
            Player player = (Player) Assertions.checkNotNull(PlayerView.this.player);
            if (player.isCommandAvailable(17)) {
                timeline = player.getCurrentTimeline();
            } else {
                timeline = Timeline.EMPTY;
            }
            if (timeline.isEmpty()) {
                this.lastPeriodUidWithTracks = null;
            } else if (player.isCommandAvailable(30) && !player.getCurrentTracks().isEmpty()) {
                this.lastPeriodUidWithTracks = timeline.getPeriod(player.getCurrentPeriodIndex(), this.period, true).uid;
            } else if (this.lastPeriodUidWithTracks != null) {
                int lastPeriodIndexWithTracks = timeline.getIndexOfPeriod(this.lastPeriodUidWithTracks);
                if (lastPeriodIndexWithTracks != -1) {
                    int lastWindowIndexWithTracks = timeline.getPeriod(lastPeriodIndexWithTracks, this.period).windowIndex;
                    if (player.getCurrentMediaItemIndex() == lastWindowIndexWithTracks) {
                        return;
                    }
                }
                this.lastPeriodUidWithTracks = null;
            }
            PlayerView.this.updateForCurrentTrackSelections(false);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackStateChanged(int playbackState) {
            PlayerView.this.updateBuffering();
            PlayerView.this.updateErrorMessage();
            PlayerView.this.updateControllerVisibility();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {
            PlayerView.this.updateBuffering();
            PlayerView.this.updateControllerVisibility();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPositionDiscontinuity(Player.PositionInfo oldPosition, Player.PositionInfo newPosition, int reason) {
            if (PlayerView.this.isPlayingAd() && PlayerView.this.controllerHideDuringAds) {
                PlayerView.this.hideController();
            }
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            PlayerView.applyTextureViewRotation((TextureView) view, PlayerView.this.textureViewRotation);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PlayerView.this.toggleControllerVisibility();
        }

        @Override // androidx.media3.ui.PlayerControlView.VisibilityListener
        public void onVisibilityChange(int visibility) {
            PlayerView.this.updateContentDescription();
            if (PlayerView.this.controllerVisibilityListener != null) {
                PlayerView.this.controllerVisibilityListener.onVisibilityChanged(visibility);
            }
        }

        @Override // androidx.media3.ui.PlayerControlView.OnFullScreenModeChangedListener
        public void onFullScreenModeChanged(boolean isFullScreen) {
            if (PlayerView.this.fullscreenButtonClickListener != null) {
                PlayerView.this.fullscreenButtonClickListener.onFullscreenButtonClick(isFullScreen);
            }
        }
    }

    /* loaded from: classes24.dex */
    private static class Api34 {
        private Api34() {
        }

        public static void setSurfaceLifecycleToFollowsAttachment(SurfaceView surfaceView) {
            surfaceView.setSurfaceLifecycle(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes24.dex */
    public static final class SurfaceSyncGroupCompatV34 {
        SurfaceSyncGroup surfaceSyncGroup;

        private SurfaceSyncGroupCompatV34() {
        }

        public void postRegister(Handler mainLooperHandler, final SurfaceView surfaceView, final Runnable invalidate) {
            mainLooperHandler.post(new Runnable() { // from class: androidx.media3.ui.PlayerView$SurfaceSyncGroupCompatV34$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PlayerView.SurfaceSyncGroupCompatV34.this.m6495xd2b35cc8(surfaceView, invalidate);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$postRegister$1$androidx-media3-ui-PlayerView$SurfaceSyncGroupCompatV34, reason: not valid java name */
        public /* synthetic */ void m6495xd2b35cc8(SurfaceView surfaceView, Runnable invalidate) {
            AttachedSurfaceControl rootSurfaceControl = surfaceView.getRootSurfaceControl();
            if (rootSurfaceControl == null) {
                return;
            }
            this.surfaceSyncGroup = new SurfaceSyncGroup("exo-sync-b-334901521");
            Assertions.checkState(this.surfaceSyncGroup.add(rootSurfaceControl, new Runnable() { // from class: androidx.media3.ui.PlayerView$SurfaceSyncGroupCompatV34$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    PlayerView.SurfaceSyncGroupCompatV34.lambda$postRegister$0();
                }
            }));
            invalidate.run();
            rootSurfaceControl.applyTransactionOnDraw(new SurfaceControl.Transaction());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$postRegister$0() {
        }

        public void maybeMarkSyncReadyAndClear() {
            if (this.surfaceSyncGroup != null) {
                this.surfaceSyncGroup.markSyncReady();
                this.surfaceSyncGroup = null;
            }
        }
    }
}
