import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionEligibilityLogic {

    public static class EmployeeFact {
        private final String name;
        private final int experience;
        private final boolean trainingCompleted;
        private final int rating;

        public EmployeeFact(String name, int experience, boolean trainingCompleted, int rating) {
            this.name = name;
            this.experience = experience;
            this.trainingCompleted = trainingCompleted;
            this.rating = rating;
        }

        public String getName() {
            return name;
        }

        public int getExperience() {
            return experience;
        }

        public boolean isTrainingCompleted() {
            return trainingCompleted;
        }

        public int getRating() {
            return rating;
        }

        @Override
        public String toString() {
            return String.format("%-6s | Exp: %d yrs | Training: %-13s | Rating: %d/5",
                    name, experience, trainingCompleted ? "Completed" : "Not Completed", rating);
        }
    }

    public static boolean isEligibleForPromotion(EmployeeFact fact) {
        return fact.getExperience() >= 5
                && fact.isTrainingCompleted()
                && fact.getRating() >= 4;
    }

    public static List<String> queryPromotionEligible(List<EmployeeFact> knowledgeBase) {
        return knowledgeBase.stream()
                .filter(PromotionEligibilityLogic::isEligibleForPromotion)
                .map(EmployeeFact::getName)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<EmployeeFact> knowledgeBase = Arrays.asList(
                new EmployeeFact("Ravi", 6, true, 5),
                new EmployeeFact("Priya", 3, true, 4),
                new EmployeeFact("Kumar", 8, false, 5),
                new EmployeeFact("Meena", 7, true, 4)
        );

        System.out.println("--- Knowledge Base: Employee Performance Records ---");
        knowledgeBase.forEach(System.out::println);

        System.out.println("\n--- Evaluation of Promotion Rules ---");
        for (EmployeeFact emp : knowledgeBase) {
            boolean eligible = isEligibleForPromotion(emp);
            System.out.printf("Rule: promotion_eligible(%s) :- %s\n",
                    emp.getName(), eligible ? "ELIGIBLE" : "INELIGIBLE");
        }

        System.out.println("\n--- Query: ?- eligible_for_promotion(Emp). ---");
        List<String> eligible = queryPromotionEligible(knowledgeBase);
        System.out.println("Employees Eligible for Promotion: " + String.join(", ", eligible));
    }
}
