package Friends;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CRUD {
    private File file;

    public CRUD() {
        file = new File("friendsContact.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String addFriend(String newName, long newNumber) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        String mensaje = "Contacto agregado";
        boolean existe = false;

        while (raf.getFilePointer() < raf.length()) {
            String[] lineSplit = raf.readLine().split("!");
            if (lineSplit[0].equals(newName) || Long.parseLong(lineSplit[1]) == newNumber) {
                mensaje = "Ya existe este contacto";
                existe = true;
                break;
            }
        }

        if (!existe) {
            raf.seek(raf.length());
            raf.writeBytes(newName + "!" + newNumber);
            raf.writeBytes(System.lineSeparator());
        }

        raf.close();
        return mensaje;
    }

    public Long readFriend(String searchName) throws IOException {

        RandomAccessFile raf = new RandomAccessFile(file, "r");

        while (raf.getFilePointer() < raf.length()) {
            String[] lineSplit = raf.readLine().split("!");

            if (lineSplit[0].equals(searchName)) {
                return Long.parseLong(lineSplit[1]);
            }
        }
        return null;
    }

    public String updateFriend(String newName, long newNumber) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        File tmpFile = new File("temp.txt");
        RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
        String mensaje = "Contacto no existe";

        while (raf.getFilePointer() < raf.length()) {
            String[] lineSplit = raf.readLine().split("!");

            if (lineSplit[0].equals(newName)) {
                mensaje = "Contacto actualizado";
                tmpraf.writeBytes(newName + "!" + newNumber + System.lineSeparator());
            } else {
                tmpraf.writeBytes(lineSplit[0] + "!" + lineSplit[1] + System.lineSeparator());
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

        return mensaje;
    }

    public String deleteFriend(String nameToDelete) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        File tmpFile = new File("temp.txt");
        RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
        String mensaje = "El contacto no existe";

        while (raf.getFilePointer() < raf.length()) {
            String[] lineSplit = raf.readLine().split("!");

            if (lineSplit[0].equals(nameToDelete)) {
                mensaje = "Contacto eliminado";
                continue;
            }

            tmpraf.writeBytes(lineSplit[0] + "!" + lineSplit[1] + System.lineSeparator());
        }

        raf.setLength(0);
        tmpraf.seek(0);

        raf.setLength(0);
        while (tmpraf.getFilePointer() < tmpraf.length()) {
            raf.writeBytes(tmpraf.readLine() + System.lineSeparator());
        }

        raf.close();
        tmpraf.close();
        tmpFile.delete();

        return mensaje;
    }
}
