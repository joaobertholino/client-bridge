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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
@Tag(name = "Client Controller", description = "Controller for the Customer entity.")
public class ClientController {
	private ClientService clientService;

	@Operation(
		summary = "Find a customer.",
		description = "It searches for a Customer already entered in the database, returning the Customer if it finds it, or returning an error if the CPF entered does not correspond to any Customer saved in the database.")
	@ApiResponses({
		@ApiResponse(responseCode = "302", description = "Customer found successfully.", content = {@Content(schema = @Schema(implementation = ClientResponse.class), mediaType = "application/json")})})
	@GetMapping(value = "/find/client", produces = "application/json")
	public ResponseEntity<ClientResponse> findClientByCpf(String cpf) {
		return ResponseEntity.status(HttpStatus.FOUND).body(this.clientService.findClientByCpf(cpf));
	}

	@Operation(
			summary = "Inserts a new customer.",
			description = "Inserts a new customer according to the contents of the requisition body, returning the inserted customer.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Customer successfully inserted.", content = {@Content(schema = @Schema(implementation = ClientResponse.class), mediaType = "application/json")})})
	@PostMapping(value = "/insert", produces = "application/json")
	public ResponseEntity<ClientResponse> insertClient(@RequestBody @Validated ClientRequest clientRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(this.clientService.insertClient(clientRequest));
	}

}
