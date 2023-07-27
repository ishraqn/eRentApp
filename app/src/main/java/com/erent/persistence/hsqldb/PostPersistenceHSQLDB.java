package com.erent.persistence.hsqldb;

import com.erent.objects.Post;
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
    private final String defaultLocation = "22 Jump Street";
    private final String defaultCategory = "Construction";
    private final String defaultPictureName = "placeholder_post_img";
    private final String defaultRentalCondition = "No";
    private final String defaultRentedBy = null;


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
        final String description = rs.getString("description");
        final float price = rs.getFloat("price");
        final int rentDuration = rs.getInt("rentduration");
        final String isRented = rs.getString("isrented");
        final String rentedBy = rs.getString("rentedBy");

        Post post =  new Post(postId, postName, postedBy, description, price, rentDuration);

        if(isRented.equals("Yes"))
            post.setIsRentedToTrue();
        else
            post.setIsRentedToFalse();

        post.setRentedBy(rentedBy);

        return post;
    }

    @Override
    public List<Post> getPostList()
    {
        List<Post> posts = new ArrayList<>();
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
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public List<Post> getFirstNPosts(int numberOfPosts)
    {
        final List<Post> posts = new ArrayList<>();
        try (Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM POSTS LIMIT ?");
            statement.setInt(1, numberOfPosts);
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Post post = fromResultSet(resultSet);
                posts.add(post);
            }
            resultSet.close();
            statement.close();

        } catch (final SQLException e) {
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public List<Post> getPostByUser(String userName)
    {
        List<Post> posts = new ArrayList<>();
        try (Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM POSTS WHERE POSTEDBY = ?");
            statement.setString(1,userName);
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Post post = fromResultSet(resultSet);
                posts.add(post);
            }
            resultSet.close();
            statement.close();

        } catch (final SQLException e) {
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public List<Post> getPostNotByUser(String userName)
    {
        List<Post> posts = new ArrayList<>();
        try (Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM POSTS WHERE POSTEDBY != ?");
            statement.setString(1,userName);
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Post post = fromResultSet(resultSet);
                posts.add(post);
            }
            resultSet.close();
            statement.close();

        } catch (final SQLException e) {
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
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
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
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
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
            e.printStackTrace();
        }

        return post;
    }

    @Override
    public void setRentalToTrue(int postID, String userName)
    {
        try (Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("UPDATE POSTS SET ISRENTED = ? WHERE POSTID = ?");
            statement.setString(1, "Yes");
            statement.setInt(2, postID);
            statement.executeUpdate();
            final PreparedStatement statement2 = connection.prepareStatement("UPDATE POSTS SET RENTEDBY = ? WHERE POSTID = ?");
            statement2.setString(1, userName);
            statement2.setInt(2, postID);
            statement2.executeUpdate();

            statement.close();
            statement2.close();

        } catch (final SQLException e) {
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public void setRentalToFalse(int postID)
    {
        try (Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("UPDATE POSTS SET ISRENTED = ? WHERE POSTID = ?");
            statement.setString(1, "No");
            statement.setInt(2, postID);
            statement.executeUpdate();
            final PreparedStatement statement2 = connection.prepareStatement("UPDATE POSTS SET RENTEDBY = NULL WHERE POSTID = ?");
            statement2.setInt(1, postID);
            statement.close();

        } catch (final SQLException e) {
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
            e.printStackTrace();
        }
    }

    @Override
    public Post createPost(String postName, String postedBy, String description, float price, int rentDuration) {
        Post post = null;
        try (Connection connection = connection()) {
            // Get the highest ID in the posts table so the new post will have unique ID
            List<Post> posts = getPostList();
            int greatestID = 0;
            for (int i = 0; i < posts.size(); i++)
            {
                if(posts.get(i).getPostID() > greatestID)
                {
                    greatestID = posts.get(i).getPostID();
                }
            }

            // Insert the post into the database
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO POSTS VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, greatestID + 1);
            statement.setString(2, postName);
            statement.setString(3, postedBy);
            statement.setString(4, defaultLocation);
            statement.setString(5, defaultCategory);
            statement.setFloat(6, price);
            statement.setInt(7, rentDuration);
            statement.setString(8, defaultPictureName);
            statement.setString(9, defaultRentalCondition);
            statement.setString(10, defaultRentedBy);
            statement.setString(11, description);
            statement.executeUpdate();

            // Get the post data from the database
            final PreparedStatement statement2 = connection.prepareStatement(" SELECT * FROM POSTS WHERE POSTID = ?");
            statement2.setInt(1, greatestID + 1);
            final ResultSet resultSet = statement2.executeQuery();

            // Create a post from the post data if possible
            if (resultSet.next()) {
                post = fromResultSet(resultSet);
            }

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
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
            e.printStackTrace();
        }

        return post;
    }

    @Override
    public boolean deletePost(int postID)
    {
        boolean isDeleted = false;
        try (Connection connection = connection()) {
            //check if the post exists
            if(getPostByID(postID) != null)  {
                final PreparedStatement statement = connection.prepareStatement("DELETE FROM USERS_POSTS WHERE POSTID = ?");
                statement.setInt(1, postID);
                statement.executeUpdate();
                final PreparedStatement statement2 = connection.prepareStatement("DELETE FROM POSTS WHERE POSTID = ?");
                statement2.setInt(1, postID);
                statement2.executeUpdate();
                final PreparedStatement statement3 = connection.prepareStatement("DELETE FROM BOOKMARKS WHERE POSTID = ?");
                statement3.setInt(1, postID);
                statement3.executeUpdate();
                isDeleted = true;

                statement.close();
                statement2.close();
                statement3.close();
            }

        } catch (final SQLException e) {
            System.out.println("Connect SQL, " + e.getMessage() + ", " + e.getSQLState());
            e.printStackTrace();
        }
        return isDeleted;
    }
}
