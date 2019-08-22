package ada.synoptic.project.membershipsystem.rest.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class RegisterNewEmployeeRequest {

    private String cardId;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private String pin;
    private double balance;

    @JsonCreator
    public RegisterNewEmployeeRequest(@JsonProperty("cardId") String cardId,
                                      @JsonProperty("employeeId") String employeeId,
                                      @JsonProperty("firstName") String firstName,
                                      @JsonProperty("lastName") String lastName,
                                      @JsonProperty("email") String email,
                                      @JsonProperty("mobileNo") String mobileNo,
                                      @JsonProperty("pin") String pin,
                                      @JsonProperty("balance") double balance) {
        this.cardId = cardId;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.pin = pin;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmployeeId() {
        return employeeId;
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

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "RegisterNewEmployeeRequest{" +
                "cardId='" + cardId + '\'' +
                ", employeeId='" + employeeId + '\'' +
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
        RegisterNewEmployeeRequest that = (RegisterNewEmployeeRequest) o;
        return Double.compare(that.balance, balance) == 0 &&
                Objects.equals(cardId, that.cardId) &&
                Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(mobileNo, that.mobileNo) &&
                Objects.equals(pin, that.pin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardId, employeeId, firstName, lastName, email, mobileNo, pin, balance);
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}
