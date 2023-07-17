package com.example.Todoserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TodoRequest {
    private String title;
    private Long order;
    private Boolean completed;

}
