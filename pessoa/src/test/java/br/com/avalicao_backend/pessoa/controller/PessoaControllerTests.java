package br.com.avalicao_backend.pessoa.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.avalicao_backend.pessoa.models.Pessoa;
import br.com.avalicao_backend.pessoa.repository.PessoaRepository;
import br.com.avalicao_backend.pessoa.service.PessoaService;

@WebMvcTest(PessoaController.class)
public class PessoaControllerTests {

    @MockBean
    PessoaService pessoaService;

    @MockBean
    PessoaRepository pessoaRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deve_retornar_pessoa_criada() throws Exception {
        Pessoa request = new Pessoa();
        request.setNome("usar teste");

        Pessoa user = new Pessoa();
        user.setNome(request.getNome());
        ResponseEntity<Pessoa> retorno = new ResponseEntity<Pessoa>(user, HttpStatus.CREATED);
        when(pessoaService.salvarPessoa(any(Pessoa.class))).thenAnswer(
                x -> retorno);

        mockMvc.perform(post("/pessoa")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void tratando_dados_invalidos() throws Exception {
        Pessoa request = new Pessoa();
        request.setNome("user invalido");

        Pessoa user = new Pessoa();
        user.setNome(request.getNome());
        ResponseEntity<String> retorno = new ResponseEntity<String>(
                "Dados informados inválido! Verificar se os dados informados já foram cadastrados.",
                HttpStatus.BAD_REQUEST);
        when(pessoaService.salvarPessoa(any(Pessoa.class))).thenAnswer(
                x -> retorno);

        mockMvc.perform(post("/pessoa")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    

}
