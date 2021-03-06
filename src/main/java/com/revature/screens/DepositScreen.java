package com.revature.screens;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.TransactionHistory;
import com.revature.beans.User;
import com.revature.daos.TransactionDao;
import com.revature.daos.UserDao;
import com.revature.util.AppState;

public class DepositScreen implements Screen{
	private Logger log = Logger.getRootLogger();
	private AppState state = AppState.state;
	private User u = state.getCurrentUser();
	private UserDao ud = UserDao.currentUserDao;
	private TransactionDao td = TransactionDao.currentTransactionDao;
	private Scanner scan = new Scanner(System.in);
	
	private static final DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
	
	@Override
	public Screen start() {
		if(u == null) {
			return new LoginScreen();
		}
		try {
			TransactionHistory t = new TransactionHistory();
			System.out.println("How much would you like to deposit?");
			double userBalance = u.getBalance();
			double amount = scan.nextDouble();
			userBalance = userBalance + amount;
			
			ud.updateUserBalance(userBalance, u.getUsername());
			u.setBalance(userBalance);
			state.setCurrentUser(u);
			System.out.println("Deposited: " + amount + "energies" 
								+ "\nNew Balance: " + userBalance + " energies");
			
			LocalDateTime thisTime = LocalDateTime.now();
			String sb = new String(dateTime.format(thisTime) + " ");
			
			t.setAction("You have deposited " + amount + " energies");
			t.setDate(sb);
			t.setUserId(u.getId());
			int transactionId = td.createTransaction(t);
			if(transactionId == 0) {
				log.error("failed to create transactions");
				return new LoginScreen();
			}
			t.setId(transactionId);
			log.info("created transaction" + t);
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid number");
		}

		return new HomeScreen();
	}

}
