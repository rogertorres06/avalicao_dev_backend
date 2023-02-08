package br.com.avalicao_backend.pessoa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import java.util.Optional;
import br.com.avalicao_backend.pessoa.models.Endereco;
import br.com.avalicao_backend.pessoa.models.Pessoa;
import br.com.avalicao_backend.pessoa.repository.EnderecoRepository;
import br.com.avalicao_backend.pessoa.repository.PessoaRepository;



@SpringBootTest
public class PessoaServiceTests {

   

    @Mock
    PessoaRepository pessoaRepository;

    @Mock
    EnderecoRepository enderecoRepository;

    @InjectMocks
    PessoaService pessoaService;

    

    @Test
    public void deve_retornar_pessoa_criada() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("usar teste");

        Pessoa retorno = new Pessoa();
        retorno.setNome(pessoa.getNome());
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(
                retorno);

        ResponseEntity<?> created = pessoaService.salvarPessoa(pessoa);
        assertEquals(created.getStatusCode(), HttpStatusCode.valueOf(201));

    }

    @Test
    public void deve_retornar_endereco_criada() throws Exception {
        Endereco endereco = new Endereco();
        endereco.setLogradouro("logradouro teste");

        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);

        ResponseEntity<?> created = pessoaService.salvarEndereco(endereco);
        assertEquals(created.getStatusCode(), HttpStatusCode.valueOf(201));

    }

    @Test
    public void deve_retornar_busca_Por_Id() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1);

        when(pessoaRepository.findById(pessoa.getId())).thenReturn(Optional.of(pessoa));

        ResponseEntity<?> buscado = pessoaService.buscarPorIdPessoa(pessoa.getId());
        assertEquals(buscado.getStatusCode(), HttpStatusCode.valueOf(200));

    }

  

  




}
