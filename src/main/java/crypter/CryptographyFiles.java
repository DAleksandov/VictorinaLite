package crypter;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CryptographyFiles {

    public String crypt(String text, String key) throws IOException {
        byte[] keyBytes = key.getBytes(Charset.forName("UTF-8"));//не влияет
        byte[] bytes = text.getBytes();
        return crypt(keyBytes, bytes);
    }

    public String decryptFromFile(String filePath, String key) throws IOException {
        Path path = Paths.get(filePath);
        byte[] keyBytes = key.getBytes();
        byte[] bytes = Files.readAllBytes(path);
        return crypt(keyBytes, bytes);
    }

    public String decryptFromBytes(byte[] sourceBytes, String key) throws IOException {
        byte[] keyBytes = key.getBytes();
        return crypt(keyBytes, sourceBytes);
    }

    //сдвигать байты
    private String crypt(byte[] keyBytes, byte[] bytes) {

        for (int i = 0; i < bytes.length; i++) {
            // bytes[i] = (byte) ((bytes[i]  << 1) | (bytes[i]  >> 1)); // как это расшифровать? Правильно ли приводить к байтам?
            bytes[i] ^= keyBytes[i % keyBytes.length];
        }
        return new String(bytes, Charset.forName("UTF-8"));//не влияет
    }

    public void fileWriter(String fileName, byte[] bytes) throws IOException {
        try (BufferedOutputStream bufferedWriter = new BufferedOutputStream(new FileOutputStream(fileName))) {
            bufferedWriter.write(bytes);
        }
    }
}
