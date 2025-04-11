package usjt.atividade.views.authentication;

import usjt.atividade.views.AbstractView;
import usjt.atividade.views.authentication.utils.UIStyle;

public abstract class AuthView extends AbstractView {

    public AuthView(String title) {
        super(title);
        setSize(400, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(UIStyle.BACKGROUND_COLOR);
    }
}
