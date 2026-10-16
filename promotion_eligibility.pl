employee(ravi, 6, completed, 5).
employee(priya, 3, completed, 4).
employee(kumar, 8, not_completed, 5).
employee(meena, 7, completed, 4).

eligible_for_promotion(Emp) :-
    employee(Emp, Exp, completed, Rating),
    Exp >= 5,
    Rating >= 4.
