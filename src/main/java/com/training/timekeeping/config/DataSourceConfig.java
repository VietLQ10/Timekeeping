package com.training.timekeeping.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
//public class DataSourceConfig {
//
//    @Bean
//    public DataSource dataSource() {
//        DataSourceBuilder builder = DataSourceBuilder.create();
//        builder.url("jdbc:mysql://localhost/DB_Timekeeping");
//        builder.username("root");
//        builder.password("Viet@1234");
//        return builder.build();
//    }
//}
