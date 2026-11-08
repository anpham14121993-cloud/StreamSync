package com.google.common.util.concurrent;

import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes25.dex */
public final class LazyLogger {
    private volatile Logger logger;
    private final String loggerName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LazyLogger(Class<?> ownerOfLogger) {
        this.loggerName = ownerOfLogger.getName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Logger get() {
        Logger local = this.logger;
        if (local != null) {
            return local;
        }
        synchronized (this) {
            Logger local2 = this.logger;
            if (local2 != null) {
                return local2;
            }
            Logger logger = Logger.getLogger(this.loggerName);
            this.logger = logger;
            return logger;
        }
    }
}
