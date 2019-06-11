package br.unitins.buteco.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.buteco.dao.BebidasDAO;
import br.unitins.buteco.model.Bebida;
import br.unitins.buteco.model.Categoria;
import br.unitins.buteco.model.Marca;

@Named
@ViewScoped
public class BebidaController implements Serializable{
	
	private static final long serialVersionUID = -4723320668531860883L;
	
	private Bebida bebida;
	private List<Bebida> listaBebida = null;
	
	public Bebida getBebida() {
		if (bebida == null) {
			bebida = new Bebida();
		}
		return bebida;
	}
	
	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}
	
	public List<Bebida> getListaBebida() {
		if (listaBebida == null) {
			BebidasDAO dao = new BebidasDAO();
			listaBebida = dao.findAll();
			if (listaBebida == null)
				listaBebida= new ArrayList<Bebida>();
			dao.closeConnection();
		}
		
		return listaBebida;
	}
	
	public void setListaBebida(List<Bebida> listaBebida) {
		this.listaBebida = listaBebida;
	}
	public Marca[] getListaMarca() {
		return Marca.values();
	}
	
	public Categoria[] getListaCategoria() {
		return Categoria.values();
	}
	
	public void limpar() {
		bebida = null;
	}
	
	
	public void editar(int id) {
		BebidasDAO dao = new BebidasDAO();
		setBebida(dao.findById(id));
	}
	
	
	public void incluir() {
		BebidasDAO dao = new BebidasDAO();

		if (dao.create(getBebida())) {
			limpar();
			// para atualizar o data table
			listaBebida = null;
		}
		dao.closeConnection();
	}
	
	public void alterar() {
		BebidasDAO dao = new BebidasDAO();
		if (dao.update(getBebida())) {
			limpar();
			// para atualizar o data table
			listaBebida = null;
		}
		dao.closeConnection();
	}
	
	public void excluir() {
		BebidasDAO dao = new BebidasDAO();
		if (dao.delete(getBebida().getId())) {
			limpar();
			// para atualizar o data table
			listaBebida = null;
		}
		dao.closeConnection();
	}
	

}
