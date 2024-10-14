package dev.joaobertholino.clientbridge.mapper;

import dev.joaobertholino.clientbridge.model.Enterprise;
import dev.joaobertholino.clientbridge.request.EnterpriseRequest;
import dev.joaobertholino.clientbridge.response.EnterpriseResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
public class EnterpriseMapper {
	public static Enterprise buildEnterprise(@Validated EnterpriseRequest enterpriseRequest) {
		return new Enterprise(null,
				enterpriseRequest.name(),
				enterpriseRequest.cnpj(),
				enterpriseRequest.balance());
	}

	public static EnterpriseResponse buildEnterpriseResponse(@Validated Enterprise enterprise) {
		return new EnterpriseResponse(enterprise.getId(),
				enterprise.getName(),
				enterprise.getCnpj(),
				enterprise.getBalance());
	}
}
