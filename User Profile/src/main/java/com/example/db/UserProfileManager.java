package com.example.db;
import com.example.service.UserProfileService;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Scanner;

public class UserProfileManager {
    public static void main(String[] args) {
        MongoDatabase database = MongoDBConnection.getDatabase();
        UserProfileService userProfileService = new UserProfileService(database);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the User Profile Manager!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create New Profile");
            System.out.println("2. Update Bio");
            System.out.println("3. View Profile");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createProfile(scanner, userProfileService);
                    break;
                case 2:
                    updateBio(scanner, userProfileService);
                    break;
                case 3:
                    viewProfile(scanner, userProfileService);
                    break;
                case 4:
                    System.out.println("Thank you for using the User Profile Manager!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createProfile(Scanner scanner, UserProfileService userProfileService) {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Bio: ");
        String bio = scanner.nextLine();
        System.out.print("Enter Profile Picture URL: ");
        String profilePictureUrl = scanner.nextLine();

        userProfileService.createProfile(username, bio, profilePictureUrl);
        System.out.println("Profile created successfully for username: " + username);
    }

    private static void updateBio(Scanner scanner, UserProfileService userProfileService) {
        System.out.print("Enter Username to update bio: ");
        String username = scanner.nextLine();
        System.out.print("Enter new Bio: ");
        String newBio = scanner.nextLine();

        userProfileService.updateBio(username, newBio);
        System.out.println("Bio updated successfully for username: " + username);
    }

    private static void viewProfile(Scanner scanner, UserProfileService userProfileService) {
        System.out.print("Enter Username to view profile: ");
        String username = scanner.nextLine();
        Document profile = userProfileService.getProfileByUsername(username);
        
        if (profile != null) {
            System.out.println("Username: " + profile.getString("username"));
            System.out.println("Bio: " + profile.getString("bio"));
            System.out.println("Profile Picture URL: " + profile.getString("profilePictureUrl"));
        } else {
            System.out.println("Profile not found for username: " + username);
        }
    }
}
