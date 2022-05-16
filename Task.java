import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


/**
* Task class for tasks implimentation and all related functions
* @author Muhammad Ali Waris
* @version 
*/

//Another class should automatically make the Task Table when user registers
//Apparently in the main class, there should be a method comparing the local date and time with the due date and time for all the tasks
public class Task implements Notification {

    String title;
    int taskCount;
    String priority;
    LocalDate startDate;
    User taskOwner;
    LocalDate dueDate;
    LocalTime startTime;
    LocalTime dueTime;
    Boolean status;
    String category;
    Boolean needsVerification;
    Boolean isVerified;
    int xpReward;
    LocalDate dateRightNow = LocalDate.now();
    LocalTime timeRightNow = LocalTime.now();
    
    public Task(User user,String title, String priority, LocalDate startDate, LocalTime startTime, LocalDate dueDate, LocalTime dueTime, String category){
        this.setTitle(title);
        this.taskOwner = user;
        this.setPriority(priority);
        this.setCategory(category);
        this.setStartDate(startDate);
        this.setDueDate(dueDate);
        this.setStartTime(startTime);
        this.setDueTime(dueTime);
        this.taskCount++;
        if (DB.checkIfTaskTableExistsForUser(user.getProfileID()) == false) {
            createTaskTableDB(user.getProfileID());
        }

        addTask(user.getProfileID());
        
    }

    public int getScore() {
        return 0; //pending
    }

    public void resetScore() {
        //return 0; //pending
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public LocalDate getDate(){
        return dueDate;
    }

    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    public LocalTime getTime() {
        return dueTime;
    }

    public void setStartTime(LocalTime startTime){
        this.startTime = startTime;
    }

    public void setDueTime (LocalTime dueTime){
        this.dueTime = dueTime;
    }

    public String getTitle(){
        return this.title;
    }

    public Boolean getStatus(){
        return this.status;
    }

    public void setStatus(Boolean newStatus){
        this.status = newStatus;
    }

    public void setCategory(String newCategory){
        this.category = newCategory;
    }

    public String getCategory(){
        return this.category;
    }

    public void setPriority(String newPriority){
        this.priority = newPriority;
    }

    public String getPriority(){
        return this.priority;
    }

    public void notifyThis(){
        //proceed to notify events from here
    }


    public void addTask(int userID){
        
        DB.addToTaskTable(userID, this.title, this.priority, this.startDate, this.dueDate, this.startTime, this.dueTime, this.status, this.category, this.needsVerification, this.isVerified, this.xpReward);
    }

    public void createTaskTableDB(int userID){
        DB.createTaskTable(userID);  //This line is for a class that registers a new user
    }


    public void notifyUser(User user){

    }

    //Should be in the user class tho
    public void groupCheck(ArrayList<Group> groupName){
        //get array of all the groups the user is in
        for (int i  = 0 ; i < groupName.size(); i++) {
            User currentUserInstance = taskOwner;
            User topScorer = groupName.get(i).getTopScorer();

            for (int x = 0; x < groupName.get(i).getCategories().size() ; x++ ) {
                ArrayList<String> categories = groupName.get(i).getCategories();

                if (this.category == categories.get(x)) {
                    if (currentUserInstance == topScorer) {
                        this.needsVerification = true;
                        this.isVerified = false;
                    }
                }
            }
            
            
        }


    }   



}