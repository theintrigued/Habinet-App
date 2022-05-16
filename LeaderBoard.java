
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;


/**
 * LeaderBoard for group
 * @version 
*/
public class LeaderBoard  
{
    // Properties
    private Member topScorer;
    private Date startDate;
    private Time startTime;
    private ArrayList<Member> members;
    private int groupID;
    private String groupName;

    // Constructors
    /**
     * Creates a group leaderboard using the score of each member
     * startDate and startTime are initialized according to current time
     * @param members list from the group
     * @param groupName
     */
    public LeaderBoard(ArrayList<Member> members, int groupID, String groupName){
        
        startDate = new Date(System.currentTimeMillis());
        startTime = new Time(System.currentTimeMillis());
        this.members = members;
        this.groupID = groupID;
        this.groupName = groupName;

        if (members.size() >= 1) {
            topScorer = members.get(0);
        }
        else{
            topScorer = null;
        }
        
    }

    /**
     * For existing LeaderBoard
     * @throws SQLException
     */
    public LeaderBoard(int groupID, String groupName) throws SQLException{        

        startDate = new Date(System.currentTimeMillis());
        startTime = new Time(System.currentTimeMillis());

        this.members = DB.getGroup(groupID).getMembers();
        this.groupID = groupID;
        this.groupName = groupName;

        if (members.size() >= 1) {
            topScorer = members.get(0);
        }
        else{
            topScorer = null;
        }
        
        sortLeaderBoard(false);        
    }



    // Methods

    /**
     * Get the topScorer of a Group
     * @return the first element of Members list
     */
    public Member getTopScorer() {

        topScorer = members.get(0);
        return topScorer;
    }

    /**
     * Get the ordered ArrayList of members
     * @return
     */
    public ArrayList<Member> getMembers() {
        sortLeaderBoard(true);
        return members;
    }

    /**
     * Sorts the members ArrayList according to the score
     * Starting from the topscorer to the bottom scorer
     * @param notify true if changes in Leaderboard should be notified
     * if notify is true then all Users are notified if their ranks are changed
     */
    public void sortLeaderBoard(boolean notify){

        String notification;

        for (int i = 0; i < members.size() -1; i++) {
            
            for (int j = 1 ; j < members.size(); j++) {
                
                if (members.get(i).getScore() < members.get(j).getScore()  ) {
                    
                    if (notify) {
                        
                        //Notification for the User who goes UP in rank
                        notification = "Congratulations!! you have surpassed " + members.get(i).getUsername() + " in " + groupName + " Group's Leader Board.";
                        DB.addNotification(((User) members.get(j)).getProfileID(), notification, "GREEN", "Group" + groupID);

                        //Notification for the User who goes DOWN in rank
                        notification = "Unfortunately, " + members.get(j).getUsername() + " has surpassed in Leaderboard of " + groupName+ " Group.";
                        DB.addNotification(((User) members.get(i)).getProfileID(), notification, "RED", "Group" + groupID);
                    }


                    //Swaps the elements
                    Member temp = members.get(j);
                    members.set(j, members.get(i));
                    members.set(i, temp);

                }
            }
        }
        //Notifing TopScorer
        if (members.size() > 0 && notify) {

            notification = "Congratulations!! you are currently the TOPSCORER of " + groupName + " Group.";
            DB.addNotification(members.get(0).getProfileID(), notification, "GREEN" , "Group" + groupID );
        }
        
    }

    /**
     * Adds a User to the LeaderBoard
     * creates a new Member within the method and adds it
     * @param newMember a User to be added
     */
    public void addMember(User newMember) {

        members.add(new Member(newMember));

    }

    /**
     * Removes a user Member from the members list
     * @param removeMember the User/Member object that needs to be deleted
     */
    public void removeMember(User removeMember) {

        for (int i = 0; i < members.size(); i++) {
            //Removes the member with the same profileID
            if (members.get(i).getProfileID() == (removeMember.getProfileID())) {
                members.remove(i);
            }
        }
    }

    /**
     * Resets the score for all members in the ArrayList
     * calls resetDate
     */
    public void resetScore(){

        for (int i = 0; i < members.size(); i++) {
            members.get(i).resetScores();
        }

        resetDate();
    }

    /**
     * Resets the Date and Time for the leaderBoard to Current Time and Date
     * call only if the current date and time is greater than a week from the previous
     * date and time.
     */
    public void resetDate(){        
        
        startDate = new Date(System.currentTimeMillis());
        startTime = new Time(System.currentTimeMillis());
    }

    /**
     * Get method for StartDate
     */
    public Date getStartDate(){
        return startDate;
    }

     /**
     * Get method for StartTime
     */
    public Time getStartTime(){
        return startTime;
    }

}
