package com.geniessoft.backend.oauth2Security.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentUserInfo {

    private String firstName;
    private String lastName;
    private String email;
    private long id;
    private String[] roles;
    private String avatar;
}
