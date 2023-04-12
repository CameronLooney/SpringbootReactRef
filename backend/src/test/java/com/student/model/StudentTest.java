package com.student.model;

import org.testng.annotations.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {

    @Test
    public void testGetId() {
        Student student = new Student();
        student.setId(1);
        assertEquals(1, student.getId());
    }


    @Test
    public void testGetName() {
        Student student = new Student();
        student.setName("John Doe");
        assertEquals("John Doe", student.getName());
    }

    @Test
    public void testGetEmail() {
        Student student = new Student();
        student.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", student.getEmail());
    }

    @Test
    public void testConstructor() {
        Student student = new Student("John Doe", "john.doe@example.com");
        assertEquals("John Doe", student.getName());
        assertEquals("john.doe@example.com", student.getEmail());

    }
}
