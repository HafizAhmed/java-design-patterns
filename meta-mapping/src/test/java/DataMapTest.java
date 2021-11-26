import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class DataMapTest {
    DataMap dataMap;
    @Before
    public void createDataMap(){
        dataMap = new DataMap(Student.class, "student");
    }
    @Test
    public void getDomainClassTest(){
        assertEquals(dataMap.getDomainClass(),Student.class);
    }
    @Test
    public void getTableNameTest(){
        assertEquals(dataMap.getTableName(),"student");
    }
    @Test
    public void addAndGetColumnTest(){
        String[] columnName = new String[]{"id","firstname","lastname","score"};
        String[] fieldName = new String[]{"id","firstName","lastName","score"};
        dataMap.addColumn(columnName[0],fieldName[0]);
        dataMap.addColumn(columnName[1],fieldName[1]);
        dataMap.addColumn(columnName[2],fieldName[2]);
        dataMap.addColumn(columnName[3],fieldName[3]);

        int index = 0;
        for (Iterator it = dataMap.getColumn(); it.hasNext();){
            ColumnMap columnMap = (ColumnMap) it.next();
            assertEquals(columnMap.getColumnName(),columnName[index]);
            assertEquals(columnMap.getFieldName(),fieldName[index]);
            assertEquals(columnMap.getDataMap(),dataMap);
            index+=1;
        }
    }
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
