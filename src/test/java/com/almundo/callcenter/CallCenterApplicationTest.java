package com.almundo.callcenter;

import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.Client;
import com.almundo.callcenter.service.Dispatcher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Tests
 *
 * @author Lorenzo Pinango.
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
public class CallCenterApplicationTest extends ConfigurationTest {

    @Autowired
    private Dispatcher dispatcher;

    @Value("${callCenter.maxDurationCall}")
    private int maxDurationCall;

    @Value("${callCenter.minDurationCall}")
    private int minDurationCall;

    @Test
    public void testCalls() throws InterruptedException {
        //Se inicia simulacion de llamadas
        for (int i = 0; i < 9; i++) {
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
