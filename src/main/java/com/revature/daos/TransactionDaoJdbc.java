package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.TransactionHistory;
import com.revature.beans.User;
import com.revature.util.ConnectionUtil;

public class TransactionDaoJdbc implements TransactionDao {
	private Logger log = Logger.getRootLogger();
	private ConnectionUtil cu = ConnectionUtil.cu;

	@Override
	public int createTransaction(TransactionHistory t) {
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO transaction_history (user_action, action_date, user_id) VALUES (?,?,?)",
					new String[] {"transaction_id"});
			ps.setString(1, t.getAction());
			ps.setString(2, t.getDate());
			ps.setInt(3, t.getUserId());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " records created");
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				log.trace("generated transaction id is" + rs.getInt("transaction_id"));
				return rs.getInt("transaction_id");
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			for(StackTraceElement ste: e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new user");
		}
		return 0;
	}

	@Override
	public List<TransactionHistory> findByUserId(int id) {
		try (Connection conn = cu.getConnection()){
			List<TransactionHistory> transactions = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM transaction_history WHERE user_id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				TransactionHistory list = new TransactionHistory();
				list.setId(rs.getInt("transaction_id"));
				list.setAction(rs.getString("user_action"));
				list.setDate(rs.getString("action_date"));
				list.setUserId(rs.getInt("user_id"));
				transactions.add(list);
			}	
			return transactions;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
