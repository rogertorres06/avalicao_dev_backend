package br.com.avalicao_backend.pessoa.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Endereco implements Serializable {
   
    @Id 
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String logradouro;
    
    private String cep;
    
    private Integer numero;
    
    private String cidade;
    private Boolean enderecoPrincipal;
   
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pessoa")
    @JsonBackReference
    private Pessoa pessoa; 
  
    
    
  
  
   
}