import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ElectricityMeterFP {

    public static class DayReading {
        private final int day;
        private final double units;

        public DayReading(int day, double units) {
            this.day = day;
            this.units = units;
        }

        public int getDay() {
            return day;
        }

        public double getUnits() {
            return units;
        }

        @Override
        public String toString() {
            return String.format("Day %d: %.1f units", day, units);
        }
    }

    public static List<DayReading> filterHighConsumption(List<DayReading> readings, double threshold) {
        return readings.stream()
                .filter(r -> r.getUnits() > threshold)
                .collect(Collectors.toList());
    }

    public static List<Double> calculateDailyCosts(List<DayReading> readings, double ratePerUnit) {
        return readings.stream()
                .map(r -> r.getUnits() * ratePerUnit)
                .collect(Collectors.toList());
    }

    public static double calculateTotalUnits(List<DayReading> readings) {
        return readings.stream()
                .map(DayReading::getUnits)
                .reduce(0.0, Double::sum);
    }

    public static double computeTotalBill(List<DayReading> readings, double ratePerUnit) {
        return readings.stream()
                .map(r -> r.getUnits() * ratePerUnit)
                .reduce(0.0, Double::sum);
    }

    public static void main(String[] args) {
        List<DayReading> monthReadings = Arrays.asList(
                new DayReading(1, 8.5),
                new DayReading(2, 12.0),
                new DayReading(3, 15.5),
                new DayReading(4, 7.0),
                new DayReading(5, 18.2),
                new DayReading(6, 9.8),
                new DayReading(7, 14.1),
                new DayReading(8, 6.4),
                new DayReading(9, 11.0),
                new DayReading(10, 20.3)
        );

        System.out.println("Household Daily Consumption Records:");
        monthReadings.forEach(System.out::println);

        List<DayReading> highDays = filterHighConsumption(monthReadings, 10.0);
        System.out.println("\nDays with Consumption > 10 Units (filter):");
        highDays.forEach(System.out::println);

        double rate = 6.0;
        List<Double> costs = calculateDailyCosts(monthReadings, rate);
        System.out.println("\nDaily Electricity Costs at ₹6 per Unit (map):");
        for (int i = 0; i < monthReadings.size(); i++) {
            System.out.printf("Day %d (%.1f units): ₹%.2f\n",
                    monthReadings.get(i).getDay(),
                    monthReadings.get(i).getUnits(),
                    costs.get(i));
        }

        double totalUnits = calculateTotalUnits(monthReadings);
        System.out.printf("\nTotal Electricity Consumption (reduce): %.2f units\n", totalUnits);

        double totalBill = computeTotalBill(monthReadings, rate);
        System.out.printf("Total Electricity Bill via Pure Function: ₹%.2f\n", totalBill);
    }
}
