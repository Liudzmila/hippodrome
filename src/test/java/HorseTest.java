import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @Test
    public void whenNullHorseName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\r", "\t", "\n"})
    public void whenBlankHorseName(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1.0, 1.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void whenNegativeHorseSpeed() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -1.0, 1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void whenNegativeHorseDistance() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getName() {
        String expectedName = "Little Pony";
        Horse horse = new Horse(expectedName, 1.0, 1.0);
        assertEquals(expectedName, horse.getName());
    }

    @Test
    public void getSpeed() {
        double expectedSpeed = 5.0;
        Horse horse = new Horse("Horse", expectedSpeed, 1.0);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        double expectedDistance = 5.0;
        Horse horse = new Horse("Horse", 1.0, expectedDistance);
        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    public void getDistanceZeroByDefault() {
        Horse horse = new Horse("Horse", 1.0);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void moveInvokesGetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Little Pony", 1.0, 1.0);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "5.0, 10.0, 0.5",
            "2.0, 5.0, 0.4"
    })
    public void moveAssignDistanceValue(double speed, double distance, double randomValue) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Little Pony", speed, distance);
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            horse.move();
            double expectedDistance = distance + speed * randomValue;
            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}