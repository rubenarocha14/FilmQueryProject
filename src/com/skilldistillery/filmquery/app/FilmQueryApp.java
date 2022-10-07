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

//	private void test() throws SQLException {
//		menuGreeting();
//		startUserInterface(scan);
//
//		Film film = db.findFilmById(1);
//		System.out.println(film);
//
//		Actor actor = db.findActorById(1);
//		System.out.println(actor);
//
//		List<Film> filmList = db.findFilmsByKeyword("animal");
//		System.out.println(filmList);
//
//	}

	private void launch() throws SQLException {
		menuGreeting();
		startUserInterface();

	}

	private void startUserInterface() throws NumberFormatException, SQLException {
		int selection;
		do {
			System.out.println("Please choose from the following menu:");
			System.out.println("1) Select film by id");
			System.out.println("2) Select film by keyword");
			System.out.println("3) Quit App");
			selection = 0;
			selection = scan.nextInt();

			switch (selection) {
			case 1:

				System.out.println("What is the id you want to seach by?");
				String id = scan.next();
				Film film = db.findFilmById(Integer.parseInt(id));
				if (film != null) {
					System.out.println(film);
					// db.findLanguageName(film.getId());
					List<Actor> actors = db.findActorsByFilmId(film.getId());
					System.out.println(
							"Actors: \n" + actors.toString().replace("[", " ").replace(",", "").replace("]", ""));
				} else {
					System.out.println("Sorry, no film found by that Id!");
				}

				break;

			case 2:
				System.out.println("What word would you like to search by?");
				String keyword = scan.next();
				List<Film> filmsFound = db.findFilmsByKeyword(keyword);
				if (filmsFound.size() > 0) {
					for (Film film1 : filmsFound) {
						System.out.println(film1);
						List<Actor> actors = db.findActorsByFilmId(film1.getId());
						System.out.println(
								"Actors: \n" + actors.toString().replace("[", " ").replace(",", "").replace("]", ""));
					}
				} else {
					System.out.println("Sorry, no films were found by that keyword!");
				}
				break;
			case 3:
				goodbyeMessage();
				break;
			default:
				System.out.println("Please select a valid option");
				startUserInterface();
			}

		}

		while (selection != 3);
	}

	private void menuGreeting() {
		System.out.println("=============================================================");
		System.out.println("\t\t  Welcome to the IMD(upe)B");
		System.out.println("\t\tLets get some film knowledge!");
		System.out.println("=============================================================");

	}

	private void goodbyeMessage() {
		System.out.println("Thank you for using IMD(upe)B! Happy watching!");
	}

}
