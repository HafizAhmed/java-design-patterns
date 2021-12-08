package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TableManager {
    private Map<String, Table> tableMap;
    private Table baseTable;
    public TableManager(Table baseTable){
        tableMap= new HashMap<>();
        this.baseTable = baseTable;
        tableMap.put(baseTable.getTableKey(),baseTable);
    }
    public boolean addNewTable(String newTableKey){
        if(tableMap.containsKey(newTableKey)) {
            return false;
        }
        else {
            Table newTable = createNewTable(baseTable,newTableKey);
            tableMap.put(newTable.getTableKey(),newTable);
            return true;
        }
    }

    protected  Map<String, Table> getTableMap(){
        return tableMap;
    }
    protected Table createNewTable(Table baseTable,String newTableKey) {    return null;    }
    public Table getTableByKey(String key){ return tableMap.get(key);    }
    public List<Data> getDataByKey( String key) {    return null;    }
}
