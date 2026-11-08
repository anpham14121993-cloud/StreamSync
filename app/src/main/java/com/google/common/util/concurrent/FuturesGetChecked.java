package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes25.dex */
public final class FuturesGetChecked {
    private static final Ordering<List<Class<?>>> ORDERING_BY_CONSTRUCTOR_PARAMETER_LIST = Ordering.natural().onResultOf(new Function() { // from class: com.google.common.util.concurrent.FuturesGetChecked$$ExternalSyntheticLambda0
        @Override // com.google.common.base.Function
        public final Object apply(Object obj) {
            Comparable valueOf;
            valueOf = Boolean.valueOf(((List) obj).contains(String.class));
            return valueOf;
        }
    }).compound(Ordering.natural().onResultOf(new Function() { // from class: com.google.common.util.concurrent.FuturesGetChecked$$ExternalSyntheticLambda1
        @Override // com.google.common.base.Function
        public final Object apply(Object obj) {
            Comparable valueOf;
            valueOf = Boolean.valueOf(((List) obj).contains(Throwable.class));
            return valueOf;
        }
    })).reverse();
    private static final Ordering<Constructor<?>> WITH_STRING_PARAM_THEN_WITH_THROWABLE_PARAM = ORDERING_BY_CONSTRUCTOR_PARAMETER_LIST.onResultOf(new Function() { // from class: com.google.common.util.concurrent.FuturesGetChecked$$ExternalSyntheticLambda2
        @Override // com.google.common.base.Function
        public final Object apply(Object obj) {
            List asList;
            asList = Arrays.asList(((Constructor) obj).getParameterTypes());
            return asList;
        }
    });

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes25.dex */
    public interface GetCheckedTypeValidator {
        void validateClass(Class<? extends Exception> exceptionClass);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @ParametricNullness
    public static <V, X extends Exception> V getChecked(Future<V> future, Class<X> cls) throws Exception {
        return (V) getChecked(bestGetCheckedTypeValidator(), future, cls);
    }

    @ParametricNullness
    static <V, X extends Exception> V getChecked(GetCheckedTypeValidator validator, Future<V> future, Class<X> exceptionClass) throws Exception {
        validator.validateClass(exceptionClass);
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw newWithCause(exceptionClass, e);
        } catch (ExecutionException e2) {
            wrapAndThrowExceptionOrError(e2.getCause(), exceptionClass);
            throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @ParametricNullness
    public static <V, X extends Exception> V getChecked(Future<V> future, Class<X> exceptionClass, long timeout, TimeUnit unit) throws Exception {
        bestGetCheckedTypeValidator().validateClass(exceptionClass);
        try {
            return future.get(timeout, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw newWithCause(exceptionClass, e);
        } catch (ExecutionException e2) {
            wrapAndThrowExceptionOrError(e2.getCause(), exceptionClass);
            throw new AssertionError();
        } catch (TimeoutException e3) {
            throw newWithCause(exceptionClass, e3);
        }
    }

    private static GetCheckedTypeValidator bestGetCheckedTypeValidator() {
        return GetCheckedTypeValidatorHolder.BEST_VALIDATOR;
    }

    static GetCheckedTypeValidator weakSetValidator() {
        return GetCheckedTypeValidatorHolder.WeakSetValidator.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes25.dex */
    public static class GetCheckedTypeValidatorHolder {
        static final GetCheckedTypeValidator BEST_VALIDATOR = getBestValidator();

        GetCheckedTypeValidatorHolder() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes25.dex */
        public enum WeakSetValidator implements GetCheckedTypeValidator {
            INSTANCE;

            private static final Set<WeakReference<Class<? extends Exception>>> validClasses = new CopyOnWriteArraySet();

            @Override // com.google.common.util.concurrent.FuturesGetChecked.GetCheckedTypeValidator
            public void validateClass(Class<? extends Exception> exceptionClass) {
                for (WeakReference<Class<? extends Exception>> knownGood : validClasses) {
                    if (exceptionClass.equals(knownGood.get())) {
                        return;
                    }
                }
                FuturesGetChecked.checkExceptionClassValidity(exceptionClass);
                if (validClasses.size() > 1000) {
                    validClasses.clear();
                }
                validClasses.add(new WeakReference<>(exceptionClass));
            }
        }

        static GetCheckedTypeValidator getBestValidator() {
            return FuturesGetChecked.weakSetValidator();
        }
    }

    private static <X extends Exception> void wrapAndThrowExceptionOrError(Throwable cause, Class<X> exceptionClass) throws Exception {
        if (cause instanceof Error) {
            throw new ExecutionError((Error) cause);
        }
        if (cause instanceof RuntimeException) {
            throw new UncheckedExecutionException(cause);
        }
        throw newWithCause(exceptionClass, cause);
    }

    private static boolean hasConstructorUsableByGetChecked(Class<? extends Exception> exceptionClass) {
        try {
            newWithCause(exceptionClass, new Exception());
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static <X extends Exception> X newWithCause(Class<X> exceptionClass, Throwable cause) {
        List<Constructor<X>> constructors = Arrays.asList(exceptionClass.getConstructors());
        for (Constructor<X> constructor : preferringStringsThenThrowables(constructors)) {
            X x = (X) newFromConstructor(constructor, cause);
            if (x != null) {
                if (x.getCause() == null) {
                    x.initCause(cause);
                }
                return x;
            }
        }
        throw new IllegalArgumentException("No appropriate constructor for exception of type " + exceptionClass + " in response to chained exception", cause);
    }

    private static <X extends Exception> List<Constructor<X>> preferringStringsThenThrowables(List<Constructor<X>> list) {
        return (List<Constructor<X>>) WITH_STRING_PARAM_THEN_WITH_THROWABLE_PARAM.sortedCopy(list);
    }

    @CheckForNull
    private static <X> X newFromConstructor(Constructor<X> constructor, Throwable cause) {
        Class<?>[] paramTypes = constructor.getParameterTypes();
        Object[] params = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> paramType = paramTypes[i];
            if (paramType.equals(String.class)) {
                params[i] = cause.toString();
            } else {
                if (!paramType.equals(Throwable.class)) {
                    return null;
                }
                params[i] = cause;
            }
        }
        try {
            return constructor.newInstance(params);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException e) {
            return null;
        }
    }

    static boolean isCheckedException(Class<? extends Exception> type) {
        return !RuntimeException.class.isAssignableFrom(type);
    }

    static void checkExceptionClassValidity(Class<? extends Exception> exceptionClass) {
        Preconditions.checkArgument(isCheckedException(exceptionClass), "Futures.getChecked exception type (%s) must not be a RuntimeException", exceptionClass);
        Preconditions.checkArgument(hasConstructorUsableByGetChecked(exceptionClass), "Futures.getChecked exception type (%s) must be an accessible class with an accessible constructor whose parameters (if any) must be of type String and/or Throwable", exceptionClass);
    }

    private FuturesGetChecked() {
    }
}
