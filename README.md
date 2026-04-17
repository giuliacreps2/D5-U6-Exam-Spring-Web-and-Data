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

