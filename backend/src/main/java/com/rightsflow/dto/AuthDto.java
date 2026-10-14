package com.rightsflow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public class AuthDto {

    public static class LoginRequest {
        @NotBlank(message = "Email address is required")
        @Email(message = "Invalid email format")
        private String email;

        @NotBlank(message = "Password is required")
        private String password;

        private Boolean rememberMe;
        private String expectedRole;

        public LoginRequest() {}
        public LoginRequest(String email, String password, Boolean rememberMe, String expectedRole) {
            this.email = email;
            this.password = password;
            this.rememberMe = rememberMe;
            this.expectedRole = expectedRole;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String email;
            private String password;
            private Boolean rememberMe = false;
            private String expectedRole;

            public Builder email(String email) { this.email = email; return this; }
            public Builder password(String password) { this.password = password; return this; }
            public Builder rememberMe(Boolean rememberMe) { this.rememberMe = rememberMe; return this; }
            public Builder expectedRole(String expectedRole) { this.expectedRole = expectedRole; return this; }
            public LoginRequest build() {
                return new LoginRequest(email, password, rememberMe, expectedRole);
            }
        }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public Boolean getRememberMe() { return rememberMe; }
        public void setRememberMe(Boolean rememberMe) { this.rememberMe = rememberMe; }
        public String getExpectedRole() { return expectedRole; }
        public void setExpectedRole(String expectedRole) { this.expectedRole = expectedRole; }
    }

    public static class LoginResponse {
        private String token;
        private String type = "Bearer";
        private Long id;
        private String fullName;
        private String email;
        private String phone;
        private List<String> roles;
        private String preferredLanguage;
        private String stateLocation;

        public LoginResponse() {}
        public LoginResponse(String token, String type, Long id, String fullName, String email, String phone, List<String> roles, String preferredLanguage, String stateLocation) {
            this.token = token;
            this.type = type != null ? type : "Bearer";
            this.id = id;
            this.fullName = fullName;
            this.email = email;
            this.phone = phone;
            this.roles = roles;
            this.preferredLanguage = preferredLanguage;
            this.stateLocation = stateLocation;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String token;
            private String type = "Bearer";
            private Long id;
            private String fullName;
            private String email;
            private String phone;
            private List<String> roles;
            private String preferredLanguage;
            private String stateLocation;

            public Builder token(String token) { this.token = token; return this; }
            public Builder type(String type) { this.type = type; return this; }
            public Builder id(Long id) { this.id = id; return this; }
            public Builder fullName(String fullName) { this.fullName = fullName; return this; }
            public Builder email(String email) { this.email = email; return this; }
            public Builder phone(String phone) { this.phone = phone; return this; }
            public Builder roles(List<String> roles) { this.roles = roles; return this; }
            public Builder preferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; return this; }
            public Builder stateLocation(String stateLocation) { this.stateLocation = stateLocation; return this; }
            public LoginResponse build() {
                return new LoginResponse(token, type, id, fullName, email, phone, roles, preferredLanguage, stateLocation);
            }
        }

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public List<String> getRoles() { return roles; }
        public void setRoles(List<String> roles) { this.roles = roles; }
        public String getPreferredLanguage() { return preferredLanguage; }
        public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
        public String getStateLocation() { return stateLocation; }
        public void setStateLocation(String stateLocation) { this.stateLocation = stateLocation; }
    }

    public static class RegisterRequest {
        @NotBlank(message = "Full name is required")
        @Size(min = 2, max = 150, message = "Name must be between 2 and 150 characters")
        private String fullName;

        @NotBlank(message = "Email address is required")
        @Email(message = "Invalid email format")
        private String email;

        private String phone;

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 100, message = "Password must be at least 6 characters")
        private String password;

        private String preferredLanguage = "en";
        private String stateLocation;
        private List<String> legalInterests;

        public RegisterRequest() {}
        public RegisterRequest(String fullName, String email, String phone, String password, String preferredLanguage, String stateLocation, List<String> legalInterests) {
            this.fullName = fullName;
            this.email = email;
            this.phone = phone;
            this.password = password;
            this.preferredLanguage = preferredLanguage;
            this.stateLocation = stateLocation;
            this.legalInterests = legalInterests;
        }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String fullName;
            private String email;
            private String phone;
            private String password;
            private String preferredLanguage = "en";
            private String stateLocation;
            private List<String> legalInterests;

            public Builder fullName(String fullName) { this.fullName = fullName; return this; }
            public Builder email(String email) { this.email = email; return this; }
            public Builder phone(String phone) { this.phone = phone; return this; }
            public Builder password(String password) { this.password = password; return this; }
            public Builder preferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; return this; }
            public Builder stateLocation(String stateLocation) { this.stateLocation = stateLocation; return this; }
            public Builder legalInterests(List<String> legalInterests) { this.legalInterests = legalInterests; return this; }
            public RegisterRequest build() {
                return new RegisterRequest(fullName, email, phone, password, preferredLanguage, stateLocation, legalInterests);
            }
        }

        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getPreferredLanguage() { return preferredLanguage; }
        public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
        public String getStateLocation() { return stateLocation; }
        public void setStateLocation(String stateLocation) { this.stateLocation = stateLocation; }
        public List<String> getLegalInterests() { return legalInterests; }
        public void setLegalInterests(List<String> legalInterests) { this.legalInterests = legalInterests; }
    }

    public static class PasswordUpdateRequest {
        @NotBlank(message = "Current password is required")
        private String currentPassword;

        @NotBlank(message = "New password is required")
        @Size(min = 6, max = 100, message = "Password must be at least 6 characters")
        private String newPassword;

        public PasswordUpdateRequest() {}
        public PasswordUpdateRequest(String currentPassword, String newPassword) {
            this.currentPassword = currentPassword;
            this.newPassword = newPassword;
        }

        public String getCurrentPassword() { return currentPassword; }
        public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }
        public String getNewPassword() { return newPassword; }
        public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
    }
}
