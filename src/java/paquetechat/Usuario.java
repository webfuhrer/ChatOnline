/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetechat;

/**
 *
 * @author luis
 */
class Usuario {
    private String nombre, password, avatar;

    public Usuario(String nombre, String password, String avatar) {
        this.nombre = nombre;
        this.password = password;
        this.avatar = avatar;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatar() {
        return avatar;
    }
    
}
