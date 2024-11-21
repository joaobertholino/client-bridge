package dev.joaobertholino.clientbridge.service.implementation;

import dev.joaobertholino.clientbridge.exceptions.EnterpriseNotFoundException;
import dev.joaobertholino.clientbridge.mapper.EnterpriseMapper;
import dev.joaobertholino.clientbridge.model.Enterprise;
import dev.joaobertholino.clientbridge.repository.EnterpriseRepository;
import dev.joaobertholino.clientbridge.request.EnterpriseRequest;
import dev.joaobertholino.clientbridge.response.EnterpriseResponse;
import dev.joaobertholino.clientbridge.service.EnterpriseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EnterpriseServiceImpl implements EnterpriseService {
	private final EnterpriseRepository enterpriseRepository;
	private final EnterpriseMapper enterpriseMapper;

	@Override
	public EnterpriseResponse findEnterpriseByCnpj(String cnpj) {
		Optional<Enterprise> enterprise = this.enterpriseRepository.findEnterpriseByCnpj(cnpj);
		return this.enterpriseMapper.buildEnterpriseResponse(enterprise.orElseThrow(() -> new EnterpriseNotFoundException("Enterprise was not found.")));
	}

	@Override
	public EnterpriseResponse insertEnterprise(EnterpriseRequest enterpriseRequest) {
		Enterprise enterpriseSaved = this.enterpriseRepository.save(this.enterpriseMapper.buildEnterprise(enterpriseRequest));
		return this.enterpriseMapper.buildEnterpriseResponse(enterpriseSaved);
	}
}
