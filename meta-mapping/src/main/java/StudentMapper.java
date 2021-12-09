import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * the student mapper which maps student objects to data rows in student table
 */
public class StudentMapper {
    private DataMap dataMap;
    private final static String url = "jdbc:mysql://localhost:3306/test";
    private final static String username = "root";
    private final static String password = "admin";
    private static final Logger logger = LogManager.getLogger(StudentMapper.class);
    private final static String exception = "Exception: ";

    /**
     * create the datamap and add columns to the datamap
     */
    protected void loadDataMap(){
        dataMap = new DataMap(Student.class, "student");
        dataMap.addColumn("id","id");
        dataMap.addColumn("firstname","firstName");
        dataMap.addColumn("lastname","lastName");
        dataMap.addColumn("score","score");
    }

    /**
     * create the table if the table does not exist
     */
    protected void createTable(){
        try(Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            ResultSet rs = connection.getMetaData().getTables(null,null,dataMap.getTableName(),new String[]{"TABLE"});
            ){
                if (!rs.next()) {
                    String sqlCrateTable = "CREATE TABLE " + dataMap.getTableName() + " " +
                            "(id INTEGER not NULL, " +
                            " firstname VARCHAR(255), " +
                            " lastname VARCHAR(255), " +
                            " score INTEGER, " +
                            " PRIMARY KEY ( id ))";
                    statement.executeUpdate(sqlCrateTable);
                }
        } catch (Exception e) {
            logger.error(exception + e.getMessage());
        }
    }

    /**
     * find the student with id key in student table and convert the student data row to student object
     * @param key id
     * @return the student object
     * @throws SQLException
     */
    public Student findStudentById(int key) throws SQLException {
        String sql = "SELECT * FROM " + dataMap.getTableName()+" WHERE id = ?";
        Student student = null;
        ResultSet rs = null;
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement stmt = connection.prepareStatement(sql);
            ){
            stmt.setInt(1,key);
            rs = stmt.executeQuery();
            rs.next();
            student = load(rs);

        }catch (Exception e) {
            logger.error(exception + e.getMessage());
        } finally{
            if (rs != null) {
                rs.close();
            }
        }
        return student;
    }

    /**
     * update the student table based on given student object
     * @param student
     */
    public void update(Student student){
        String sql = "UPDATE " + dataMap.getTableName()+ dataMap.updateList() + " WHERE id = ?";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement stmt = connection.prepareStatement(sql);
        ){
            int count = 1;
            Iterator tempIt = dataMap.getColumn();
            tempIt.next();
            for (Iterator it = tempIt; it.hasNext();){
                ColumnMap columnMap = (ColumnMap) it.next();
                stmt.setObject(count++,columnMap.getValue(student));
            }
            stmt.setInt(count,student.getId());
            stmt.executeUpdate();
        }catch (Exception e) {
            logger.error(exception + e.getMessage());
        }
    }

    /**
     * Convert the given student object to student data and insert the data into student table
     * @param student
     * @return given student id
     */
    public int insert(Student student){
        String sql = "INSERT INTO " + dataMap.getTableName() + " VALUES (" + dataMap.insertList() + ")";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement stmt = connection.prepareStatement(sql);
        ){
            stmt.setObject(1,student.getId());
            int count = 2;

            Iterator tempIt = dataMap.getColumn();
            tempIt.next();
            for (Iterator it = tempIt; it.hasNext();){
                ColumnMap columnMap = (ColumnMap) it.next();
                stmt.setObject(count++,columnMap.getValue(student));
            }
            stmt.executeUpdate();
        }catch (Exception e) {
            logger.error(exception + e.getMessage());
        }
        return student.getId();
    }

    /**
     * delete the student with id from the table
     * @param id
     */
    public void deleteById(int id){
        String sql = "DELETE FROM " + dataMap.getTableName() + " WHERE id=?";
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement stmt = connection.prepareStatement(sql);
        ){
            stmt.setInt(1,id);
            stmt.executeUpdate();
        }catch (Exception e) {
            logger.error(exception + e.getMessage());
        }
    }

    /**
     * delete the table
     */
    public void deleteTable(){
        String sql = "DROP TABLE " + dataMap.getTableName();
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement stmt = connection.prepareStatement(sql);
        ){
            stmt.executeUpdate();
        }catch (Exception e) {
            logger.error(exception + e.getMessage());
        }
    }
    private Student load(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        int key = rs.getInt("id");
        Student student = (Student)dataMap.getDomainClass().getDeclaredConstructor(int.class).newInstance(key);
        loadFields(rs,student);
        return student;
    }

    private void loadFields(ResultSet rs, Student student) throws SQLException {
        for (Iterator it = dataMap.getColumn(); it.hasNext();) {
            ColumnMap columnMap = (ColumnMap) it.next();
            Object columnValue = rs.getObject(columnMap.getColumnName());
            columnMap.setField(student,columnValue);
        }
    }
}
