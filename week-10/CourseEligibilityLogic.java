import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CourseEligibilityLogic {

    public enum Status {
        PASS,
        FAIL
    }

    public static class StudentRecord {
        private final String studentName;
        private final Status pythonStatus;
        private final Status dsStatus;

        public StudentRecord(String studentName, Status pythonStatus, Status dsStatus) {
            this.studentName = studentName;
            this.pythonStatus = pythonStatus;
            this.dsStatus = dsStatus;
        }

        public String getStudentName() {
            return studentName;
        }

        public Status getPythonStatus() {
            return pythonStatus;
        }

        public Status getDsStatus() {
            return dsStatus;
        }

        @Override
        public String toString() {
            return String.format("%-8s | Python: %-4s | Data Structures: %-4s",
                    studentName, pythonStatus, dsStatus);
        }
    }

    public static boolean isEligibleForAdvanced(StudentRecord record) {
        return record.getPythonStatus() == Status.PASS && record.getDsStatus() == Status.PASS;
    }

    public static List<String> queryEligibleStudents(List<StudentRecord> knowledgeBase) {
        return knowledgeBase.stream()
                .filter(CourseEligibilityLogic::isEligibleForAdvanced)
                .map(StudentRecord::getStudentName)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<StudentRecord> knowledgeBase = Arrays.asList(
                new StudentRecord("Arun", Status.PASS, Status.PASS),
                new StudentRecord("Bala", Status.PASS, Status.FAIL),
                new StudentRecord("Charan", Status.PASS, Status.PASS),
                new StudentRecord("Divya", Status.FAIL, Status.PASS)
        );

        System.out.println("--- Knowledge Base: Student Academic Results ---");
        knowledgeBase.forEach(System.out::println);

        System.out.println("\n--- Declarative Rule Evaluation ---");
        for (StudentRecord record : knowledgeBase) {
            boolean eligible = isEligibleForAdvanced(record);
            System.out.printf("Rule: eligible_for_advanced(%s) :- %s\n",
                    record.getStudentName(), eligible ? "SUCCESS (Eligible)" : "FAIL (Not Eligible)");
        }

        System.out.println("\n--- Query: ?- eligible_for_advanced(Student). ---");
        List<String> eligibleList = queryEligibleStudents(knowledgeBase);
        System.out.println("Eligible Students: " + String.join(", ", eligibleList));
    }
}
