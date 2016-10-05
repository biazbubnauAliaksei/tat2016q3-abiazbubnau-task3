package com.epam.homework.product.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.file.Path;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageWithAttach {
    private String email;
    private String subject;
    private String body;
    private List<Path> attaches;
}
