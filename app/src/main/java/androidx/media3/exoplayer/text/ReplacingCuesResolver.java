package androidx.media3.exoplayer.text;

import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.text.CuesWithTiming;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.util.ArrayList;

/* loaded from: classes10.dex */
final class ReplacingCuesResolver implements CuesResolver {
    private final ArrayList<CuesWithTiming> cuesWithTimingList = new ArrayList<>();

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public boolean addCues(CuesWithTiming cues, long currentPositionUs) {
        Assertions.checkArgument(cues.startTimeUs != C.TIME_UNSET);
        boolean cuesAreShownAtCurrentTime = cues.startTimeUs <= currentPositionUs && (cues.endTimeUs == C.TIME_UNSET || currentPositionUs < cues.endTimeUs);
        for (int i = this.cuesWithTimingList.size() - 1; i >= 0; i--) {
            if (cues.startTimeUs >= this.cuesWithTimingList.get(i).startTimeUs) {
                this.cuesWithTimingList.add(i + 1, cues);
                return cuesAreShownAtCurrentTime;
            }
            if (this.cuesWithTimingList.get(i).startTimeUs <= currentPositionUs) {
                cuesAreShownAtCurrentTime = false;
            }
        }
        this.cuesWithTimingList.add(0, cues);
        return cuesAreShownAtCurrentTime;
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public ImmutableList<Cue> getCuesAtTimeUs(long timeUs) {
        int indexStartingAfterTimeUs = getIndexOfCuesStartingAfter(timeUs);
        if (indexStartingAfterTimeUs == 0) {
            return ImmutableList.of();
        }
        CuesWithTiming cues = this.cuesWithTimingList.get(indexStartingAfterTimeUs - 1);
        if (cues.endTimeUs == C.TIME_UNSET || timeUs < cues.endTimeUs) {
            return cues.cues;
        }
        return ImmutableList.of();
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public void discardCuesBeforeTimeUs(long timeUs) {
        int indexToDiscardTo = getIndexOfCuesStartingAfter(timeUs);
        if (indexToDiscardTo > 0) {
            this.cuesWithTimingList.subList(0, indexToDiscardTo).clear();
        }
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public long getPreviousCueChangeTimeUs(long timeUs) {
        if (this.cuesWithTimingList.isEmpty() || timeUs < this.cuesWithTimingList.get(0).startTimeUs) {
            return C.TIME_UNSET;
        }
        for (int i = 1; i < this.cuesWithTimingList.size(); i++) {
            long nextCuesStartTimeUs = this.cuesWithTimingList.get(i).startTimeUs;
            if (timeUs == nextCuesStartTimeUs) {
                return nextCuesStartTimeUs;
            }
            if (timeUs < nextCuesStartTimeUs) {
                CuesWithTiming cues = this.cuesWithTimingList.get(i - 1);
                if (cues.endTimeUs != C.TIME_UNSET && cues.endTimeUs <= timeUs) {
                    return cues.endTimeUs;
                }
                return cues.startTimeUs;
            }
        }
        CuesWithTiming lastCues = (CuesWithTiming) Iterables.getLast(this.cuesWithTimingList);
        if (lastCues.endTimeUs == C.TIME_UNSET || timeUs < lastCues.endTimeUs) {
            return lastCues.startTimeUs;
        }
        return lastCues.endTimeUs;
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public long getNextCueChangeTimeUs(long timeUs) {
        if (this.cuesWithTimingList.isEmpty()) {
            return Long.MIN_VALUE;
        }
        if (timeUs < this.cuesWithTimingList.get(0).startTimeUs) {
            return this.cuesWithTimingList.get(0).startTimeUs;
        }
        for (int i = 1; i < this.cuesWithTimingList.size(); i++) {
            CuesWithTiming cues = this.cuesWithTimingList.get(i);
            if (timeUs < cues.startTimeUs) {
                CuesWithTiming previousCues = this.cuesWithTimingList.get(i - 1);
                if (previousCues.endTimeUs != C.TIME_UNSET && previousCues.endTimeUs > timeUs && previousCues.endTimeUs < cues.startTimeUs) {
                    return previousCues.endTimeUs;
                }
                return cues.startTimeUs;
            }
        }
        CuesWithTiming lastCues = (CuesWithTiming) Iterables.getLast(this.cuesWithTimingList);
        if (lastCues.endTimeUs == C.TIME_UNSET || timeUs >= lastCues.endTimeUs) {
            return Long.MIN_VALUE;
        }
        return lastCues.endTimeUs;
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public void clear() {
        this.cuesWithTimingList.clear();
    }

    private int getIndexOfCuesStartingAfter(long timeUs) {
        for (int i = 0; i < this.cuesWithTimingList.size(); i++) {
            if (timeUs < this.cuesWithTimingList.get(i).startTimeUs) {
                return i;
            }
        }
        return this.cuesWithTimingList.size();
    }
}
