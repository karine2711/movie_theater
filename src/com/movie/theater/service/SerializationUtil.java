package com.movie.theater.service;

import com.movie.theater.model.Movie;
import com.movie.theater.model.MovieSession;

import java.io.*;
import java.util.ArrayList;

public class SerializationUtil {

    public static void serializeSessions() throws IOException {
        SessionManager sessionManager = SessionManager.getSessionManager();
        for (MovieSession session : sessionManager.getSessionList()) {
            serializeSession(session);
        }
    }

    public static void serializeSession(MovieSession session) throws IOException {
        String fileName="src/resources/sessions/" + session.getFileName();
        writeToFile(fileName, session);
    }

    public static void serializeMovies() throws IOException {
        MovieManager movieManager = MovieManager.getMovieManager();
        for (Movie movie : movieManager.getMovieList()) {
            serializeMovie(movie);
        }
    }

    public static void serializeMovie(Movie movie) throws IOException {
        writeToFile("src/resources/movies/" + movie.getName().replaceAll(" ", "_") + ".txt", movie);
    }

    public static ArrayList<Movie> deserializeMovies() throws IOException, ClassNotFoundException {
        File movieDir = new File("src/resources/movies/");
        ArrayList<Movie> movieList = new ArrayList<>();
        for (File movieFile : movieDir.listFiles()) {
            Movie movie = (Movie) readFromFile(movieFile.getAbsolutePath());
            movieList.add(movie);
        }
        return movieList;
//        MovieManager movieManager = MovieManager.getMovieManager();
//        for (Movie movie : movieManager.getMovieList()) {
//            writeToFile("src/resources/movies/"+movie.getName().replaceAll(" ","_")+".txt",movie);
//        }
//        writeToFile(MovieManager.MOVIE_LIST_FILE, movieManager.MOVIE_LIST);
    }


    public static ArrayList<MovieSession> deserializeSessions() throws IOException, ClassNotFoundException {
        File sessionDir = new File("src/resources/sessions/");
        ArrayList<MovieSession> sessionList = new ArrayList<>();
        for (File sessionFile : sessionDir.listFiles()) {
            MovieSession movie = (MovieSession) readFromFile(sessionFile.getAbsolutePath());
            sessionList.add(movie);
        }
        return sessionList;
//        MovieManager movieManager = MovieManager.getMovieManager();
//        for (Movie movie : movieManager.getMovieList()) {
//            writeToFile("src/resources/movies/"+movie.getName().replaceAll(" ","_")+".txt",movie);
//        }
//        writeToFile(MovieManager.MOVIE_LIST_FILE, movieManager.MOVIE_LIST);
    }

    public static void writeToFile(String fileName, Serializable object) throws IOException {
        FileOutputStream fileOutputStream
                = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public static Object readFromFile(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream
                = new FileInputStream(fileName);
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        Object recoveredObject = objectInputStream.readObject();
        objectInputStream.close();

        return recoveredObject;
    }
}
