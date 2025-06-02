package usjt.atividade.domain.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class User {

    private UUID userId;
    private String fullname;
    private String email;
    private String passwordHash;
    private LocalDate birthDate;
    private String cpf;
    private String phoneNumber;
    private String addressLine;
    private String city;
    private String state;
    private String postalCode;
    private UserType type;
    private boolean isActive;
    private String profilePhotoUrl;
    private LocalDateTime createDate;
    private LocalDateTime changeDate;

    public User(UUID userId, String fullname, String email, String passwordHash,
                UserType type, boolean isActive, LocalDate birthDate,
                String cpf, String phoneNumber, String addressLine,
                String city, String state, String postalCode,
                String profilePhotoUrl, LocalDateTime createDate, LocalDateTime changeDate) {
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.type = type;
        this.isActive = isActive;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.profilePhotoUrl = profilePhotoUrl;
        this.createDate = createDate;
        this.changeDate = changeDate;
    }

    public void setEmail(String email) {
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

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public UserType getType() {
        return type;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }
}
