package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.core.SleepAnalysisFunction;
import ru.yandex.practicum.sleeptracker.core.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.core.SleepingSession;

import java.util.List;

public class TotalSessionsFunction implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult<Integer> analyze(List<SleepingSession> sessions) {
        return new SleepAnalysisResult<>(
                "Total sleep sessions",
                sessions.size()
        );
    }
}