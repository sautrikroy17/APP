import socket
import sys

def query_student_result(reg_no, host="localhost", port=6001):
    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    try:
        client.connect((host, port))
        client.sendall(reg_no.strip().encode("utf-8"))
        response = client.recv(1024).decode("utf-8")
        return response
    finally:
        client.close()

if __name__ == "__main__":
    if len(sys.argv) > 1:
        reg_number = sys.argv[1]
    else:
        reg_number = input("Enter Student Register Number: ").strip()

    print(f"Connecting to Examination Result Server...")
    result = query_student_result(reg_number)
    print(f"\nResult Received:\n{result}")
