package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.util.ConnectionUtil;

public class UserDaoJdbc implements UserDao {
	private ConnectionUtil cu = ConnectionUtil.cu;
	private Logger log = Logger.getRootLogger();
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void createUser(User u) {
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO user_account (username, password, age, firstname, lastname, balance) VALUES (?,?,?,?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setInt(3, u.getAge());
			ps.setString(4, u.getFirstName());
			ps.setString(5, u.getLastName());
			ps.setDouble(6, u.getBalance());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " records created");
		} catch (SQLException e) {
			// TODO: handle exception
			log.error(e.getMessage());
			for(StackTraceElement ste: e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new user");
		}
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM user_account WHERE username=? AND password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User u = new User();
				u.setUsername(rs.getString("username"));
				u.setAge(rs.getInt("age"));
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
				u.setId(rs.getInt("user_id"));
				u.setBalance(rs.getDouble("balance"));
				return u;
			} else {
				log.warn("failed to find user with provided credentials from the db");
				return null;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findAdminByUsernameAndPassword(String username, String password) {
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM admin_account WHERE username = ? AND password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User u = new User();
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
				u.setId(rs.getInt("admin_id"));
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateUserBalance(double balance, String username) {
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE user_account SET balance=? WHERE username=?");
			ps.setDouble(1, balance);
			ps.setString(2, username);
			int recordsUpdated = ps.executeUpdate();
			log.trace(recordsUpdated + " records updated");
		} catch (SQLException e) {
			log.error(e.getMessage());
			for(StackTraceElement ste: e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed update user balance");
		}

	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub

	}

}
