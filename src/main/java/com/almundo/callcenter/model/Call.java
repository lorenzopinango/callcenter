package com.almundo.callcenter.model;

import com.google.gson.Gson;
import lombok.Data;

import java.util.Random;

/**
 * Call
 *
 * @author Lorenzo Pinango.
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
@Data
public class Call implements Runnable {

    private int id;
    private Client client;
    private int duration;
    private Employee employee;
    private StatusCall status;

    public Call(int id, Client client, int maxDuration, int minDuration) {
        Random random = new Random();
        this.id = id;
        this.client = client;
        this.duration = (random.nextInt(maxDuration - minDuration) + minDuration) * 1000;
        this.setStatus(StatusCall.INITIATED);
    }


    public void setEmployee(Employee employee) {
        employee.setStatus(Employee.Status.ONCALL);
        this.employee = employee;
    }

    @Override
    public void run() {
        System.out.println("[INFO] Inicio llamada "  + this.id + " con el empleado " + this.employee.getFullName() + " con una duracion de "
                + this.duration);
        try {
            Thread.sleep(9000);
            this.employee.setStatus(Employee.Status.AVAILABLE);
            this.setStatus(StatusCall.FINISH_OK);
            System.out.println("[INFO] Finalizo llamada "  + this.id + " del empleado " + this.employee.getFullName());
        } catch (InterruptedException e) {
            this.setStatus(StatusCall.FINISH_FAILURE);
            System.out.println("[ERROR] Hilo interrupido llamada " + this.id);
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
