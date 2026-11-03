import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubstringNFA {

    public enum State {
        Q0, Q1, Q2
    }

    public static Set<State> getNextStates(State current, char input) {
        Set<State> next = new HashSet<>();
        switch (current) {
            case Q0:
                if (input == 'a') {
                    next.add(State.Q0);
                    next.add(State.Q1);
                } else if (input == 'b') {
                    next.add(State.Q0);
                }
                break;
            case Q1:
                if (input == 'b') {
                    next.add(State.Q2);
                }
                break;
            case Q2:
                if (input == 'a' || input == 'b') {
                    next.add(State.Q2);
                }
                break;
        }
        return next;
    }

    public static class SimulationResult {
        final boolean accepted;
        final List<Set<State>> path;

        public SimulationResult(boolean accepted, List<Set<State>> path) {
            this.accepted = accepted;
            this.path = path;
        }
    }

    public static SimulationResult evaluateString(String input) {
        Set<State> currentStates = new HashSet<>();
        currentStates.add(State.Q0);

        List<Set<State>> path = new ArrayList<>();
        path.add(new HashSet<>(currentStates));

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch != 'a' && ch != 'b') {
                return new SimulationResult(false, path);
            }

            Set<State> nextStates = new HashSet<>();
            for (State s : currentStates) {
                nextStates.addAll(getNextStates(s, ch));
            }
            currentStates = nextStates;
            path.add(new HashSet<>(currentStates));
        }

        boolean accepted = currentStates.contains(State.Q2);
        return new SimulationResult(accepted, path);
    }

    public static void main(String[] args) {
        System.out.println("===========================================================================");
        System.out.println("TEXT PROCESSING SYSTEM: SUBSTRING 'ab' RECOGNITION (NFA - JAVA)");
        System.out.println("Language: All strings over {a, b} containing substring 'ab'");
        System.out.println("===========================================================================");

        System.out.println("\n1. NFA FORMAL SPECIFICATION:");
        System.out.println("   States       : {Q0, Q1, Q2}");
        System.out.println("   Alphabet     : {'a', 'b'}");
        System.out.println("   Start State  : Q0");
        System.out.println("   Accept State : {Q2}");

        System.out.println("\n2. STATE TRANSITION TABLE:");
        System.out.println("   +---------------+---------------+---------------+");
        System.out.println("   | Current State | Input 'a'     | Input 'b'     |");
        System.out.println("   +---------------+---------------+---------------+");
        System.out.println("   | -> Q0         | {Q0, Q1}      | {Q0}          |");
        System.out.println("   |    Q1         | {}            | {Q2}          |");
        System.out.println("   |  * Q2         | {Q2}          | {Q2}          |");
        System.out.println("   +---------------+---------------+---------------+");

        List<String> testCases = Arrays.asList(
                "ab",
                "aab",
                "bab",
                "abba",
                "bbabb",
                "abaab",
                "bbaab",
                "ba",
                "a",
                "b",
                "bb",
                "bba",
                "aaa",
                "bbb",
                ""
        );

        System.out.println("\n3. SUBSTRING 'ab' EVALUATION TEST SUITE:");
        System.out.printf("   %-15s | %-10s | %s%n", "Input String", "Result", "Active State Sets");
        System.out.println("   ----------------------------------------------------------------------");

        for (String test : testCases) {
            SimulationResult res = evaluateString(test);
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
