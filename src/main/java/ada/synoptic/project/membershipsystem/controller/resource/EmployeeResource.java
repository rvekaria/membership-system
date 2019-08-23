package ada.synoptic.project.membershipsystem.controller.resource;

import ada.synoptic.project.membershipsystem.model.Employee;

import java.util.Objects;

public class EmployeeResource {

    private Employee employee;
    private String responseMessage;

    public EmployeeResource(Employee employee, String responseMessage) {
        this.employee = employee;
        this.responseMessage = responseMessage;
    }

    public EmployeeResource(Employee employee) {
        this(employee,"");
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeResource that = (EmployeeResource) o;
        return Objects.equals(employee, that.employee) &&
                Objects.equals(responseMessage, that.responseMessage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(employee, responseMessage);
    }

    @Override
    public String toString() {
        return "EmployeeResource{" +
                "employee=" + employee +
                ", responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
