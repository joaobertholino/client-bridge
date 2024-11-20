package dev.joaobertholino.clientbridge.mapper;

import dev.joaobertholino.clientbridge.model.Client;
import dev.joaobertholino.clientbridge.model.Enterprise;
import dev.joaobertholino.clientbridge.model.Transaction;
import dev.joaobertholino.clientbridge.request.TransactionRequest;
import dev.joaobertholino.clientbridge.response.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.math.BigDecimal;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClientMapper.class, EnterpriseMapper.class})
public interface TransactionMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "dateTime", ignore = true)
	Transaction buildTransaction(Enterprise enterprise, Client client, TransactionRequest transactionRequest, BigDecimal feePercentage);

	TransactionResponse buildTransactionResponse(Transaction transaction);
}
