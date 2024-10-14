package dev.joaobertholino.clientbridge.controller;

import dev.joaobertholino.clientbridge.request.ClientRequest;
import dev.joaobertholino.clientbridge.response.ClientResponse;
import dev.joaobertholino.clientbridge.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
@Tag(name = "Client Controller", description = "Controller for the Customer entity.")
public class ClientController {
	private ClientService clientService;

	@Operation(
			summary = "Inserts a new customer.",
			description = "Inserts a new customer according to the contents of the requisition body, returning the inserted customer.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Customer successfully inserted.", content = {@Content(schema = @Schema(implementation = ClientResponse.class), mediaType = "application/json")})})
	@PostMapping("/insert")
	public ClientResponse insertClient(@RequestBody @Validated ClientRequest clientRequest) {
		return this.clientService.insertClient(clientRequest);
	}
}
