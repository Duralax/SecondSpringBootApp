package ru.davydov.MySecondAppSpringBoot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.davydov.MySecondAppSpringBoot.model.Request;
import ru.davydov.MySecondAppSpringBoot.model.Response;

@Slf4j
@Service
public class ModifyBonusResponseServiceImpl implements ModifyBonusResponseService{

    private final AnnualBonusService annualBonusService;

    public ModifyBonusResponseServiceImpl(AnnualBonusService annualBonusService) {
        this.annualBonusService = annualBonusService;
    }

    @Override
    public void modify(Request request, Response response){
        double annualBonus = annualBonusService.calculate(
                request.getPosition(),
                request.getSalary(),
                request.getBonus(),
                request.getWorkDays()
        );
        response.setAnnualBonus(annualBonus);

        Double quarterlyBonus = annualBonusService.calculateQuarterlyBonus(
                request.getPosition(),
                request.getSalary(),
                request.getBonus(),
                request.getWorkDays()
        );
        response.setQuarterlyBonus(quarterlyBonus);

        log.info("response после вычисления премий: {}", response);
    }

}
