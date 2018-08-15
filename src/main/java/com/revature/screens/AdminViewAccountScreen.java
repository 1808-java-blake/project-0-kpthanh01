package com.revature.screens;

import java.util.Scanner;

public class AdminViewAccountScreen implements Screen {
	private  Scanner scan = new Scanner(System.in);

	@Override
	public Screen start() {
		System.out.println("Welcome Admin!");
		System.out.println("Please choose one of the options below.");
		System.out.println("1: Get a list of user accounts");
		System.out.println("2: Get a list of transaction history of a specific user");
		System.out.println("3: Logout");
		String input = scan.nextLine();
		
		switch (input) {
		case "1":
			break;
		case "2":
			return new AdminViewTransactionScreen();
		case "3":
			return new LoginScreen();
		default:
			break;
		}
		return this;
	}

}
