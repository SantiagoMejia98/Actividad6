package pruebas;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class update {

    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Enter name: ");
            String newName = input.nextLine();

            System.out.println("Enter number: ");
            Long newNumber = input.nextLong();

            File file = new File("friendsContact.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            if (updateFriend(file, newName, newNumber)) {
                System.out.println("Friend updated.");
            } else {
                System.out.println("Input name does not exist.");
            }

        } catch (IOException ioe) {
            System.out.println("File error: " + ioe.getMessage());
        } catch (NumberFormatException nef) {
            System.out.println("Number format error: " + nef.getMessage());
        }
    }

    private static boolean updateFriend(File file, String newName, Long newNumber) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        File tmpFile = new File("temp.txt");
        RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

        String nameNumberString;
        String name;
        boolean found = false;

        while (raf.getFilePointer() < raf.length()) {
            nameNumberString = raf.readLine();
            String[] lineSplit = nameNumberString.split("!");

            name = lineSplit[0];

            if (name.equals(newName)) {
                found = true;
                tmpraf.writeBytes(newName + "!" + newNumber + System.lineSeparator());
            } else {
                tmpraf.writeBytes(nameNumberString + System.lineSeparator());
            }
        }

        raf.setLength(0);
        tmpraf.seek(0);

        while (tmpraf.getFilePointer() < tmpraf.length()) {
            raf.writeBytes(tmpraf.readLine() + System.lineSeparator());
        }

        raf.close();
        tmpraf.close();
        tmpFile.delete();

        return found;
    }
}

