package dev.joaobertholino.clientbridge.notification.implementation;

import dev.joaobertholino.clientbridge.enums.TransactionType;
import dev.joaobertholino.clientbridge.model.Client;
import dev.joaobertholino.clientbridge.notification.NotificationComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationComponentImpl implements NotificationComponent {
	private final JavaMailSender javaMailSender;

	@Override
	public void sendNotification(Client client, TransactionType transactionType, String relativeMessage) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(client.getEmail());
		message.setText(transactionType.getTransactionType() + " " + relativeMessage);

		this.javaMailSender.send(message);
	}
}
