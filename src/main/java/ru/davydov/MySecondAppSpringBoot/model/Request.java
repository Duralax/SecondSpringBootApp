package ru.davydov.MySecondAppSpringBoot.model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    /***
     * Уникальный идентификатор сообщения
     */
    @NotBlank(message = "UID не может быть пустым")
    @Size(max = 32)
    private String uid;
    /***
     * Уникальный идентификатор операции
     */
    @NotBlank
    @Size(max = 32)
    private String operationUid;
    /***
     * Имя системы отправителя
     */
    private String systemName; //Systems
    /***
     * Время создания сообщения
     */
    @NotBlank
    private String systemTime;
    /***
     * Наименование ресурса
     */
    private String source;
    /***
     * Должность работника
     */
    private Positions position;
    /***
     * Заработная плата
     */
    private double salary;
    /***
     * Коэффициент премии
     */
    private double bonus;
    /***
     * Отработанные дни в году
     */
    @NotNull(message = "workDays не может быть пустым")
    @Min(value = 1, message = "workDays должен быть больше 0")
    private Integer workDays;
    /***
     * Уникальный идентификатор коммуникации
     */
    @Min(1)
    @Max(100000)
    private int communicationId;
    /***
     * Уникальный идентификатор шаблона
     */
    private int templateId;
    /***
     * Код продукта
     */
    private int productCode;
    /***
     * Смс код
     */
    private int smsCode;

    @Override
    public String toString(){
        return "{" +
                "uid=" + uid + '\'' +
                ", operationUid=" + operationUid + '\'' +
                ", systemName=" + systemName + '\'' +
                ", systemTime=" + systemTime + '\'' +
                ", source=" + source + '\'' +
                ", position=" + position + '\'' +
                ", salary=" + salary + '\'' +
                ", bonus=" + bonus + '\'' +
                ", workDays=" + workDays + '\'' +
                ", communicationId=" + communicationId + '\'' +
                ", templateId=" + templateId + '\'' +
                ", productCode=" + productCode + '\'' +
                ", smsCode=" + smsCode + '\'' +
                '}';
    }

}
