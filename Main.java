import java.io.*;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {


        File src = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Installation1\\Games\\src");
        File res = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Installation1\\Games\\res");
        File saveGames = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Installation1\\Games\\saveGames");
        File temp = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Installation1\\Games\\temp");
        File mainDir = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Installation1\\Games\\src\\main");
        File test = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Installation1\\Games\\src\\test");
        File mainFile = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Installation1\\Games\\src\\main\\Main.java");
        File utilsFile = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Installation1\\Games\\src\\main\\Utils.java");
        File drawables = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Installation1\\Games\\res\\drawables");
        File vectors = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Installation1\\Games\\res\\vectors");
        File icons = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Installation1\\Games\\res\\icons");
        File tempTxt = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Installation1\\Games\\temp\\temp.txt");


        // для сборки логов
        StringBuilder bigLogs = new StringBuilder();

        // создаем директории
        createNewDir (src, bigLogs);
        createNewDir (res, bigLogs);
        createNewDir (saveGames, bigLogs);
        createNewDir (temp, bigLogs);
        createNewDir (mainDir, bigLogs);
        createNewDir (test, bigLogs);
        createNewDir (drawables, bigLogs);
        createNewDir (vectors, bigLogs);
        createNewDir (icons, bigLogs);

        // создаем файлы
        createNewFile (mainFile);
        createNewFile (utilsFile);
        createNewFile (tempTxt);


        String logsForWriting = bigLogs.toString();
        System.out.println(logsForWriting);

        try (FileWriter writer = new FileWriter(tempTxt, false)) {
            writer.write(logsForWriting);
            writer.flush();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        GameProgress savingFirst = new GameProgress(100, 50, 10, 20.00);
        GameProgress savingSecond = new GameProgress(90, 40, 13, 10.00);
        GameProgress savingThird = new GameProgress(80, 30, 5, 5.00);

        saveGame("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\saveGames\\savingFirst.dat", savingFirst);
        saveGame("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\saveGames\\savingSecond.dat", savingSecond);
        saveGame("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\saveGames\\savingThird.dat", savingThird);



        zipFiles("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\zip_output1.zip", "C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\saveGames\\savingFirst.dat", "packed_savingFirst.dat" );
        zipFiles("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\zip_output2.zip", "C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\saveGames\\savingSecond.dat", "packed_savingSecond.dat" );
        zipFiles("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\zip_output3.zip", "C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\saveGames\\savingThird.dat", "packed_savingThird.dat" );

    }
























    // создаем файлы и записываем в них состояние объектов
    public static void saveGame(String path, GameProgress savingNumber) {

        File savingFile = new File(path);
        try {
            savingFile.createNewFile();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(savingNumber);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // создаем архив и пакуем файл
    public static void zipFiles (String zipArch, String zipPath, String fileName) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipArch));
             FileInputStream fis = new FileInputStream(zipPath)) {
            ZipEntry entry = new ZipEntry(fileName);
            zout.putNextEntry(entry);
            // считываем содержимое файла в массив
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);
            // закрываем текущую запись для новой записи
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void createNewDir (File nameDir, StringBuilder bigLogs) {
        String logName;
        if (nameDir.mkdir()) {
            logName = "Директория создана успешно";
        } else {
            logName = "директория не создана";
        }
        bigLogs.append(logName);
        bigLogs.append("\n");
    }

    public static void createNewFile (File nameDir) {
        try {
            nameDir.createNewFile();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

    }



}

