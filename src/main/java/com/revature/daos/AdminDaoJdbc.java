package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.Admin;
import com.revature.beans.User;
import com.revature.util.ConnectionUtil;

public class AdminDaoJdbc implements AdminDao{
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
	public Admin findAdminByUsernameAndPassword(String username, String password) {
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM admin_account WHERE username = ? AND password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Admin a = new Admin();
				a.setFirstName(rs.getString("firstname"));
				a.setLastName(rs.getString("lastname"));
				a.setId(rs.getInt("admin_id"));
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> findAllUserAccounts() {
		try (Connection conn = cu.getConnection()){
			List<User> users = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement(
					"SELECT user_id, username, firstname, lastname FROM user_account");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("user_id"));
				u.setUsername(rs.getString("username"));
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
				users.add(u);
			}	
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Admin createAdmin(Admin a) {
		try (Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO admin_account (username, password, firstname, lastname) VALUES (?,?,?,?)");
			ps.setString(1, a.getUsername());
			ps.setString(2, a.getPassword());
			ps.setString(3, a.getFirstName());
			ps.setString(4, a.getLastName());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " records created");
		} catch (SQLException e) {
			log.error(e.getMessage());
			for(StackTraceElement ste: e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new user");
		}
		return null;
	}

}
