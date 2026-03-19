package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.outlet.Outlet;
import seedu.address.model.outlet.OutletAddress;
import seedu.address.model.outlet.OutletName;
import seedu.address.model.outlet.OutletPostalCode;

/**
 * Jackson-friendly version of {@link Outlet}.
 */
class JsonAdaptedOutlet {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Outlet's %s field is missing!";

    private final String name;
    private final String address;
    private final String postalCode;

    /**
     * Constructs a {@code JsonAdaptedOutlet} with the given outlet details.
     */
    @JsonCreator
    public JsonAdaptedOutlet(@JsonProperty("name") String name,
            @JsonProperty("address") String address,
            @JsonProperty("postalCode") String postalCode) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
    }

    /**
     * Converts a given {@code Outlet} into this class for Jackson use.
     */
    public JsonAdaptedOutlet(Outlet source) {
        name = source.getOutletName().value;
        address = source.getOutletAddress().value;
        postalCode = source.getPostalCode().value;
    }

    /**
     * Converts this Jackson-friendly adapted outlet object into the model's {@code Outlet} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted outlet.
     */
    public Outlet toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OutletName.class.getSimpleName()));
        }
        if (!OutletName.isValidOutletName(name)) {
            throw new IllegalValueException(OutletName.MESSAGE_CONSTRAINTS);
        }
        final OutletName modelOutletName = new OutletName(name);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OutletAddress.class.getSimpleName()));
        }
        if (!OutletAddress.isValidOutletAddress(address)) {
            throw new IllegalValueException(OutletAddress.MESSAGE_CONSTRAINTS);
        }
        final OutletAddress modelOutletAddress = new OutletAddress(address);

        if (postalCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OutletPostalCode.class.getSimpleName()));
        }
        if (!OutletPostalCode.isValidPostalCode(postalCode)) {
            throw new IllegalValueException(OutletPostalCode.MESSAGE_CONSTRAINTS);
        }
        final OutletPostalCode modelPostalCode = new OutletPostalCode(postalCode);

        return new Outlet(modelOutletName, modelOutletAddress, modelPostalCode);
    }
}
