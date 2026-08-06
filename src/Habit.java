public class Habit {
    private String name;
    private int streak;

    public Habit(String name) {
        this.name = name;
        this.streak = 0;
    }
    public String getName() {
        return name;
    }
    public int getStreak() {
        return streak;
    }

    public void incrementStreak() {
        this.streak++;
    }
    public void displayHabit() {
        System.out.println("Habit: " + name + " | Current Streak: " + streak + " days");
    }
    public String toCsv() {
        return name + "," + streak;
    }

    public static Habit fromCsv(String line) {
        String[] parts = line.split(",");
        if (parts.length == 2) {
            return new Habit(parts[0], Integer.parseInt(parts[1].trim()));
        }
        return null;
    }
}

