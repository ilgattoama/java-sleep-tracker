package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.core.SleepAnalysisFunction;
import ru.yandex.practicum.sleeptracker.core.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.core.SleepingSession;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChronotypeFunction implements SleepAnalysisFunction {
    public SleepAnalysisResult<String> analyze(List<SleepingSession> sessions) {
        Map<String, Long> typeCounts = sessions.stream()
                .filter(s -> {
                    if (s.getStart().toLocalDate().isBefore(s.getEnd().toLocalDate())) {
                        return true;
                    }
                    LocalTime start = s.getStart().toLocalTime();
                    LocalTime end = s.getEnd().toLocalTime();
                    boolean isDaytime = start.isAfter(LocalTime.of(7, 0)) && end.isBefore(LocalTime.of(22, 0));
                    return !isDaytime;
                })
                .collect(Collectors.groupingBy(s -> {
                    LocalTime start = s.getStart().toLocalTime();
                    LocalTime end = s.getEnd().toLocalTime();

                    if (start.isAfter(LocalTime.of(23, 0)) && end.isAfter(LocalTime.of(9, 0))) {
                        return "Сова";
                    }
                    if (start.isBefore(LocalTime.of(22, 0)) && end.isBefore(LocalTime.of(7, 0))) {
                        return "Жаворонок";
                    }
                    return "Голубь";
                }, Collectors.counting()));

        long nightOwls = typeCounts.getOrDefault("Сова", 0L);
        long earlyBirds = typeCounts.getOrDefault("Жаворонок", 0L);
        long pigeons = typeCounts.getOrDefault("Голубь", 0L);

        String chronotype = (nightOwls > earlyBirds && nightOwls > pigeons) ? "Сова"
                : (earlyBirds > nightOwls && earlyBirds > pigeons) ? "Жаворонок"
                : "Голубь";

        return new SleepAnalysisResult<>("Хронотип пользователя", chronotype);
    }
}