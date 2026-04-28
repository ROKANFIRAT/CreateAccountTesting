import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @AfterEach
    void tearDown() {
        account = null;
    }

    @Test
    void validAccountShouldBeAccepted() {
        assertTrue(account.createAccount(
                "Rokan",
                "Firat",
                "rokan@gmail.com",
                "12/05/2000",
                "Rokan@123",
                "Rokan@123"
        ));
    }

    @Test
    void emptyFirstNameShouldBeRejected() {
        assertFalse(account.isValidName(""));
    }

    @Test
    void shortFirstNameShouldBeRejected() {
        assertFalse(account.isValidName("A"));
    }

    @Test
    void firstNameWithTwoLettersShouldBeAccepted() {
        assertTrue(account.isValidName("Al"));
    }

    @Test
    void longFirstNameShouldBeRejected() {
        assertFalse(account.isValidName("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    void firstNameWithNumberShouldBeRejected() {
        assertFalse(account.isValidName("Ali1"));
    }

    @Test
    void emptyLastNameShouldBeRejected() {
        assertFalse(account.isValidName(""));
    }

    @Test
    void emailWithoutAtShouldBeRejected() {
        assertFalse(account.isValidEmail("rokangmail.com"));
    }

    @Test
    void emailWithoutDomainShouldBeRejected() {
        assertFalse(account.isValidEmail("rokan@"));
    }

    @Test
    void validEmailShouldBeAccepted() {
        assertTrue(account.isValidEmail("rokan@gmail.com"));
    }

    @Test
    void emptyDateOfBirthShouldBeRejected() {
        assertFalse(account.isValidDateOfBirth(""));
    }

    @Test
    void wrongDateFormatShouldBeRejected() {
        assertFalse(account.isValidDateOfBirth("2000/05/12"));
    }

    @Test
    void futureDateShouldBeRejected() {
        assertFalse(account.isValidDateOfBirth("01/01/2030"));
    }

    @Test
    void underAgeUserShouldBeRejected() {
        assertFalse(account.isValidDateOfBirth("01/01/2020"));
    }

    @Test
    void weakPasswordShouldBeRejected() {
        assertFalse(account.isValidPassword("abc123"));
    }

    @Test
    void strongPasswordShouldBeAccepted() {
        assertTrue(account.isValidPassword("Rokan@123"));
    }

    @Test
    void differentConfirmPasswordShouldBeRejected() {
        assertFalse(account.doPasswordsMatch("Rokan@123", "Rokan@999"));
    }

    @Test
    void sqlInjectionNameShouldBeRejected() {
        assertFalse(account.isValidName("Ali' OR '1'='1"));
    }

    @Test
    void xssNameShouldBeRejected() {
        assertFalse(account.isValidName("<script>alert(1)</script>"));
    }
}