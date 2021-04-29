package com.movie.theater.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationUtil {

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
