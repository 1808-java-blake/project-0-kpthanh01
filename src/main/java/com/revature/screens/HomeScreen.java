package com.revature.screens;

import java.util.List;
import java.util.Scanner;

import com.revature.beans.TransactionHistory;
import com.revature.beans.User;
import com.revature.daos.TransactionDao;
import com.revature.util.AppState;

public class HomeScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private TransactionDao td = TransactionDao.currentTransactionDao;
	private AppState state = AppState.state;
	private User u = state.getCurrentUser();
	
	public Screen start() {
		System.out.println("\nPlease choose the following options");
		System.out.println("Enter 1 to View Balance");
		System.out.println("Enter 2 to Deposit Energy");
		System.out.println("Enter 3 to Withdraw Energy");
		System.out.println("Enter 4 to View Transaction History");
		System.out.println("Enter 5 to Logout");
		String input = scan.nextLine();
		
		switch (input) {
		case "1":
			System.out.println("====================================" 
					+ "\nYour Balance is: " + u.getBalance() + " Potential Energies"
					+ "\n====================================");
			break;
		case "2":
			return new DepositScreen();
		case "3":
			return new WithdrawScreen();
		case "4":
			List<TransactionHistory> transactions = td.findByUserId(state.getCurrentUser().getId());
			transactions.stream().forEach((each) -> {
				System.out.println("Date: " + each.getDate() + ", Action: "+ each.getAction());
			});
			break;
		case "5":
			return new LoginScreen();
		default:
			break;
		}
		return this;
	}

}
