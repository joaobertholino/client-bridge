package dev.joaobertholino.clientbridge.webhook.impl;

import dev.joaobertholino.clientbridge.exceptions.response.ExceptionResponse;
import dev.joaobertholino.clientbridge.mapper.TransactionMapper;
import dev.joaobertholino.clientbridge.model.Transaction;
import dev.joaobertholino.clientbridge.response.TransactionResponse;
import dev.joaobertholino.clientbridge.webhook.SendCallBack;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class SendCallBackImpl implements SendCallBack {
	private final RestTemplate restTemplate;

	@Value(value = "${webhook.callback}")
	private String webHookUrl;

	@Override
	public TransactionResponse sendTransactionCallback(Transaction transaction) {
		TransactionResponse transactionResponse = TransactionMapper.buildTransactionResponse(transaction);
		this.restTemplate.postForObject(this.webHookUrl, transactionResponse, String.class);
		return transactionResponse;
	}

	@Override
	public void sendExceptionCallBack(ExceptionResponse exceptionResponse) {
		this.restTemplate.postForObject(this.webHookUrl, exceptionResponse, String.class);
	}
}