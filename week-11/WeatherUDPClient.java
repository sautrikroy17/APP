import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class WeatherUDPClient {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 6002;

        String sensorId = "SENSOR_DEV_01";
        double temp = 29.5;
        double humidity = 60.0;

        if (args.length >= 3) {
            sensorId = args[0];
            temp = Double.parseDouble(args[1]);
            humidity = Double.parseDouble(args[2]);
        } else if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Sensor ID: ");
            sensorId = scanner.nextLine().trim();
            System.out.print("Enter Temperature (°C): ");
            temp = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Enter Humidity (%): ");
            humidity = Double.parseDouble(scanner.nextLine().trim());
        }

        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String payload = String.format("Sensor: %s | Temp: %.2f C | Humidity: %.1f %% | Time: %s",
                sensorId, temp, humidity, time);

        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] data = payload.getBytes(StandardCharsets.UTF_8);
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
            System.out.println("Dispatched UDP datagram to " + host + ":" + port);
            System.out.println("Payload: " + payload);
        } catch (Exception e) {
            System.err.println("Transmission error: " + e.getMessage());
        }
    }
}
