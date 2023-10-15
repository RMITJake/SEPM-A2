# 👨‍💻👩‍💻 IT Service Desk Ticketing System
- Software Engineering Project Management Assessment 2
- An IT Service Desk ticketing system for a small startup, Cinco. 
<br/>It allows staff members to submit IT issues and technicians to manage and resolve the issues efficiently.
- Developed using openjdk version "17.0.8" 2023-07-18.

## 📁 Files
- As the project is pure Java and there is no scope to write SQL queries, data persistence will be handled through csv files in the "records" folder which will act in place of database tables. Each file will be a list of records reflecting the structure of it's associated class.


---
# 📄 User Guide
## ✅ Prerequisites 
- OpenJDK 17.0.8 or a compatible version should be installed.
- Have an access to [RMIT's myDesktop](https://mydesktop.rmit.edu.au/).

## 🔢 Example Accounts (for Testing Purposes)

> **Staff**

| User ID  | Password  |
|---|---|
| `4005`  | `SuperSecure55SuperSecure55`  |


<br/>

> **Technician**

| User ID  | Password  |
|---|---|
| `4000`  | `Password123Password123`  |


## ▶ Running the program
Follow these steps to execute the IT Service Desk Ticketing System.

### Step 1: 📥 Download the ZIP file
1. Open ***Google Chrome*** and go to [SEPM-2 Github](https://github.com/RMITJake/SEPM-A2).
2. Sign in using your Github credentials.
3. Click `<> Code` and `Download ZIP` to download `SEPM-2-main.zip`.
4. Unzip the downloaded file in the ***Downloads*** folder. 

### Step 2: ▶ Run the Java application
1. Open ***Eclipse*** >> ***File*** >> ***Open Projects from File System...***
2. Click ***Directory*** >> ***Downloads/SEPM-A2-main*** >> ***Finish***
3. Click the `SEPM-A2-main` in ***Project Explorer*** and then click the *green play button* to run the app.
<br/>![Screenshot1](https://github.com/RMITJake/SEPM-A2/blob/1a7c87126fa5b65ae0fc21d96013e39ac71f0d00/Screen%20Shot%2056.png) 

4. The Java application is successfully running!
<br/>![Screenshot2](https://github.com/RMITJake/SEPM-A2/blob/579735a7708b685596d7f76bca497ac8bb4c82f4/Screen%20Shot%2057.png) 

### Step 3: 🖥 Execute the IT Service Desk Ticketing System
1. Menu Options: 
    1. Upon starting, the program presents the following options:
        - `L` - Log in to an existing account.
        - `C` - Create a new user account.
        - `F` - Forgot password feature.
        - `Q` - Quit the system.

2. Creating an Account:
    1. Enter `C` to create a new user account.
    2. Enter your email address.
    3. Enter your full name.
    4. Enter your valid phone number (*the phone number length should be **10***).
    5. Enter a new password (including **lowercase**/**uppercase**/**number**/**min 20 characters**).
    6. Confirm the entered password.
    7. Check the Account Summary and confirm it is correct `Y`/`N`.
    8. New account successfully created! <br/>Don't forget your Account ID and password.

3. ***`[AS A STAFF]`*** Login:
    1. Enter `L` to log in.
    2. Enter your account ID (e.g. `4005`).
    3. Enter your password (e.g. `SuperSecure55SuperSecure55`).
    4. Logged in succesfully!
    5. The Main Menu presents the following options:
        - `N` - Create a New ticket.
        - `O` - View my Open Tickets.
        - `L` - Logout the account.
        - `Q` - Quit the system.
    6. Enter `N` to create a new ticket.
        1. Provide a description of your IT issue.
        2. Provide a severity of the issue (`low`/`medium`/`high`).
        3. Check the Ticket Summary and confirm it is correct `Y`/`N`.
        4. The ticket for your issue has been opened! 
    7. Enter `O` to view my tickets that have been opened.
        1. Check the Ticket Information and its status.
    8. Enter `Q` to quit the system. Thank you for using!

4. 🔐 ***`[AS A TECHNICIAN]`*** Login:
    1. Enter `L` to log in.
    2. Enter your account ID (e.g. `4000`).
    3. Enter your password (e.g. `Password123Password123`).
    4. Logged in succesfully!
    5. The Main Menu and Tech Menu present the following options:
        - **Main Menu**
            - `N` - Create a New ticket.
            - `O` - View my Open Tickets.
        - **Tech Menu**
            - `A` - View Assigned Tickets.
            - `P` - Pick a ticket to take actions.
            - `G` - My Closed Tickets
            - `H` - Other Technician's Closed Tickets
            - `U` - My Archived Tickets
            - `V` - Other Technician's Archived Tickets
            - `L` - Logout the account.
            - `Q` - Quit the system.
    6. Enter `N` to create a new ticket.
        1. Provide a description of your IT issue.
        2. Provide a severity of the issue (`low`/`medium`/`high`).
        3. Check the Ticket Summary and confirm it is correct `Y`/`N`.
        4. The ticket for your issue has been opened! 
    7. Enter `O` to view my tickets that have been opened.
        1. Check the Ticket Information and its status.
    8. Enter `A` to view all of the tickets assigned to you.
    9. Enter `P` to pick a ticket to take actions.
        1. Enter the Ticket ID.
        2. View the detailed ticket information and the following options.
            - `S` - Set the ticket severity.
            - `E` - Escalate the ticket.
            - `T` - Change the ticket status.
            - `B` - Back to the Main Menu.
        3. Enter `S` to change the ticket severity.
            1. Enter a new severity (`low`/`medium`/`high`).
            2. Check the updated Ticket Information.
        4. 🆙 Enter `E` to escalate the ticket.
            1. Provide a reason to escalate this ticket.
            2. Confirm the reason for escalation by entering `Y`.
            3. Check the updated Ticket Information.
        5. Enter `T` to change the ticket status. The following status options are shown:
            - `O` - `Open`
            - `X` - `Close`
            - `B` - Back to the Main Menu.<br/>
            1. `Open` status
                1. Enter `O` to open the ticket.
                2. The ticket has been opened. Check the updated Ticket Information.
            2. `Close` status - `Resolved` ✅
                1. Enter `X` to close the ticket.
                2. Enter ***Y***, meaning that the issue was resolved.
                3. Check the updated Ticket Information.
            3. `Close` status - `Unresolved` ❎
                1. Enter `X` to close the ticket.
                2. Enter ***N***, meaning that the issue was unresolved.
                3. Check the updated Ticket Information.
        6. Enter `B` to go back to the Main Menu.
    10. Enter `G` to view all of the closed tickets assigned to you.
    11. Enter `H` to view all of the closed tickets assigned to other technicians.
    12. Enter `U` to view all of the archived tickets assigned to you.
    13. Enter `V` to view all of the archived tickets assigned to other technicians.
    14. Enter `L` to log out of the current user and return to the login menu.
    15. Enter `Q` to quit the system. 


4. Forgot Password:
    1. Enter `F` to access the ***Forgot Password*** feature.
    2. Enter your email address to reset your password.
    3. A ticket to reset your password has been opened.<br/>It is marked as ❗***High Priority*** and will be resolved as soon as possible.


5. Exit:
    1. Enter `Q` to quit the system.


---
