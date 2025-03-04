package com.example.todo_checklist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.stream.CharacterStreamWritingMessageHandler;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
@IntegrationComponentScan
public class IntegrationConfig {

    @Bean
    public MessageChannel fileChannel() {
        return new DirectChannel();
    }

    @Bean
    public FileWritingMessageHandler fileWriter() {
        File directory = new File("tasks");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        FileWritingMessageHandler handler = new FileWritingMessageHandler(directory);
        handler.setAppendNewLine(true);
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setFileNameGenerator(message -> "tasks.txt");

        return handler;
    }

    @Bean
    public CharacterStreamWritingMessageHandler consoleWriter() {
        return CharacterStreamWritingMessageHandler.stdout();
    }
}