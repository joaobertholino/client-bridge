package dev.joaobertholino.clientbridge.notification;

import dev.joaobertholino.clientbridge.enums.TransactionType;
import dev.joaobertholino.clientbridge.model.Client;

public interface NotificationComponent {
	void sendNotification(Client client, TransactionType transactionType, String relativeMessage);
}
