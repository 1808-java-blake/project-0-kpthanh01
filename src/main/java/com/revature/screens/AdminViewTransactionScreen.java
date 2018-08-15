package com.revature.screens;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class AdminViewTransactionScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private User selectedUser;
	private List<String> transactions = new ArrayList<>();

	@Override
	public Screen start() {
		System.out.println("Enter the username of an account to see their transaction history \n");
		System.out.println("Username: ");
		String username = scan.nextLine();
		
		selectedUser = ud.findUser(username);
		if(selectedUser != null) {
			transactions = selectedUser.getTransactionHistory();
			if(transactions.size() == 0) {
				System.out.println(username + " has no transaction history");
			}
			for(String history : transactions) {
				System.out.println(history);
			}
		}
		
		return this;
	}

}
