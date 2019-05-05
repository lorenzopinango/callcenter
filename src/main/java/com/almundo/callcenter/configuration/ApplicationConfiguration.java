package com.almundo.callcenter.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.almundo.callcenter.model.Director;
import com.almundo.callcenter.model.Employee;
import com.almundo.callcenter.model.Operator;
import com.almundo.callcenter.model.Supervisor;

/**
 * App Configuration
 *
 * @author Lorenzo Pinango.
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
@Configuration
public class ApplicationConfiguration {
    @Value("${callCenter.numberConcurrentCalls}")
    private int numberConcurrentCalls;
    @Value("${callCenter.queueCapacity}")
    private int queueCapacity;
    @Value("${callCenter.numberOperators}")
    private int numberOperators;
    @Value("${callCenter.numberSupervisor}")
    private int numberSupervisor;
    @Value("${callCenter.numberDirector}")
    private int numberDirector;

    private List<Employee> loadEmployees(String type) {
        List<Employee> employees = new ArrayList<>();
        switch (type) {
            case "operator":
                for (Integer i = 1; i <= numberOperators; i++) {
                    employees.add(new Operator(i.longValue(), type + i));
                }
                break;
            case "supervisor":
                for (Integer i = 1; i <= numberSupervisor; i++) {
                    employees.add(new Supervisor(i.longValue(), type + i));
                }
                break;
            case "director":
                for (Integer i = 1; i <= numberDirector; i++) {
                    employees.add(new Director(i.longValue(), type + i));
                }
                break;
            default:
                int size = 5;
                for (Integer i = 1; i <= size; i++) {
                    employees.add(new Employee(i.longValue(), type + i));
                }
                break;
        }


        return employees;
    }

    @Bean
    public List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.addAll(loadEmployees("operator"));
        employees.addAll(loadEmployees("supervisor"));
        employees.addAll(loadEmployees("director"));
        return employees;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(numberConcurrentCalls);
        executor.setMaxPoolSize(numberConcurrentCalls);
        executor.setQueueCapacity(queueCapacity);
        return executor;
    }
}
