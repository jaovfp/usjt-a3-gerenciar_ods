package usjt.atividade.infra.Cep;

import com.google.gson.Gson;
import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.infra.Cep.Dto.AddressDto;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViaCepClient {

    private static final String BASE_URL = "https://viacep.com.br/ws/";

    public AddressDto getAddressByCep(String cep) {
        try {
            URL url = new URL(BASE_URL + cep + "/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
                return new Gson().fromJson(reader, AddressDto.class);
            }

        } catch (Exception e) {
            System.err.println(e);
            throw new RuntimeException("Erro ao consultar CEP: " + cep, e);
        }
    }

}
