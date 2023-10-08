# IT Service Desk Ticketing System
- Software Engineering Project Management Assessment 2
- An IT Service Desk ticketing system for a small startup, Cinco. 
<br/>It allows staff members to submit IT issues and technicians to manage and resolve the issues efficiently.
- Developed using openjdk version "17.0.8" 2023-07-18.

## Files
As the project is pure Java and there is no scope to write SQL queries, data persistence will be handled through standalone files which will act in place of database tables. Each file will be a list of records reflecting the structure of it's associated class.


---
# User Guide
## Prerequisites 
- OpenJDK 17.0.8 or a compatible version should be installed.
- Have an access to [RMIT's myDesktop](https://mydesktop.rmit.edu.au/).

## Running the program
Follow these steps to execute the IT Service Desk Ticketing System.

### Step 1: Download the ZIP file
1. Open ***Google Chrome*** and go to [SEPM-2 Github](https://github.com/RMITJake/SEPM-A2#compile-and-run-on-linux-using-the-build-script).
2. Sign in using your Github credentials.
3. Click `<> Code` and `Download ZIP` to download `SEPM-2-main.zip`.
4. Unzip the downloaded file in the ***Downloads*** folder. 

### Step 2: Run the Java codes
1. Open ***Eclipse*** >> ***File*** >> ***Open Projects from File System...***
2. Click ***Directory*** >> ***Downloads/SEPM-A2-main*** >> ***Open***
3. Click the `SEPM-A2-main` in ***Project Explorer*** and `Run App`.
<br/>![Screenshot1](https://github.com/RMITJake/SEPM-A2/blob/1a7c87126fa5b65ae0fc21d96013e39ac71f0d00/Screen%20Shot%2056.png) 

4. Java codes are successfully running!
<br/>![Screenshot2](https://github.com/RMITJake/SEPM-A2/blob/19046f501341baf7cbc340c663afd79487f6d12c/Screen%20Shot%2057.png) 

### Step 3: Execute the IT Service Desk Tikcketing System
1. Menu Options: 
    1. Upon starting, the program presents the following options:
        - `C` - Create a new user account.
        - `F` - Forgot password feature.
        - `L` - Log in to an existing account.
        - `E` - Exit the system.

2. Creating an Account:
    1. Enter `C` to create a new user account.
    2. Enter your email address.
    3. Enter your full name.
    4. Enter your valid phone number.
    5. Enter a new password (including **lowercase**/**uppercase**/**number**).
    6. Confirm the entered password.
    7. Check the Account Summary and confirm it is correct `Y`/`N`.
    8. New account successfully created! Don't forget your account number and password(In this case, `4008` and `Abc123` are the account number and password respectively.)
    <br/>![Screenshot3](https://github.com/RMITJake/SEPM-A2/blob/4b68369c7b58db5d56747a2413268c6b36a7380a/Screen%20Shot%2059.png) 
    9. Press ***Enter*** to move on.

3. Login:
    1. Enter `L` to log in.
    2. Enter your account number.
    3. Enter your password.
    4. Logged in succesfully!
    5. The Main Menu presents the following options:
        - ***C*** - Create a new ticket
        - ***S*** - View the Tickets
        - ***M*** - My Open Tickets
        - ***L*** - Logout the account
        - ***E*** - Exit the system.
    6. \[AS A STAFF\] Enter ***C*** to create a new ticket.
        1. Provide a description of your IT issue.
        2. Provide a severity of the issue (`low`/`medium`/`high`).
        3. The ticket has been opened!
        4. Check the Ticket Summary and confirm it is correct `Y`/`N`.
    7. \[AS A STAFF\] Enter ***M*** to view my tickets that have been opened.
        1. Check the Ticket Summary and confirm its information.
    8. \[AS A TECHNICIAN\] Enter ***S*** to view the ticket submitted.
        1. Enter the Ticket ID.
        2. View the detailed ticket information.
        <br/>![Screenshot4](https://github.com/RMITJake/SEPM-A2/blob/4b68369c7b58db5d56747a2413268c6b36a7380a/Screen%20Shot%2058.png) 
        3. Enter `S` to change the ticket severity.
            1. Enter the appropriate severity.
            2. Confirm the updated Ticket Summary.
        4. Enter `E` to escalate the ticket.
            1. Provide a reason to escalate this ticket.
            2. Confirm the reason and updated Ticket Summary.
        5. Enter `M` to go back to the Main Menu.
    9. Enter ***E*** to Exit the system. 


4. Forgot Password:
    1. Enter `F` to access the ***Forgot Password*** feature.
    2. Enter your email address to reset your password.
    3. A ticket to reset your password has been opened.<br/>It is marked as High Priority and will be resolved as soon as possible.


5. Exit:
    1. Enter `E` to Exit the system.


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
