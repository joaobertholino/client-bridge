package dev.joaobertholino.clientbridge.controller;

import dev.joaobertholino.clientbridge.enums.TransactionType;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
@Tag(name = "Transaction Controller", description = "Controller regarding the execution of a new transaction between Client and Company.")
public class TransactionController {
	private TransactionService transactionService;

	@Operation(
		summary = "Find a transaction.",
		description = "Search for a list of Transactions saved in the database according to the given type, returns the list of transactions if it exists, or returns an error if there are no transactions.")
	@ApiResponses({
		@ApiResponse(responseCode = "302", description = "Transaction found successfully.", content = {@Content(schema = @Schema(implementation = TransactionResponse.class), mediaType = "application/json")}),
		@ApiResponse(responseCode = "404", description = "Transaction not found", content = {@Content(schema = @Schema(implementation = ProblemDetail.class), mediaType = "application/json")})})
	@GetMapping(value = "/find", produces = "application/json")
	public ResponseEntity<List<TransactionResponse>> findTransactionByType(@RequestParam TransactionType type) {
		return ResponseEntity.status(HttpStatus.FOUND).body(this.transactionService.findTransactionByType(type));
	}

	@Operation(
		summary = "Executes a new transaction.",
		description = "Executes a new transaction, updates the data of the Customer and Company entities in the database, saves the record referring to the transaction in the database, sends a callback to the company and returns the transaction data.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Transaction carried out successfully.", content = {@Content(schema = @Schema(implementation = TransactionResponse.class), mediaType = "application/json")}),
		@ApiResponse(responseCode = "404", description = "Customer was not found in the database.", content = {@Content(schema = @Schema(implementation = ProblemDetail.class), mediaType = "application/json")}),
		@ApiResponse(responseCode = "404", description = "Company was not found in the database.", content = {@Content(schema = @Schema(implementation = ProblemDetail.class), mediaType = "application/json")}),
		@ApiResponse(responseCode = "400", description = "Invalid transaction.", content = {@Content(schema = @Schema(implementation = ProblemDetail.class), mediaType = "application/json")})})
	@PostMapping(value = "/make", produces = "application/json")
	public ResponseEntity<TransactionResponse> makeTransaction(@RequestBody @Validated TransactionRequest transactionRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(this.transactionService.makeTransaction(transactionRequest));
	}
}
