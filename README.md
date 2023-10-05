# SEPM-A2
Software Engineering Project Management Assessment 2

---
# Summary
Developed using openjdk version "17.0.8" 2023-07-18

## Files
As the project is pure Java and there is no scope to write SQL queries, data persistence will be handled through standalone files which will act in place of database tables.
Each file will be a list of records reflecting the structure of it's associated class.

---
# User Guide
## Running the program
### Compile and run on Linux using the build script
1. In a terminal cd into the project root directory.
2. In the terminal run the build.sh script in the project root directory.
3. Run the command "java App" to start the program.

### Compile and run on Linux manually
1. In a terminal cd into the project root directory.
2. Run the command "javac *.java src/controllers/*.java src/handlers/*.java src/models/*.java src/views/*.java"
3. Run the command "java App" to start the program.

### Compile and run on Windows using Eclipse

## Using the program

 
---
# Deliverables:
- Console based Java application
- Ticketing System
---
# Limitations
- Compatible with RMIT MyDesktop service
- JAVA only
- No SQL
- Data persistence, using .csv files for persistence, risky in the case of multiple write attempts to an already open file.
---
