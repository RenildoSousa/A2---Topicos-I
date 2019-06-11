package br.unitins.buteco.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.buteco.application.Session;
import br.unitins.buteco.application.Util;
import br.unitins.buteco.model.Usuario;

@Named
@ViewScoped
public class MenuController implements Serializable {
	
	private static final long serialVersionUID = 2346073357079884165L;
	Usuario usuarioLogado = null;
	
	public MenuController() {
		usuarioLogado = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void telaLogin() {
		Util.redirect("login.xhtml");
	}
	public void telaUsuario() {
		Util.redirect("usuario.xhtml");
	}
	public void telaMenu() {
		Util.redirect("menu.xhtml");
	}
	public void telaConsultaUsuario() {
		Util.redirect("consultausuario.xhtml");
	}
	public void telaBebida() {
		Util.redirect("bebida.xhtml");
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
}

