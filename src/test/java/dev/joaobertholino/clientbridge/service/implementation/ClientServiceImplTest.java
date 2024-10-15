package dev.joaobertholino.clientbridge.service.implementation;

import dev.joaobertholino.clientbridge.data.test.builder.ClientDataTestBuilder;
import dev.joaobertholino.clientbridge.model.Client;
import dev.joaobertholino.clientbridge.repository.ClientRepository;
import dev.joaobertholino.clientbridge.request.ClientRequest;
import dev.joaobertholino.clientbridge.response.ClientResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
class ClientServiceImplTest {
	private static ClientResponse clientResponse;
	private static ClientRequest clientRequest;
	private static Client client;

	@InjectMocks
	private ClientServiceImpl clientService;

	@Mock
	private ClientRepository clientRepository;

	@BeforeAll
	static void setUp() {
		ClientServiceImplTest.clientResponse = ClientDataTestBuilder.clientResponseBuilder(UUID.randomUUID(), "Joao", "Bertholino", "joao@gmail.com", "48624704839", BigDecimal.valueOf(100));
		ClientServiceImplTest.clientRequest = ClientDataTestBuilder.clientRequestBuilder("Joao", "Bertholino", "joao@gmail.com", "48624704839", BigDecimal.valueOf(100));
		ClientServiceImplTest.client = ClientDataTestBuilder.clientBuilder(ClientServiceImplTest.clientResponse.id(), "Joao", "Bertholino", "joao@gmail.com", "48624704839", BigDecimal.valueOf(100));
	}

	@Test
	void insertClientPassed() {
		Mockito.when(this.clientRepository.save(Mockito.any(Client.class))).thenReturn(ClientServiceImplTest.client);
		ClientResponse clientResponse = this.clientService.insertClient(ClientServiceImplTest.clientRequest);

		assertNotNull(clientResponse, "This object of type Customer is null.");
		assertDoesNotThrow(() -> this.clientRepository.save(Mockito.any(Client.class)));
		assertDoesNotThrow(() -> this.clientService.insertClient(ClientServiceImplTest.clientRequest));
		assertEquals(ClientServiceImplTest.clientResponse, clientResponse);
		assertEquals(ClientServiceImplTest.clientResponse.firstName(), clientResponse.firstName());
		assertEquals(ClientServiceImplTest.clientResponse.lastName(), clientResponse.lastName());
		assertEquals(ClientServiceImplTest.clientResponse.email(), clientResponse.email());
		assertEquals(ClientServiceImplTest.clientResponse.cpf(), clientResponse.cpf());
	}
}