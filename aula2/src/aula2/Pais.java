package aula2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Pais {

	private int id;
	private String nome;
	private long populacao;
	private double area;
	private double valor;
	private ArrayList<Pais> lista;

	public Pais() {

	}

	public Pais(int id, String nome, long populacao, double area) {
		this.id = id;
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	// listar paises

	public void incluir(Connection conn) {
		String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES (?, ?,?)";

		PreparedStatement stm = null;
		try {

			stm = conn.prepareStatement(sqlInsert);
			stm.setString(2, getNome());
			stm.setLong(3, getPopulacao());
			stm.setDouble(4, getArea());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
	}

	public void excluir(Connection conn) {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sqlDelete);
			stm.setInt(1, getId());

			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
	}

	public void atualizar(Connection conn) {
		String sqlUpdate = "UPDATE pais SET Valor = ? WHERE id = ?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sqlUpdate);
			stm.setDouble(1, getValor());
			stm.setInt(2, getId());

			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
	}

	public void carregar(Connection conn) {
		String sqlSelect = "SELECT valor FROM pais WHERE id = ?";

		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sqlSelect);
			stm.setInt(1, getId());
			rs = stm.executeQuery();

			if (rs.next()) {
				this.setValor(rs.getDouble(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e1) {
					System.out.print(e1.getStackTrace());
				}
			}
		}
	}

	// maior populacao
	public Pais maiorPopulacao(Connection conn) {
		String sqlSelect = "SELECT * FROM pais where populacao IN (SELECT MAX(populacao) FROM pais)";

		try {
			PreparedStatement stm = conn.prepareStatement(sqlSelect);

			ResultSet rs = stm.executeQuery();

			Pais pais = new Pais();

			if (rs.next()) {

				pais.setNome(rs.getString("nome"));
				pais.setPopulacao(rs.getLong("populacao"));
				pais.setArea(rs.getDouble("area"));

			}

			return pais;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// menor populacao
	public Pais menorPopulacao(Connection conn) {
		String sqlSelect = "SELECT * FROM pais where area IN (SELECT MIN(area) FROM pais)";

		try {
			PreparedStatement stm = conn.prepareStatement(sqlSelect);

			ResultSet rs = stm.executeQuery();

			Pais pais = new Pais();

			if (rs.next()) {

				pais.setNome(rs.getString("nome"));
				pais.setPopulacao(rs.getLong("populacao"));
				pais.setArea(rs.getDouble("area"));

			}

			return pais;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	// vetor
	public ArrayList<Pais> vetor(Connection conn) {
		String sqlSelect = "SELECT * FROM pais LIMIT 3";

		try {
			PreparedStatement stm = conn.prepareStatement(sqlSelect);

			ResultSet rs = stm.executeQuery();

			ArrayList<Pais> vetor = new ArrayList<Pais>();

			while (rs.next()) {

				Pais pais = new Pais();

				pais.setNome(rs.getString("nome"));
				pais.setPopulacao(rs.getLong("populacao"));
				pais.setArea(rs.getDouble("area"));

				vetor.add(pais);

			}

			return vetor;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Nome: " + this.getNome() + " Populacao: " + this.getPopulacao() + " Area: " + this.getArea();
	}

}
