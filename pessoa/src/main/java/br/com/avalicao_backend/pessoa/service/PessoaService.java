package br.com.avalicao_backend.pessoa.service;


import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;

import br.com.avalicao_backend.pessoa.models.Pessoa;
import br.com.avalicao_backend.pessoa.models.Endereco;
import java.util.Optional;
import org.springframework.stereotype.Service;

import br.com.avalicao_backend.pessoa.repository.PessoaRepository;
import br.com.avalicao_backend.pessoa.repository.EnderecoRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    //BUSCAR POR ID DA PESSOA
    public ResponseEntity<?> buscarPorIdPessoa(Long id) {
        Optional<Pessoa> pessoa = this.repository.findById(id);

        if (pessoa.orElseGet(() -> null) != null) {
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Pessoa nao localizada, Tente novamente", HttpStatus.BAD_REQUEST);
        }
    }

    //METODO PARA SALVAR UMA PESSOA
    public ResponseEntity<?> salvarPessoa(Pessoa pessoa ){
        try{
            return new ResponseEntity<Pessoa>(this.repository.save(pessoa),HttpStatus.CREATED);
        }catch(JpaSystemException | GenericJDBCException | HttpMessageNotReadableException | DataIntegrityViolationException e){
            e.printStackTrace();
            return new ResponseEntity<String>("Dados informados inválido! Verificar se os dados informados já foram cadastrados.", HttpStatus.BAD_REQUEST);
        }
    }

    //METODO PARA SALVAR UM ENDERECO

    public ResponseEntity<?> salvarEndereco(Endereco endereco){
        try{
            return new ResponseEntity<Endereco>(this.enderecoRepository.save(endereco), HttpStatus.CREATED);
        } catch(JpaSystemException | GenericJDBCException | HttpMessageNotReadableException | DataIntegrityViolationException e){
            e.printStackTrace();
            return new ResponseEntity<String>("Dados informados inválido! Verificar se os dados informados já foram cadastrados.", HttpStatus.BAD_REQUEST);
    }
    }
}
