package seedu.noknock.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.noknock.model.person.NextOfKin;
import seedu.noknock.model.person.Patient;
import seedu.noknock.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label ic;
    @FXML
    private Label ward;
    @FXML
    private VBox nextOfKins;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        if (person instanceof Patient) {
            Patient patient = (Patient) person;
            ic.setText(patient.getIC().toString());
            ward.setText(patient.getWard().toString());
            patient.getTags()
                .forEach(tag -> tags.getChildren().add(new Label(tag.toString())));

            // Display NOKs as numbered list
            int nokIndex = 1;
            for (NextOfKin nok : patient.getNextOfKinList()) {
                Label nokLabel = new Label(String.format("%d. %s (%s) - %s",
                    nokIndex++,
                    nok.getName().fullName,
                    nok.getRelationship().toString(),
                    nok.getPhone().toString()));
                nokLabel.setWrapText(true);
                nextOfKins.getChildren().add(nokLabel);
            }
        }
    }
}
