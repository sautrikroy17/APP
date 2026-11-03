import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryAccessCodeDFA {

    public enum State {
        Q0, Q1, Q2
    }

    public static State transition(State current, char input) {
        switch (current) {
            case Q0:
                return (input == '0') ? State.Q1 : State.Q0;
            case Q1:
                return (input == '0') ? State.Q1 : State.Q2;
            case Q2:
                return (input == '0') ? State.Q1 : State.Q0;
            default:
                return State.Q0;
        }
    }

    public static class SimulationResult {
        final boolean accepted;
        final List<State> path;

        public SimulationResult(boolean accepted, List<State> path) {
            this.accepted = accepted;
            this.path = path;
        }
    }

    public static SimulationResult validateCode(String binaryString) {
        State current = State.Q0;
        List<State> path = new ArrayList<>();
        path.add(current);

        for (int i = 0; i < binaryString.length(); i++) {
            char ch = binaryString.charAt(i);
            if (ch != '0' && ch != '1') {
                return new SimulationResult(false, path);
            }
            current = transition(current, ch);
            path.add(current);
        }

        boolean accepted = (current == State.Q2);
        return new SimulationResult(accepted, path);
    }

    public static void main(String[] args) {
        System.out.println("===========================================================================");
        System.out.println("DIGITAL SECURITY SYSTEM: BINARY ACCESS CODE VALIDATION (DFA - JAVA)");
        System.out.println("Pattern Constraint: Binary strings terminating with '01'");
        System.out.println("===========================================================================");

        System.out.println("\n1. DFA FORMAL SPECIFICATION:");
        System.out.println("   States       : {Q0, Q1, Q2}");
        System.out.println("   Alphabet     : {'0', '1'}");
        System.out.println("   Start State  : Q0");
        System.out.println("   Accept State : {Q2}");

        System.out.println("\n2. STATE TRANSITION TABLE:");
        System.out.println("   +---------------+-----------+-----------+");
        System.out.println("   | Current State | Input '0' | Input '1' |");
        System.out.println("   +---------------+-----------+-----------+");
        System.out.println("   | -> Q0         | Q1        | Q0        |");
        System.out.println("   |    Q1         | Q1        | Q2        |");
        System.out.println("   |  * Q2         | Q1        | Q0        |");
        System.out.println("   +---------------+-----------+-----------+");

        List<String> testCases = Arrays.asList(
                "01",
                "101",
                "001",
                "1101",
                "1001",
                "00001",
                "1110101",
                "0",
                "1",
                "10",
                "100",
                "010",
                "111",
                "000",
                ""
        );

        System.out.println("\n3. VALIDATION TEST SUITE RESULTS:");
        System.out.printf("   %-15s | %-10s | %s%n", "Access Code", "Result", "State Path");
        System.out.println("   ------------------------------------------------------------");

        for (String test : testCases) {
            SimulationResult res = validateCode(test);
            String label = test.isEmpty() ? "\"\" (empty)" : test;
            String status = res.accepted ? "ACCEPTED" : "REJECTED";

            StringBuilder pathStr = new StringBuilder();
            for (int i = 0; i < res.path.size(); i++) {
                pathStr.append(res.path.get(i));
                if (i < res.path.size() - 1) {
                    pathStr.append(" -> ");
                }
            }

            System.out.printf("   %-15s | %-10s | %s%n", label, status, pathStr.toString());
        }
        System.out.println("===========================================================================");
    }
}
