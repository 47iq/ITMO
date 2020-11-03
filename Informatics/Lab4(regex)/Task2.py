import re

ans = open(r"C:\Users\admin\PycharmProjects\Parser\out", "w", encoding="utf-8")
file = open(r"D:\Google Downloads\Hamlet.txt", "r", encoding="utf-8")
string = file.read()
strings = re.findall("[^!.?,]+,[^!.?]+!", string)
for s in strings:
    ans.write(s)
    ans.write("\n")

