package dev.joaobertholino.clientbridge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {

	@Operation(
		summary = "Inserts a new customer.",
		description = "Inserts a new customer according to the contents of the requisition body, returning the inserted customer.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "System root resource", content = @Content(schema = @Schema(implementation = String.class), mediaType = "text/plain"))})
	@GetMapping(produces = "application/json")
	public ResponseEntity<String> root() {
		return ResponseEntity.status(HttpStatus.OK).body("This is the root of the system :)");
	}
}
