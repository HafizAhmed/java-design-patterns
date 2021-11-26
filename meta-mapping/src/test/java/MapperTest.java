import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class MapperTest {
    private StudentMapper sm;
    @Before
    public void createTableAndRelationship(){
        sm = new StudentMapper();
        sm.loadDataMap();
        sm.createTable();
    }
    @After
    public void deleteTable() {
        sm.deleteTable();
    }
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
        assertEquals(check,null);
    }
}
