import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Main {
    public static StringBuilder logger = new StringBuilder();

    public static void main(String[] args) throws IOException {
        deleteDirectory(new File("Games"));
        addLog("Запуск программы");
        createFolder("Games");
        createFolder("Games/src");
        createFolder("Games/res");
        createFolder("Games/savegames");
        createFolder("Games/temp");
        createFolder("Games/src/main");
        createFolder("Games/src/test");
        createFolder("Games/res/drawables");
        createFolder("Games/res/vectors");
        createFolder("Games/res/icons");
        createFile("Games/src/Main.java");
        createFile("Games/src/Utils.java");
        createFile("Games/temp/temp.txt");
        writeFile("Games/temp/temp.txt", logger.toString());
        System.out.print(logger);
    }

    public static void addLog(String msg) {
        logger.append(new Timestamp(System.currentTimeMillis()))
                .append(" ")
                .append(msg)
                .append("\n");
    }

    public static void createFile(String fileName) throws IOException {
        if (fileName != null && fileName.length() > 0) {
            File file = new File(fileName);
            try {
                if(file.createNewFile()) {
                    addLog("Файл " + file.getAbsolutePath() + " создан");
                } else {
                    addLog("ОШИБКА! Не удалось создать файл " + fileName);
                }
            } catch (IOException e) {
                addLog(e.getMessage());
            }
        }
    }

    public static void createFolder(String folderName) {
        if (folderName != null && folderName.length() > 0) {
            File folder = new File(folderName);
            if (folder.mkdir()) {
                addLog("Каталог " + folder.getAbsolutePath() + " создан");
            } else {
                addLog("ОШИБКА! Не удалось создать каталог " + folderName);
            }
        } else {
            addLog("ОШИБКА! Имя каталога для создания не задано");
        }
    }

    public static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    public static void writeFile(String fileName, String text) throws IOException {
        if (fileName != null && fileName.length() > 0 && text != null && text.length() > 0) {
            File file = new File(fileName);
            if (file.exists() && file.canWrite()) {
                try(FileWriter fw = new FileWriter(file)) {
                    fw.write(text);
                } catch (IOException e) {
                    addLog(e.getMessage());
                }
            } else {
                addLog("ОШИБКА! Файл " + fileName + " не доступен для записи");
            }
        } else {
            addLog("ОШИБКА! Не указано имя файла для записи или не задан текст");
        }

    }
}