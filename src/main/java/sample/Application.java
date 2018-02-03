package sample;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.example.ServiceFireBaseDung;

@SpringBootApplication
public class Application {
	 
    public static void main(String[] args) {
    	

        SpringApplication.run(Application.class, args);
    }
}
