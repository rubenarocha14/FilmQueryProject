package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to load database driver:");
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		String user = "student";
		String pass = "student";
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * FROM film join language on film.language_id = language.id " + "where  film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();

			while (filmResult.next()) {
				film = new Film();
				film.setId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setRating(filmResult.getString("rating"));
				film.setReleaseYear(filmResult.getInt("release_year"));
				film.setLanguageId(filmResult.getInt("language_id"));
				film.setLanguageName(filmResult.getString("name"));
				film.setRentalDuration(filmResult.getInt("rental_duration"));
				film.setRentalRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setReplacementCost(filmResult.getDouble("replacement_cost"));

			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		String user = "student";
		String pass = "student";
		Actor actor = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();

			while (actorResult.next()) {
				actor = new Actor();
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));

			}

			actorResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;

	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		String user = "student";
		String pass = "student";
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "select actor.id, actor.first_name, actor.last_name, "
					+ "film.title from actor  join film_actor on " + "actor.id = film_actor.actor_id join film on "
					+ "film_actor.film_id = film.id where film.id =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet actorsResults = stmt.executeQuery();
			while (actorsResults.next()) {
				Actor actor = new Actor();
				actor.setId(actorsResults.getInt("id"));
				actor.setFirstName(actorsResults.getString("first_name"));
				actor.setLastName(actorsResults.getString("last_name"));
				actors.add(actor);
			}
			actorsResults.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	@Override
	public List<Film> findFilmsByKeyword(String keyword) throws SQLException {
		Film film = null;
		String user = "student";
		String pass = "student";
		List<Film> films = new ArrayList<>();

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "select * " + "from film join language on film.language_id = language.id where "
				+ "film.title like ? or film.description like ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + keyword + "%");
		stmt.setString(2, "%" + keyword + "%");
		ResultSet filmsResult = stmt.executeQuery();
		while (filmsResult.next()) {
			film = new Film();
			film.setId(filmsResult.getInt("id"));
			film.setTitle(filmsResult.getString("title"));
			film.setDescription(filmsResult.getString("description"));
			film.setReleaseYear(filmsResult.getInt("release_year"));
			film.setLanguageId(filmsResult.getInt("language_id"));
			film.setLanguageName(filmsResult.getString("name"));
			film.setRentalDuration(filmsResult.getInt("rental_duration"));
			film.setRentalRate(filmsResult.getDouble("rental_rate"));
			film.setLength(filmsResult.getInt("length"));
			film.setReplacementCost(filmsResult.getDouble("replacement_cost"));
			film.setRating(filmsResult.getString("rating"));
			film.setSpecialFeatures(filmsResult.getString("special_features"));
			films.add(film);
		}
		filmsResult.close();
		stmt.close();
		conn.close();

		return films;
	}

}
