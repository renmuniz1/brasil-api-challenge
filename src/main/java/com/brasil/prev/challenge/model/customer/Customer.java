package com.brasil.prev.challenge.model.customer;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.brasil.prev.challenge.dto.model.customer.CustumerDTO;
import com.sun.istack.NotNull;

@Entity
@Table(name = "customer")
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 2597449516907514872L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String cpf;
	
	@NotNull
	private String address;
	
	public Customer() {
		super();
	}

	public Customer(Long id, String nome, String cpf, String address) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.address = address;
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
        return Objects.equals(id, that.id);
    }
	
	public int hashCode() {
		return Objects.hash(id);
	}
	
	public CustumerDTO convertEntityToDTO() {
		return new CustumerDTO(this.id, this.nome, this.cpf, this.address);
	}
	
}
