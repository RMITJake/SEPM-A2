# Checklist
Legend: X = done, W = Work in progress
1.
[X] Create Account - Email, Full Name, Phone Number, Password
[X] Password regex - for testing only checks for min length 4, for submission change to min length 20
[X] Email uniqueness check

2.
[X] Sumbit a Ticket - description, severity, requires Login
[X] Set the ticket status to open
[X] Forgot Password

3.
[X] Assign ticket by severity

4.
[X] Assign ticket by ticket count
[X] Assign ticket at random

5.
[X] Add predefine technicians to Accounts+Login+Technician.csv

6.
[X] Allow technicians to set the severity of a ticket, requires SelectTicket
[X] Escalate ticket, requires SelectTicket

7.
[X] Change ticket status, requires SelectTicket
-> additional move tickets to the ArchiveTicket record

8.
[X] Move ticket from closed to archived after 24 hours

9.
[X] Log in
[X] Staff - See my open tickets
[X] Staff - See my archived tickets
[X] Tech - See my assigned tickets
[X] Tech - See all archived tickets
[X] Tech - See all closed tickets
[ ] Tech - See all tickets

10.
[X] Show a report for a specified period
[X] Show how many tickets were submitted in the report
[ ] Show how many tickets are:
	- [ ] Open
	- [ ] Closed and Resolved
	- [ ] Closed and Unresolved
	- [ ] Archived
[ ] Show who submitted each ticket
[ ] Show the creation time of each ticket
[ ] Show the severity
[ ] Show who closed the ticket and how long it took to close

---
# Key Classes and Functions
- MenuUI
  Handles all frontend UI interactions with a user
	- ShowRelevantText
- MenuController
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
- Escalation (maybe merge model with TicketUpdate and just use and Escalation record to store data)
	- Id
	- TicketId
	- RequesterId
	- Reason
	- UpdateTime
- Technician
	- Id
	- AccountId (FK Account)
	- Level [1,2,3]
- Login
	- Id
	- AccountId (FK Account)
	- Password [a-zA-Z\d]{20,}
- Technician
	- Id
	- AccountId
	- Level
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

