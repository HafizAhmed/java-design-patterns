import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StudentMapper {
    private DataMap dataMap;
    private final static String url = "jdbc:mysql://localhost:3306/test";
    private final static String username = "root";
    private final static String password = "admin";
    private static final Logger logger = LogManager.getLogger(StudentMapper.class);
    private final static String exception = "Exception: ";
    protected void loadDataMap(){
        dataMap = new DataMap(Student.class, "student");
        dataMap.addColumn("id","id");
        dataMap.addColumn("firstname","firstName");
        dataMap.addColumn("lastname","lastName");
        dataMap.addColumn("score","score");
    }
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

    public Student load(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        int key = rs.getInt("id");
        Student student = (Student)dataMap.getDomainClass().getDeclaredConstructor(int.class).newInstance(key);
        //Student student = new Student(key);
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
}
