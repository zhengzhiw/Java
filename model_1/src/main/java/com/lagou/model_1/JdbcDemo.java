package com.lagou.model_1;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDemo {
    public static void main (String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 通过数据库链接连接数据库
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo?serverTimezone=Asia/Shanghai&characterEncoding=utf8", "root",
                    "admin");
            // 自定义sql ？表示占位符
            String sql = "select * from student where age = ?";
            // 预加载sql
            preparedStatement = connection.prepareStatement(sql);
            // 如果有？，则设置参数，第一个参数表示第几个?,第二个参数为值，set*代表数据类型
            preparedStatement.setInt(1, 15);
            // 执行sql返回结果
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
