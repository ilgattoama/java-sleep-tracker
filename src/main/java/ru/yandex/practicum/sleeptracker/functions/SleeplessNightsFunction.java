package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.core.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SleeplessNightsFunction implements SleepAnalysisFunction {

    @Override
    public SleepAnalysisResult<Long> analyze(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult<>("Бессонные ночи", 0L);
        }

        Set<LocalDate> relevantNights = sessions.stream()
                .map(s -> {
                    LocalTime startTime = s.getStart().toLocalTime();
                    LocalDate startDate = s.getStart().toLocalDate();
                    return startTime.isAfter(LocalTime.of(12, 0))
                            ? startDate.plusDays(1)
                            : startDate;
                })
                .collect(Collectors.toSet());

        long sleeplessCount = 0;

        for (LocalDate nightDate : relevantNights) {
            LocalDateTime nightStart = nightDate.atTime(0, 0);
            LocalDateTime nightEnd = nightDate.atTime(6, 0);

            boolean hasSleep = sessions.stream()
                    .anyMatch(s -> s.getStart().isBefore(nightEnd) &&
                            s.getEnd().isAfter(nightStart));

            if (!hasSleep) {
                sleeplessCount++;
            }
        }

        return new SleepAnalysisResult<>("Бессонные ночи", sleeplessCount);
    }
}