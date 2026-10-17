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
import ru.davydov.MySecondAppSpringBoot.service.*;
import ru.davydov.MySecondAppSpringBoot.util.DateTimeUtil;
import ru.davydov.MySecondAppSpringBoot.util.ResponseBuilder;

import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyNameRequestService;
    private final ModifyRequestService modifySourceRequestService;
    private final ModifyBonusResponseService modifyBonusResponseService;

    @Autowired
    public MyController(ValidationService validationService, @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        @Qualifier("ModifySystemNameRequestService") ModifyRequestService modifyNameRequestService, @Qualifier("ModifySourceRequestService") ModifyRequestService modifySourceRequestService,
                        ModifyBonusResponseService modifyBonusResponseService){

        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyNameRequestService = modifyNameRequestService;
        this.modifySourceRequestService = modifySourceRequestService;
        this.modifyBonusResponseService = modifyBonusResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult)
    throws UnsupportedCodeException, ValidationFailedException{

        log.info("request: {}", request);

        request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));

        Response response = ResponseBuilder.buildInitialResponse(request);

        log.info("response сформирован: {}", response);

        if ("123".equals(request.getUid())){
            throw new UnsupportedCodeException("Unsupported UID: " + request.getUid());
        }
        validationService.isValid(bindingResult);

        modifyBonusResponseService.modify(request, response);

        modifyResponseService.modify(response);
        log.info("response после модификации: {}", response);

        modifySourceRequestService.modify(request);
        log.info("request после модификации Source: {}", request);
        modifyNameRequestService.modify(request);
        log.info("request после модификации SystemName: {}", request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
