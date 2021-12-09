package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NormalizedIndexTable extends TableManager {

    private Map<String, Table> tableMap;
    private String primaryKey;
    private PrimaryTable primaryTable;

    public NormalizedIndexTable(PrimaryTable primaryTable){
        super(primaryTable);
        this.primaryTable = primaryTable;
        this.primaryKey = primaryTable.getTableKey();
        this.tableMap=super.getTableMap();
        this.primaryTable.setNormalizedTableManager(this);
    }

    @Override
    protected Table createNewTable(PrimaryTable primaryTable,String newTableKey) {
        Table newTable = new Table(newTableKey);
        for (String key : primaryTable.getDataStore().keySet()) {
            Data data = primaryTable.getDataByKey(key);
            String[] tempValues = data.getValue().split(",");
                for(String tempValue : tempValues){
                    if(tempValue.contains(newTableKey)){
                        String temp = primaryTable.getTableKey() +": "+data.getKey();
                        newTable.storeData(new Data(tempValue,temp));
                    }
                }
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
        List<Data> tempData = new ArrayList<>();
        List<Data> data = new ArrayList<>();
        String[] keys = key.split(": ");
        for(Map.Entry<String, Table> table : tableMap.entrySet()){
            if (keys[0].equals(table.getKey())){
                tempData = table.getValue().getDataByKey(key);
            }
        }
        for(Data d : tempData){
            for(String s : d.getValue().split(",")){
                if(s.contains(primaryKey+":")){
                    String[] tempKey = s.split(": ");
                    data.add((Data) primaryTable.getDataByKey(tempKey[1]));
                }
            }
        }
        return data;
    }

    @Override
    protected int getTableMapSize(){
        return this.tableMap.size();
    }

}
