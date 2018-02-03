package com.google.firebase.quickstart;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.UserRecord.UpdateRequest;
import com.google.firebase.example.ServiceFireBaseQuy;
import com.google.firebase.example.ServiceFireBaseDung;
import com.google.firebase.tasks.Task;
import com.googlecus.map.bo.Impl.LocationBoImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Auth snippets for documentation.
 *
 * See:
 * https://firebase.google.com/docs/auth/admin
 */
public class ServiceMainBk {

  public static Task<UserRecord> getUserById(String uid) {
    // [START get_user_by_id]
    Task<UserRecord> task = FirebaseAuth.getInstance().getUser(uid)
        .addOnSuccessListener(userRecord -> {
          // See the UserRecord reference doc for the contents of userRecord.
          System.out.println("Successfully fetched user data: " + userRecord.getUid());
        })
        .addOnFailureListener(e -> {
          System.err.println("Error fetching user data: " + e.getMessage());
        });
    // [END get_user_by_id]

    return task;
  }

  public static Task<UserRecord> getUserByEmail(String email) {
    // [START get_user_by_email]
    Task<UserRecord> task = FirebaseAuth.getInstance().getUserByEmail(email)
        .addOnSuccessListener(userRecord -> {
          // See the UserRecord reference doc for the contents of userRecord.
          System.out.println("Successfully fetched user data: " + userRecord.getEmail());
        })
        .addOnFailureListener(e -> {
          System.err.println("Error fetching user data: " + e.getMessage());
        });
    // [END get_user_by_email]

    return task;
  }

  public static Task<UserRecord> getUserByPhoneNumber(String phoneNumber) {
	 
    // [START get_user_by_phone]
    Task<UserRecord> task = FirebaseAuth.getInstance().getUserByPhoneNumber(phoneNumber)
        .addOnSuccessListener(userRecord -> {
          // See the UserRecord reference doc for the contents of userRecord.
          System.out.println("Successfully fetched user data: " + userRecord.getPhoneNumber());
        })
        .addOnFailureListener(e -> {
          System.err.println("Error fetching user data: " + e.getMessage());
        });
    // [END get_user_by_phone]

    return task;
  }

  public static Task<UserRecord> createUser() {
    // [START create_user]
    CreateRequest request = new CreateRequest()
        .setEmail("user@example.com")
        .setEmailVerified(false)
        .setPassword("secretPassword")
         .setPhoneNumber("+11234567890")
        .setDisplayName("John Doe")
        .setPhotoUrl("http://www.example.com/12345678/photo.png")
        .setDisabled(false);

    Task<UserRecord> task = FirebaseAuth.getInstance().createUser(request)
        .addOnSuccessListener(userRecord -> {
          // See the UserRecord reference doc for the contents of userRecord.
          System.out.println("Successfully created new user: " + userRecord.getUid());
        })
        .addOnFailureListener(e -> {
          System.err.println("Error creating new user: " + e.getMessage());
        });
    // [END create_user]

    return task;
  }

  public static Task<UserRecord> createUserWithUid() {
    // [START create_user_with_uid]
    CreateRequest request = new CreateRequest()
        .setUid("some-uid")
        .setEmail("user@example.com")
       .setPhoneNumber("+11234567890");

    Task<UserRecord> task = FirebaseAuth.getInstance().createUser(request)
        .addOnSuccessListener(userRecord -> {
          // See the UserRecord reference doc for the contents of userRecord.
          System.out.println("Successfully created new user: " + userRecord.getUid());
        })
        .addOnFailureListener(e -> {
          System.err.println("Error creating new user: " + e.getMessage());
        });
    // [END create_user_with_uid]

    return task;
  }

  public static Task<UserRecord> updateUser(String uid) {
    // [START update_user]
    UpdateRequest request = new UpdateRequest(uid)
        .setEmail("user@example.com")
         .setPhoneNumber("+11234567890")
        .setEmailVerified(true)
        .setPassword("newPassword")
        .setDisplayName("Jane Doe")
        .setPhotoUrl("http://www.example.com/12345678/photo.png")
        .setDisabled(true);

    Task<UserRecord> task = FirebaseAuth.getInstance().updateUser(request)
        .addOnSuccessListener(userRecord -> {
          // See the UserRecord reference doc for the contents of userRecord.
          System.out.println("Successfully updated user: " + userRecord.getUid());
        })
        .addOnFailureListener(e -> {
          System.err.println("Error updating user: " + e.getMessage());
        });
    // [END update_user]

    return task;
  }

  public static Task<Void> deleteUser(String uid) {
    // [START delete_user]
    Task<Void> task = FirebaseAuth.getInstance().deleteUser(uid)
        .addOnSuccessListener(aVoid -> System.out.println("Successfully deleted user."))
        .addOnFailureListener(e -> System.err.println("Error updating user: " + e.getMessage()));
    // [END delete_user]

    return task;
  }
//  private static final String DATABASE_URL = "https://demofirebase-97584.firebaseio.com/";
//  private static final String DATABASE_URL = "https://adminsdk-8d22a.firebaseio.com/";
  
  private static final String DATABASE_URL = "https://checkit-e0148.firebaseio.com/";
  public static void main(String[] args) {
    System.out.println("Hello, AuthSnippets!");

    // Initialize Firebase
    try {
      // [START initialize]
    	
    	 FileInputStream serviceAccount = new FileInputStream("service-account.json");
         FirebaseOptions options;
			try {
				options = new FirebaseOptions.Builder()
				        .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
				        .setDatabaseUrl(DATABASE_URL)
				        .build();
				  FirebaseApp.initializeApp(options);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//    	
//      FileInputStream serviceAccount = new FileInputStream("service-account.json");
//      FirebaseOptions options = new FirebaseOptions.Builder()
//          .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
//          .build();
//      FirebaseApp.initializeApp(options);
      // [END initialize]
    } catch (IOException e) {
      System.out.println("ERROR: invalid service account credentials. See README.");
      System.out.println(e.getMessage());

      System.exit(1);
    }
    ServiceFireBaseDung  mn = new ServiceFireBaseDung();
    //mn.deleteAll();
    //mn.service
    mn.service();
 
 
    Scanner scanner = new Scanner(System.in);
    while(!scanner.nextLine().equals("Quit")){}
    System.exit(0);
    // Smoke test
//    createUserWithUid()
//        .continueWithTask(task -> getUserById("some-uid"))
//        .continueWithTask(task -> getUserByEmail("user@example.com"))
//        .continueWithTask(task -> getUserByPhoneNumber("+11234567890"))
//        .continueWithTask(task -> updateUser("some-uid"))
//      
//        .addOnCompleteListener(task -> System.out.println("Done! Success: " + task.isSuccessful()));
    // .continueWithTask(task -> deleteUser("some-uid"))
  }

}
