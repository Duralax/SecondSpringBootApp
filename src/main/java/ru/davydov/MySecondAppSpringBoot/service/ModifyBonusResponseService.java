package ru.davydov.MySecondAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.davydov.MySecondAppSpringBoot.model.Request;
import ru.davydov.MySecondAppSpringBoot.model.Response;

@Service
public interface ModifyBonusResponseService {
    void modify(Request request, Response response);
}
