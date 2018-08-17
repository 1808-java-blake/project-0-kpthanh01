package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class LoginScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private Logger log = Logger.getRootLogger();

	public Screen start() {
		log.debug("started login screen");
		System.out.println("Hello Welcome to the Energy Bank where you can store your Potential Energies \n");
		System.out.println("If you have an account type 'Login'. \nOtherwise, type 'Register' to create a new account.");
		String userInput = scan.nextLine();
		
		switch (userInput.toLowerCase()) {
		case "login":
			System.out.println("Enter Username");
			String username = scan.nextLine();
			System.out.println("Enter Password: ");
			String password = scan.nextLine();
			
			User currentUser = ud.findByUsernameAndPassword(username, password);
			if (currentUser != null) {
				return new HomeScreen(currentUser);
			}

			System.out.println("unable to login");
		case "register":
			return new RegisterUserScreen();
		default:
			break;
		}
		
		return this;
	}

}
