import sys

print("!!!Сначала берете и в файле в 3 столбце сильно отличающиеся от соседних значения заменяете "
      "на среднее арифметическое соседей (типа если 3 228 5, то 228->4, итог: 3 4 5)")
print("Да, можно было это автоматизировать, но меня ебет это делать, сорри\n")
print("Ввести сюда имя файла с данными (предварительно киньте его в директорию проекта): ")
file = sys.stdin.readline().split("\n")
f1 = open(file[0], "r")
lin = f1.readlines()
single = 0
double = 0
interrupt = 0
flag = int(lin[1].split(",")[1]) - int(lin[1].split(",")[2])
for line in lin:
    try:
        line = line.split(",")
        flag2 = int(line[1]) - int(line[2])
        if flag == flag2 & (flag2 > -1 & flag < 1 | flag > -1 & flag2 < 1):
            if line[0].lower() == "single":
                single += 1
            elif line[0].lower() == "double":
                double += 1
            else:
                interrupt += 1
        flag = flag2
    except Exception:
        print("")
print("SINGLE: " + str(single))
print("INTERRUPT: " + str(interrupt))
print("DOUBLE: " + str(double))
