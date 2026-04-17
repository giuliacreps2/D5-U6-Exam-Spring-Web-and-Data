```
Employee

id
username
name
surname
email
-------------

Travel

destination
bookingDate
travelStatus

@ManyToOne
@JoinColumn
private Booking Booking
-------------

Booking

requestDate
notes

@ManyToOne
@JoinColumn
private Employee employee

/////////
SERVICE EMPLOYEE
CRUD


////////
SERVICE TRAVEL
findById
saveTravel
putStatusTravel
deleteTravel


////////
SERVICE BOOKING
saveBooking
--->controllo che un dipendente abbia un solo viaggio
--->gestione errori con status code
