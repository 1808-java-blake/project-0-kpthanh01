package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.util.AppState;

public class HomeScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private AppState state = AppState.state;
	private User u = state.getCurrentUser();
	
	public Screen start() {
		System.out.println(u.getBalance());
		System.out.println("Please choose the following options");
		System.out.println("Enter 1 to View Balance");
		System.out.println("Enter 2 to Deposit Energy");
		System.out.println("Enter 3 to Withdraw Energy");
		System.out.println("Enter 4 to View Transaction History");
		System.out.println("Enter 5 to Logout");
		String input = scan.nextLine();
		
		switch (input) {
		case "1":
//			System.out.println("Your Potential Energy Balance is: " + currentUser.getBalance() + "\n");
			break;
		case "2":
			return new DepositScreen();
		case "3":
			return new WithdrawScreen();
		case "4":
//			System.out.println("Your transaction history: \n" + currentUser.getTransactionHistory());
			break;
		case "5":
			return new LoginScreen();
		default:
			break;
		}
		return this;
	}

}
