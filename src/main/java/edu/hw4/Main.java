package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import static edu.hw4.Animal.Sex;
import static edu.hw4.Animal.Type;

@SuppressWarnings({"HideUtilityClassConstructor", "MagicNumber", "ModifiedControlVariable"})

public class Main {

    // Task 1
    static List<Animal> sortByAgeAsc(@NotNull List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::age))
            .toList();
    }

    // Task 2
    static List<Animal> sortByWeightDescChooseK(@NotNull List<Animal> animals, int k) {
        if (k < 1) {
            return new ArrayList<>() {
            };
        }

        return animals.stream()
            .sorted(Comparator.comparing(animal -> -animal.weight()))
            .limit(k)
            .toList();
    }

    // Task 3
    static @NotNull Map<Type, Integer> createAnimalMap(@NotNull List<Animal> animals) {
        int cats = 0;
        int dogs = 0;
        int birds = 0;
        int fish = 0;
        int spiders = 0;

        Map<Animal.Type, Integer> map = new HashMap<>();

        for (Animal animal : animals) {
            switch (animal.type()) {
                case CAT -> map.put(Type.CAT, ++cats);
                case DOG -> map.put(Type.DOG, ++dogs);
                case FISH -> map.put(Type.FISH, ++birds);
                case BIRD -> map.put(Type.BIRD, ++fish);
                case SPIDER -> map.put(Type.SPIDER, ++spiders);
                default -> {
                }
            }
        }

        return map;
    }

    // Task 4
    static Animal findAnimalWithLongestName(@NotNull List<Animal> animals) {
        Optional<Animal> animalWithLongestName =
            animals.stream().max(Comparator.comparing(animal -> animal.name().length()));
        return animalWithLongestName.orElse(null);
    }

    // Task 5
    static Sex findMostPopularSex(@NotNull List<Animal> animals) {
        long females = animals.stream().filter(animal -> animal.sex() == Sex.F).count();
        long males = animals.stream().filter(animal -> animal.sex() == Sex.M).count();

        if (females > males) {
            return Sex.F;
        }

        if (females < males) {
            return Sex.M;
        }

        return animals.get(0).sex();
    }

    // Task 6
    static @NotNull Map<Type, Animal> createHeaviestAnimalMap(@NotNull List<Animal> animals) {

        Map<Type, Animal> map = new HashMap<>();

        for (Type type : Animal.Type.values()) {
            Optional<Animal> heaviestAnimal = animals.stream()
                .filter(animal -> animal.type() == type)
                .max(Comparator.comparing(Animal::weight));

            heaviestAnimal.ifPresent(animal -> map.put(type, animal));
        }

        return map;
    }

    // Task 7
    static Animal findOldestAnimalK(@NotNull List<Animal> animals, int k) {
        if (k < 1) {
            return new Animal(null, null, null, 0, 0, 0, false);
        }

        return animals.stream()
            .sorted(Comparator.comparing(animal -> -animal.age()))
            .limit(k)
            .toList()
            .get(k - 1);
    }

    // Task 8
    static Animal findHeaviestAnimalWithHeightLowerK(@NotNull List<Animal> animals, int k) {
        if (k <= 1) {
            return new Animal(null, null, null, 0, 0, 0, false);
        }

        Optional<Animal> heaviestAnimal = animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparing(Animal::weight));

        return heaviestAnimal.orElse(null);
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

        Map<Type, Integer> map = new HashMap<>();

        if (!(k > l || l < 0)) {
            for (Type type : Type.values()) {
                Integer sum = animals.stream()
                    .filter(animal -> animal.type() == type && animal.age() >= k && animal.age() <= l)
                    .reduce(0, (sumWeight, animal) -> sumWeight + animal.weight(), Integer::sum);

                map.put(type, sum);
            }
        }

        return map;
    }

    // Task 16
    static List<Animal> sortTypeSexName(@NotNull List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type))
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
    static @NotNull Boolean isSpidersBiteMoreThanDogs(@NotNull List<Animal> animals) {
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
        Animal heaviestFish = new Animal(null, null, null, 0, 0, 0, false);
        for (List<Animal> list : lists) {
            if (list != null) {
                Animal heaviestFishInList = list.stream()
                    .filter(animal -> animal.type() == Type.FISH)
                    .sorted(Comparator.comparingInt(Animal::weight))
                    .toList()
                    .reversed()
                    .get(0);

                heaviestFish =
                    (heaviestFishInList.weight() > heaviestFish.weight()) ? heaviestFishInList : heaviestFish;
            }
        }
        return heaviestFish;
    }

    // Task 19
    static @NotNull Map<String, Set<ValidationError>> findIncorrectAnimals(@NotNull List<Animal> animals) {
        Map<String, Set<ValidationError>> map = new HashMap<>();

        Set<ValidationError> errors = new HashSet<>();

        boolean isNameChecked = false;
        boolean isAgeChecked = false;
        boolean isHeightChecked = false;
        boolean isWeightChecked = false;
        boolean newIteration = false;

        for (int i = 0; i < animals.size(); i++) {

            Animal animal = animals.stream().toList().get(i);

            // Если новая итерация, обнуляем все
            if (newIteration) {
                isNameChecked = false;
                isAgeChecked = false;
                isHeightChecked = false;
                isWeightChecked = false;
                errors = new HashSet<>();
            }

            try {
                newIteration = false;

                if (!isNameChecked) {
                    isNameChecked = true;
                    if (animal.name().replace(" ", "").isEmpty()) {
                        throw new ValidationError("Некорректное имя!");
                    }
                }

                if (!isAgeChecked) {
                    isAgeChecked = true;
                    if (animal.age() <= 0) {
                        throw new ValidationError("Некорректный возраст!");
                    }
                }

                if (!isHeightChecked) {
                    isHeightChecked = true;
                    if (animal.height() <= 0) {
                        throw new ValidationError("Некорректный рост!");
                    }
                }

                if (!isWeightChecked) {
                    isWeightChecked = true;
                    if (animal.weight() <= 0) {
                        throw new ValidationError("Некорректный вес!");
                    }
                }

                newIteration = true;

            } catch (ValidationError e) {
                // Если в животном ошибка, то возвращаемся к тому же животному
                i--;

                errors.add(e);
                map.put(animal.name(), errors);
            }
        }
        return map;
    }

    static @NotNull Map<String, String> findIncorrectAnimals2(@NotNull List<Animal> animals) {
        Map<String, Set<ValidationError>> oldMap = findIncorrectAnimals(animals);
        Map<String, String> newMap = new HashMap<>();

        for (Map.Entry<String, Set<ValidationError>> entry : oldMap.entrySet()) {
            StringBuilder errorsStr = new StringBuilder();

            Set<ValidationError> errors = entry.getValue();

            for (ValidationError error : errors.stream().sorted(Comparator.comparing(o -> o.message)).toList()) {
                errorsStr.append(error.message).append(" ");
            }

            newMap.put(entry.getKey(), String.valueOf(errorsStr).trim());
        }

        return newMap;
    }
}
