import re

ans = open(r"C:\Users\admin\PycharmProjects\Parser\out", "w", encoding="utf-8")
file = open(r"C:\Users\admin\PycharmProjects\Parser\txt", "r", encoding="utf-8")
string = file.read()
string = re.sub("(^|\s)((?:[0-1][0-9]|2[0-3]):[0-5][0-9](?::[0-5][0-9]|))([.?!,;\s]|$)", r"\1(TBD)\3", string)
ans.write(string)