import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HabitStorage {
    private static final String FILE_NAME = "habits.csv";

    public static List<Habit> loadHabits() {
        List<Habit> habits = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return habits;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Habit habit = Habit.fromCsv(line);
                if (habit != null) {
                    habits.add(habit);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading habits: " + e.getMessage());
        }

        return habits;
    }

    public static void saveHabits(List<Habit> habits) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Habit habit : habits) {
                writer.write(habit.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving habits: " + e.getMessage());
        }
    }
}

