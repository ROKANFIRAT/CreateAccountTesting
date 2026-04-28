import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Account {

    public boolean createAccount(
            String firstName,
            String lastName,
            String email,
            String dateOfBirth,
            String password,
            String confirmPassword
    ) {
        return isValidName(firstName)
                && isValidName(lastName)
                && isValidEmail(email)
                && isValidDateOfBirth(dateOfBirth)
                && isValidPassword(password)
                && doPasswordsMatch(password, confirmPassword);
    }

    public boolean isValidName(String name) {
        if (name == null) {
            return false;
        }

        return name.matches("^[A-Za-z]{2,30}$");
    }

    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }

        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public boolean isValidDateOfBirth(String dateOfBirth) {
        if (dateOfBirth == null) {
            return false;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);
            LocalDate today = LocalDate.now();

            if (birthDate.isAfter(today)) {
                return false;
            }

            int age = Period.between(birthDate, today).getYears();
            return age >= 13;

        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }

        return password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[!@#$%^&*()].*");
    }

    public boolean doPasswordsMatch(String password, String confirmPassword) {
        if (password == null || confirmPassword == null) {
            return false;
        }

        return password.equals(confirmPassword);
    }
}