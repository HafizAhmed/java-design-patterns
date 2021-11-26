import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DataMap {
    private Class domainClass;
    private String tableName;
    private List<ColumnMap> columnMaps = new ArrayList<>();

    public DataMap(Class domainClass, String tableName){
        this.domainClass = domainClass;
        this.tableName = tableName;
    }
    public Class getDomainClass(){
        return this.domainClass;
    }
    public String getTableName(){
        return this.tableName;
    }
    public List<ColumnMap> getColumnMaps(){
        return columnMaps;
    }
    public void addColumn(String columnName,String fieldName){
        ColumnMap newColumn = new ColumnMap(columnName,fieldName,this);
        columnMaps.add(newColumn);
    }
    public Iterator<ColumnMap> getColumn(){
        return Collections.unmodifiableCollection(columnMaps).iterator();
    }
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
