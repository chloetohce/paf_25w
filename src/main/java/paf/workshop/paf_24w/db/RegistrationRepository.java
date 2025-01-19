package paf.workshop.paf_24w.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationRepository {
    @Autowired
    @Qualifier("order")
    private RedisTemplate<String, String> template;

    @Value("${app.name}")
    private String appName;

    public List<String> getAllRegistrations() {
        return template.opsForList().range("registrations", 0, template.opsForList().size("registrations"));    
    }

    /**
     * Checks if list of registrations contains the name of the app. Returns false if app is 
     * already registered. Otherwise, adds the app to the list of registrations and then returns true.
     * @param name
     * @return whether there was an operation to register the app
     */
    public boolean registerNewList(String name) {
        ListOperations<String, String> ops = template.opsForList();
        if (ops.indexOf("registrations", name) != null) {
            return false;

        } 
        ops.rightPush("registrations", name);
        return true;
    }
}
