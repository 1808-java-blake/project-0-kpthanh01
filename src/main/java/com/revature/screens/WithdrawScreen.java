package com.revature.screens;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.daos.UserSerializer;

public class WithdrawScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private User currentUser;
	private UserDao ud = UserDao.currentUserDao;
	private User u = UserSerializer.getCurrentUser();
	private static final DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
	LocalDateTime thisTime = LocalDateTime.now();
	StringBuffer sb = new StringBuffer(dateTime.format(thisTime) + " ");
	
	public WithdrawScreen(User currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public Screen start() {
		System.out.println("How much energy would you like to withdraw? ");
		try {
			double amount = scan.nextDouble();
			u.withdrawBalance(amount);
			u.setTransactionHistory(amount + " energy has been taken out of your balance");
			ud.updateUser(u);
		} catch (InputMismatchException e) {
			System.out.println("Invalid Number");
			return new WithdrawScreen(currentUser);
		}
		
		return new HomeScreen(currentUser);
	}
	
	
//	sb2.append("Deposit ");
//	sb2.append(deposit);
//	ReadAndWrite.writeToFront(sb2.toString());

}
