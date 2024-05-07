/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.serviciosREST.Usuarios.servicio;

import com.serviciosREST.Usuarios.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class Servicio {

    private List<Usuario> userList;

    public Servicio() {
        // Inicalizacion de la lista de "userList" con ejemplos de usuarios
        userList = new ArrayList<>();

        Usuario usuario1 = new Usuario(1, "Juan", 32, "Juan@mail.com");
        Usuario usuario2 = new Usuario(2, "Lorena", 26, "Lorena@mail.com");
        Usuario usuario3 = new Usuario(3, "Pablo", 45, "Pablo@mail.com");
        Usuario usuario4 = new Usuario(4, "Laura", 32, "Laura@mail.com");
        Usuario usuario5 = new Usuario(5, "Jorge", 59, "Jorge@mail.com");

        // Se añaden los usuarios de ejemplo
        userList.addAll(Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5));
    }

    /*
    Devuelve la lista completa de los usuarios que se instanciaron
     */
    public List<Usuario> getAllUsers() {
        return userList;
    }

    /*
     Agregar un nuevo usuario a la lista 
     */
    public void addUser(Usuario user) {
        userList.add(user);
    }

    /*
     Busca y devuelve un usuario por el ID que se le instancia
     Adicional se agrega una clase de Java llamada Optional con el fin de evitar errores al momento de generar un objeto nulo
     debido a que se esta buscando obtener un usuario con todas las caractersiticas
     */
    public Optional<Usuario> getUser(Integer id) {
        Optional<Usuario> opcional = Optional.empty();
        for (Usuario usuario : userList) {
            if (id == usuario.getId()) {
                opcional = Optional.of(usuario);
                return opcional;
            }
        }
        return opcional;
    }

    /*
      Actualiza los datos de un usuario existente por si ID
     */
    public void editUser(Integer id, Usuario userActualizado) {
        // Primero, verificamos si el ID existe
        Optional<Usuario> usuarioExistente = getUser(id);
        if (usuarioExistente.isPresent()) {
            // Si el ID existe, actualizamos los atributos del usuario
            Usuario usuario = usuarioExistente.get();
            usuario.setNombre(userActualizado.getNombre());
            usuario.setEdad(userActualizado.getEdad());
            usuario.setEmail(userActualizado.getEmail());
        } else {
            // Si el ID no existe, lanzamos una excepción o manejamos el error de alguna otra manera
            throw new IllegalArgumentException("El ID " + id + " no existe");
        }
    }
/*
    Elimiza un usuario de la lista por medio de su ID
    */
    public void deleteUser(Integer id) {
        Optional<Usuario> usuarioOpcional = getUser(id);
        if (usuarioOpcional.isPresent()) {
            Usuario usuario = usuarioOpcional.get();
            userList.remove(usuario);
        } else {
            // si el ID no existe, se lanza una excepcion para saber que no existe un usuario por eliminar
            throw new IllegalArgumentException("El ID " + id + " no existe");
        }
    }

}
