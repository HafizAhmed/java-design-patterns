package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Table class used to store data in a table form.
 */
public class Table {

    private final String tableSearchKey;
    private final Map<String, List<Data>> dataStore;

    /**
     * constructor for main.SecondaryTable class
     * @param tableKey key for the table
     */
    public Table(final String tableKey) {
        this.tableSearchKey = tableKey;
        this.dataStore = new HashMap<>();
    }

    /**
     * method to store data into the table
     * @param data data to be stored into the table
     */
    protected void storeData(Data data) {
        String[] splitDataValue = data.getValue().split(",");

        for (String string : splitDataValue) {
            List<Data> list = new ArrayList<>();
            //check for NormalizedIndexTable
            if(splitDataValue.length==1){
                if(dataStore.containsKey(data.getKey())){
                    list = dataStore.get(data.getKey());
                }
                list.add(data);
                dataStore.put(data.getKey(),list);
            }
            //check for DenormalizedIndexTable
            if (string.contains(tableSearchKey + ":")) {
                String[] subString = string.split(" ");
                int size = subString.length;
                if(dataStore.containsKey(tableSearchKey +": " + subString[size-1])){
                    list = dataStore.get(tableSearchKey +": " + subString[size-1]);
                }
                list.add(data);
                dataStore.put(string, list);
            }
        }
    }

    protected void removeData(Data data){
        String[] splitDataValue = data.getValue().split(",");

        for (String string : splitDataValue) {
            List<Data> list = new ArrayList<>();
            //check for NormalizedIndexTable
            if(splitDataValue.length==1){
                if(dataStore.containsKey(data.getKey())){
                    for (Data d : list){
                        if(!d.equals(data)){
                            list.add(data);
                        }
                    }
                }
                list.add(data);
                dataStore.put(data.getKey(),list);
            }
            //check for DenormalizedIndexTable
            if (string.contains(tableSearchKey + ":")) {
                String[] subString = string.split(": ");
                int size = subString.length;
                if(dataStore.containsKey(tableSearchKey +": " + subString[size-1])){
                    for(Data d: list){
                        if(!d.equals(data)){
                            list.add(data);
                        }
                    }
                }
                list.add(data);
                dataStore.put(string, list);
            }
        }
    }

    protected void updateData(Data oldData, Data newData) {
        this.removeData(oldData);
        this.storeData(newData);
    }

    protected List<Data> getDataByKey( String key) {    return dataStore.get(key);    }

    /**
     * method to get table's key
     * @return string representing key for the table
     */
    public String getTableKey() {
        return tableSearchKey;
    }

    protected Map<String, List<Data>> getDataStore(){
        return dataStore;
    }

    /**
     * method to get table size
     * @return size of the table representing count of rows in a table
     */
    public int getTableSize(){
        return dataStore.size();
    }
}