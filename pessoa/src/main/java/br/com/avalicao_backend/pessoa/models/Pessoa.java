package br.com.avalicao_backend.pessoa.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Pessoa")
@Table(name = "Pessoa")
public class Pessoa  implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long codigo;
    @Column(name = "nome")
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dataNasc")
    private String dataNasc;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pessoa")
    @JsonManagedReference
    private List<Endereco>   enderecoList = new ArrayList<>();

    public void addEndereco(Endereco endereco){
        enderecoList.add(endereco);
    }

    public void removerEndereco(Endereco endereco){
        enderecoList.remove(endereco);
    }

 
   
}
