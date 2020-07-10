package oopsDesign;

import java.time.Duration;
import java.time.LocalDateTime;

/* Class for Parking Attendant
 * Contains functions for invoking the
 * allocating / deallocating Parking spots
 * and view available parking spots
 * */
public class ParkingAttendant {
	static ParkingSpot spot;
	static Ticket ticket;
	public ParkingAttendant(ParkingSpot spot) {
		this.spot = spot;
	}
	public static Ticket assignTicket(Car c) {
		if(spot.free(c.type)) {
			ticket = new Ticket(c);
			System.out.println(spot.assignSpots(ticket));
			//System.out.println(spot.hatchBackCount);
			return ticket;
		}
		return null;
	}
	
	public static double deAssignTicket(Ticket ticket) {
		if(spot.deAssignSpots(ticket)) {
			LocalDateTime now = LocalDateTime.now();
			Duration duration = Duration.between(ticket.issue,now);
			
			return ((double)duration.getSeconds()/3600)*ticket.cost;
		}
		return (double)0;
	}
	public static void viewfreeSpots() {
		spot.viewAvailableSpots();
	}


	
}