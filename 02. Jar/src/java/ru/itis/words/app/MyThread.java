package ru.itis.words.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MyThread implements Runnable {
    String name;
    String folder;
    URL web;

    public MyThread (String name, String folder) throws MalformedURLException {
        this.name = name;
        this.folder = folder;
        web = new URL(name);
    }

    @Override
    public void run() {
        try (InputStream in = web.openStream()) {
            Files.copy(in, Paths.get(folder));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
