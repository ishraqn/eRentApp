package com.erent.persistence.hsqldb;

import android.util.Log;

import com.erent.objects.Post;
import com.erent.objects.User;
import com.erent.persistence.IPostPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostPersistenceHSQLDB implements IPostPersistence {

    private final String dbPath;

    public PostPersistenceHSQLDB(final String dbPath)
    {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Post fromResultSet(final ResultSet rs) throws SQLException
    {
        final int postId = rs.getInt("postid");
        final String postName = rs.getString("postname");
        final String postedBy = rs.getString("postedby");
        final String location = rs.getString("location");
        final String category = rs.getString("category");
        return new Post(postId,postName,postedBy,location,category);
    }

    @Override
    public List<Post> getPostList()
    {
        final List<Post> posts = new ArrayList<>();
        try (Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM POSTS");
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Post post = fromResultSet(resultSet);
                posts.add(post);
            }
            resultSet.close();
            statement.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public List<Post> getFirstNPosts(int numberOfPosts)
    {
        final List<Post> posts = new ArrayList<>();
        try (Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT TOP ? FROM POSTS");
            statement.setInt(1, numberOfPosts);
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Post post = fromResultSet(resultSet);
                posts.add(post);
            }
            resultSet.close();
            statement.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post getPostByID(int postID)
    {
        Post post = null;
        try (Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM POSTS WHERE POSTID = ?");
            statement.setInt(1, postID);
            final ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                post = fromResultSet(resultSet);
            }

            resultSet.close();
            statement.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }

        return post;
    }

    @Override
    public Post getPostByPostName(String postName)
    {
        Post post = null;
        try (Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM POSTS WHERE POSTNAME = ?");
            statement.setString(1, postName);
            final ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                post = fromResultSet(resultSet);
            }

            resultSet.close();
            statement.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }

        return post;
    }

    @Override
    public Post createPost(String postName, String postedBy, String location, String category) {
        Post post = null;
        float price = 100;
        int rentduration = 1;
        String pictureName = "placeholder_post_img";
        try (Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS VALUES(?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, postName);
            statement.setString(2, postedBy);
            statement.setString(3, location);
            statement.setString(4, category);
            statement.setFloat(5, price);
            statement.setInt(6, rentduration);
            statement.setString(7, pictureName);
            statement.executeUpdate();
            final PreparedStatement statement2 = connection.prepareStatement(" SELECT * FROM POSTS WHERE POSTNAME = ? AND POSTEDBY = ?");
            statement2.setString(1, postName);
            statement2.setString(2, postedBy);
            final ResultSet resultSet = statement.executeQuery();
            post = fromResultSet(resultSet);

            //update the USERS_POSTS table
            final PreparedStatement statement3 = connection.prepareStatement("INSERT INTO USERS_POSTS VALUES(?, ?)");
            statement3.setString(1, postedBy);
            statement3.setInt(2, post.getPostID());
            statement3.executeUpdate();

            resultSet.close();
            statement.close();
            statement2.close();
            statement3.close();

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }

        return post;
    }

    @Override
    public boolean deletePost(int postID)
    {
        boolean isDeleted = false;
        try (Connection connection = connection()) {
            //check if the user  exists
            if(getPostByID(postID) != null)  {
                final PreparedStatement statement = connection.prepareStatement("DELETE FROM USERS_POSTS WHERE POSTID = ?");
                statement.setInt(1, postID);
                statement.executeUpdate();
                final PreparedStatement statement2 = connection.prepareStatement("DELETE FROM POSTS WHERE POSTID = ?");
                statement2.setInt(1, postID);
                statement2.executeUpdate();
                isDeleted = true;

                statement.close();
                statement2.close();
            }

        } catch (final SQLException e) {
            Log.e("Connect SQL", e.getMessage() + e.getSQLState());
            e.printStackTrace();
        }
        return isDeleted;
    }
}
