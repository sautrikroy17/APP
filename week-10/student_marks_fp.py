from functools import reduce

students = [
    ("Arun", 45),
    ("Bala", 78),
    ("Charan", 89),
    ("Divya", 32),
    ("Esha", 98),
    ("Farhan", 50)
]

print("Original Student Marks:")
for name, mark in students:
    print(f"{name}: {mark}")

passed = list(filter(lambda s: s[1] >= 50, students))
print("\nStudents scoring 50 or above (filter):")
for name, mark in passed:
    print(f"{name}: {mark}")

with_grace = list(map(lambda s: (s[0], min(100, s[1] + 5)), students))
print("\nMarks after 5 grace marks (map, max 100):")
for name, mark in with_grace:
    print(f"{name}: {mark}")

total_original = reduce(lambda acc, s: acc + s[1], students, 0)
total_with_grace = reduce(lambda acc, s: acc + s[1], with_grace, 0)
print(f"\nTotal Marks before grace (reduce): {total_original}")
print(f"Total Marks after grace (reduce): {total_with_grace}")
