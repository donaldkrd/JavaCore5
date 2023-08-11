import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        String pathname = null;

        try {
            pathname = ".";
            Tree.print(new File(pathname), "", true);
        } catch (NullPointerException ex) {
            System.out.println("Неверный путь дирректории: " + pathname);
        }

        reservCopying("./testing", "./backup");

    }

    public static void reservCopying(String sourseDir, String destDir) throws IOException {
        try {
            File file = new File(sourseDir);
            File[] files = file.listFiles();
            if (!Files.exists(Paths.get(destDir))) {
                Files.createDirectory(Paths.get(destDir));
            }
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    try (FileOutputStream fileOutputStream = new FileOutputStream(destDir + "/" + files[i].getName())) {
                        try (FileInputStream fileInputStream = new FileInputStream(files[i])) {
                            int count;
                            while ((count = fileInputStream.read()) != -1) {
                                fileOutputStream.write(count);
                            }
                        }
                    }
                    System.out.printf("Файл %s успешно скопирован... \n", files[i]);
                }
            }
            System.out.println("Файлы успешно скопированы... ");
        } catch (NullPointerException e) {
            System.out.println("Некооректно введён адресс. ");
        }

    }

}