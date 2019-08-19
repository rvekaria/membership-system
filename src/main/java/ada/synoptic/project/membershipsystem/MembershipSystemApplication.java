package ada.synoptic.project.membershipsystem;

import ada.synoptic.project.membershipsystem.domain.*;
import ada.synoptic.project.membershipsystem.rest.MemberController;
import ada.synoptic.project.membershipsystem.rest.resource.CreateNewMemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MembershipSystemApplication implements CommandLineRunner {

    @Autowired
    private MemberRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(MembershipSystemApplication.class, args);
    }

    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of customers
        repository.save(Member.createNewMemberWithInitialBalance("Alice", "Smith", 30));
        repository.save(Member.createNewMember("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Member member : repository.findAll()) {
            System.out.println(member);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println();

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Member customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }

        System.out.println("Customers found with findByMemberId(1):");
        System.out.println("--------------------------------");
        System.out.print(repository.findByMemberId(1));

        System.out.println("Update member 2's balance");
        Member updateMember = repository.findByMemberId(2);
        updateMember.setBalance(10);
        repository.save(updateMember);
        System.out.println("All Members:");
        for (Member member : repository.findAll()) {
            System.out.println(member);
        }

        MemberClient memberClient = new MemberClientImpl(repository);
        MemberService memberService = new MemberServiceImpl(memberClient);
        MemberController controller = new MemberController(memberService);


        CreateNewMemberRequest createNewMemberRequest = new CreateNewMemberRequest("John", "Nelson");
        controller.addNewMember(createNewMemberRequest);

    }

}

