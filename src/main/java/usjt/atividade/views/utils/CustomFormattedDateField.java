package usjt.atividade.views.utils;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class CustomFormattedDateField extends AbstractCustomFormattedTextField {

    public CustomFormattedDateField() {
        super.setFormatterFactory(new DefaultFormatterFactory(createFormatter()));
    }

    private MaskFormatter createFormatter() {
        try {
            MaskFormatter formatter = new MaskFormatter("##/##/####");
            formatter.setPlaceholderCharacter('_');
            formatter.setAllowsInvalid(false);
            return formatter;
        } catch (ParseException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    protected boolean shouldShowPlaceholder() {
        return getText().trim().equals("__/__/____");
    }

}
