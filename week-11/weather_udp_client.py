import datetime
import socket
import sys

def send_weather_reading(sensor_id, temperature, humidity, host="localhost", port=6002):
    client_sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    timestamp = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    payload = f"Sensor: {sensor_id} | Temp: {temperature:.2f} C | Humidity: {humidity:.1f} % | Timestamp: {timestamp}"

    try:
        client_sock.sendto(payload.encode("utf-8"), (host, port))
        print(f"[UDP Client Sent] {payload}")
    finally:
        client_sock.close()

if __name__ == "__main__":
    sensor = sys.argv[1] if len(sys.argv) > 1 else "WEATHER_SENSOR_01"
    temp = float(sys.argv[2]) if len(sys.argv) > 2 else 28.5
    hum = float(sys.argv[3]) if len(sys.argv) > 3 else 62.0

    send_weather_reading(sensor, temp, hum)
