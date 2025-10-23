package family;

import strategy.*;
import java.util.*;

public class FamilyTree {
    private Map<String, Person> people;
    private TraversalStrategy defaultTraversal;
    private Renderer defaultRenderer;

    public FamilyTree() {
        this.people = new HashMap<>();
        this.defaultTraversal = new DFSTraversal();
        this.defaultRenderer = new IndentedTreeRenderer();
    }

    public void setTraversalStrategy(TraversalStrategy strategy) {
        this.defaultTraversal = strategy;
    }

    public void setRenderer(Renderer renderer) {
        this.defaultRenderer = renderer;
    }

    public String addPerson(String fullName, Gender gender, int birthYear, Integer deathYear) {
        Person person = PersonFactory.getInstance().createPerson(fullName, gender, birthYear, deathYear);
        people.put(person.getId(), person);
        return person.getId();
    }

    public void addParentChild(String parentId, String childId) {
        Person parent = people.get(parentId);
        Person child = people.get(childId);

        if (parent == null) {
            throw new IllegalArgumentException("Parent ID not found: " + parentId);
        }
        if (child == null) {
            throw new IllegalArgumentException("Child ID not found: " + childId);
        }

        if (child.getParents().size() >= 2) {
            throw new IllegalArgumentException("Child already has 2 parents");
        }

        if (isAncestor(child, parent)) {
            throw new IllegalArgumentException("Cycle detected: child cannot be ancestor of parent");
        }

        child.addParent(parent);
        parent.addChild(child);
    }

    public void marry(String personAId, String personBId, int year) {
        Person personA = people.get(personAId);
        Person personB = people.get(personBId);

        if (personA == null || personB == null) {
            throw new IllegalArgumentException("One or both person IDs not found");
        }

        if (personA.getSpouse() != null) {
            throw new IllegalArgumentException(personA.getId() + " is already married");
        }
        if (personB.getSpouse() != null) {
            throw new IllegalArgumentException(personB.getId() + " is already married");
        }

        personA.setSpouse(personB, year);
        personB.setSpouse(personA, year);
    }

    public List<Person> ancestorsOf(String personId, int generations) {
        Person person = people.get(personId);
        if (person == null) {
            throw new IllegalArgumentException("Person ID not found: " + personId);
        }

        AncestorTraversal ancestorTraversal = new AncestorTraversal();
        return ancestorTraversal.traverse(person, generations);
    }

    public List<Person> descendantsOf(String personId, int generations) {
        Person person = people.get(personId);
        if (person == null) {
            throw new IllegalArgumentException("Person ID not found: " + personId);
        }
        return defaultTraversal.traverse(person, generations);
    }

    public List<Person> siblingsOf(String personId) {
        Person person = people.get(personId);
        if (person == null) {
            throw new IllegalArgumentException("Person ID not found: " + personId);
        }

        Set<Person> siblings = new HashSet<>();
        for (Person parent : person.getParents()) {
            for (Person child : parent.getChildren()) {
                if (!child.equals(person)) {
                    siblings.add(child);
                }
            }
        }
        return new ArrayList<>(siblings);
    }

    public List<Person> childrenOf(String personId) {
        Person person = people.get(personId);
        if (person == null) {
            throw new IllegalArgumentException("Person ID not found: " + personId);
        }
        return person.getChildren();
    }

    public Person spouseOf(String personId) {
        Person person = people.get(personId);
        if (person == null) {
            throw new IllegalArgumentException("Person ID not found: " + personId);
        }
        return person.getSpouse();
    }

    public Person getPerson(String personId) {
        return people.get(personId);
    }

    public String renderPersons(List<Person> persons) {
        return defaultRenderer.render(persons, 0);
    }

    private boolean isAncestor(Person potentialAncestor, Person person) {
        if (potentialAncestor.equals(person)) return true;
        for (Person parent : person.getParents()) {
            if (isAncestor(potentialAncestor, parent)) {
                return true;
            }
        }
        return false;
    }
}