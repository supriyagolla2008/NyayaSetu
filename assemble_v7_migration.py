import os

def sq(text):
    if text is None:
        return "NULL"
    return "'" + str(text).replace("'", "''") + "'"

with open("generate_sql_parts.py", "w") as f:
    f.write("# Generator script\n")
print("Initialized assemble script")
