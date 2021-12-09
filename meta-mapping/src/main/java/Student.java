/**
 * student object class
 */
public class Student {
    private final int id;
    private String firstName;
    private String lastName;
    private int score;

    /**
     * constructor which creates the student object
     * @param id
     */
    public Student(int id) {
        this.id = id;
    }

    /**
     * get the id of student
     * @return id
     */
    public int getId(){
        return id;
    }

    /**
     * get the first name of the student
     * @return first name
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * set the first name of the student
     * @param firstName
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /**
     * get the last name of the student
     * @return last name
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * set the last name of the student
     * @param lastName
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * get the score of the student
     * @return score
     */
    public int getScore(){
        return score;
    }

    /**
     * set the score of the student
     * @param score
     */
    public void setScore(int score){
        this.score = score;
    }
}
