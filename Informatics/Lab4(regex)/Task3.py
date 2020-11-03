import re

ans = open(r"C:\Users\admin\PycharmProjects\Parser\out", "w", encoding="utf-8")
file = open(r"C:\Users\admin\PycharmProjects\Parser\txt2", "r", encoding="utf-8")
string = file.read()
strings = re.split("\s", string)
container = []
substitute = []
for i in range(len(strings)):
    ans.write(strings[i] + "\n")
    st = strings[i]
    if len(st) % 2 != 0:
        length = int((len(st) - 1) / 2)
        template = "([0-9]{" + str(length) + "})0([0-9]{" + str(length) + "})"
        temp = re.findall(template, st)
        if len(temp) > 0:
            if len(temp[0]) == 2:
                container.append(temp[0][0] + temp[0][1] + "0")
                substitute.append(temp[0][0] + "0" + temp[0][1])
for i in range(len(container)):
    string = re.sub(substitute[i], container[i], string)
ans.write(string)
