package ru.netology.test.geo;

import org.junit.jupiter.api.Assertions;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {
    @org.junit.jupiter.api.Test
    void GeoServiceImplTest() {
        //given
        String ipMoscow = "172.";
        String ipNY = "96.";
        GeoServiceImpl gs = new GeoServiceImpl();
        Location resultLocationM = gs.byIp(ipMoscow);
        Location resultLocationNY = gs.byIp(ipNY);
        //when
        Location exeptedLocationM = new Location("Moscow", Country.RUSSIA, null, 0);
        Location exeptedLocationNY = new Location("New York", Country.USA, null,  0);
        //then
        Assertions.assertEquals(resultLocationM, exeptedLocationM);
        Assertions.assertEquals(resultLocationNY, exeptedLocationNY);
    }
}