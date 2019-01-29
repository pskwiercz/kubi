package com.company.client;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void givenClient1_whenServerResponds_thenCorrect() {
        Client client1 = new Client();
        client1.startConnection("127.0.0.1", 5555);
        String msg = client1.getMessage();
        String msg1 = client1.sendMessage("LOGIN aaaa");
        String msg2 = client1.getMessage();
        String msg3 = client1.getMessage();
        String msg4 = client1.sendMessage("ATAK 2 3 4 6");

        assertEquals(msg, "POLACZONO");
        assertEquals(msg1, "Added login aaaa");
        assertEquals(msg2, "START 0 1");
        assertEquals(msg3, "TWOJ RUCH");
        assertEquals(msg4, "ERROR");
    }

    @Test
    public void givenClient2_whenServerResponds_thenCorrect() {
        Client client1 = new Client();
        client1.startConnection("127.0.0.1", 5555);
        String msg = client1.getMessage();
        String msg1 = client1.sendMessage("LOGIN bbbb");
        String msg2 = client1.getMessage();
        String msg3 = client1.sendMessage("ATAK 2 3 4 6");

        assertEquals(msg, "POLACZONO");
        assertEquals(msg1, "Added login bbbb");
        assertEquals(msg2, "START 1 1");

    }
}