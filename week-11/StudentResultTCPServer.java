import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class StudentResultTCPServer {

    private static final Map<String, String> STUDENT_RECORDS = new HashMap<>();

    static {
        STUDENT_RECORDS.put("RA2311003010001", "Name: Aarav Sharma | Dept: CSE | GPA: 9.12 | Result: PASS");
        STUDENT_RECORDS.put("RA2311003010002", "Name: Bhavna Patel | Dept: ECE | GPA: 8.85 | Result: PASS");
        STUDENT_RECORDS.put("RA2311003010003", "Name: Chirag Verma | Dept: CSE | GPA: 7.40 | Result: PASS");
        STUDENT_RECORDS.put("RA2311003010004", "Name: Diya Nair | Dept: IT | GPA: 9.65 | Result: PASS");
        STUDENT_RECORDS.put("RA2311003010005", "Name: Eshan Gupta | Dept: Mech | GPA: 4.20 | Result: REAPPEAR");
    }

    public static void main(String[] args) {
        int port = 6001;
        System.out.println("Central Student Information TCP Server started on port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                    String regNo = reader.readLine();
                    if (regNo != null) {
                        regNo = regNo.trim();
                        System.out.println("Received result query for: " + regNo);
                        String result = STUDENT_RECORDS.getOrDefault(regNo, "ERROR: Register number not found.");
                        writer.println(result);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
