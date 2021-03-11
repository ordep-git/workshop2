import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserDao {

    private static final String CREATE_USER_QUERY = "INSERT INTO user(username, email, password) VALUES (?, ?, ?)";
    public static final String SELECT_USER_QUERY = "SELECT * FROM user WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM user";

    public User create(User user) {
        try (Connection connect = DBUtil.connect()) {

            DBUtil.insert(connect, CREATE_USER_QUERY, user);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public User read(int userId) {

        try (Connection conn = DBUtil.connect()) {
            PreparedStatement statement = conn.prepareStatement(SELECT_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(User user) {
        try (Connection connect = DBUtil.connect()) {
            DBUtil.updateUser(connect, "user", user);

            System.out.println("Edycja OK. Zmieniono rekord nr " + user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection connect = DBUtil.connect()) {
            DBUtil.remove(connect, "user", userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User[] addToArray(User user, User[] users) {
        users = Arrays.copyOf(users, users.length + 1);
        users[users.length-1] = user;
        return users;
    }

    public User[] findAll() {
        try (Connection connect = DBUtil.connect()) {
            User[] users = new User[0];
            PreparedStatement statement = connect.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users = addToArray(user, users);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
