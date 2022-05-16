public class Member extends User
{
    private int id;
    private String name;
    private int score;

    public Member (int id, int score)
    {
        this.id = id;
        //toDo constructor
        name = "";
        this.score = score;
    }
    public Member ( User user)
    {
        super();
        this.id = user.getProfileID ();
        this.name = user.getUsername ();
        score = 0;
    }

    public int getScore()
    {
        return score;
    }

    public void incScore ( int i)
    {
        score += i;
    }

    public int getProfileId()
    {
        return id;
    }

    public void resetScores()
    {
        score = 0;
        //toDo DB method
    }

}
