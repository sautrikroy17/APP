# Week 11: Network Socket Programming, Symbolic Mathematics & Automata Theory

**Course:** `21CSC203P - Advanced Programming Practice`  
**Tutorial Date:** 03-11-2026  
**Department:** Computer Science and Engineering  

---

## 📂 Overview

This directory contains pure, verified solutions for **Tutorial 11** covering:
1. **Network Socket Programming:** Reliable connection-oriented TCP client–server communication for student record lookup and lightweight connectionless UDP datagram streaming for real-time weather sensor telemetry.
2. **Symbolic Mathematics & Calculus:** Algebraic expansion, factorization, rational simplification, quadratic equation solving, symbolic differentiation, and definite integration using SymPy and analytical Java kinematics engines.
3. **Formal Languages & Automata Theory:** Deterministic Finite Automata (DFA) access code validation and Non-deterministic Finite Automata (NFA) substring recognition using the Automata library and pure Java state automata engines.

---

## 📑 Program Summary

| Problem | Java Implementation | Python Implementation | Core Topic |
| :--- | :--- | :--- | :--- |
| **Q1. Student Result Query** | `StudentResultTCP.java`<br>`StudentResultTCPServer.java`<br>`StudentResultTCPClient.java` | `student_result_tcp.py`<br>`student_tcp_server.py`<br>`student_tcp_client.py` | TCP Sockets (`ServerSocket`, `Socket`, bidirectional stream) |
| **Q2. Weather Monitoring Station** | `WeatherMonitoringUDP.java`<br>`WeatherUDPServer.java`<br>`WeatherUDPClient.java` | `weather_monitoring_udp.py`<br>`weather_udp_server.py`<br>`weather_udp_client.py` | UDP Datagrams (`DatagramSocket`, `DatagramPacket`, telemetry) |
| **Q3. Production Cost Algebra** | `ProductionCostAlgebra.java` | `production_cost_algebra.py` | SymPy algebra (`expand`, `factor`, `simplify`, `solve`) |
| **Q4. Vehicle Motion Kinematics** | `VehicleKinematics.java` | `vehicle_kinematics.py` | SymPy calculus (`diff`, `integrate`, stationary points) |
| **Q5. Binary Access Code Validator** | `BinaryAccessCodeDFA.java` | `binary_access_code_dfa.py` | DFA (`automata-lib`, states ending in `"01"`) |
| **Q6. Substring Pattern Recognizer** | `SubstringNFA.java` | `substring_ab_nfa.py` | NFA (`automata-lib`, substring `"ab"` containment) |

---

## 📝 Detailed Question Solutions & Concept Explanations

### Question 1: Student Information System (TCP Client–Server)
- **Problem Statement:** A central examination server stores student results. A client transmits a student's registration number over TCP, and the server queries its database to return examination results and GPA.
- **Architectural Workflow:**
  1. **TCP Handshake:** Establishes a reliable, ordered, full-duplex byte stream via TCP 3-way handshake (`SYN`, `SYN-ACK`, `ACK`).
  2. **Client Request:** Client transmits ASCII registration numbers (e.g. `RA2311003010001`).
  3. **Server Lookup:** Server parses the request, performs an $O(1)$ lookup in the student database, and formats the student profile (Name, Department, GPA, Result Status, Subject Grades).
  4. **Response Delivery:** Server streams the formatted result back to the client and gracefully closes connection.

---

### Question 2: Weather Monitoring Station (UDP Client–Server & Justification)
- **Problem Statement:** Multiple environmental sensors stream temperature and humidity telemetry to a central monitoring station using UDP.
- **Suitability Justification of UDP over TCP:**
  1. **Minimal Header Overhead:**
     - UDP features a compact 8-byte header compared to TCP's 20–60 byte header.
     - For periodic micro-telemetry payloads (20–40 bytes), UDP reduces protocol overhead by over 60%, drastically conserving network bandwidth across dense IoT sensor networks.
  2. **Connectionless & Energy Efficiency:**
     - UDP eliminates connection setup (`SYN`/`ACK`) and teardown handshakes.
     - Battery-powered IoT sensors can wake from deep sleep, dispatch a single datagram, and return to low-power sleep within milliseconds, extending field battery lifetimes.
  3. **Real-Time Freshness & Loss Tolerance:**
     - In periodic environmental monitoring (e.g. readings every 1–5 seconds), an occasional dropped packet is harmless because the subsequent datagram delivers the latest state.
     - TCP's mandatory acknowledgement and retransmission causes head-of-line blocking, needlessly transmitting outdated temperature data.
  4. **Multicast and Broadcast Capability:**
     - UDP natively supports broadcasting datagrams to multiple dashboard endpoints simultaneously without maintaining server-side connection state.

---

### Question 3: Manufacturing Production Cost Algebra (SymPy)
- **Problem Statement:** Simplify mathematical expressions for production cost modeling, expand/factorize polynomials, simplify rational cost functions, and solve quadratic cost equations for production quantities.
- **Algebraic Derivations:**
  - **Expansion:** Factored cost function $(2q + 15)(q + 40)$ expands to:
    $$C(q) = 2q^2 + 95q + 600$$
  - **Factorization:** Unfactored cost expression $3q^2 + 18q + 24$ factors to:
    $$P(q) = 3(q + 2)(q + 4)$$
  - **Rational Simplification:**
    $$R(q) = \frac{2q^3 + 10q^2 + 12q}{2q^2 + 4q} = \frac{2q(q + 2)(q + 3)}{2q(q + 2)} = q + 3$$
  - **Quadratic Solution:**
    $$2q^2 + 30q - 1400 = 0 \iff q^2 + 15q - 700 = 0 \iff (q - 20)(q + 35) = 0$$
    Mathematical roots: $q \in \{20, -35\}$.  
    Since physical production quantity cannot be negative ($q \ge 0$), the optimal output quantity is **$q = 20$ units**.

---

### Question 4: Automobile Motion Analysis (SymPy Calculus)
- **Displacement Function:**
  $$s(t) = t^3 - 6t^2 + 9t + 5$$
- **Velocity Function:** First derivative with respect to time $t$:
  $$v(t) = \frac{ds}{dt} = 3t^2 - 12t + 9$$
- **Acceleration Function:** Second derivative with respect to time $t$:
  $$a(t) = \frac{dv}{dt} = \frac{d^2s}{dt^2} = 6t - 12$$
- **Momentary Rest Times ($v(t) = 0$):**
  $$3t^2 - 12t + 9 = 0 \implies 3(t^2 - 4t + 3) = 0 \implies (t - 1)(t - 3) = 0$$
  Vehicle is at rest at **$t = 1\text{ second}$** and **$t = 3\text{ seconds}$**.
  - At $t = 1\text{ s}$: $s(1) = 9\text{ m}$, $a(1) = -6\text{ m/s}^2$ (decelerating)
  - At $t = 3\text{ s}$: $s(3) = 5\text{ m}$, $a(3) = +6\text{ m/s}^2$ (accelerating forward)
- **Definite Integration over Interval $[0, 5]$ seconds:**
  $$\Delta s = \int_{0}^{5} (3t^2 - 12t + 9) \, dt = \left[ t^3 - 6t^2 + 9t \right]_0^5 = 125 - 150 + 45 = 20\text{ meters}$$
  Analytical check: $s(5) - s(0) = 25 - 5 = 20\text{ meters}$.

---

### Question 5: Digital Security Binary Access Code Validator (DFA)
- **Language:** All binary strings over $\Sigma = \{0, 1\}$ terminating in `"01"`.
- **Formal 5-Tuple Specification:**
  - $Q = \{q_0, q_1, q_2\}$
  - $\Sigma = \{0, 1\}$
  - Initial State: $q_0$
  - Accept State: $F = \{q_2\}$
  - Transition Function $\delta$:
- **State Transition Table:**

| Current State | Input '0' | Input '1' | State Interpretation |
| :---: | :---: | :---: | :--- |
| $\rightarrow q_0$ | $q_1$ | $q_0$ | Initial state / Suffix not matching |
| $q_1$ | $q_1$ | $q_2$ | Last symbol observed is '0' |
| $*q_2$ | $q_1$ | $q_0$ | **Accept State**: Last two symbols are "01" |

---

### Question 6: Substring Pattern Recognizer (NFA)
- **Language:** All strings over $\Sigma = \{a, b\}$ containing the substring `"ab"`.
- **Formal 5-Tuple Specification:**
  - $Q = \{q_0, q_1, q_2\}$
  - $\Sigma = \{a, b\}$
  - Initial State: $q_0$
  - Accept State: $F = \{q_2\}$
  - Transition Function $\delta$:
- **State Transition Table:**

| Current State | Input 'a' | Input 'b' | State Interpretation |
| :---: | :---: | :---: | :--- |
| $\rightarrow q_0$ | $\{q_0, q_1\}$ | $\{q_0\}$ | Scanning prefix non-deterministically |
| $q_1$ | $\emptyset$ | $\{q_2\}$ | Matched 'a', transitioning on 'b' |
| $*q_2$ | $\{q_2\}$ | $\{q_2\}$ | **Accept State**: Substring "ab" detected, absorbing loop |

---
