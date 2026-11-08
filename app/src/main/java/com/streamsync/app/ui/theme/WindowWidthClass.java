package com.streamsync.app.ui.theme;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* compiled from: WindowWidthClass.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/streamsync/app/ui/theme/WindowWidthClass;", "", "(Ljava/lang/String;I)V", "isCompact", "", "()Z", "COMPACT", "MEDIUM", "EXPANDED", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public enum WindowWidthClass {
    COMPACT,
    MEDIUM,
    EXPANDED;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<WindowWidthClass> getEntries() {
        return $ENTRIES;
    }

    public final boolean isCompact() {
        return this == COMPACT;
    }
}
