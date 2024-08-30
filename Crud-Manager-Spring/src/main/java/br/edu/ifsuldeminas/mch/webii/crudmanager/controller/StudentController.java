package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.School;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Student;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.SchoolRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.StudentRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private SchoolRepository schoolRepo;
	
	@GetMapping
	public String listStudent(Model model) {
	    List<Student> students = studentRepo.findAll();
	    model.addAttribute("students", students);
	    return "students_page";
	}
			
	@GetMapping("/form")
	public String studentsForm(@ModelAttribute("student") Student student, Model model) { 
		// Estudar o mecanismo de "Binding" do Spring
		List<School> schools = schoolRepo.findAll();
		
		model.addAttribute("schools", schools);
					
		return "students_form";
	}
	
	@PostMapping("/register")
	public String studentNew(@Valid
			              @ModelAttribute("student") 
							Student student,
	                      BindingResult erros) {
		
		if (erros.hasErrors()) {
			return "students_form";
		}
		
		studentRepo.save(student);
		
		return "redirect:/students";
	}
	
	
	@GetMapping("/update/{id}")
	public String studentUpdate(@PathVariable("id") 
	                         Integer id,
	                         Model model) {
		
		Optional<Student> studentOpt = studentRepo.findById(id);
		Student student;
		
		if (!studentOpt.isPresent()) {
			student = new Student();
		} else {
			student = studentOpt.get();
		}		
		
		List<School> schools = schoolRepo.findAll();
        model.addAttribute("schools", schools);
		model.addAttribute("student", student);
		
		return "students_form";
	}
	
	@GetMapping("/delete/{id}")
	public String studentlDelete(@PathVariable("id") Integer id) {
		
		studentRepo.delete(new Student(id));
		
		return "redirect:/students";
	}
}
