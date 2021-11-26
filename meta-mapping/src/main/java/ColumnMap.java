import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

public class ColumnMap {
    private String columnName;
    private String fieldName;
    private Field field;
    private DataMap dataMap;
    private static final Logger logger = LogManager.getLogger(ColumnMap.class);

    public ColumnMap(String columnName, String fieldName, DataMap dataMap){
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.dataMap = dataMap;
        initField();
    }
    public String getColumnName(){
        return this.columnName;
    }
    public void setColumnName(String columnName){
        this.columnName = columnName;
    }
    public final String getFieldName(){
        return this.fieldName;
    }
    public void setFieldName(String fieldName){
        this.fieldName = fieldName;
    }
    public DataMap getDataMap(){
        return this.dataMap;
    }
    public void setDataMap(DataMap dataMap){
        this.dataMap = dataMap;
    }

    private void initField(){
        try{
            field = dataMap.getDomainClass().getDeclaredField(getFieldName());
            field.setAccessible(true);
        }catch (Exception e) {
            logger.error("unable to set up field: " + this.fieldName);
        }
    }
    public void setField(Object result, Object columnValue){
        try{
            this.field.set(result,columnValue);
        } catch(Exception e) {
            logger.error("Error in setting " + this.fieldName);
        }
    }
    public Object getValue(Object subject){
        try{
            return field.get(subject);
        } catch(Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
        return null;
    }

}
