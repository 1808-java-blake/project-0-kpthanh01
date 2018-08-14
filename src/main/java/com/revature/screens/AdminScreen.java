package com.revature.screens;

import java.util.Scanner;

public class AdminScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	
	@Override
	public Screen start() {
		System.out.println("Welcome Administrator!");
		System.out.println("Please choose one of the options you would like to do today.");
		System.out.println("1: Check list of accounts");
		System.out.println("2: Check the Transaction History of a User");
		System.out.println("3: Logout");
		String selectOption = scan.nextLine();
		
		switch(selectOption) {
		case "1":
			return new AdminViewAccountScreen();
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