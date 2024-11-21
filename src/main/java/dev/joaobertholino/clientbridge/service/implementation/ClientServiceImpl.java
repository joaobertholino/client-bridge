package dev.joaobertholino.clientbridge.service.implementation;

import dev.joaobertholino.clientbridge.exceptions.ClientNotFoundException;
import dev.joaobertholino.clientbridge.mapper.ClientMapper;
import dev.joaobertholino.clientbridge.model.Client;
import dev.joaobertholino.clientbridge.repository.ClientRepository;
import dev.joaobertholino.clientbridge.request.ClientRequest;
import dev.joaobertholino.clientbridge.response.ClientResponse;
import dev.joaobertholino.clientbridge.service.ClientService;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
	private final ClientRepository clientRepository;
	private final ClientMapper clientMapper;

	@Override
	public ClientResponse findClientByCpf(@CPF String clientCpf) {
		Optional<Client> client = this.clientRepository.findClientByCpf(clientCpf);
		return this.clientMapper.buildClientResponse(client.orElseThrow(() -> new ClientNotFoundException("Customer was not found.")));
	}

	@Override
	public ClientResponse insertClient(ClientRequest clientRequest) {
		Client clientSaved = this.clientRepository.save(this.clientMapper.buildClient(clientRequest));
		return this.clientMapper.buildClientResponse(clientSaved);
	}
}
