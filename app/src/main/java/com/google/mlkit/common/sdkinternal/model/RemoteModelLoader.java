package com.google.mlkit.common.sdkinternal.model;

import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.io.File;
import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes25.dex */
public class RemoteModelLoader {
    private static final GmsLogger zza = new GmsLogger("RemoteModelLoader", "");
    private static final Map zzb = new HashMap();
    private final MlKitContext zzc;
    private final RemoteModel zzd;
    private final RemoteModelDownloadManager zze;
    private final RemoteModelFileManager zzf;
    private final RemoteModelLoaderHelper zzg;
    private boolean zzi = true;
    private final zzsh zzh = zzss.zzb("common");

    private RemoteModelLoader(MlKitContext mlKitContext, RemoteModel remoteModel, ModelValidator modelValidator, RemoteModelLoaderHelper remoteModelLoaderHelper, RemoteModelFileMover remoteModelFileMover) {
        this.zzf = new RemoteModelFileManager(mlKitContext, remoteModel, modelValidator, new ModelFileHelper(mlKitContext), remoteModelFileMover);
        this.zze = RemoteModelDownloadManager.getInstance(mlKitContext, remoteModel, new ModelFileHelper(mlKitContext), this.zzf, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
        this.zzg = remoteModelLoaderHelper;
        this.zzc = mlKitContext;
        this.zzd = remoteModel;
    }

    public static synchronized RemoteModelLoader getInstance(MlKitContext mlKitContext, RemoteModel remoteModel, ModelValidator checker, RemoteModelLoaderHelper loaderHelper, RemoteModelFileMover modelFileMover) {
        RemoteModelLoader remoteModelLoader;
        synchronized (RemoteModelLoader.class) {
            String uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
            if (!zzb.containsKey(uniqueModelNameForPersist)) {
                zzb.put(uniqueModelNameForPersist, new RemoteModelLoader(mlKitContext, remoteModel, checker, loaderHelper, modelFileMover));
            }
            remoteModelLoader = (RemoteModelLoader) zzb.get(uniqueModelNameForPersist);
        }
        return remoteModelLoader;
    }

    private final MappedByteBuffer zza(String str) throws MlKitException {
        return this.zzg.loadModelAtPath(str);
    }

    private final MappedByteBuffer zzb(File file) throws MlKitException {
        try {
            return zza(file.getAbsolutePath());
        } catch (Exception e) {
            this.zzf.zzc(file);
            throw new MlKitException("Failed to load newly downloaded model.", 14, e);
        }
    }

    public RemoteModel getRemoteModel() {
        return this.zzd;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00cd A[Catch: all -> 0x0112, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0020, B:9:0x0028, B:11:0x00cd, B:13:0x00de, B:18:0x00e8, B:21:0x00ee, B:22:0x010c, B:23:0x010d, B:25:0x0030, B:27:0x0054, B:30:0x005e, B:32:0x007e, B:34:0x0086, B:36:0x009a, B:38:0x00a2, B:40:0x00bc), top: B:2:0x0001, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x010d A[Catch: all -> 0x0112, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0020, B:9:0x0028, B:11:0x00cd, B:13:0x00de, B:18:0x00e8, B:21:0x00ee, B:22:0x010c, B:23:0x010d, B:25:0x0030, B:27:0x0054, B:30:0x005e, B:32:0x007e, B:34:0x0086, B:36:0x009a, B:38:0x00a2, B:40:0x00bc), top: B:2:0x0001, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.nio.MappedByteBuffer load() throws com.google.mlkit.common.MlKitException {
        /*
            Method dump skipped, instructions count: 277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelLoader.load():java.nio.MappedByteBuffer");
    }
}
