package com.movie.theater.service;

import java.io.*;

public class SerializationUtil {

    public static void serializeSessions() throws IOException {
        SessionManager sessionManager = SessionManager.getSessionManager();
        writeToFile(SessionManager.SESSION_LIST_FILE, sessionManager.SESSION_LIST);
    }

    public static void serializeMovies() throws IOException {
        MovieManager movieManager = MovieManager.getMovieManager();
        writeToFile(MovieManager.MOVIE_LIST_FILE, movieManager.MOVIE_LIST);
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
