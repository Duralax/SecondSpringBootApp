package ru.davydov.MySecondAppSpringBoot.util;

import ru.davydov.MySecondAppSpringBoot.model.*;

import java.util.Date;

public class ResponseBuilder {
    public static Response buildInitialResponse(Request request){
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }
}
