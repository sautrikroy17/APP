import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class StudentResultTCP {

    public static class StudentRecord {
        private final String regNumber;
        private final String name;
        private final String department;
        private final double gpa;
        private final String result;
        private final String grades;

        public StudentRecord(String regNumber, String name, String department, double gpa, String result, String grades) {
            this.regNumber = regNumber;
            this.name = name;
            this.department = department;
            this.gpa = gpa;
            this.result = result;
            this.grades = grades;
        }

        public String formatResult() {
            return String.format("RegNo: %s | Name: %s | Dept: %s | GPA: %.2f | Result: %s | Details: [%s]",
                    regNumber, name, department, gpa, result, grades);
        }
    }

    public static final Map<String, StudentRecord> DATABASE = new HashMap<>();

    static {
        DATABASE.put("RA2311003010001", new StudentRecord("RA2311003010001", "Aarav Sharma", "CSE", 9.12, "PASS", "Math: O, OS: A+, DS: A+, Java: O"));
        DATABASE.put("RA2311003010002", new StudentRecord("RA2311003010002", "Bhavna Patel", "ECE", 8.85, "PASS", "Math: A+, Signals: A, Circuits: A+, Java: A+"));
        DATABASE.put("RA2311003010003", new StudentRecord("RA2311003010003", "Chirag Verma", "CSE", 7.40, "PASS", "Math: B+, OS: A, DS: B+, Java: A"));
        DATABASE.put("RA2311003010004", new StudentRecord("RA2311003010004", "Diya Nair", "IT", 9.65, "PASS", "Math: O, OS: O, DS: O, Java: O"));
        DATABASE.put("RA2311003010005", new StudentRecord("RA2311003010005", "Eshan Gupta", "Mech", 4.20, "REAPPEAR", "Thermo: F, Math: P, Mechanics: C, Java: P"));
    }

    public static String queryDatabase(String regNo) {
        StudentRecord record = DATABASE.get(regNo.trim());
        if (record != null) {
            return record.formatResult();
        }
        return "ERROR: Student with register number " + regNo.trim() + " not found.";
    }

    public static void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[TCP Server] Listening on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String regNo = in.readLine();
                if (regNo != null) {
                    System.out.println("[TCP Server] Received query for Register No: " + regNo);
                    String response = queryDatabase(regNo);
                    out.println(response);
                }
                clientSocket.close();
            }
        } catch (Exception e) {
            System.out.println("[TCP Server Stopped] " + e.getMessage());
        }
    }

    public static String queryClient(String host, int port, String regNo) {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(regNo);
            return in.readLine();
        } catch (Exception e) {
            return "Connection failed: " + e.getMessage();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 6001;

        if (args.length > 0 && args[0].equalsIgnoreCase("server")) {
            startServer(port);
            return;
        }

        if (args.length > 1 && args[0].equalsIgnoreCase("client")) {
            String res = queryClient("localhost", port, args[1]);
            System.out.println("[Client Received] " + res);
            return;
        }

        Thread serverThread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("--- Student Information TCP Service Initialized on port " + port + " ---");
                for (int i = 0; i < 6; i++) {
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    String regNo = in.readLine();
                    String response = queryDatabase(regNo);
                    out.println(response);
                    clientSocket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();
        Thread.sleep(300);

        String[] testQueries = {
                "RA2311003010001",
                "RA2311003010002",
                "RA2311003010003",
                "RA2311003010004",
                "RA2311003010005",
                "RA2311003010999"
        };

        System.out.println("--- Client Dispatching Result Queries over TCP ---");
        for (String q : testQueries) {
            System.out.println("[Client Requesting] Register No: " + q);
            String response = queryClient("localhost", port, q);
            System.out.println("[Server Response]   " + response);
            System.out.println();
        }
        System.out.println("--- All TCP Student Queries Processed Successfully ---");
    }
}
