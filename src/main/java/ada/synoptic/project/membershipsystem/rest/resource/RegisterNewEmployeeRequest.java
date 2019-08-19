package ada.synoptic.project.membershipsystem.rest.resource;

import java.util.Objects;

public class RegisterNewEmployeeRequest {

    private String cardNumber;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private String pin;

    public RegisterNewEmployeeRequest(String cardNumber, String employeeId, String firstName, String lastName, String email, String mobileNo, String pin) {
        this.cardNumber = cardNumber;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.pin = pin;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "RegisterNewEmployeeRequest{" +
                "cardNumber='" + cardNumber + '\'' +
                ", employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterNewEmployeeRequest that = (RegisterNewEmployeeRequest) o;
        return employeeId == that.employeeId &&
                Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(mobileNo, that.mobileNo) &&
                Objects.equals(pin, that.pin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cardNumber, employeeId, firstName, lastName, email, mobileNo, pin);
    }
}
