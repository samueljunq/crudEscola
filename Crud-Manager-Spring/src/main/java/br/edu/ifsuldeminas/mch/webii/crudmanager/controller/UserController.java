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

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Address;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.AddressRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.UserRepository;
import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		
		List<User> users = userRepo.findAll();
		
		model.addAttribute("usarios", users);
		
		return "index";
	}
	
	@GetMapping("/users/form")
	public String userForm(@ModelAttribute("usuario") User user) { 
		// Estudar o mecanismo de "Binding" do Spring 
					
		return "users_form";
	}
	
	@PostMapping("/users/register")
	public String userNew(@Valid
			              @ModelAttribute("usuario") 
	                      User user,
	                      BindingResult erros) {
		
		if (erros.hasErrors()) {
			return "users_form";
		}
		
		addressRepo.save(user.getAddress());
		userRepo.save(user);
		
		return "redirect:/users";
	}
	
	
	@GetMapping("/users/update/{id}")
	public String userUpdate(@PathVariable("id") 
	                         Integer id,
	                         Model model) {
		
		Optional<User> userOpt = userRepo.findById(id);
		User user;
		
		if (!userOpt.isPresent()) {
			user = new User();
		} else {
			user = userOpt.get();
		}		
		
		model.addAttribute("usuario", user);
		
		return "users_form";
	}
	
	@GetMapping("/users/delete/{id}")
	public String userDelete(@PathVariable("id") Integer id) {
		
		userRepo.delete(new User(id));
		
		return "redirect:/users";
	}

}
