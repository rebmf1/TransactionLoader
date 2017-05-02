package transactionloader.ui;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserQuery {

    private static final String[] BUTTON_VALUES = {"Cancel", "Add new category", "OK"};

    private Set<String> possibleValues;

    public UserQuery(List<String> possibleValues) {

        this.possibleValues = new HashSet<>(possibleValues);
    }

    public String query(String queryText) {

        Object[] values = possibleValues.toArray();
        Arrays.sort(values);

        return JEnhancedOptionPane.showInputDialog
                (queryText, values);
    }


    public static class JEnhancedOptionPane extends JOptionPane {
        public static String showInputDialog(final Object message, final Object[] comboOptions) {
            final JOptionPane pane = new JOptionPane(message, QUESTION_MESSAGE,
                    YES_NO_CANCEL_OPTION, null,
                    BUTTON_VALUES, null);
            pane.setWantsInput(true);
            pane.setComponentOrientation((getRootFrame()).getComponentOrientation());
            pane.setMessageType(QUESTION_MESSAGE);
            pane.setSelectionValues(comboOptions);
            pane.selectInitialValue();
            final String title = "Category required";
            final JDialog dialog = pane.createDialog(null, title);
            dialog.setVisible(true);
            dialog.dispose();
            final Object value = pane.getInputValue();
            if (pane.getValue() == BUTTON_VALUES[1]) {
                return JOptionPane.showInputDialog(message);
            }
            return (value == UNINITIALIZED_VALUE) ? null : (String) value;
        }
    }
}
