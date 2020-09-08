package ru.netology.test.i18n;

import org.junit.jupiter.api.Assertions;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {
    @org.junit.jupiter.api.Test
    void LocalizationServiceImplTest() {
        //given
        Country countryRUS = Country.RUSSIA;
        Country countryENG = Country.BRAZIL;
        LocalizationServiceImpl ls = new LocalizationServiceImpl();
        String resultTextRUS = ls.locale(countryRUS);
        String resultTextENG = ls.locale(countryENG);
        //when
        String expectedTextRUS = ls.locale(Country.RUSSIA);
        String expectedTextENG = ls.locale(Country.USA);
        //then
        Assertions.assertEquals(resultTextRUS, expectedTextRUS);
        Assertions.assertEquals(resultTextENG, expectedTextENG);
    }
}