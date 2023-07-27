package com.erent.persistence.hsqldb;

import com.erent.objects.User;
import com.erent.persistence.IUserPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserPersistenceHSQLDB implements IUserPersistence {

    private final String dbPath;

    public UserPersistenceHSQLDB(final String dbPath)
    {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User fromResultSet(final ResultSet rs) throws SQLException
    {
        final String username = rs.getString("username");
        final String password = rs.getString("password");
        return new User(username,password);
    }

    @Override
    public User getUserByUsername(String username)
    {
        User user = null;
        try (Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ?");
            statement.setString(1, username);
            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = fromResultSet(resultSet);
            }
            resultSet.close();
            statement.close();

        } catch (final SQLException e) {
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User createUser(String username, String password)
    {
        User user = null;
        try (Connection connection = connection()) {
            //check if the user already exists
            if(getUserByUsername(username) == null)  {
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS VALUES(?, ?)");
                statement.setString(1, username);
                statement.setString(2, password);
                statement.executeUpdate();
                user = new User(username, password);

                statement.close();
            }

        } catch (final SQLException e) {
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean deleteUser(String username) {
        boolean isDeleted = false;
        try (Connection connection = connection()) {
            //check if the user  exists
            if(getUserByUsername(username) != null)  {
                final PreparedStatement statement = connection.prepareStatement("DELETE FROM USERS_POSTS WHERE USERNAME = ?");
                statement.setString(1, username);
                statement.executeUpdate();
                final PreparedStatement statement2 = connection.prepareStatement("DELETE FROM POSTS WHERE POSTEDBY = ?");
                statement2.setString(1, username);
                statement2.executeUpdate();
                final PreparedStatement statement3 = connection.prepareStatement("DELETE FROM USERS WHERE USERNAME = ?");
                statement3.setString(1, username);
                statement3.executeUpdate();
                final PreparedStatement statement4 = connection.prepareStatement("DELETE FROM BOOKMARKS WHERE USERNAME = ?");
                statement4.setString(1, username);
                statement4.executeUpdate();
                isDeleted = true;

                statement.close();
                statement2.close();
                statement3.close();
                statement4.close();
            }

        } catch (final SQLException e) {
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
            e.printStackTrace();
        }
        return isDeleted;
    }
}
