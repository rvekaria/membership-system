package ada.synoptic.project.membershipsystem;

import ada.synoptic.project.membershipsystem.domain.*;
import ada.synoptic.project.membershipsystem.rest.MemberController;
import ada.synoptic.project.membershipsystem.rest.resource.CreateNewEmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MembershipSystemApplication implements CommandLineRunner {

    @Autowired
    private EmployeeRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(MembershipSystemApplication.class, args);
    }

    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of customers
        repository.save(Employee.createNewMemberWithInitialBalance("Alice", "Smith", 30));
        repository.save(Employee.createNewMember("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Employee employee : repository.findAll()) {
            System.out.println(employee);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println();

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Employee customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }

        System.out.println("Customers found with findByEmployeeId(1):");
        System.out.println("--------------------------------");
        System.out.print(repository.findByEmployeeId(1));

        System.out.println("Update member 2's balance");
        Employee updateEmployee = repository.findByEmployeeId(2);
        updateEmployee.setBalance(10);
        repository.save(updateEmployee);
        System.out.println("All Members:");
        for (Employee employee : repository.findAll()) {
            System.out.println(employee);
        }

        MemberClient memberClient = new MemberClientImpl(repository);
        MemberService memberService = new MemberServiceImpl(memberClient);
        MemberController controller = new MemberController(memberService);


        CreateNewEmployeeRequest createNewEmployeeRequest = new CreateNewEmployeeRequest("John", "Nelson");
        controller.addNewMember(createNewEmployeeRequest);

    }

}

