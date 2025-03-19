package ru.netology.i18n;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalizationServiceImplTests {

    @ParameterizedTest
    @EnumSource(Country.class)
    void testLocale(Country country) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected;
        switch (country) {
            case RUSSIA:
                expected = "Добро пожаловать";
                break;
            default:
                expected = "Welcome";
                break;
        }

        String actual = localizationService.locale(country);
        assertEquals(expected, actual);
    }
}