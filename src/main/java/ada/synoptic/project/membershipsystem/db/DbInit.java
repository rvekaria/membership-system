package ada.synoptic.project.membershipsystem.db;

import ada.synoptic.project.membershipsystem.model.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DbInit implements CommandLineRunner {

    private EmployeeRepository repository;
    private PasswordEncoder passwordEncoder;

    public DbInit(EmployeeRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        String encodedPin = this.passwordEncoder.encode("0000");
        Employee testUser = Employee.createNewMemberWithInitialBalance("test", "test", "test", "test", "test", "test", encodedPin, 0);
        testUser.setRole("TEST");
        repository.save(testUser);
    }
}
