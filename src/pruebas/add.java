package pruebas;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class add {

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

            if (addFriend(file, newName, newNumber)) {
                System.out.println("Friend added.");
            } else {
                System.out.println("Friend with this name or number already exists.");
            }

        } catch (IOException ioe) {
            System.out.println("File error: " + ioe.getMessage());
        } catch (NumberFormatException nef) {
            System.out.println("Number format error: " + nef.getMessage());
        }
    }

    private static boolean addFriend(File file, String newName, Long newNumber) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        String nameNumberString;
        String name;
        long number;

        while (raf.getFilePointer() < raf.length()) {
            nameNumberString = raf.readLine();
            String[] lineSplit = nameNumberString.split("!");

            name = lineSplit[0];
            number = Long.parseLong(lineSplit[1]);

            if (name.equals(newName) || number == newNumber) {
                raf.close();
                return false;
            }
        }

        raf.seek(raf.length());
        nameNumberString = newName + "!" + newNumber;
        raf.writeBytes(nameNumberString + System.lineSeparator());

        raf.close();
        return true;
    }
}

