package org.mfusco;

import java.time.LocalDate;

public class Person {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int income;

    @Override
    public String toString() {
        return "Person {" +
                " firstName = \"" + firstName + "\"" +
                ", lastName = \"" + lastName + "\"" +
                ", birthDate = " + birthDate +
                ", income = " + income +
                " }";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getIncome() {
        return income;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        return birthDate.until(LocalDate.now()).getYears();
    }
}
