package aula2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoBD {
	
	private int id;
	private String nome;
	private long populacao;
	private double area;
	private double valor;
	
	static {
	      try {
	         Class.forName("com.mysql.jdbc.Driver");
	      } 
	      catch (ClassNotFoundException e) {
	         throw new RuntimeException(e);
	      }
	   }

	   public Connection conectar() throws SQLException {
	      String servidor = "localhost";
	      String porta = "3306";
	      String database = "Banco_aula02";
	      String usuario = "alunos";
	      String senha = "alunos";
	      return DriverManager
	         	.getConnection("jdbc:mysql://"+servidor+":"+porta+
	            "/"+database+"?user="+usuario+"&password="+senha);
	   }
	   
	   public static void main (String[] args) {
		   connection();
	   }

		private static void connection() {
		// TODO Auto-generated method stub
		
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

	public double getValor() {
		return valor;
	}


	public void setArea(double area) {
		this.area = area;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

		public void incluir(Connection conn) {
			String sqlInsert = "INSERT INTO pais(id, nome, populacao, area) VALUES (?, ?, ?,?)";

			PreparedStatement stm = null;
			try {

				stm = conn.prepareStatement(sqlInsert);
				stm.setInt(1, getId());
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
		
	}
