package oopsDesign;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/* Class for Parking Spot
 * Contains functions for allocating and deallocating
 * spots to the cars based on the car type
 * hashmaps used to store Ticket to Parking spot mapping
 * Queues used to store the available Parking spots 
 * */
public class ParkingSpot{
	private int hatchs;
	private int sedans;
	private int minitrucks;
	
    Queue<Integer> freeHatchSpots;
    Queue<Integer> freeSedanSpots;
    Queue<Integer> freeMiniTruckSpots ;

	HashMap<Ticket,Integer> hatchBackCount;
	HashMap<Ticket,Integer> sedanCount;
	HashMap<Ticket,Integer> minitruckCount;
	
	ParkingSpot(int size){
		
		hatchs = size/3;
		sedans = hatchs+size/3;
		minitrucks = sedans+size/3;
		
		freeHatchSpots = new LinkedList<>();
		freeSedanSpots = new LinkedList<>();
		freeMiniTruckSpots = new LinkedList<>();
		
		hatchBackCount = new HashMap<>();
		sedanCount = new HashMap<>();
		minitruckCount = new HashMap<>();
		
		for(int i=1;i<=size;i++) {
			if(i>=1 && i<=hatchs) freeHatchSpots.add(i);
			else if(i>hatchs && i<=sedans) freeSedanSpots.add(i);
			else freeMiniTruckSpots.add(i);
		}
		
	}
	
	public boolean free(CarType type) {
		
		if( type == CarType.HatchBack) {
			return freeHatchSpots.size()>0;
		}
		else if(type == CarType.Sedan) {
			return freeSedanSpots.size()>0;
		}
		else if(type == CarType.MiniTruck) {
			return freeMiniTruckSpots.size()>0;
		}
		else {
			return false;
		}
	}
	
//spot assignment on the basis of car type 
public Boolean assignSpots(Ticket ticket) {
        int pos = 0;
        //spotchecking for hatchback car type.
		if(ticket.car.type == CarType.HatchBack && free(ticket.car.type)) {
		     pos = freeHatchSpots.poll();
		     hatchBackCount.put(ticket,pos);
		}
		//spotchecking for Sedan car type.
		else if(ticket.car.type == CarType.Sedan && free(ticket.car.type)) {
		     pos = freeSedanSpots.poll();
		     sedanCount.put(ticket,pos);
		}
		//spotchecking for MiniTruck car type.
		else if(ticket.car.type == CarType.MiniTruck && free(ticket.car.type)) {
		      pos = freeMiniTruckSpots.poll();
		      minitruckCount.put(ticket,pos);
		}
        if(pos!=-1)
        {
            System.out.println("spot "+pos+" is allocated");
            return true;
        }
		return false;
}

public Boolean deAssignSpots(Ticket ticket) {
	int pos = 0;
	if(ticket.car.type == CarType.HatchBack) {
		if(hatchBackCount.containsKey(ticket)) {
		                        pos = hatchBackCount.get(ticket);
			freeHatchSpots.add(pos);
			hatchBackCount.remove(ticket);
		}
		return true;
	}
	else if(ticket.car.type == CarType.Sedan) {
		if(sedanCount.containsKey(ticket)) {
		    pos = sedanCount.get(ticket);
			freeSedanSpots.add(pos);
			sedanCount.remove(ticket);
		}
		return true;
		}
		else if(ticket.car.type== CarType.MiniTruck) {
			if(minitruckCount.containsKey(ticket)) {
			    pos = minitruckCount.get(ticket);
				freeMiniTruckSpots.add(pos);
				minitruckCount.remove(ticket);
			}
		}
		if(pos != 0){
	        System.out.println("spot "+pos+" is now free");
	        return true;
		 }
    return false;
}
		
	public void viewAvailableSpots() {
		System.out.println("HatchBacks "+freeHatchSpots);
		System.out.println("Sedans "+freeSedanSpots);
		System.out.println("Mini Trucks "+freeMiniTruckSpots);
	}
	

}

