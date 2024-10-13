package dev.joaobertholino.clientbridge.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI openApi() {
		Contact contact = new Contact()
				.name("João Bertholino")
				.email("comercial.bertholino@gmail.com");

		return new OpenAPI().info(new Info()
				.title("Client Bridge API")
				.description("API for transactions between customers and businesses.")
				.version("1.0")
				.contact(contact));
	}
}
