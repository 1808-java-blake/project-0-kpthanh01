package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class HomeScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private User currentUser;
	
	public HomeScreen(User currentUser) {
		this.currentUser = currentUser;
	}

	public Screen start() {
		System.out.println("Please choose the following options");
		System.out.println("Enter 1 to View Balance");
		System.out.println("Enter 2 to Deposit Energy");
		System.out.println("Enter 3 to Withdraw Energy");
		System.out.println("Enter 4 to View Transaction History");
		String input = scan.nextLine();
		
		switch (input) {
		case "1":
			System.out.println("Your Potential Energy Balance is: " + currentUser.getBalance() + "\n");
			break;
		case "2":
			return new DepositScreen(currentUser);
		case "3":
			return new WithdrawScreen(currentUser);
		case "4":
			System.out.println("Your transaction history: \n" + currentUser.getTransactionHistory());
			break;
		default:
			break;
		}
		return this;
	}

}
