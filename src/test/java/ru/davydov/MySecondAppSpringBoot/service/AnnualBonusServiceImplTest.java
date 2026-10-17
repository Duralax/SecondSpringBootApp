package ru.davydov.MySecondAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.davydov.MySecondAppSpringBoot.model.Positions;

import java.time.Year;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {
        // given
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        // when
        double result = new AnnualBonusServiceImpl().calculate(position, salary, bonus, workDays);

        // then
        int daysInYear = Year.now().length();
        double expected = salary * bonus * daysInYear * position.getPositionCoefficient() / workDays;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateQuarterlyBonusManager() {
        // given
        Positions position = Positions.TL;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        // when
        Double result = new AnnualBonusServiceImpl().calculateQuarterlyBonus(position, salary, bonus, workDays);

        // then
        int daysInYear = Year.now().length();
        double expected = (salary * bonus * daysInYear * position.getPositionCoefficient() / workDays) / 4;

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateQuarterlyBonusNonManager() {
        // given
        Positions position = Positions.DEV;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        // when
        Double result = new AnnualBonusServiceImpl().calculateQuarterlyBonus(position, salary, bonus, workDays);

        // then
        assertThat(result).isNull();
    }

}