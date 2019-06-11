package br.unitins.buteco.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.buteco.model.Bebida;
import br.unitins.buteco.model.Categoria;
import br.unitins.buteco.model.Marca;
import br.unitins.buteco.application.Util;

public class BebidasDAO extends DAO<Bebida> {

	@Override
	public boolean create(Bebida obj) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement("INSERT INTO bebidas ( " + "  nome, " + "  categoria, "
					+ "  marca ) " + "VALUES ( " + " ?, " + " ?, " + " ? ) ");
			stat.setString(1, obj.getNome());
			stat.setInt(2, obj.getCategoria().getValue());
			stat.setInt(3, obj.getMarca().getValue());

			stat.execute();
			Util.addMessageError("Cadastro realizado com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao incluir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public boolean update(Bebida obj) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement(
					"UPDATE bebidas SET " + "  nome = ?, " + "  categoria = ?, " + "  marca = ?  " + "WHERE id = ? ");
			stat.setString(1, obj.getNome());
			stat.setInt(2, obj.getCategoria().getValue());
			stat.setInt(3, obj.getMarca().getValue());
			stat.setInt(4, obj.getId());

			stat.execute();
			Util.addMessageError("Alteração realizada com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao Alterar.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public boolean delete(int id) {
		boolean resultado = false;

		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}

		PreparedStatement stat = null;
		try {
			stat = getConnection().prepareStatement("DELETE FROM bebidas WHERE id = ? ");
			stat.setInt(1, id);

			stat.execute();
			Util.addMessageError("Exclusão realizada com sucesso!");
			resultado = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao Excluir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

	@Override
	public Bebida findById(int id) {
		// verificando se tem uma conexao valida
				if (getConnection() == null) {
					Util.addMessageError("Falha ao conectar ao Banco de Dados.");
					return null;
				}
				Bebida bebida = null;
				
				PreparedStatement stat = null;
				
				try {
					stat = getConnection().prepareStatement("SELECT * FROM bebidas WHERE id = ?");
					stat.setInt(1, id);
					
					ResultSet rs = stat.executeQuery();
					if(rs.next()) {
						bebida = new Bebida();
						bebida.setId(rs.getInt("id"));
						bebida.setNome(rs.getString("nome"));
						bebida.setCategoria(Categoria.valueOf(rs.getInt("categoria")));
						bebida.setMarca(Marca.valueOf(rs.getInt("marca")));
					}
				} catch (SQLException e) {
					e.printStackTrace();
					Util.addMessageError("Falha ao consultar o Banco de Dados.");
				} finally {
					try {
						stat.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return bebida;
	}

	@Override
	public List<Bebida> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Bebida> listaBebida = new ArrayList<Bebida>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM bebidas");
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				Bebida b = new Bebida();
				b.setId(rs.getInt("id"));
				b.setNome(rs.getString("nome"));
				b.setCategoria(Categoria.valueOf(rs.getInt("categoria")));
				b.setMarca(Marca.valueOf(rs.getInt("marca")));

				listaBebida.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaBebida = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaBebida;
	}

}
