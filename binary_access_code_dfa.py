from automata.fa.dfa import DFA
from automata.base.exceptions import RejectionException

def main():
    print("=" * 75)
    print("DIGITAL SECURITY SYSTEM: BINARY ACCESS CODE VALIDATION (DFA)")
    print("Language: All binary strings ending with '01'")
    print("=" * 75)

    dfa = DFA(
        states={'q0', 'q1', 'q2'},
        input_symbols={'0', '1'},
        transitions={
            'q0': {'0': 'q1', '1': 'q0'},
            'q1': {'0': 'q1', '1': 'q2'},
            'q2': {'0': 'q1', '1': 'q0'},
        },
        initial_state='q0',
        final_states={'q2'},
    )

    print("\n1. DFA FORMAL DEFINITION (5-TUPLE):")
    print("   Q (States)        : {q0, q1, q2}")
    print("   Σ (Alphabet)      : {0, 1}")
    print("   q0 (Start State)  : q0")
    print("   F (Accept States) : {q2}")
    print("   δ (Transitions)   :")
    print("     δ(q0, '0') -> q1 | δ(q0, '1') -> q0")
    print("     δ(q1, '0') -> q1 | δ(q1, '1') -> q2 [Accept]")
    print("     δ(q2, '0') -> q1 | δ(q2, '1') -> q0")

    print("\n2. DFA STATE TRANSITION TABLE:")
    print("   +---------------+-----------+-----------+-----------------------------------+")
    print("   | Current State | Input '0' | Input '1' | Description                       |")
    print("   +---------------+-----------+-----------+-----------------------------------+")
    print("   | -> q0         | q1        | q0        | Initial state / Suffix not '01'   |")
    print("   |    q1         | q1        | q2        | Suffix ends in '0'                |")
    print("   |  * q2         | q1        | q0        | Accept state / Suffix ends in '01'|")
    print("   +---------------+-----------+-----------+-----------------------------------+")

    test_strings = [
        "01",
        "101",
        "001",
        "1101",
        "1001",
        "00001",
        "1110101",
        "0",
        "1",
        "10",
        "100",
        "010",
        "111",
        "000",
        ""
    ]

    print("\n3. TESTING ACCESS CODE VALIDATION:")
    print(f"   {'Access Code':<15} | {'Decision':<12} | {'State Traversal Path'}")
    print("   " + "-" * 60)

    for code in test_strings:
        path = []
        is_valid = False
        try:
            for st in dfa.read_input_stepwise(code):
                path.append(st)
            is_valid = True
        except RejectionException:
            is_valid = False

        decision = "ACCEPTED" if is_valid else "REJECTED"
        path_str = " -> ".join(path) if path else "q0"
        display_code = repr(code) if code == "" else code
        print(f"   {display_code:<15} | {decision:<12} | {path_str}")

    print("=" * 75)

if __name__ == "__main__":
    main()
