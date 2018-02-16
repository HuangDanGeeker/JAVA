package com.wang.jdbc.mysql;


import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by HuangDanGeeker on 2018/2/15.
 * use the SqlQuery.sql in the same directory to generate the 'student' table
 */
public class mysql {
    public static void main(String[] args) {
        String dirver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        //TODO change the 'user' and 'password' properties to fit your own mysql settings
        String user = "root";
        String password = "";
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            Class.forName(dirver);
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String sql = "select * from student";
            // 1.Statement
            resultSet = statement.executeQuery(sql);    //执行查询语句
            int id;
            String name;
            while(resultSet.next()){
                //get the db returned data orderd by index
                id = resultSet.getInt(1);
                name  = resultSet.getString(2);
                System.out.println("student{id="+id+", name="+name+"}");
            }
            statement.execute("delete from student where name = 'wang'");  //执行updata insert delete

            // 2.preparedStatement
            sql = "select * from student where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            //if we need and only need execute SQL query, use 'execute()',
            // if we need executed SQL query and get DB return valses, use 'executeQuery'
            boolean executed = preparedStatement.execute();
            System.out.println("preparedStatement excuted : "+executed);

            // 3. transaction
            connection.setAutoCommit(false);  // stop auto commit
            // ** execute some sql operation
            statement.execute("insert into student values(22, '22')");
            statement.execute("insert into student values(24, '24')");
            statement.execute("insert into student values(25, '25')");
            connection.commit(); //commit changes
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();  //rollback if commit accours errors
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
