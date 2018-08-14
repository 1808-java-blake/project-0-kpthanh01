package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class LoginScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;

	public Screen start() {
		System.out.println("Hello Welcome to the Energy Bank where you can store your Potential Energies \n");
		System.out.println("If you have an account press enter. \nOtherwise, type 'Register' to create a new account.");
		String userInput = scan.nextLine();
		if ("register".equalsIgnoreCase(userInput)) {
			return new RegisterUserScreen();
		}
		System.out.println("Enter Username");
		String username = scan.nextLine();
		System.out.println("Enter Password: ");
		String password = scan.nextLine();
		
		if(username.equals("admin") && password.equals("hello")) {
			return new AdminScreen();
		}
		User currentUser = ud.findByUsernameAndPassword(username, password);
		if (currentUser != null) {
			return new HomeScreen(currentUser);
		}

		System.out.println("unable to login");
		return this;
	}

}
