package com.student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.model.Student;
import com.student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    @Test
    public void testGetAllStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "john@example.com"));
        students.add(new Student("Alice", "alice@example.com"));
        when(studentService.findAll()).thenReturn(students);

        mockMvc.perform(get("/api/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(students)));
    }

    @Test
    public void testAddStudent() throws Exception {
        Student student = new Student("John", "john@example.com");
        String json = objectMapper.writeValueAsString(student);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("New Student Added: " + student));
    }

    @Test
    public void testGetStudentById() throws Exception {
        int studentId = 1;
        Student student = new Student("John", "john@example.com");
        student.setId(studentId);
        when(studentService.findById(studentId)).thenReturn(student);

        mockMvc.perform(get("/api/students/" + studentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(student)));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        int studentId = 1;
        Student student = new Student("John", "john@example.com");
        student.setId(studentId);
        String json = objectMapper.writeValueAsString(student);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Student updated successfully: " + student));
    }
    @Test
    public void testDeleteStudent() throws Exception {
        // setup
        int studentId = 1;

        // execute
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/students/" + studentId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Student with ID: " + studentId + " deleted successfully"));

        // verify
        verify(studentService, times(1)).deleteById(studentId);
    }


}