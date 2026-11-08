package com.streamsync.app.net;

import com.streamsync.app.settings.BufferCeiling;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import javax.net.SocketFactory;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.DebugKt;

/* compiled from: DpiBypassSocketFactory.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0014\u0015B!\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0016J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u0004H\u0016J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/streamsync/app/net/DpiBypassSocketFactory;", "Ljavax/net/SocketFactory;", "delegate", "fragmentSize", "", "delayMs", "", "(Ljavax/net/SocketFactory;IJ)V", "minSizeToFragment", "createSocket", "Ljava/net/Socket;", "host", "Ljava/net/InetAddress;", "port", "address", "localAddress", "localPort", "", "wrap", "socket", "FragmentingOutputStream", "FragmentingSocket", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
/* loaded from: classes21.dex */
public final class DpiBypassSocketFactory extends SocketFactory {
    public static final int $stable = 8;
    private final long delayMs;
    private final SocketFactory delegate;
    private final int fragmentSize;
    private final int minSizeToFragment;

    public /* synthetic */ DpiBypassSocketFactory(SocketFactory socketFactory, int i, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(socketFactory, (i2 & 2) != 0 ? 8 : i, (i2 & 4) != 0 ? 1L : j);
    }

    public DpiBypassSocketFactory(SocketFactory delegate, int fragmentSize, long delayMs) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
        this.fragmentSize = fragmentSize;
        this.delayMs = delayMs;
        this.minSizeToFragment = 64;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() {
        Socket createSocket = this.delegate.createSocket();
        Intrinsics.checkNotNullExpressionValue(createSocket, "createSocket(...)");
        return wrap(createSocket);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String host, int port) {
        Intrinsics.checkNotNullParameter(host, "host");
        Socket createSocket = this.delegate.createSocket(host, port);
        Intrinsics.checkNotNullExpressionValue(createSocket, "createSocket(...)");
        return wrap(createSocket);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String host, int port, InetAddress localAddress, int localPort) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(localAddress, "localAddress");
        Socket createSocket = this.delegate.createSocket(host, port, localAddress, localPort);
        Intrinsics.checkNotNullExpressionValue(createSocket, "createSocket(...)");
        return wrap(createSocket);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress host, int port) {
        Intrinsics.checkNotNullParameter(host, "host");
        Socket createSocket = this.delegate.createSocket(host, port);
        Intrinsics.checkNotNullExpressionValue(createSocket, "createSocket(...)");
        return wrap(createSocket);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(localAddress, "localAddress");
        Socket createSocket = this.delegate.createSocket(address, port, localAddress, localPort);
        Intrinsics.checkNotNullExpressionValue(createSocket, "createSocket(...)");
        return wrap(createSocket);
    }

    private final Socket wrap(Socket socket) {
        try {
            Result.Companion companion = Result.INSTANCE;
            DpiBypassSocketFactory dpiBypassSocketFactory = this;
            socket.setTcpNoDelay(true);
            Result.m6717constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m6717constructorimpl(ResultKt.createFailure(th));
        }
        return new DpiBypassSocket(socket, this.fragmentSize, this.delayMs, this.minSizeToFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DpiBypassSocketFactory.kt */
    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0096\u0002J\u0010\u0010\u0016\u001a\n \u0018*\u0004\u0018\u00010\u00170\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0013H\u0016J\b\u0010\u001e\u001a\u00020\u001aH\u0016J\b\u0010\u001f\u001a\u00020\u0004H\u0016J\u0010\u0010 \u001a\n \u0018*\u0004\u0018\u00010\u000e0\u000eH\u0016J\b\u0010!\u001a\u00020\nH\u0016J\b\u0010\"\u001a\u00020\u0004H\u0016J\b\u0010#\u001a\u00020\u0004H\u0016J\u0010\u0010$\u001a\n \u0018*\u0004\u0018\u00010\u000e0\u000eH\u0016J\b\u0010%\u001a\u00020\u0004H\u0016J\b\u0010&\u001a\u00020\u0004H\u0016J\b\u0010'\u001a\u00020\u0004H\u0016J\b\u0010(\u001a\u00020\u0013H\u0016J\b\u0010)\u001a\u00020\u0004H\u0016J\b\u0010*\u001a\u00020\u0013H\u0016J\b\u0010+\u001a\u00020\u0013H\u0016J\b\u0010,\u001a\u00020\u0013H\u0016J\b\u0010-\u001a\u00020\u0013H\u0016J\b\u0010.\u001a\u00020\u0013H\u0016J\u0010\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\u0013H\u0016J\u0010\u00101\u001a\u00020\f2\u0006\u00102\u001a\u00020\u0004H\u0016J\u0010\u00103\u001a\u00020\f2\u0006\u00102\u001a\u00020\u0004H\u0016J\u0018\u00104\u001a\u00020\f2\u0006\u00100\u001a\u00020\u00132\u0006\u00105\u001a\u00020\u0004H\u0016J\u0010\u00106\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J\u0010\u00107\u001a\u00020\f2\u0006\u00100\u001a\u00020\u0013H\u0016J\b\u00108\u001a\u00020\fH\u0016J\b\u00109\u001a\u00020\fH\u0016J\b\u0010:\u001a\u00020;H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/streamsync/app/net/DpiBypassSocketFactory$FragmentingSocket;", "Ljava/net/Socket;", "real", "fragmentSize", "", "delayMs", "", "minSizeToFragment", "(Ljava/net/Socket;IJI)V", "output", "Ljava/io/OutputStream;", "bind", "", "endpoint", "Ljava/net/SocketAddress;", "close", "connect", "timeout", "equals", "", "other", "", "getChannel", "Ljava/nio/channels/SocketChannel;", "kotlin.jvm.PlatformType", "getInetAddress", "Ljava/net/InetAddress;", "getInputStream", "Ljava/io/InputStream;", "getKeepAlive", "getLocalAddress", "getLocalPort", "getLocalSocketAddress", "getOutputStream", "getPort", "getReceiveBufferSize", "getRemoteSocketAddress", "getSendBufferSize", "getSoLinger", "getSoTimeout", "getTcpNoDelay", "hashCode", "isBound", "isClosed", "isConnected", "isInputShutdown", "isOutputShutdown", "setKeepAlive", DebugKt.DEBUG_PROPERTY_VALUE_ON, "setReceiveBufferSize", "size", "setSendBufferSize", "setSoLinger", "linger", "setSoTimeout", "setTcpNoDelay", "shutdownInput", "shutdownOutput", "toString", "", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* renamed from: com.streamsync.app.net.DpiBypassSocketFactory$FragmentingSocket, reason: from toString */
    /* loaded from: classes21.dex */
    public static final class DpiBypassSocket extends Socket {
        private final long delayMs;
        private final int fragmentSize;
        private final int minSizeToFragment;
        private OutputStream output;
        private final Socket real;

        public DpiBypassSocket(Socket real, int fragmentSize, long delayMs, int minSizeToFragment) {
            Intrinsics.checkNotNullParameter(real, "real");
            this.real = real;
            this.fragmentSize = fragmentSize;
            this.delayMs = delayMs;
            this.minSizeToFragment = minSizeToFragment;
        }

        @Override // java.net.Socket
        public InputStream getInputStream() {
            InputStream inputStream = this.real.getInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
            return inputStream;
        }

        @Override // java.net.Socket
        public OutputStream getOutputStream() {
            OutputStream it = this.output;
            if (it != null) {
                return it;
            }
            OutputStream outputStream = this.real.getOutputStream();
            Intrinsics.checkNotNullExpressionValue(outputStream, "getOutputStream(...)");
            FragmentingOutputStream created = new FragmentingOutputStream(outputStream, this.fragmentSize, this.delayMs, this.minSizeToFragment);
            this.output = created;
            return created;
        }

        @Override // java.net.Socket
        public boolean isConnected() {
            return this.real.isConnected();
        }

        @Override // java.net.Socket
        public boolean isClosed() {
            return this.real.isClosed();
        }

        @Override // java.net.Socket
        public boolean isBound() {
            return this.real.isBound();
        }

        @Override // java.net.Socket
        public boolean isInputShutdown() {
            return this.real.isInputShutdown();
        }

        @Override // java.net.Socket
        public boolean isOutputShutdown() {
            return this.real.isOutputShutdown();
        }

        @Override // java.net.Socket
        public InetAddress getInetAddress() {
            InetAddress inetAddress = this.real.getInetAddress();
            Intrinsics.checkNotNullExpressionValue(inetAddress, "getInetAddress(...)");
            return inetAddress;
        }

        @Override // java.net.Socket
        public InetAddress getLocalAddress() {
            InetAddress localAddress = this.real.getLocalAddress();
            Intrinsics.checkNotNullExpressionValue(localAddress, "getLocalAddress(...)");
            return localAddress;
        }

        @Override // java.net.Socket
        public int getPort() {
            return this.real.getPort();
        }

        @Override // java.net.Socket
        public int getLocalPort() {
            return this.real.getLocalPort();
        }

        @Override // java.net.Socket
        public SocketAddress getRemoteSocketAddress() {
            return this.real.getRemoteSocketAddress();
        }

        @Override // java.net.Socket
        public SocketAddress getLocalSocketAddress() {
            return this.real.getLocalSocketAddress();
        }

        @Override // java.net.Socket
        public void connect(SocketAddress endpoint) {
            Intrinsics.checkNotNullParameter(endpoint, "endpoint");
            this.real.connect(endpoint);
        }

        @Override // java.net.Socket
        public void connect(SocketAddress endpoint, int timeout) {
            Intrinsics.checkNotNullParameter(endpoint, "endpoint");
            this.real.connect(endpoint, timeout);
        }

        @Override // java.net.Socket
        public void bind(SocketAddress endpoint) {
            Intrinsics.checkNotNullParameter(endpoint, "endpoint");
            this.real.bind(endpoint);
        }

        @Override // java.net.Socket
        public SocketChannel getChannel() {
            return this.real.getChannel();
        }

        @Override // java.net.Socket
        public void setSoTimeout(int timeout) {
            this.real.setSoTimeout(timeout);
        }

        @Override // java.net.Socket
        public int getSoTimeout() {
            return this.real.getSoTimeout();
        }

        @Override // java.net.Socket
        public void setTcpNoDelay(boolean on) {
            this.real.setTcpNoDelay(on);
        }

        @Override // java.net.Socket
        public boolean getTcpNoDelay() {
            return this.real.getTcpNoDelay();
        }

        @Override // java.net.Socket
        public void setKeepAlive(boolean on) {
            this.real.setKeepAlive(on);
        }

        @Override // java.net.Socket
        public boolean getKeepAlive() {
            return this.real.getKeepAlive();
        }

        @Override // java.net.Socket
        public void setReceiveBufferSize(int size) {
            this.real.setReceiveBufferSize(size);
        }

        @Override // java.net.Socket
        public int getReceiveBufferSize() {
            return this.real.getReceiveBufferSize();
        }

        @Override // java.net.Socket
        public void setSendBufferSize(int size) {
            this.real.setSendBufferSize(size);
        }

        @Override // java.net.Socket
        public int getSendBufferSize() {
            return this.real.getSendBufferSize();
        }

        @Override // java.net.Socket
        public void setSoLinger(boolean on, int linger) {
            this.real.setSoLinger(on, linger);
        }

        @Override // java.net.Socket
        public int getSoLinger() {
            return this.real.getSoLinger();
        }

        @Override // java.net.Socket
        public void shutdownInput() {
            this.real.shutdownInput();
        }

        @Override // java.net.Socket
        public void shutdownOutput() {
            this.real.shutdownOutput();
        }

        @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.real.close();
        }

        @Override // java.net.Socket
        public String toString() {
            return "DpiBypassSocket(" + this.real + ")";
        }

        public boolean equals(Object other) {
            return Intrinsics.areEqual(this.real, other);
        }

        public int hashCode() {
            return this.real.hashCode();
        }
    }

    /* compiled from: DpiBypassSocketFactory.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\bJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\b\u0010\u0011\u001a\u00020\u000fH\u0016J \u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0016J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0004H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/streamsync/app/net/DpiBypassSocketFactory$FragmentingOutputStream;", "Ljava/io/OutputStream;", "real", "fragmentSize", "", "delayMs", "", "minSizeToFragment", "(Ljava/io/OutputStream;IJI)V", "firstWriteDone", "", "flushAfter", "pending", "Ljava/io/ByteArrayOutputStream;", "close", "", "emit", "flush", "write", "b", "", DebugKt.DEBUG_PROPERTY_VALUE_OFF, "len", "app_mobileDebug"}, k = 1, mv = {1, 9, 0}, xi = BufferCeiling.MIN_CEILING_MB)
    /* loaded from: classes21.dex */
    private static final class FragmentingOutputStream extends OutputStream {
        private final long delayMs;
        private boolean firstWriteDone;
        private final int flushAfter;
        private final int fragmentSize;
        private final int minSizeToFragment;
        private final ByteArrayOutputStream pending;
        private final OutputStream real;

        public FragmentingOutputStream(OutputStream real, int fragmentSize, long delayMs, int minSizeToFragment) {
            Intrinsics.checkNotNullParameter(real, "real");
            this.real = real;
            this.fragmentSize = fragmentSize;
            this.delayMs = delayMs;
            this.minSizeToFragment = minSizeToFragment;
            this.flushAfter = 24;
            this.pending = new ByteArrayOutputStream();
        }

        @Override // java.io.OutputStream
        public synchronized void write(int b) {
            if (this.firstWriteDone) {
                this.real.write(b);
                return;
            }
            this.pending.write(b);
            if (this.pending.size() >= this.flushAfter) {
                emit();
            }
        }

        @Override // java.io.OutputStream
        public synchronized void write(byte[] b, int off, int len) {
            Intrinsics.checkNotNullParameter(b, "b");
            if (this.firstWriteDone) {
                this.real.write(b, off, len);
                return;
            }
            this.pending.write(b, off, len);
            if (this.pending.size() >= this.flushAfter) {
                emit();
            }
        }

        private final void emit() {
            byte[] data = this.pending.toByteArray();
            this.pending.reset();
            this.firstWriteDone = true;
            if (data.length < this.minSizeToFragment) {
                this.real.write(data);
                return;
            }
            for (IntRange piece : DpiBypassSocketFactoryKt.planFragments(data.length, this.fragmentSize)) {
                this.real.write(data, piece.getFirst(), (piece.getLast() - piece.getFirst()) + 1);
                if (this.delayMs > 0) {
                    try {
                        Thread.sleep(this.delayMs);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            this.real.flush();
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public synchronized void flush() {
            if (!this.firstWriteDone && this.pending.size() > 0) {
                emit();
            }
            this.real.flush();
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            if (!this.firstWriteDone && this.pending.size() > 0) {
                emit();
            }
            this.real.close();
        }
    }
}
