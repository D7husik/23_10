package composite;

import family.Person;
import java.util.*;

public class PersonNode {
    private Person person;
    private List<PersonNode> childNodes;

    public PersonNode(Person person) {
        this.person = person;
        this.childNodes = new ArrayList<>();
    }

    public Person getPerson() { return person; }

    public void addChild(PersonNode child) {
        childNodes.add(child);
    }

    public List<PersonNode> getChildNodes() {
        return new ArrayList<>(childNodes);
    }

    public void printTree(int depth, String prefix) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + prefix + person.getId() + " " + person.getFullName() +
                " (b." + person.getBirthYear() + ")");

        for (PersonNode child : childNodes) {
            child.printTree(depth + 1, "- ");
        }
    }

    public int countAllDescendants() {
        int count = childNodes.size();
        for (PersonNode child : childNodes) {
            count += child.countAllDescendants();
        }
        return count;
    }
}