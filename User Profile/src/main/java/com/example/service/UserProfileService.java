package com.example.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class UserProfileService {
    private final MongoCollection<Document> profileCollection;

    public UserProfileService(MongoDatabase database) {
        this.profileCollection = database.getCollection("users");
    }

    // Method to create a new user profile
    public void createProfile(String username, String bio, String profilePictureUrl) {
        Document profile = new Document("username", username)
                .append("bio", bio)
                .append("profilePictureUrl", profilePictureUrl);
        profileCollection.insertOne(profile);
        System.out.println("Profile created: " + profile);
    }

    // Method to retrieve a user profile by username
    public Document getProfileByUsername(String username) {
        return profileCollection.find(Filters.eq("username", username)).first();
    }

    // Method to update the bio of an existing user profile
    public void updateBio(String username, String newBio) {
        profileCollection.updateOne(Filters.eq("username", username), Updates.set("bio", newBio));
        System.out.println("Bio updated for username: " + username);
    }

    // Method to delete a user profile by username
    public void deleteProfile(String username) {
        profileCollection.deleteOne(Filters.eq("username", username));
        System.out.println("Profile deleted for username: " + username);
    }
}
