package dev.joaobertholino.clientbridge.service;

import dev.joaobertholino.clientbridge.request.ClientRequest;
import dev.joaobertholino.clientbridge.response.ClientResponse;

public interface ClientService {
	ClientResponse insertClient(ClientRequest clientRequest);
}
