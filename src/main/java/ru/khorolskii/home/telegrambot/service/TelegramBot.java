package ru.khorolskii.home.telegrambot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.khorolskii.home.telegrambot.config.BotConfiguration;
import java.io.IOException;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfiguration configuration;
    private final OpenAiConnector openAiConnector;
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            switch (message){
                case "/start":
                    startCommand(chatId, update.getMessage().getChat().getUserName());
                    break;
                case "/status":
                    getStatus(chatId);
                    break;
                default:
                    sendMessage(chatId, "Здесь должно быть какое-то сообщение");
                    try {
                        openAiConnector.send(message);;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    @Override
    public String getBotUsername() {
        return configuration.getBotName();
    }
    @Override
    public String getBotToken(){
        return configuration.getToken();
    }

    private void startCommand(Long chatId, String name){
        String response = String.format("Привет %s! На связи чат помощник.\nПросто напиши вопрос и я отвечу на него ;-)", name);
        sendMessage(chatId, response);
    }

    private void sendMessage(Long chatId, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //TODO проработать отсылку сообщения с версией бота и статусом его работы. Работает/работает с ограничением/не работает
    private void getStatus(Long chatId){
        sendMessage(chatId,"Работает");
    }
}
