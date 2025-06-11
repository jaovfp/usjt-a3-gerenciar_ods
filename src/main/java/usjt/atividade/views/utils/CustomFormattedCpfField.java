package usjt.atividade.views.utils;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class CustomFormattedCpfField extends AbstractCustomFormattedTextField{

    public CustomFormattedCpfField() {
        super.setFormatterFactory(new DefaultFormatterFactory(createFormatter()));
    }

    private MaskFormatter createFormatter() {
        try {
            // Formato: 000.000.000-00
            MaskFormatter formatter = new MaskFormatter("###.###.###-##");
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
        return getText().trim().equals("___.___.___-__");
    }

}
