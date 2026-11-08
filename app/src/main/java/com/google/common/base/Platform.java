package com.google.common.base;

import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes24.dex */
public final class Platform {
    private static final PatternCompiler patternCompiler = loadPatternCompiler();

    private Platform() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharMatcher precomputeCharMatcher(CharMatcher matcher) {
        return matcher.precomputedInternal();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends Enum<T>> Optional<T> getEnumIfPresent(Class<T> enumClass, String value) {
        WeakReference<? extends Enum<?>> ref = Enums.getEnumConstants(enumClass).get(value);
        return ref == null ? Optional.absent() : Optional.of(enumClass.cast(ref.get()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String formatCompact4Digits(double value) {
        return String.format(Locale.ROOT, "%.4g", Double.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean stringIsNullOrEmpty(@CheckForNull String string) {
        return string == null || string.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String nullToEmpty(@CheckForNull String string) {
        return string == null ? "" : string;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CheckForNull
    public static String emptyToNull(@CheckForNull String string) {
        if (stringIsNullOrEmpty(string)) {
            return null;
        }
        return string;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CommonPattern compilePattern(String pattern) {
        Preconditions.checkNotNull(pattern);
        return patternCompiler.compile(pattern);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean patternCompilerIsPcreLike() {
        return patternCompiler.isPcreLike();
    }

    private static PatternCompiler loadPatternCompiler() {
        return new JdkPatternCompiler();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes24.dex */
    public static final class JdkPatternCompiler implements PatternCompiler {
        private JdkPatternCompiler() {
        }

        @Override // com.google.common.base.PatternCompiler
        public CommonPattern compile(String pattern) {
            return new JdkPattern(Pattern.compile(pattern));
        }

        @Override // com.google.common.base.PatternCompiler
        public boolean isPcreLike() {
            return true;
        }
    }
}
