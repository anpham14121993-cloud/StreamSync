package androidx.media3.extractor.jpeg;

import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.metadata.mp4.MotionPhotoMetadata;
import java.util.List;

/* loaded from: classes24.dex */
final class MotionPhotoDescription {
    public final List<ContainerItem> items;
    public final long photoPresentationTimestampUs;

    /* loaded from: classes24.dex */
    public static final class ContainerItem {
        public final long length;
        public final String mime;
        public final long padding;
        public final String semantic;

        public ContainerItem(String mime, String semantic, long length, long padding) {
            this.mime = mime;
            this.semantic = semantic;
            this.length = length;
            this.padding = padding;
        }
    }

    public MotionPhotoDescription(long photoPresentationTimestampUs, List<ContainerItem> items) {
        this.photoPresentationTimestampUs = photoPresentationTimestampUs;
        this.items = items;
    }

    public MotionPhotoMetadata getMotionPhotoMetadata(long motionPhotoLength) {
        if (this.items.size() < 2) {
            return null;
        }
        long photoStartPosition = -1;
        long photoLength = -1;
        long mp4StartPosition = -1;
        long mp4Length = -1;
        boolean itemContainsMp4 = false;
        long itemStartPosition = motionPhotoLength;
        long itemEndPosition = motionPhotoLength;
        for (int i = this.items.size() - 1; i >= 0; i--) {
            ContainerItem item = this.items.get(i);
            itemContainsMp4 |= MimeTypes.VIDEO_MP4.equals(item.mime);
            long itemEndPosition2 = itemStartPosition;
            if (i == 0) {
                long itemStartPosition2 = item.padding;
                itemEndPosition = itemEndPosition2 - itemStartPosition2;
                itemStartPosition = 0;
            } else {
                long itemEndPosition3 = item.length;
                itemStartPosition -= itemEndPosition3;
                itemEndPosition = itemEndPosition2;
            }
            if (itemContainsMp4 && itemStartPosition != itemEndPosition) {
                mp4StartPosition = itemStartPosition;
                mp4Length = itemEndPosition - itemStartPosition;
                itemContainsMp4 = false;
            }
            if (i == 0) {
                photoStartPosition = itemStartPosition;
                photoLength = itemEndPosition;
            }
        }
        if (mp4StartPosition == -1 || mp4Length == -1 || photoStartPosition == -1 || photoLength == -1) {
            return null;
        }
        return new MotionPhotoMetadata(photoStartPosition, photoLength, this.photoPresentationTimestampUs, mp4StartPosition, mp4Length);
    }
}
