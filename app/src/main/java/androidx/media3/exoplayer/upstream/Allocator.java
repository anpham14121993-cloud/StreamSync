package androidx.media3.exoplayer.upstream;

/* loaded from: classes10.dex */
public interface Allocator {

    /* loaded from: classes10.dex */
    public interface AllocationNode {
        Allocation getAllocation();

        AllocationNode next();
    }

    Allocation allocate();

    int getIndividualAllocationLength();

    int getTotalBytesAllocated();

    void release(Allocation allocation);

    void release(AllocationNode allocationNode);

    void trim();
}
