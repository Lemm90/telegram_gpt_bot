package ru.khorolskii.home.telegrambot.dto;

import lombok.Data;

import java.util.List;

@Data
public class RequestDto {
    private String model;
    private List<Message> messages;


    public RequestDto(String content) {
        this.model = "text-davinci-002";
        Message message = new Message(content);
        System.out.println("КОНСТРУКТОР REQUEST" + "model:" + model + "message" + message.toString());
    }

    @Data
    public static class Message {
        private String role;
        private String content;

        public Message(String content) {
            this.role = "user";
            this.content = content;
            System.out.println("КОНСТРУКТОР MESSAGE " + "role: " + role + " content" + content);
        }
    }

    @Override
    public String toString() {
        return "RequestDto{" +
                "model='" + model + '\'' +
                ", messages=" + messages +
                '}';
    }
}
