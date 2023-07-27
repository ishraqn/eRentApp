package com.erent.persistence.hsqldb;

import com.erent.objects.Post;
import com.erent.objects.User;
import com.erent.persistence.IBookmarkPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkPersistenceHSQLDB implements IBookmarkPersistence {

    private final String dbPath;

    public BookmarkPersistenceHSQLDB(final String dbPath)
    {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public List<Integer> getBookmarkedPostsOfUser(String username)
    {
        //returns a list of ints which are the ids of the posts the passed user has bookmarked
        //decided a bookmark object would be unnecessary overhead for this operation, so we just used ints
        List<Integer> bookmarkedPostIDs = new ArrayList<>();

        try (Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM BOOKMARKS WHERE USERNAME = ?");
            statement.setString(1, username);
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int postID = resultSet.getInt("postid");
                bookmarkedPostIDs.add(postID);
            }
            resultSet.close();
            statement.close();

        } catch (final SQLException e) {
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
            e.printStackTrace();
        }
        return bookmarkedPostIDs;
    }

    @Override
    public boolean createBookmark(String username, int postID)
    {
        boolean bookmarkCreated = false;

        try (Connection connection = connection()) {
            //check if the bookmark already exists
            if(getBookmarkedPostsOfUser(username).contains(postID) == false)  {
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO BOOKMARKS VALUES(?, ?)");
                statement.setString(1, username);
                statement.setInt(2, postID);
                statement.executeUpdate();

                //record that the creation was successful
                bookmarkCreated = true;

                statement.close();
            }

        } catch (final SQLException e) {
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
            e.printStackTrace();
        }

        return bookmarkCreated;
    }

    @Override
    public boolean deleteBookmark(String username, int postID)
    {
        boolean isDeleted = false;

        try (Connection connection = connection()) {
            //check if the bookmark exists
            if(getBookmarkedPostsOfUser(username).contains(postID) == true)  {
                final PreparedStatement statement = connection.prepareStatement("DELETE FROM BOOKMARKS WHERE USERNAME = ? AND POSTID = ?");
                statement.setString(1, username);
                statement.setInt(2, postID);
                statement.executeUpdate();

                //record that the deletion was successful
                isDeleted = true;

                statement.close();
            }

        } catch (final SQLException e) {
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
            e.printStackTrace();
        }

        return isDeleted;
    }
}
