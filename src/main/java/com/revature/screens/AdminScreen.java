package com.revature.screens;

import java.util.List;
import java.util.Scanner;

import com.revature.beans.Admin;
import com.revature.beans.User;
import com.revature.daos.AdminDao;
import com.revature.util.AppState;

public class AdminScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private AdminDao ad = AdminDao.currentUserDao;
	private AppState state = AppState.state;
	private Admin a = state.getAdminUser();
	
	@Override
	public Screen start() {
		System.out.println("Welcome Administrator " + a.getFirstName() + "!");
		System.out.println("Please choose one of the options you would like to do today.");
		System.out.println("1: Check list of accounts");
		System.out.println("2: Check the Transaction History of a User");
		System.out.println("3: Create a new Administrator account");
		System.out.println("4: Logout");
		String selectOption = scan.nextLine();
		
		switch(selectOption) {
		case "1":
			List<User> users = ad.findAllUserAccounts();
			users.stream().forEach((each) -> {
				System.out.println("ID: " + each.getId() + ", Account Name: " + each.getFirstName() + " " + each.getLastName());
			});
			break;
		case "2":
			return new AdminViewTransactionScreen();
		case "3":
			return new AdminCreateScreen();
		case "4":
			return new LoginScreen();
		default:
			break;
		}
		return this;
	}

}
