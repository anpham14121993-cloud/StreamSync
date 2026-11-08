package com.streamsync.app.data.repository;

import com.streamsync.app.data.local.WatchProgressEntity;
import com.streamsync.app.data.model.WatchProgress;
import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;

/* compiled from: SyncRepository.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\f\u0010\u0005\u001a\u00020\u0002*\u00020\u0001H\u0002¨\u0006\u0006"}, d2 = {"toEntity", "Lcom/streamsync/app/data/local/WatchProgressEntity;", "Lcom/streamsync/app/data/model/WatchProgress;", "pendingSync", "", "toModel", "app_mobileDebug"}, k = 2, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class SyncRepositoryKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final WatchProgress toModel(WatchProgressEntity $this$toModel) {
        return new WatchProgress($this$toModel.getMediaId(), $this$toModel.getPositionMs(), $this$toModel.getDurationMs(), $this$toModel.getUpdatedAtEpochMs(), $this$toModel.getDeviceId(), $this$toModel.getStreamUrl(), $this$toModel.getAddonId(), $this$toModel.getAddonName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WatchProgressEntity toEntity(WatchProgress $this$toEntity, boolean pendingSync) {
        return new WatchProgressEntity($this$toEntity.getMediaId(), $this$toEntity.getPositionMs(), $this$toEntity.getDurationMs(), $this$toEntity.getUpdatedAtEpochMs(), $this$toEntity.getDeviceId(), pendingSync, $this$toEntity.getStreamUrl(), $this$toEntity.getAddonId(), $this$toEntity.getAddonName());
    }
}
