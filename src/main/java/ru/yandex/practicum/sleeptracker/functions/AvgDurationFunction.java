package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.core.SleepAnalysisFunction;
import ru.yandex.practicum.sleeptracker.core.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.core.SleepingSession;

import java.util.List;

public class AvgDurationFunction implements SleepAnalysisFunction {
    public SleepAnalysisResult<Double> analyze(List<SleepingSession> sessions) {
        return new SleepAnalysisResult<>(
                "Средняя продолжительность сна (минуты)",
                sessions.stream().mapToLong(SleepingSession::getDurationMinutes).average().orElse(0.0)
        );
    }
}