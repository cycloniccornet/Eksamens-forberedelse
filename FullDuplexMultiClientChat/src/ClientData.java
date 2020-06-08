import java.net.InetAddress;

public class ClientData {
    private InetAddress address;
    private int port;
    private String nickname;

    public ClientData(InetAddress address, int port, String nickname) {
        this.address = address;
        this.port = port;
        this.nickname = nickname;
    }

    public ClientData(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
