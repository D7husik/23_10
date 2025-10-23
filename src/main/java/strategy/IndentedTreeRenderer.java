package strategy;

import family.Person;
import java.util.*;

public class IndentedTreeRenderer implements Renderer {
    @Override
    public String render(List<Person> people, int startGeneration) {
        StringBuilder sb = new StringBuilder();
        Map<Person, Integer> levels = new HashMap<>();

        if (!people.isEmpty()) {
            levels.put(people.get(0), 0);
            for (int i = 1; i < people.size(); i++) {
                Person p = people.get(i);
                int level = findLevel(p, people.get(0), 0);
                levels.put(p, level);
            }
        }

        for (Person p : people) {
            int level = levels.getOrDefault(p, 0);
            String indent = "  ".repeat(level);
            sb.append(indent).append("- ")
                    .append(p.getId()).append(" ")
                    .append(p.getFullName())
                    .append(" (b.").append(p.getBirthYear()).append(")")
                    .append("\n");
        }
        return sb.toString();
    }

    private int findLevel(Person target, Person current, int level) {
        if (current.equals(target)) return level;
        for (Person child : current.getChildren()) {
            int found = findLevel(target, child, level + 1);
            if (found >= 0) return found;
        }
        for (Person parent : current.getParents()) {
            int found = findLevel(target, parent, level + 1);
            if (found >= 0) return found;
        }
        return -1;
    }
}