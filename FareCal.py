def calcFare(d,t,h):
    rates = {"Economy":10,"Premium":18,"SUV":25}
    if t not in rates:
        return None
    r = rates[t]
    total = d*r
    s = 1
    if h>=17 and h<=20:
        s = 1.5
    total = total*s
    return total,s

print("CityCab")
try:
    d=float(input("Enter distance (in km): "))
    t= input("Enter vehicle type (Economy / Premium / SUV): ").strip()
    h=int(input("Enter hour of travel (0-23): "))
except:
    print("invalid input")
    exit()
res = calcFare(d,t,h)
if res==None:
    print("service not there")
else:
    amt,surge = res
    print("\n-- bill --")
    print("km:",d)
    print("type:",t)
    print("time:",h)
    if surge>1:
        print("surge on")
    else:
        print("no surge")
    print("pay:",amt)