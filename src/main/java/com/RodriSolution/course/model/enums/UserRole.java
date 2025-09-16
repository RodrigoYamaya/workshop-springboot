package com.RodriSolution.course.model.enums;
//continua aki criando as roles admin e client e apos criar private string role
// e apos criar construtor e um getter;
//apos isso remos mecher na classe entity para acrescentar enumRole;
//apost fazer as alteração no entity, service , dtos, mapper preencher role "admin/client etc;
// iremos mexer na entity  user para implements UserDetails nessa user;
//apos iremos criar as altorizações das roles admin/client;
//apos iremos gerar as credencias das validações na propria "USER";
//APOS iremos criar userDetailsService, jwrUtil, jwtAuthFilter;
//final iremos criar endPoints da Altenticação e valildação token para finalizar;
public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }





}
