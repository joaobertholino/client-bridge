package dev.joaobertholino.clientbridge.service.implementation;

import dev.joaobertholino.clientbridge.mapper.ClientMapper;
import dev.joaobertholino.clientbridge.model.Client;
import dev.joaobertholino.clientbridge.repository.ClientRepository;
import dev.joaobertholino.clientbridge.request.ClientRequest;
import dev.joaobertholino.clientbridge.response.ClientResponse;
import dev.joaobertholino.clientbridge.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
	private final ClientRepository clientRepository;
	private final ClientMapper clientMapper;

	@Override
	public ClientResponse insertClient(ClientRequest clientRequest) {
		Client clientSaved = this.clientRepository.save(this.clientMapper.buildClient(clientRequest));
		return this.clientMapper.buildClientResponse(clientSaved);
	}
}
