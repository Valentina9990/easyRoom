/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.easyRoom.service;

import com.easyRoom.persistence.Usuario;
import com.easyRoom.persistence.UsuarioRepository;
import java.util.List;

/**
 *
 * @author Valentina Sarmiento
 */
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
    }

    public void saveUsuario(Usuario usuario) {
        usuarioRepository.saveUsuario(usuario);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAllUsuarios();
    }
}
