package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NormalizedIndexTable extends TableManager {

    private Map<String, Table> tableMap;
    private String primaryKey;
    public NormalizedIndexTable(Table baseTable){
        super(baseTable);
        this.primaryKey = baseTable.getTableKey();
        this.tableMap=super.getTableMap();
    }

    @Override
    protected Table createNewTable(Table baseTable,String newTableKey) {
        Table newTable = new Table(newTableKey);
        for (String key : baseTable.getDataStore().keySet()) {
            List<Data> data = baseTable.getDataByKey(key);
            for(Data d : data){
                String[] tempValues = d.getValue().split(",");
                for(String tempValue : tempValues){
                    if(tempValue.contains(newTableKey)){
                        String temp = baseTable.getTableKey() +": "+d.getKey();
                        newTable.storeData(new Data(tempValue,temp));
                    }
                }
            }
        }
        return newTable;
    }

    @Override
    public List<Data> getDataByKey( String key) {
        List<Data> tempData = new ArrayList<>();
        List<Data> data = new ArrayList<>();
        String[] keys = key.split(": ");
        for(Map.Entry<String, Table> table : tableMap.entrySet()){
            if (keys[0].equals(table.getKey())){
                tempData = table.getValue().getDataByKey(key);
            }
        }
        if(primaryKey.equals(key.split(": ")[0])){
            data = tempData;
        }
        else{
            for(Data d : tempData){
                data.add((Data) tableMap.get(primaryKey).getDataByKey(d.getValue()).toArray()[0]);
            }
        }

        return data;
    }

}
