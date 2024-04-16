package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.time.LocalDate.*;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       TODO:  1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findPersonsWithFirstNameErik = person -> person.getFirstName().equals("Erik");
        List<Person> resultFindPersonsWithFirstNameErik = storage.findMany(findPersonsWithFirstNameErik);
        System.out.println(resultFindPersonsWithFirstNameErik);
        System.out.println("----------------------");
    }

    /*
        TODO:  2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findFemales = person -> person.getGender() == Gender.FEMALE;
        List<Person> resultFindFemales = storage.findMany(findFemales);
        System.out.println(resultFindFemales);
        System.out.println("----------------------");
    }

    /*
        TODO:  3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findPersonsAfter2000 = person -> person.getBirthDate().isAfter(of(2000, 1, 1));
        List<Person> resultFindPersonsAfter2000 = storage.findMany(findPersonsAfter2000);
        System.out.println(resultFindPersonsAfter2000);
        System.out.println("----------------------");
    }

    /*
        TODO: 4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findPersonById123 = person -> person.getId() == 123;
        Person resultFindByPersonId123 = storage.findOne(findPersonById123);
        System.out.println("resultFindByPersonId123 = " + resultFindByPersonId123);
        System.out.println("----------------------");

    }

    /*
        TODO:  5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findPersonById456 = person -> person.getId() == 456;
        Function<Person, String> mapPersonToString = person -> "Name: " + person.getFirstName() + " " + person.getLastName() + " born " + person.getBirthDate();
        String resultFindPersonById456 = storage.findOneAndMapToString(findPersonById456, mapPersonToString);
        System.out.println(resultFindPersonById456);
        System.out.println("----------------------");
    }

    /*
        TODO:  6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findMalePersonsWithFirstNameE = person -> person.getFirstName().startsWith("E");
        Function<Person, String> mapPersonToString = person -> person.getFirstName() + " " + person.getLastName() + " " + person.getBirthDate();
        List<String> resultFindMalePersonsWithFirstNameE = storage.findManyAndMapEachToString(findMalePersonsWithFirstNameE, mapPersonToString);
        System.out.println(resultFindMalePersonsWithFirstNameE); // >
        System.out.println("----------------------");
    }

    /*
        TODO:  7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findAgeBelowTen = person -> {
            LocalDate today = LocalDate.now();
            LocalDate birthDate = person.getBirthDate();
            Period period = Period.between(birthDate, today);
            int age = period.getYears();
            return age < 10;
        };
        System.out.println("----------------------");
    }

    /*
        TODO:  8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findPersonsWithFirstNameUlf = person -> person.getFirstName().equals("Ulf");
        Consumer<Person> printPersonWithFirstNameUlf = person -> System.out.println(person);
        storage.findAndDo(findPersonsWithFirstNameUlf, printPersonWithFirstNameUlf);
        System.out.println("----------------------");
    }

    /*
        TODO:  9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        // Find all people whose last name contains their first name
        Predicate<Person> findPersonsWithMatchingNames = person -> person.getLastName().contains(person.getFirstName());
        // Print each person
        Consumer<Person> printPerson = System.out::println;
        storage.findAndDo(findPersonsWithMatchingNames, printPerson);
        System.out.println("----------------------");
    }

    /*
        TODO:  10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        Predicate<Person> findFirstNamePalindrome = person -> {
            String firstName = person.getFirstName().toLowerCase();
            return firstName.equals(new StringBuilder(firstName).reverse().toString());
        };
        Consumer<Person> printFirstAndLastName = person -> System.out.println(person.getFirstName() + " " + person.getLastName());
        storage.findAndDo(findFirstNamePalindrome, printFirstAndLastName);
        System.out.println("----------------------");
    }

    /*
        TODO:  11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findPersonsWithFirstNameStartingWithA = person -> person.getFirstName().startsWith("A");
        Comparator<Person> compareByBirthDate = Comparator.comparing(Person::getBirthDate);
        List<Person> resultFindPersonsWithFirstNameStartingWithA = storage.findAndSort(findPersonsWithFirstNameStartingWithA, compareByBirthDate);
        System.out.println(resultFindPersonsWithFirstNameStartingWithA);
        System.out.println("----------------------");
    }

    /*
        TODO:  12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findPersonsBornBefore1950 = person -> person.getBirthDate().getYear() < 1950;
        Comparator<Person> compareByBirthDate = Comparator.comparing(Person::getBirthDate).reversed();
        List<Person> resultFindPersonsBornBefore1950 = storage.findAndSort(findPersonsBornBefore1950, compareByBirthDate);
        System.out.println(resultFindPersonsBornBefore1950);
        System.out.println("----------------------");
    }

    /*
        TODO:  13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here
        Comparator<Person> sortByLastNameFirstNameBirthDate = Comparator.comparing(Person::getLastName)
                .thenComparing(Person::getFirstName)
                .thenComparing(Person::getBirthDate);
        List<Person> resultSortByLastNameFirstNameBirthDate = storage.findAndSort(sortByLastNameFirstNameBirthDate);
        resultSortByLastNameFirstNameBirthDate.forEach(System.out::println);


        System.out.println("----------------------");
    }
}
