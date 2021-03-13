import java.util.Scanner;

public class MainDao {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();

//        tworzenie użytkownika
//        User user = new User();
//        user.setUserName("userKolejny2");
//        user.setEmail("userKolejny2@test.pl");
//        user.setPassword("654321");
//        userDao.create(user);

//         edycja użytkownika
//        User userToUpdate = userDao.read(1);
//        userToUpdate.setEmail("user1@gmail.com");
//        userToUpdate.setUserName("user1");
//        userToUpdate.setPassword("superPasswordTest1");
//        userDao.update(userToUpdate);

//        pobieranie
        User read = userDao.read(getIdUser());
        System.out.println(read);

//        usuwanie
//        userDao.delete(9);
//        userDao.deleteAll();


//        lista użytkowników
//        User[] allUser = userDao.findAll();
//        for (User u : allUser) {
//            System.out.println(u);
//        }
    }

    public static int getIdUser() {
        System.out.print("Podaj id Usera: ");
        Scanner scan = new Scanner(System.in);
        Integer id = scan.nextInt();
        return id;
    }

}
