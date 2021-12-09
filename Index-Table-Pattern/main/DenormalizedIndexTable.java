package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DenormalizedIndexTable extends TableManager{

    private Map<String, Table> tableMap;
    private PrimaryTable primaryTable;

    public DenormalizedIndexTable(PrimaryTable table){
        super(table);
        this.primaryTable = table;
        this.tableMap=super.getTableMap();
        this.primaryTable.setDenormalizedTableManager(this);
    }

    @Override
    protected Table createNewTable(PrimaryTable primaryTable,String newTableKey) {
        Table newTable = new Table(newTableKey);
        for (String key : primaryTable.getDataStore().keySet()) {
            Data data = primaryTable.getDataByKey(key);
            newTable.storeData(data);
        }
        return newTable;
    }

    /**
     * method to retrieve data with given key value
     * @param key string value representing key of data
     * @return list of data whose key is key
     */
    @Override
    public List<Data> getDataByKey( String key) {
        List<Data> data = new ArrayList<>();
        String[] keys = key.split(": ");
        for (Map.Entry<String, Table> table : tableMap.entrySet()) {
            if (keys[0].equals(table.getKey())) {
                data = table.getValue().getDataByKey(key);
            }
        }
        return data;
    }

    @Override
    protected int getTableMapSize(){
        return this.tableMap.size();
    }
}
