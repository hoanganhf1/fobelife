package vn.com.fobelife.component.user.service;

import lombok.Getter;

@Getter
public class UserImportModel {

    private String username;
    private String email;
    private String password;
    private String status;

    public UserImportModel(String[] properties) {
        this.username = properties[0];
        this.email = properties[1];
        this.password = properties[2];
        this.status = properties[3];
    }
}
