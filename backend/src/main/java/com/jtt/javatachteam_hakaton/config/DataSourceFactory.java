package com.jtt.javatachteam_hakaton.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public final class DataSourceFactory {
    private DataSourceFactory() {
    }

    public static DataSource create(AppConfig config) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.datasourceUrl());
        hikariConfig.setUsername(config.datasourceUsername());
        hikariConfig.setPassword(config.datasourcePassword());
        hikariConfig.setDriverClassName(config.datasourceDriver());
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setPoolName("jtt-hikari-pool");
        return new HikariDataSource(hikariConfig);
    }
}
