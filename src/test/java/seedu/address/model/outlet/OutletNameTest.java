package seedu.address.model.outlet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OutletNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OutletName(null));
    }

    @Test
    public void constructor_invalidOutletName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new OutletName(" "));
    }

    @Test
    public void isValidOutletName() {
        assertThrows(NullPointerException.class, () -> OutletName.isValidOutletName(null));

        assertFalse(OutletName.isValidOutletName(""));
        assertFalse(OutletName.isValidOutletName("   "));

        assertTrue(OutletName.isValidOutletName("TechCo"));
        assertTrue(OutletName.isValidOutletName("FinServ Marina"));
    }
}
