package com.epam.homework.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Message {
    private final String email;
    private final String subject;
    private final String body;
}
