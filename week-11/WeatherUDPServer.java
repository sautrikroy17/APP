import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class WeatherUDPServer {

    public static void main(String[] args) {
        int port = 6002;
        System.out.println("Central Weather Monitoring UDP Server started on port " + port);

        try (DatagramSocket socket = new DatagramSocket(port)) {
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);

                System.out.printf("[%s:%d] Weather Reading: %s%n",
                        packet.getAddress().getHostAddress(), packet.getPort(), msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
