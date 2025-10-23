package family;

import java.util.ArrayList;
import java.util.*;

public abstract class Person {
    private String id;
    private String fullName;
    private Gender gender;
    private int birthYear;
    private Integer deathYear;
    private List<Person> parents;
    private List<Person> children;
    private Person spouse;
    private Integer marriageYear;
    private Integer divorceYear;

    public Person(String id, String fullName, Gender gender, int birthYear, Integer deathYear) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be null or blank");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name cannot be null or blank");
        }
        if (birthYear < 1800 || birthYear > 2024) {
            throw new IllegalArgumentException("Birth year must be between 1800 and 2024");
        }
        if (deathYear != null && deathYear < birthYear) {
            throw new IllegalArgumentException("Death year cannot be before birth year");
        }

        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public Gender getGender() { return gender; }
    public int getBirthYear() { return birthYear; }
    public Integer getDeathYear() { return deathYear; }
    public List<Person> getParents() { return new ArrayList<>(parents); }
    public List<Person> getChildren() { return new ArrayList<>(children); }
    public Person getSpouse() { return spouse; }
    public Integer getMarriageYear() { return marriageYear; }
    public Integer getDivorceYear() { return divorceYear; }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name cannot be null or blank");
        }
        this.fullName = fullName;
    }

    public void setDeathYear(Integer deathYear) {
        if (deathYear != null && deathYear < birthYear) {
            throw new IllegalArgumentException("Death year cannot be before birth year");
        }
        this.deathYear = deathYear;
    }

    public boolean isAlive() {
        return deathYear == null;
    }

    public int ageIn(int year) {
        if (year < birthYear) {
            throw new IllegalArgumentException("Year cannot be before birth year");
        }
        int endYear = (deathYear != null && deathYear < year) ? deathYear : year;
        return endYear - birthYear;
    }

    public void addParent(Person parent) {
        if (!parents.contains(parent)) {
            parents.add(parent);
        }
    }

    public void addChild(Person child) {
        if (!children.contains(child)) {
            children.add(child);
        }
    }

    public void setSpouse(Person spouse, int year) {
        this.spouse = spouse;
        this.marriageYear = year;
    }

    public void divorce(int year) {
        if (this.spouse == null) {
            throw new IllegalArgumentException("Cannot divorce: not married");
        }
        if (year < this.marriageYear) {
            throw new IllegalArgumentException("Divorce year cannot be before marriage year");
        }
        this.divorceYear = year;
    }

    public void removeSpouse() {
        this.spouse = null;
        this.marriageYear = null;
        this.divorceYear = null;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | b.%d%s | spouse=%s | children=%d",
                id, fullName, gender,
                birthYear,
                deathYear != null ? " d." + deathYear : "",
                spouse != null ? spouse.getId() : "none",
                children.size());
    }}