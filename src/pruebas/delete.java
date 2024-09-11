package pruebas;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class delete {

    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Enter name: ");
            String nameToDelete = input.nextLine();

            File file = new File("friendsContact.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            if (deleteFriend(file, nameToDelete)) {
                System.out.println("Friend deleted.");
            } else {
                System.out.println("Input name does not exist.");
            }

        } catch (IOException ioe) {
            System.out.println("File error: " + ioe.getMessage());
        }
    }

    private static boolean deleteFriend(File file, String nameToDelete) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        String nameNumberString;
        String name;
        boolean found = false;

        File tmpFile = new File("temp.txt");
        RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

        while (raf.getFilePointer() < raf.length()) {
            nameNumberString = raf.readLine();
            String[] lineSplit = nameNumberString.split("!");

            name = lineSplit[0];

            if (name.equals(nameToDelete)) {
                found = true;
                continue;
            }

            tmpraf.writeBytes(nameNumberString + System.lineSeparator());
        }

        raf.seek(0);
        tmpraf.seek(0);

        raf.setLength(0);
        while (tmpraf.getFilePointer() < tmpraf.length()) {
            raf.writeBytes(tmpraf.readLine() + System.lineSeparator());
        }

        raf.close();
        tmpraf.close();
        tmpFile.delete();

        return found;
    }
}

