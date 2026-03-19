package seedu.address.model.outlet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OutletPostalCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OutletPostalCode(null));
    }

    @Test
    public void constructor_invalidPostalCode_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new OutletPostalCode(""));
    }

    @Test
    public void isValidPostalCode() {
        assertThrows(NullPointerException.class, () -> OutletPostalCode.isValidPostalCode(null));

        assertFalse(OutletPostalCode.isValidPostalCode(""));
        assertFalse(OutletPostalCode.isValidPostalCode("12345"));
        assertFalse(OutletPostalCode.isValidPostalCode("1234567"));
        assertFalse(OutletPostalCode.isValidPostalCode("12A456"));

        assertTrue(OutletPostalCode.isValidPostalCode("000000"));
        assertTrue(OutletPostalCode.isValidPostalCode("048623"));
    }
}
