import datetime
import socket
import threading
import time

def udp_server_worker(port, stop_event):
    server = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server.bind(("localhost", port))
    server.settimeout(0.5)

    while not stop_event.is_set():
        try:
            data, addr = server.recvfrom(1024)
            msg = data.decode("utf-8")
            print(f"[Server Received from {addr[0]}:{addr[1]}] -> {msg}")
        except socket.timeout:
            continue
    server.close()

def main():
    port = 6002
    stop_event = threading.Event()
    server_thread = threading.Thread(target=udp_server_worker, args=(port, stop_event), daemon=True)
    server_thread.start()
    time.sleep(0.2)

    sensors_data = [
        ("SENSOR_NORTH_01", 28.45, 65.2),
        ("SENSOR_SOUTH_02", 31.80, 58.7),
        ("SENSOR_EAST_03", 34.15, 52.0),
        ("SENSOR_WEST_04", 24.60, 82.4),
        ("SENSOR_CORE_05", 21.30, 45.0)
    ]

    print("=" * 75)
    print("UDP CLIENT-SERVER WEATHER MONITORING APPLICATION")
    print("=" * 75)

    client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    for sensor_id, temp, hum in sensors_data:
        ts = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        payload = f"SensorID: {sensor_id} | Temp: {temp:.2f} C | Humidity: {hum:.1f} % | Time: {ts}"
        client.sendto(payload.encode("utf-8"), ("localhost", port))
        print(f"[Client Dispatched]     -> {payload}")
        time.sleep(0.1)

    client.close()
    time.sleep(0.3)
    stop_event.set()
    server_thread.join(timeout=1.0)

    print("\n" + "=" * 75)
    print("JUSTIFICATION: SUITABILITY OF UDP OVER TCP FOR WEATHER MONITORING")
    print("=" * 75)
    justification = """
1. Minimal Header Overhead:
   - UDP adds only an 8-byte header compared to TCP's 20-60 byte header.
   - For frequent micro-telemetry payloads (20-60 bytes), UDP cuts packet overhead by over 60%,
     conserving bandwidth across large-scale distributed IoT networks.

2. Connectionless & Energy Efficiency:
   - UDP requires no 3-way handshake (SYN, SYN-ACK, ACK) and no connection teardown.
   - Remote battery-operated environmental sensors can wake up from deep sleep, transmit datagrams
     immediately, and power down within milliseconds, dramatically extending sensor battery lifespans.

3. Low Latency & High Tolerance to Packet Loss:
   - In periodic weather telemetry (e.g., transmitting every few seconds), a dropped reading is
     naturally superseded by the subsequent reading.
   - TCP's guaranteed delivery causes head-of-line blocking and delayed retransmissions of stale data,
     which is counterproductive for real-time monitoring.

4. Natural Multicast / Broadcast Support:
   - UDP seamlessly enables sensor hubs to broadcast readings to multiple analytics nodes simultaneously
     without maintaining complex multi-connection states on resource-constrained embedded devices.
"""
    print(justification.strip())
    print("=" * 75)

if __name__ == "__main__":
    main()
