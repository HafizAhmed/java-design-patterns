import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.assertEquals;

/**
 * DataMap Test
 */
public class DataMapTest {
    DataMap dataMap;

    /**
     * initialize the dataMap
     */
    @Before
    public void createDataMap(){
        dataMap = new DataMap(Student.class, "student");
    }

    /**
     * test the get domain class
     */
    @Test
    public void getDomainClassTest(){
        assertEquals(dataMap.getDomainClass(),Student.class);
    }

    /**
     * test the get table name
     */
    @Test
    public void getTableNameTest(){
        assertEquals(dataMap.getTableName(),"student");
    }

    /**
     * test the add and get column into/from the columnMap List
     */
    @Test
    public void addAndGetColumnTest(){
        String[] columnName = new String[]{"id","firstname","lastname","score"};
        String[] fieldName = new String[]{"id","firstName","lastName","score"};
        dataMap.addColumn(columnName[0],fieldName[0]);
        dataMap.addColumn(columnName[1],fieldName[1]);
        dataMap.addColumn(columnName[2],fieldName[2]);
        dataMap.addColumn(columnName[3],fieldName[3]);

        int index = 0;
        for (Iterator<ColumnMap> it = dataMap.getColumn(); it.hasNext();){
            ColumnMap columnMap = it.next();
            assertEquals(columnMap.getColumnName(),columnName[index]);
            assertEquals(columnMap.getFieldName(),fieldName[index]);
            assertEquals(columnMap.getDataMap(),dataMap);
            index+=1;
        }
    }

    /**
     * test the update sql statement string
     */
    @Test
    public void updateListTest(){
        String[] columnName = new String[]{"id","firstname","lastname","score"};
        String[] fieldName = new String[]{"id","firstName","lastName","score"};
        dataMap.addColumn(columnName[0],fieldName[0]);
        dataMap.addColumn(columnName[1],fieldName[1]);
        dataMap.addColumn(columnName[2],fieldName[2]);
        dataMap.addColumn(columnName[3],fieldName[3]);
        assertEquals(dataMap.updateList(), " SET firstname=?,lastname=?,score=?");
    }

    /**
     * test the insert sql statement string
     */
    @Test
    public void insertListTest(){
        String[] columnName = new String[]{"id","firstname","lastname","score"};
        String[] fieldName = new String[]{"id","firstName","lastName","score"};
        dataMap.addColumn(columnName[0],fieldName[0]);
        dataMap.addColumn(columnName[1],fieldName[1]);
        dataMap.addColumn(columnName[2],fieldName[2]);
        dataMap.addColumn(columnName[3],fieldName[3]);
        assertEquals(dataMap.insertList(), "?,?,?,?");
    }
}
