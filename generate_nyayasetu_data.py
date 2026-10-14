# Generator for NyayaSetu V7 Migration
import os

def sq(text):
    if text is None:
        return "NULL"
    return "'" + str(text).replace("'", "''") + "'"

out_path = "backend/src/main/resources/db/migration/V7__seed_full_14_categories_legal_data.sql"

print("Starting generation of NyayaSetu V7 migration...")
