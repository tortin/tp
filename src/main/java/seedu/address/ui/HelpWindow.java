package seedu.address.ui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://se-education.org/addressbook-level3/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    // Path to UserGuide.md relative to project root
    private static final String USERGUIDE_PATH = "docs/Userguide.md";

    // Heading line from Userguide.md to start from
    private static final String START_HEADING = "## Features";

    // The heading line to stop reading at (exclusive), or null to read to end of file
    private static final String END_HEADING = null;


    @FXML
    private Button copyButton;

    @FXML
    private TextArea helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(loadUserGuide());
        helpMessage.setWrapText(true);
        helpMessage.setEditable(false);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Reads UserGuide.md from the filesystem path USERGUIDE_PATH,
     * extracts the section from START_HEADING to END_HEADING (or EOF),
     * and strips Markdown formatting to return plain text.
     */

    /** Reads UserGuide.md from the relative path USERGUIDE_PATH,
     * runs extractUserGuide to extract UserGuide from START_HEADING to END_HEADING
     * @return
     */

    /*
    private String loadUserGuide() {
        try {
            InputStream is = new FileInputStream(USERGUIDE_PATH);

            List<String> lines = new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .collect(Collectors.toCollection(ArrayList::new));

            // Find the start line index
            int start = 0;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith(START_HEADING)) {
                    start = i;
                    break;
                }
            }

            // Find the end line index, or use end of file
            int end = lines.size();
            if (END_HEADING != null) {
                for (int i = start + 1; i < lines.size(); i++) {
                    if (lines.get(i).startsWith(END_HEADING)) {
                        end = i;
                        break;
                    }
                }
            }

            return lines.subList(start, end)
                    .stream()
                    .collect(Collectors.joining("\n"));

        } catch (Exception e) {
            logger.warning("Failed to load UserGuide.md from path '"
                    + USERGUIDE_PATH + "': " + e.getMessage());
            return "Could not load user guide.\nVisit: " + USERGUIDE_URL;
        }
    }
    */


    private String loadUserGuide() {
        try {

            FileInputStream userGuideInput = new FileInputStream(USERGUIDE_PATH);
            InputStreamReader inputStreamReader = new InputStreamReader(userGuideInput);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            try {
                return extractUserGuide(reader, START_HEADING, END_HEADING);
            } finally {
                reader.close();
            }

        } catch (Exception e) {
            logger.warning("Failed to load UserGuide.md from path " + USERGUIDE_PATH +
                    e.getMessage());
            return "Failed to load local user-guide. \n Visit: " + USERGUIDE_URL +
                    "instead";
        }

    }

    // ExtractUserGuide is a helper method used to contain the file reading logic for easier testing
    private String extractUserGuide(BufferedReader reader, String startString, String endString) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        String line = reader.readLine();

        // Consumes all lines in the UserGuide
        while (line != null) {
            lines.add(line);
            line = reader.readLine();
        }

        // Checks for a match to the start heading or string to determine the i-th line to start at
        int startingLine = -1; // Forces an exception if the startString cannot be found
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith(startString)) {
                startingLine = i;
                break;
            }
        }
        if (startingLine == -1) {
            throw new IOException("startString not found: " + startString);
        }

        // Checks for a match to the end string to determine where to stop, otherwise just reach end of file
        int endingLine = lines.size();
        for (int i = startingLine + 1; endString != null && i < lines.size(); i++) {
            if (!lines.get(i).startsWith(endString)) {
                continue;
            }
            endingLine = i;
            break;
        }

        // Takes the ArrayList of lines starting from startingLine, builds them into a string for
        // loadUserGuide to pass to HelpWindow

        StringBuilder result = new StringBuilder();
        for (int i = startingLine; i< endingLine; i++) {
            result.append(lines.get(i));
            result.append("\n");
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length()-1);
        }

        return result.toString();

    }




    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
