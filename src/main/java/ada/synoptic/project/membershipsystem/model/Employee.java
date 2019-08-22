package ada.synoptic.project.membershipsystem.model;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Employee {

    @Id
    private String cardId;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private String pin;
    private double balance;
    private boolean active;
    private String role;

    private Employee(String cardId, String employeeId, String firstName, String lastName, String email, String mobileNo, String pin, double balance) {
        this.cardId = cardId;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.pin = pin;
        this.balance = balance;
        this.active = true;
        this.role = "user";
    }

    public boolean isActive() {
        return active;
    }

    public static Employee createNewMemberWithInitialBalance(String cardNumber, String employeeId, String firstName, String lastName, String email, String mobileNo, String pin, double initialBalance) {
        return new Employee(cardNumber, employeeId, firstName, lastName, email, mobileNo, pin, initialBalance);
    }

    public static Employee createNewMember(String cardNumber, String employeeId, String firstName, String lastName, String email, String mobileNo, String pin) {
        return new Employee(cardNumber, employeeId, firstName, lastName, email, mobileNo, pin, 0);
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.balance, balance) == 0 &&
                active == employee.active &&
                Objects.equals(cardId, employee.cardId) &&
                Objects.equals(employeeId, employee.employeeId) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(mobileNo, employee.mobileNo) &&
                Objects.equals(pin, employee.pin) &&
                Objects.equals(role, employee.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardId, employeeId, firstName, lastName, email, mobileNo, pin, balance, active, role);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "cardId='" + cardId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", pin='" + pin + '\'' +
                ", balance=" + balance +
                ", active=" + active +
                ", role='" + role + '\'' +
                '}';
    }
}
