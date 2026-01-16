import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChronotypeFunction implements SleepAnalysisFunction {
    public SleepAnalysisResult<String> analyze(List<SleepingSession> sessions) {
        Map<String, Long> typeCounts = sessions.stream()
                .filter(s -> s.getStart().getHour() < 24 && s.getEnd().getHour() < 24) // Игнорируем дневные и бессонные
                .collect(Collectors.groupingBy(s -> {
                    int startHour = s.getStart().getHour();
                    int endHour = s.getEnd().getHour();

                    if (startHour >= 23 && endHour >= 9) return "Сова";
                    if (startHour <= 22 && endHour <= 7) return "Жаворонок";
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