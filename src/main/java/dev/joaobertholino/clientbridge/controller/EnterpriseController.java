package dev.joaobertholino.clientbridge.controller;

import dev.joaobertholino.clientbridge.request.EnterpriseRequest;
import dev.joaobertholino.clientbridge.response.EnterpriseResponse;
import dev.joaobertholino.clientbridge.service.EnterpriseService;
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
@RequestMapping("/enterprise")
@AllArgsConstructor
@Tag(name = "Enterprise Controller", description = "Controller referring to the Company entity.")
public class EnterpriseController {
	private EnterpriseService enterpriseService;

	@Operation(
		summary = "Find a Company",
		description = "It searches for a Company already entered in the database, returning the Company if it finds it, or returning an error if the CNPJ entered does not correspond to any Company saved in the database.")
	@ApiResponses({
		@ApiResponse(responseCode = "302", description = "Company found successfully.", content = {@Content(schema = @Schema(implementation = EnterpriseResponse.class), mediaType = "application/json")})})
	@GetMapping(value = "/find", produces = "application/json")
	public ResponseEntity<EnterpriseResponse> findEnterpriseByCnpj(@RequestParam String cnpj) {
		return ResponseEntity.status(HttpStatus.FOUND).body(this.enterpriseService.findEnterpriseByCnpj(cnpj));
	}

	@Operation(
			summary = "Inserts a new company.",
			description = "Inserts a new company by retrieving the data from the requisition body, returning the data of the entered company.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Company successfully inserted.", content = {@Content(schema = @Schema(implementation = EnterpriseResponse.class), mediaType = "application/json")})})
	@PostMapping(value = "/insert", produces = "application/json")
	public ResponseEntity<EnterpriseResponse> insertEnterprise(@RequestBody @Validated EnterpriseRequest enterpriseRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(this.enterpriseService.insertEnterprise(enterpriseRequest));
	}
}
