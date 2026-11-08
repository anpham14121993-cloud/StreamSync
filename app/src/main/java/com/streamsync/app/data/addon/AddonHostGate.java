package com.streamsync.app.data.addon;

import com.streamsync.app.settings.BufferCeiling;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: AddonHostGate.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001 B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010J\u000e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0010J\u000e\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0010J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010J\u001d\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u001aJ\u000e\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010J\u0006\u0010\u001c\u001a\u00020\u0013J\u0010\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0011H\u0002J\u000e\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0010J\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/streamsync/app/data/addon/AddonHostGate;", "", "()V", "MAX_LEVEL", "", "MAX_POLL_MS", "", "POLL_MS", "SPACING_MS", "", "getSPACING_MS", "()[J", "START_LEVEL", "SUCCESSES_PER_STEP", "hosts", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/streamsync/app/data/addon/AddonHostGate$HostState;", "acquire", "", "host", "cooldownUntilOf", "levelOf", "onSuccess", "penalize", "retryAfterMs", "(Ljava/lang/String;Ljava/lang/Long;)V", "release", "resetForTest", "spacingOf", "state", "stateOf", "HostState", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes14.dex */
public final class AddonHostGate {
    public static final int MAX_LEVEL = 4;
    private static final long MAX_POLL_MS = 1000;
    private static final long POLL_MS = 20;
    public static final int START_LEVEL = 2;
    private static final int SUCCESSES_PER_STEP = 6;
    public static final AddonHostGate INSTANCE = new AddonHostGate();
    private static final long[] SPACING_MS = {1000, 1000, 300, 0};
    private static final ConcurrentHashMap<String, HostState> hosts = new ConcurrentHashMap<>();
    public static final int $stable = 8;

    private AddonHostGate() {
    }

    public final long[] getSPACING_MS() {
        return SPACING_MS;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AddonHostGate.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0006\"\u0004\b\u001b\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/streamsync/app/data/addon/AddonHostGate$HostState;", "", "()V", "active", "", "getActive", "()I", "setActive", "(I)V", "cooldownUntilMs", "", "getCooldownUntilMs", "()J", "setCooldownUntilMs", "(J)V", "lastSentMs", "getLastSentMs", "setLastSentMs", "level", "getLevel", "setLevel", "lock", "Ljava/lang/Object;", "getLock", "()Ljava/lang/Object;", "streak", "getStreak", "setStreak", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes14.dex */
    public static final class HostState {
        private int active;
        private long cooldownUntilMs;
        private long lastSentMs;
        private int streak;
        private final Object lock = new Object();
        private int level = 1;

        public final Object getLock() {
            return this.lock;
        }

        public final int getActive() {
            return this.active;
        }

        public final void setActive(int i) {
            this.active = i;
        }

        public final int getLevel() {
            return this.level;
        }

        public final void setLevel(int i) {
            this.level = i;
        }

        public final long getCooldownUntilMs() {
            return this.cooldownUntilMs;
        }

        public final void setCooldownUntilMs(long j) {
            this.cooldownUntilMs = j;
        }

        public final long getLastSentMs() {
            return this.lastSentMs;
        }

        public final void setLastSentMs(long j) {
            this.lastSentMs = j;
        }

        public final int getStreak() {
            return this.streak;
        }

        public final void setStreak(int i) {
            this.streak = i;
        }
    }

    public final void acquire(String host) {
        Intrinsics.checkNotNullParameter(host, "host");
        HostState state = stateOf(host);
        synchronized (state.getLock()) {
            while (true) {
                long now = System.currentTimeMillis();
                long readyAt = Math.max(state.getCooldownUntilMs(), state.getLastSentMs() + INSTANCE.spacingOf(state));
                if (now >= readyAt && state.getActive() <= state.getLevel()) {
                    state.setActive(state.getActive() + 1);
                    state.setLastSentMs(now);
                } else {
                    state.getLock().wait(Math.min(Math.max(readyAt - now, 20L), 1000L));
                }
            }
        }
    }

    public final void release(String host) {
        Intrinsics.checkNotNullParameter(host, "host");
        ConcurrentHashMap<String, HostState> concurrentHashMap = hosts;
        String lowerCase = host.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        HostState state = concurrentHashMap.get(lowerCase);
        if (state == null) {
            return;
        }
        synchronized (state.getLock()) {
            if (state.getActive() > 0) {
                state.setActive(state.getActive() - 1);
            }
            state.getLock().notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void penalize(String host, Long retryAfterMs) {
        Intrinsics.checkNotNullParameter(host, "host");
        HostState state = stateOf(host);
        synchronized (state.getLock()) {
            state.setLevel(0);
            state.setStreak(0);
            long until = System.currentTimeMillis() + (retryAfterMs != null ? retryAfterMs.longValue() : 800L);
            if (until > state.getCooldownUntilMs()) {
                state.setCooldownUntilMs(until);
            }
            state.getLock().notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void onSuccess(String host) {
        Intrinsics.checkNotNullParameter(host, "host");
        HostState state = stateOf(host);
        synchronized (state.getLock()) {
            if (state.getLevel() >= 3) {
                return;
            }
            state.setStreak(state.getStreak() + 1);
            if (state.getStreak() >= 6) {
                state.setLevel(state.getLevel() + 1);
                state.setStreak(0);
            }
            state.getLock().notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final int levelOf(String host) {
        int level;
        Intrinsics.checkNotNullParameter(host, "host");
        HostState s = stateOf(host);
        synchronized (s.getLock()) {
            level = s.getLevel();
        }
        return level;
    }

    public final long spacingOf(String host) {
        long spacingOf;
        Intrinsics.checkNotNullParameter(host, "host");
        HostState s = stateOf(host);
        synchronized (s.getLock()) {
            spacingOf = INSTANCE.spacingOf(s);
        }
        return spacingOf;
    }

    public final long cooldownUntilOf(String host) {
        long cooldownUntilMs;
        Intrinsics.checkNotNullParameter(host, "host");
        ConcurrentHashMap<String, HostState> concurrentHashMap = hosts;
        String lowerCase = host.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        HostState it = concurrentHashMap.get(lowerCase);
        if (it != null) {
            synchronized (it.getLock()) {
                cooldownUntilMs = it.getCooldownUntilMs();
            }
            return cooldownUntilMs;
        }
        return 0L;
    }

    public final void resetForTest() {
        hosts.clear();
    }

    private final HostState stateOf(String host) {
        HostState putIfAbsent;
        ConcurrentMap $this$getOrPut$iv = hosts;
        String lowerCase = host.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        HostState hostState = $this$getOrPut$iv.get(lowerCase);
        if (hostState == null && (putIfAbsent = $this$getOrPut$iv.putIfAbsent(lowerCase, (hostState = new HostState()))) != null) {
            hostState = putIfAbsent;
        }
        Intrinsics.checkNotNullExpressionValue(hostState, "getOrPut(...)");
        return hostState;
    }

    private final long spacingOf(HostState state) {
        return SPACING_MS[RangesKt.coerceIn(state.getLevel(), 0, SPACING_MS.length - 1)];
    }
}
