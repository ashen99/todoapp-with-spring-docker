package com.example.todoapp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoInfo {
    private Long id;
    private String todoTittle;
    private String todoDescription;
}
