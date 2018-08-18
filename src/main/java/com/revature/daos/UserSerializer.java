package com.revature.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.plaf.synth.SynthScrollBarUI;

import com.revature.beans.User;

public class UserSerializer implements UserDao {
	public static final UserSerializer us = new UserSerializer();
	private static User currentUser;
	private UserSerializer() {
		super();
	}

	@Override
	public void createUser(User u) {
		if (u == null) {
			return;
		}
		File f = new File("src/main/resources/users/" + u.getUsername() + ".txt");
		System.out.println(f.getName());
		if (f.exists()) {
			return;
		}
		try {
			f.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("src/main/resources/users/" + u.getUsername() + ".txt"))) {

			oos.writeObject(u);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static User getCurrentUser() {
		return currentUser;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		// verify that what was passed in is not null
		if (username == null || password == null) {
			return null;
		}
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("src/main/resources/users/" + username + ".txt"))) {

			currentUser = (User) ois.readObject(); // retrieve the user if it can
			// verify that the password matches
			if (password.equals(currentUser.getPassword())) {
				return currentUser;
			} else {
				return null;
			}

		} catch (FileNotFoundException e) {
//			e.printStackTrace();
		} catch (IOException e) {
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public User findAdminByUsernameAndPassword(String username, String password) {
		// verify that what was passed in is not null
		if (username == null) {
			return null;
		}
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("src/main/resources/users/" + username + ".txt"))) {

		currentUser = (User) ois.readObject(); // retrieve the user if it can
		
		return currentUser;

		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println(username + " does not exist");
		} catch (IOException e) {
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateUser(User u) {
		if(u == null) {
			return;
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("src/main/resources/users/" + u.getUsername() + ".txt"))) {

			oos.writeObject(u);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub

	}
}
