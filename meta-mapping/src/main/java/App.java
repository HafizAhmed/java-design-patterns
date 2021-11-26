import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        StudentMapper sm = new StudentMapper();
        sm.loadDataMap();
        sm.createTable();
        Student newStudent = new Student(1);
        newStudent.setFirstName("Tom");
        newStudent.setLastName("Cat");
        newStudent.setScore(100);
        sm.insert(newStudent);
        Student check = sm.findStudentById(1);
        check.setFirstName("Jerry");
        check.setFirstName("Mouse");
        check.setScore(60);
        sm.update(newStudent);
        sm.deleteById(1);
        sm.deleteTable();
    }
}
