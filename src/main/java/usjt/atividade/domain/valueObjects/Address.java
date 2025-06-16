package usjt.atividade.domain.valueObjects;

import static usjt.atividade.common.utils.ValidatorUtils.isStringNullOrEmpty;

public class Address {

    private final String addressLine;
    private final String city;
    private final String state;
    private final String postalCode;

    public Address(String addressLine, String city, String state, String postalCode) {
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String toString() {
        return addressLine + ", " + city + ", " + state + " - " + postalCode;
    }

    public boolean isBlank() {
        return isStringNullOrEmpty(addressLine) || isStringNullOrEmpty(city) || isStringNullOrEmpty(state) || isStringNullOrEmpty(postalCode);
    }
}
