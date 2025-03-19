package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MessageSenderImplTests {

    @Test
    void testSendRussianMessage() {

        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");

        Location russianLocation = new Location(null, Country.RUSSIA, null, 0);
        when(geoService.byIp("172.0.32.11")).thenReturn(russianLocation);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        String message = messageSender.send(headers);

        assertEquals("Добро пожаловать", message);
    }

    @Test
    void testSendEnglishMessage() {

        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        Location americanLocation = new Location(null, Country.USA, null, 0);
        when(geoService.byIp("96.44.183.149")).thenReturn(americanLocation);
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        String message = messageSender.send(headers);

        assertEquals("Welcome", message);
    }
}