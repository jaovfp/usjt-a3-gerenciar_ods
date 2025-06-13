package usjt.atividade.domain.entities;

import usjt.atividade.domain.valueObjects.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class User {

    private UUID userId;
    private String fullname;
    private Email email;
    private String passwordHash;
    private LocalDate birthDate;
    private CPF cpf;
    private PhoneNumber phoneNumber;
    private Address address;
    private UserType type;
    private boolean isActive;
    private String profilePhotoUrl;
    private LocalDateTime createDate;
    private LocalDateTime changeDate;
    private boolean isProfileComplete;

    public User(){}

    public User(UUID userId, String fullname, Email email, String passwordHash,
                UserType type, boolean isActive, LocalDate birthDate,
                String cpf, String phoneNumber, String addressLine, String city, String state, String postalCode,
                String profilePhotoUrl, LocalDateTime createDate, LocalDateTime changeDate) {
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.type = type;
        this.isActive = isActive;
        this.birthDate = birthDate;
        setCpf(cpf);
        setAddress(addressLine, city, state, postalCode);
        setPhoneNumber(phoneNumber);
        this.profilePhotoUrl = profilePhotoUrl;
        this.createDate = createDate;
        this.changeDate = changeDate;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isActive() {
        return isActive;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getFullname() {
        return fullname;
    }

    public Email getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public UserType getType() {
        return type;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCpfValue() {
        return cpf != null ? cpf.getValue() : null;
    }

    public String getPhoneNumberValue() {
        return phoneNumber != null ? phoneNumber.getValue() : null;
    }

    public String getAddressLine() {
        return address != null ? address.getAddressLine() : null;
    }

    public String getCity() {
        return address != null ? address.getCity() : null;
    }

    public String getState() {
        return address != null ? address.getState() : null;
    }

    public String getPostalCode() {
        return address != null ? address.getPostalCode() : null;
    }

    public void setCpf(String cpf) {
        this.cpf = (cpf != null && !cpf.isBlank()) ? new CPF(cpf) : null;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = (phoneNumber != null && !phoneNumber.isBlank()) ? new PhoneNumber(phoneNumber) : null;
    }

    public void setAddress(String addressLine, String city, String state, String postalCode) {
        if ((addressLine == null || addressLine.isBlank()) &&
                (city == null || city.isBlank()) &&
                (state == null || state.isBlank()) &&
                (postalCode == null || postalCode.isBlank())) {
            this.address = null;
        } else {
            this.address = new Address(addressLine, city, state, postalCode);
        }
    }
}
