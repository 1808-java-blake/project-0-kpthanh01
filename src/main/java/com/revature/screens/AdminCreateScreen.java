package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.Admin;
import com.revature.daos.AdminDao;

public class AdminCreateScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private AdminDao ad = AdminDao.currentUserDao;

	@Override
	public Screen start() {
		Admin a = new Admin();
		System.out.println("Enter new username");
		a.setUsername(scan.nextLine());
		System.out.println("Enter password");
		a.setPassword(scan.nextLine());
		System.out.println("Enter first name");
		a.setFirstName(scan.nextLine());
		System.out.println("Enter last name");
		a.setLastName(scan.nextLine());
		
		try {
			ad.createAdmin(a);
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid number");
		}
		
		return new AdminScreen();
	}

}
