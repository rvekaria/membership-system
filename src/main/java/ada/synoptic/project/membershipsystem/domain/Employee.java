package ada.synoptic.project.membershipsystem.domain;

import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee {

    private static final AtomicInteger count = new AtomicInteger(0);
    @Id
    private int employeeId;
    private String firstName;
    private String lastName;
    private double balance;

    private Employee(String firstName, String lastName, double balance) {
        this.employeeId = count.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public static Employee createNewMemberWithInitialBalance(String firstName, String lastName, double initialBalance) {
        return new Employee(firstName, lastName, initialBalance);
    }

    public static Employee createNewMember(String firstName, String lastName){
        return new Employee(firstName, lastName, 0);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                '}';
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId &&
                Double.compare(employee.balance, balance) == 0 &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(employeeId, firstName, lastName, balance);
    }
}
