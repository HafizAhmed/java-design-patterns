import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * DataMap class which is used to map object class to a table
 */
public class DataMap {
    private Class domainClass;
    private String tableName;
    private List<ColumnMap> columnMaps = new ArrayList<>();

    /**
     * constructor which creates a new dataMap object
     * @param domainClass object class
     * @param tableName table name
     */
    public DataMap(Class domainClass, String tableName){
        this.domainClass = domainClass;
        this.tableName = tableName;
    }

    /**
     * get the object class
     * @return object class
     */
    public Class getDomainClass(){
        return this.domainClass;
    }

    /**
     * get the table name
     * @return table name
     */
    public String getTableName(){
        return this.tableName;
    }

    /**
     * get the columnMaps of the table
     * @return list of columnMaps
     */
    public List<ColumnMap> getColumnMaps(){
        return columnMaps;
    }

    /**
     * add new column to columnMap List
     * @param columnName column name
     * @param fieldName field name
     */
    public void addColumn(String columnName,String fieldName){
        ColumnMap newColumn = new ColumnMap(columnName,fieldName,this);
        columnMaps.add(newColumn);
    }

    /**
     * get the iterator of the columnMap list
     * @return iterator
     */
    public Iterator<ColumnMap> getColumn(){
        return Collections.unmodifiableCollection(columnMaps).iterator();
    }

    /**
     *  the format string of sql statement that used to update columns in table
     * @return updateList string
     */
    public String updateList(){
        StringBuffer result = new StringBuffer(" SET ");
        Iterator tempIt = columnMaps.iterator();
        tempIt.next();
        for (Iterator it = tempIt; it.hasNext();) {
            ColumnMap columnMap = (ColumnMap) it.next();
            result.append(columnMap.getColumnName());
            result.append("=?,");
        }
        result.setLength(result.length()-1);
        return result.toString();
    }

    /**
     * the format string of sql statements that used to insert columns in table
     * @return insertList string
     */
    public String insertList(){
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < columnMaps.size(); i++) {
            result.append("?");
            result.append(",");
        }
        result.setLength(result.length()-1);
        return result.toString();
    }
}
