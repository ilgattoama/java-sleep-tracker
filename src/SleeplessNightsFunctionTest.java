import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessNightsFunctionTest {
    @Test
    void sleeplessNights_none() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 0),
                        LocalDateTime.of(2025, 10, 2, 6, 0),
                        SleepQuality.GOOD
                )
        );

        SleeplessNightsFunction f = new SleeplessNightsFunction();
        assertEquals(0L, f.analyze(sessions).getValue());
    }

    @Test
    void sleeplessNights_oneNight() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 7, 0),
                        LocalDateTime.of(2025, 10, 1, 9, 0),
                        SleepQuality.NORMAL
                )
        );

        SleeplessNightsFunction f = new SleeplessNightsFunction();
        assertEquals(1L, f.analyze(sessions).getValue());
    }

    @Test
    void sleeplessNights_multiple() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 7, 0),
                        LocalDateTime.of(2025, 10, 1, 8, 0),
                        SleepQuality.NORMAL
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 3, 7, 0),
                        LocalDateTime.of(2025, 10, 3, 8, 0),
                        SleepQuality.NORMAL
                )
        );

        SleeplessNightsFunction f = new SleeplessNightsFunction();
        assertEquals(2L, f.analyze(sessions).getValue());
    }

    @Test
    void sleeplessNights_crossingNight() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 2, 0),
                        LocalDateTime.of(2025, 10, 1, 7, 0),
                        SleepQuality.GOOD
                )
        );

        SleeplessNightsFunction f = new SleeplessNightsFunction();
        assertEquals(0L, f.analyze(sessions).getValue());
    }
}
