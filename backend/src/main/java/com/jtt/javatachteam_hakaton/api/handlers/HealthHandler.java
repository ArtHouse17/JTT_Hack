package com.jtt.javatachteam_hakaton.api.handlers;

import io.javalin.http.Context;

public class HealthHandler {

    // Пустой конструктор, так как для health-чека зависимости не нужны
    public HealthHandler() {}

    public void health(Context ctx) {
        // Возвращаем простой JSON-ответ, подтверждающий, что сервер жив
        ctx.status(200).json("{\"status\": \"UP\"}");
    }
}