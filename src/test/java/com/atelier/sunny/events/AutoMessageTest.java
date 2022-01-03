package com.atelier.sunny.events;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AutoMessageTest {
    AutoMessage autoMessage = new AutoMessage();

    @Test
    void run() {
        assertEquals("morning", autoMessage.checkTime(LocalTime.now()));
    }
}