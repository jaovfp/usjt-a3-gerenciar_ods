package usjt.atividade.common;

public class MessageConstants {
    public static final String USER_CREATED_SUCCESS = "Usuário criado com sucesso.";
    public static final String USER_UPDATED_SUCCESS = "Usuário atualizado com sucesso.";
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
    public static final String USER_NOT_FOUND = "O usuário não foi encontrado";
    public static final String EVENTS_SUBSCRIBES_NOT_FOUND = "Não foi encontrada nenhuma inscrição de usuários.";
    public static final String ODS_TOPICS_NOT_FOUND = "Ocorreu um erro interno e não há tópicos da ODS disponíveis. Tente novamente mais tarde";
    public static final String CEP_NOT_FOUND = "O cep informado não existe.";
    public static final String CREATE_EVENT_REQUEST_SUCCESS = "Evento solicitado com sucesso. Aguarde a aprovação de um administrador";
    public static final String ODS_NOT_FOUND = "A ODS informada não existe.";
    public static final String EXISTS_REQUEST_EVENT_FOR_DAY = "Já existe uma solicitação de evento para o dia: ";
    public static final String ERROR_EVENT_DATE_IN_PAST = "A data do evento não pode ser anterior à data atual.";
    public static final String EVENT_REQUEST_APPROVED_SUCCESS = "O evento foi aprovado com sucesso.";
    public static final String EVENT_REQUEST_CANCELED_SUCCESS = "O evento foi cancelado com sucesso.";
    public static final String EVENT_REQUEST_REJECT_SUCCESS = "O evento foi rejeitado com sucesso.";
    public static final String ONLY_ADMINS_CAN_APPROVE_REJECT = "Apenas administradores podem aprovar ou rejeitar eventos.";
    public static final String ONLY_PENDING_CAN_APPROVE_REJECT = "Somente eventos pendentes podem ser aprovados ou rejeitados.";
    public static final String ONLY_CREATOR_CAN_CANCEL = "Somente o criador da solicitação pode cancelar.";
    public static final String ONLY_PENDING_CAN_CANCEL = "Somente eventos pendentes podem ser cancelados.";
    public static final String STATUS_TRANSITION_NOT_ALLOWED = "Transição de status não permitida.";
    public static final String ODS_REQUIRED = "ODS deve ser informado.";
    public static final String EVENT_NAME_REQUIRED = "Nome do evento deve ser informado.";
    public static final String EVENT_DESCRIPTION_REQUIRED = "Descrição do evento deve ser informada.";
    public static final String EVENT_DATE_INVALID = "Data do evento inválida.";
    public static final String EVENT_ADDRESS_REQUIRED = "Endereço do evento deve ser informado.";
    public static final String EVENT_REQUESTED_BY_REQUIRED = "Solicitante do evento deve ser informado.";
    public static final String USER_NOT_PROFILE_COMPLETE = "Seu cadastro está incompleto. Por favor, atualize suas informações antes de continuar.";
    public static final String SUBSCRIBE_SUCCESS = "Inscrição realizada com sucesso";
    public static final String UNSUBSCRIBE_SUCCESS = "Inscrição cancelada com sucesso";
    public static final String USER_ID_REQUIRED = "ID do usuário é obrigatório.";
    public static final String EVENT_ID_REQUIRED = "ID do evento é obrigatório.";
    public static final String ALREADY_SUBSCRIBED = "Usuário já está inscrito neste evento.";
    public static final String EVENT_NOT_FOUND = "Evento não encontrado.";
    public static final String SUBSCRIBE_ALREADY_DELETED = "A inscrição já foi removida";
    public static final String EVENT_CREATOR_CANNOT_SUBSCRIBE = "O criador do evento não pode se inscrever no próprio evento.";

    private void MessageConstants() {
    }
}
