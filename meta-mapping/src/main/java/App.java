import java.sql.SQLException;

/**
 * Metadata Mapping pattern provides a simple way for programmers to map objects to database data.
 */
public class App {
    /**
     * a guideline of how to use the metadata mapping pattern
     * @param args input args
     * @throws SQLException sql exception
     */
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
