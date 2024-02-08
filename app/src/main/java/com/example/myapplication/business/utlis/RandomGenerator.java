package com.example.myapplication.business.utlis;

import java.util.Random;

public class RandomGenerator {
    public static String getRandomName() {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Henry", "Ivy", "Jack"};
        return names[new Random().nextInt(names.length)];
    }

    // Method to generate a random address
    public static String getRandomAddress() {
        String[] addresses = {"123 Main St", "456 Elm St", "789 Oak St", "321 Pine St", "654 Maple St"};
        return addresses[new Random().nextInt(addresses.length)];
    }

    public static String getRandomBookName() {
        String[] bookNames = {"The Great Gatsby", "To Kill a Mockingbird", "1984", "Pride and Prejudice", "The Catcher in the Rye", "The Hobbit", "The Lord of the Rings", "Harry Potter and the Philosopher's Stone", "The Da Vinci Code", "The Hunger Games"};
        return bookNames[new Random().nextInt(bookNames.length)];
    }

    // Method to generate a random price between 10 and 100
    public static double getRandomPrice() {
        return 10 + (90 * new Random().nextDouble());
    }

    public static float randomEdition() {
        Random random = new Random();
        return 1 + random.nextFloat() * 9;
    }

    public static String randomAuthorName() {
        String[] firstNames = {"John", "Mary", "David", "Emma", "Michael", "Jennifer", "James", "Lisa", "Robert", "Sarah"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};

        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];

        return firstName + " " + lastName;
    }

    public static int generateRandomRating() {
        Random random = new Random();
        return random.nextInt(6)%5; // Generates a random number between 0 and 5
    }

    // Function to generate a random string comment
    public static String generateRandomComment() {
        Random random = new Random();
        String[] comments = {"Great product!", "Could be better", "Average", "Not satisfied", "Excellent service"};
        return comments[random.nextInt(comments.length)]; // Picks a random comment from the array
    }
}
