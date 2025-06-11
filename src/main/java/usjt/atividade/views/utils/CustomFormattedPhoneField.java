package usjt.atividade.views.utils;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class CustomFormattedPhoneField extends AbstractCustomFormattedTextField{

    public CustomFormattedPhoneField() {
        super.setFormatterFactory(new DefaultFormatterFactory(createFormatter()));
    }

    private MaskFormatter createFormatter() {
        try {
            MaskFormatter formatter = new MaskFormatter("+55 (##) 9####-####");
            formatter.setPlaceholderCharacter('_');
            formatter.setAllowsInvalid(false);
            return formatter;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected boolean shouldShowPlaceholder() {
        return getText().trim().equals("+55 (__) 9____-____");
    }

}
