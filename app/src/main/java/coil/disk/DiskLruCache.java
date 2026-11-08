package coil.disk;

import coil.util.FileSystems;
import com.streamsync.app.settings.BufferCeiling;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okio.BufferedSink;
import okio.FileSystem;
import okio.ForwardingFileSystem;
import okio.Okio;
import okio.Path;
import okio.Sink;

/* compiled from: DiskLruCache.kt */
@Metadata(d1 = {"\u0000u\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0014*\u0001\u0014\b\u0000\u0018\u0000 C2\u00060\u0001j\u0002`\u00022\u00020\u0003:\u0004CDEFB5\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u000fJ\b\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020'H\u0016J\u001c\u0010)\u001a\u00020'2\n\u0010*\u001a\u00060+R\u00020\u00002\u0006\u0010,\u001a\u00020\u0013H\u0002J\b\u0010-\u001a\u00020'H\u0002J\u0014\u0010.\u001a\b\u0018\u00010+R\u00020\u00002\u0006\u0010/\u001a\u00020\u001fJ\u0006\u00100\u001a\u00020'J\b\u00101\u001a\u00020'H\u0016J\u0017\u00102\u001a\b\u0018\u000103R\u00020\u00002\u0006\u0010/\u001a\u00020\u001fH\u0086\u0002J\u0006\u00104\u001a\u00020'J\b\u00105\u001a\u00020\u0013H\u0002J\b\u00106\u001a\u00020'H\u0002J\b\u00107\u001a\u00020\u001cH\u0002J\b\u00108\u001a\u00020'H\u0002J\b\u00109\u001a\u00020'H\u0002J\u0010\u0010:\u001a\u00020'2\u0006\u0010;\u001a\u00020\u001fH\u0002J\u000e\u0010<\u001a\u00020\u00132\u0006\u0010/\u001a\u00020\u001fJ\u0014\u0010=\u001a\u00020\u00132\n\u0010>\u001a\u00060 R\u00020\u0000H\u0002J\b\u0010?\u001a\u00020\u0013H\u0002J\u0006\u0010%\u001a\u00020\u000bJ\b\u0010@\u001a\u00020'H\u0002J\u0010\u0010A\u001a\u00020'2\u0006\u0010/\u001a\u00020\u001fH\u0002J\b\u0010B\u001a\u00020'H\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\u001d\u001a&\u0012\u0004\u0012\u00020\u001f\u0012\b\u0012\u00060 R\u00020\u00000\u001ej\u0012\u0012\u0004\u0012\u00020\u001f\u0012\b\u0012\u00060 R\u00020\u0000`!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcoil/disk/DiskLruCache;", "Ljava/io/Closeable;", "Lokio/Closeable;", "Ljava/io/Flushable;", "fileSystem", "Lokio/FileSystem;", "directory", "Lokio/Path;", "cleanupDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "maxSize", "", "appVersion", "", "valueCount", "(Lokio/FileSystem;Lokio/Path;Lkotlinx/coroutines/CoroutineDispatcher;JII)V", "cleanupScope", "Lkotlinx/coroutines/CoroutineScope;", "closed", "", "coil/disk/DiskLruCache$fileSystem$1", "Lcoil/disk/DiskLruCache$fileSystem$1;", "hasJournalErrors", "initialized", "journalFile", "journalFileBackup", "journalFileTmp", "journalWriter", "Lokio/BufferedSink;", "lruEntries", "Ljava/util/LinkedHashMap;", "", "Lcoil/disk/DiskLruCache$Entry;", "Lkotlin/collections/LinkedHashMap;", "mostRecentRebuildFailed", "mostRecentTrimFailed", "operationsSinceRewrite", "size", "checkNotClosed", "", "close", "completeEdit", "editor", "Lcoil/disk/DiskLruCache$Editor;", "success", "delete", "edit", "key", "evictAll", "flush", "get", "Lcoil/disk/DiskLruCache$Snapshot;", "initialize", "journalRewriteRequired", "launchCleanup", "newJournalWriter", "processJournal", "readJournal", "readJournalLine", "line", "remove", "removeEntry", "entry", "removeOldestEntry", "trimToSize", "validateKey", "writeJournal", "Companion", "Editor", "Entry", "Snapshot", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes24.dex */
public final class DiskLruCache implements Closeable, Flushable {
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    public static final String JOURNAL_FILE = "journal";
    public static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    public static final String JOURNAL_FILE_TMP = "journal.tmp";
    public static final String MAGIC = "libcore.io.DiskLruCache";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    public static final String VERSION = "1";
    private final int appVersion;
    private final CoroutineScope cleanupScope;
    private boolean closed;
    private final Path directory;
    private final DiskLruCache$fileSystem$1 fileSystem;
    private boolean hasJournalErrors;
    private boolean initialized;
    private final Path journalFile;
    private final Path journalFileBackup;
    private final Path journalFileTmp;
    private BufferedSink journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries;
    private final long maxSize;
    private boolean mostRecentRebuildFailed;
    private boolean mostRecentTrimFailed;
    private int operationsSinceRewrite;
    private long size;
    private final int valueCount;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Regex LEGAL_KEY_PATTERN = new Regex("[a-z0-9_-]{1,120}");

    /* JADX WARN: Type inference failed for: r0v22, types: [coil.disk.DiskLruCache$fileSystem$1] */
    public DiskLruCache(final FileSystem fileSystem, Path directory, CoroutineDispatcher cleanupDispatcher, long maxSize, int appVersion, int valueCount) {
        this.directory = directory;
        this.maxSize = maxSize;
        this.appVersion = appVersion;
        this.valueCount = valueCount;
        if (!(this.maxSize > 0)) {
            throw new IllegalArgumentException("maxSize <= 0".toString());
        }
        if (!(this.valueCount > 0)) {
            throw new IllegalArgumentException("valueCount <= 0".toString());
        }
        this.journalFile = this.directory.resolve(JOURNAL_FILE);
        this.journalFileTmp = this.directory.resolve(JOURNAL_FILE_TMP);
        this.journalFileBackup = this.directory.resolve(JOURNAL_FILE_BACKUP);
        this.lruEntries = new LinkedHashMap<>(0, 0.75f, true);
        this.cleanupScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(cleanupDispatcher.limitedParallelism(1)));
        this.fileSystem = new ForwardingFileSystem(fileSystem) { // from class: coil.disk.DiskLruCache$fileSystem$1
            @Override // okio.ForwardingFileSystem, okio.FileSystem
            public Sink sink(Path file, boolean mustCreate) {
                Path p0 = file.parent();
                if (p0 != null) {
                    createDirectories(p0);
                }
                return super.sink(file, mustCreate);
            }
        };
    }

    public final synchronized void initialize() {
        if (this.initialized) {
            return;
        }
        delete(this.journalFileTmp);
        if (exists(this.journalFileBackup)) {
            if (exists(this.journalFile)) {
                delete(this.journalFileBackup);
            } else {
                atomicMove(this.journalFileBackup, this.journalFile);
            }
        }
        if (exists(this.journalFile)) {
            try {
                readJournal();
                processJournal();
                this.initialized = true;
                return;
            } catch (IOException e) {
                try {
                    delete();
                    this.closed = false;
                } catch (Throwable th) {
                    this.closed = false;
                    throw th;
                }
            }
        }
        writeJournal();
        this.initialized = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x014f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void readJournal() {
        /*
            Method dump skipped, instructions count: 355
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.disk.DiskLruCache.readJournal():void");
    }

    private final BufferedSink newJournalWriter() {
        Sink fileSink = appendingSink(this.journalFile);
        FaultHidingSink faultHidingSink = new FaultHidingSink(fileSink, new Function1<IOException, Unit>() { // from class: coil.disk.DiskLruCache$newJournalWriter$faultHidingSink$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IOException iOException) {
                invoke2(iOException);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IOException it) {
                DiskLruCache.this.hasJournalErrors = true;
            }
        });
        return Okio.buffer(faultHidingSink);
    }

    private final void readJournalLine(String line) {
        String key;
        Entry entry;
        int firstSpace = StringsKt.indexOf$default((CharSequence) line, ' ', 0, false, 6, (Object) null);
        if (firstSpace == -1) {
            throw new IOException("unexpected journal line: " + line);
        }
        int keyBegin = firstSpace + 1;
        int secondSpace = StringsKt.indexOf$default((CharSequence) line, ' ', keyBegin, false, 4, (Object) null);
        if (secondSpace == -1) {
            String substring = line.substring(keyBegin);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            key = substring;
            if (firstSpace == 6 && StringsKt.startsWith$default(line, REMOVE, false, 2, (Object) null)) {
                this.lruEntries.remove(key);
                return;
            }
        } else {
            String substring2 = line.substring(keyBegin, secondSpace);
            Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
            key = substring2;
        }
        Map $this$getOrPut$iv = this.lruEntries;
        Entry entry2 = $this$getOrPut$iv.get(key);
        if (entry2 == null) {
            entry = new Entry(key);
            $this$getOrPut$iv.put(key, entry);
        } else {
            entry = entry2;
        }
        Entry entry3 = entry;
        if (secondSpace != -1 && firstSpace == 5 && StringsKt.startsWith$default(line, CLEAN, false, 2, (Object) null)) {
            String substring3 = line.substring(secondSpace + 1);
            Intrinsics.checkNotNullExpressionValue(substring3, "substring(...)");
            List parts = StringsKt.split$default((CharSequence) substring3, new char[]{' '}, false, 0, 6, (Object) null);
            entry3.setReadable(true);
            entry3.setCurrentEditor(null);
            entry3.setLengths(parts);
            return;
        }
        if (secondSpace == -1 && firstSpace == 5 && StringsKt.startsWith$default(line, DIRTY, false, 2, (Object) null)) {
            entry3.setCurrentEditor(new Editor(entry3));
        } else if (secondSpace != -1 || firstSpace != 4 || !StringsKt.startsWith$default(line, READ, false, 2, (Object) null)) {
            throw new IOException("unexpected journal line: " + line);
        }
    }

    private final void processJournal() {
        long size = 0;
        Iterator iterator = this.lruEntries.values().iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (entry.getCurrentEditor() == null) {
                int i = this.valueCount;
                for (int i2 = 0; i2 < i; i2++) {
                    size += entry.getLengths()[i2];
                }
            } else {
                entry.setCurrentEditor(null);
                int i3 = this.valueCount;
                for (int i4 = 0; i4 < i3; i4++) {
                    delete(entry.getCleanFiles().get(i4));
                    delete(entry.getDirtyFiles().get(i4));
                }
                iterator.remove();
            }
        }
        this.size = size;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void writeJournal() {
        BufferedSink bufferedSink = this.journalWriter;
        if (bufferedSink != null) {
            bufferedSink.close();
        }
        FileSystem $this$iv = this.fileSystem;
        Path file$iv = this.journalFileTmp;
        Closeable $this$use$iv$iv = Okio.buffer($this$iv.sink(file$iv, false));
        Object result$iv$iv = null;
        Throwable thrown$iv$iv = null;
        try {
            BufferedSink it$iv = (BufferedSink) $this$use$iv$iv;
            it$iv.writeUtf8(MAGIC).writeByte(10);
            it$iv.writeUtf8("1").writeByte(10);
            it$iv.writeDecimalLong(this.appVersion).writeByte(10);
            it$iv.writeDecimalLong(this.valueCount).writeByte(10);
            it$iv.writeByte(10);
            for (Entry entry : this.lruEntries.values()) {
                BufferedSink it$iv2 = it$iv;
                if (entry.getCurrentEditor() != null) {
                    it$iv.writeUtf8(DIRTY);
                    it$iv.writeByte(32);
                    it$iv.writeUtf8(entry.getKey());
                    it$iv.writeByte(10);
                    it$iv = it$iv2;
                } else {
                    it$iv.writeUtf8(CLEAN);
                    it$iv.writeByte(32);
                    it$iv.writeUtf8(entry.getKey());
                    entry.writeLengths(it$iv);
                    it$iv.writeByte(10);
                    it$iv = it$iv2;
                }
            }
            result$iv$iv = Unit.INSTANCE;
            if ($this$use$iv$iv != null) {
                try {
                    $this$use$iv$iv.close();
                } catch (Throwable t$iv$iv) {
                    thrown$iv$iv = t$iv$iv;
                }
            }
        } catch (Throwable t$iv$iv2) {
            thrown$iv$iv = t$iv$iv2;
            if ($this$use$iv$iv != null) {
                try {
                    $this$use$iv$iv.close();
                } catch (Throwable t$iv$iv3) {
                    ExceptionsKt.addSuppressed(thrown$iv$iv, t$iv$iv3);
                }
            }
        }
        if (thrown$iv$iv != null) {
            throw thrown$iv$iv;
        }
        Intrinsics.checkNotNull(result$iv$iv);
        if (exists(this.journalFile)) {
            atomicMove(this.journalFile, this.journalFileBackup);
            atomicMove(this.journalFileTmp, this.journalFile);
            delete(this.journalFileBackup);
        } else {
            atomicMove(this.journalFileTmp, this.journalFile);
        }
        this.journalWriter = newJournalWriter();
        this.operationsSinceRewrite = 0;
        this.hasJournalErrors = false;
        this.mostRecentRebuildFailed = false;
    }

    public final synchronized Snapshot get(String key) {
        Snapshot snapshot;
        checkNotClosed();
        validateKey(key);
        initialize();
        Entry entry = this.lruEntries.get(key);
        if (entry != null && (snapshot = entry.snapshot()) != null) {
            this.operationsSinceRewrite++;
            BufferedSink $this$get_u24lambda_u245 = this.journalWriter;
            Intrinsics.checkNotNull($this$get_u24lambda_u245);
            $this$get_u24lambda_u245.writeUtf8(READ);
            $this$get_u24lambda_u245.writeByte(32);
            $this$get_u24lambda_u245.writeUtf8(key);
            $this$get_u24lambda_u245.writeByte(10);
            if (journalRewriteRequired()) {
                launchCleanup();
            }
            return snapshot;
        }
        return null;
    }

    public final synchronized Editor edit(String key) {
        checkNotClosed();
        validateKey(key);
        initialize();
        Entry entry = this.lruEntries.get(key);
        if ((entry != null ? entry.getCurrentEditor() : null) != null) {
            return null;
        }
        if (entry != null && entry.getLockingSnapshotCount() != 0) {
            return null;
        }
        if (!this.mostRecentTrimFailed && !this.mostRecentRebuildFailed) {
            BufferedSink $this$edit_u24lambda_u246 = this.journalWriter;
            Intrinsics.checkNotNull($this$edit_u24lambda_u246);
            $this$edit_u24lambda_u246.writeUtf8(DIRTY);
            $this$edit_u24lambda_u246.writeByte(32);
            $this$edit_u24lambda_u246.writeUtf8(key);
            $this$edit_u24lambda_u246.writeByte(10);
            $this$edit_u24lambda_u246.flush();
            if (this.hasJournalErrors) {
                return null;
            }
            if (entry == null) {
                entry = new Entry(key);
                this.lruEntries.put(key, entry);
            }
            Editor editor = new Editor(entry);
            entry.setCurrentEditor(editor);
            return editor;
        }
        launchCleanup();
        return null;
    }

    public final synchronized long size() {
        initialize();
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void completeEdit(Editor editor, boolean success) {
        Entry entry = editor.getEntry();
        if (!Intrinsics.areEqual(entry.getCurrentEditor(), editor)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (success && !entry.getZombie()) {
            int i = this.valueCount;
            for (int i2 = 0; i2 < i; i2++) {
                if (editor.getWritten()[i2] && !exists(entry.getDirtyFiles().get(i2))) {
                    editor.abort();
                    return;
                }
            }
            int i3 = this.valueCount;
            for (int i4 = 0; i4 < i3; i4++) {
                Path dirty = entry.getDirtyFiles().get(i4);
                Path clean = entry.getCleanFiles().get(i4);
                if (exists(dirty)) {
                    atomicMove(dirty, clean);
                } else {
                    FileSystems.createFile(this.fileSystem, entry.getCleanFiles().get(i4));
                }
                long oldLength = entry.getLengths()[i4];
                Long size = metadata(clean).getSize();
                long newLength = size != null ? size.longValue() : 0L;
                entry.getLengths()[i4] = newLength;
                this.size = (this.size - oldLength) + newLength;
            }
        } else {
            int i5 = this.valueCount;
            for (int i6 = 0; i6 < i5; i6++) {
                delete(entry.getDirtyFiles().get(i6));
            }
        }
        entry.setCurrentEditor(null);
        if (entry.getZombie()) {
            removeEntry(entry);
            return;
        }
        this.operationsSinceRewrite++;
        BufferedSink $this$completeEdit_u24lambda_u247 = this.journalWriter;
        Intrinsics.checkNotNull($this$completeEdit_u24lambda_u247);
        if (!success && !entry.getReadable()) {
            this.lruEntries.remove(entry.getKey());
            $this$completeEdit_u24lambda_u247.writeUtf8(REMOVE);
            $this$completeEdit_u24lambda_u247.writeByte(32);
            $this$completeEdit_u24lambda_u247.writeUtf8(entry.getKey());
            $this$completeEdit_u24lambda_u247.writeByte(10);
            $this$completeEdit_u24lambda_u247.flush();
            if (this.size <= this.maxSize || journalRewriteRequired()) {
                launchCleanup();
            }
        }
        entry.setReadable(true);
        $this$completeEdit_u24lambda_u247.writeUtf8(CLEAN);
        $this$completeEdit_u24lambda_u247.writeByte(32);
        $this$completeEdit_u24lambda_u247.writeUtf8(entry.getKey());
        entry.writeLengths($this$completeEdit_u24lambda_u247);
        $this$completeEdit_u24lambda_u247.writeByte(10);
        $this$completeEdit_u24lambda_u247.flush();
        if (this.size <= this.maxSize) {
        }
        launchCleanup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean journalRewriteRequired() {
        return this.operationsSinceRewrite >= 2000;
    }

    public final synchronized boolean remove(String key) {
        checkNotClosed();
        validateKey(key);
        initialize();
        Entry entry = this.lruEntries.get(key);
        if (entry == null) {
            return false;
        }
        boolean removed = removeEntry(entry);
        if (removed && this.size <= this.maxSize) {
            this.mostRecentTrimFailed = false;
        }
        return removed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean removeEntry(Entry entry) {
        BufferedSink $this$removeEntry_u24lambda_u248;
        if (entry.getLockingSnapshotCount() > 0 && ($this$removeEntry_u24lambda_u248 = this.journalWriter) != null) {
            $this$removeEntry_u24lambda_u248.writeUtf8(DIRTY);
            $this$removeEntry_u24lambda_u248.writeByte(32);
            $this$removeEntry_u24lambda_u248.writeUtf8(entry.getKey());
            $this$removeEntry_u24lambda_u248.writeByte(10);
            $this$removeEntry_u24lambda_u248.flush();
        }
        if (entry.getLockingSnapshotCount() > 0 || entry.getCurrentEditor() != null) {
            entry.setZombie(true);
            return true;
        }
        int i = this.valueCount;
        for (int i2 = 0; i2 < i; i2++) {
            delete(entry.getCleanFiles().get(i2));
            this.size -= entry.getLengths()[i2];
            entry.getLengths()[i2] = 0;
        }
        int i3 = this.operationsSinceRewrite;
        this.operationsSinceRewrite = i3 + 1;
        BufferedSink $this$removeEntry_u24lambda_u249 = this.journalWriter;
        if ($this$removeEntry_u24lambda_u249 != null) {
            $this$removeEntry_u24lambda_u249.writeUtf8(REMOVE);
            $this$removeEntry_u24lambda_u249.writeByte(32);
            $this$removeEntry_u24lambda_u249.writeUtf8(entry.getKey());
            $this$removeEntry_u24lambda_u249.writeByte(10);
        }
        this.lruEntries.remove(entry.getKey());
        if (journalRewriteRequired()) {
            launchCleanup();
        }
        return true;
    }

    private final void checkNotClosed() {
        if (!(!this.closed)) {
            throw new IllegalStateException("cache is closed".toString());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.initialized && !this.closed) {
            Collection $this$toTypedArray$iv = this.lruEntries.values();
            for (Entry entry : (Entry[]) $this$toTypedArray$iv.toArray(new Entry[0])) {
                Editor currentEditor = entry.getCurrentEditor();
                if (currentEditor != null) {
                    currentEditor.detach();
                }
            }
            trimToSize();
            CoroutineScopeKt.cancel$default(this.cleanupScope, null, 1, null);
            BufferedSink bufferedSink = this.journalWriter;
            Intrinsics.checkNotNull(bufferedSink);
            bufferedSink.close();
            this.journalWriter = null;
            this.closed = true;
            return;
        }
        this.closed = true;
    }

    @Override // java.io.Flushable
    public synchronized void flush() {
        if (this.initialized) {
            checkNotClosed();
            trimToSize();
            BufferedSink bufferedSink = this.journalWriter;
            Intrinsics.checkNotNull(bufferedSink);
            bufferedSink.flush();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void trimToSize() {
        while (this.size > this.maxSize) {
            if (!removeOldestEntry()) {
                return;
            }
        }
        this.mostRecentTrimFailed = false;
    }

    private final boolean removeOldestEntry() {
        for (Entry toEvict : this.lruEntries.values()) {
            if (!toEvict.getZombie()) {
                removeEntry(toEvict);
                return true;
            }
        }
        return false;
    }

    private final void delete() {
        close();
        FileSystems.deleteContents(this.fileSystem, this.directory);
    }

    public final synchronized void evictAll() {
        initialize();
        Collection $this$toTypedArray$iv = this.lruEntries.values();
        for (Entry entry : (Entry[]) $this$toTypedArray$iv.toArray(new Entry[0])) {
            removeEntry(entry);
        }
        this.mostRecentTrimFailed = false;
    }

    private final void launchCleanup() {
        BuildersKt__Builders_commonKt.launch$default(this.cleanupScope, null, null, new DiskLruCache$launchCleanup$1(this, null), 3, null);
    }

    private final void validateKey(String key) {
        if (!LEGAL_KEY_PATTERN.matches(key)) {
            throw new IllegalArgumentException(("keys must match regex [a-z0-9_-]{1,120}: \"" + key + Typography.quote).toString());
        }
    }

    /* compiled from: DiskLruCache.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0011\u0012\n\u0010\u0003\u001a\u00060\u0004R\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J\f\u0010\r\u001a\b\u0018\u00010\u000eR\u00020\u0005J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0015\u0010\u0003\u001a\u00060\u0004R\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcoil/disk/DiskLruCache$Snapshot;", "Ljava/io/Closeable;", "Lokio/Closeable;", "entry", "Lcoil/disk/DiskLruCache$Entry;", "Lcoil/disk/DiskLruCache;", "(Lcoil/disk/DiskLruCache;Lcoil/disk/DiskLruCache$Entry;)V", "closed", "", "getEntry", "()Lcoil/disk/DiskLruCache$Entry;", "close", "", "closeAndEdit", "Lcoil/disk/DiskLruCache$Editor;", "file", "Lokio/Path;", "index", "", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes24.dex */
    public final class Snapshot implements Closeable {
        private boolean closed;
        private final Entry entry;

        public Snapshot(Entry entry) {
            this.entry = entry;
        }

        public final Entry getEntry() {
            return this.entry;
        }

        public final Path file(int index) {
            if (!this.closed) {
                return this.entry.getCleanFiles().get(index);
            }
            throw new IllegalStateException("snapshot is closed".toString());
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (!this.closed) {
                this.closed = true;
                DiskLruCache diskLruCache = DiskLruCache.this;
                DiskLruCache diskLruCache2 = DiskLruCache.this;
                synchronized (diskLruCache) {
                    this.entry.setLockingSnapshotCount(r3.getLockingSnapshotCount() - 1);
                    if (this.entry.getLockingSnapshotCount() == 0 && this.entry.getZombie()) {
                        diskLruCache2.removeEntry(this.entry);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
        }

        public final Editor closeAndEdit() {
            Editor edit;
            DiskLruCache diskLruCache = DiskLruCache.this;
            DiskLruCache diskLruCache2 = DiskLruCache.this;
            synchronized (diskLruCache) {
                close();
                edit = diskLruCache2.edit(this.entry.getKey());
            }
            return edit;
        }
    }

    /* compiled from: DiskLruCache.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\f\u0010\u0011\u001a\b\u0018\u00010\u0012R\u00020\u0004J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0007H\u0002J\u0006\u0010\u0015\u001a\u00020\u000fJ\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0015\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lcoil/disk/DiskLruCache$Editor;", "", "entry", "Lcoil/disk/DiskLruCache$Entry;", "Lcoil/disk/DiskLruCache;", "(Lcoil/disk/DiskLruCache;Lcoil/disk/DiskLruCache$Entry;)V", "closed", "", "getEntry", "()Lcoil/disk/DiskLruCache$Entry;", "written", "", "getWritten", "()[Z", "abort", "", "commit", "commitAndGet", "Lcoil/disk/DiskLruCache$Snapshot;", "complete", "success", "detach", "file", "Lokio/Path;", "index", "", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes24.dex */
    public final class Editor {
        private boolean closed;
        private final Entry entry;
        private final boolean[] written;

        public Editor(Entry entry) {
            this.entry = entry;
            this.written = new boolean[DiskLruCache.this.valueCount];
        }

        public final Entry getEntry() {
            return this.entry;
        }

        public final boolean[] getWritten() {
            return this.written;
        }

        public final Path file(int index) {
            Path path;
            DiskLruCache diskLruCache = DiskLruCache.this;
            DiskLruCache diskLruCache2 = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (!(!this.closed)) {
                    throw new IllegalStateException("editor is closed".toString());
                }
                this.written[index] = true;
                Path path2 = this.entry.getDirtyFiles().get(index);
                Path p0 = path2;
                FileSystems.createFile(diskLruCache2.fileSystem, p0);
                path = path2;
            }
            return path;
        }

        public final void detach() {
            if (Intrinsics.areEqual(this.entry.getCurrentEditor(), this)) {
                this.entry.setZombie(true);
            }
        }

        public final void commit() {
            complete(true);
        }

        public final Snapshot commitAndGet() {
            Snapshot snapshot;
            DiskLruCache diskLruCache = DiskLruCache.this;
            DiskLruCache diskLruCache2 = DiskLruCache.this;
            synchronized (diskLruCache) {
                commit();
                snapshot = diskLruCache2.get(this.entry.getKey());
            }
            return snapshot;
        }

        public final void abort() {
            complete(false);
        }

        private final void complete(boolean success) {
            DiskLruCache diskLruCache = DiskLruCache.this;
            DiskLruCache diskLruCache2 = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (!(!this.closed)) {
                    throw new IllegalStateException("editor is closed".toString());
                }
                if (Intrinsics.areEqual(this.entry.getCurrentEditor(), this)) {
                    diskLruCache2.completeEdit(this, success);
                }
                this.closed = true;
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* compiled from: DiskLruCache.kt */
    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010)\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00030,J\f\u0010-\u001a\b\u0018\u00010.R\u00020\rJ\u000e\u0010/\u001a\u00020*2\u0006\u00100\u001a\u000201R!\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR \u0010\u000b\u001a\b\u0018\u00010\fR\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R!\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010#\"\u0004\b(\u0010%¨\u00062"}, d2 = {"Lcoil/disk/DiskLruCache$Entry;", "", "key", "", "(Lcoil/disk/DiskLruCache;Ljava/lang/String;)V", "cleanFiles", "Ljava/util/ArrayList;", "Lokio/Path;", "Lkotlin/collections/ArrayList;", "getCleanFiles", "()Ljava/util/ArrayList;", "currentEditor", "Lcoil/disk/DiskLruCache$Editor;", "Lcoil/disk/DiskLruCache;", "getCurrentEditor", "()Lcoil/disk/DiskLruCache$Editor;", "setCurrentEditor", "(Lcoil/disk/DiskLruCache$Editor;)V", "dirtyFiles", "getDirtyFiles", "getKey", "()Ljava/lang/String;", "lengths", "", "getLengths", "()[J", "lockingSnapshotCount", "", "getLockingSnapshotCount", "()I", "setLockingSnapshotCount", "(I)V", "readable", "", "getReadable", "()Z", "setReadable", "(Z)V", "zombie", "getZombie", "setZombie", "setLengths", "", "strings", "", "snapshot", "Lcoil/disk/DiskLruCache$Snapshot;", "writeLengths", "writer", "Lokio/BufferedSink;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes24.dex */
    public final class Entry {
        private final ArrayList<Path> cleanFiles;
        private Editor currentEditor;
        private final ArrayList<Path> dirtyFiles;
        private final String key;
        private final long[] lengths;
        private int lockingSnapshotCount;
        private boolean readable;
        private boolean zombie;

        public Entry(String key) {
            this.key = key;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new ArrayList<>(DiskLruCache.this.valueCount);
            this.dirtyFiles = new ArrayList<>(DiskLruCache.this.valueCount);
            StringBuilder fileBuilder = new StringBuilder(this.key).append('.');
            int truncateTo = fileBuilder.length();
            int i = DiskLruCache.this.valueCount;
            for (int i2 = 0; i2 < i; i2++) {
                fileBuilder.append(i2);
                this.cleanFiles.add(DiskLruCache.this.directory.resolve(fileBuilder.toString()));
                fileBuilder.append(".tmp");
                this.dirtyFiles.add(DiskLruCache.this.directory.resolve(fileBuilder.toString()));
                fileBuilder.setLength(truncateTo);
            }
        }

        public final String getKey() {
            return this.key;
        }

        public final long[] getLengths() {
            return this.lengths;
        }

        public final ArrayList<Path> getCleanFiles() {
            return this.cleanFiles;
        }

        public final ArrayList<Path> getDirtyFiles() {
            return this.dirtyFiles;
        }

        public final boolean getReadable() {
            return this.readable;
        }

        public final void setReadable(boolean z) {
            this.readable = z;
        }

        public final boolean getZombie() {
            return this.zombie;
        }

        public final void setZombie(boolean z) {
            this.zombie = z;
        }

        public final Editor getCurrentEditor() {
            return this.currentEditor;
        }

        public final void setCurrentEditor(Editor editor) {
            this.currentEditor = editor;
        }

        public final int getLockingSnapshotCount() {
            return this.lockingSnapshotCount;
        }

        public final void setLockingSnapshotCount(int i) {
            this.lockingSnapshotCount = i;
        }

        public final void setLengths(List<String> strings) {
            if (strings.size() != DiskLruCache.this.valueCount) {
                throw new IOException("unexpected journal line: " + strings);
            }
            try {
                int size = strings.size();
                for (int i = 0; i < size; i++) {
                    this.lengths[i] = Long.parseLong(strings.get(i));
                }
            } catch (NumberFormatException e) {
                throw new IOException("unexpected journal line: " + strings);
            }
        }

        public final void writeLengths(BufferedSink writer) {
            for (long length : this.lengths) {
                writer.writeByte(32).writeDecimalLong(length);
            }
        }

        public final Snapshot snapshot() {
            if (!this.readable || this.currentEditor != null || this.zombie) {
                return null;
            }
            List $this$forEachIndices$iv = this.cleanFiles;
            DiskLruCache diskLruCache = DiskLruCache.this;
            int size = $this$forEachIndices$iv.size();
            for (int i$iv = 0; i$iv < size; i$iv++) {
                Path file = $this$forEachIndices$iv.get(i$iv);
                if (!diskLruCache.fileSystem.exists(file)) {
                    try {
                        diskLruCache.removeEntry(this);
                    } catch (IOException e) {
                    }
                    return null;
                }
            }
            this.lockingSnapshotCount++;
            return new Snapshot(this);
        }
    }

    /* compiled from: DiskLruCache.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0016\u0010\b\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002R\u0016\u0010\n\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0002R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0002¨\u0006\u0014"}, d2 = {"Lcoil/disk/DiskLruCache$Companion;", "", "()V", DiskLruCache.CLEAN, "", DiskLruCache.DIRTY, "JOURNAL_FILE", "getJOURNAL_FILE$coil_base_release$annotations", "JOURNAL_FILE_BACKUP", "getJOURNAL_FILE_BACKUP$coil_base_release$annotations", "JOURNAL_FILE_TMP", "getJOURNAL_FILE_TMP$coil_base_release$annotations", "LEGAL_KEY_PATTERN", "Lkotlin/text/Regex;", "MAGIC", "getMAGIC$coil_base_release$annotations", DiskLruCache.READ, DiskLruCache.REMOVE, "VERSION", "getVERSION$coil_base_release$annotations", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes24.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getJOURNAL_FILE$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getJOURNAL_FILE_BACKUP$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getJOURNAL_FILE_TMP$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getMAGIC$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getVERSION$coil_base_release$annotations() {
        }

        private Companion() {
        }
    }
}
