package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.core.SleepAnalysisFunction;
import ru.yandex.practicum.sleeptracker.core.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.core.SleepQuality;
import ru.yandex.practicum.sleeptracker.core.SleepingSession;

import java.util.List;

public class BadSleepCountFunction implements SleepAnalysisFunction {
    public SleepAnalysisResult<Long> analyze(List<SleepingSession> sessions) {
        return new SleepAnalysisResult<>(
                "Количество сессий с плохим сном",
                sessions.stream().filter(s -> s.getQuality() == SleepQuality.BAD).count()
        );
    }
}