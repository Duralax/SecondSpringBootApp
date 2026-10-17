package ru.davydov.MySecondAppSpringBoot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    /***
     * Уникальный идентификатор сообщение
     */
    private String uid;
    /***
     * Уникальный идентификатор операции
     */
    private String operationUid;
    /***
     * Время создания сообщения
     */
    private String systemTime;
    /***
     * Сообщение об ошибке
     */
    private Codes code;
    /***
     * Премия
     */
    private Double annualBonus;
    /***
     * Квартальная премия
     */
    private Double quarterlyBonus;
    /***
     * Код ошибки
     */
    private ErrorCodes errorCode;
    /***
     * Сообщение ошибки
     */
    private ErrorMessages errorMessage;

    @Override
    public String toString(){
        return "{" +
                "uid=" + uid + '\'' +
                ", operationUid=" + operationUid + '\'' +
                ", systemTime=" + systemTime + '\'' +
                ", code=" + code + '\'' +
                ", annualBonus=" + annualBonus + '\'' +
                ", quarterlyBonus=" + quarterlyBonus + '\'' +
                ", ErrorCodes=" + errorCode + '\'' +
                ", ErrorMessages=" + errorMessage + '\'' +
                '}';
    }
}
