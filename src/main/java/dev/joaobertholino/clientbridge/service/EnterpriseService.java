package dev.joaobertholino.clientbridge.service;

import dev.joaobertholino.clientbridge.request.EnterpriseRequest;
import dev.joaobertholino.clientbridge.response.EnterpriseResponse;

public interface EnterpriseService {
	EnterpriseResponse insertEnterprise(EnterpriseRequest enterprise);
}
