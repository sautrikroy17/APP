import sympy as sp

def main():
    print("=" * 70)
    print("MANUFACTURING PRODUCTION COST ALGEBRAIC MODELING (SYMPY)")
    print("=" * 70)

    q = sp.symbols('q', positive=True, real=True)
    c = sp.symbols('c', real=True)

    print("\n1. DEFINING SYMBOLIC VARIABLES:")
    print(f"   Production Quantity Variable : {q} (q >= 0, units)")
    print(f"   Unit Fixed Cost Parameter    : {c}")

    cost_factored = (2 * q + 15) * (q + 40)
    print("\n2. EXPANDING ALGEBRAIC EXPRESSIONS:")
    print(f"   Factored Cost Expression : {cost_factored}")
    cost_expanded = sp.expand(cost_factored)
    print(f"   Expanded Cost Polynomial : {cost_expanded}")

    unfactored_cost = 3 * q**2 + 18 * q + 24
    print("\n3. FACTORIZING ALGEBRAIC EXPRESSIONS:")
    print(f"   Raw Cost Expression       : {unfactored_cost}")
    factored_result = sp.factor(unfactored_cost)
    print(f"   Factored Expression       : {factored_result}")

    rational_cost_expr = (2 * q**3 + 10 * q**2 + 12 * q) / (2 * q**2 + 4 * q)
    print("\n4. SIMPLIFYING RATIONAL PRODUCTION COST EXPRESSIONS:")
    print(f"   Complex Rational Function : {rational_cost_expr}")
    simplified_cost = sp.simplify(rational_cost_expr)
    print(f"   Simplified Unit Cost Func : {simplified_cost}")

    quad_eq = sp.Eq(2 * q**2 + 30 * q - 1400, 0)
    print("\n5. SOLVING QUADRATIC EQUATION FOR UNKNOWN PRODUCTION QUANTITY:")
    print(f"   Cost Balance Equation     : {quad_eq}")
    roots = sp.solve(quad_eq, q)
    print(f"   All Mathematical Roots    : {roots}")

    valid_quantities = [r for r in roots if r > 0]
    print(f"   Feasible Production Qty   : {valid_quantities[0]} units")
    print("=" * 70)

if __name__ == "__main__":
    main()
