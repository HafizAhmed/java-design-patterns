package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DenormalizedIndexTable extends TableManager{

    private Map<String, Table> tableMap;
    public DenormalizedIndexTable(Table baseTable){
        super(baseTable);
        this.tableMap=super.getTableMap();
    }

    @Override
    protected Table createNewTable(Table baseTable,String newTableKey) {
        Table newTable = new Table(newTableKey);
        for (String key : baseTable.getDataStore().keySet()) {
            List<Data> data = baseTable.getDataByKey(key);
            for (Data d:data){
                newTable.storeData(d);
            }
        }
        return newTable;
    }

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
}
