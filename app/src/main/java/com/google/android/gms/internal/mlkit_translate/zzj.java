package com.google.android.gms.internal.mlkit_translate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:translate@@17.0.3 */
/* loaded from: classes24.dex */
public final class zzj {
    public static final /* synthetic */ int zza = 0;

    @CheckForNull
    private static final Object zzb = zza();

    static {
        if (zzb != null) {
            zzb("getStackTraceElement", Throwable.class, Integer.TYPE);
        }
        Object obj = zzb;
        if (obj == null) {
            return;
        }
        zzc(obj);
    }

    @CheckForNull
    private static Object zza() {
        try {
            return Class.forName("sun.misc.SharedSecrets", false, null).getMethod("getJavaLangAccess", new Class[0]).invoke(null, new Object[0]);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            return null;
        }
    }

    @CheckForNull
    private static Method zzb(String str, Class... clsArr) throws ThreadDeath {
        try {
            return Class.forName("sun.misc.JavaLangAccess", false, null).getMethod(str, clsArr);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            return null;
        }
    }

    @CheckForNull
    private static Method zzc(Object obj) {
        try {
            Method zzb2 = zzb("getStackTraceDepth", Throwable.class);
            if (zzb2 == null) {
                return null;
            }
            zzb2.invoke(obj, new Throwable());
            return zzb2;
        } catch (IllegalAccessException | UnsupportedOperationException | InvocationTargetException e) {
            return null;
        }
    }
}
