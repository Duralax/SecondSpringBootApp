package ru.davydov.MySecondAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.davydov.MySecondAppSpringBoot.model.Positions;

import java.time.Year;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService{
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays){

        int daysInYear = Year.now().length();

        return salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;
    }

    @Override
    public Double calculateQuarterlyBonus(Positions positions, double salary, double bonus, int workDays){
        if (!positions.isManager()) {
            return null;
        }

        int daysInYear = Year.now().length();
        double annualBonus = salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;

        return annualBonus / 4;
    }
}
