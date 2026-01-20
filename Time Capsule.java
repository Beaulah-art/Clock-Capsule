import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ClockCapsule {
    static class TimeCapsule {
		// making all fields private
        private String title;
        private String message;
        private LocalDate date;
        private LocalTime time;
        private String zone;
        private boolean reminderSet;

        public TimeCapsule(String title, String message, LocalDate date, LocalTime time, String zone, boolean reminderSet) {
            this.title = title;
            this.message = message;
            this.date = date;
            this.time = time;
            this.zone = zone;  
            this.reminderSet = reminderSet;
        }

        public String toFormattedString() {
            ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.of(date, time), ZoneId.of(zone));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
            return "Title: " + title + "\nMessage: " + message + "\nTime Capsule for: " + zdt.format(formatter) + "\nReminder Set: " + reminderSet;
        }

        public ZonedDateTime getZonedDateTime() {
            return ZonedDateTime.of(LocalDateTime.of(date, time), ZoneId.of(zone));
        }

        public String getZone() {
            return zone;
        }

        public LocalDate getDate() {
            return date;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<TimeCapsule> capsules = new ArrayList<>();

        System.out.print("How many capsules would you like to create? ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {
            System.out.println("\nEnter details for Capsule " + (i + 1));

            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Message: ");
            String message = scanner.nextLine();

            System.out.print("Date (yyyy-MM-dd): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            System.out.print("Time (HH:mm): ");
            LocalTime time = LocalTime.parse(scanner.nextLine());

            System.out.print("Zone (e.g. Africa/South Africa): ");
            String zone = scanner.nextLine();

            System.out.print("Reminder set? (true/false): ");
            boolean reminder = Boolean.parseBoolean(scanner.nextLine());

            capsules.add(new TimeCapsule(title, message, date, time, zone, reminder));
        }

        System.out.println("\n All Capsules");
        for (TimeCapsule capsule : capsules) {
            System.out.println(capsule.toFormattedString());
            System.out.println();
        }

        // Date/time calculations
        for (TimeCapsule capsule : capsules) {
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of(capsule.getZone()));
            ZonedDateTime capsuleTime = capsule.getZonedDateTime();

            Duration duration = Duration.between(now, capsuleTime);

            System.out.println("Time until capsule \"" + capsule.getZone() + "\":");
            System.out.println("Days remaining: " + duration.toDays());
            System.out.println("Minutes remaining: " + duration.toMinutes());
            System.out.println();
        }

        // BONUS: Filter by zone
        System.out.print("Filter capsules by zone (or press Enter to skip): ");
        String filterZone = scanner.nextLine();

        if (!filterZone.isEmpty()) {
            System.out.println("Filtered Capsules in zone " + filterZone + ":");
            for (TimeCapsule capsule : capsules) {
                if (capsule.getZone().equalsIgnoreCase(filterZone)) {
                    System.out.println(capsule.toFormattedString());
                    System.out.println();
                }
            }
        }

        scanner.close();
    }
}