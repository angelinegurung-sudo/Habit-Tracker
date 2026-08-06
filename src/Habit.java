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
}

