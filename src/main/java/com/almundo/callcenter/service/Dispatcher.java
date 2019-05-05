package com.almundo.callcenter.service;

import java.util.Iterator;
import java.util.List;

import com.almundo.callcenter.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Distpatcher call
 *
 * @author Lorenzo Pinango.
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
@Service("dispatcher")
public class Dispatcher {

    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    @Qualifier("loadEmployees")
    private List<Employee> employees;
    @Value("${callCenter.waitOperatorAvailable}")
    private boolean waitOperatorAvailable;
    @Value("${callCenter.timeAnswerOperatorAvailable}")
    private int timeAnswerOperatorAvailable;

    private Employee findEmployeeAvailableByType(Class<?> type) {
        //Verifica si hay empleado disponible segun la clase enviado al metodo
        for (Iterator<Employee> iterator = this.employees.iterator(); iterator.hasNext();) {
            Employee employee = iterator.next();
            if (employee.getStatus().equals(Employee.Status.AVAILABLE) && employee.getClass().equals(type)) {
                return employee;
            }
        }
        return null;
    }

    private Employee findEmployeeAvailable() {
        //Se busca operador disponible
        Employee operator = findEmployeeAvailableByType(Operator.class);
        if (operator != null) {
            return operator;
        } else {
            // En caso de encontrar operador disponible busca supervisor disponible
            Employee supervisor = findEmployeeAvailableByType(Supervisor.class);
            if (supervisor != null) {
                return supervisor;
            } else {
                //En caso de no encontrar supervisor disponible busca director disponible
                Employee director = findEmployeeAvailableByType(Director.class);
                if (director != null) {
                    return director;
                }
            }
        }
        return null;
    }

    public void dispatchCall(Call call) throws InterruptedException {
        call.setStatus(StatusCall.ON_ASSIGNMENT);
        //Se busca operador disponible
        Employee employeeAvailable = findEmployeeAvailable();

        //En caso de no encontrar operador disponible
        if (employeeAvailable == null) {
            //Se valida si esta activo parametro para esperar operador disponible
            if (waitOperatorAvailable) {
                System.out.println("[INFO] Llamada " + call.getId() + " en espera por asignacion de operador");
                call.setStatus(StatusCall.ON_HOLD);
                while (employeeAvailable == null) {
                    waitOperatorAvailable(call);
                    employeeAvailable = findEmployeeAvailable();
                }
            } else {
                call.setStatus(StatusCall.EMPLOYEES_NOT_AVAILABLE);
                System.out.println("[WARNING] No hay operadores disponibles para atender la llamada " + call.getId());
            }
        }

        if (employeeAvailable != null) {
            call.setStatus(StatusCall.IN_PROGRESS);
            System.out.println("[INFO] Llamada " + call.getId() + " en progreso");
            //Se asigna operador a la llamada
            call.setEmployee(employeeAvailable);
            //Se adiciona la llamada al pool de tareas
            taskExecutor.execute(call);
        }
    }

    private void waitOperatorAvailable(Call call) {
        try {
            //Se duerme hilo segun tiempo parametrizado para volver a verificar si hay operadores disponibles
            Thread.sleep(timeAnswerOperatorAvailable * 1000);
        } catch (InterruptedException e) {
            call.setStatus(StatusCall.FINISH_FAILURE);
            System.out.println("[ERROR] Hilo interrupido llamada " + call.getId());
        }
    }

}

