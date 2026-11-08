package androidx.media3.extractor.mp4;

import androidx.media3.extractor.SniffFailure;
import com.google.common.primitives.ImmutableIntArray;

/* loaded from: classes24.dex */
public final class UnsupportedBrandsSniffFailure implements SniffFailure {
    public final ImmutableIntArray compatibleBrands;
    public final int majorBrand;

    public UnsupportedBrandsSniffFailure(int majorBrand, int[] compatibleBrands) {
        ImmutableIntArray of;
        this.majorBrand = majorBrand;
        if (compatibleBrands != null) {
            of = ImmutableIntArray.copyOf(compatibleBrands);
        } else {
            of = ImmutableIntArray.of();
        }
        this.compatibleBrands = of;
    }
}
