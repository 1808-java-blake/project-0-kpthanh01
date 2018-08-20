package com.revature.screens;

import java.util.List;
import java.util.Scanner;
import com.revature.beans.TransactionHistory;
import com.revature.daos.TransactionDao;

public class AdminViewTransactionScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private TransactionDao td = TransactionDao.currentTransactionDao;
	

	@Override
	public Screen start() {
		System.out.println("Please enter a user id");
		int adminInput = scan.nextInt();
		List<TransactionHistory> transactions = td.findByUserId(adminInput);
		if(transactions.isEmpty()) {
			System.out.println("This User ID contains no account");
			return this;
		}
		transactions.stream().forEach((each) -> {
			System.out.println("Date: " + each.getDate() + ", Action: "+ each.getAction());
		});
		
		return new AdminScreen();
	}

}
