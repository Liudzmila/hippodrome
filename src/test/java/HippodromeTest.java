import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    public void nullHorses() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void emptyHorses() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Horse1_President", 10.0, 1.0));
        horses.add(new Horse("Horse2_Lucky", 10.1, 1.1));
        horses.add(new Horse("Horse3_Horseshoe", 10.2, 1.2));
        horses.add(new Horse("Horse4_Blazer", 10.3, 1.3));
        horses.add(new Horse("Horse5_Franklin", 10.4, 1.4));
        horses.add(new Horse("Horse6_Blacksmith", 10.5, 1.5));
        horses.add(new Horse("Horse7_Washington", 10.6, 1.6));
        horses.add(new Horse("Horse8_King", 10.7, 1.7));
        horses.add(new Horse("Horse9_Wrangler", 10.8, 1.8));
        horses.add(new Horse("Horse10_Texas", 10.9, 1.9));
        horses.add(new Horse("Horse11_Colorado", 11.0, 2.0));
        horses.add(new Horse("Horse12_Shoelace", 11.1, 2.1));
        horses.add(new Horse("Horse13_Steeltoe", 11.2, 2.2));
        horses.add(new Horse("Horse14_Sheriff", 11.3, 2.3));
        horses.add(new Horse("Horse15_Barley", 11.4, 2.4));
        horses.add(new Horse("Horse16_Kentucky", 11.5, 2.5));
        horses.add(new Horse("Horse17_Jockey", 11.6, 2.6));
        horses.add(new Horse("Horse18_Ridgeline", 11.7, 2.7));
        horses.add(new Horse("Horse19_Colonel", 11.8, 2.8));
        horses.add(new Horse("Horse20_Officer", 11.9, 2.9));
        horses.add(new Horse("Horse21_Colt", 12.0, 3.0));
        horses.add(new Horse("Horse22_Bronco", 12.1, 3.1));
        horses.add(new Horse("Horse23_Trigger", 12.2, 3.2));
        horses.add(new Horse("Horse24_Diesel", 12.3, 3.3));
        horses.add(new Horse("Horse25_Jupiter", 12.4, 3.4));
        horses.add(new Horse("Horse26_Clydesdale", 12.5, 3.5));
        horses.add(new Horse("Horse27_Quarterback", 12.6, 3.6));
        horses.add(new Horse("Horse28_Ironclad", 12.7, 3.7));
        horses.add(new Horse("Horse29_Rushmore", 12.8, 3.8));
        horses.add(new Horse("Horse30_Tank", 12.9, 3.9));
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    public void getWinner() {
        Horse horse1 = Mockito.mock(Horse.class);
        Horse horse2 = Mockito.mock(Horse.class);
        Horse horse3 = Mockito.mock(Horse.class);

        Mockito.when(horse1.getDistance()).thenReturn(150.0);
        Mockito.when(horse2.getDistance()).thenReturn(180.0);
        Mockito.when(horse3.getDistance()).thenReturn(130.0);

        List<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        Hippodrome hippodrome = new Hippodrome(horses);

        assertSame(horse2, hippodrome.getWinner());
    }
}