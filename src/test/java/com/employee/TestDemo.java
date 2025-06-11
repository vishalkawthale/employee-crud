package com.employee;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class TestDemo {

    @Test
    void test() {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("currentTimeMillis: " + new Date(currentTimeMillis));
        long l = System.currentTimeMillis() + 60 * 60 * 1000;
        System.out.println("l: " + new Date(l));

    }
}
