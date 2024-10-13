package dev.joaobertholino.clientbridge.service;

import dev.joaobertholino.clientbridge.model.Enterprise;
import dev.joaobertholino.clientbridge.response.EnterpriseResponse;

public interface EnterpriseService {
	EnterpriseResponse insertEnterprise(Enterprise enterprise);
}
