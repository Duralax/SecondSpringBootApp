package ru.davydov.MySecondAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.davydov.MySecondAppSpringBoot.exception.UnsupportedCodeException;
import ru.davydov.MySecondAppSpringBoot.exception.ValidationFailedException;
import ru.davydov.MySecondAppSpringBoot.model.*;
import ru.davydov.MySecondAppSpringBoot.service.ModifyRequestService;
import ru.davydov.MySecondAppSpringBoot.service.ModifyResponseService;
import ru.davydov.MySecondAppSpringBoot.service.ValidationService;
import ru.davydov.MySecondAppSpringBoot.util.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyNameRequestService;
    private final ModifyRequestService modifySourceRequestService;

    @Autowired
    public MyController(ValidationService validationService, @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        @Qualifier("ModifySystemNameRequestService") ModifyRequestService modifyNameRequestService, @Qualifier("ModifySourceRequestService") ModifyRequestService modifySourceRequestService
                        ){

        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyNameRequestService = modifyNameRequestService;
        this.modifySourceRequestService = modifySourceRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult){

        log.info("request: {}", request);

        request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
        log.info("response сформирован: {}", response);

        try {
            if ("123".equals(request.getUid())){
                throw new UnsupportedCodeException("Unsupported UID: " + request.getUid());
            }
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            log.error("Ошибка валидации: {}", bindingResult.getAllErrors());

            log.error("Ошибка на валидации, response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_CODE_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED_CODE);
            log.error("Ошибка с uid, response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            log.error("Ошибка, response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyResponseService.modify(response);
        log.info("response после модификации: {}", response);

        modifySourceRequestService.modify(request);
        log.info("request после модификации Source: {}", request);
        modifyNameRequestService.modify(request);
        log.info("request после модификации SystemName: {}", request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
