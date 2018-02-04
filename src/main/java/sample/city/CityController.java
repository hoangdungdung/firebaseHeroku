package sample.city;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.example.ServiceFireBaseDung;

@RestController
@Transactional
public class CityController {

  //  private final CityDao dao;

//    @Autowired
//    public CityController(CityDao dao) {
//       // this.dao = dao;
//    }

    @RequestMapping("/")
    public List<City> selectAll() {

        
    	City c= new City(); 
    	c.id=1;
    	c.name="Ha noi";
    	ArrayList<City> arr=new ArrayList<City>();
    	arr.add(c);
      //  City city = dao.selectById(id);
        //city.name = name;
        //dao.update(city);
        //return dao.selectAll();
    	 System.out.println("Hello, AuthSnippets!");
    	 System.out.println("update, AuthSnippets!");
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
//    	      FileInputStream serviceAccount = new FileInputStream("service-account.json");
//    	      FirebaseOptions options = new FirebaseOptions.Builder()
//    	          .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
//    	          .build();
//    	      FirebaseApp.initializeApp(options);
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
    	 
		return arr;
    
    }
    private static final String DATABASE_URL = "https://checkit-e0148.firebaseio.com/";
    @RequestMapping("/update")
    public List<City> updateAndSelectAll(
            @RequestParam(value = "id", defaultValue = "1") int id,
            @RequestParam("name") String name) {
     
    	City c= new City(); 
    	c.id=1;
    	c.name="Ha noi";
    	ArrayList<City> arr=new ArrayList<City>();
    	arr.add(c);
      //  City city = dao.selectById(id);
        //city.name = name;
        //dao.update(city);
        //return dao.selectAll();
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
//    	      FileInputStream serviceAccount = new FileInputStream("service-account.json");
//    	      FirebaseOptions options = new FirebaseOptions.Builder()
//    	          .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
//    	          .build();
//    	      FirebaseApp.initializeApp(options);
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
    	 
		return arr;
    }

}
