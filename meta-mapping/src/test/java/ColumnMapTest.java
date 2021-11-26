import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ColumnMapTest {
    private ColumnMap columnMap;
    private DataMap dataMap;
    @Before
    public void initialize(){
        dataMap = new DataMap(Student.class,"student");
        columnMap = new ColumnMap("id","id",dataMap);
    }
    @Test
    public void getColumnNameTest(){
        assertEquals(columnMap.getColumnName(),"id");
    }
    @Test
    public void setColumnNameTest(){
        columnMap.setColumnName("name");
        assertEquals(columnMap.getColumnName(),"name");
    }
    @Test
    public void getFieldName(){
        assertEquals(columnMap.getFieldName(),"id");
    }
    @Test
    public void setFieldName(){
        columnMap.setFieldName("name");
        assertEquals(columnMap.getFieldName(),"name");
    }
    @Test
    public void getDataMap(){
        assertEquals(columnMap.getDataMap(),dataMap);
    }
    @Test
    public void setDataMap(){
        columnMap.setDataMap(null);
        assertEquals(columnMap.getDataMap(),null);
    }
    @Test
    public void setField(){
        Student s = new Student(2);
        columnMap.setField(s,1);
        assertEquals(s.getId(),1);
    }
    @Test
    public void getValue(){
        Student s = new Student(1);
        s.setFirstName("Tom");
        s.setLastName("Cat");
        s.setScore(60);
        assertEquals(columnMap.getValue(s),1);

    }
}
