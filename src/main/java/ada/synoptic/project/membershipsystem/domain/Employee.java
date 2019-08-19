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
    private final String email;
    private final String mobileNo;
    private final String pin;
    private double balance;

    private Employee(String firstName, String lastName, String email, String mobileNo, String pin, double balance) {
        this.employeeId = count.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.pin = pin;
        this.balance = balance;
    }

    public static Employee createNewMemberWithInitialBalance(String firstName, String lastName, String email, String mobileNo, String pin, double initialBalance) {
        return new Employee(firstName, lastName, email, mobileNo, pin, initialBalance);
    }

    public static Employee createNewMember(String firstName, String lastName, String email, String mobileNo, String pin) {
        return new Employee(firstName, lastName, email, mobileNo, pin, 0);
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

    public String getEmail() {
        return email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", pin='" + pin + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId &&
                Double.compare(employee.balance, balance) == 0 &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(mobileNo, employee.mobileNo) &&
                Objects.equals(pin, employee.pin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(employeeId, firstName, lastName, email, mobileNo, pin, balance);
    }
}
