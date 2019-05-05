package com.almundo.callcenter.model;

import com.google.gson.Gson;
import lombok.Data;

/**
 * Employee
 *
 * @author Lorenzo Pinango.
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
@Data
public class Employee {
    private Long id;
    private String fullName;
    private Enum<Status> status;

    public Employee(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
        this.status = Status.AVAILABLE;
    }

    public static enum Status {
        ONCALL, AVAILABLE;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Employee employee = (Employee) object;
        return this.id.equals(employee.getId());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
