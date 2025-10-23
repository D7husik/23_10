package strategy;
import family.Person;
import java.util.*;


public class BFSTraversal implements TraversalStrategy {
    @Override
    public List<Person> traverse(Person root, int generations) {
        List<Person> result = new ArrayList<>();
        Queue<PersonGenPair> queue = new LinkedList<>();
        queue.offer(new PersonGenPair(root, 0));

        while (!queue.isEmpty()) {
            PersonGenPair pair = queue.poll();
            if (pair.generation > generations) break;

            result.add(pair.person);

            if (pair.generation < generations) {
                for (Person child : pair.person.getChildren()) {
                    queue.offer(new PersonGenPair(child, pair.generation + 1));
                }
            }
        }
        return result;
    }

    private static class PersonGenPair {
        Person person;
        int generation;
        PersonGenPair(Person p, int g) { person = p; generation = g; }
    }
}