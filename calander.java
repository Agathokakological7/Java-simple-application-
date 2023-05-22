
import java.util.*;

public class CalendarApp {
    private static Map<Date, List<String>> calendar;

    public static void main(String[] args) {
        calendar = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCalendar App");
            System.out.println("1. View events for a specific date");
            System.out.println("2. Add event");
            System.out.println("3. Remove event");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewEvents();
                    break;
                case 2:
                    addEvent(scanner);
                    break;
                case 3:
                    removeEvent(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void viewEvents() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter date (DD/MM/YYYY): ");
        String dateStr = scanner.next();

        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
            List<String> events = calendar.get(date);

            if (events != null) {
                System.out.println("Events for " + dateStr + ":");
                for (String event : events) {
                    System.out.println("- " + event);
                }
            } else {
                System.out.println("No events found for " + dateStr);
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use DD/MM/YYYY.");
        }
    }

    private static void addEvent(Scanner scanner) {
        System.out.print("Enter date (DD/MM/YYYY): ");
        String dateStr = scanner.next();

        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);

            System.out.print("Enter event description: ");
            String event = scanner.next();

            List<String> events = calendar.getOrDefault(date, new ArrayList<>());
            events.add(event);
            calendar.put(date, events);

            System.out.println("Event added successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use DD/MM/YYYY.");
        }
    }

    private static void removeEvent(Scanner scanner) {
        System.out.print("Enter date (DD/MM/YYYY): ");
        String dateStr = scanner.next();

        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);

            List<String> events = calendar.get(date);

            if (events != null) {
                System.out.println("Events for " + dateStr + ":");
                for (int i = 0; i < events.size(); i++) {
                    System.out.println((i + 1) + ". " + events.get(i));
                }

                System.out.print("Enter the event number to remove: ");
                int eventNumber = scanner.nextInt();

                if (eventNumber >= 1 && eventNumber <= events.size()) {
                    events.remove(eventNumber - 1);
                    calendar.put(date, events);
                    System.out.println("Event removed successfully.");
                } else {
                    System.out.println("Invalid event number.");
                }
            } else {
                System.out.println("No events found for " + dateStr);
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use DD/MM/YYYY.");
        }
   
