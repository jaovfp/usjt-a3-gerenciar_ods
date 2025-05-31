package usjt.atividade.views.User.Events;

import usjt.atividade.domain.model.User.User;
import usjt.atividade.views.AbstractPanel;
import usjt.atividade.views.utils.UIStyle;

public class RegisteredEventsPanel extends AbstractPanel {

    private final User user;

    public RegisteredEventsPanel(User user) {
        super(UIStyle.BG_USER_ADMIN_COLOR, UIStyle.CONTENT_TOPIC_USER_ADMIN_DIMENSION);
        this.user = user;
        initComponents();
        layoutComponents();
        addListeners();
    }

    @Override
    public void initComponents(){

    }

    @Override
    public void layoutComponents(){

    }

    @Override
    public void addListeners(){

    }

}
