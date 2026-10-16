import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentMarksFP {

    public static class Student {
        private final String name;
        private final int mark;

        public Student(String name, int mark) {
            this.name = name;
            this.mark = mark;
        }

        public String getName() {
            return name;
        }

        public int getMark() {
            return mark;
        }

        @Override
        public String toString() {
            return name + " (" + mark + ")";
        }
    }

    public static List<Student> filterPassed(List<Student> students) {
        return students.stream()
                .filter(s -> s.getMark() >= 50)
                .collect(Collectors.toList());
    }

    public static List<Student> awardGraceMarks(List<Student> students, int grace) {
        return students.stream()
                .map(s -> new Student(s.getName(), Math.min(100, s.getMark() + grace)))
                .collect(Collectors.toList());
    }

    public static int calculateTotalMarks(List<Student> students) {
        return students.stream()
                .map(Student::getMark)
                .reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        List<Student> examMarks = Arrays.asList(
                new Student("Arun", 45),
                new Student("Bala", 78),
                new Student("Charan", 89),
                new Student("Divya", 32),
                new Student("Esha", 98),
                new Student("Farhan", 50)
        );

        System.out.println("Original Student Marks:");
        examMarks.forEach(System.out::println);

        List<Student> passed = filterPassed(examMarks);
        System.out.println("\nStudents scoring 50 or above (filter):");
        passed.forEach(System.out::println);

        List<Student> withGrace = awardGraceMarks(examMarks, 5);
        System.out.println("\nMarks after awarding 5 grace marks (map, max 100):");
        withGrace.forEach(System.out::println);

        int totalOriginal = calculateTotalMarks(examMarks);
        int totalWithGrace = calculateTotalMarks(withGrace);
        System.out.println("\nTotal Marks before grace (reduce): " + totalOriginal);
        System.out.println("Total Marks after grace (reduce): " + totalWithGrace);
    }
}
