package dev.joaobertholino.clientbridge.service.implementation;

import dev.joaobertholino.clientbridge.enums.TransactionType;
import dev.joaobertholino.clientbridge.model.Client;
import dev.joaobertholino.clientbridge.model.Enterprise;
import dev.joaobertholino.clientbridge.model.Transaction;
import dev.joaobertholino.clientbridge.notification.implementation.NotificationComponentImpl;
import dev.joaobertholino.clientbridge.repository.ClientRepository;
import dev.joaobertholino.clientbridge.repository.EnterpriseRepository;
import dev.joaobertholino.clientbridge.repository.TransactionRepository;
import dev.joaobertholino.clientbridge.request.ClientRequest;
import dev.joaobertholino.clientbridge.request.EnterpriseRequest;
import dev.joaobertholino.clientbridge.request.TransactionRequest;
import dev.joaobertholino.clientbridge.response.ClientResponse;
import dev.joaobertholino.clientbridge.response.EnterpriseResponse;
import dev.joaobertholino.clientbridge.response.TransactionResponse;
import dev.joaobertholino.clientbridge.webhook.impl.SendCallBackImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class TransactionServiceImplTest {
	private static ClientResponse clientResponse;
	private static ClientRequest clientRequest;
	private static Client client;
	private static EnterpriseResponse enterpriseResponse;
	private static EnterpriseRequest enterpriseRequest;
	private static Enterprise enterprise;
	private static TransactionResponse transactionResponse;
	private static TransactionRequest transactionRequest;
	private static Transaction transaction;

	@InjectMocks
	private TransactionServiceImpl transactionService;

	@Mock
	private ClientRepository clientRepository;

	@Mock
	private EnterpriseRepository enterpriseRepository;

	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private NotificationComponentImpl notificationComponent;

	@Mock
	private SendCallBackImpl sendCallBack;

	@BeforeAll
	static void setUp() {
		TransactionServiceImplTest.clientResponse = new ClientResponse(UUID.randomUUID(), "Joao", "Bertholino", "joao@gmail.com", "48624704839", BigDecimal.valueOf(100));
		TransactionServiceImplTest.clientRequest = new ClientRequest("Joao", "Bertholino", "joao@gmail.com", "48624704839", BigDecimal.valueOf(100));
		TransactionServiceImplTest.client = new Client(TransactionServiceImplTest.clientResponse.id(), "Joao", "Bertholino", "joao@gmail.com", "48624704839", BigDecimal.valueOf(100));

		TransactionServiceImplTest.enterpriseResponse = new EnterpriseResponse(UUID.randomUUID(), "Google", "54.160.463/0001-40", BigDecimal.valueOf(100));
		TransactionServiceImplTest.enterpriseRequest = new EnterpriseRequest("Google", "54.160.463/0001-40", BigDecimal.valueOf(100));
		TransactionServiceImplTest.enterprise = new Enterprise(TransactionServiceImplTest.enterpriseResponse.id(), "Google", "54.160.463/0001-40", BigDecimal.valueOf(100));

		TransactionServiceImplTest.transactionResponse = new TransactionResponse(UUID.randomUUID(), TransactionServiceImplTest.clientResponse, TransactionServiceImplTest.enterpriseResponse, BigDecimal.valueOf(90), BigDecimal.valueOf(0.02), TransactionType.DEPOSIT, LocalDateTime.now().toString());
		TransactionServiceImplTest.transactionRequest = new TransactionRequest("48624704839", "54.160.463/0001-40", BigDecimal.valueOf(90), TransactionType.DEPOSIT);
		TransactionServiceImplTest.transaction = new Transaction(TransactionServiceImplTest.transactionResponse.id(), TransactionServiceImplTest.client, TransactionServiceImplTest.enterprise, BigDecimal.valueOf(90), BigDecimal.valueOf(0.02), TransactionType.DEPOSIT, LocalDateTime.now());
	}

	@Test
	void makeTransaction() {
		Mockito.when(this.clientRepository.findClientByCpf(Mockito.any(String.class))).thenReturn(Optional.of(TransactionServiceImplTest.client));
		Mockito.when(this.enterpriseRepository.findEnterpriseByCnpj(Mockito.any(String.class))).thenReturn(Optional.of(TransactionServiceImplTest.enterprise));

		Mockito.when(this.clientRepository.save(Mockito.any(Client.class))).thenReturn(TransactionServiceImplTest.client);
		Mockito.when(this.enterpriseRepository.save(Mockito.any(Enterprise.class))).thenReturn(TransactionServiceImplTest.enterprise);
		Mockito.when(this.transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(TransactionServiceImplTest.transaction);
		Mockito.when(this.sendCallBack.sendTransactionCallback(Mockito.any(Transaction.class))).thenReturn(TransactionServiceImplTest.transactionResponse);

		TransactionResponse transactionResponse = this.transactionService.makeTransaction(TransactionServiceImplTest.transactionRequest);

		assertNotNull(transactionResponse, "This transaction object is null.");
		assertDoesNotThrow(() -> this.clientRepository.findClientByCpf(Mockito.any(String.class)));
		assertDoesNotThrow(() -> this.enterpriseRepository.findEnterpriseByCnpj(Mockito.any(String.class)));
		assertDoesNotThrow(() -> this.clientRepository.save(Mockito.any(Client.class)));
		assertDoesNotThrow(() -> this.enterpriseRepository.save(Mockito.any(Enterprise.class)));
		assertDoesNotThrow(() -> this.transactionRepository.save(Mockito.any(Transaction.class)));
		assertDoesNotThrow(() -> this.sendCallBack.sendTransactionCallback(Mockito.any(Transaction.class)));
		assertDoesNotThrow(() -> this.transactionService.makeTransaction(TransactionServiceImplTest.transactionRequest));
		assertEquals(TransactionServiceImplTest.clientResponse, transactionResponse.client());
		assertEquals(TransactionServiceImplTest.enterpriseResponse, transactionResponse.enterprise());
		assertEquals(TransactionServiceImplTest.transactionResponse, transactionResponse);
	}
}