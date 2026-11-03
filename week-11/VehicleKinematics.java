import java.util.ArrayList;
import java.util.List;

public class VehicleKinematics {

    public static double displacement(double t) {
        return Math.pow(t, 3) - (6 * Math.pow(t, 2)) + (9 * t) + 5;
    }

    public static double velocity(double t) {
        return (3 * Math.pow(t, 2)) - (12 * t) + 9;
    }

    public static double acceleration(double t) {
        return (6 * t) - 12;
    }

    public static List<Double> findRestTimes() {
        List<Double> restTimes = new ArrayList<>();
        double a = 3.0;
        double b = -12.0;
        double c = 9.0;
        double disc = (b * b) - (4 * a * c);
        if (disc >= 0) {
            double sqrtD = Math.sqrt(disc);
            restTimes.add((-b - sqrtD) / (2 * a));
            restTimes.add((-b + sqrtD) / (2 * a));
        }
        return restTimes;
    }

    public static double integrateVelocity(double t1, double t2) {
        double f2 = Math.pow(t2, 3) - (6 * Math.pow(t2, 2)) + (9 * t2);
        double f1 = Math.pow(t1, 3) - (6 * Math.pow(t1, 2)) + (9 * t1);
        return f2 - f1;
    }

    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println("AUTOMOBILE KINEMATICS & MOTION ANALYSIS (JAVA)");
        System.out.println("======================================================================");

        System.out.println("\n1. DISPLACEMENT FUNCTION:");
        System.out.println("   s(t) = t^3 - 6t^2 + 9t + 5");

        System.out.println("\n2. VELOCITY FUNCTION v(t) = ds/dt:");
        System.out.println("   v(t) = 3t^2 - 12t + 9");

        System.out.println("\n3. ACCELERATION FUNCTION a(t) = dv/dt = d^2s/dt^2:");
        System.out.println("   a(t) = 6t - 12");

        List<Double> restTimes = findRestTimes();
        System.out.println("\n4. MOMENTARY REST TIMESTAMPS (v(t) = 0):");
        for (Double t : restTimes) {
            System.out.printf("   t = %.0f seconds -> s(%.0f) = %.2f m, a(%.0f) = %.2f m/s^2%n",
                    t, t, displacement(t), t, acceleration(t));
        }

        double t1 = 0.0;
        double t2 = 5.0;
        double deltaS = integrateVelocity(t1, t2);
        double directDeltaS = displacement(t2) - displacement(t1);

        System.out.printf("%n5. DEFINITE INTEGRAL DISPLACEMENT OVER [%.0f, %.0f] SECONDS:%n", t1, t2);
        System.out.printf("   ∫[%.0f to %.0f] v(t) dt   = %.2f meters%n", t1, t2, deltaS);
        System.out.printf("   Direct s(%.0f) - s(%.0f) = %.2f - %.2f = %.2f meters%n",
                t2, t1, displacement(t2), displacement(t1), directDeltaS);

        System.out.println("======================================================================");
    }
}
