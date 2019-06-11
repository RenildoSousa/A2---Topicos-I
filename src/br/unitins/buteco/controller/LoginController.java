package br.unitins.buteco.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.buteco.application.Util;
import br.unitins.buteco.dao.UsuarioDAO;
import br.unitins.buteco.model.Usuario;

@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;
	
	public void entrar() {
		UsuarioDAO dao = new UsuarioDAO();
		// gerando o hash da senha informada na tela de login
		String senhaEncriptada = Util.encrypt(getUsuario().getSenha());
		
		// comparando os dados da tela de login com o banco de dados
		if (dao.findUsuario(getUsuario().getLogin(), senhaEncriptada) != null) {
			// login valido
			Util.redirect("menu.xhtml");
		} else 
			Util.addMessageError("Usuário ou senha inválido.");
		
	}
	
	
	public void limpar() {
		setUsuario(null);
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

