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
public class ServiceMain {

 

 
 

 
 
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
