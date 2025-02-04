package com.example.todoapp.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoResponse {
    private String responseCode;
    private String responseMessage;
    private TodoInfo todoInfo;
}
