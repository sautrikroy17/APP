import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class WeatherMonitoringUDP {

    public static class SensorReading {
        private final String sensorId;
        private final String location;
        private final double temperature;
        private final double humidity;

        public SensorReading(String sensorId, String location, double temperature, double humidity) {
            this.sensorId = sensorId;
            this.location = location;
            this.temperature = temperature;
            this.humidity = humidity;
        }

        public String serialize() {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return String.format("%s|%s|%.2f|%.1f|%s", sensorId, location, temperature, humidity, timestamp);
        }

        public static void displayDatagram(String datagram, String senderAddr, int senderPort) {
            String[] parts = datagram.split("\\|");
            if (parts.length >= 5) {
                System.out.printf("[UDP Server Received from %s:%d]%n", senderAddr, senderPort);
                System.out.printf("  Sensor ID   : %s%n", parts[0]);
                System.out.printf("  Location    : %s%n", parts[1]);
                System.out.printf("  Temperature : %s °C%n", parts[2]);
                System.out.printf("  Humidity    : %s %%%n", parts[3]);
                System.out.printf("  Timestamp   : %s%n%n", parts[4]);
            } else {
                System.out.println("[UDP Server] Raw Message: " + datagram);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 6002;

        Thread serverThread = new Thread(() -> {
            try (DatagramSocket serverSocket = new DatagramSocket(port)) {
                System.out.println("--- Central Weather Monitoring UDP Server Listening on Port " + port + " ---");
                byte[] buffer = new byte[1024];

                for (int i = 0; i < 5; i++) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    serverSocket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
                    SensorReading.displayDatagram(received, packet.getAddress().getHostAddress(), packet.getPort());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();
        Thread.sleep(300);

        List<SensorReading> telemetryStream = Arrays.asList(
                new SensorReading("SENSOR_NORTH_01", "Rooftop North", 28.45, 65.2),
                new SensorReading("SENSOR_SOUTH_02", "Substation Perimeter", 31.80, 58.7),
                new SensorReading("SENSOR_EAST_03", "Solar Field Inverter", 34.15, 52.0),
                new SensorReading("SENSOR_WEST_04", "Cooling Tower Array", 24.60, 82.4),
                new SensorReading("SENSOR_CORE_05", "Server Room Baseline", 21.30, 45.0)
        );

        System.out.println("--- Transmitting Weather Sensor Telemetry Streams over UDP ---");
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress host = InetAddress.getByName("localhost");

            for (SensorReading reading : telemetryStream) {
                String payload = reading.serialize();
                byte[] data = payload.getBytes(StandardCharsets.UTF_8);
                DatagramPacket packet = new DatagramPacket(data, data.length, host, port);
                clientSocket.send(packet);
                System.out.println("[Sensor Client Transmitted] " + payload);
                Thread.sleep(150);
            }
        }

        Thread.sleep(300);
        System.out.println("--- All Sensor UDP Datagrams Successfully Processed ---");
    }
}
