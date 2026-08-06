import java.util.ArrayList;
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
                System.out.println("3. Exit");
                System.out.print("Choice: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("Enter habit name: ");
                        String habitName = scanner.nextLine();
                        if (!habitName.trim().isEmpty()) {
                            habits.add(new Habit(habitName));
                            System.out.println("Habit added successfully!");
                        } else {
                            System.out.println("Habit name cannot be empty.");
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
                        running = false;
                        System.out.println("Keep up the great work! Goodbye.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please choose 1, 2, or 3.");
                }
            }
            scanner.close();
        }
    }
