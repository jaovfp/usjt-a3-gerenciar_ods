package usjt.atividade.infra.Cep;

import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.infra.Cep.Dto.AddressDto;

public class ViaCepService {

    private final ViaCepClient client;

    public ViaCepService() {
        this.client = new ViaCepClient();
    }

    public AddressDto searchAddressByCep(String cep) {
        AddressDto responseViaCep = client.getAddressByCep(cep);
        if (responseViaCep.isErro()) {
            throw new NotFoundException(MessageConstants.CEP_NOT_FOUND);
        }
        return responseViaCep;
    }

}
