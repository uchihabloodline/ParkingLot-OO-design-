package oopsDesign;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class ParkingLot {
	private static ParkingLot single_instance=null; 
	 public static ParkingLot ParkingLot() 
	    { 
	        // To ensure only one instance is created 
	        if (single_instance == null) 
	        { 
	            single_instance = new ParkingLot(); 
	        } 
	        return single_instance; 
	   }
	 
	 //will and contain the alloted ticket for the car parking
	private Object allotedtickets; 
	public  void plotFull(Car c) {
		System.out.println(c.type +" plot is full");
	}
	
	//MAIN function to run the project.
	public static void main(String[] args) {
		single_instance = new ParkingLot();
		System.out.println(single_instance);
		ParkingSpot spot;
		ParkingAttendant pa;
		ArrayList<Ticket> allotedtickets = new ArrayList<>();
		
		Scanner in = new Scanner(System.in);
		//assuming the size of parking lot is divisible by 3
		System.out.println("Enter size of the parking spot");
		
		int size = in.nextInt();
		spot = new ParkingSpot(size);
		pa = new ParkingAttendant(spot);
		
		while(true) {
			System.out.println("Enter choice \n 1:Allocate a car \n 2:View available spots\n 3:DeAllocate a car \n 4:Exit \n");
			int choice = in.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Enter License Number");
				String license = in.next();
				System.out.println("Choose the type of car \n 1: HatchBack\n 2: Sedan \n 3: MiniTruck");
				int carChoice = in.nextInt();
				Car car = null;;
				Ticket ticket;
				switch(carChoice) {
					case 1:
						car = new HatchBack(license);
						break;
					case 2:
						car = new Sedan(license);
						break;
					case 3:
						car = new MiniTruck(license);
						break;
					default:
						System.out.println("Wrong choice");
						break;
				}
				ticket = pa.assignTicket(car);
				if(ticket == null) single_instance.plotFull(car);
				if(ticket!=null) allotedtickets.add(ticket);
				break;
			case 2:
				pa.viewfreeSpots();
				break;
			case 3:
				for(int i=0;i<allotedtickets.size();i++) {
					System.out.println((i+1)+" "+allotedtickets.get(i).ticketNumber+" "+allotedtickets.get(i).car.type);
				}
				System.out.println("Select the required ticket to deAllocate");
				int index = in.nextInt();
				Ticket t = allotedtickets.remove(index-1);
				double cost =  pa.deAssignTicket(t);
				System.out.println((int)cost);
				break;
			default:
				break;
			}

			System.out.println("---------------------------------------------------------------------------------");
		}
	}
}
