package strategy;

import family.Person;

import java.util.List;

public interface TraversalStrategy {
    List<Person> traverse(Person root, int generations);
}