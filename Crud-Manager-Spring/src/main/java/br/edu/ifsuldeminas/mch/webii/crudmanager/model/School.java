package br.edu.ifsuldeminas.mch.webii.crudmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 
 */
@Entity
@Table(name = "school")
public class School {
	@NotBlank(message = "O nome não pode ser vazio!")
	private String name;
	@NotNull(message = "Número de estudantes é obrigatório.")
	@Digits(fraction = 0, integer = 10, message = "Número inválido.")
	private Integer numberOfStudents;
	@NotBlank(message = "O nível de escola não pode ser vazio!")
	private String levelOfEducation;
	@NotBlank(message = "O tipo de escola não pode ser vazio!")
	private String schoolType;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	public School() {}

	public School(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumberOfStudents() {
		return numberOfStudents;
	}
	public void setNumberOfStudents(Integer numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}
	public String getLevelOfEducation() {
		return levelOfEducation;
	}
	public void setLevelOfEducation(String levelOfEducation) {
		this.levelOfEducation = levelOfEducation;
	}
	public String getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
