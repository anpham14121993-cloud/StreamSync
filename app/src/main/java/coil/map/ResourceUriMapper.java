package coil.map;

import android.content.res.Resources;
import android.net.Uri;
import coil.request.Options;
import com.streamsync.app.settings.BufferCeiling;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ResourceUriMapper.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0002J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcoil/map/ResourceUriMapper;", "Lcoil/map/Mapper;", "Landroid/net/Uri;", "()V", "isApplicable", "", "data", "map", "options", "Lcoil/request/Options;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class ResourceUriMapper implements Mapper<Uri, Uri> {
    @Override // coil.map.Mapper
    public Uri map(Uri data, Options options) {
        if (!isApplicable(data)) {
            return null;
        }
        String packageName = data.getAuthority();
        if (packageName == null) {
            packageName = "";
        }
        Resources resources = options.getContext().getPackageManager().getResourcesForApplication(packageName);
        List<String> pathSegments = data.getPathSegments();
        String type = pathSegments.get(0);
        String name = pathSegments.get(1);
        int id = resources.getIdentifier(name, type, packageName);
        if (!(id != 0)) {
            throw new IllegalStateException(("Invalid android.resource URI: " + data).toString());
        }
        String $this$toUri$iv = "android.resource://" + packageName + '/' + id;
        return Uri.parse($this$toUri$iv);
    }

    private final boolean isApplicable(Uri data) {
        if (!Intrinsics.areEqual(data.getScheme(), "android.resource")) {
            return false;
        }
        String authority = data.getAuthority();
        return !(authority == null || StringsKt.isBlank(authority)) && data.getPathSegments().size() == 2;
    }
}
