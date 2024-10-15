package dev.joaobertholino.clientbridge.service.implementation;

import dev.joaobertholino.clientbridge.model.Enterprise;
import dev.joaobertholino.clientbridge.repository.EnterpriseRepository;
import dev.joaobertholino.clientbridge.request.EnterpriseRequest;
import dev.joaobertholino.clientbridge.response.EnterpriseResponse;
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
class EnterpriseServiceImplTest {
	private static EnterpriseResponse enterpriseResponse;
	private static EnterpriseRequest enterpriseRequest;
	private static Enterprise enterprise;

	@InjectMocks
	private EnterpriseServiceImpl enterpriseService;

	@Mock
	private EnterpriseRepository enterpriseRepository;

	@BeforeAll
	static void setUp() {
		EnterpriseServiceImplTest.enterpriseResponse = new EnterpriseResponse(UUID.randomUUID(), "Google", "54.160.463/0001-40", BigDecimal.valueOf(100));
		EnterpriseServiceImplTest.enterpriseRequest = new EnterpriseRequest("Google", "54.160.463/0001-40", BigDecimal.valueOf(100));
		EnterpriseServiceImplTest.enterprise = new Enterprise(EnterpriseServiceImplTest.enterpriseResponse.id(), "Google", "54.160.463/0001-40", BigDecimal.valueOf(100));
	}

	@Test
	void insertEnterprise() {
		Mockito.when(this.enterpriseRepository.save(Mockito.any(Enterprise.class))).thenReturn(EnterpriseServiceImplTest.enterprise);
		EnterpriseResponse enterpriseResponse = this.enterpriseService.insertEnterprise(EnterpriseServiceImplTest.enterpriseRequest);

		assertNotNull(enterpriseResponse, "This object of type company code is null.");
		assertDoesNotThrow(() -> this.enterpriseRepository.save(Mockito.any(Enterprise.class)));
		assertDoesNotThrow(() -> this.enterpriseService.insertEnterprise(EnterpriseServiceImplTest.enterpriseRequest));
		assertEquals(EnterpriseServiceImplTest.enterpriseResponse, enterpriseResponse);
		assertEquals(EnterpriseServiceImplTest.enterpriseResponse.name(), enterpriseResponse.name());
		assertEquals(EnterpriseServiceImplTest.enterpriseResponse.cnpj(), enterpriseResponse.cnpj());
		assertEquals(EnterpriseServiceImplTest.enterpriseResponse.balance(), enterpriseResponse.balance());
	}
}