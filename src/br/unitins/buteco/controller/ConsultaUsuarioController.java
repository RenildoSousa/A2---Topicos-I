package br.unitins.buteco.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.buteco.application.Util;
import br.unitins.buteco.dao.UsuarioDAO;
import br.unitins.buteco.model.Usuario;

@Named
@ViewScoped
public class ConsultaUsuarioController  implements Serializable {

	private static final long serialVersionUID = -1345986213676035637L;

	private String nome;
	
	private List<Usuario> listaUsuario = null;
	
	public List<Usuario> getListaUsuario(){
		if (listaUsuario == null) {
			UsuarioDAO dao = new UsuarioDAO();
			listaUsuario = dao.findByNome(getNome());
			if (listaUsuario == null)
				listaUsuario = new ArrayList<Usuario>();
			dao.closeConnection();
		}
		
		return listaUsuario;
	}
	
	public void pesquisar() {
		listaUsuario = null;
	}
	
	public void editar(int id) {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.findById(id);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("usuarioFlash", usuario);
		Util.redirect("usuario.xhtml");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

