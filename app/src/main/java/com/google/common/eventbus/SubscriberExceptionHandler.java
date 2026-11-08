package com.google.common.eventbus;

@ElementTypesAreNonnullByDefault
/* loaded from: classes25.dex */
public interface SubscriberExceptionHandler {
    void handleException(Throwable exception, SubscriberExceptionContext context);
}
