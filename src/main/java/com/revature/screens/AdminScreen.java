package com.revature.screens;

import java.util.List;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.util.AppState;

public class AdminScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private AppState state = AppState.state;
	private User u = state.getCurrentUser();
	
	@Override
	public Screen start() {
		System.out.println("Welcome Administrator " + u.getFirstName() + "!");
		System.out.println("Please choose one of the options you would like to do today.");
		System.out.println("1: Check list of accounts");
		System.out.println("2: Check the Transaction History of a User");
		System.out.println("3: Logout");
		String selectOption = scan.nextLine();
		
		switch(selectOption) {
		case "1":
			List<User> users = ud.findAllUserAccounts();
			users.stream().forEach((each) -> {
				System.out.println(each);
			});
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
