package seedu.address.model.outlet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OutletAddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OutletAddress(null));
    }

    @Test
    public void constructor_invalidOutletAddress_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new OutletAddress(" "));
    }

    @Test
    public void isValidOutletAddress() {
        assertThrows(NullPointerException.class, () -> OutletAddress.isValidOutletAddress(null));

        assertFalse(OutletAddress.isValidOutletAddress(""));
        assertFalse(OutletAddress.isValidOutletAddress("   "));

        assertTrue(OutletAddress.isValidOutletAddress("Raffles Place"));
        assertTrue(OutletAddress.isValidOutletAddress("18 Cross St, #08-01"));
    }
}
