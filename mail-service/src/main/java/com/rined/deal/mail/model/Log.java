package com.rined.deal.mail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Getter
@Document("log")
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    @Id
    private String id;

    @Field("destination")
    private DestinationDto destinationDto;

    @Field("from")
    private SenderService sender;

    public Log(DestinationDto destinationDto, SenderService sender) {
        this.destinationDto = destinationDto;
        this.sender = sender;
    }
}
