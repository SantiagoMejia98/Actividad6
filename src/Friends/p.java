package Friends;

import java.io.IOException;

public class p {
    public static void main(String[] args) throws IOException {
        CRUD crud = new CRUD();

        System.out.println(crud.addFriend("carlos",12));
    }
}
