import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
/**
 * ColumnMap Test
 */
public class ColumnMapTest {
    private ColumnMap columnMap;
    private DataMap dataMap;

    /**
     * initialize the dataMap and columnMap
     */
    @Before
    public void initialize(){
        dataMap = new DataMap(Student.class,"student");
        columnMap = new ColumnMap("id","id",dataMap);
    }

    /**
     * test the get column name
     */
    @Test
    public void getColumnNameTest(){
        assertEquals(columnMap.getColumnName(),"id");
    }

    /**
     * test the set column name
     */
    @Test
    public void setColumnNameTest(){
        columnMap.setColumnName("name");
        assertEquals(columnMap.getColumnName(),"name");
    }

    /**
     * test the get field name
     */
    @Test
    public void getFieldName(){
        assertEquals(columnMap.getFieldName(),"id");
    }

    /**
     * test the set field name
     */
    @Test
    public void setFieldName(){
        columnMap.setFieldName("name");
        assertEquals(columnMap.getFieldName(),"name");
    }

    /**
     * test the get dataMap
     */
    @Test
    public void getDataMap(){
        assertEquals(columnMap.getDataMap(),dataMap);
    }

    /**
     * test the set dataMap
     */
    @Test
    public void setDataMap(){
        columnMap.setDataMap(null);
        assertNull(columnMap.getDataMap());
    }

    /**
     * test the set field in object class
     */
    @Test
    public void setField(){
        Student s = new Student(2);
        columnMap.setField(s,1);
        assertEquals(s.getId(),1);
    }

    /**
     * test the get field value in object class
     */
    @Test
    public void getValue(){
        Student s = new Student(1);
        s.setFirstName("Tom");
        s.setLastName("Cat");
        s.setScore(60);
        assertEquals(columnMap.getValue(s),1);
    }
}
