//package ;

import family.FamilyTree;
import family.Gender;
import family.Person;

import java.util.List;
import java.util.Scanner;


public class CLI {
    private FamilyTree familyTree;
    private Scanner scanner;

    public CLI() {
        this.familyTree = new FamilyTree();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== FAMILY TREE SYSTEM ===");
        System.out.println("Commands: ADD_PERSON, ADD_PARENT_CHILD, MARRY, DIVORCE, ANCESTORS, DESCENDANTS, SIBLINGS, SHOW, EXIT");
        System.out.println();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;
            if (input.equalsIgnoreCase("EXIT")) break;

            try {
                processCommand(input);
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }

        System.out.println("Goodbye!");
    }

    private void processCommand(String input) {
        String[] parts = input.split("\\s+", 2);
        String command = parts[0].toUpperCase();
        String args = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "ADD_PERSON":
                handleAddPerson(args);
                break;
            case "ADD_PARENT_CHILD":
                handleAddParentChild(args);
                break;
            case "MARRY":
                handleMarry(args);
                break;
            case "DIVORCE":
                handleDivorce(args);
                break;
            case "ANCESTORS":
                handleAncestors(args);
                break;
            case "DESCENDANTS":
                handleDescendants(args);
                break;
            case "SIBLINGS":
                handleSiblings(args);
                break;
            case "SHOW":
                handleShow(args);
                break;
            default:
                System.out.println("Unknown command: " + command);
        }
    }

    private void handleAddPerson(String args) {
        String[] parts = args.split("\"");
        if (parts.length < 2) {
            System.out.println("ERROR: Invalid format. Use: ADD_PERSON \"Full Name\" GENDER BIRTHYEAR [DEATHYEAR]");
            return;
        }

        String fullName = parts[1];
        String[] remainingParts = parts[2].trim().split("\\s+");

        if (remainingParts.length < 2) {
            System.out.println("ERROR: Missing gender or birth year");
            return;
        }

        Gender gender = Gender.valueOf(remainingParts[0].toUpperCase());
        int birthYear = Integer.parseInt(remainingParts[1]);
        Integer deathYear = remainingParts.length > 2 ? Integer.parseInt(remainingParts[2]) : null;

        String id = familyTree.addPerson(fullName, gender, birthYear, deathYear);
        System.out.println("-> " + id);
    }

    private void handleAddParentChild(String args) {
        String[] parts = args.split("\\s+");
        if (parts.length < 2) {
            System.out.println("ERROR: Format: ADD_PARENT_CHILD <parentId> <childId>");
            return;
        }

        familyTree.addParentChild(parts[0], parts[1]);
        System.out.println("OK");
    }

    private void handleMarry(String args) {
        String[] parts = args.split("\\s+");
        if (parts.length < 3) {
            System.out.println("ERROR: Format: MARRY <personAId> <personBId> <year>");
            return;
        }

        familyTree.marry(parts[0], parts[1], Integer.parseInt(parts[2]));
        System.out.println("OK");
    }

    private void handleDivorce(String args) {
        String[] parts = args.split("\\s+");
        if (parts.length < 2) {
            System.out.println("ERROR: Format: DIVORCE <personId> <year>");
            return;
        }

        String personId = parts[0];
        int year = Integer.parseInt(parts[1]);

        Person person = familyTree.getPerson(personId);
        if (person == null) {
            System.out.println("ERROR: Person not found: " + personId);
            return;
        }

        Person spouse = person.getSpouse();
        if (spouse == null) {
            System.out.println("ERROR: Person is not married");
            return;
        }

        person.divorce(year);
        spouse.divorce(year);
        System.out.println("OK");
    }

    private void handleAncestors(String args) {
        String[] parts = args.split("\\s+");
        if (parts.length < 2) {
            System.out.println("ERROR: Format: ANCESTORS <personId> <generations>");
            return;
        }

        List<Person> ancestors = familyTree.ancestorsOf(parts[0], Integer.parseInt(parts[1]));
        System.out.println(familyTree.renderPersons(ancestors));
    }

    private void handleDescendants(String args) {
        String[] parts = args.split("\\s+");
        if (parts.length < 2) {
            System.out.println("ERROR: Format: DESCENDANTS <personId> <generations>");
            return;
        }

        List<Person> descendants = familyTree.descendantsOf(parts[0], Integer.parseInt(parts[1]));
        System.out.println(familyTree.renderPersons(descendants));
    }

    private void handleSiblings(String args) {
        String personId = args.trim();
        List<Person> siblings = familyTree.siblingsOf(personId);

        if (siblings.isEmpty()) {
            System.out.println("<none>");
        } else {
            System.out.println(familyTree.renderPersons(siblings));
        }
    }

    private void handleShow(String args) {
        String personId = args.trim();
        Person person = familyTree.getPerson(personId);

        if (person == null) {
            System.out.println("ERROR: Person not found: " + personId);
        } else {
            System.out.println(person.toString());
        }
    }

    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.run();
    }
}