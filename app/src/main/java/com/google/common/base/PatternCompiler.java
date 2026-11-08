package com.google.common.base;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes24.dex */
public interface PatternCompiler {
    CommonPattern compile(String pattern);

    boolean isPcreLike();
}
