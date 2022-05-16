/**
* For Classes that have score or XP
* @author Maher Athar Ilyas 
* @version 
*/
public interface Scoreable {

    //CONSTANTS

    int TIME_MULTIPLIER = 10; //per hour (per minute would require to use decimal which can give problems)
   
    //Mode Multipliers
    int STRICT_MODE = 3;
    int NON_STRICT_MODE = 2;
    int CAREFREE_MODE = 1;

    //Priority Multipliers
    int HIGH_PRIORITY = 3;
    int MEDIUM_PRIORITY = 2;
    int LOW_PRIORITY = 1;

    double STEAK_MULTIPLY = 1.1;
     

    //Methods
    void setScore(int score);
    int getScore();
    void resetScore();
}