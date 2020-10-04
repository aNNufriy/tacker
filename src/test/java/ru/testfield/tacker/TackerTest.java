package ru.testfield.tacker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TackerTest {

    @Test
    void helloWorldTest(){
        Tacker tacker = new Tacker();
        assertEquals("hello, world", tacker.helloWorld());
    }

}
