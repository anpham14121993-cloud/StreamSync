package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentDiscovery;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.ComponentRuntime;
import com.google.mlkit.common.internal.MlKitComponentDiscoveryService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes25.dex */
public class MlKitContext {
    private static final Object zza = new Object();
    private static MlKitContext zzb;
    private ComponentRuntime zzc;

    private MlKitContext() {
    }

    public static MlKitContext getInstance() {
        MlKitContext mlKitContext;
        synchronized (zza) {
            Preconditions.checkState(zzb != null, "MlKitContext has not been initialized");
            mlKitContext = (MlKitContext) Preconditions.checkNotNull(zzb);
        }
        return mlKitContext;
    }

    public static MlKitContext initialize(Context context, List<ComponentRegistrar> list) {
        MlKitContext mlKitContext;
        synchronized (zza) {
            Preconditions.checkState(zzb == null, "MlKitContext is already initialized");
            zzb = new MlKitContext();
            MlKitContext mlKitContext2 = zzb;
            Context zzc = zzc(context);
            HashMap hashMap = new HashMap();
            for (ComponentRegistrar componentRegistrar : list) {
                hashMap.put(componentRegistrar.getClass(), componentRegistrar);
            }
            mlKitContext2.zzc = new ComponentRuntime(TaskExecutors.MAIN_THREAD, new ArrayList(hashMap.values()), (Component<?>[]) new Component[]{Component.of(zzc, Context.class, new Class[0]), Component.of(mlKitContext2, MlKitContext.class, new Class[0])});
            mlKitContext2.zzc.initializeEagerComponents(true);
            mlKitContext = zzb;
        }
        return mlKitContext;
    }

    public static MlKitContext initializeIfNeeded(Context context) {
        MlKitContext mlKitContext;
        synchronized (zza) {
            mlKitContext = zzb;
            if (mlKitContext == null) {
                mlKitContext = zza(context);
            }
        }
        return mlKitContext;
    }

    public static MlKitContext zza(Context context) {
        MlKitContext zzb2;
        synchronized (zza) {
            zzb2 = zzb(context, TaskExecutors.MAIN_THREAD);
        }
        return zzb2;
    }

    public static MlKitContext zzb(Context context, Executor executor) {
        MlKitContext mlKitContext;
        synchronized (zza) {
            Preconditions.checkState(zzb == null, "MlKitContext is already initialized");
            zzb = new MlKitContext();
            MlKitContext mlKitContext2 = zzb;
            Context zzc = zzc(context);
            mlKitContext2.zzc = ComponentRuntime.builder(executor).addLazyComponentRegistrars(ComponentDiscovery.forContext(zzc, MlKitComponentDiscoveryService.class).discoverLazy()).addComponent(Component.of(zzc, Context.class, new Class[0])).addComponent(Component.of(mlKitContext2, MlKitContext.class, new Class[0])).build();
            mlKitContext2.zzc.initializeEagerComponents(true);
            mlKitContext = zzb;
        }
        return mlKitContext;
    }

    private static Context zzc(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }

    public <T> T get(Class<T> cls) {
        Preconditions.checkState(zzb == this, "MlKitContext has been deleted");
        Preconditions.checkNotNull(this.zzc);
        return (T) this.zzc.get(cls);
    }

    public Context getApplicationContext() {
        return (Context) get(Context.class);
    }

    public static MlKitContext initializeIfNeeded(Context context, List<ComponentRegistrar> list) {
        MlKitContext mlKitContext;
        synchronized (zza) {
            mlKitContext = zzb;
            if (mlKitContext == null) {
                mlKitContext = initialize(context, list);
            }
        }
        return mlKitContext;
    }

    public static MlKitContext initializeIfNeeded(Context context, Executor executor) {
        MlKitContext mlKitContext;
        synchronized (zza) {
            mlKitContext = zzb;
            if (mlKitContext == null) {
                mlKitContext = zzb(context, executor);
            }
        }
        return mlKitContext;
    }
}
