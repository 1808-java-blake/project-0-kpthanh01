package com.revature.util;

import com.revature.beans.Admin;
import com.revature.beans.User;

public class AppState {
	public static final AppState state = new AppState();
	private User currentUser;
	private Admin adminUser;
	
	private AppState() {
		
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public Admin getAdminUser() {
		return adminUser;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public void setAdminUser(Admin adminUser) {
		this.adminUser = adminUser;
	}
	
}
