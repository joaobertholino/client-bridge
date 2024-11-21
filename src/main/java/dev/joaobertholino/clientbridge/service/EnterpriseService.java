package dev.joaobertholino.clientbridge.service;

import dev.joaobertholino.clientbridge.request.EnterpriseRequest;
import dev.joaobertholino.clientbridge.response.EnterpriseResponse;

public interface EnterpriseService {
	EnterpriseResponse findEnterpriseByCnpj(String cnpj);
	EnterpriseResponse insertEnterprise(EnterpriseRequest enterprise);
}
