package dev.joaobertholino.clientbridge.mapper;

import dev.joaobertholino.clientbridge.model.Client;
import dev.joaobertholino.clientbridge.request.ClientRequest;
import dev.joaobertholino.clientbridge.response.ClientResponse;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
	public static Client buildClient(ClientRequest clientRequest) {
		return new Client(null,
				clientRequest.firstName(),
				clientRequest.lastName(),
				clientRequest.email(),
				clientRequest.cpf(),
				clientRequest.balance());
	}

	public static ClientResponse buildClientResponse(Client client) {
		return new ClientResponse(client.getId(),
				client.getFirstName(),
				client.getLastName(),
				client.getEmail(),
				client.getCpf(),
				client.getBalance());
	}
}