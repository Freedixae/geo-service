package ru.netology.test.sender;

import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {
    @org.junit.jupiter.api.Test
    void messageRusTest() {
        //given
        Location location = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        GeoServiceImpl gs = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(gs.byIp(GeoServiceImpl.MOSCOW_IP)).thenReturn(location);
        LocalizationServiceImpl ls = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(ls.locale(location.getCountry())).thenCallRealMethod();
        MessageSender resultMessage = new MessageSenderImpl(gs, ls);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        String result = resultMessage.send(resultMap);
        //when
        GeoServiceImpl geoService = new GeoServiceImpl();
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        MessageSenderImpl expectedMessage = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");
        String expected = expectedMessage.send(expectedMap);
        //then
        Assertions.assertEquals(result, expected);
    }

    @org.junit.jupiter.api.Test
    void messageEngTest() {
        //given
        GeoServiceImpl gs = Mockito.mock(GeoServiceImpl.class);
        Location location = new Location("New York", Country.USA, " 10th Avenue", 32);
        Mockito.when(gs.byIp(GeoServiceImpl.NEW_YORK_IP)).thenReturn(location);
        LocalizationServiceImpl ls = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(ls.locale(location.getCountry())).thenCallRealMethod();
        MessageSender resultMessage = new MessageSenderImpl(gs, ls);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        String result =  resultMessage.send(headers);
        //when
        GeoService geoService = new GeoServiceImpl();
        LocalizationService localizationService = new LocalizationServiceImpl();
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> expected = new HashMap<>();
        expected.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.");
        String exepted = messageSender.send(expected);
        //then
        Assertions.assertEquals(result, exepted);
    }
}
