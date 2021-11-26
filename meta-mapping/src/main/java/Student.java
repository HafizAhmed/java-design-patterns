public class Student {
    private final int id;
    private String firstName;
    private String lastName;
    private int score;
    public Student(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score = score;
    }
}
