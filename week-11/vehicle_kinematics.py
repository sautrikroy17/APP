import sympy as sp

def main():
    print("=" * 70)
    print("AUTOMOBILE KINEMATICS & MOTION ANALYSIS (SYMPY)")
    print("=" * 70)

    t = sp.symbols('t', real=True, positive=True)

    s = t**3 - 6 * t**2 + 9 * t + 5
    print("\n1. DISPLACEMENT FUNCTION:")
    print(f"   s(t) = {s}")

    v = sp.diff(s, t)
    print("\n2. VELOCITY FUNCTION v(t) = ds/dt:")
    print(f"   v(t) = {v}")

    a = sp.diff(v, t)
    print("\n3. ACCELERATION FUNCTION a(t) = dv/dt = d^2s/dt^2:")
    print(f"   a(t) = {a}")

    rest_condition = sp.Eq(v, 0)
    rest_times = sp.solve(rest_condition, t)
    print("\n4. TIME AT WHICH VEHICLE IS MOMENTARILY AT REST (v(t) = 0):")
    print(f"   Rest Condition Equation : {rest_condition}")
    print(f"   Vehicle Rest Timestamps : t = {rest_times} seconds")
    for idx, r_t in enumerate(rest_times, 1):
        pos_at_rest = s.subs(t, r_t)
        acc_at_rest = a.subs(t, r_t)
        print(f"   At t = {r_t} s -> Displacement s({r_t}) = {pos_at_rest} m, Acceleration a({r_t}) = {acc_at_rest} m/s^2")

    t1, t2 = 0, 5
    displacement_interval = sp.integrate(v, (t, t1, t2))
    print(f"\n5. DISPLACEMENT OVER TIME INTERVAL [{t1}, {t2}] SECONDS USING DEFINITE INTEGRATION:")
    print(f"   Integral : ∫[{t1} to {t2}] ({v}) dt")
    print(f"   Net Displacement Δs = {displacement_interval} meters")

    s_t1 = s.subs(t, t1)
    s_t2 = s.subs(t, t2)
    analytical_check = s_t2 - s_t1
    print(f"   Analytical Check s({t2}) - s({t1}) = {s_t2} - {s_t1} = {analytical_check} meters")
    print("=" * 70)

if __name__ == "__main__":
    main()
