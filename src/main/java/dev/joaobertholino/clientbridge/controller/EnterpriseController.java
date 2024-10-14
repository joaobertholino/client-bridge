package dev.joaobertholino.clientbridge.controller;

import dev.joaobertholino.clientbridge.mapper.EnterpriseMapper;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enterprise")
@AllArgsConstructor
@Tag(name = "Enterprise Controller", description = "Controller referring to the Company entity.")
public class EnterpriseController {
	private EnterpriseService enterpriseService;

	@Operation(
			summary = "Inserts a new company.",
			description = "Inserts a new company by retrieving the data from the requisition body, returning the data of the entered company.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Company successfully inserted.", content = {@Content(schema = @Schema(implementation = EnterpriseResponse.class), mediaType = "application/json")})})
	@PostMapping("/insert")
	public EnterpriseResponse insertEnterprise(@RequestBody @Validated EnterpriseRequest enterpriseRequest) {
		return this.enterpriseService.insertEnterprise(enterpriseRequest);
	}
}
