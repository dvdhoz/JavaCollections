package com.company;

import java.util.ArrayList;
import java.util.List;

public class theatre {
    private final String theatreName; //field only available in this class, when field is instantiated, the String name does not change
    private List<Seat> seats = new ArrayList<>();  // field only available within the theatre class, creates an array list of  type Seats called seats

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

    private class Seat {
        private final String seatNumber;
        private boolean reserved = false;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public boolean reserve() {
            if(!this.reserved) {
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " reserved");
                return true;
            } else {
                return false;
            }
        }

        public boolean cancel() {
            if(this.reserved) {
                System.out.println("Reservation of seat " + seatNumber + " cancelled");
                return true;
            } else {
                return false;
            }
        }

        public String getSeatNumber() {
            return seatNumber;
        }
    }
}
