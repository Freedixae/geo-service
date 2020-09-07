package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

class MessageTest {
    @org.junit.jupiter.api.Test
    void mRusTest() {
        //given
        GeoServiceImpl gs = Mockito.mock(GeoServiceImpl.class);
        Location location = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Mockito.when(gs.byIp(GeoServiceImpl.MOSCOW_IP)).thenReturn(location);
        LocalizationServiceImpl ls = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(ls.locale(location.getCountry())).thenCallRealMethod();
        //when
        MessageSenderImpl ms = new MessageSenderImpl(gs, ls);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        ms.send(headers);
        Map<String, String> expected = new HashMap<String, String>();
        expected.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        //then
        Assertions.assertNotEquals(headers, expected);
    }

    @org.junit.jupiter.api.Test
    void mEngTest() {
        //given
        GeoServiceImpl gs = Mockito.mock(GeoServiceImpl.class);
        Location location = new Location("New York", Country.USA, " 10th Avenue", 32);
        Mockito.when(gs.byIp(GeoServiceImpl.NEW_YORK_IP)).thenReturn(location);
        LocalizationServiceImpl ls = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(ls.locale(location.getCountry())).thenCallRealMethod();
        //when
        MessageSenderImpl ms = new MessageSenderImpl(gs, ls);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        ms.send(headers);
        Map<String, String> expected = new HashMap<String, String>();
        expected.put(MessageSenderImpl.IP_ADDRESS_HEADER, "127.0.0.1");
        //
        Assertions.assertNotEquals(headers, expected);
    }

    @org.junit.jupiter.api.Test
    void locationIP() {
        //given
        GeoServiceImpl gs = new GeoServiceImpl();
        //when
        Location locationM = gs.byIp(GeoServiceImpl.MOSCOW_IP);
        Location locationNY = gs.byIp(GeoServiceImpl.NEW_YORK_IP);
        //then
        Assertions.assertNotEquals(locationM, locationNY);
    }

    @org.junit.jupiter.api.Test
    void returnText() {
        //given
        LocalizationServiceImpl ls = new LocalizationServiceImpl();
        //when
        String textRUS = ls.locale(Country.RUSSIA);
        String textENG = ls.locale(Country.USA);
        //then
        Assertions.assertNotEquals(textENG, textRUS);
    }
}