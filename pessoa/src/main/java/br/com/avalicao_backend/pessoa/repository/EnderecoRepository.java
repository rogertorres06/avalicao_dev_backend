package br.com.avalicao_backend.pessoa.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.avalicao_backend.pessoa.models.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
 }
