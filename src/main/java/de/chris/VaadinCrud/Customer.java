package de.chris.VaadinCrud;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer
{
    @Id
    @Column(name = "customerid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Customer setCustomerId(Integer customerId) {
        this.customerId = customerId;
        return this;
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
}
