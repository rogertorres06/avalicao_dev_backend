package br.com.avalicao_backend.pessoa.service;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.avalicao_backend.pessoa.repository.PessoaRepository;

public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public ResponseEntity<?> buscarPorIdPessoa(Long codigo) {
        Optional<Pessoa> pessoa = this.repository.findByCodigo(codigo);

        if (pessoa.orElseget(() -> null) != null) {
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Pessoa nao localizada, Tente novamente", HttpStatus.BAD_REQUEST);
        }
    }
}
