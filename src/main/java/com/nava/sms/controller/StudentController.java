package com.nava.sms.controller;

import com.nava.sms.entity.Student;
import com.nava.sms.service.StudentService;
import net.bytebuddy.matcher.StringMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // using constructor based dependency

     public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    // handler method to handle list of students and return model and view
    @GetMapping("/students")
    public String listStudents(Model model)
    {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";   // view name, we need to create view(students) in template folder inside resource folder

    }

   @GetMapping("/students/new")
    public String createStudentForm(Model model) // passing model object as a method argument
    {
        Student student = new Student(); // creating student object to hold student form data
        model.addAttribute("student",student );
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) //@ModelAttribute is used to bind the student model object to student
    {
         studentService.saveStudent(student);
         return "redirect:/students";

    }


    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model)
    {
         model.addAttribute("student",studentService.getStudentById(id));
         return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model)
    {
        // get the student from database by ID
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        // save updated student object
        studentService.updateStudent(existingStudent);
        return "redirect:/students";

    }

    // handler method to handle delete student request

    @GetMapping("/students/{id}")
    public String deleteStudentById(@PathVariable Long id)
    {
        studentService.deleteStudentById(id);
        return "redirect:/students";

    }


}
