package com.mydemo.operations;

import java.util.Scanner;

import com.mydemo.daoimp.RoutesImp;
import com.mydemo.daoimp.TrainImp;
import com.mydemo.daoimp.TrainSeatImp;
import com.mydemo.daoimp.TransactionImp;
import com.mydemo.daoimp.UserImp;
import com.mydemo.model.Routes;
import com.mydemo.model.TrainSeat;
import com.mydemo.model.TrainfareCalculator;
import com.mydemo.model.Transaction;
import com.mydemo.model.User;

public class MainApp {
	static Scanner rs = new Scanner(System.in);

	public static void main(String[] args) {
		int options;
		do {
			System.out.println("\n1 book ticket");
			options = rs.nextInt();
			switch (options) {
			case 1:
				showLoginInfo();
				break;
			case 2:
				System.out.println("logging you out...");
				break;

			}

		} while (options != 2);

	}

	private static void showLoginInfo() {
		System.out.println("Enter UserName");
		String username = rs.next();
		System.out.println("Enter Password");
		String pass = rs.next();
		UserImp imp = new UserImp();
		User user = new User();
		user.setUserName(username);
		user.setUserPass(pass);
		String message = imp.login(user);
		if (message.equals("success")) {
			bookTicketOperations();
		} else {
			System.out.println("sorry you cant book ticket ");
		}
	}

	private static void bookTicketOperations() {
		int tempChoice;
		do {
			System.out.println("1:book ticket\n2:exit");
			tempChoice = rs.nextInt();
			switch (tempChoice) {
			case 1:
				rs.nextLine();
				System.out.println("Enter Source ");
				String source = rs.nextLine();
				System.out.println("enter destination");
				String destination = rs.nextLine();
				Routes route = new Routes();
				route.setSrc(source);
				route.setDesc(destination);
				RoutesImp routesImp = new RoutesImp();
				route = routesImp.getAllTrainDetails(route);
				if (!route.getTrainList().isEmpty()) {
					checkAndBookTicket(route);

				} else {
					System.out.println("we couldnt find any train passing that location");
				}

			}
		} while (tempChoice != 2);

	}

	private static void checkAndBookTicket(Routes route) {
		boolean isSeatsAvailable;
		System.out.println("All train that goes to that locations ");
		route.getTrainList().forEach((train) -> {
			System.out.println(train);
		});
		System.out.println("Enter your train no from the list");
		int trainName = rs.nextInt();
		System.out.println("Enter no of passenger");
		int noOfPassenger = rs.nextInt();
		System.out.println("Enter the person booking the ticket");
		String customerName = rs.next();
		//checking if seats are available ?
		TrainSeatImp trainImp=new TrainSeatImp();
		TrainSeat trainSeat =new TrainSeat();
		trainSeat.setNoOfSeats(noOfPassenger);
		trainSeat.setTrainNo(trainName);
		isSeatsAvailable=trainImp.checkSeats(trainSeat);
		if(isSeatsAvailable) {
			   //train fare calculating
			TrainfareCalculator trainfareCalculator = new TrainfareCalculator();
			trainfareCalculator.setSource(route.getSrc());
			trainfareCalculator.setDestination(route.getDesc());
			trainfareCalculator.setTrainNo(trainName);
			trainfareCalculator.setNoOfPassenger(noOfPassenger);
			float totalCost = new TrainImp().calculateCost(trainfareCalculator);
			//transaction part 
			TransactionImp imp = new TransactionImp();
			Transaction transaction = new Transaction();
			transaction.setCustomerName(customerName);
			transaction.setTrainNo(trainName);
			transaction.setTotalCost(totalCost);
			transaction.setNoOfPassenger(noOfPassenger);
			imp.initTransaction(transaction);
		}
		else {
			System.out.println("tickets not available");
		}
	 

	}
}
