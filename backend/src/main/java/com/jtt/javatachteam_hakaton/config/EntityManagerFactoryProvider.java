package com.jtt.javatachteam_hakaton.config;

import com.jtt.javatachteam_hakaton.entity.Attempt;
import com.jtt.javatachteam_hakaton.entity.AttemptAnswer;
import com.jtt.javatachteam_hakaton.entity.SqlTaskConfig;
import com.jtt.javatachteam_hakaton.entity.Task;
import com.jtt.javatachteam_hakaton.entity.TaskOption;
import com.jtt.javatachteam_hakaton.entity.User;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;
import java.util.Properties;

public final class EntityManagerFactoryProvider {
    private EntityManagerFactoryProvider() {
    }

    public static EntityManagerFactory create(DataSource dataSource) {
        Properties properties = new Properties();
        properties.put(AvailableSettings.DATASOURCE, dataSource);
        properties.put(AvailableSettings.HBM2DDL_AUTO, "none");
        properties.put(AvailableSettings.SHOW_SQL, "false");
        properties.put(AvailableSettings.FORMAT_SQL, "false");
        properties.put(AvailableSettings.JAKARTA_TRANSACTION_TYPE, "RESOURCE_LOCAL");
        properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Task.class);
        configuration.addAnnotatedClass(TaskOption.class);
        configuration.addAnnotatedClass(Attempt.class);
        configuration.addAnnotatedClass(AttemptAnswer.class);
        configuration.addAnnotatedClass(SqlTaskConfig.class);

        return configuration.buildSessionFactory();
    }
}
