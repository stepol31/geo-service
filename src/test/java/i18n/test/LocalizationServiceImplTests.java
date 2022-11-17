package i18n.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static ru.netology.entity.Country.*;

public class LocalizationServiceImplTests {
    LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @MethodSource("source")
    @ParameterizedTest
    public void testLocale(Country country, String expected) {
        String result = localizationService.locale(country);
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of(RUSSIA, "Добро пожаловать"),
                Arguments.of(USA, "Welcome"),
                Arguments.of(GERMANY, "Welcome"));
    }
}
