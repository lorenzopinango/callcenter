package com.almundo.callcenter.model;

import com.google.gson.Gson;
import lombok.Data;

/**
 * Supervisor
 *
 * @author Lorenzo Pinango.
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
@Data
public class Supervisor extends Employee{

    public Supervisor(Long id, String firstName) {
        super(id, firstName);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
