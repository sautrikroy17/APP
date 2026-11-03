import socket

def run_udp_server(host="localhost", port=6002):
    server_sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_sock.bind((host, port))
    print(f"[UDP Server] Central Weather Station listening on {host}:{port}...")

    try:
        while True:
            data, client_addr = server_sock.recvfrom(1024)
            reading = data.decode("utf-8")
            print(f"[UDP Server] Telemetry from {client_addr}:")
            print(f"  {reading}\n")
    except KeyboardInterrupt:
        print("\n[UDP Server] Terminating.")
    finally:
        server_sock.close()

if __name__ == "__main__":
    run_udp_server()
