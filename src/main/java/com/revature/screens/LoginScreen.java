package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.Admin;
import com.revature.beans.User;
import com.revature.daos.AdminDao;
import com.revature.daos.UserDao;
import com.revature.util.AppState;

public class LoginScreen implements Screen {
	private AppState state = AppState.state;
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private AdminDao ad = AdminDao.currentUserDao;
	private Logger log = Logger.getRootLogger();

	public Screen start() {
		log.debug("started login screen");
		System.out.println(header);
		System.out.println("Welcome to the Energy Bank! \n");
		System.out.println("If you have an account type 'Login'."
				+ "\nIf you are an administrator type 'Admin' "
				+ "\nOtherwise, type 'Register' to create a new account.");
		String userInput = scan.nextLine();
		
		switch (userInput.toLowerCase()) {
		case "login":
			System.out.println("\nSigning in as a user");
			System.out.println("Enter Username");
			String username = scan.nextLine();
			System.out.println("Enter Password: ");
			String password = scan.nextLine();
			
			User currentUser = ud.findByUsernameAndPassword(username, password);
			if (currentUser != null) {
				state.setCurrentUser(currentUser);
				log.info("user succefully logged in");
				log.info("welcome" + currentUser);
				return new HomeScreen();
			}
			
			System.out.println("Unable to login");
			return this;
			
		case "admin":
			System.out.println("\nSigning in as an Administrator");
			System.out.println("Enter Username");
			String adminUsername = scan.nextLine();
			System.out.println("Enter Password: ");
			String adminPassword = scan.nextLine();
			
			Admin adminUser = ad.findAdminByUsernameAndPassword(adminUsername, adminPassword);
			if (adminUser != null) {
				state.setAdminUser(adminUser);
				log.info("user succefully logged in");
				log.info("welcome" + adminUser);
				return new AdminScreen();
			}

			System.out.println("Unable to login");
			return this;
			
		case "register":
			return new RegisterUserScreen();
		default:
			break;
		}
		
		return this;
	}
}
