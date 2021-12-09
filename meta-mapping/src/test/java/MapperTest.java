import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
/**
 * Student Mapper Test
 */
public class MapperTest {
    private StudentMapper sm;

    /**
     * build the mapping relationship between object and table and create the table in database
     */
    @Before
    public void createTableAndRelationship(){
        sm = new StudentMapper();
        sm.loadDataMap();
        sm.createTable();
    }

    /**
     * delete the table from the database
     */
    @After
    public void deleteTable() {
        sm.deleteTable();
    }

    /**
     * insert a student object to table and find that student in table by id
     * @throws SQLException sql exceptions
     */
    @Test
    public void insertAndFindTest() throws SQLException {
        int id = 2;
        String firstname = "jerry";
        String lastname = "Mat";
        int score = 60;
        Student newStudent = new Student(id);
        newStudent.setFirstName(firstname);
        newStudent.setLastName(lastname);
        newStudent.setScore(score);
        sm.insert(newStudent);
        Student check = sm.findStudentById(id);
        assertEquals(check.getId(),id);
        assertEquals(check.getFirstName(),firstname);
        assertEquals(check.getLastName(),lastname);
        assertEquals(check.getScore(),score);
    }

    /**
     * insert a student object to table and update that student in table
     * @throws SQLException sql exceptions
     */
    @Test
    public void updateTest() throws SQLException {
        int id = 2;
        String firstname = "tom";
        String lastname = "Lee";
        int score = 100;

        Student student = new Student(id);
        student.setFirstName("jerry");
        student.setLastName("Mat");
        student.setScore(60);
        sm.insert(student);
        Student newStudent = new Student(id);
        newStudent.setFirstName(firstname);
        newStudent.setLastName(lastname);
        newStudent.setScore(score);
        sm.update(newStudent);
        Student check = sm.findStudentById(id);
        assertEquals(check.getId(),id);
        assertEquals(check.getFirstName(),firstname);
        assertEquals(check.getLastName(),lastname);
        assertEquals(check.getScore(),score);
    }

    /**
     * delete a student with id from table
     * @throws SQLException sql exceptions
     */
    @Test
    public void deleteTest() throws SQLException {
        int id = 2;
        String firstname = "jerry";
        String lastname = "Mat";
        int score = 60;
        Student newStudent = new Student(id);
        newStudent.setFirstName(firstname);
        newStudent.setLastName(lastname);
        newStudent.setScore(score);
        sm.insert(newStudent);
        sm.deleteById(id);
        Student check = sm.findStudentById(id);
        assertNull(check);
    }
}
