parking_slots = [
    ("S1", "Car", "Available"),
    ("S2", "Bike", "Occupied"),
    ("S3", "Car", "Available"),
    ("S4", "Bike", "Available"),
    ("S5", "Car", "Occupied")
]

def is_available(slot_tuple, vehicle_type):
    slot_id, vtype, status = slot_tuple
    return vtype.lower() == vehicle_type.lower() and status.lower() == "available"

def query_available_slots(vehicle_type):
    return [slot_id for slot_id, vtype, status in parking_slots if is_available((slot_id, vtype, status), vehicle_type)]

print("Knowledge Base Facts (Mall Parking Slots):")
for slot_id, vtype, status in parking_slots:
    print(f"fact: parking_slot({slot_id}, {vtype}, {status})")

print("\nLogical Rule:")
print("available_slot(Slot, VehicleType) :- parking_slot(Slot, VehicleType, available).")

print("\nQuery Evaluation: ?- available_slot(Slot, car).")
car_slots = query_available_slots("car")
print(f"Available Car Slots: {', '.join(car_slots)}")

print("\nQuery Evaluation: ?- available_slot(Slot, bike).")
bike_slots = query_available_slots("bike")
print(f"Available Bike Slots: {', '.join(bike_slots)}")
