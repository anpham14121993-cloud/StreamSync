package com.google.common.io;

import java.io.IOException;

@ElementTypesAreNonnullByDefault
/* loaded from: classes25.dex */
public interface LineProcessor<T> {
    @ParametricNullness
    T getResult();

    boolean processLine(String line) throws IOException;
}
