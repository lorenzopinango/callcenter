package com.almundo.callcenter.model;

import com.google.gson.Gson;
import lombok.Data;

/**
 * Client
 *
 * @author Lorenzo Pinango.
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
@Data
public class Client {

    private String name;

    public Client(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
