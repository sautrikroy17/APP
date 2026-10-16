from functools import reduce

def calculate_bill_pure(readings, rate_per_unit):
    return sum(r * rate_per_unit for r in readings)

daily_consumption = [8.5, 12.0, 15.5, 7.0, 18.2, 9.8, 14.1, 6.4, 11.0, 20.3]

print("Daily Consumption Readings (Units):")
for day, units in enumerate(daily_consumption, 1):
    print(f"Day {day}: {units:.1f} units")

high_consumption_days = list(filter(lambda u: u > 10.0, daily_consumption))
print(f"\nReadings exceeding 10 units (filter): {high_consumption_days}")

rate = 6.0
daily_costs = list(map(lambda u: u * rate, daily_consumption))
print("\nDaily Electricity Cost at ₹6 per unit (map):")
for day, cost in enumerate(daily_costs, 1):
    print(f"Day {day}: ₹{cost:.2f}")

total_units = reduce(lambda acc, u: acc + u, daily_consumption, 0.0)
print(f"\nTotal Units Consumed (reduce): {total_units:.2f} units")

total_bill = calculate_bill_pure(daily_consumption, rate)
print(f"Total Electricity Bill via Pure Function: ₹{total_bill:.2f}")
