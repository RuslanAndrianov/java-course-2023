package edu.hw3.Task5;

import java.util.Arrays;
import java.util.Comparator;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task5 {

    public static Person[] parseContacts(String[] list, String order) {

        // Граничные случаи
        if (list == null || list.length == 0) {
            return new Person[]{};
        }

        Person[] sortedList = new Person[list.length];

        fillPersonList(list, sortedList);

        sortPersonListASC(sortedList);

        return switch (order) {
            case "ASC" -> sortedList;
            case "DESC" -> {
                reversePersonsList(sortedList);
                yield sortedList;
            }
            default -> new Person[]{}; // остальные ошибочные случаи
        };
    }

    public static void fillPersonList(String @NotNull [] strList, Person[] personList) {
        String wordBoundary = "\\s+";
        for (int i = 0; i < strList.length; i++) {
            // Парсим либо только имя, либо фамилию и имя
            String[] nameAndSurname = strList[i].trim().split(wordBoundary, 2);
            String name = nameAndSurname[0].trim();
            String surname = "";

            if (nameAndSurname.length > 1) {
                // В случае передачи трех и более имен собственных в строке
                // первое парсится как имя, второе - как фамилия,
                // остальное игнорируем (см. testFillPersonList в TestTask5)
                surname = nameAndSurname[1].split(wordBoundary)[0];
            }

            personList[i] = new Person(name, surname);
        }
    }

    public static void sortPersonListASC(Person[] personList) {
        Arrays.sort(personList, Comparator.comparing(person -> {
            String sortingField = person.getSurname();
            if (person.getSurname().isEmpty()) {
                sortingField = person.getName();
            }
            return sortingField;
        }));
    }

    public static void reversePersonsList(Person @NotNull [] list) {
        for (int i = 0; i < list.length / 2; i++) {
            Person temp = list[i];
            list[i] = list[list.length - i - 1];
            list[list.length - i - 1] = temp;
        }
    }
}
