package ru.khorolskii.home.telegrambot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Data
@PropertySource("application.properties")
public class OpenAiConfiguration {

    @Value("${openAi.name}")
    String botName;
    @Value("${openAi.token}")
    String token;
    @Value("${openAi.url}")
    String url;

    @Bean
    public WebClient getWebClient(){
        return WebClient.create();
    }
}
