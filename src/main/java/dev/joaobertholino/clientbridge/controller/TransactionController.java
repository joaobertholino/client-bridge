package dev.joaobertholino.clientbridge.controller;

import dev.joaobertholino.clientbridge.request.TransactionRequest;
import dev.joaobertholino.clientbridge.response.TransactionResponse;
import dev.joaobertholino.clientbridge.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
@Tag(name = "Transaction Controller", description = "Controller regarding the execution of a new transaction between Client and Company.")
public class TransactionController {
	private TransactionService transactionService;

	@Operation(
			summary = "Executes a new transaction.",
			description = "Executes a new transaction, updates the data of the Customer and Company entities in the database, saves the record referring to the transaction in the database, sends a callback to the company and returns the transaction data.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Transaction carried out successfully.", content = {@Content(schema = @Schema(implementation = TransactionResponse.class), mediaType = "application/json")}),
			@ApiResponse(responseCode = "404", description = "Customer was not found in the database.", content = {@Content(schema = @Schema(implementation = ProblemDetail.class), mediaType = "application/json")}),
			@ApiResponse(responseCode = "404", description = "Company was not found in the database.", content = {@Content(schema = @Schema(implementation = ProblemDetail.class), mediaType = "application/json")}),
			@ApiResponse(responseCode = "400", description = "Invalid transaction.", content = {@Content(schema = @Schema(implementation = ProblemDetail.class), mediaType = "application/json")})})
	@PostMapping(value = "/make", produces = "application/json")
	public TransactionResponse makeTransaction(@RequestBody @Validated TransactionRequest transactionRequest) {
		return this.transactionService.makeTransaction(transactionRequest);
	}
}
