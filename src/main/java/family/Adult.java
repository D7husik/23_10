package family;

public class Adult extends Person {
    public Adult(String id, String fullName, Gender gender, int birthYear, Integer deathYear) {
        super(id, fullName, gender, birthYear, deathYear);
        int currentAge = ageIn(2024);
        if (deathYear == null && currentAge < 18) {
            throw new IllegalArgumentException("Adult must be at least 18 years old");
        }
    }

    public boolean canMarry() {
        return isAlive();
    }
}