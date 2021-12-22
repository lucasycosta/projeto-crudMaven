package br.com.lucas.projeto.teste.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
@Entity
@Table
@SuppressWarnings("serial")
public @Data class Usuario implements Serializable{
	
	@Id
	@Column(name="id_usuario")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	@SequenceGenerator(allocationSize = 1, name="seq_usuario", sequenceName = "seq_usuario")
	private Long id;
	
	@Column
	@NotBlank(message = "Campo não informado")
	@Pattern(regexp = "^[A-Z]+(.)*", message = "Nome deve começar com letra maiúscula")
	private String nome;
	
	@Column
	@Email(message = "Email inválido")
	@NotBlank(message = "Campo não informado")
	private String email;
	
	@Column(length = 14)
	@CPF(message = "CPF inválido")
	@NotBlank(message = "Campo não informado")
	private String cpf;
	
	
}
