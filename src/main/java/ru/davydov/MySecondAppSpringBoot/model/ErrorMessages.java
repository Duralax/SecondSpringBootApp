package ru.davydov.MySecondAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages {
    EMPTY(""),
    VALIDATION("Ошибка валидации"),
    UNKNOWN("Не поддерживаемая ошибка"),
    UNSUPPORTED_CODE("Не поддерживаемый UID"),
    UNSUPPORTED_POSITION("Квартальная премия недоступна для данной должности");


    private final String description;

    ErrorMessages(String description){
        this.description = description;
    }

    @JsonValue
    public String getName(){
        return description;
    }

    @Override
    public String toString(){
        return description;
    }
}
