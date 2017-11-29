/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.prueba3.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Noticia implements Serializable{
    
    static final long serialVersionUID = 2L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2, max = 20)
    @Column(nullable = false, unique = true)
    private String imagen;
    private String titulo;
    private String desarrollo;
    
    @ManyToOne
    private String categoria;

    public Noticia() {
    }

    public Noticia(Long id, String imagen, String titulo, String desarrollo, String categoria) {
        this.id = id;
        this.imagen = imagen;
        this.titulo = titulo;
        this.desarrollo = desarrollo;
        this.categoria = categoria;
    }
        public Noticia(String imagen, String titulo, String desarrollo, String categoria) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.desarrollo = desarrollo;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesarrollo() {
        return desarrollo;
    }

    public void setDesarrollo(String desarrollo) {
        this.desarrollo = desarrollo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

   
    
}
