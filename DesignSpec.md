# Checklist
1.
[X] Create Account - Email, Full Name, Phone Number, Password
[X] Password regex
[X] Email uniqueness check

2.
[X] Sumbit a Ticket - description, severity, requires Login
[X] Set the ticket status to open
[X] Forgot Password

3.
[X] Assign ticket by severity

4.
[X] Assign ticket by ticket count
[ ] Assign ticket at random

5.
[X] Add predefine technicians to Accounts+Login+Technician.csv

6.
[ ] Allow technicians to set the severity of a ticket, requires SelectTicket
[ ] Escalate ticket, requires SelectTicket

7.
[ ] Change ticket status, requires SelectTicket

8.
[ ] Move ticket from OpenTickets to ArchivedTickets after 24 hours

9.
[X] Log in
[ ] Staff - See my open tickets
[ ] Tech - See all open tickets
[ ] Tech - See all archived tickets
[ ] Tech - See all closed tickets
[ ] Tech - See all tickets

---
# Key Classes and Functions
- MenuUI
  Handles all frontend UI interactions with a user
	- ShowRelevantText
- Menu
  Handles the menu logic and backend
- InputHandler
	Parses and interprets user inputs, directs the Menu class backend
  - GetInput
- ValidationHandler
  Validates the user input and throws exceptions where needed
  - RegexMatch
- File Handler
  Handles the retreival and modification of records from the CSV files
  - ReadFile
	- SearchId
	- SearchEmail
	- AddLine
	- RemoveLine
- Account
  - CreateAccount - requires unique email address, full name, phone number, password [a-zA-Z\d]{20,}
  - Login
  - SubmitTicket - Requires log in
	- ForgotPassword > Submits forgot password ticket
  - DisableAccount
	- CheckOpenTickets
	- CheckAssignedTickets
	- CheckClosedTickets
- Ticket
  - UpdateTicket
	- SetPassword
	- SetSeverity
	- ChangeSeverity > SetSeverity
	- AssignTicket
	- MarkResolved
	- ReopenTicket
	- Archive (background task)

---
# Models
- Account
	- Id
	- Email (unique)
	- Full Name
	- Phone Number [\d]{10}
	- CreationDate
	- Disabled
- Technician
	- Id
	- AccountId (FK Account)
	- Level [1,2,3]
- Login
	- Id
	- AccountId (FK Account)
	- Password [a-zA-Z\d]{20,}
- Ticket
	- Id
	- TechnicianAssignedId (FK Technician)
	- RequesterId (FK Account)
	- Description
	- Severity
	- CreationDate
	- ResolvedDate
- TicketUpdate
	- Id
	- TicketId (FK Ticket)
	- Description
	- UpdateTime

