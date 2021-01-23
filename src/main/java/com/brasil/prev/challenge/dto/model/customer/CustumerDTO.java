package com.brasil.prev.challenge.dto.model.customer;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;

import com.brasil.prev.challenge.model.customer.Customer;
import com.sun.istack.NotNull;

public class CustumerDTO implements Serializable{

	private static final long serialVersionUID = -1992787607430534505L;
	
	private Long id;
	
	@NotEmpty  @NotNull 
	private String nome;
	
	@NotEmpty  @NotNull 
	private String cpf;
	
	@NotEmpty  @NotNull 
	private String address;
	
	
	
	public CustumerDTO(Long id, @NotEmpty String nome, @NotEmpty String cpf, @NotEmpty String address) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.address = address;
	}
	

	public CustumerDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        return Objects.equals(id, that.getId());
    }
	
	public int hashCode() {
		return Objects.hash(id);
	}
	
	public Customer convertDTOToEntity() {
		return new Customer(this.id, this.nome, this.cpf, this.address);
	}
	
	

}
