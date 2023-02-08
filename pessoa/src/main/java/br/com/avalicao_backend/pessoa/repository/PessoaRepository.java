package br.com.avalicao_backend.pessoa.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.avalicao_backend.pessoa.models.Pessoa;



@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
 }