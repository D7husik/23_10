package family;

public class Minor extends Person {
    private Person guardian;

    public Minor(String id, String fullName, Gender gender, int birthYear, Integer deathYear) {
        super(id, fullName, gender, birthYear, deathYear);
        int currentAge = ageIn(2024);
        if (deathYear == null && currentAge >= 18) {
            throw new IllegalArgumentException("Minor must be under 18 years old");
        }
    }

    public Person getGuardian() { return guardian; }

    public void setGuardian(Person guardian) {
        this.guardian = guardian;
    }

    public boolean canMarry() {
        return false;
    }
}