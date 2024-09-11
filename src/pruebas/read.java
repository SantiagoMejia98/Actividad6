package pruebas;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

class read {

    public static void main(String[] args) {
        try {
            File file = new File("friendsContact.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            displayFriends(file);

        } catch (IOException ioe) {
            System.out.println("File error: " + ioe.getMessage());
        }
    }

    private static void displayFriends(File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        String nameNumberString;
        String name;
        long number;

        while (raf.getFilePointer() < raf.length()) {
            nameNumberString = raf.readLine();

            String[] lineSplit = nameNumberString.split("!");

            name = lineSplit[0];
            number = Long.parseLong(lineSplit[1]);

            System.out.println("Friend Name: " + name + "\nContact Number: " + number + "\n");
        }

        raf.close();
    }
}