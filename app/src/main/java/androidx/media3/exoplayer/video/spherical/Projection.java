package androidx.media3.exoplayer.video.spherical;

import androidx.media3.common.util.Assertions;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes7.dex */
final class Projection {
    public static final int DRAW_MODE_TRIANGLES = 0;
    public static final int DRAW_MODE_TRIANGLES_FAN = 2;
    public static final int DRAW_MODE_TRIANGLES_STRIP = 1;
    public static final int POSITION_COORDS_PER_VERTEX = 3;
    public static final int TEXTURE_COORDS_PER_VERTEX = 2;
    public final Mesh leftMesh;
    public final Mesh rightMesh;
    public final boolean singleMesh;
    public final int stereoMode;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes7.dex */
    public @interface DrawMode {
    }

    public static Projection createEquirectangular(int stereoMode) {
        return createEquirectangular(50.0f, 36, 72, 180.0f, 360.0f, stereoMode);
    }

    public static Projection createEquirectangular(float radius, int latitudes, int longitudes, float verticalFovDegrees, float horizontalFovDegrees, int stereoMode) {
        int vOffset;
        int k;
        float f = radius;
        int i = latitudes;
        int i2 = longitudes;
        Assertions.checkArgument(f > 0.0f);
        Assertions.checkArgument(i >= 1);
        Assertions.checkArgument(i2 >= 1);
        Assertions.checkArgument(verticalFovDegrees > 0.0f && verticalFovDegrees <= 180.0f);
        Assertions.checkArgument(horizontalFovDegrees > 0.0f && horizontalFovDegrees <= 360.0f);
        float verticalFovRads = (float) Math.toRadians(verticalFovDegrees);
        float horizontalFovRads = (float) Math.toRadians(horizontalFovDegrees);
        float quadHeightRads = verticalFovRads / i;
        float quadWidthRads = horizontalFovRads / i2;
        int vertexCount = (((i2 + 1) * 2) + 2) * i;
        float[] vertexData = new float[vertexCount * 3];
        float[] textureData = new float[vertexCount * 2];
        int vOffset2 = 0;
        int tOffset = 0;
        int j = 0;
        while (j < i) {
            float phiLow = (j * quadHeightRads) - (verticalFovRads / 2.0f);
            float phiHigh = ((j + 1) * quadHeightRads) - (verticalFovRads / 2.0f);
            int i3 = 0;
            while (i3 < i2 + 1) {
                int k2 = 0;
                while (k2 < 2) {
                    float phi = k2 == 0 ? phiLow : phiHigh;
                    float phiLow2 = phiLow;
                    float phiLow3 = i3;
                    float theta = ((phiLow3 * quadWidthRads) + 3.1415927f) - (horizontalFovRads / 2.0f);
                    int vOffset3 = vOffset2 + 1;
                    int vertexCount2 = vertexCount;
                    float phiHigh2 = phiHigh;
                    int k3 = k2;
                    vertexData[vOffset2] = -((float) (f * Math.sin(theta) * Math.cos(phi)));
                    int vOffset4 = vOffset3 + 1;
                    float[] textureData2 = textureData;
                    vertexData[vOffset3] = (float) (f * Math.sin(phi));
                    int vOffset5 = vOffset4 + 1;
                    vertexData[vOffset4] = (float) (f * Math.cos(theta) * Math.cos(phi));
                    int tOffset2 = tOffset + 1;
                    textureData2[tOffset] = (i3 * quadWidthRads) / horizontalFovRads;
                    int tOffset3 = tOffset2 + 1;
                    textureData2[tOffset2] = ((j + k3) * quadHeightRads) / verticalFovRads;
                    if (i3 == 0 && k3 == 0) {
                        i2 = longitudes;
                        k = k3;
                    } else {
                        i2 = longitudes;
                        if (i3 == i2) {
                            k = k3;
                            if (k != 1) {
                                vOffset = vOffset5;
                            }
                        } else {
                            vOffset = vOffset5;
                            k = k3;
                        }
                        tOffset = tOffset3;
                        vOffset2 = vOffset;
                        textureData = textureData2;
                        k2 = k + 1;
                        phiLow = phiLow2;
                        vertexCount = vertexCount2;
                        phiHigh = phiHigh2;
                        f = radius;
                    }
                    System.arraycopy(vertexData, vOffset5 - 3, vertexData, vOffset5, 3);
                    vOffset = vOffset5 + 3;
                    System.arraycopy(textureData2, tOffset3 - 2, textureData2, tOffset3, 2);
                    tOffset3 += 2;
                    tOffset = tOffset3;
                    vOffset2 = vOffset;
                    textureData = textureData2;
                    k2 = k + 1;
                    phiLow = phiLow2;
                    vertexCount = vertexCount2;
                    phiHigh = phiHigh2;
                    f = radius;
                }
                i3++;
                f = radius;
                phiHigh = phiHigh;
            }
            j++;
            i = latitudes;
            f = radius;
        }
        SubMesh subMesh = new SubMesh(0, vertexData, textureData, 1);
        return new Projection(new Mesh(subMesh), stereoMode);
    }

    public Projection(Mesh mesh, int stereoMode) {
        this(mesh, mesh, stereoMode);
    }

    public Projection(Mesh leftMesh, Mesh rightMesh, int stereoMode) {
        this.leftMesh = leftMesh;
        this.rightMesh = rightMesh;
        this.stereoMode = stereoMode;
        this.singleMesh = leftMesh == rightMesh;
    }

    /* loaded from: classes7.dex */
    public static final class SubMesh {
        public static final int VIDEO_TEXTURE_ID = 0;
        public final int mode;
        public final float[] textureCoords;
        public final int textureId;
        public final float[] vertices;

        public SubMesh(int textureId, float[] vertices, float[] textureCoords, int mode) {
            this.textureId = textureId;
            Assertions.checkArgument(((long) vertices.length) * 2 == ((long) textureCoords.length) * 3);
            this.vertices = vertices;
            this.textureCoords = textureCoords;
            this.mode = mode;
        }

        public int getVertexCount() {
            return this.vertices.length / 3;
        }
    }

    /* loaded from: classes7.dex */
    public static final class Mesh {
        private final SubMesh[] subMeshes;

        public Mesh(SubMesh... subMeshes) {
            this.subMeshes = subMeshes;
        }

        public int getSubMeshCount() {
            return this.subMeshes.length;
        }

        public SubMesh getSubMesh(int index) {
            return this.subMeshes[index];
        }
    }
}
