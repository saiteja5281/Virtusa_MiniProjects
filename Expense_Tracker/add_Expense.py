from utils import load_data, save_data

def add_expense():
    data = load_data()
    date = input("Enter date (YYYY-MM-DD): ")
    category = input("Enter category (Food/Travel/Bills/etc): ")
    
    try:
        amount = float(input("Enter amount: "))
    except:
        print("Invalid amount")
        return
    
    desc = input("Enter description: ")
    expense = {
        "date": date,
        "category": category,
        "amount": amount,
        "description": desc
    }

    data.append(expense)
    save_data(data)
    print("Expense added successfully!")