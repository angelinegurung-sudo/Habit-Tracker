import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Habit> habits = HabitStorage.loadHabits();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Welcome to Habit Tracker ===");
        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a new habit");
            System.out.println("2. View all habits");
            System.out.println("3. Check in on a habit (Increase Streak)");
            System.out.println("4. Delete a habit");
            System.out.println("5. exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter habit name: ");
                    String habitName = scanner.nextLine().trim();

                    if (habitName.isEmpty()) {
                        System.out.println("Habit name cannot be empty.");
                    } else {
                        boolean exists = false;
                        for (Habit h : habits) {
                            if (h.getName().equalsIgnoreCase(habitName)) {
                                exists = true;
                                break;
                            }
                        }

                        if (exists) {
                            System.out.println("Habit already exists! Duplicate discarded.");
                        } else {
                            habits.add(new Habit(habitName));
                            HabitStorage.saveHabits(habits);
                            System.out.println("Habit added and saved successfully!");
                        }
                    }
                    break;

                case "2":
                    if (habits.isEmpty()) {
                        System.out.println("No habits tracked yet.");
                    } else {
                        System.out.println("\n--- Your Habits ---");
                        for (Habit h : habits) {
                            h.displayHabit();
                        }
                    }
                    break;

                case "3":
                    if (habits.isEmpty()) {
                        System.out.println("No habits available to check in on.");
                    } else {
                        System.out.println("\n--- Select Habits to Check In ---");
                        for (int i = 0; i < habits.size(); i++) {
                            System.out.println((i + 1) + ". " + habits.get(i).getName() + " (Current Streak: " + habits.get(i).getStreak() + ")");
                        }
                        System.out.print("Enter numbers separated by spaces (e.g. 1 2 3): ");
                        String input = scanner.nextLine().trim();

                        if (!input.isEmpty()) {
                            String[] tokens = input.split("[,\\s]+");
                            boolean updatedAny = false;

                            for (String token : tokens) {
                                try {
                                    int index = Integer.parseInt(token) - 1;
                                    if (index >= 0 && index < habits.size()) {
                                        habits.get(index).incrementStreak();
                                        System.out.println("-> Streak updated for: " + habits.get(index).getName());
                                        updatedAny = true;
                                    } else {
                                        System.out.println("-> Invalid number skipped: " + (index + 1));
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("-> Skipping invalid input: " + token);
                                }
                            }
                            if (updatedAny) {
                                HabitStorage.saveHabits(habits);
                                System.out.println("All valid check-ins saved successfully!");
                            }
                        }
                    }
                    break;

                case "4":
                    if (habits.isEmpty()) {
                        System.out.println("No habits available to delete.");
                    } else {
                        System.out.println("\n--- Select a Habit to Delete ---");
                        for (int i = 0; i < habits.size(); i++) {
                            System.out.println((i + 1) + ". " + habits.get(i).getName());
                        }
                        System.out.print("Enter number of the habit to delete: ");
                        try {
                            int index = Integer.parseInt(scanner.nextLine()) - 1;
                            if (index >= 0 && index < habits.size()) {
                                Habit removed = habits.remove(index);
                                HabitStorage.saveHabits(habits);
                                System.out.println("Deleted habit: " + removed.getName());
                            } else {
                                System.out.println("Invalid habit selection.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                        }
                    }
                    break;

                case "5":
                    running = false;
                    System.out.println("Keep up the great work! Goodbye.");
                    break;

                default:
                    System.out.println("Invalid choice. Please choose 1, 2, 3, 4, or 5.");
            }
        }
        scanner.close();
    }
}