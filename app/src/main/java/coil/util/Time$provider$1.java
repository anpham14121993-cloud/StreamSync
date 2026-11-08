package coil.util;

import com.streamsync.app.settings.BufferCeiling;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: Time.kt */
@Metadata(k = 3, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
/* synthetic */ class Time$provider$1 extends FunctionReferenceImpl implements Function0<Long> {
    public static final Time$provider$1 INSTANCE = new Time$provider$1();

    Time$provider$1() {
        super(0, System.class, "currentTimeMillis", "currentTimeMillis()J", 0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Long invoke() {
        return Long.valueOf(System.currentTimeMillis());
    }
}
