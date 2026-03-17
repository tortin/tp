package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * A UI component that displays a {@Code Tag} alongside its corresponding frequency in the address book.
 */
public class TagCounts {
    private static final String FXML = "TagCounts.fxml";

    @FXML
    private ListView tagCounts;

    /**
     * Creates a {@code TagCounts} with the given {@code Person} and index to display.
     */
    public TagCounts() {

    }
}
