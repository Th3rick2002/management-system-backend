package com.managementsystem.managementsystembackend.Handlers;

import com.managementsystem.managementsystembackend.DTO.GlobalResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response
    ) {
        ServletServerHttpResponse servletResponse = (ServletServerHttpResponse) response;
        int status = servletResponse.getServletResponse().getStatus();
        if (body instanceof GlobalResponse<?> || ( status >= 400 )) {
            return body;
        }

        String message = "";

        switch (status) {
            case 200:
                message = "OK";
                break;
            case 201:
                message = "Created";
                break;
            case 204:
                message = "No Content";
                break;
            case 400:
                message = "Bad Request";
                break;
            case 401:
                message = "Unauthorized";
                break;
            case 403:
                message = "Forbidden";
                break;
            case 404:
                message = "Not Found";
                break;
            case 409:
                message = "Conflict";
                break;
            case 500:
                message = "Internal Server Error";
                break;
        }

        return new GlobalResponse<>(
                LocalDateTime.now(),
                status,
                message,
                body
        );
    }
}
