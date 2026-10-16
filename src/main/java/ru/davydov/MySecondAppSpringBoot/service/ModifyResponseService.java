package ru.davydov.MySecondAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.davydov.MySecondAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
