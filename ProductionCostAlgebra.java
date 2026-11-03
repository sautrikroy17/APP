import java.util.ArrayList;
import java.util.List;

public class ProductionCostAlgebra {

    public static class LinearTerm {
        final double coeff;
        final double constant;

        public LinearTerm(double coeff, double constant) {
            this.coeff = coeff;
            this.constant = constant;
        }

        @Override
        public String toString() {
            return String.format("(%.0fq + %.0f)", coeff, constant);
        }
    }

    public static class QuadraticPolynomial {
        final double a;
        final double b;
        final double c;

        public QuadraticPolynomial(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public static QuadraticPolynomial multiply(LinearTerm t1, LinearTerm t2) {
            double a = t1.coeff * t2.coeff;
            double b = (t1.coeff * t2.constant) + (t1.constant * t2.coeff);
            double c = t1.constant * t2.constant;
            return new QuadraticPolynomial(a, b, c);
        }

        public List<Double> solveRoots() {
            List<Double> roots = new ArrayList<>();
            double discriminant = (b * b) - (4 * a * c);
            if (discriminant >= 0) {
                double sqrtD = Math.sqrt(discriminant);
                double r1 = (-b + sqrtD) / (2 * a);
                double r2 = (-b - sqrtD) / (2 * a);
                roots.add(r1);
                roots.add(r2);
            }
            return roots;
        }

        public String factorize() {
            List<Double> roots = solveRoots();
            if (roots.size() == 2) {
                double r1 = roots.get(0);
                double r2 = roots.get(1);
                String term1 = (-r1 >= 0) ? String.format("+ %.0f", -r1) : String.format("- %.0f", Math.abs(-r1));
                String term2 = (-r2 >= 0) ? String.format("+ %.0f", -r2) : String.format("- %.0f", Math.abs(-r2));
                return String.format("%.0f * (q %s)(q %s)", a, term1, term2);
            }
            return toString();
        }

        @Override
        public String toString() {
            return String.format("%.0fq^2 + %.0fq + %.0f", a, b, c);
        }
    }

    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println("MANUFACTURING PRODUCTION COST ALGEBRAIC MODELING (JAVA)");
        System.out.println("======================================================================");

        System.out.println("\n1. DEFINING SYMBOLIC PRODUCTION VARIABLES:");
        System.out.println("   Variable q : Production Batch Quantity (q >= 0)");
        System.out.println("   Variable c : Overhead Unit Variable Component");

        LinearTerm factor1 = new LinearTerm(2, 15);
        LinearTerm factor2 = new LinearTerm(1, 40);
        System.out.println("\n2. EXPANDING ALGEBRAIC COST EXPRESSIONS:");
        System.out.println("   Factored Expression : " + factor1 + " * " + factor2);
        QuadraticPolynomial expanded = QuadraticPolynomial.multiply(factor1, factor2);
        System.out.println("   Expanded Polynomial : " + expanded);

        QuadraticPolynomial rawCost = new QuadraticPolynomial(3, 18, 24);
        System.out.println("\n3. FACTORIZING ALGEBRAIC COST EXPRESSIONS:");
        System.out.println("   Raw Expression      : " + rawCost);
        System.out.println("   Factored Form       : " + rawCost.factorize());

        System.out.println("\n4. SIMPLIFYING RATIONAL PRODUCTION COST EXPRESSIONS:");
        System.out.println("   Rational Function   : (2q^3 + 10q^2 + 12q) / (2q^2 + 4q)");
        System.out.println("   Numerator Factored  : 2q * (q + 2) * (q + 3)");
        System.out.println("   Denominator Factored: 2q * (q + 2)");
        System.out.println("   Simplified Result   : q + 3");

        QuadraticPolynomial targetEquation = new QuadraticPolynomial(2, 30, -1400);
        System.out.println("\n5. SOLVING QUADRATIC EQUATION FOR OPTIMAL PRODUCTION QUANTITY:");
        System.out.println("   Equation            : " + targetEquation + " = 0");
        List<Double> roots = targetEquation.solveRoots();
        System.out.printf("   Roots Calculated    : q1 = %.2f, q2 = %.2f%n", roots.get(0), roots.get(1));

        double physicalQuantity = roots.stream().filter(r -> r > 0).findFirst().orElse(0.0);
        System.out.printf("   Feasible Output Qty : %.0f units%n", physicalQuantity);
        System.out.println("======================================================================");
    }
}
