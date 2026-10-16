def calculate_discounted_price(price):
    if price > 1000.0:
        return price * 0.80
    return price

products = [
    ("Wireless Mouse", 650.0),
    ("Mechanical Keyboard", 2800.0),
    ("USB-C Hub", 1200.0),
    ("Mouse Pad", 350.0),
    ("Noise Cancelling Headset", 4500.0),
    ("Webcam HD", 950.0),
    ("External SSD 1TB", 7200.0)
]

print("Original Product Catalog:")
for name, price in products:
    print(f"{name}: ₹{price:.2f}")

costly_products = list(filter(lambda p: p[1] > 1000.0, products))
print("\nProducts Costing > ₹1,000 (filter):")
for name, price in costly_products:
    print(f"{name}: ₹{price:.2f}")

discounted_catalog = list(map(lambda p: (p[0], calculate_discounted_price(p[1])), products))
print("\nProduct Catalog After 20% Discount (map):")
for name, price in discounted_catalog:
    print(f"{name}: ₹{price:.2f}")

print("\nVerification of Original List Preservation:")
for name, price in products:
    print(f"{name}: ₹{price:.2f}")
