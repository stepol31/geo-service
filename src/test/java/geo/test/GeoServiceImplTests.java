package geo.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTests {
    GeoServiceImpl geoService = new GeoServiceImpl();
    Location result = null;

    @MethodSource("source")
    @ParameterizedTest
    public void testByIp(String ip, Location expected) {
        result = geoService.byIp(ip);
        Assertions.assertSame(expected.getCity(), result.getCity());
        Assertions.assertSame(expected.getCountry(), result.getCountry());
        Assertions.assertSame(expected.getStreet(), result.getStreet());
        Assertions.assertSame(expected.getBuilding(), result.getBuilding());
    }

    public static Stream<Arguments> source() {

        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.0.50.11", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.0.32.11", new Location("New York", Country.USA, null, 0)));
    }


}
