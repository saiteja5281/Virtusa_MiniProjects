import matplotlib.pyplot as plt
from utils import load_data

def monthly_summary():
    data = load_data()
    summary = {}
    for exp in data:
        month = exp["date"][:7]   
        if month not in summary:
            summary[month] = 0
        summary[month] += exp["amount"]
    print("\n--- Monthly Summary ---")
    for m in summary:
        print(m, "-> /-", summary[m])

def category_analysis():
    data = load_data()
    cat = {}
    for exp in data:
        c = exp["category"]
        if c not in cat:
            cat[c] = 0
        cat[c] += exp["amount"]
    print("\n--- Category Breakdown ---")
    for c in cat:
        print(c, "-> /-", cat[c])
    max_cat = None
    max_val = 0

    for c in cat:
        if cat[c] > max_val:
            max_val = cat[c]
            max_cat = c
    print("\nHighest spending category:", max_cat, max_val,"/-")
    monthly_summary()
    show_pie()

def show_pie():
    data = load_data()
    cat = {}
    for exp in data:
        c = exp["category"]
        cat[c] = cat.get(c, 0) + exp["amount"]

    labels = list(cat.keys())
    values = list(cat.values())

    plt.pie(values, labels=labels, autopct='%1.1f%%')
    plt.title("Expense Distribution")
    plt.show()