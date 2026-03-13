package ru.yandex.practicum.sleeptracker.core;

import java.util.List;

@FunctionalInterface
public interface SleepAnalysisFunction {
    SleepAnalysisResult<?> analyze(List<SleepingSession> sessions);
}