package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedOutlet.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.outlet.Outlet;
import seedu.address.model.outlet.OutletAddress;
import seedu.address.model.outlet.OutletName;
import seedu.address.model.outlet.OutletPostalCode;

public class JsonAdaptedOutletTest {

    private static final String INVALID_OUTLET_NAME = " ";
    private static final String INVALID_OUTLET_ADDRESS = " ";
    private static final String INVALID_POSTAL_CODE = "12A456";

    private static final String VALID_OUTLET_NAME = "TechCo";
    private static final String VALID_OUTLET_ADDRESS = "Raffles Place";
    private static final String VALID_POSTAL_CODE = "048623";

    private static final Outlet VALID_OUTLET = new Outlet(
            new OutletName(VALID_OUTLET_NAME),
            new OutletAddress(VALID_OUTLET_ADDRESS),
            new OutletPostalCode(VALID_POSTAL_CODE));

    @Test
    public void toModelType_validOutletDetails_returnsOutlet() throws Exception {
        JsonAdaptedOutlet outlet = new JsonAdaptedOutlet(VALID_OUTLET);
        assertEquals(VALID_OUTLET, outlet.toModelType());
    }

    @Test
    public void toModelType_invalidOutletName_throwsIllegalValueException() {
        JsonAdaptedOutlet outlet = new JsonAdaptedOutlet(INVALID_OUTLET_NAME, VALID_OUTLET_ADDRESS, VALID_POSTAL_CODE);
        assertThrows(IllegalValueException.class, OutletName.MESSAGE_CONSTRAINTS, outlet::toModelType);
    }

    @Test
    public void toModelType_nullOutletName_throwsIllegalValueException() {
        JsonAdaptedOutlet outlet = new JsonAdaptedOutlet(null, VALID_OUTLET_ADDRESS, VALID_POSTAL_CODE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, OutletName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, outlet::toModelType);
    }

    @Test
    public void toModelType_invalidOutletAddress_throwsIllegalValueException() {
        JsonAdaptedOutlet outlet = new JsonAdaptedOutlet(VALID_OUTLET_NAME, INVALID_OUTLET_ADDRESS, VALID_POSTAL_CODE);
        assertThrows(IllegalValueException.class, OutletAddress.MESSAGE_CONSTRAINTS, outlet::toModelType);
    }

    @Test
    public void toModelType_nullOutletAddress_throwsIllegalValueException() {
        JsonAdaptedOutlet outlet = new JsonAdaptedOutlet(VALID_OUTLET_NAME, null, VALID_POSTAL_CODE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, OutletAddress.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, outlet::toModelType);
    }

    @Test
    public void toModelType_invalidPostalCode_throwsIllegalValueException() {
        JsonAdaptedOutlet outlet = new JsonAdaptedOutlet(VALID_OUTLET_NAME, VALID_OUTLET_ADDRESS, INVALID_POSTAL_CODE);
        assertThrows(IllegalValueException.class, OutletPostalCode.MESSAGE_CONSTRAINTS, outlet::toModelType);
    }

    @Test
    public void toModelType_nullPostalCode_throwsIllegalValueException() {
        JsonAdaptedOutlet outlet = new JsonAdaptedOutlet(VALID_OUTLET_NAME, VALID_OUTLET_ADDRESS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, OutletPostalCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, outlet::toModelType);
    }
}
