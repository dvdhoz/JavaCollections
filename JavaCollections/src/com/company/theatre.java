package com.company;

import java.util.*;

public class theatre {
    private final String theatreName; //field only available in this class, when field is instantiated, the String name does not change
    private Collection<Seat> seats = new LinkedHashSet<>();  // field only available within the theatre class, creates an array list of  type Seats called seats

    public theatre(String theatreName, int numRows, int seatsPerRow) {  // constructor that takes in a name, num of rows, and seats per row.  Whenever the theatre class is instantiated, need to specify these values
        this.theatreName = theatreName;  //theatre Name from the field will reference theatreName

        int lastRow = 'A' + (numRows - 1); //sts the last row
        for( char row = 'A'; row <= lastRow; row++) {
            for(int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                Seat seat = new Seat(row + String.format("%02d", seatNum));  //instantiates the seat object, Seat just needs to take in a number
                seats.add(seat);  //adding seat object to array list of seat
            }
        }

    }

    public String getTheatreName() {
        return theatreName;
    }  //getter for theatre name

    public boolean reserveSeat(String seatNumber) {  //method to reserve seat with seat number as parameter
        Seat requestedSeat = null;  //create an empty seat object
        for(Seat seat : seats) {  //looping through the seat array list
            if(seat.getSeatNumber().equals(seatNumber)) {  //if seat number is equal to seat number in the parameter, seat number is valid
                requestedSeat = seat; //set seat number as a requested seat
                break;
            }
        }
        if(requestedSeat == null ) {  //if requested seat is null meaning the looping didnt assign a reference to requested seat, then the parameter seat value was out of range
            System.out.println("There is no seat " + seatNumber);
            return false;
        }
        return requestedSeat.reserve();  // use the reserve method within the Seat Class on the requested seat object.
    }

    public void getSeats() {  // method to return seat numbers
        for(Seat seat : seats) {
            System.out.println(seat.getSeatNumber());
        }
    }

    private class Seat { // Seat class can only be used within theatre class
        private final String seatNumber;  //can only be used within Seat class, the instantiated seat number cannot be changed/re-referenced
        private boolean reserved = false;  // can only be used in the Seat class, starts the reserved field as false

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }  //constructor for the Seat class with seat number as parameter field

        public boolean reserve() {  // public method setting the seat class to true when reserved
            if(!this.reserved) {  //if reserved field is false
                this.reserved = true;  //set the reserved field to true
                System.out.println("Seat " + seatNumber + " reserved");
                return true; // return true, needs to return true because the reserveSeat method in the theatre class requires a boolean return type
            } else {
                return false;  // returns false when this.reserved is true, so the seat cannot be reserved
            }
        }

        public boolean cancel() { // public method that cancels a reserved seat
            if(this.reserved) {  //if reserved is true
                System.out.println("Reservation of seat " + seatNumber + " cancelled");
                return true;  //return true
            } else {
                return false;  //return false
            }
        }

        public String getSeatNumber() {  // getter for seat number
            return seatNumber;
        }
    }
}
