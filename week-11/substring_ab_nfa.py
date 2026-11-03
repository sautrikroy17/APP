from automata.fa.nfa import NFA
from automata.base.exceptions import RejectionException

def main():
    print("=" * 75)
    print("TEXT PROCESSING SYSTEM: SUBSTRING 'ab' RECOGNITION (NFA)")
    print("Language: All strings over {a, b} containing substring 'ab'")
    print("=" * 75)

    nfa = NFA(
        states={'q0', 'q1', 'q2'},
        input_symbols={'a', 'b'},
        transitions={
            'q0': {'a': {'q0', 'q1'}, 'b': {'q0'}},
            'q1': {'b': {'q2'}},
            'q2': {'a': {'q2'}, 'b': {'q2'}},
        },
        initial_state='q0',
        final_states={'q2'},
    )

    print("\n1. NFA FORMAL DEFINITION (5-TUPLE):")
    print("   Q (States)        : {q0, q1, q2}")
    print("   Σ (Alphabet)      : {a, b}")
    print("   q0 (Start State)  : q0")
    print("   F (Accept States) : {q2}")
    print("   δ (Transitions)   :")
    print("     δ(q0, 'a') -> {q0, q1} | δ(q0, 'b') -> {q0}")
    print("     δ(q1, 'a') -> ∅        | δ(q1, 'b') -> {q2} [Accept]")
    print("     δ(q2, 'a') -> {q2}     | δ(q2, 'b') -> {q2}")

    print("\n2. NFA STATE TRANSITION TABLE:")
    print("   +---------------+---------------+---------------+---------------------------------------+")
    print("   | Current State | Input 'a'     | Input 'b'     | Description                           |")
    print("   +---------------+---------------+---------------+---------------------------------------+")
    print("   | -> q0         | {q0, q1}      | {q0}          | Scanning prefix / non-deterministic   |")
    print("   |    q1         | ∅             | {q2}          | Matched 'a', awaiting 'b'             |")
    print("   |  * q2         | {q2}          | {q2}          | Accept state: 'ab' detected           |")
    print("   +---------------+---------------+---------------+---------------------------------------+")

    test_strings = [
        "ab",
        "aab",
        "bab",
        "abba",
        "bbabb",
        "abaab",
        "bbaab",
        "ba",
        "a",
        "b",
        "bb",
        "bba",
        "aaa",
        "bbb",
        ""
    ]

    print("\n3. TESTING SUBSTRING 'ab' RECOGNITION:")
    print(f"   {'Input String':<15} | {'Decision':<12} | {'Active State Sets Sequence'}")
    print("   " + "-" * 65)

    for text in test_strings:
        path = []
        is_valid = False
        try:
            for active_states in nfa.read_input_stepwise(text):
                formatted_states = "{" + ",".join(sorted(list(active_states))) + "}"
                path.append(formatted_states)
            is_valid = True
        except RejectionException:
            is_valid = False

        decision = "ACCEPTED" if is_valid else "REJECTED"
        path_str = " -> ".join(path) if path else "{q0}"
        display_text = repr(text) if text == "" else text
        print(f"   {display_text:<15} | {decision:<12} | {path_str}")

    print("=" * 75)

if __name__ == "__main__":
    main()
