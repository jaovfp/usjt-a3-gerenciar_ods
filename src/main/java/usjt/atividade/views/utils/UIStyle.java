package usjt.atividade.views.utils;

import usjt.atividade.domain.valueObjects.EventRequestStatus;

import java.awt.*;

import static java.util.Objects.isNull;

public class UIStyle {

    public static final Color BG_AUTH_COLOR = new Color(0, 33, 66);
    public static final Dimension AUTH_DIMENSION = new Dimension(500, 700);
    public static final Color PLACEHOLDER_COLOR = Color.GRAY;
    public static final Color AUTH_TEXT_COLOR = Color.WHITE;
    public static final Color AUTH_ACCENT_COLOR = new Color(0, 140, 160);
    public static final Font AUTH_TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    public static final Font AUTH_TEXT_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font AUTH_BTN_FONT = new Font("Arial", Font.BOLD, 18);
    public static final String FORGOT_PASSWORD_TEXT =  "<html><div style='width: 300px;'>Por favor, digite o endereço de e-mail vinculado à sua conta.<br> Enviaremos um código para que você possa redefinir sua senha.</div></html>";
    public static final Color BG_USER_ADMIN_COLOR = new Color(247, 247, 247);
    public static final Font USER_ADMIN_TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    public static final Color BG_SIDE_MENU_USER_COLOR = new Color(0, 66, 99);
    public static final Color BG_SIDE_MENU_ADMIN_COLOR = new Color(0, 96, 12);
    public static final Color SIDE_MENU_TEXT_COLOR = Color.WHITE;
    public static final Font SIDE_MENU_TITLE_FONT = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font SIDE_MENU_TEXT_FONT = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font SIDE_MENU_BTN_TEXT_FONT = new Font("Segoe UI", Font.BOLD, 12);
    public static final Color TRANSPARENT_COLOR = new Color(0,0,0, 0);
    public static final Dimension USER_ADMIN_CONTENT_DIMENSION = new Dimension(1100, 1000);
    public static final Dimension SIDE_MENU_DIMENSION = new Dimension(250, 800);
    public static final Dimension USER_ADMIN_DIMENSION = new Dimension(1300, 800);
    public static final Dimension CONTENT_USER_ADMIN_DIMENSION = new Dimension(1100, 800);
    public static final Dimension CONTENT_TOPIC_USER_ADMIN_DIMENSION = new Dimension(1100, 620);
    public static final Dimension USER_ADMIN_PAGINATION_DIMENSION = new Dimension(1100, 50);
    public static final Font CONTENT_USER_ADMIN_TEXT_FONT = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font USER_CONTENT_BTN_FONT = new Font("Arial", Font.BOLD, 18);
    public static final Font HEADER_FONT =  new Font("SegoeUI", Font.BOLD, 14);
    public static final Font ROW_BTN_FONT =  new Font("SegoeUI", Font.BOLD, 12);

    public static Color defineEventRequestStatusColorByStatus(EventRequestStatus status) {
        if (isNull(status)) {
            return Color.GRAY;
        }
        switch (status) {
            case PENDING:
                return UIStyle.BG_SIDE_MENU_USER_COLOR.brighter().brighter();
            case APPROVED:
                return Color.green.darker();
            case REJECTED:
                return Color.red.darker();
            case CANCELED:
                return Color.gray;
            default:
                return Color.GRAY;
        }
    }
}
