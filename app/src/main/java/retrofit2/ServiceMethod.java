package retrofit2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes25.dex */
public abstract class ServiceMethod<T> {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract T invoke(Object obj, Object[] objArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> ServiceMethod<T> parseAnnotations(Retrofit retrofit, Class<?> service, Method method) {
        RequestFactory requestFactory = RequestFactory.parseAnnotations(retrofit, service, method);
        Type returnType = method.getGenericReturnType();
        if (Utils.hasUnresolvableType(returnType)) {
            throw Utils.methodError(method, "Method return type must not include a type variable or wildcard: %s", returnType);
        }
        if (returnType == Void.TYPE) {
            throw Utils.methodError(method, "Service methods cannot return void.", new Object[0]);
        }
        return HttpServiceMethod.parseAnnotations(retrofit, method, requestFactory);
    }
}
