package br.fiap.projeto.contexto.identificacao;

import br.fiap.projeto.contexto.identificacao.adapter.controller.rest.response.ClienteResponseDTO;
import br.fiap.projeto.contexto.identificacao.external.repository.entity.ClienteEntity;
import br.fiap.projeto.contexto.identificacao.external.repository.postgres.SpringClienteRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application.properties")
public class ClienteControllerTestAntigo {

        private final static String CAMINHO_RAIZ = "/clientes";
        private final static String CAMINHO_RAIZ_CODIGO = "/clientes/{codigo}";

        private final static Supplier<String> CPF_GENERATOR = () -> IntStream.range(0, 11)
                        .mapToObj(i -> String.valueOf(new Random().nextInt(10)))
                        .collect(Collectors.joining());

        private final ObjectMapper mapper = new ObjectMapper();

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private SpringClienteRepository clienteRepository;

        // @Test
        // public void testeBuscaTodos() throws Exception {

        // ResultActions resultActions =
        // mockMvc.perform(MockMvcRequestBuilders.get(CAMINHO_RAIZ + "/todos"))
        // .andExpect(MockMvcResultMatchers.status().isOk());

        // MvcResult result = resultActions.andReturn();
        // String sContent = result.getResponse().getContentAsString();

        // List<ClienteResponseDTO> clienteDTOS = mapper.readValue(sContent,
        // new TypeReference<List<ClienteResponseDTO>>() {
        // });

        // assertFalse(CollectionUtils.isEmpty(clienteDTOS));
        // }

        // @Test
        // public void testeBuscaPorCpf() throws Exception {

        // String cpf = CPF_GENERATOR.get();

        // ClienteResponseDTO cliente = new ClienteResponseDTO("TesteBusca", cpf,
        // "teste@busca.com");
        // String sCliente = mapper.writeValueAsString(cliente);

        // mockMvc.perform(MockMvcRequestBuilders.post(CAMINHO_RAIZ)
        // .contentType(APPLICATION_JSON)
        // .content(sCliente))
        // .andExpect(MockMvcResultMatchers.status().isCreated());

        // ResultActions resultActions =
        // mockMvc.perform(MockMvcRequestBuilders.get(CAMINHO_RAIZ + "/cpf")
        // .param("cpf", cpf))
        // .andExpect(MockMvcResultMatchers.status().isOk());

        // MvcResult result = resultActions.andReturn();
        // String sContent = result.getResponse().getContentAsString();
        // ClienteResponseDTO clienteDTO = mapper.readValue(sContent,
        // ClienteResponseDTO.class);

        // assertNotNull(clienteDTO);
        // assertEquals(clienteDTO.getCpf(), cpf);

        // }

        // @Test
        // public void testeBuscaPorCpfInexistente() throws Exception {

        // String cpf = CPF_GENERATOR.get();

        // mockMvc.perform(MockMvcRequestBuilders.get(CAMINHO_RAIZ + "/cpf")
        // .param("cpf", cpf))
        // .andExpect(MockMvcResultMatchers.status().isNotFound());
        // }

        // @Test
        // public void testeBuscaPorCpfNaoInformado() throws Exception {

        // mockMvc.perform(MockMvcRequestBuilders.get(CAMINHO_RAIZ + "/cpf"))
        // .andExpect(MockMvcResultMatchers.status().isBadRequest());
        // }

        // @Test
        // public void testeInsere() throws Exception {

        // String cpf = CPF_GENERATOR.get();
        // ClienteResponseDTO cliente = new ClienteResponseDTO("TesteBusca", cpf,
        // "teste3@busca.com");
        // String sCliente = mapper.writeValueAsString(cliente);

        // mockMvc.perform(MockMvcRequestBuilders.post(CAMINHO_RAIZ)
        // .contentType(APPLICATION_JSON)
        // .content(sCliente))
        // .andExpect(MockMvcResultMatchers.status().isCreated());

        // }

        // @Test
        // public void testeEdita() throws Exception {

        // String cpf1 = CPF_GENERATOR.get();
        // String cpf2 = CPF_GENERATOR.get();
        // String nome1 = "TesteInsere";
        // String nome2 = "TesteEdita";
        // String email1 = "teste@insere.com";
        // String email2 = "teste@edita.com";

        // ClienteResponseDTO clienteDTO;
        // String sCliente;
        // String codigo;
        // String sContent;

        // ResultActions resultActions;
        // MvcResult result;

        // clienteDTO = new ClienteResponseDTO(nome1, cpf1, email1);
        // sCliente = mapper.writeValueAsString(clienteDTO);

        // // Cria o cliente
        // resultActions = mockMvc.perform(MockMvcRequestBuilders.post(CAMINHO_RAIZ)
        // .contentType(APPLICATION_JSON)
        // .content(sCliente))
        // .andExpect(MockMvcResultMatchers.status().isCreated());

        // // Recupera os dados do cliente criado
        // result = resultActions.andReturn();
        // sContent = result.getResponse().getContentAsString();
        // clienteDTO = mapper.readValue(sContent, ClienteResponseDTO.class);

        // // Salva o código para a validação final
        // codigo = clienteDTO.getCodigo();

        // // Edita o nome cliente
        // clienteDTO = new ClienteResponseDTO(clienteDTO.getCodigo(), nome2, cpf2,
        // email2);
        // sCliente = mapper.writeValueAsString(clienteDTO);
        // mockMvc.perform(MockMvcRequestBuilders.put(CAMINHO_RAIZ_CODIGO, codigo)
        // .contentType(APPLICATION_JSON)
        // .content(sCliente))
        // .andExpect(MockMvcResultMatchers.status().isOk());

        // // Busca o cliente com o cpf antigo
        // mockMvc.perform(MockMvcRequestBuilders.get(CAMINHO_RAIZ + "/cpf")
        // .param("cpf", cpf1))
        // .andExpect(MockMvcResultMatchers.status().isNotFound());

        // // Busca o cliente com o cpf novo
        // resultActions = mockMvc.perform(MockMvcRequestBuilders.get(CAMINHO_RAIZ +
        // "/cpf")
        // .param("cpf", cpf2))
        // .andExpect(MockMvcResultMatchers.status().isOk());

        // // Recupera os dados do cliente buscado
        // result = resultActions.andReturn();
        // sContent = result.getResponse().getContentAsString();
        // clienteDTO = mapper.readValue(sContent, ClienteResponseDTO.class);

        // // Valida se os dados foram atualizados
        // assertEquals(clienteDTO.getEmail(), email2);
        // assertEquals(clienteDTO.getNome(), nome2);
        // assertEquals(clienteDTO.getCodigo(), codigo);
        // }

        // @Test
        // public void testeRemove() throws Exception {

        // String codigo;
        // ClienteResponseDTO cliente, resultado;

        // cliente = new ClienteResponseDTO("NomeTeste", CPF_GENERATOR.get(),
        // "teste@teste.com");
        // String sCliente = mapper.writeValueAsString(cliente);

        // // Insere o cliente
        // ResultActions resultActions =
        // mockMvc.perform(MockMvcRequestBuilders.post(CAMINHO_RAIZ)
        // .contentType(APPLICATION_JSON)
        // .content(sCliente))
        // .andExpect(MockMvcResultMatchers.status().isCreated());
        // MvcResult result = resultActions.andReturn();
        // String sContent = result.getResponse().getContentAsString();
        // resultado = mapper.readValue(sContent, ClienteResponseDTO.class);
        // codigo = resultado.getCodigo();

        // // Busca o cliente usando o código
        // resultActions =
        // mockMvc.perform(MockMvcRequestBuilders.get(CAMINHO_RAIZ_CODIGO, codigo)
        // .param("codigo", codigo))
        // .andExpect(MockMvcResultMatchers.status().isOk());

        // result = resultActions.andReturn();
        // sContent = result.getResponse().getContentAsString();
        // cliente = mapper.readValue(sContent, ClienteResponseDTO.class);

        // assertNotNull(cliente);

        // // Remove o cliente
        // mockMvc.perform(MockMvcRequestBuilders.delete(CAMINHO_RAIZ_CODIGO, codigo)
        // .param("codigo", codigo))
        // .andExpect(MockMvcResultMatchers.status().isOk());

        // // Busca o cliente usando o código para verificar se foi removido
        // mockMvc.perform(MockMvcRequestBuilders.get(CAMINHO_RAIZ_CODIGO, codigo)
        // .param("codigo", codigo))
        // .andExpect(MockMvcResultMatchers.status().isNotFound());

        // // Busca o cliente diretamente do repositório para garantir a exclusão lógica
        // ClienteEntity entity = clienteRepository.findById(codigo)
        // .orElse(null);
        // assertNotNull(entity);
        // assertNotNull(entity.getDataExclusao());
        // assertNotNull(entity.getCodigo());
        // assertEquals(entity.getCodigo().toString(), codigo);
        // }

}
