package com.bancolombia.prestamos.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuditoriaInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ){
        System.out.println("Solicitud entrante;" + request.getMethod());
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception exception
    ) throws Exception{
        System.out.println("Operacion completada para: " + request.getMethod() + " " + request.getRequestURI()  +
                " Estado Respuesta: " + response.getStatus());
    }
}
