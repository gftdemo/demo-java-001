package com.scalesec.vulnado;

// Removed unused import 'org.apache.catalina.Server'
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger; // Added for logging

public class Comment {
  private static final Logger logger = Logger.getLogger(Comment.class.getName()); // Added for logging

  private String id; // Made id non-public and provided accessor
  private String username; // Made username non-public and provided accessor
  private String body; // Made body non-public and provided accessor
  private Timestamp createdOn; // Renamed created_on to createdOn and made it non-public and provided accessor

  public Comment(String id, String username, String body, Timestamp createdOn) { // Renamed created_on to createdOn
    this.id = id;
    this.username = username;
    this.body = body;
    this.createdOn = createdOn; // Renamed created_on to createdOn
  }

  public String getId() { return id; } // Accessor for id
  public String getUsername() { return username; } // Accessor for username
  public String getBody() { return body; } // Accessor for body
  public Timestamp getCreatedOn() { return createdOn; } // Accessor for createdOn

  public static Comment create(String username, String body){
    long time = new Date().getTime();
    Timestamp timestamp = new Timestamp(time);
    Comment comment = new Comment(UUID.randomUUID().toString(), username, body, timestamp);
    try {
      if (comment.commit()) {
        return comment;
      } else {
        throw new BadRequest("Unable to save comment");
      }
    } catch (Exception e) {
      throw new ServerError(e.getMessage());
    }
  }

  public static List<Comment> fetchAll() { // Renamed fetch_all to fetchAll
    Statement stmt = null;
    List<Comment> comments = new ArrayList<>();
    try {
      Connection cxn = Postgres.connection();
      stmt = cxn.createStatement();

      String query = "select * from comments;";
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String id = rs.getString("id");
        String username = rs.getString("username");
        String body = rs.getString("body");
        Timestamp createdOn = rs.getTimestamp("created_on"); // Renamed created_on to createdOn
        Comment c = new Comment(id, username, body, createdOn); // Renamed created_on to createdOn
        comments.add(c);
      }
    } catch (Exception e) {
      logger.severe(e.getClass().getName()+": "+e.getMessage()); // Replaced System.err with logger
    } finally {
      try {
        if (stmt != null) stmt.close(); // Closed Statement in finally clause
      } catch (SQLException se) {
        logger.severe(se.getMessage()); // Replaced System.err with logger
      }
    }
    return comments; // Moved return statement out of finally block
  }

  public static boolean delete(String id) { // Changed Boolean to boolean
    PreparedStatement pStatement = null; // Moved declaration outside try block
    try {
      String sql = "DELETE FROM comments where id = ?";
      Connection con = Postgres.connection();
      pStatement = con.prepareStatement(sql);
      pStatement.setString(1, id);
      return 1 == pStatement.executeUpdate();
    } catch(Exception e) {
      logger.severe(e.getMessage()); // Replaced System.err with logger
      return false; // Moved return statement into catch block
    } finally {
      try {
        if (pStatement != null) pStatement.close(); // Closed PreparedStatement in finally clause
      } catch (SQLException se) {
        logger.severe(se.getMessage()); // Replaced System.err with logger
      }
    }
  }

  private boolean commit() throws SQLException { // Changed Boolean to boolean
    String sql = "INSERT INTO comments (id, username, body, created_on) VALUES (?,?,?,?)";
    Connection con = Postgres.connection();
    PreparedStatement pStatement = con.prepareStatement(sql);
    pStatement.setString(1, this.id);
    pStatement.setString(2, this.username);
    pStatement.setString(3, this.body);
    pStatement.setTimestamp(4, this.createdOn); // Renamed created_on to createdOn
    return 1 == pStatement.executeUpdate();
  }
}