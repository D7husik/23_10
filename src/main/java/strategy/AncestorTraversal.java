package strategy;


import family.Person;
import java.util.*;


public class AncestorTraversal implements TraversalStrategy {
    @Override
    public List<Person> traverse(Person root, int generations) {
        List<Person> result = new ArrayList<>();
        collectAncestors(root, 0, generations, result);
        return result;
    }

    private void collectAncestors(Person person, int currentGen, int maxGen, List<Person> result) {
        if (person == null || currentGen > maxGen) return;
        result.add(person);
        if (currentGen < maxGen) {
            for (Person parent : person.getParents()) {
                collectAncestors(parent, currentGen + 1, maxGen, result);
            }
        }
    }
}

