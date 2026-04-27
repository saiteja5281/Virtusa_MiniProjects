from utils import load_data

def view_all():
    data = load_data()
    if len(data) == 0:
        print("No expenses found")
        return

    print("\n--- All Expenses ---")
    for i, exp in enumerate(data):
        print(f"{i+1}. {exp['date']} | {exp['category']} | ₹{exp['amount']} | {exp['description']}")