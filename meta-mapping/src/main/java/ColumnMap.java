import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

/**
 * the column map is used to map table columns to object fields
 */
public class ColumnMap {
    private String columnName;
    private String fieldName;
    private Field field;
    private DataMap dataMap;
    private static final Logger logger = LogManager.getLogger(ColumnMap.class);

    /**
     * the constructor of columnMap
     * @param columnName corresponding column name
     * @param fieldName corresponding file name in object
     * @param dataMap corresponding dataMap
     */
    public ColumnMap(String columnName, String fieldName, DataMap dataMap){
        this.columnName = columnName;
        this.fieldName = fieldName;
        this.dataMap = dataMap;
        initField();
    }

    /**
     * get column name
     * @return String
     */
    public String getColumnName(){
        return this.columnName;
    }

    /**
     * set column name
     * @param columnName column name
     */
    public void setColumnName(String columnName){
        this.columnName = columnName;
    }

    /**
     * get field name
     * @return String
     */
    public final String getFieldName(){
        return this.fieldName;
    }

    /**
     * set field name
     * @param fieldName field name
     */
    public void setFieldName(String fieldName){
        this.fieldName = fieldName;
    }

    /**
     * get dataMap
     * @return DataMap
     */
    public DataMap getDataMap(){
        return this.dataMap;
    }

    /**
     * set dataMap
     * @param dataMap dataMap
     */
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

    /**
     * set the field of the object
     * @param result object
     * @param columnValue field value
     */
    public void setField(Object result, Object columnValue){
        try{
            this.field.set(result,columnValue);
        } catch(Exception e) {
            logger.error("Error in setting " + this.fieldName);
        }
    }

    /**
     * gte the field value of an object
     * @param subject target object
     * @return the field value
     */
    public Object getValue(Object subject){
        try{
            return field.get(subject);
        } catch(Exception e) {
            logger.error("Exception: " + e.getMessage());
        }
        return null;
    }
}
