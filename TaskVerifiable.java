import javax.swing.ImageIcon;

/**
* TaskVerifying class for verifying the tasks if the user who completes it is a top scorer
* @author Muhammad Ali Waris
* @version 
*/

public class TaskVerifiable extends Task {
    int upVote;
    int downVote;
    ImageIcon imageVerify;
    String taskDescription;

    //THis class cannot initiate the Task...
    // Need to do something for the error
    
    public TaskVerifiable(){
        this.upVote = 0;
        this.downVote = 0;
        super.status = false;
    }

    public void incUpVotes(){
        this.upVote++;
    }

    public int getUpVotes(){
        return this.upVote;
    }

    public void incDownVotes(){
        this.downVote++;
    }

    public boolean getStatus(){
        return super.status;
    }   

    public void setStatus(boolean newStatus){
        this.status = newStatus;
    }

    public void setImage(ImageIcon newImage){
        this.imageVerify = newImage;
    }

    public void setDescription(String description){
        this.taskDescription = description;
    }

    public String getDescription(){
        return this.taskDescription;
    }

}