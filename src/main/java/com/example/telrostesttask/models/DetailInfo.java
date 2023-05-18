package com.example.telrostesttask.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DetailInfo implements BuildOnUser, UserBuilder{

    private String name;

    private String patronymic;

    private String surname;

    private String bornDate;


    public void buildFromUser(User user){
        this.name = user.getName();
        this.patronymic = user.getPatronymic();
        this.surname = user.getSurname();
        this.bornDate = user.getBornDate();
    }
    public User getUserFrom(User user){
        user.setName(this.name);
        user.setPatronymic(this.patronymic);
        user.setSurname(this.surname);
        user.setBornDate(this.bornDate);
        return user;
    }

}
