package br.edu.ifsuldeminas.mch.webii.crudmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "student")
public class Student {
	@NotBlank(message = "O nome não pode ser vazio!")
	private String name;
	@NotNull(message = "Número de matrícula é obrigatório.")
	@Digits(fraction = 0, integer = 10, message = "Número inválido.")
	private Integer registration;
	@NotBlank(message = "O nível escolar não pode ser vazio!")
	private String level;
	@NotBlank(message = "O nome do responsavél não pode ser vazio!")
	private String nameOfResponsible;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne(optional = false)
	@JoinColumn(name = "school_id")
	@Valid
	@NotNull (message = "A escola é obrigatória!")
	private School school;
	public Student() {}

	public Student(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRegistration() {
		return registration;
	}

	public void setRegistration(Integer registration) {
		this.registration = registration;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getNameOfResponsible() {
		return nameOfResponsible;
	}

	public void setNameOfResponsible(String nameOfResponsible) {
		this.nameOfResponsible = nameOfResponsible;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
	
	
	
}
