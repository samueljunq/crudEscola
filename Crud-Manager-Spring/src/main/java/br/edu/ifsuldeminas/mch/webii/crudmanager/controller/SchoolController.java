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
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.SchoolRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/schools")
public class SchoolController {
	@Autowired
	private SchoolRepository schoolRepo;
	
	@GetMapping
	public String listSchools(Model model) {
		
		List<School> schools = schoolRepo.findAll();
		
		model.addAttribute("schools", schools);
		
		return "schools_page";
	}
	
	@GetMapping("/form")
	public String schoolForm(@ModelAttribute("school") School school) { 
		// Estudar o mecanismo de "Binding" do Spring 
					
		return "schools_form";
	}
	
	@PostMapping("/register")
	public String schoolNew(@Valid
			              @ModelAttribute("school") 
							School school,
	                      BindingResult erros) {
		
		if (erros.hasErrors()) {
			return "schools_form";
		}
		
		schoolRepo.save(school);
		
		return "redirect:/schools";
	}
	
	
	@GetMapping("/update/{id}")
	public String schoolUpdate(@PathVariable("id") 
	                         Integer id,
	                         Model model) {
		
		Optional<School> schoolOpt = schoolRepo.findById(id);
		School school;
		
		if (!schoolOpt.isPresent()) {
			school = new School();
		} else {
			school = schoolOpt.get();
		}		
		
		model.addAttribute("school", school);
		
		return "schools_form";
	}
	
	@GetMapping("/delete/{id}")
	public String schoolDelete(@PathVariable("id") Integer id) {
		
		schoolRepo.delete(new School(id));
		
		return "redirect:/schools";
	}

}
