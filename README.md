# FilmQueryProject

# Description:
This project is meant to combine our knowledge of accessing data from a database 
and displaying it to the user properyly, and safely.We began by creating 3 separate 
packages that allowed us to keep our database accessing info separate from our app
 classes. In the entities package we have our Actor and Film classes. These classes
 were meant to translate the Actor and Film tables from the database into java classes.
 The table columns were associated with class fields which were accessed through getters
 and setters. The other aspect of these classes are the toStrings which properly display 
 the requested data to the user. The Film class has one additional aspect the Actor class 
 does not, which is the list of Actors in the film. This list is also a field in Film, however
 I have not figured it our completely. I keep getting a list with no values for the actors when
 I search the movie. My SQL statement is correct, so I am missing something on the Java side. 
 The database package is separated into two files. There is the Database Accessor Interface
 and the databaseAccessorObject. The databaseAccessor is solely the declaration of the 
 methods used in the DatabaseAccessorObject. The databaseAccessorObject contains the overriden
 methods declared in the databaseAccessor. Within the class we initially define our URL
 for database connection, and  connect to the mySQL driver. Next we move onto the methods. Inside
 the class we have findFilmById(filmId: int), List<Film> findFilmsByKeyword(keyword: String), 
 findActorById(int actorId), and public List<Actor> findActorsByFilmId(filmId: int). findActorsByFilmId
 is the method that was giving me trouble inside of my application.
 Each method begins by giving the username and password to access the database with in addition
 to using a try block to establish the connection to the database, and retrieve the data, and finally
 assigning the data to the java objects we are creating. We are able to communicate with our database
 through our provided SQL statement. findFilmsById takes an int to find the film by
 it's id and returns the data associated with the film. Like all other methods in the class, it ends with 
 closing the ResultSet, PreparedStatement and Connection. The find List<Film> findFilmsByKeyword uses a while
 loop to traverse through the retrieved data and assigns the data to a film and adds the film to a list.
 The findACtorById, nearly identical to the findFilmById uses a SQL statement to access all actor info from 
 the Actor table and assigns it to a created Actor object. Finally, the List<Actor> findActorsByFilmId
 traverses through the data received from the database regarding all actors associated with a particular film, 
 assigns the data to an Actor object and adds it to the list.
 
 Our final package is our App package. It begins be creating our databaseAccessorObject so we can utilize
 our methods within the created class. In the main methods we have our instantiation of the app, and the call to 
 the launch() method which runs the functionality of the app.
 The launch() calls the startUserInterface(Scanner) which displays the menu to the user and gives them the option
 to select a film by id or keyword. Should the user select to search by id they are prompted to enter an id
 which will be used in a SQL statement to get the specified film and it's data. Should the user select to search by keyword they will
 be prompted to enter a String which will be used in a SQL to get all films with the keyword in the title or description.
 The user can also elect to exit the app. The startUserInterface() utilizes a switch statement to handle inputs that aren't
 ints associated with a selection, and a try catch block to handle those inputs that are not of type int. The method also utilizes a while 
 loop that keeps the program looping so long as the user doesn't select to exit the program, or they don't enter a input that
 prompts a custom exception message. 
 
 Once the user selects to exit the app, a goodbye message will be displayed.


#Technology Used:
Eclipse, Atom, Terminal, mySQL, stackOverflow, YouTube
MAMP

#Lessons Learned:

I had a lot more trouble with this project that I was expecting to, particularly implementing the scanner and 
getting the actor List. When I tested my code I was able to get all of it working without the scanner, except
the actor list, however, I was able to get the actor list in SQL, I just couldn't translate it over to Java. One 
unexpected issue I had was not being able to utlize the try with resources block to prevent having to close our the 
result and prepared statements and connection for each method. I would get a stacktrace error when trying to use the
try with resources. I was able to follow the class material well, but I found myself blanking when I tried to 
go off on my own this weekend. I learned that combining databases with programming languages can be fairly tricky
when you're not fully confident. I was able to watch some YouTube videos and get a slightly better feel for what is
going on, but I'm still trying to wrap my head around this past week of database/java interaction. 

