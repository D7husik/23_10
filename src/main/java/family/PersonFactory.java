package family;

public class PersonFactory {
    private static PersonFactory instance;
    private int counter = 1;

    private PersonFactory() {}

    public static PersonFactory getInstance() {
        if (instance == null) {
            instance = new PersonFactory();
        }
        return instance;
    }

    public Person createPerson(String fullName, Gender gender, int birthYear, Integer deathYear) {
        String id = generateId();
        int age = 2024 - birthYear;

        if (deathYear == null && age >= 18) {
            return new Adult(id, fullName, gender, birthYear, deathYear);
        } else {
            return new Minor(id, fullName, gender, birthYear, deathYear);
        }
    }

    private String generateId() {
        return String.format("P%03d", counter++);
    }
}