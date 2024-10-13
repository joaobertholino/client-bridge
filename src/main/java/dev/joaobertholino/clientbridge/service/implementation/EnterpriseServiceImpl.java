package dev.joaobertholino.clientbridge.service.implementation;

import dev.joaobertholino.clientbridge.mapper.EnterpriseMapper;
import dev.joaobertholino.clientbridge.model.Enterprise;
import dev.joaobertholino.clientbridge.repository.EnterpriseRepository;
import dev.joaobertholino.clientbridge.response.EnterpriseResponse;
import dev.joaobertholino.clientbridge.service.EnterpriseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnterpriseServiceImpl implements EnterpriseService {
	private final EnterpriseRepository enterpriseRepository;

	@Override
	public EnterpriseResponse insertEnterprise(Enterprise enterprise) {
		Enterprise enterpriseSaved = this.enterpriseRepository.save(enterprise);
		return EnterpriseMapper.buildEnterpriseResponse(enterpriseSaved);
	}
}
