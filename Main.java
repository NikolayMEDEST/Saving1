import java.io.*;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {


        String srcLog;
        String resLog;
        String saveGamesLog;
        String tempLog;
        String mainLog;
        String testLog;
        String mainFileLog;
        String utilsFileLog;
        String drawablesLog;
        String vectorsLog;
        String iconsLog;

        File src = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\src");
        if (src.mkdir()) {
            srcLog = "директория src создана успешно";
        } else {
            srcLog = "директория src не создана";
        }
        File res = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\res");
        if (res.mkdir()) {
            resLog = "директория res создана успешно";
        } else {
            resLog = "директория res не создана";
        }
        File saveGames = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\saveGames");
        if (saveGames.mkdir()) {
            saveGamesLog = "директория saveGames создана успешно";
        } else {
            saveGamesLog = "директория saveGames не создана";
        }
        File temp = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\temp");
        if (temp.mkdir()) {
            tempLog = "директория temp создана успешно";
        } else {
            tempLog = "директория temp не создана";
        }

        File mainDir = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\src\\main");
        if (mainDir.mkdir()) {
            mainLog = "директория main создана успешно";
        } else {
            mainLog = "директория main не создана";
        }
        File test = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\src\\test");
        if (test.mkdir()) {
            testLog = "директория test создана успешно";
        } else {
            testLog = "директория test не создана";
        }

        File mainFile = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\src\\main\\Main.java");
        try {
            mainFile.createNewFile();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        if (mainFile.exists()) mainFileLog = "файл main.java создан успешно";
        else mainFileLog = "файл main.java не был создан";

        File utilsFile = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\src\\main\\Utils.java");
        try {
            utilsFile.createNewFile();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        if (utilsFile.exists()) utilsFileLog = "файл utilsFile.java создан успешно";
        else utilsFileLog = "файл utilsFile.java не был создан";

        File drawables = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\res\\drawables");
        if (drawables.mkdir()) {
            drawablesLog = "директория drawables создана успешно";
        } else {
            drawablesLog = "директория drawables не создана";
        }
        File vectors = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\res\\vectors");
        if (vectors.mkdir()) {
            vectorsLog = "директория vectors создана успешно";
        } else {
            vectorsLog = "директория vectors не создана";
        }
        File icons = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\res\\icons");
        if (icons.mkdir()) {
            iconsLog = "директория icons создана успешно";
        } else {
            iconsLog = "директория icons не создана";
        }


        File tempTxt = new File("C:\\Users\\_\\Desktop\\HomeWorks\\Saving2\\Games\\temp\\temp.txt");
        try {
            tempTxt.createNewFile();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        // cобираем логи
        StringBuilder bigLogs = new StringBuilder()
                .append(srcLog)
                .append("\n")
                .append(resLog)
                .append("\n")
                .append(saveGamesLog)
                .append("\n")
                .append(tempLog)
                .append("\n")
                .append(mainLog)
                .append("\n")
                .append(testLog)
                .append("\n")
                .append(mainFileLog)
                .append("\n")
                .append(utilsFileLog)
                .append("\n")
                .append(drawablesLog)
                .append("\n")
                .append(vectorsLog)
                .append("\n")
                .append(iconsLog)
                .append("\n");

        String logsForWriting = bigLogs.toString();
        System.out.println(logsForWriting);

        try (FileWriter writer = new FileWriter(tempTxt, false)) {
            writer.write(logsForWriting);
            writer.append('\n');
            writer.append('!');
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
}

