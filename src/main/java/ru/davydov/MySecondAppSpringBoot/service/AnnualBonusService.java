package ru.davydov.MySecondAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.davydov.MySecondAppSpringBoot.model.Positions;

@Service
public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
    Double calculateQuarterlyBonus(Positions positions, double salary, double bonus, int workDays);
}
