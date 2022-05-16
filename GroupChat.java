import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * GroupChat Object
 * @author Maher Athar Ilyas
 * @version 
*/
public class GroupChat
{
    // Properties
    private ArrayList<Message> messages;
    private int groupID;
    private String tableName;

    // Constructors
    /**
     * Constructs a new table using the groupID to create a relevent Table name
     * eg. Table's name for groupID = 4  GroupChat4 
     * @param groupID 
     */
    public GroupChat (int groupID){

        this.groupID = groupID;
        messages = new ArrayList<>();
        tableName = "GroupChat" + groupID;

        //Creates a new Table
        createGroupChat();
    }

    /**
     * Initializes the GroupChat according to its database version
     */
    public GroupChat (String tableName){

        this.tableName = tableName;
        groupID =  Integer.parseInt(tableName.substring(9)) ;
    
        initialize();
    }


    // Methods

    /**
     * Adds a new Message to the GroupChat
     * @param newMessage
     */
    public void addMessage(Message newMessage) {
        messages.add(newMessage);
        addMessagetoDB(newMessage);
        save();
    }

    /**
     * Get methods for the list of Messages
     * @return ArrayList of messages
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }

    /**
     * Get messages according to an index
     * @return null if the index is invalid
     */
    public Message getMessage(int index) {

        if (index > messages.size()) {
            return null;
        }

        return messages.get(index);
    }

    private void save() {
        //todo
        //saves the recent most message to the database
    }

    
    /**
     * Creates a new GroupChat table in the group db
     *  eg GroupChat6
     */
    private void createGroupChat(){

        String sql = "CREATE TABLE " + tableName + " ( ";
        sql += "id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,";
        sql += "Username VARCHAR(100), ";
        sql += "Message VARCHAR(1000), ";
        sql += "Date date NOT NULL, ";
        sql += "Time time(6) NOT NULL, ";
        sql += "Image BLOB";
        sql += ")";

        try ( Connection con = DBconnection.getConnection("group");
              PreparedStatement stat = con.prepareStatement(sql);

        ) {
            stat.execute();

        } catch (SQLException e) {
            System.err.println(e);;
        }

    }

    /**
     * only works for text massages
     * @param message
     */
    private void addMessagetoDB(Message message){

        int userID = message.getSender().getProfileID();
        String sql;

       // if (message.getImage() == null) {
            sql = "INSERT INTO " + tableName + " (UserName, Message, Date, Time) "; 
            sql += ("VALUES ('" + userID +"', '" + message.getText() + "', '" + message.getSendDate() + "', '" + message.getSendTime() + "' )");
       // }
        // else{

        //     //Need to test
        //     FileInputStream fis = new FileInputStream("image");


        //     sql = "INSERT INTO " + tableName + " (UserName, Message, Date, Time, Image) "; 
        //     sql += ("VALUES ('" + userID + " '" + message.getText() + "', '" + message.getSendDate() + "', '" + message.getSendTime() + "', '" + fis.available() + "' )" ); 
        // }        
        

        try ( Connection con = DBconnection.getConnection("group");
             PreparedStatement stat = con.prepareStatement(sql);

       ) {

           stat.execute();

       } catch (SQLException e) {
           System.err.println(e);;
       }

    }

    /**
     * Inilializes messages according to data in DB
     */
    private void initialize() {
        
        try ( Connection con = DBconnection.getConnection("group");
        Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stat.executeQuery("SELECT * FROM " + tableName);
       )
        {           
            while (rs.next()) {
                
                Message temp;
                User user = DB.getUser( Integer.parseInt(rs.getString("UserID")));
                Group group = DB.getGroup(groupID);


                temp = new Message(user, rs.getString("Message"), group , rs.getDate("Date"), rs.getTime("Time"), null); //ImageIcon = null for now
                
                messages.add(temp);

            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    
    
}