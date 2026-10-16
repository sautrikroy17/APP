parking_slot(s1, car, available).
parking_slot(s2, bike, occupied).
parking_slot(s3, car, available).
parking_slot(s4, bike, available).
parking_slot(s5, car, occupied).

available_slot(Slot, VehicleType) :-
    parking_slot(Slot, VehicleType, available).
