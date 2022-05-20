import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * a  class representing a group of members(users) and related functions
 * @version 12/04/2021
 */
public class Group
{
    //properties

    //group related
    private String name;
    private int groupId;
    private Member admin;
    private ArrayList<String> categories;
    private ImageIcon groupImage;

    //member related
    private ArrayList<Member> members;
    private Member topScorer;
    private LeaderBoard leaderBoard;
    private GroupChat groupchat;


    //constructors

    /**
     * for making a new group and adding it to the database
     * @param name
     * @param admin
     * @param categories an arraylist of string categories of the group
     */
    public Group ( String name, User admin, ArrayList<Member> members, ArrayList<String> categories)
    {
        groupId = DB.groupIdGenerator();
        this.name = name;
        this.categories = categories;

        this.members = members;
        leaderBoard = new LeaderBoard (members, groupId, name);
        groupchat = new GroupChat (groupId);
        topScorer = null;

        DB.groupInit (DB.groupIdGenerator(), name, admin.getProfileID(), null);
    }

    /**
     * for constructing a group object by completely specifying the details
     * @param groupId
     * @throws Exception when a groupId is out of the index of the existing groups
     */
    public Group (int groupId, String name,
                  User admin, ArrayList<Member> members, ArrayList<String> categories) throws Exception
    {
        if (groupId >= DB.groupIdGenerator())
        {
            throw new Exception ("this group does not already exist");
        }
        else
        {
            this.groupId = groupId;
            this.name = name;
            this.categories = categories;
            this.admin = new Member (admin);
            this.members = members;
            leaderBoard = new LeaderBoard (members, groupId, name);
            topScorer = null;
            groupchat = new GroupChat (groupId);
        }

    }

    /**
     * constructs an existing group from the database
     * @param groupId
     * @throws SQLException
     */
    public Group (int groupId) throws SQLException
    {
        Group temp;

        temp = DB.getGroup (groupId);
        //not tested
        this.name = temp.getGroupName();
        this.categories = temp.getCategories();
        this.admin = new Member (temp.getAdmin());
        this.members = temp.getMembers();
        leaderBoard = new LeaderBoard (groupId, name);
        topScorer = leaderBoard.getTopScorer();
        //toDO groupChat constructor
        //groupchat = new GroupChat (groupId);
    }

    //methods
    public int getGroupID()
    {
        return groupId;
    }
    public String getGroupName()
    {
        return name;
    }
    public Member getTopScorer()
    {
        return leaderBoard.getTopScorer();
    }

    public ArrayList<Member> getMembers()
    {
        return members;
    }
    public ArrayList<String> getCategories()
    {
        return categories;
    }
    public GroupChat getGroupChat()
    {
        return groupchat;
    }
    public Member getAdmin()
    {
        return admin;
    }
    public void addUser ( User user)
    {
        Member newMember;

        newMember = new Member (user);
        members.add (newMember);
        leaderBoard.addMember (user);
        user.addGroup (this);

        DB.memberAdder (newMember.getProfileId(), groupId);
    }

    public void removeUser (User user)
    {
        members.removeIf ( Member -> user.getProfileID () == user.getProfileID());
        leaderBoard.removeMember (user);
        //toDo @Users
//        user.removeGroup(this);
        DB.memberRemover (user.getProfileID(), groupId);

    }

    //changed user input to member input
    public boolean isAdmin ( Member member)
    {
        return member.getProfileId() == admin.getProfileId();
    }
    //GUI
    public void setGroupImage (ImageIcon image)
    {
        groupImage = image;
        //toDo db method
    }
    public void addCategory (String category)
    {
        DB.categoryAdder (groupId, category);
        categories.add (category);
    }
    public void removeCategory (String category)
    {
        DB.categoryRemover (groupId, category);
        categories.remove ( category);
    }

    public void resetScores()
    {
        leaderBoard.resetScore();
    }
}
