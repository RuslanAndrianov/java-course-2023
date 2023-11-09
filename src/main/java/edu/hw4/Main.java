package edu.hw4;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import static edu.hw4.Animal.Sex;
import static edu.hw4.Animal.Type;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toMap;

@SuppressWarnings({"HideUtilityClassConstructor", "MagicNumber", "ModifiedControlVariable"})

public class Main {

    // Task 1
    static List<Animal> sortByAgeAsc(@NotNull List<Animal> animals) {
        return animals.stream()
            .sorted(comparing(Animal::age))
            .toList();
    }

    // Task 2
    static List<Animal> sortByWeightDescChooseK(@NotNull List<Animal> animals, int k) {

        return animals.stream()
            .sorted(comparing(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    // Task 3
    static @NotNull Map<Type, Integer> createAnimalMap(@NotNull List<Animal> animals) {

        return animals.stream()
            .collect(groupingBy(Animal::type, summingInt(animal -> 1)));
    }

    // Task 4
    static Animal findAnimalWithLongestName(@NotNull List<Animal> animals) {

        return animals.stream()
            .max(comparing(animal -> animal.name().length()))
            .orElse(null);
    }

    // Task 5
    static Sex findMostPopularSex(@NotNull List<Animal> animals) {
        long females = animals.stream().filter(animal -> animal.sex() == Sex.F).count();
        long males = animals.stream().filter(animal -> animal.sex() == Sex.M).count();

        return (females > males) ? Sex.F : Sex.M;
    }

    // Task 6
    static @NotNull Map<Type, Animal> createHeaviestAnimalMap(@NotNull List<Animal> animals) {

        return animals.stream()
            .collect(groupingBy(
                Animal::type,
                collectingAndThen(maxBy(comparingInt(Animal::weight)), animal -> animal.orElse(null))
            ));
    }

    // Task 7
    static Animal findOldestAnimalK(@NotNull List<Animal> animals, int k) {

        return animals.stream()
            .sorted(comparing(animal -> -animal.age()))
            .limit(k)
            .toList()
            .get(k - 1);
    }

    // Task 8
    static Animal findHeaviestAnimalWithHeightLowerK(@NotNull List<Animal> animals, int k) {

        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(comparing(Animal::weight))
            .orElse(null);
    }

    // Task 9
    static @NotNull Integer sumAllPaws(@NotNull List<Animal> animals) {

        return animals.stream()
            .reduce(0, (paws, animal) -> paws + animal.paws(), Integer::sum);
    }

    // Task 10
    static List<Animal> findAgeNotEqualPaws(@NotNull List<Animal> animals) {

        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .toList();
    }

    // Task 11
    static List<Animal> findBitesAndHeightMore100(@NotNull List<Animal> animals) {

        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > 100)
            .toList();
    }

    // Task 12
    static @NotNull Integer countWeightMoreHeight(@NotNull List<Animal> animals) {

        return Math.toIntExact(animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count());
    }

    // Task 13
    static List<Animal> findAnimalsWithNameMore2Words(@NotNull List<Animal> animals) {

        return animals.stream()
            .filter(animal -> animal.name().split("\\s+").length > 2)
            .toList();
    }

    // Task 14
    static @NotNull Boolean isDogHeightMoreK(@NotNull List<Animal> animals, int k) {

        return animals.stream()
            .anyMatch(animal -> animal.type() == Type.DOG && animal.height() > k);
    }

    // Task 15
    static @NotNull Map<Type, Integer> sumWeightAgeFormKToL(@NotNull List<Animal> animals, int k, int l) {

        return animals.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(groupingBy(Animal::type, summingInt(Animal::weight)));
    }

    // Task 16
    static List<Animal> sortTypeSexName(@NotNull List<Animal> animals) {

        return animals.stream()
            .sorted(comparing(Animal::type))
            .sorted((o1, o2) -> {
                if (o1.type() == o2.type()) {
                    return o1.sex().compareTo(o2.sex());
                }
                return 0;
            })
            .sorted((o1, o2) -> {
                if ((o1.type() == o2.type()) && (o1.sex() == o2.sex())) {
                    return o1.name().compareTo(o2.name());
                }
                return 0;
            })
            .toList();
    }

    // Task 17
    static @NotNull Boolean areSpidersBiteMoreThanDogs(@NotNull List<Animal> animals) {

        long spiderBites = animals.stream()
            .filter(animal -> animal.type() == Type.SPIDER && animal.bites())
            .count();

        long dogBites = animals.stream()
            .filter(animal -> animal.type() == Type.DOG && animal.bites())
            .count();

        return (spiderBites > dogBites);
    }

    // Task 18
    @SafeVarargs static Animal findHeaviestFishInLists(List<Animal> @NotNull...lists) {

        return Arrays.stream(lists)
            .flatMap(Collection::stream)
            .filter(animal -> animal.type() == Type.FISH)
            .sorted(comparingInt(Animal::weight))
            .toList()
            .reversed()
            .get(0);
    }

    // Task 19
    static @NotNull Map<String, Set<ValidationError>> findIncorrectAnimals(@NotNull List<Animal> animals) {

        return animals.stream()
            .filter(animal ->
                animal.name().replace(" ", "").isEmpty()
                || animal.age() <= 0
                || animal.height() <= 0
                || animal.weight() <= 0)
            .collect(toMap(Animal::name, Main::animalErrors));
    }

    private static @NotNull Set<ValidationError> animalErrors(@NotNull Animal animal) {

        Set<ValidationError> errors = new HashSet<>();

        if (animal.name().replace(" ", "").isEmpty()) {
            errors.add(new ValidationError("Некорректное имя!"));
        }

        if (animal.age() <= 0) {
            errors.add(new ValidationError("Некорректный возраст!"));
        }

        if (animal.height() <= 0) {
            errors.add(new ValidationError("Некорректный рост!"));
        }

        if (animal.weight() <= 0) {
            errors.add(new ValidationError("Некорректный вес!"));
        }

        return errors;
    }

    // Task 20
    static @NotNull Map<String, String> findIncorrectAnimals2(@NotNull List<Animal> animals) {
        Map<String, Set<ValidationError>> oldMap = findIncorrectAnimals(animals);
        Map<String, String> newMap = new HashMap<>();

        for (Map.Entry<String, Set<ValidationError>> entry : oldMap.entrySet()) {
            StringBuilder errorsStr = new StringBuilder();

            Set<ValidationError> errors = entry.getValue();

            for (ValidationError error : errors.stream().sorted(comparing(o -> o.message)).toList()) {
                errorsStr.append(error.message).append(" ");
            }

            newMap.put(entry.getKey(), String.valueOf(errorsStr).trim());
        }

        return newMap;
    }
}
