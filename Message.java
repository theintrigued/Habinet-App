import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * A message from user for a groupChat
 * @author Maher Athar Ilyas 
 * @version 
*/
public class Message implements Notification
{
    // Properties
    private String text;
    private ImageIcon image;
    private User sender;
    private Time sendTime; 
    private Date sendDate;
    private ArrayList<User> taggedUsers;
    private Group group;
    
    // Constructors
    /**
     * Creates New Message with text
     * @param sender
     * @param text
     * @param group
     */
    public Message(User sender, String text, Group group){

        this.text = text;
        image = null;
        this.sender = sender;
        sendTime = new Time (System.currentTimeMillis());
        sendDate = new Date (System.currentTimeMillis());
        this.group = group;

        findTaggedUsers(text);
        sendMessage();
    }

    /**
     * Initializes an existing Message
     * @param sender
     * @param text
     * @param group
     * @param date
     * @param time
     */
    public Message(User sender, String text, Group group, Date date, Time time, ImageIcon image){

        this.text = text;
        image = null;
        this.sender = sender;
        sendTime = time;
        sendDate = date;
        this.group = group;
        this.image = image;

        findTaggedUsers(text);
        sendMessage();
    }

    /**
     * New Message with image
     * @param sender
     * @param image
     * @param group
     */
    public Message(User sender, ImageIcon image, Group group){

        text = "";
        this.image = image;
        this.sender = sender;
        this.group = group;
        taggedUsers = new ArrayList<>();

        //Current Date and Time
        sendTime = new Time (System.currentTimeMillis());
        sendDate = new Date (System.currentTimeMillis());

        findTaggedUsers(text);

        sendMessage();
    }

    // Methods

    /**
     * Get image with the message
     * returns null if the message is a text message
     */
    public ImageIcon getImage() {
        return image;
    }

    /**
     * Get message's text
     */
    public String getText() {
        return text;
    }

    /**
     * Get message's Sender
     */
    public User getSender() {
        return sender;
    }

    /**
     * Get message's sendDate
     */
    public Date getSendDate(){
        return sendDate;
    }

    /**
     * Get message's sendTime
     */
    public Time getSendTime(){
        return sendTime;
    }

    /**
     * Get message's tagged User
     * null if theres no valid User tagged
     */
    public ArrayList<User> getTaggedUsers(){
        return taggedUsers;
    }

    /**
     * Adds this message to the GroupChat of the group
     */
    public void sendMessage(){
        //Group class should have a getGroupChat Method
        group.getGroupChat().addMessage(this);
    }

    /**
     * Searches the text for the taggedUsers
     * initializes the taggedUsers property
     * calls notifyUser(this) for the taggedUsers
     */
    public void findTaggedUsers(String text){

        // (1) taggedUser = null if the message is an image message
        if (text.length() <= 1) {
            return;
        }

        //ArrayList because there can be multiple tagged Users
        ArrayList<String> taggedUserName = new ArrayList<>();

        // (2) Searches the whole message for @ symbol and stores string as username after @
        for (int i = 0; i < text.length() ; i++) {

            if (text.charAt(i) == '@') {
                
                int endIndex = i;
                while (text.charAt( endIndex + 1) != ' ') {
                    endIndex++;
                }
                //adds the Username to the ArrayList
                taggedUserName.add( text.substring( i + 1, endIndex + 1) );
                i= endIndex;
            }
        }

        //how to search the user from username now

        // (3) Searches the members from the group and adds the taggedUsers to the ArrayList
        ArrayList<Member> members = group.getMembers();

        //For each member
        for (int j = 0; j < members.size(); j++) {

            //for each TaggedUsername
            for (int s = 0; s < taggedUserName.size(); s++) {

                //if same Username than adds the User to taggedUser ArrayList
                if ( members.get(j).getUsername().equals(taggedUserName.get(s))) {

                    User tagged = members.get(j);
                    taggedUsers.add(tagged);

                    //Since user is tagged he/she should be notified
                    notifyUser(tagged);
                }
            
            }  
        }

    }

    /**
     * Notifies each taggedUser
     */
    public void notifyUser(User user) {

        String notification = "";

        notification += "You have been tagged in a new Message by * " + sender.getUsername() + " * ";
        
        DB.addNotification(user.getProfileID(), notification, "YELLOW", "GroupChat" + group.getGroupID());
        
    }
    
}