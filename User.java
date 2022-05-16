import java.util.ArrayList;
/**
 * User class
 * @version 04.23.2021
 */
public class User {
    private String username, password, email, mode;
    private int level, xp, streak, streakFreezeCount, myID;
    private static int profileID = 0;
    private ArrayList<String> badges;
    private ArrayList<Group> groups;
    private ArrayList<Task> taskList;
    private ArrayList<Task> completedTasks;
    private boolean notifEnable;

    /**
     * Constructor for new user with saving the user to DB
     * @param username
     * @param password
     * @param email
     * @param mode
     * @param level
     * @param xp
     * @param streak
     * @param streakFreezeCount
     */
    public User(String username, String password, String email, String mode, int level, int xp, int streak,
            int streakFreezeCount) {
        profileID++;
        myID = profileID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mode = mode;
        this.level = level;
        this.xp = xp;
        this.streak = streak;
        notifEnable = true;
        this.streakFreezeCount = streakFreezeCount;
        DB.createGroupTable(myID);
        DB.add(myID, username, password, email, level, xp, streak, streakFreezeCount, mode);
    }

    public User()
    {

    }
    /**
     * Constructor for creating an existing user with the values from DB
     * @param username
     * @param password
     * @param email
     * @param mode
     * @param level
     * @param xp
     * @param streak
     * @param streakFreezeCount
     * @param temp
     */
    public User(String username, String password, String email, String mode, int level, int xp, int streak,
            int streakFreezeCount, boolean temp) {
        profileID++;
        myID = profileID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mode = mode;
        this.level = level;
        this.xp = xp;
        this.streak = streak;
        notifEnable = true;
        this.streakFreezeCount = streakFreezeCount;
        //DB.add(myID, username, password, email, level, xp, streak, streakFreezeCount, mode);
    }

    /**
     * Constructor for checking user/pass of an existing user and creating the user from DB
     * @param user
     * @param pass
     */
    public User(String user, String pass) {
        myID = DB.getID(user, pass);
        User a = DB.getUser(myID);
        username = a.username;
        password = a.password;
        email = a.email;
        mode = a.mode;
        level = a.level;
        xp = a.xp;
        streak = a.streak;
        streakFreezeCount = a.streakFreezeCount;
        badges = a.badges;
       // taskList = a.taskList;
        completedTasks = a.completedTasks;
        groups = a.groups;
        taskList = DB.getAllUserTasks(myID);
        //TODO initialize the tasklist/groups/badges into the DB


    }

    public User(User a) {

    }

    public void setPassword(String pass) {
        DB.changeValue(pass, myID, "pass");
        password = pass;
    }

    public String getPassword() {
        return DB.getValue(myID, "pass");
    }

    public void setUsername(String user) {
        DB.changeValue(user, myID, "user");
        username = user;
    }

    public String getUsername() {
        return DB.getValue(myID, "user");

    }

    public String getEmail() {
        return DB.getValue(myID, "mail");
    }

    public void setEmail(String mail) {
        DB.changeValue(mail, myID, "mail");
        email = mail;
    }

    public void incStreakFreeze() {
        streakFreezeCount++;
        DB.changeIntValue(streakFreezeCount, myID, "streakcount");
    }

    public void incStreak() {
        streak++;
        DB.changeIntValue(streak, myID, "streak");
    }

    public void addGroup(Group a) {
        groups.add(a);
        DB.addGroupID(myID, a.getGroupID());
    }

    public void leaveGroup(Group a) {
        groups.remove(a);
    }

    public void addBadge(String badge) {
        badges.add(badge);
    }

    public ArrayList<String> getBadges() {
        // Fix diagram
        return badges;
    }

    public void updateLevel() {
        level++;
        DB.changeIntValue(streak, myID, "level");
    }

    public int getProfileID() {
        // Fix diagram
        return myID;
    }

    public void addTask(Task a) {
        taskList.add(a);
    }

    public void removeTask(Task a) {
        taskList.remove(a);
    }

    public void completeTask(Task a) {
        completedTasks.add(a);
        removeTask(a);
    }

    public void changeNotif(boolean preference) {
        notifEnable = preference;
    }
    public int getStreak(){
        return streak;
    }
    public int getStreakFreeze(){
        return streakFreezeCount;
    }
}
