package sender.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

public class MessageSenderImplTests {
    GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
    LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
    MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
    Map<String, String> headers = new HashMap<>();

    @Test
    public void testRussianSend() {
        Mockito.when(geoService.byIp("172.0.32.11"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        headers.put(IP_ADDRESS_HEADER, "172.0.32.11");
        String result = messageSender.send(headers);
        String expected = "Добро пожаловать";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testEnglishSend() {
        Mockito.when(geoService.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        headers.put(IP_ADDRESS_HEADER, "96.44.183.149");
        String result = messageSender.send(headers);
        String expected = "Welcome";
        Assertions.assertEquals(expected, result);
    }
}
