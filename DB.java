import javax.swing.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DB
{

    private static final String NOTIFICATION = "Notification";

    //Connection info
    private static final String USERNAME = "ather";
    private static final String PASSWORD = "ilyas";
    private static final String DATABASE_NAME = "habinet";
    private static final String CONN = "jdbc:mysql://localhost/" + DATABASE_NAME;

    /**
     * gets a connection to the database
     *
     * @return a connection to the database
     * @throws SQLException
     */
    private static Connection getConnection () throws SQLException
    {
        return DriverManager.getConnection (CONN, USERNAME, PASSWORD);
    }

    /**
     * adding a new user to the database
     *
     * @param id
     * @param username
     * @param pass
     * @param email
     * @param level
     * @param xp
     * @param streak
     * @param streakFreezeCount
     * @param mode
     */
    public static void add (int id, String username, String pass, String email, int level, int xp, int streak,
                            int streakFreezeCount, String mode)
    {
        String sql = "INSERT INTO `username` (`id`, `user`, `pass`, `mail`, `level`, `xp`, `mode`, `streak`, `streakcount`) VALUES (?, ?, ?, ?,?,?,?,?,?)";
        try (Connection conn = DBconnection.getConnection ("users");
             PreparedStatement stmt = conn.prepareStatement (sql);)
        {
            stmt.setInt (1, id);
            stmt.setString (2, username);
            stmt.setString (3, pass);
            stmt.setString (4, email);
            stmt.setInt (5, level);
            stmt.setInt (6, xp);
            stmt.setString (7, mode);
            stmt.setInt (8, streak);
            stmt.setInt (9, streakFreezeCount);
            stmt.execute ();
        } catch (SQLException e)
        {
            System.err.println (e);
            e.printStackTrace ();
        }
    }

    /**
     * change Any String value from users database
     *
     * @param id    profileID of user
     * @param value the value requesting
     * @return result
     */
    public static void changeValue (String newValue, int id, String value)
    {
        String sql = "UPDATE `username` SET `" + value + "` = (?) WHERE id = (?)";
        try (Connection conn = DBconnection.getConnection ("users");
             PreparedStatement stmt = conn.prepareStatement (sql);)
        {
            stmt.setString (1, newValue);
            stmt.setInt (2, id);
            stmt.execute ();
        } catch (SQLException e)
        {
            System.err.println (e);
            e.printStackTrace ();
        }
    }

    /**
     * get Any integer value from users database
     *
     * @param id    profileID of user
     * @param value the value requesting
     * @return result
     */
    public static void changeIntValue (int newValue, int id, String value)
    {
        String sql = "UPDATE `username` SET `" + value + "` = (?) WHERE id = (?)";
        try (Connection conn = DBconnection.getConnection ("users");
             PreparedStatement stmt = conn.prepareStatement (sql);)
        {
            stmt.setInt (1, newValue);
            stmt.setInt (2, id);
            stmt.execute ();
        } catch (SQLException e)
        {
            System.err.println (e);
            e.printStackTrace ();
        }
    }

    /**
     * get Any String value from users database
     *
     * @param id    profileID of user
     * @param value the value requesting
     * @return result
     */
    public static String getValue (int id, String value)
    {

        try (Connection conn = DBconnection.getConnection ("users");
             Statement stmt = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery ("SELECT * FROM username");)
        {
            rs.absolute (id);
            return rs.getString (value);
        } catch (SQLException e)
        {
            System.err.println (e);
            e.printStackTrace ();
        }
        return null;

    }

    /**
     * Getting the id of an existing user when the user is trying to sign in
     *
     * @param user     username
     * @param password password
     * @return profileID
     */
    public static int getID (String user, String password)
    {

        try (Connection conn = DBconnection.getConnection ("users");
             Statement stmt = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery ("SELECT * FROM username");)
        {
            while (rs.next ())
            {
                if (rs.getString ("user").equals (user))
                {
                    if (rs.getString ("pass").equals (password))
                    {
                        return rs.getInt ("id");
                    }
                }
            }
        } catch (SQLException e)
        {
            System.err.println (e);
        }
        return 0;
    }

    /**
     * create user based on userID
     *
     * @param id profileID
     * @return user retrieved from database
     */
    public static User getUser (int id)
    {
        try (Connection conn = DBconnection.getConnection ("users");
             Statement stmt = conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery ("SELECT * FROM username");)
        {
            rs.absolute (id);
            return new User (rs.getString ("user"), rs.getString ("pass"), rs.getString ("mail"), rs.getString ("mode"),
                    rs.getInt ("level"), rs.getInt ("xp"), rs.getInt ("streak"), rs.getInt ("streakcount"), false);
        } catch (SQLException e)
        {
            System.err.println (e);
        }
        return null;
    }

    /**
     * Creates a new Notification Table for NewUser table name = NotificationX (X =
     * UserID)
     *
     * @param userId
     */
    public static void createNotificationTable (int userId)
    {

        String sql = "CREATE TABLE " + NOTIFICATION + userId + " ( ";
        sql += "serialNum INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,";
        sql += "notifiString VARCHAR(1000), ";
        sql += "color VARCHAR(100), ";
        sql += "pageOnClick VARCHAR(100) ";
        sql += ")";

        try (Connection con = DBconnection.getConnection ("group"); PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            stat.execute ();

        } catch (SQLException e)
        {
            System.err.println (e);
            ;
        }
    }

    /**
     * Adds Notification to the User's Notification Table
     *
     * @param userId       to get correct User's Notifications
     * @param notifiString the Notification String
     * @param color        Color of the Notification (RED / YELLOW / GREEN)
     * @param pageOnClick  Page where user should be taken when Notification is
     *                     clicked (Home / Groups / NULL)
     */
    public static void addNotification (int userId, String notifiString, String color, String pageOnClick)
    {

        String sql;

        sql = "INSERT INTO " + NOTIFICATION + userId + " (notifiString, color, pageOnClick) ";
        sql += ("VALUES ('" + notifiString + "', '" + color + "', '" + pageOnClick + "' )");

        try (Connection con = DBconnection.getConnection ("group"); PreparedStatement stat = con.prepareStatement (sql);

        )
        {

            stat.execute ();

        } catch (SQLException e)
        {
            System.err.println (e);
            ;
        }

    }

    //===========================================================================================================

    /**
     * Creates a new Task Table for NewUser
     * table name = TaskX (X = UserID)
     *
     * @param userId to identify the User and his/her tasks
     */
    public static void createTaskTable (int userId)
    {

        String sql = "CREATE TABLE Tasks" + userId + " ( ";
        sql += "serialNum INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,";
        sql += "title VARCHAR(1000), ";
        sql += "priority VARCHAR(1000), ";
        sql += "startDate VARCHAR(1000), ";
        sql += "dueDate VARCHAR(1000), ";
        sql += "startTime VARCHAR(1000), ";
        sql += "dueTime VARCHAR(1000), ";
        sql += "status VARCHAR(1000), "; //completed or not
        sql += "category VARCHAR(1000), ";
        sql += "needsVerification VARCHAR(1000), ";
        sql += "isVerified VARCHAR(1000), ";
        sql += "xpReward INT(6),";
        sql += "upVote INT(3),";
        sql += "downVote INT(3),";
        sql += "verificationImageLocation VARCHAR(10000), ";
        sql += "taskDescription VARCHAR(10000) ";
        sql += ")";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            stat.execute ();

        } catch (SQLException e)
        {
            System.err.println (e);
        }
    }

    /**
     * Adds Notification to the User's Notification Table
     *
     * @param userId            to get User's ID
     * @param title             the title of the task
     * @param priority          the priority of the task
     * @param startDateLD       (LD - LocalDate) the starting date of the task - Must be changed to string before calling SQL statement
     * @param dueDateLD         (LD - LocalDate) the end date of the task - Must be changed to string before calling SQL statement
     * @param startTimeLT       (LT - LocalTime) the start time of the task - Must be changed to string before calling SQL statement
     * @param dueTimeLT         (LT - LocalTime) the end time of the task - Must be changed to string before calling SQL statement
     * @param status            the status of task (comleted, not completed, missed)
     * @param category          the category of the task (maths, physics etc.)
     * @param needsVerification does the task need verification (false, true)
     * @param isVerified        is the task verified (true,false)
     * @param xpReward          the xp reward amound to be given after completing the task
     */

    public static void addToTaskTable (int userId, String title, String priority, LocalDate startDateLD, LocalDate dueDateLD, LocalTime startTimeLT, LocalTime dueTimeLT, Boolean status, String category, Boolean needsVerification, Boolean isVerified, int xpReward)
    {

        String startDate = startDateLD.toString ();
        String dueDate = dueDateLD.toString ();
        String startTime = startTimeLT.toString ();
        String dueTime = dueTimeLT.toString ();


        String sql = "insert into Tasks" + userId;
        sql += "( title, priority, startDate, dueDate, startTime, dueTime, status, category, needsVerification, isVerified, xpReward)";
        sql += "values ('" + title + "','" + priority + "','" + startDate + "','" + dueDate + "','" + startTime + "','" + dueTime + "','" + status + "','" + category + "','" + needsVerification + "','" + isVerified + "','" + xpReward + "')";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            stat.execute ();

        } catch (SQLException e)
        {
            System.err.println (e);
        }


    }


    /**
     * Get the biggest serial num to increment when a new task is added so a record of each task is stored and can be accessed
     *
     * @param userId        to get the User's ID
     * @param resultOfQuery the max value of the column "serialNum" returned
     */

    public static void getMaxSerialNum (int userId)
    {

        String sql = "SELECT MAX(serialNum)";
        sql += "FROM Tasks" + userId;


        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);


        )
        {
            ResultSet resultOfQuery = stat.executeQuery (sql);
            if (resultOfQuery.next ())
            {
                System.out.println (resultOfQuery.getInt (1));
            }

        } catch (SQLException e)
        {
            System.err.println (e);
        }


    }


    /**
     * Check if a given Task Table with a given UserID exists
     *
     * @param userId        to get the User's ID
     * @param resultOfQuery the value returned if a Table Exists
     */

    public static Boolean checkIfTaskTableExistsForUser (int userId)
    {

        String sql = "";


        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            DatabaseMetaData dbm = con.getMetaData ();
            ResultSet resultOfQuery = dbm.getTables (null, null, "Tasks" + userId, null);
            if (resultOfQuery.next ())
            {
                return true;
            } else
            {
                return false;
            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return false;
        }


    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId           to get the User's ID
     * @param taskSerialNumber the serial number of the task to be removed
     */

    public static void removeTaskFromTable (int userId, int taskSerialNumber)
    {

        String sql = "DELETE FROM Tasks" + userId;
        sql += " WHERE serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            stat.execute ();

        } catch (SQLException e)
        {
            System.err.println (e);
        }


    }


    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId           to get the User's ID
     * @param taskSerialNumber the serial number of the task to be removed
     * @param parsedTitle      the task title returned from the database
     */

    public static String getTaskTitle (int userId, int taskSerialNumber)
    {
        String parsedTitle = "";
        String sql = "select title from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedTitle = rs.getString ("title");
                System.out.println (parsedTitle);
                return parsedTitle;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedTitle;

        }

        return parsedTitle;

    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId           to get the User's ID
     * @param taskSerialNumber the serial number of the task to be removed
     * @param parsedPriority   the task priority returned from the database
     */

    public static String getTaskPriority (int userId, int taskSerialNumber)
    {
        String parsedPriority = "";
        String sql = "select priority from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedPriority = rs.getString ("priority");
                System.out.println (parsedPriority);
                return parsedPriority;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedPriority;

        }

        return parsedPriority;

    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId           to get the User's ID
     * @param taskSerialNumber the serial number of the task to be removed
     * @param parsedStartDate  the task start date returned from the database
     */

    public static LocalDate getTaskStartDate (int userId, int taskSerialNumber)
    {
        LocalDate parsedStartDate = null;
        String sql = "select startDate from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedStartDate = LocalDate.parse (rs.getString ("startDate"));
                System.out.println (parsedStartDate);
                return parsedStartDate;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedStartDate;

        }

        return parsedStartDate;

    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId           to get the User's ID
     * @param taskSerialNumber the serial number of the task to be removed
     * @param parsedDueDate    the task start due returned from the database
     */

    public static LocalDate getTaskDueDate (int userId, int taskSerialNumber)
    {
        LocalDate parsedDueDate = null;
        String sql = "select dueDate from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedDueDate = LocalDate.parse (rs.getString ("dueDate"));
                System.out.println (parsedDueDate);
                return parsedDueDate;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedDueDate;

        }

        return parsedDueDate;

    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId           to get the User's ID
     * @param taskSerialNumber the serial number of the task to be removed
     * @param parsedStartTime  the task start time returned from the database
     */

    public static LocalTime getTaskStartTime (int userId, int taskSerialNumber)
    {
        LocalTime parsedStartTime = null;
        String sql = "select startTime from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedStartTime = LocalTime.parse (rs.getString ("startTime"));
                System.out.println (parsedStartTime);
                return parsedStartTime;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedStartTime;

        }

        return parsedStartTime;

    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId           to get the User's ID
     * @param taskSerialNumber the serial number of the task to be removed
     * @param parsedDueTime    the task due time returned from the database
     */

    public static LocalTime getTaskDueTime (int userId, int taskSerialNumber)
    {
        LocalTime parsedDueTime = null;
        String sql = "select dueTime from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedDueTime = LocalTime.parse (rs.getString ("dueTime"));
                System.out.println (parsedDueTime);
                return parsedDueTime;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedDueTime;

        }

        return parsedDueTime;

    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId           to get the User's ID
     * @param taskSerialNumber the serial number of the task to be removed
     * @param parsedStatus     the task current status returned from the database (Comleted or Not Completed)
     */

    public static Boolean getTaskCurrentStatus (int userId, int taskSerialNumber)
    {
        Boolean parsedStatus = null;
        String sql = "select status from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedStatus = Boolean.parseBoolean (rs.getString ("status"));
                System.out.println (parsedStatus);
                return parsedStatus;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedStatus;

        }

        return parsedStatus;

    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId           to get the User's ID
     * @param taskSerialNumber the serial number of the task to be removed
     * @param parsedCategory   the task category returned from the database (maths/eng etc.)
     */

    public static String getTaskCategory (int userId, int taskSerialNumber)
    {
        String parsedCategory = null;
        String sql = "select category from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedCategory = rs.getString ("category");
                System.out.println (parsedCategory);
                return parsedCategory;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedCategory;

        }

        return parsedCategory;

    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId                  to get the User's ID
     * @param taskSerialNumber        the serial number of the task to be removed
     * @param parsedNeedsVerification the value of the question that "Does that task need verification? Is the task from a top scorer?"
     */

    public static Boolean getTaskNeedsVerification (int userId, int taskSerialNumber)
    {
        Boolean parsedNeedsVerification = null;
        String sql = "select needsVerification from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedNeedsVerification = Boolean.parseBoolean (rs.getString ("needsVerification"));
                System.out.println (parsedNeedsVerification);
                return parsedNeedsVerification;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedNeedsVerification;

        }

        return parsedNeedsVerification;

    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId           to get the User's ID
     * @param taskSerialNumber the serial number of the task to be removed
     * @param parsedIsVerified the value returned after "So that task is from a top scorer.. has it been verified?"
     */

    public static Boolean getTaskIsVerified (int userId, int taskSerialNumber)
    {
        Boolean parsedIsVerified = null;
        String sql = "select isVerified from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedIsVerified = Boolean.parseBoolean (rs.getString ("isVerified"));
                System.out.println (parsedIsVerified);
                return parsedIsVerified;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedIsVerified;

        }

        return parsedIsVerified;

    }


    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId           to get the User's ID
     * @param taskSerialNumber the serial number of the task to be removed
     * @param parsedXpReward   the xp reward returned from the databse
     */

    public static int getTaskXpReward (int userId, int taskSerialNumber)
    {
        int parsedXpReward = 0;
        String sql = "select xpReward from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedXpReward = rs.getInt ("xpReward");
                System.out.println (parsedXpReward);
                return parsedXpReward;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedXpReward;

        }

        return parsedXpReward;

    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId           to get the User's ID
     * @param taskSerialNumber the serial number of the task to be removed
     * @param parsedUpVotes    the upVotes returned from a verifiable task
     */

    public static int getTaskUpVotes (int userId, int taskSerialNumber)
    {
        int parsedUpVotes = 0;
        String sql = "select upVote from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedUpVotes = rs.getInt ("upVote");
                System.out.println (parsedUpVotes);
                return parsedUpVotes;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedUpVotes;

        }

        return parsedUpVotes;

    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId           to get the User's ID
     * @param taskSerialNumber the serial number of the task to be removed
     * @param parsedDownVotes  the downVotes returned from a verifiable task
     */

    public static int getTaskDownVotes (int userId, int taskSerialNumber)
    {
        int parsedDownVotes = 0;
        String sql = "select downVote from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedDownVotes = rs.getInt ("downVote");
                System.out.println (parsedDownVotes);
                return parsedDownVotes;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedDownVotes;

        }

        return parsedDownVotes;

    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId                          to get the User's ID
     * @param taskSerialNumber                the serial number of the task to be removed
     * @param parsedVerificationImageLocation the stored image location of the uploaded verification image from the top scorer if he chooses to do so
     */

    public static String getTaskVerificationImageLocation (int userId, int taskSerialNumber)
    {
        String parsedVerificationImageLocation = "";
        String sql = "select verificationImageLocation from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedVerificationImageLocation = rs.getString ("verificationImageLocation");
                System.out.println (parsedVerificationImageLocation);
                return parsedVerificationImageLocation;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedVerificationImageLocation;

        }

        return parsedVerificationImageLocation;

    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId                        to get the User's ID
     * @param taskSerialNumber              the serial number of the task to be removed
     * @param parsedVerificationDescription the stored image location of the uploaded Task Description from the top scorer if he chooses to do so
     */

    public static String getTaskVerificationDescription (int userId, int taskSerialNumber)
    {
        String parsedVerificationDescription = "";
        String sql = "select taskDescription from Tasks" + userId;
        sql += " where serialNum = '" + taskSerialNumber + "'";

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);
            while (rs.next ())
            {
                parsedVerificationDescription = rs.getString ("taskDescription");
                System.out.println (parsedVerificationDescription);
                return parsedVerificationDescription;

            }

        } catch (SQLException e)
        {
            System.err.println (e);
            return parsedVerificationDescription;

        }

        return parsedVerificationDescription;

    }

    /**
     * Remove task from table given a User ID and the Task Serial Number
     *
     * @param userId       to get the User's ID
     * @param parsedParams these are all those params which are taken from the database and converted to their respective data types
     * @param list         the list of a single task that is returned after each while loop
     *                     The order of each stored task is : serialNum, title, priority, startDate, dueDate, startTime, dueTime, status, category, needsVerification, isVerified, xpReward, upVote, downVote, verificationImageLocation, taskDescription
     *                     respectively. So the data can be accessed in this format with serialNum at index = 0 and taskDescription at index = 15
     * @param listOfLists  the lists of all the tasks combined together as one
     *                     The Data from the listOfLists can be accessed from the first task in the database at index = 0 and the last task at index = n, where n can be anything
     */

    public static ArrayList<Task> getAllUserTasks (int userId)
    {
        ArrayList<Task> listOfLists = new ArrayList<> ();
        String sql = "select serialNum, title, priority, startDate, dueDate, startTime, dueTime, status, category, needsVerification, isVerified, xpReward, upVote, downVote, verificationImageLocation, taskDescription from Tasks" + userId;

        try (Connection con = DBconnection.getConnection ("group");
             PreparedStatement stat = con.prepareStatement (sql);

        )
        {
            ResultSet rs = stat.executeQuery (sql);

            while (rs.next ())
            {
                String parsedSerialNum = rs.getString ("serialNum");
                String parsedTitle = rs.getString ("title");
                String parsedPriority = rs.getString ("priority");
                LocalDate parsedStartDate = LocalDate.parse (rs.getString ("startDate"));
                LocalDate parsedDueDate = LocalDate.parse (rs.getString ("dueDate"));
                LocalTime parsedStartTime = LocalTime.parse (rs.getString ("startTime"));
                LocalTime parsedDueTime = LocalTime.parse (rs.getString ("dueTime"));
                Boolean parsedStatus = Boolean.parseBoolean (rs.getString ("status"));
                String parsedCategory = rs.getString ("category");
                Boolean parsedNeedsVerification = Boolean.parseBoolean (rs.getString ("needsVerification"));
                Boolean parsedIsVerified = Boolean.parseBoolean (rs.getString ("isVerified"));
                int parsedXpReward = rs.getInt ("xpReward");
                int parsedUpVote = rs.getInt ("upVote");
                int parsedDownVote = rs.getInt ("downVote");
                String parsedVerificationImageLocation = rs.getString ("verificationImageLocation");
                String parsedDescription = rs.getString ("taskDescription");
                ArrayList list = new ArrayList<> ();
                list.add (parsedSerialNum);
                list.add (parsedTitle);
                list.add (parsedPriority);
                list.add (parsedStartDate);
                list.add (parsedDueDate);
                list.add (parsedStartTime);
                list.add (parsedDueTime);
                list.add (parsedStatus);
                list.add (parsedCategory);
                list.add (parsedNeedsVerification);
                list.add (parsedIsVerified);
                list.add (parsedXpReward);
                list.add (parsedUpVote);
                list.add (parsedDownVote);
                list.add (parsedVerificationImageLocation);
                list.add (parsedDescription);

                listOfLists.addAll (list);

            }
            return listOfLists;

        } catch (SQLException e)
        {
            System.err.println (e);
            return listOfLists;
        }
    }

    //===========================================================================================================

    /**
     * @return the next id after the last generated id in the database
     */
    public static int groupIdGenerator()
    {
        String sqlLister = "SELECT * FROM groups";

        try (Connection con = getConnection ();
             Statement st = con.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery (sqlLister))
        {
            rs.last ();
            return rs.getInt (1) + 1;
        } catch (SQLException e)
        {
            e.printStackTrace ();
            return -1;
        }
    }

    /**
     * initializes the group in the database
     * @param groupId
     * @param groupName
     * @param adminId
     * @param image the image part is not functional
     */
    public static void groupInit (int groupId, String groupName, int adminId, ImageIcon image)
    {
        String membersTable;
        String categoriesTable;

        membersTable = "`" + membersTableName (groupId) + "`";
        categoriesTable = "`" + categoriesTableName (groupId) + "`";
        if (groupId != -1)
        {
            //adds the group
            String sqlGroupAdder = "INSERT INTO `groups` ( `id`, `name`, `admin_id`,`date_joined`, `image`) " +
                    "VALUES ( " + groupId + ",\'" + groupName + "\'," + adminId + ", CURRENT_TIMESTAMP, Null)";

            //creates the members table
            String sqlMemberMaker = "CREATE TABLE " +
                    "`" + DATABASE_NAME + "`." + membersTable +
                    " ( `id` INT NOT NULL , `score` INT NOT NULL ) ENGINE = InnoDB;";

            //creates the categories table
            String sqlCategoriesMaker = "CREATE TABLE " +
                    "`" + DATABASE_NAME + "`." + categoriesTable +
                    " ( `category` TEXT NOT NULL ) ENGINE = InnoDB;";

            try (Connection con = getConnection ();
                 PreparedStatement pst = con.prepareStatement (sqlGroupAdder))
            {
                //toDo setting image as blob
                //pst.setBlob (1, (Blob) image);
                pst.execute ();
                pst.executeUpdate (sqlMemberMaker);
                pst.executeUpdate ("ALTER TABLE `" + membersTableName (groupId)
                        + "` ADD PRIMARY KEY(`id`)");
                pst.executeUpdate (sqlCategoriesMaker);
                pst.executeUpdate ("ALTER TABLE `" + categoriesTableName (groupId)
                        + "` ADD PRIMARY KEY(`category`(500))");
            } catch (SQLException e)
            {
                e.printStackTrace ();
            }
        }
    }

    /**
     * transforms a database group object to a group object
     * @param groupId
     * @return a group object
     * @throws SQLException
     */
    public static Group getGroup (int groupId) throws SQLException
    {
        String sqlGroupRetrieve = "SELECT * FROM groups WHERE id =" + groupId;
        String sqlMemberRetrieve = "SELECT * FROM " + membersTableName (groupId);
        String sqlCategoryRetrive = "SELECT * FROM " + categoriesTableName (groupId);

        int id;
        String name;
        int adminId;
        ArrayList<Member> members;
        ArrayList<String> categories;
        ResultSet rs;

        rs = null;
        members = new ArrayList<Member> ();
        categories = new ArrayList<String> ();
        try (Connection con = getConnection ();
             Statement st = con.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY))
        {

            rs = st.executeQuery (sqlGroupRetrieve);
            rs.next ();
            id = rs.getInt (1);
            name = rs.getString (2);
            adminId = rs.getInt (3);

            rs = st.executeQuery (sqlMemberRetrieve);
            while (rs.next ())
            {
                members.add (new Member (rs.getInt (1), rs.getInt (2)));
            }

            rs = st.executeQuery (sqlCategoryRetrive);
            while (rs.next ())
            {
                categories.add (rs.getString (1));
            }

            return new Group (id, name, getUser (adminId), members, categories);
            //toDo
//            groupDetail.set (3, rs.getBlob (5));
        } catch (SQLException e)
        {
            e.printStackTrace ();
            return null;
        } catch (Exception e)
        {
            e.printStackTrace ();
            return null;
        } finally
        {
            rs.close ();
        }
    }

    /**
     * removes the group from the database
     * @param groupId
     */
    public static void groupRemover (int groupId)
    {
        String sqlGroupRemover = "DELETE FROM groups WHERE id =" + groupId;
        String sqlGroupMemberTableRemover = "DROP TABLE " + membersTableName (groupId);
        String sqlGroupCatRemover = "DROP TABLE " + categoriesTableName (groupId);

        try (Connection con = getConnection ();
             PreparedStatement pst = con.prepareStatement (sqlGroupRemover))
        {
            pst.execute ();
            pst.executeUpdate (sqlGroupMemberTableRemover);
            pst.executeUpdate (sqlGroupCatRemover);
        } catch (SQLException e)
        {
            e.printStackTrace ();
        }
    }

    /**
     * @param groupId
     * @return the corresponding member table name to a certain group
     */
    public static String membersTableName (int groupId)
    {
        return groupId + "_members";
    }

    public static void memberAdder (int groupId, int memberID)
    {
        String sqlMemberAdder = "INSERT INTO " + membersTableName (groupId) +
                " (id, score) VALUES ( ?, ?)";

        try (Connection con = getConnection ();
             PreparedStatement pst = con.prepareStatement (sqlMemberAdder))
        {
            pst.setInt (1, memberID);
            pst.setInt (2, 0);
            pst.execute ();
        } catch (SQLException e)
        {
            e.printStackTrace ();
        }
    }

    //toDO getMember
    public static String memberScore (int groupId, int memberId)
    {
        String sqlDataRetrieve = "SELECT * FROM " + membersTableName (groupId) +
                " WHERE id =" + memberId;

        try (Connection con = getConnection ();
             Statement st = con.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery (sqlDataRetrieve))
        {
            rs.next ();
            return rs.getString (2);
        } catch (SQLException e)
        {
            e.printStackTrace ();
            return null;
        }
    }

    //toDO
    public void memberScoreSetter (int groupId, int score)
    {

    }
    /**
     * @param memberID the user id of the member that should be removed
     * @param groupId
     */
    public static void memberRemover (int memberID, int groupId)
    {
        String sqlGroupMemberRemover = "DELETE FROM " + membersTableName (groupId) + " WHERE id ="
                + memberID;

        try (Connection con = getConnection ();
             PreparedStatement pst = con.prepareStatement (sqlGroupMemberRemover))
        {
            pst.execute ();
        } catch (SQLException e)
        {
            e.printStackTrace ();
        }
    }

    /**
     * @param groupId
     * @return the corresponding category table name to a certain group
     */
    public static String categoriesTableName (int groupId)
    {
        return groupId + "_categories";
    }

    /**
     * adds a category to that group's table name
     * @param groupId
     * @param category a string of the name of the category
     */
    public static void categoryAdder (int groupId, String category)
    {
        String sqlCatAdder = "INSERT INTO " + categoriesTableName (groupId) +
                " (category) VALUES (?)";

        try (Connection con = getConnection ();
             PreparedStatement pst = con.prepareStatement (sqlCatAdder))
        {
            pst.setString (1, category);
            pst.execute ();
        } catch (SQLException e)
        {
            e.printStackTrace ();
        }
    }

    /**
     * removes a category from the related group
     * @param groupId
     * @param category the string form of the category name
     */
    public static void categoryRemover (int groupId, String category)
    {
        String sqlGroupMemberRemover = "DELETE FROM " + categoriesTableName (groupId) +
                " WHERE category = '" + category + "'";

        try (Connection con = getConnection ();
             PreparedStatement pst = con.prepareStatement (sqlGroupMemberRemover))
        {
            pst.execute ();
        } catch (SQLException e)
        {
            e.printStackTrace ();
        }
    }
    //==============================================================
     /**
     * Creating the users table
     */
    public static void createUsersTable() {
        String sql = "CREATE TABLE username" + " ( ";
        sql += "id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,";
        sql += "user VARCHAR(1000), ";
        sql += "pass VARCHAR(1000), ";
        sql += "mail VARCHAR(1000), ";
        sql += "level INT(6), ";
        sql += "xp INT(6), ";
        sql += "mode VARCHAR(1000), ";
        sql += "streak INT(6), ";
        sql += "streakcount INT(6) ";
        sql += ")";

        try (Connection con = DBconnection.getConnection("users"); PreparedStatement stat = con.prepareStatement(sql);

        ) {
            stat.execute();

        } catch (SQLException e) {
            System.err.println(e);
            ;
        }
    }

    /**
     * add the group to the user's group table
     * 
     * @param userID
     * @param groupID
     */
    public static void addGroupID(int userID, int groupID) {

        String sql = "INSERT INTO `groups" + userID + "` VALUES (?)";

        try (Connection conn = DBconnection.getConnection("users");
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, groupID);
            stmt.execute();

        } catch (SQLException e) {
            System.err.println(e);
            e.printStackTrace();
            ;
        }
    }
    //TODO: getGroups

    /**
     * Create a groups table for each user
     * 
     * @param userID
     */
    public static void createGroupTable(int userID) {
        String sql = "CREATE TABLE groups" + userID + " ( ";
        sql += "id INT(6)";
        sql += ")";

        try (Connection con = DBconnection.getConnection("users"); PreparedStatement stat = con.prepareStatement(sql);

        ) {
            stat.execute();

        } catch (SQLException e) {
            System.err.println(e);
            ;
        }
    }


    public static void main (String[] args)
    {
//        DB.groupInit (1, "boomers", 2, null);
//        DB.memberAdder (1, 1);
//        DB.categoryAdder (1, "meth");
        DB.categoryRemover (1, "meth");
    }
}
