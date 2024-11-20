package dev.joaobertholino.clientbridge.mapper;

import dev.joaobertholino.clientbridge.model.Enterprise;
import dev.joaobertholino.clientbridge.request.EnterpriseRequest;
import dev.joaobertholino.clientbridge.response.EnterpriseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnterpriseMapper {

	@Mapping(target = "id", ignore = true)
	Enterprise buildEnterprise(EnterpriseRequest enterpriseRequest);

	EnterpriseResponse buildEnterpriseResponse(Enterprise enterprise);
}
