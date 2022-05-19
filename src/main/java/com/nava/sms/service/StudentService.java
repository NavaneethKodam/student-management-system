package com.nava.sms.service;

import com.nava.sms.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    public Student saveStudent(Student student);

    public Student getStudentById(Long id);

    public Student updateStudent(Student student);

    public void deleteStudentById(Long id);

}
