package seedu.medibook.model;

import seedu.medibook.model.doctor.Doctor;

public class Account {

    private String username;
    private String password;
    private Doctor doctor;

    /**
     * Constructs an account with a username and a password.
     */
    public Account(String username, String password, Doctor doctor) {
        this.username = username;
        this.password = password;
        this.doctor = doctor;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    /**
     * Checks if two accounts are equal.
     */
    public boolean equals(Account otherAccount) {
        if (otherAccount == this) {
            return true;
        }
        
        return otherAccount.getUsername().equals(getUsername())
                && otherAccount.getPassword().equals(getPassword());
    }
    
    public boolean check(String username, String password) {
        return getUsername().equals(username) && getPassword().equals(password);
    }
}
