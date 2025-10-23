package strategy;
//import java.util.ArrayList;

import family.Person;

import java.util.ArrayList;
import java.util.List;

public class DFSTraversal implements TraversalStrategy {
    @Override
    public List<Person> traverse(Person root, int generations) {
        List<Person> result = new ArrayList<>();
        dfs(root, 0, generations, result);
        return result;
    }

    private void dfs(Person person, int currentGen, int maxGen, List<Person> result) {
        if (person == null || currentGen > maxGen) return;
        result.add(person);
        if (currentGen < maxGen) {
            for (Person child : person.getChildren()) {
                dfs(child, currentGen + 1, maxGen, result);
            }
        }
    }
}
