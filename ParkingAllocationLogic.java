import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingAllocationLogic {

    public enum VehicleType {
        CAR,
        BIKE
    }

    public enum Availability {
        AVAILABLE,
        OCCUPIED
    }

    public static class ParkingSlotFact {
        private final String slotId;
        private final VehicleType vehicleType;
        private final Availability availability;

        public ParkingSlotFact(String slotId, VehicleType vehicleType, Availability availability) {
            this.slotId = slotId;
            this.vehicleType = vehicleType;
            this.availability = availability;
        }

        public String getSlotId() {
            return slotId;
        }

        public VehicleType getVehicleType() {
            return vehicleType;
        }

        public Availability getAvailability() {
            return availability;
        }

        @Override
        public String toString() {
            return String.format("%-4s | %-5s | %s", slotId, vehicleType, availability);
        }
    }

    public static boolean isAvailableSlot(ParkingSlotFact slot, VehicleType targetType) {
        return slot.getVehicleType() == targetType && slot.getAvailability() == Availability.AVAILABLE;
    }

    public static List<String> queryAvailableSlots(List<ParkingSlotFact> knowledgeBase, VehicleType targetType) {
        return knowledgeBase.stream()
                .filter(slot -> isAvailableSlot(slot, targetType))
                .map(ParkingSlotFact::getSlotId)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<ParkingSlotFact> knowledgeBase = Arrays.asList(
                new ParkingSlotFact("S1", VehicleType.CAR, Availability.AVAILABLE),
                new ParkingSlotFact("S2", VehicleType.BIKE, Availability.OCCUPIED),
                new ParkingSlotFact("S3", VehicleType.CAR, Availability.AVAILABLE),
                new ParkingSlotFact("S4", VehicleType.BIKE, Availability.AVAILABLE),
                new ParkingSlotFact("S5", VehicleType.CAR, Availability.OCCUPIED)
        );

        System.out.println("--- Knowledge Base: Mall Parking Slots ---");
        knowledgeBase.forEach(System.out::println);

        System.out.println("\n--- Query: ?- available_slot(Slot, car). ---");
        List<String> carSlots = queryAvailableSlots(knowledgeBase, VehicleType.CAR);
        System.out.println("Available Car Parking Slots: " + String.join(", ", carSlots));

        System.out.println("\n--- Query: ?- available_slot(Slot, bike). ---");
        List<String> bikeSlots = queryAvailableSlots(knowledgeBase, VehicleType.BIKE);
        System.out.println("Available Bike Parking Slots: " + String.join(", ", bikeSlots));
    }
}
