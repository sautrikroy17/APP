student_results = {
    "Arun": {"python": "Pass", "ds": "Pass"},
    "Bala": {"python": "Pass", "ds": "Fail"},
    "Charan": {"python": "Pass", "ds": "Pass"},
    "Divya": {"python": "Fail", "ds": "Pass"}
}

def is_eligible_for_advanced(student):
    results = student_results.get(student, {})
    return results.get("python") == "Pass" and results.get("ds") == "Pass"

def query_eligible_students():
    return [student for student in student_results if is_eligible_for_advanced(student)]

print("Knowledge Base Facts (Student Academic Results):")
for student, record in student_results.items():
    print(f"fact: result({student}, python={record['python']}, ds={record['ds']})")

print("\nLogical Rule:")
print("eligible_for_advanced(Student) :- passed(Student, python), passed(Student, ds).")

print("\nQuery Evaluation: ?- eligible_for_advanced(Student).")
eligible = query_eligible_students()
print(f"Result: {', '.join(eligible)}")
