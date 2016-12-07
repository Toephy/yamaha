import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Toephy on 2016/6/14 17:03
 */
public class IpAccessor {


    public String getLocalIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new IpAccessor().getLocalIp());
    }
}
