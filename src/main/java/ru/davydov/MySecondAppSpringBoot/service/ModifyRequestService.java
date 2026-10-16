package ru.davydov.MySecondAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.davydov.MySecondAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
