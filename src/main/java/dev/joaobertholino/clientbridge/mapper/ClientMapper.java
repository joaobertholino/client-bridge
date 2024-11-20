package dev.joaobertholino.clientbridge.mapper;

import dev.joaobertholino.clientbridge.model.Client;
import dev.joaobertholino.clientbridge.request.ClientRequest;
import dev.joaobertholino.clientbridge.response.ClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

	@Mapping(target = "id", ignore = true)
	Client buildClient(ClientRequest clientRequest);

	ClientResponse buildClientResponse(Client client);
}
