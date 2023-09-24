# Key Classes and Functions
- MenuUI
  Handles all frontend UI interactions with a user
	- ShowRelevantText
- Menu
  Handles the menu logic and backend
- InputHandler
	Parses and interprets user inputs, directs the Menu class backend
  - GetInput
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
	- AccountId (FK Account)
	- Description
	- Severity
	- CreationDate
	- ResolvedDate
- TicketUpdate
	- Id
	- TicketId (FK Ticket)
	- UpdateDescription
	- UpdateDate

