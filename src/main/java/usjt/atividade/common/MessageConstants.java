package usjt.atividade.common;

public class MessageConstants {
    public static final String USER_CREATED_SUCCESS = "Usuário criado com sucesso.";
    public static final String GENERIC_CREATE_USER_ERROR = "Erro ao criar usuário: ";
    public static final String PASSWORDS_DO_NOT_MATCH = "As senhas não coincidem.";
    public static final String ERROR_EMPTY_FIELDS = "Preencha todos os campos!";
    public static final String ERROR_INVALID_EMAIL = "O e-mail informado é inválido.";
    public static final String ERROR_PASSWORD_LENGTH = "A senha deve ter no mínimo 8 caracteres.";
    public static final String ERROR_PASSWORD_UPPERCASE = "A senha deve conter ao menos uma letra maiúscula.";
    public static final String ERROR_PASSWORD_SPECIAL_CHAR = "A senha deve conter ao menos um caractere especial.";
    public static final String ERROR_EMAIL_ALREADY_EXISTS = "Já existe um usuário com este e-mail.";
    public static final String INTERNAL_ERROR = "Ocorreu um erro interno";
    public static final String INVALID_AUTHENTICATE = "E-mail ou senha inválidos.";
    public static final String RESET_PASSWORD_REQUEST_SUCCESS = "O código de verificação foi enviado para o e-mail ";
    public static final String EMAIL_NOT_FOUND = "O e-mail informado não existe.";
    public static final String ERROR_SEND_EMAIL = "Erro ao enviar e-mail de recuperação para ";
    public static final String SUCCESS_UPDATE_PASSWORD = "Senha alterada com sucesso";
    public static final String PIN_CODE_ERROR = "PIN inválido ou expirado";
    public static final String PIN_CODE_ERROR_LENGHT = "O Pin deve conter apenas 6 dígitos.";
    public static final String EVENTS_REQUESTS_NOT_FOUND = "Não foi encontrada nenhuma solicitação de eventos";

    private void MessageConstants() {
    }
}
