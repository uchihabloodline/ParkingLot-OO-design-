package oopsDesign;

import java.time.LocalDateTime;

/* Class for Ticket
 * Contains randomly generated ticket number
 * and the cost for each car type 
 * */

public class Ticket{
	String ticketNumber;
	Car car;
	LocalDateTime issue;
	double cost;
	Ticket(Car car){
		ticketNumber = String.valueOf((int)(Math.random()*1000));
		this.car = car;
		issue = LocalDateTime.now() ;
		
		switch(car.type) {
		case HatchBack:
			this.cost = 30;
			break;
		case Sedan:
			this.cost = 50;
			break;
		case MiniTruck:
			this.cost = 70;
			break;
		}

	}
}