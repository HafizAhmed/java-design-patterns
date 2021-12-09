package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TableManager {
    private Map<String, Table> tableMap;  // HashMap of Table key and Table
    private PrimaryTable primaryTable;
    public TableManager(PrimaryTable primaryTable){
        tableMap= new HashMap<>();
        this.primaryTable = primaryTable;
    }

    /**
     * method to create a new table based on key value specified
     * @param newTableKey representing the key on which new table is to be created
     * @return false when the table already exists
     * @return true when a new table is created based on provided key
     */
    public boolean addNewTable(String newTableKey){
        if(tableMap.containsKey(newTableKey)) {
            return false;
        }
        else {
            Table newTable = createNewTable(primaryTable,newTableKey);
            tableMap.put(newTable.getTableKey(),newTable);
            return true;
        }
    }

    protected  Map<String, Table> getTableMap(){
        return tableMap;
    }
    protected Table createNewTable(PrimaryTable baseTable,String newTableKey) {    return null;    }

    /**
     * method to retrieve data with given key value
     * @param key string value representing key of data
     * @return table whose identifying key is key
     */
    public Table getTableByKey(String key){ return tableMap.get(key);    }

    /**
     * method to retrieve data with given key value
     * @param key string value representing key of data
     * @return list of data whose key is key
     */
    public List<Data> getDataByKey( String key) {    return null;    }

    protected int getTableMapSize(){
        return tableMap.size();
    }
}
