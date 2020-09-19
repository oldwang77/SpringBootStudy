package com.kuang;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot04DataApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        //查看一下默认的数据源：class com.zaxxer.hikari.HikariDataSource
        System.out.println(dataSource.getClass());

        // 获得数据库链接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        // xxxx Template:SpringBoot已经配置好的模版bean，拿来即用
        // JDBC Template
        // radis Template

        // 关闭链接
        connection.close();
    }
}
