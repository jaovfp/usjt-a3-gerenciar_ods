package usjt.atividade.domain.model.User;

import java.time.LocalDateTime;

public class User {

    private int userId;
    private String username;
    private String email;
    private String passwordHash;
    private UserType type;
    private boolean isActive;
    private String profilePhotoUrl;
    private LocalDateTime createDate;
    private LocalDateTime changeDate;

    public User(int userId, String username, String email, String passwordHash, UserType type,
                boolean isActive, String profilePhotoUrl, LocalDateTime createDate, LocalDateTime changeDate) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.type = type;
        this.isActive = isActive;
        this.profilePhotoUrl = profilePhotoUrl;
        this.createDate = createDate;
        this.changeDate = changeDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }
}
