package ada.synoptic.project.membershipsystem.domain;

import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Member {

    private static final AtomicInteger count = new AtomicInteger(0);
    @Id
    private int memberId;
    private String firstName;
    private String lastName;
    private double balance;

    private Member(String firstName, String lastName, double balance) {
        this.memberId = count.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public static Member createNewMemberWithInitialBalance(String firstName, String lastName, double initialBalance) {
        return new Member(firstName, lastName, initialBalance);
    }

    public static Member createNewMember(String firstName, String lastName){
        return new Member(firstName, lastName, 0);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                '}';
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return memberId == member.memberId &&
                Double.compare(member.balance, balance) == 0 &&
                Objects.equals(firstName, member.firstName) &&
                Objects.equals(lastName, member.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(memberId, firstName, lastName, balance);
    }
}
