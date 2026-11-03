import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class StudentResultTCPClient {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 6001;

        String regNo;
        if (args.length > 0) {
            regNo = args[0];
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Student Register Number: ");
            regNo = scanner.nextLine().trim();
        }

        try (Socket socket = new Socket(host, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connecting to Exam Result Server at " + host + ":" + port);
            writer.println(regNo);

            String response = reader.readLine();
            System.out.println("Received Examination Result: " + response);
        } catch (Exception e) {
            System.err.println("Communication error: " + e.getMessage());
        }
    }
}
