employees = {
    "Ravi": {"experience": 6, "training": "Completed", "rating": 5},
    "Priya": {"experience": 3, "training": "Completed", "rating": 4},
    "Kumar": {"experience": 8, "training": "Not Completed", "rating": 5},
    "Meena": {"experience": 7, "training": "Completed", "rating": 4}
}

def is_eligible_for_promotion(emp):
    record = employees.get(emp, {})
    return (
        record.get("experience", 0) >= 5
        and record.get("training") == "Completed"
        and record.get("rating", 0) >= 4
    )

def query_promotion_candidates():
    return [emp for emp in employees if is_eligible_for_promotion(emp)]

print("Knowledge Base Facts (Employee Records):")
for emp, data in employees.items():
    print(f"fact: employee({emp}, exp={data['experience']}, training={data['training']}, rating={data['rating']})")

print("\nLogical Rule:")
print("eligible_for_promotion(Emp) :- experience(Emp) >= 5, training(Emp, completed), rating(Emp) >= 4.")

print("\nQuery Evaluation: ?- eligible_for_promotion(Emp).")
promoted = query_promotion_candidates()
print(f"Result: {', '.join(promoted)}")
