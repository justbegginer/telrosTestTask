package com.example.telrostesttask.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ContactInfo implements BuildOnUser, UserBuilder{
    private String email;
    private String telephoneNumber;
    @Override
    public void buildFromUser(User user){
        this.email = user.getEmail();
        this.telephoneNumber = user.getTelephoneNumber();
    }
    @Override
    public User getUserFrom(User user){
        user.setEmail(this.email);
        user.setTelephoneNumber(this.telephoneNumber);
        return user;
    }
}
