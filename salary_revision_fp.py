from functools import reduce

employees = [
    ("Ravi", 35000.0),
    ("Priya", 42000.0),
    ("Kumar", 28000.0),
    ("Meena", 55000.0),
    ("Suresh", 39000.0),
    ("Ananya", 60000.0)
]

print("Original Salaries:")
for name, sal in employees:
    print(f"{name}: ₹{sal:.2f}")

eligible = list(filter(lambda e: e[1] < 40000.0, employees))
print("\nEligible for Increment (< ₹40,000) (filter):")
for name, sal in eligible:
    print(f"{name}: ₹{sal:.2f}")

revised = list(map(lambda e: (e[0], e[1] * 1.10 if e[1] < 40000.0 else e[1]), employees))
print("\nSalaries After 10% Increment (map):")
for name, sal in revised:
    print(f"{name}: ₹{sal:.2f}")

total_before = reduce(lambda acc, e: acc + e[1], employees, 0.0)
total_after = reduce(lambda acc, e: acc + e[1], revised, 0.0)
print(f"\nTotal Expenditure Before Revision: ₹{total_before:.2f}")
print(f"Total Expenditure After Revision: ₹{total_after:.2f}")
print(f"Net Additional Cost: ₹{(total_after - total_before):.2f}")
