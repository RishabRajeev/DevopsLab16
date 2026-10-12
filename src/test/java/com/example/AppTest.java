package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppTest {
    @Test
    public void testGreetingNotNull() {
        assertNotNull(App.getGreeting(), "Greeting output must not be null");
    }
}
