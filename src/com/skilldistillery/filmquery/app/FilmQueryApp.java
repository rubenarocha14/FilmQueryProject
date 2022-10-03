package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
	}

	private void test() throws SQLException {
		menuGreeting();
		startUserInterface(scan);

		Film film = db.findFilmById(1);
		System.out.println(film);

		Actor actor = db.findActorById(1);
		System.out.println(actor);

		List<Film> filmList = db.findFilmsByKeyword("animal");
		System.out.println(filmList);

	}

	private void launch() {

		startUserInterface(scan);

		scan.close();
	}

	private void startUserInterface(Scanner input) {
		int selection;
		try {
			do {
				System.out.println("Please choose from the following menu:");
				System.out.println("1) Select film by id");
				System.out.println("2) Select film by keyword");
				System.out.println("3) Quit App");

				selection = 0;
				selection = scan.nextInt();
				switch (selection) {
				case 1:
					try {
						System.out.println("What is the id you want to seach by?");
						String id = scan.next();
						db.findFilmById(Integer.parseInt(id));
						System.out.println("select by id");
						break;
					} catch (Exception e) {
						System.out.println("Only integers allowed as an input for search by Id");
					}
				case 2:
					System.out.println("What word would you like to search by?");
					String keyword = scan.next();
					db.findFilmsByKeyword(keyword);
					System.out.println("select by keyword");
					break;
				case 3:
					System.out.println("select exit");
					goodbyeMessage();
					break;
				default:
					System.out.println("Please select a valid option");
					startUserInterface(input);
				}

			}

			while (selection != 3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Only integers 1-3 allowed");
		}
//	 
	}

	private void menuGreeting() {
		System.out.println("=============================================================");
		System.out.println("\t\tWelcome to the IMD(upe)B");
		System.out.println("\t\tLets get some film knowledge!");
		System.out.println("=============================================================");

	}

	private void goodbyeMessage() {
		System.out.println("Thank you for using IMD(upe)B! Happy watching!");
	}

}
