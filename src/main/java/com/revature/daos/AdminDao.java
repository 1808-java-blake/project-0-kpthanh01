package com.revature.daos;

import java.util.List;

import com.revature.beans.Admin;
import com.revature.beans.User;

public interface AdminDao {
	public static final AdminDao currentUserDao = new AdminDaoJdbc();
	
	Admin createAdmin(Admin a);
	Admin findAdminByUsernameAndPassword(String username, String password);
	List<User> findAllUserAccounts();
}
