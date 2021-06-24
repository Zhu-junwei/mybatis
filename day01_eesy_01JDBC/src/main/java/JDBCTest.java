import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //通过驱动管理类获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eesy_spring?useSSL=false&serverTimeZone=Shanghai/Asia","root","123456");
            //定义sql语句 ?表示占位符
            String sql = "select * from account where id = ?";
            //获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            //设置参数，第一个参数为sql语句中参数的序号，第二个参数为设置的参数值
//            preparedStatement.setInt(1,1);
            preparedStatement.setLong(1,1);
            //向数据库发出sql执行查询，查询出结果集
            resultSet = preparedStatement.executeQuery();
            //遍历结果集
            while (resultSet.next()){
                System.out.println(resultSet.getString("id")+" "+resultSet.getString("name")+ " " + resultSet.getString("money"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            if (resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
    }
}
