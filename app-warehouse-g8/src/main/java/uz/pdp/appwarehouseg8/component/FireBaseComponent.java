package uz.pdp.appwarehouseg8.component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class FireBaseComponent implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(
                        GoogleCredentials.fromStream(
                                new ClassPathResource("/firebase_config.json").getInputStream()))
                .setDatabaseUrl("https://app-warehouseg8-default-rtdb.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
