import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SleeplessNightsFunction implements SleepAnalysisFunction {
    public SleepAnalysisResult<Long> analyze(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) return new SleepAnalysisResult<>("Бессонные ночи", 0L);

        LocalDate startDate = sessions.get(0).getStart().toLocalDate();
        LocalDate endDate = sessions.get(sessions.size() - 1).getEnd().toLocalDate();

        long sleeplessCount = startDate.datesUntil(endDate.plusDays(1))
                .filter(date -> {
                    LocalTime nightStart = LocalTime.MIDNIGHT;
                    LocalTime nightEnd = LocalTime.of(6, 0);

                    return sessions.stream().noneMatch(s -> {
                        LocalTime sStart = s.getStart().toLocalTime();
                        LocalTime sEnd = s.getEnd().toLocalTime();
                        LocalDate sDate = s.getStart().toLocalDate();

                        boolean overlaps = (
                                (sDate.isEqual(date) && !sEnd.isBefore(nightStart) && !sStart.isAfter(nightEnd)) ||
                                        (sDate.isEqual(date.minusDays(1)) && !sEnd.isBefore(nightStart) && !sStart.isAfter(nightEnd))
                        );
                        return overlaps;
                    });
                }).count();

        return new SleepAnalysisResult<>("Бессонные ночи", sleeplessCount);
    }
}