import os
import re

ROOT_DIR = "/Users/gollasupriyashri/.gemini/antigravity/scratch/rightsflow/backend/src/main/java/com/rightsflow"

def refactor_file(filepath):
    with open(filepath, "r", encoding="utf-8") as f:
        content = f.read()

    has_required_args = "@RequiredArgsConstructor" in content
    has_slf4j = "@Slf4j" in content

    if not has_required_args and not has_slf4j:
        return

    # Extract class name
    class_match = re.search(r"public\s+class\s+([A-Za-z0-9_]+)", content)
    if not class_match:
        return
    class_name = class_match.group(1)

    # Remove imports
    content = re.sub(r"import\s+lombok\.RequiredArgsConstructor;\s*\n", "", content)
    content = re.sub(r"import\s+lombok\.extern\.slf4j\.Slf4j;\s*\n", "", content)
    content = re.sub(r"import\s+lombok\.\*;\s*\n", "", content)

    # Remove annotations
    content = re.sub(r"@RequiredArgsConstructor\s*", "", content)
    content = re.sub(r"@Slf4j\s*", "", content)

    # Find all `private final <Type> <name>;` before the first method or constructor
    # Find class body start
    body_idx = content.find("public class " + class_name)
    open_brace_idx = content.find("{", body_idx)
    if open_brace_idx == -1:
        return

    # Find private final fields
    field_pattern = re.compile(r"^\s*private\s+final\s+([A-Za-z0-9_<>, ?]+)\s+([A-Za-z0-9_]+);", re.MULTILINE)
    fields = field_pattern.findall(content[open_brace_idx:])

    # Prepare logger declaration
    logger_code = ""
    if has_slf4j:
        logger_code = f"    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger({class_name}.class);\n\n"

    # Prepare constructor
    constructor_code = ""
    if fields:
        param_str = ", ".join([f"{ftype.strip()} {fname.strip()}" for ftype, fname in fields])
        assignments = "\n".join([f"        this.{fname.strip()} = {fname.strip()};" for _, fname in fields])
        constructor_code = f"    public {class_name}({param_str}) {{\n{assignments}\n    }}\n\n"
    elif has_required_args:
        constructor_code = f"    public {class_name}() {{}}\n\n"

    # Insert logger and constructor right after class open brace
    insertion = "\n"
    if logger_code:
        insertion += logger_code
    if constructor_code:
        insertion += constructor_code

    new_content = content[:open_brace_idx + 1] + insertion + content[open_brace_idx + 1:]

    with open(filepath, "w", encoding="utf-8") as f:
        f.write(new_content)
    print(f"Refactored: {os.path.basename(filepath)}")

# Run across directories
for sub in ["controller", "service", "ai", "security", "config"]:
    folder = os.path.join(ROOT_DIR, sub)
    if os.path.exists(folder):
        for fname in os.listdir(folder):
            if fname.endswith(".java"):
                refactor_file(os.path.join(folder, fname))
