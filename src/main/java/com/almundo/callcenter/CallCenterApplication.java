package com.almundo.callcenter;

import com.almundo.callcenter.model.StatusCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.Client;
import com.almundo.callcenter.service.Dispatcher;

@SpringBootApplication
public class CallCenterApplication implements CommandLineRunner {

    @Autowired
    private Dispatcher dispatcher;

    @Value("${callCenter.maxDurationCall}")
    private int maxDurationCall;

    @Value("${callCenter.minDurationCall}")
    private int minDurationCall;

    public static void main(String[] args) {
        SpringApplication.run(CallCenterApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        //Se inicia simulacion de llamadas
        for (int i = 0; i < 20; i++) {
            System.out.println("[INFO] Inicio llamada " + i);
            //Se crea cliente de simulacion
            Client client = new Client("client" + i);
            //Se crea llamada de simulacion
            Call call = new Call(i, client, maxDurationCall, minDurationCall);
            //Se dispara tarea
            dispatcher.dispatchCall(call);
        }

    }
}

