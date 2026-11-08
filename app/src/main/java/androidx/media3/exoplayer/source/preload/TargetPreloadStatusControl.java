package androidx.media3.exoplayer.source.preload;

/* loaded from: classes2.dex */
public interface TargetPreloadStatusControl<T> {

    /* loaded from: classes2.dex */
    public interface PreloadStatus {
        int getStage();

        long getValue();
    }

    PreloadStatus getTargetPreloadStatus(T t);
}
