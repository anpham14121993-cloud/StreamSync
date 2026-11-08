package retrofit2;

import android.os.Build;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import javax.annotation.Nullable;

/* loaded from: classes25.dex */
class Reflection {
    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDefaultMethod(Method method) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Object invokeDefaultMethod(Method method, Class<?> declaringClass, Object proxy, @Nullable Object[] args) throws Throwable {
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String describeMethodParameter(Method method, int index) {
        return "parameter #" + (index + 1);
    }

    /* loaded from: classes25.dex */
    static class Java8 extends Reflection {
        @Override // retrofit2.Reflection
        boolean isDefaultMethod(Method method) {
            return method.isDefault();
        }

        @Override // retrofit2.Reflection
        Object invokeDefaultMethod(Method method, Class<?> declaringClass, Object proxy, @Nullable Object[] args) throws Throwable {
            return DefaultMethodSupport.invoke(method, declaringClass, proxy, args);
        }

        @Override // retrofit2.Reflection
        String describeMethodParameter(Method method, int index) {
            Parameter parameter = method.getParameters()[index];
            if (parameter.isNamePresent()) {
                return "parameter '" + parameter.getName() + '\'';
            }
            return super.describeMethodParameter(method, index);
        }
    }

    /* loaded from: classes25.dex */
    static final class Android24 extends Reflection {
        @Override // retrofit2.Reflection
        boolean isDefaultMethod(Method method) {
            return method.isDefault();
        }

        @Override // retrofit2.Reflection
        Object invokeDefaultMethod(Method method, Class<?> declaringClass, Object proxy, @Nullable Object[] args) throws Throwable {
            if (Build.VERSION.SDK_INT < 26) {
                throw new UnsupportedOperationException("Calling default methods on API 24 and 25 is not supported");
            }
            return DefaultMethodSupport.invoke(method, declaringClass, proxy, args);
        }
    }
}
