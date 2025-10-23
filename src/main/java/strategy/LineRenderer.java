package strategy;

import family.Person;
import java.util.List;


public class LineRenderer implements Renderer {
    @Override
    public String render(List<Person> people, int startGeneration) {
        StringBuilder sb = new StringBuilder();
        for (Person p : people) {
            sb.append(p.getId()).append(" ")
                    .append(p.getFullName())
                    .append(" (b.").append(p.getBirthYear()).append(")")
                    .append("\n");
        }
        return sb.toString();
    }
}