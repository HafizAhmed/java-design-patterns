package main;

import java.util.HashMap;
import java.util.Map;

public class PrimaryTable {

    private final String tablePrimaryKey;
    private final Map<String, Data> dataStore;
    private TableManager denormalizedTableManager;
    private TableManager normalizedTableManager;

    /**
     * constructor for main.Table class
     * @param tableKey key for the table
     */
    public PrimaryTable(final String tableKey) {
        this.tablePrimaryKey = tableKey;
        this.dataStore = new HashMap<>();
    }

    /**
     * method to insert a new data into the table
     * @param data new data to be stored into the table
     */
    public void insertData(Data data) {
        dataStore.put(data.getKey(), data);
        if(denormalizedTableManager!=null && denormalizedTableManager.getTableMapSize()!=0){
            for(Map.Entry<String, Table> indexTable : denormalizedTableManager.getTableMap().entrySet()){
                indexTable.getValue().storeData(data);
            }
        }
        if(normalizedTableManager!=null && normalizedTableManager.getTableMapSize()!=0){
            for(Map.Entry<String, Table> indexTable : normalizedTableManager.getTableMap().entrySet()){
                indexTable.getValue().storeData(data);
            }
        }
    }

    /**
     * method to remove a data from primary table with given primary key
     * @param key key representing primary key of the data to be removed
     */
    public void removeData(String key){
        Data data = dataStore.get(key);
        dataStore.remove(data.getKey());
        if(denormalizedTableManager!=null && denormalizedTableManager.getTableMapSize()!=0){
            for(Map.Entry<String, Table> indexTable : denormalizedTableManager.getTableMap().entrySet()){
                indexTable.getValue().removeData(data);
            }
        }
        if(normalizedTableManager!=null && normalizedTableManager.getTableMapSize()!=0){
            for(Map.Entry<String, Table> indexTable : normalizedTableManager.getTableMap().entrySet()){
                indexTable.getValue().removeData(data);
            }
        }
    }

    /**
     * method to update a data in primary table
     * @param newData representing data to be updated
     */
    public void updateData(Data newData){
        Data oldData = dataStore.get(newData.getKey());
        dataStore.put(newData.getKey(), newData);
        if(denormalizedTableManager!=null && denormalizedTableManager.getTableMapSize()!=0){
            for(Map.Entry<String, Table> indexTable : denormalizedTableManager.getTableMap().entrySet()){
                indexTable.getValue().updateData(oldData, newData);
            }
        }
        if(normalizedTableManager!=null && normalizedTableManager.getTableMapSize()!=0){
            for(Map.Entry<String, Table> indexTable : normalizedTableManager.getTableMap().entrySet()){
                indexTable.getValue().updateData(oldData, newData);
            }
        }
    }

    /**
     * method to search for data using primary key of the data
     * @param key primary key to search data in the table
     * @return data with the given primary key
     */

    public Data getDataByKey( String key) {    return dataStore.get(key);    }

    /**
     * method to get table's key
     * @return string representing key for the table
     */
    public String getTableKey() {
        return tablePrimaryKey;
    }

    protected Map<String, Data> getDataStore(){
        return dataStore;
    }

    /**
     * method to get table size
     * @return size of the table representing count of rows in a table
     */
    public int getTableSize(){
        return dataStore.size();
    }

    protected void setDenormalizedTableManager(TableManager tableManager){
        denormalizedTableManager = tableManager;
    }
    protected void setNormalizedTableManager(TableManager tableManager){
        normalizedTableManager = tableManager;
    }

}
