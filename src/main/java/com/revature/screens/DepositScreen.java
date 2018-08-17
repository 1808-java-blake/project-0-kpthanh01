package com.revature.screens;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.daos.UserSerializer;

public class DepositScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private User currentUser;
	private UserDao ud = UserDao.currentUserDao;
	private User u = UserSerializer.getCurrentUser();

	public DepositScreen(User currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public Screen start() {
//		System.out.println("How much energy would you like to deposit? ");
//		try {
//			double amount = scan.nextDouble();
//			u.depositBalance(amount);
//			ud.updateUser(u);
//		} catch (InputMismatchException e) {
//			System.out.println("Invalid Number");
//			return new DepositScreen(currentUser);
//		}

		return new HomeScreen(currentUser);
	}

}
