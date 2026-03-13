package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.core.SleepQuality;
import ru.yandex.practicum.sleeptracker.core.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadSleepCountFunctionTest {

    @Test
    void badSleep_someBad() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 0),
                        LocalDateTime.of(2025, 10, 2, 7, 0),
                        SleepQuality.BAD
                ),
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 2, 23, 0),
                        LocalDateTime.of(2025, 10, 3, 7, 0),
                        SleepQuality.GOOD
                )
        );

        BadSleepCountFunction f = new BadSleepCountFunction();
        assertEquals(1, f.analyze(sessions).getValue());
    }

    @Test
    void badSleep_noneBad() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 10, 1, 23, 0),
                        LocalDateTime.of(2025, 10, 2, 7, 0),
                        SleepQuality.GOOD
                )
        );

        BadSleepCountFunction f = new BadSleepCountFunction();
        assertEquals(0, f.analyze(sessions).getValue());
    }
}