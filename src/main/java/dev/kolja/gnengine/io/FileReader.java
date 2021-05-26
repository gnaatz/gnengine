package dev.kolja.gnengine.io;

import dev.kolja.gnengine.core.Engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    public static String readFile(String path)
    {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            Engine.LOGGER.error("Could not find file [" + path + "]");
        }
        return new String(encoded, Charset.defaultCharset());
    }

    public static void readFileLineByLine(String path, IForEach iForEach) throws IOException {
        File file = new File(path);
        BufferedReader br;
        try {
            java.io.FileReader fr = new java.io.FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        String line;

        int index = 0;
        while ((line = br.readLine()) != null) {
            iForEach.forEach(line, index);
            index++;
        }
    }

    public interface IForEach {
        void forEach(String line, int index);
    }
}
