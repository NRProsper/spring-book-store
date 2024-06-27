package dev.kiki.bookstore.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse <T>{

    private T data;
    private String message;
    private int statusCode;

}
