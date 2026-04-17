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

-------------

Booking

requestDate
notes

@ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

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
