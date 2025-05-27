package usjt.atividade.common;

public class MessageConstants {
    public static final String USER_CREATED_SUCCESS = "Usuário criado com sucesso.";
    public static final String GENERIC_CREATE_USER_ERROR = "Erro ao criar usuário: ";
    public static final String GENERIC_AUTHENTICATE_ERROR = "Erro ao autenticar: ";
    public static final String PASSWORDS_DO_NOT_MATCH = "As senhas não coincidem.";
    public static final String ERROR_EMPTY_FIELDS = "Preencha todos os campos!";
    public static final String ERROR_INVALID_EMAIL = "O e-mail informado é inválido.";
    public static final String ERROR_PASSWORD_LENGTH = "A senha deve ter no mínimo 8 caracteres.";
    public static final String ERROR_PASSWORD_UPPERCASE = "A senha deve conter ao menos uma letra maiúscula.";
    public static final String ERROR_PASSWORD_SPECIAL_CHAR = "A senha deve conter ao menos um caractere especial.";
    public static final String ERROR_EMAIL_ALREADY_EXISTS = "Já existe um usuário com este e-mail.";
    public static final String INTERNAL_ERROR = "Ocorreu um erro interno";
    public static final String INVALID_AUTHENTICATE = "E-mail ou senha inválidos.";

    private void MessageConstants() {
    }
}
