package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

public class GeoServiceImplTests {

    private final GeoService geoService = new GeoServiceImpl();

    @ParameterizedTest
    @MethodSource("ipLocationProvider")
    void testByIp(String ip, Location expectedLocation) {
        Location actualLocation = geoService.byIp(ip);
        if (expectedLocation == null) {
            Assertions.assertNull(actualLocation);
        } else {
            Assertions.assertEquals(expectedLocation.getCity(), actualLocation.getCity());
            Assertions.assertEquals(expectedLocation.getCountry(), actualLocation.getCountry());
            Assertions.assertEquals(expectedLocation.getStreet(), actualLocation.getStreet());
            Assertions.assertEquals(expectedLocation.getBuiling(), actualLocation.getBuiling());
        }
    }

    private static Stream<Arguments> ipLocationProvider() {
        return Stream.of(
                Arguments.of(GeoServiceImpl.LOCALHOST, new Location(null, null, null, 0)),
                Arguments.of(GeoServiceImpl.MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(GeoServiceImpl.NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.16.0.1", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.127.0.1", new Location("New York", Country.USA, null, 0)),
                Arguments.of("192.168.1.1", null) //  Неизвестный IP
        );
    }

    @Test
    public void testByCoordinates() {
        GeoServiceImpl geoService = new GeoServiceImpl();

        Assertions.assertThrows(RuntimeException.class, () -> {
            geoService.byCoordinates(55.7558, 37.6176);
        }, "Exception");
    }
}
