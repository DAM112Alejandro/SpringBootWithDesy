package com.example.libreria.model;



import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Autor implements Serializable {
    @Getter
    @Setter
    @Id
    @NotNull
    private String dni;
    @Getter
    @Setter
    @NotNull
    private String nombre;
    @Getter
    @Setter
    @NotNull
    private String apellido1;
    @Getter
    @Setter
    private String apellido2;
    @Getter
    @Setter
    private String telefono;
    @Getter
    @Setter
    private String email;
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
    
    
}