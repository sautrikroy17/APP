result(arun, pass, pass).
result(bala, pass, fail).
result(charan, pass, pass).
result(divya, fail, pass).

eligible_for_advanced(Student) :-
    result(Student, pass, pass).
