package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static edu.hw4.Animal.Sex.*;
import static edu.hw4.Animal.Type.*;
import static edu.hw4.Main.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestAnimal {

    List<Animal> animals1 = new ArrayList<>(List.of(
        new Animal("Murka", CAT, F, 2, 10, 2, true),
        new Animal("Dori", FISH, F, 3, 20, 2, false),
        new Animal("Spike", DOG, M, 8, 110, 20, true),
        new Animal("Tom", CAT, M, 5, 80, 4, true),
        new Animal("Jewel", BIRD, F, 2, 25, 1, false),
        new Animal("Peter Parker Spider-Man", SPIDER, M, 18, 180, 70, false),
        new Animal("Tigra", CAT, F, 3, 15, 3, true),
        new Animal("Marli", DOG, M, 11, 40, 15, true),
        new Animal("Nemo", FISH, M, 1, 10, 1, false)
    ));

    List<Animal> animals2 = new ArrayList<>(List.of(
        new Animal("Puss In Boots", CAT, M, 3, 30, 3, true),
        new Animal("Fish1", FISH, M, 3, 100, 10, true),
        new Animal("Fish2", FISH, F, 2, 25, 1, false),
        new Animal("Cat Bazilio From Buratino", CAT, M, 5, 100, 5, true),
        new Animal("Fish3", FISH, F, 1, 10, 2, false),
        new Animal("Fish4", FISH, F, 3, 20, 5, false),
        new Animal("Fish5", FISH, F, 3, 20, 5, false),
        new Animal("Hatiko", DOG, M, 5, 30, 10, false)
    ));

    List<Animal> animalsWithSomeIncorrect = new ArrayList<>(List.of(
        new Animal("Murka", CAT, F, -2, 10, 2, true),
        new Animal("Dori", FISH, F, 3, -20, 2, false),
        new Animal("       ", SPIDER, M, -18, 180, -70, false),
        new Animal("Correct animal", CAT, F, 5, 15, 3, false)
    ));

    @Test
    @DisplayName("Тест задачи 1")
    void testTask1() {
        List<Animal> sortedAnimals1 = sortByAgeAsc(animals1);
        List<Animal> sortedAnimals2 = sortByAgeAsc(animals2);

        ArrayList<Integer> ages1 = new ArrayList<>();
        ArrayList<Integer> ages2 = new ArrayList<>();

        for (Animal animal: sortedAnimals1) {
            ages1.add(animal.age());
        }

        for (Animal animal: sortedAnimals2) {
            ages2.add(animal.age());
        }

        assertEquals(ages1, new ArrayList<>(List.of(1, 2, 2, 3, 3, 5, 8, 11, 18)));
        assertEquals(ages2, new ArrayList<>(List.of(1, 2, 3, 3, 3, 3, 5, 5)));
    }

    @Test
    @DisplayName("Тест задачи 2")
    void testTask2() {

        List<Animal> sortedAnimals1 = sortByWeightDescChooseK(animals1, 3);
        List<Animal> sortedAnimals2 = sortByWeightDescChooseK(animals2, 5);
        List<Animal> sortedAnimals3 = sortByWeightDescChooseK(animals1, 0);

        try {
            sortByWeightDescChooseK(animals2, -5);
        } catch (Exception e) {
            assertInstanceOf(IllegalArgumentException.class, e);
        }

        ArrayList<Integer> weights1 = new ArrayList<>();
        ArrayList<Integer> weights2 = new ArrayList<>();

        for (Animal animal: sortedAnimals1) {
            weights1.add(animal.weight());
        }

        for (Animal animal: sortedAnimals2) {
            weights2.add(animal.weight());
        }

        assertEquals(weights1, new ArrayList<>(List.of(70, 20, 15)));
        assertEquals(weights2, new ArrayList<>(List.of(10, 10, 5, 5, 5)));
        assertEquals(sortedAnimals3, new ArrayList<>());
    }

    @Test
    @DisplayName("Тест задачи 3")
    void testTask3() {
        Map<Animal.Type, Integer> map1 = createAnimalMap(animals1);
        Map<Animal.Type, Integer> map2 = createAnimalMap(animals2);

        assertTrue(map1.values().containsAll(List.of(1, 1, 2, 2, 3)));
        assertTrue(map2.values().containsAll(List.of(1, 2, 5)));
    }

    @Test
    @DisplayName("Тест задачи 4")
    void testTask4() {
        Animal animal1 = findAnimalWithLongestName(animals1);
        Animal animal2 = findAnimalWithLongestName(animals2);

        assertEquals(animal1.name(), "Peter Parker Spider-Man");
        assertEquals(animal2.name(), "Cat Bazilio From Buratino");
    }

    @Test
    @DisplayName("Тест задачи 5")
    void testTask5() {
        assertEquals(M, findMostPopularSex(animals1));

        // M = F, но M первый по порядку добавления
        assertEquals(M, findMostPopularSex(animals2));
    }

    @Test
    @DisplayName("Тест задачи 6")
    void testTask6() {

        Map<Animal.Type, Animal> map1 = createHeaviestAnimalMap(animals1);
        Map<Animal.Type, Animal> map2 = createHeaviestAnimalMap(animals2);

        ArrayList<String> names1 = new ArrayList<>();
        ArrayList<String> names2 = new ArrayList<>();

        for (Map.Entry<Animal.Type, Animal> entry: map1.entrySet()) {
            names1.add(entry.getValue().name());
        }

        for (Map.Entry<Animal.Type, Animal> entry: map2.entrySet()) {
            names2.add(entry.getValue().name());
        }

        assertTrue(names1.containsAll(List.of("Tom", "Spike", "Jewel", "Dori", "Peter Parker Spider-Man")));
        assertTrue(names2.containsAll(List.of("Cat Bazilio From Buratino", "Hatiko", "Fish1")));
    }

    @Test
    @DisplayName("Тест задачи 7")
    void testTask7() {

        Animal animal1 = findOldestAnimalK(animals1, 3);
        Animal animal2 = findOldestAnimalK(animals2, 5);

        try {
            findOldestAnimalK(animals1, 0);
        } catch (Exception e) {
            assertInstanceOf(ArrayIndexOutOfBoundsException.class, e);
        }

        try {
            findOldestAnimalK(animals2, -3);
        } catch (Exception e) {
            assertInstanceOf(IllegalArgumentException.class, e);
        }

        assertEquals(animal1.name(), "Spike");
        assertEquals(animal2.name(), "Fish4"); // В случае равенства смотрим на порядок добавления
    }

    @Test
    @DisplayName("Тест задачи 8")
    void testTask8() {

        Animal animal1 = findHeaviestAnimalWithHeightLowerK(animals1, 100);
        Animal animal2 = findHeaviestAnimalWithHeightLowerK(animals2, 30);
        Animal animal3 = findHeaviestAnimalWithHeightLowerK(animals1, 0);

        assertEquals(animal1.name(), "Marli");
        assertEquals(animal2.name(), "Fish4"); // В случае равенства смотрим на порядок добавления
        assertNull(animal3);
    }

    @Test
    @DisplayName("Тест задачи 9")
    void testTask9() {
        assertEquals(sumAllPaws(animals1), 30);
        assertEquals(sumAllPaws(animals2), 12);
    }

    @Test
    @DisplayName("Тест задачи 10")
    void testTask10() {
        ArrayList<String> names1 = new ArrayList<>();
        ArrayList<String> names2 = new ArrayList<>();

        for (Animal animal: findAgeNotEqualPaws(animals1)) {
            names1.add(animal.name());
        }

        for (Animal animal: findAgeNotEqualPaws(animals2)) {
            names2.add(animal.name());
        }

        assertFalse(names1.contains("Jewel"));
        assertEquals(names2.size(), animals2.size());
    }

    @Test
    @DisplayName("Тест задачи 11")
    void testTask11() {

        ArrayList<String> names1 = new ArrayList<>();
        ArrayList<String> names2 = new ArrayList<>();

        for (Animal animal: findBitesAndHeightMore100(animals1)) {
            names1.add(animal.name());
        }

        for (Animal animal: findBitesAndHeightMore100(animals2)) {
            names2.add(animal.name());
        }

        assertTrue(names1.contains("Spike") && names1.size() == 1);
        assertTrue(names2.isEmpty());
    }

    @Test
    @DisplayName("Тест задачи 12")
    void testTask12() {
        assertEquals(countWeightMoreHeight(animals1), 0);
        assertEquals(countWeightMoreHeight(animals2), 0);
    }

    @Test
    @DisplayName("Тест задачи 13")
    void testTask13() {

        ArrayList<String> names1 = new ArrayList<>();
        ArrayList<String> names2 = new ArrayList<>();

        for (Animal animal: findAnimalsWithNameMore2Words(animals1)) {
            names1.add(animal.name());
        }

        for (Animal animal: findAnimalsWithNameMore2Words(animals2)) {
            names2.add(animal.name());
        }

        assertTrue(names1.contains("Peter Parker Spider-Man") && names1.size() == 1);
        assertTrue(names2.containsAll(List.of("Cat Bazilio From Buratino", "Puss In Boots")));
    }

    @Test
    @DisplayName("Тест задачи 14")
    void testTask14() {
        assertTrue(isDogHeightMoreK(animals1, 50));
        assertFalse(isDogHeightMoreK(animals2, 500));
    }

    @Test
    @DisplayName("Тест задачи 15")
    void testTask15() {
        Map<Animal.Type, Integer> map11 = sumWeightAgeFormKToL(animals1, 2, 10);
        Map<Animal.Type, Integer> map12 = sumWeightAgeFormKToL(animals1, 2, 1);
        Map<Animal.Type, Integer> map21 = sumWeightAgeFormKToL(animals2, 0, 5);
        Map<Animal.Type, Integer> map22 = sumWeightAgeFormKToL(animals2, -3, -1);

        int sum11 = 0;
        int sum12 = 0;
        int sum21 = 0;
        int sum22 = 0;

        for (Map.Entry<Animal.Type, Integer> entry: map11.entrySet()) {
            sum11 += entry.getValue();
        }

        for (Map.Entry<Animal.Type, Integer> entry: map12.entrySet()) {
            sum12 += entry.getValue();
        }

        for (Map.Entry<Animal.Type, Integer> entry: map21.entrySet()) {
            sum21 += entry.getValue();
        }

        for (Map.Entry<Animal.Type, Integer> entry: map22.entrySet()) {
            sum22 += entry.getValue();
        }

        assertEquals(sum11, 32);
        assertEquals(sum12, 0);
        assertEquals(sum21, 41);
        assertEquals(sum22, 0);
    }

    @Test
    @DisplayName("Тест задачи 16")
    void testTask16() {
        List<Animal> sortedAnimals1 = sortTypeSexName(animals1);
        List<Animal> sortedAnimals2 = sortTypeSexName(animals2);

        ArrayList<String> names1 = new ArrayList<>();
        ArrayList<String> names2 = new ArrayList<>();

        for (Animal animal : sortedAnimals1) {
            names1.add(animal.name());
        }

        for (Animal animal : sortedAnimals2) {
            names2.add(animal.name());
        }

        assertEquals(
            names1,
            new ArrayList<>(List.of("Tom",
                "Murka",
                "Tigra",
                "Marli",
                "Spike",
                "Jewel",
                "Nemo",
                "Dori",
                "Peter Parker Spider-Man"
            ))
        );
        assertEquals(names2, new ArrayList<>(List.of("Cat Bazilio From Buratino", "Puss In Boots", "Hatiko",
            "Fish1", "Fish2", "Fish3", "Fish4", "Fish5")));
    }

    @Test
    @DisplayName("Тест задачи 17")
    void testTask17() {
        assertFalse(areSpidersBiteMoreThanDogs(animals1));
        assertFalse(areSpidersBiteMoreThanDogs(animals2));
    }

    @Test
    @DisplayName("Тест задачи 18")
    void testTask18() {
        assertEquals(findHeaviestFishInLists(animals1, animals2, animalsWithSomeIncorrect).name(), "Fish1");
        assertEquals(findHeaviestFishInLists(animals1, animals2).name(), "Fish1");
    }

    @Test
    @DisplayName("Тест задачи 19")
    void testTask19() {

        Map<String, Set<ValidationError>> incorrectAnimals = findIncorrectAnimals(animalsWithSomeIncorrect);
        ArrayList<Integer> countErrors = new ArrayList<>();

        for (Map.Entry<String, Set<ValidationError>> entry : incorrectAnimals.entrySet()) {
            int errors = 0;
            for (ValidationError ignored : entry.getValue()) {
                errors++;
            }
            countErrors.add(errors);
        }

        assertEquals(countErrors, new ArrayList<>(List.of(1, 1, 3)));
    }

    @Test
    @DisplayName("Тест задачи 20")
    void testTask20() {
        Map<String, String> incorrectAnimals = findIncorrectAnimals2(animalsWithSomeIncorrect);

        ArrayList<String> errorStrs = new ArrayList<>();

        for (Map.Entry<String, String> entry : incorrectAnimals.entrySet()) {
            errorStrs.add(entry.getValue());
        }

        assertTrue(errorStrs.containsAll(List.of("Некорректный рост!",
            "Некорректный возраст!",
            "Некорректное имя! Некорректный вес! Некорректный возраст!")) && errorStrs.size() == 3);
    }
}
