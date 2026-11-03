import socket
import threading
import time

STUDENT_RECORDS = {
    "RA2311003010001": {"name": "Aarav Sharma", "dept": "CSE", "gpa": 9.12, "result": "PASS", "grades": "Math: O, OS: A+, DS: A+, Java: O"},
    "RA2311003010002": {"name": "Bhavna Patel", "dept": "ECE", "gpa": 8.85, "result": "PASS", "grades": "Math: A+, Signals: A, Circuits: A+, Java: A+"},
    "RA2311003010003": {"name": "Chirag Verma", "dept": "CSE", "gpa": 7.40, "result": "PASS", "grades": "Math: B+, OS: A, DS: B+, Java: A"},
    "RA2311003010004": {"name": "Diya Nair", "dept": "IT", "gpa": 9.65, "result": "PASS", "grades": "Math: O, OS: O, DS: O, Java: O"},
    "RA2311003010005": {"name": "Eshan Gupta", "dept": "Mech", "gpa": 4.20, "result": "REAPPEAR", "grades": "Thermo: F, Math: P, Mechanics: C, Java: P"},
}

def lookup_result(reg_no):
    key = reg_no.strip()
    if key in STUDENT_RECORDS:
        rec = STUDENT_RECORDS[key]
        return f"RegNo: {key} | Name: {rec['name']} | Dept: {rec['dept']} | GPA: {rec['gpa']} | Status: {rec['result']} | Grades: [{rec['grades']}]"
    return f"ERROR: Register number '{key}' not found in examination records."

def start_server_daemon(port, stop_event):
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server.bind(("localhost", port))
    server.listen(5)
    server.settimeout(0.5)

    while not stop_event.is_set():
        try:
            conn, addr = server.accept()
            req = conn.recv(1024).decode("utf-8")
            if req:
                resp = lookup_result(req)
                conn.sendall(resp.encode("utf-8"))
            conn.close()
        except socket.timeout:
            continue
    server.close()

def client_request(port, reg_no):
    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client.connect(("localhost", port))
    client.sendall(reg_no.encode("utf-8"))
    data = client.recv(1024).decode("utf-8")
    client.close()
    return data

def main():
    port = 6001
    stop_event = threading.Event()
    server_t = threading.Thread(target=start_server_daemon, args=(port, stop_event), daemon=True)
    server_t.start()
    time.sleep(0.2)

    queries = [
        "RA2311003010001",
        "RA2311003010002",
        "RA2311003010003",
        "RA2311003010004",
        "RA2311003010005",
        "RA2311003010999"
    ]

    print("=" * 70)
    print("TCP CLIENT-SERVER STUDENT RESULT RETRIEVAL SYSTEM")
    print("=" * 70)

    for q in queries:
        print(f"[Client Sending Query]  -> Register No: {q}")
        response = client_request(port, q)
        print(f"[Server Response]       <- {response}\n")

    stop_event.set()
    server_t.join(timeout=1.0)
    print("=" * 70)
    print("All TCP examination result transactions completed successfully.")
    print("=" * 70)

if __name__ == "__main__":
    main()
