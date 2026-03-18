package seedu.address.model.outlet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.outlet.exceptions.DuplicateOutletException;
import seedu.address.model.outlet.exceptions.OutletNotFoundException;

public class UniqueOutletListTest {

    private static final Outlet OUTLET_ALPHA = new Outlet(
            new OutletName("TechCo"),
            new OutletAddress("Raffles Place"),
            new OutletPostalCode("048623"));

    private static final Outlet OUTLET_ALPHA_EDITED = new Outlet(
            new OutletName("TechCo Branch"),
            new OutletAddress("Raffles Place"),
            new OutletPostalCode("048623"));

    private static final Outlet OUTLET_BETA = new Outlet(
            new OutletName("FinServ"),
            new OutletAddress("Marina Bay"),
            new OutletPostalCode("018956"));

    private final UniqueOutletList uniqueOutletList = new UniqueOutletList();

    @Test
    public void contains_nullOutlet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOutletList.contains(null));
    }

    @Test
    public void contains_outletNotInList_returnsFalse() {
        assertFalse(uniqueOutletList.contains(OUTLET_ALPHA));
    }

    @Test
    public void contains_outletInList_returnsTrue() {
        uniqueOutletList.add(OUTLET_ALPHA);
        assertTrue(uniqueOutletList.contains(OUTLET_ALPHA));
    }

    @Test
    public void contains_outletWithSameIdentityInList_returnsTrue() {
        uniqueOutletList.add(OUTLET_ALPHA);
        assertTrue(uniqueOutletList.contains(OUTLET_ALPHA_EDITED));
    }

    @Test
    public void add_duplicateOutlet_throwsDuplicateOutletException() {
        uniqueOutletList.add(OUTLET_ALPHA);
        assertThrows(DuplicateOutletException.class, () -> uniqueOutletList.add(OUTLET_ALPHA_EDITED));
    }

    @Test
    public void setOutlet_outletNotInList_throwsOutletNotFoundException() {
        assertThrows(OutletNotFoundException.class, () -> uniqueOutletList.setOutlet(OUTLET_ALPHA, OUTLET_ALPHA));
    }

    @Test
    public void setOutlet_success() {
        uniqueOutletList.add(OUTLET_ALPHA);
        uniqueOutletList.setOutlet(OUTLET_ALPHA, OUTLET_BETA);
        UniqueOutletList expectedUniqueOutletList = new UniqueOutletList();
        expectedUniqueOutletList.add(OUTLET_BETA);
        assertEquals(expectedUniqueOutletList, uniqueOutletList);
    }

    @Test
    public void setOutlets_listWithDuplicateOutlets_throwsDuplicateOutletException() {
        List<Outlet> listWithDuplicateOutlets = Arrays.asList(OUTLET_ALPHA, OUTLET_ALPHA_EDITED);
        assertThrows(DuplicateOutletException.class, () -> uniqueOutletList.setOutlets(listWithDuplicateOutlets));
    }

    @Test
    public void setOutlets_list_replacesOwnListWithProvidedList() {
        uniqueOutletList.add(OUTLET_ALPHA);
        List<Outlet> outlets = Collections.singletonList(OUTLET_BETA);
        uniqueOutletList.setOutlets(outlets);
        UniqueOutletList expectedUniqueOutletList = new UniqueOutletList();
        expectedUniqueOutletList.add(OUTLET_BETA);
        assertEquals(expectedUniqueOutletList, uniqueOutletList);
    }

    @Test
    public void remove_outletDoesNotExist_throwsOutletNotFoundException() {
        assertThrows(OutletNotFoundException.class, () -> uniqueOutletList.remove(OUTLET_ALPHA));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        List<Outlet> outlets = uniqueOutletList.asUnmodifiableObservableList();
        assertThrows(UnsupportedOperationException.class, () -> outlets.remove(0));
    }
}
