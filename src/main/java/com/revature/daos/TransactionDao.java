package com.revature.daos;

import java.util.List;

import com.revature.beans.TransactionHistory;

public interface TransactionDao {
	public static final TransactionDao currentTransactionDao = new TransactionDaoJdbc();
	
	int createTransaction(TransactionHistory t);
	
	List<TransactionHistory> findByUserId(int id);
}
