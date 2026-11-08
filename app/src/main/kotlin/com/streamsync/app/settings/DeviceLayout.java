package com.streamsync.app.settings;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* compiled from: UiSettingsStore.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006j\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/streamsync/app/settings/DeviceLayout;", "", "(Ljava/lang/String;I)V", "description", "", "getDescription", "()Ljava/lang/String;", "label", "getLabel", "PHONE", "TABLET", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public enum DeviceLayout {
    PHONE,
    TABLET;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    /* compiled from: UiSettingsStore.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DeviceLayout.values().length];
            try {
                iArr[DeviceLayout.PHONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[DeviceLayout.TABLET.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static EnumEntries<DeviceLayout> getEntries() {
        return $ENTRIES;
    }

    public final String getLabel() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return "Điện thoại";
            case 2:
                return "Máy tính bảng";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final String getDescription() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return "Nội dung canh giữa, đúng cỡ như trên điện thoại";
            case 2:
                return "Nội dung trải hết màn hình, không chừa hai bên";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
