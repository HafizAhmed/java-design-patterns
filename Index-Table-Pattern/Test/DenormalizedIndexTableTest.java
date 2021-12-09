package Test;

import main.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DenormalizedIndexTableTest {

    @Test
    public void insertSingleIntoPrimaryTable(){
        Data data = new Data("1","Customer_ID: 1,LastName: Smith,Town: Redmond");
        PrimaryTable primaryTable = new PrimaryTable("Customer_ID");
        TableManager tableManager = new DenormalizedIndexTable(primaryTable);
        tableManager.addNewTable("Customer_ID");
        primaryTable.insertData(data);
        assertEquals(1,primaryTable.getTableSize());
        assertTrue(tableManager.getDataByKey("Customer_ID: 1").toArray()[0].equals( new Data("1","Customer_ID: 1,LastName: Smith,Town: Redmond")));
    }

    @Test
    public void insertMultipleIntoPrimaryTable(){
        Data data1 = new Data("1","Customer_ID: 1,LastName: Smith,Town: Redmond");
        Data data2 = new Data("2","Customer_ID: 2,LastName: Jones,Town: Seattle");
        Data data3 = new Data("3","Customer_ID: 3,LastName: Robinson,Town: Portland");
        Data data4 = new Data("4","Customer_ID: 4,LastName: Brown,Town: Redmond");
        Data data5 = new Data("5","Customer_ID: 5,LastName: Smith,Town: Chicago");
        Data data6 = new Data("6","Customer_ID: 6,LastName: Green,Town: Redmond");
        Data data7 = new Data("7","Customer_ID: 7,LastName: Clarke,Town: Portland");
        Data data8 = new Data("8","Customer_ID: 8,LastName: Smith,Town: Redmond");
        Data data9 = new Data("9","Customer_ID: 9,LastName: Jones,Town: Chicago");
        Data data1000 = new Data("1000","Customer_ID: 1000,LastName: Clarke,Town: Chicago");
        List<Data> data = new ArrayList<>();
        PrimaryTable primaryTable = new PrimaryTable("Customer_ID");
        data.add(data1);
        data.add(data2);
        data.add(data3);
        data.add(data4);
        data.add(data5);
        data.add(data6);
        data.add(data7);
        data.add(data8);
        data.add(data9);
        data.add(data1000);
        for (Data d: data){
            primaryTable.insertData(d);
        }
        TableManager tableManager = new DenormalizedIndexTable(primaryTable);
        assertEquals(10,primaryTable.getTableSize());
        assertTrue(primaryTable.getDataByKey("1").equals( new Data("1","Customer_ID: 1,LastName: Smith,Town: Redmond")));
        assertTrue(primaryTable.getDataByKey("5").equals( new Data("5","Customer_ID: 5,LastName: Smith,Town: Chicago")));
        assertTrue(primaryTable.getDataByKey("9").equals( new Data("9","Customer_ID: 9,LastName: Jones,Town: Chicago")));
    }

    @Test
    public void createSecondaryTable(){
        Data data1 = new Data("1","Customer_ID: 1,LastName: Smith,Town: Redmond");
        Data data2 = new Data("2","Customer_ID: 2,LastName: Jones,Town: Seattle");
        Data data3 = new Data("3","Customer_ID: 3,LastName: Robinson,Town: Portland");
        Data data4 = new Data("4","Customer_ID: 4,LastName: Brown,Town: Redmond");
        Data data5 = new Data("5","Customer_ID: 5,LastName: Smith,Town: Chicago");
        Data data6 = new Data("6","Customer_ID: 6,LastName: Green,Town: Redmond");
        Data data7 = new Data("7","Customer_ID: 7,LastName: Clarke,Town: Portland");
        Data data8 = new Data("8","Customer_ID: 8,LastName: Smith,Town: Redmond");
        Data data9 = new Data("9","Customer_ID: 9,LastName: Jones,Town: Chicago");
        Data data1000 = new Data("1000","Customer_ID: 1000,LastName: Clarke,Town: Chicago");
        List<Data> data = new ArrayList<>();
        PrimaryTable primaryTable = new PrimaryTable("Customer_ID");
        data.add(data1);
        data.add(data2);
        data.add(data3);
        data.add(data4);
        data.add(data5);
        data.add(data6);
        data.add(data7);
        data.add(data8);
        data.add(data9);
        data.add(data1000);
        for (Data d: data){
            primaryTable.insertData(d);
        }

        TableManager tableManager = new DenormalizedIndexTable(primaryTable);


        tableManager.addNewTable("Town");
        Table secondaryTableByTown =  tableManager.getTableByKey("Town");
        assertEquals(secondaryTableByTown.getTableKey(),"Town");
        assertEquals(4,secondaryTableByTown.getTableSize());
        assertEquals(tableManager.getDataByKey("Town: Seattle").toArray()[0],
                new Data("2","Customer_ID: 2,LastName: Jones,Town: Seattle"));
        assertTrue(tableManager.getDataByKey("Town: Portland").size() == 2
                && tableManager.getDataByKey("Town: Portland").containsAll(Arrays.asList(new Data("3", "Customer_ID: 3,LastName: Robinson,Town: Portland"), new Data("7", "Customer_ID: 7,LastName: Clarke,Town: Portland")))
                &&  Arrays.asList(new Data("3", "Customer_ID: 3,LastName: Robinson,Town: Portland"), new Data("7", "Customer_ID: 7,LastName: Clarke,Town: Portland")).containsAll(tableManager.getDataByKey("Town: Portland")));
        assertTrue(tableManager.getDataByKey("Town: Chicago").size() == 3
                && tableManager.getDataByKey("Town: Chicago").containsAll(Arrays.asList(new Data("1000","Customer_ID: 1000,LastName: Clarke,Town: Chicago"),new Data("5","Customer_ID: 5,LastName: Smith,Town: Chicago"),new Data("9","Customer_ID: 9,LastName: Jones,Town: Chicago")))
                &&  Arrays.asList(new Data("1000","Customer_ID: 1000,LastName: Clarke,Town: Chicago"),new Data("5","Customer_ID: 5,LastName: Smith,Town: Chicago"),new Data("9","Customer_ID: 9,LastName: Jones,Town: Chicago")).containsAll(tableManager.getDataByKey("Town: Chicago")));
        assertTrue(tableManager.getDataByKey("Town: Redmond").size() == 4);


        tableManager.addNewTable("LastName");
        Table secondaryTableByLastName =  tableManager.getTableByKey("LastName");
        assertEquals(secondaryTableByLastName.getTableKey(),"LastName");
        assertEquals(6,secondaryTableByLastName.getTableSize());
        assertEquals(tableManager.getDataByKey("LastName: Green").toArray()[0],
                new Data("6","Customer_ID: 6,LastName: Green,Town: Redmond"));
        assertTrue(tableManager.getDataByKey("LastName: Jones").size() == 2
                && tableManager.getDataByKey("LastName: Jones").containsAll(Arrays.asList(new Data("2","Customer_ID: 2,LastName: Jones,Town: Seattle"),new Data("9","Customer_ID: 9,LastName: Jones,Town: Chicago")))
                &&  Arrays.asList(new Data("2","Customer_ID: 2,LastName: Jones,Town: Seattle"),new Data("9","Customer_ID: 9,LastName: Jones,Town: Chicago")).containsAll(tableManager.getDataByKey("LastName: Jones")));
        assertTrue(tableManager.getDataByKey("LastName: Smith").size() == 3
                && tableManager.getDataByKey("LastName: Smith").containsAll(Arrays.asList(new Data("1","Customer_ID: 1,LastName: Smith,Town: Redmond"), new Data("5","Customer_ID: 5,LastName: Smith,Town: Chicago"),new Data("8","Customer_ID: 8,LastName: Smith,Town: Redmond")))
                &&  Arrays.asList(new Data("1","Customer_ID: 1,LastName: Smith,Town: Redmond"),new Data("5","Customer_ID: 5,LastName: Smith,Town: Chicago"),new Data("8","Customer_ID: 8,LastName: Smith,Town: Redmond")).containsAll(tableManager.getDataByKey("LastName: Smith")));
        assertEquals(1,tableManager.getDataByKey("LastName: Robinson").size());
    }

    @Test
    public void duplicateSecondaryTable() {
        Data data1 = new Data("1", "Customer_ID: 1,LastName: Smith,Town: Redmond");

        List<Data> data = new ArrayList<>();
        PrimaryTable primaryTable = new PrimaryTable("Customer_ID");
        data.add(data1);
        primaryTable.insertData(data1);

        TableManager tableManager = new DenormalizedIndexTable(primaryTable);

        tableManager.addNewTable("Town");
        Table secondaryTableByTown = tableManager.getTableByKey("Town");
        assertEquals(secondaryTableByTown.getTableKey(), "Town");

        tableManager.addNewTable("LastName");
        Table secondaryTableByLastName = tableManager.getTableByKey("LastName");
        assertEquals(secondaryTableByLastName.getTableKey(), "LastName");

        assertFalse(tableManager.addNewTable("Town"));
        assertFalse(tableManager.addNewTable("LastName"));
    }

    @Test
    public void insertTestOnSecondaryTable(){
        Data data1 = new Data("1","Customer_ID: 1,LastName: Smith,Town: Redmond");
        Data data2 = new Data("2","Customer_ID: 2,LastName: Jones,Town: Seattle");
        Data data3 = new Data("3","Customer_ID: 3,LastName: Robinson,Town: Portland");
        Data data4 = new Data("4","Customer_ID: 4,LastName: Brown,Town: Redmond");
        Data data5 = new Data("5","Customer_ID: 5,LastName: Smith,Town: Chicago");
        Data data6 = new Data("6","Customer_ID: 6,LastName: Green,Town: Redmond");
        Data data7 = new Data("7","Customer_ID: 7,LastName: Clarke,Town: Portland");
        Data data8 = new Data("8","Customer_ID: 8,LastName: Smith,Town: Redmond");
        Data data9 = new Data("9","Customer_ID: 9,LastName: Jones,Town: Chicago");
        Data data1000 = new Data("1000","Customer_ID: 1000,LastName: Clarke,Town: Chicago");
        List<Data> data = new ArrayList<>();
        PrimaryTable primaryTable = new PrimaryTable("Customer_ID");
        data.add(data1);
        data.add(data2);
        data.add(data3);
        data.add(data4);
        data.add(data5);
        data.add(data6);
        data.add(data7);
        data.add(data8);
        data.add(data9);
        data.add(data1000);
        for (Data d: data){
            primaryTable.insertData(d);
        }

        TableManager tableManager = new DenormalizedIndexTable(primaryTable);

        tableManager.addNewTable("Town");
        Table secondaryTableByTown =  tableManager.getTableByKey("Town");
        assertEquals(secondaryTableByTown.getTableKey(),"Town");
        assertEquals(4,secondaryTableByTown.getTableSize());
        assertEquals(tableManager.getDataByKey("Town: Seattle").toArray()[0],
                new Data("2","Customer_ID: 2,LastName: Jones,Town: Seattle"));

        tableManager.addNewTable("LastName");
        Table secondaryTableByLastName =  tableManager.getTableByKey("LastName");
        assertEquals(secondaryTableByLastName.getTableKey(),"LastName");
        assertEquals(6,secondaryTableByLastName.getTableSize());
        assertEquals(tableManager.getDataByKey("LastName: Green").toArray()[0],
                new Data("6","Customer_ID: 6,LastName: Green,Town: Redmond"));

        primaryTable.insertData(new Data("10","Customer_ID: 10,LastName: Parker,Town: New York"));
        assertEquals(5,secondaryTableByTown.getTableSize());
        assertEquals(7,secondaryTableByLastName.getTableSize());
        assertEquals(tableManager.getDataByKey("Town: New York").toArray()[0],
                new Data("10","Customer_ID: 10,LastName: Parker,Town: New York"));
        assertEquals(tableManager.getDataByKey("LastName: Parker").toArray()[0],
                new Data("10","Customer_ID: 10,LastName: Parker,Town: New York"));
    }

    @Test
    public void updateTestOnSecondaryTable(){
        Data data1 = new Data("1","Customer_ID: 1,LastName: Smith,Town: Redmond");
        Data data2 = new Data("2","Customer_ID: 2,LastName: Jones,Town: Seattle");
        Data data3 = new Data("3","Customer_ID: 3,LastName: Robinson,Town: Portland");
        Data data5 = new Data("5","Customer_ID: 5,LastName: Smith,Town: Chicago");
        Data data6 = new Data("6","Customer_ID: 6,LastName: Green,Town: Redmond");
        List<Data> data = new ArrayList<>();
        PrimaryTable primaryTable = new PrimaryTable("Customer_ID");
        data.add(data1);
        data.add(data2);
        data.add(data3);
        data.add(data5);
        data.add(data6);
        for (Data d: data){
            primaryTable.insertData(d);
        }

        TableManager tableManager = new DenormalizedIndexTable(primaryTable);

        tableManager.addNewTable("Town");
        Table secondaryTableByTown =  tableManager.getTableByKey("Town");
        assertEquals(secondaryTableByTown.getTableKey(),"Town");
        assertEquals(4,secondaryTableByTown.getTableSize());
        assertEquals(tableManager.getDataByKey("Town: Seattle").toArray()[0],
                new Data("2","Customer_ID: 2,LastName: Jones,Town: Seattle"));

        tableManager.addNewTable("LastName");
        Table secondaryTableByLastName =  tableManager.getTableByKey("LastName");
        assertEquals(secondaryTableByLastName.getTableKey(),"LastName");
        assertEquals(4,secondaryTableByLastName.getTableSize());
        assertEquals(tableManager.getDataByKey("LastName: Robinson").toArray()[0],
                new Data("3","Customer_ID: 3,LastName: Robinson,Town: Portland"));

        primaryTable.updateData(new Data("6","Customer_ID: 6,LastName: Parker,Town: New York"));
        assertEquals(primaryTable.getDataByKey("6"), new Data("6","Customer_ID: 6,LastName: Parker,Town: New York"));
        assertEquals(5,secondaryTableByTown.getTableSize());
        assertEquals(5,secondaryTableByLastName.getTableSize());
        assertEquals(tableManager.getDataByKey("Town: New York").toArray()[0],
                new Data("6","Customer_ID: 6,LastName: Parker,Town: New York"));
        assertEquals(tableManager.getDataByKey("LastName: Parker").toArray()[0],
                new Data("6","Customer_ID: 6,LastName: Parker,Town: New York"));
    }

    @Test
    public void removeFromSecondaryTable(){
        Data data1 = new Data("1","Customer_ID: 1,LastName: Smith,Town: Redmond");
        Data data2 = new Data("2","Customer_ID: 2,LastName: Jones,Town: Seattle");
        Data data3 = new Data("3","Customer_ID: 3,LastName: Robinson,Town: Portland");
        Data data4 = new Data("4","Customer_ID: 4,LastName: Brown,Town: Redmond");
        Data data5 = new Data("5","Customer_ID: 5,LastName: Smith,Town: Chicago");
        Data data6 = new Data("6","Customer_ID: 6,LastName: Green,Town: Redmond");
        Data data7 = new Data("7","Customer_ID: 7,LastName: Clarke,Town: Portland");
        Data data8 = new Data("8","Customer_ID: 8,LastName: Smith,Town: Redmond");
        Data data9 = new Data("9","Customer_ID: 9,LastName: Jones,Town: Chicago");
        Data data1000 = new Data("1000","Customer_ID: 1000,LastName: Clarke,Town: Chicago");
        List<Data> data = new ArrayList<>();
        PrimaryTable primaryTable = new PrimaryTable("Customer_ID");
        data.add(data1);
        data.add(data2);
        data.add(data3);
        data.add(data4);
        data.add(data5);
        data.add(data6);
        data.add(data7);
        data.add(data8);
        data.add(data9);
        data.add(data1000);
        for (Data d: data){
            primaryTable.insertData(d);
        }

        TableManager tableManager = new DenormalizedIndexTable(primaryTable);


        tableManager.addNewTable("Town");
        Table secondaryTableByTown =  tableManager.getTableByKey("Town");
        assertEquals(secondaryTableByTown.getTableKey(),"Town");
        assertEquals(4,secondaryTableByTown.getTableSize());
        assertEquals(tableManager.getDataByKey("Town: Seattle").toArray()[0],
                new Data("2","Customer_ID: 2,LastName: Jones,Town: Seattle"));

        assertTrue(tableManager.getDataByKey("Town: Redmond").size() == 4);


        tableManager.addNewTable("LastName");
        Table secondaryTableByLastName =  tableManager.getTableByKey("LastName");
        assertEquals(secondaryTableByLastName.getTableKey(),"LastName");
        assertEquals(6,secondaryTableByLastName.getTableSize());
        assertEquals(tableManager.getDataByKey("LastName: Green").toArray()[0],
                new Data("6","Customer_ID: 6,LastName: Green,Town: Redmond"));
        primaryTable.removeData("1");
        assertFalse(tableManager.getDataByKey("LastName: Smith").size() == 3
                && tableManager.getDataByKey("LastName: Smith").containsAll(Arrays.asList(new Data("1","Customer_ID: 1,LastName: Smith,Town: Redmond"), new Data("5","Customer_ID: 5,LastName: Smith,Town: Chicago"),new Data("8","Customer_ID: 8,LastName: Smith,Town: Redmond")))
                &&  Arrays.asList(new Data("1","Customer_ID: 1,LastName: Smith,Town: Redmond"),new Data("5","Customer_ID: 5,LastName: Smith,Town: Chicago"),new Data("8","Customer_ID: 8,LastName: Smith,Town: Redmond")).containsAll(tableManager.getDataByKey("LastName: Smith")));
        assertFalse(tableManager.getDataByKey("Town: Redmond").size() == 4);
    }
}
