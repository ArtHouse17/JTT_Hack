package com.jtt.javatachteam_hakaton.api.routes;

import com.jtt.javatachteam_hakaton.config.AppConfig;
import com.jtt.javatachteam_hakaton.config.DataSourceFactory;
import com.jtt.javatachteam_hakaton.config.EntityManagerFactoryProvider;
import com.jtt.javatachteam_hakaton.repository.*;
import com.jtt.javatachteam_hakaton.service.AttemptService;
import com.jtt.javatachteam_hakaton.service.AuthService;
import com.jtt.javatachteam_hakaton.service.TaskService;
import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

final class RouteDependencies {
    private static EntityManagerFactory EMF;

    private static AttemptService ATTEMPT_SERVICE;
    private static AuthService AUTH_SERVICE;
    private static TaskService TASK_SERVICE;

    private RouteDependencies() {}

    // Ленивая инициализация базы данных
    private static EntityManagerFactory getEmf() {
        if (EMF == null) {
            AppConfig config = AppConfig.fromEnvironment();
            DataSource dataSource = DataSourceFactory.create(config);

            EMF = EntityManagerFactoryProvider.create(dataSource);
        }
        return EMF;
    }

    static AttemptService attemptService() {
        if (ATTEMPT_SERVICE == null) {
            EntityManagerFactory emf = getEmf();
            ATTEMPT_SERVICE = new AttemptService(
                    new AttemptRepository(emf),
                    new AttemptAnswerRepository(emf),
                    new TaskRepository(emf),
                    new UserRepository(emf),
                    new TaskOptionRepository(emf)
            );
        }
        return ATTEMPT_SERVICE;
    }

    static TaskService taskService() {
        if (TASK_SERVICE == null) {
            EntityManagerFactory emf = getEmf();
            TASK_SERVICE = new TaskService(
                    new TaskRepository(emf),
                    new TaskOptionRepository(emf),
                    new AttemptRepository(emf)
            );
        }
        return TASK_SERVICE;
    }

    static AuthService authService() {
        if (AUTH_SERVICE == null) {
            AUTH_SERVICE = new AuthService(new UserRepository(getEmf()));
        }
        return AUTH_SERVICE;
    }
}
