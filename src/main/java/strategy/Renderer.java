package strategy;

import family.Person;
import java.util.List;

public interface Renderer {
    String render(List<Person> people, int startGeneration);
}