package com.google.mlkit.common.sdkinternal.model;

import com.google.mlkit.common.MlKitException;
import java.nio.MappedByteBuffer;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes25.dex */
public interface RemoteModelLoaderHelper {
    MappedByteBuffer loadModelAtPath(String str) throws MlKitException;
}
