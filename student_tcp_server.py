import socket

STUDENT_RECORDS = {
    "RA2311003010001": {"name": "Aarav Sharma", "dept": "CSE", "gpa": 9.12, "result": "PASS", "grades": "Math: O, OS: A+, DS: A+, Java: O"},
    "RA2311003010002": {"name": "Bhavna Patel", "dept": "ECE", "gpa": 8.85, "result": "PASS", "grades": "Math: A+, Signals: A, Circuits: A+, Java: A+"},
    "RA2311003010003": {"name": "Chirag Verma", "dept": "CSE", "gpa": 7.40, "result": "PASS", "grades": "Math: B+, OS: A, DS: B+, Java: A"},
    "RA2311003010004": {"name": "Diya Nair", "dept": "IT", "gpa": 9.65, "result": "PASS", "grades": "Math: O, OS: O, DS: O, Java: O"},
    "RA2311003010005": {"name": "Eshan Gupta", "dept": "Mech", "gpa": 4.20, "result": "REAPPEAR", "grades": "Thermo: F, Math: P, Mechanics: C, Java: P"},
}

def format_student_result(reg_no):
    reg_clean = reg_no.strip()
    if reg_clean in STUDENT_RECORDS:
        rec = STUDENT_RECORDS[reg_clean]
        return f"RegNo: {reg_clean} | Name: {rec['name']} | Dept: {rec['dept']} | GPA: {rec['gpa']} | Status: {rec['result']} | Grades: [{rec['grades']}]"
    return f"ERROR: Student with register number '{reg_clean}' was not found in central records."

def run_server(host="localhost", port=6001):
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server.bind((host, port))
    server.listen(5)
    print(f"[TCP Server] Listening for student result requests on {host}:{port}...")

    try:
        while True:
            client_conn, client_addr = server.accept()
            data = client_conn.recv(1024).decode("utf-8")
            if data:
                print(f"[TCP Server] Query received from {client_addr} for Register No: {data.strip()}")
                response = format_student_result(data)
                client_conn.sendall(response.encode("utf-8"))
            client_conn.close()
    except KeyboardInterrupt:
        print("\n[TCP Server] Shutting down.")
    finally:
        server.close()

if __name__ == "__main__":
    run_server()
