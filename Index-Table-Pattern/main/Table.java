package main;//package com.iluwatar.indextable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Main Table class stored data in a HashMap.
 */
public class Table {

    private final String tableKey;
    private final Map<String, List<Data>> dataStore;

    public Table(final String tableKey) {
        this.tableKey = tableKey;
        this.dataStore = new HashMap<>();
    }

    public void storeData(Data data) {
        String[] splitData = data.getValue().split(",");
        for (String string : splitData) {
            List<Data> list = new ArrayList<>();
            if(splitData.length==1){
                if(dataStore.containsKey(data.getKey())){
                    list = dataStore.get(data.getKey());
                }
                list.add(data);
                dataStore.put(data.getKey(),list);
            }
            if (string.contains(tableKey + ":")) {
                String[] subString = string.split(" ");
                int size = subString.length;
                if(dataStore.containsKey(tableKey +": " + subString[size-1])){
                    list = dataStore.get(tableKey +": " + subString[size-1]);
                }
                list.add(data);
                dataStore.put(string, list);
            }
        }
    }

    protected List<Data> getDataByKey( String key) {    return dataStore.get(key);    }

    public String getTableKey() {
        return tableKey;
    }

    protected Map<String, List<Data>> getDataStore(){
        return dataStore;
    }

    public int getTableSize(){
        return dataStore.size();
    }
}