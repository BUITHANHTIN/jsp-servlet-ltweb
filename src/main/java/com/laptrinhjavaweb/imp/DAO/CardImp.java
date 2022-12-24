package com.laptrinhjavaweb.imp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.DAO.IAccount;
import com.laptrinhjavaweb.DAO.ICard;
import com.laptrinhjavaweb.model.Account;
import com.laptrinhjavaweb.model.Card;

public class CardImp implements ICard {
	// @Inject
	// private ConnectionSQL connect;
	public Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://DESKTOP-GKACKIO\\\\THANHTIN:1433;databaseName=doanwedgiay1;integratedSecurity=true";
			String user = "sa";
			String password = "123";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
		}
		return null;
	}

	@Override
	public boolean InsertCard(Card card) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (con != null) {
			try {
				String sql = "INSERT INTO dbo.Card VALUES (?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, card.getIdAccount());
				ps.setInt(2, card.getId());
				ps.setString(3, card.getName());
				ps.setString(4, card.getImage());
				ps.setDouble(5, card.getPrice());
				ps.setInt(6, card.getCount());
				if (ps.executeUpdate() > 0) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (ps != null) {
						ps.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public List<Card> getAllbyAccountId(int id) {
		List<Card> list = new ArrayList<>();
		Card card = null;
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				String sql = "SELECT*FROM dbo.Card WHERE idAccount=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					card = new Card();
					card.setIdAccount(rs.getInt(1));
					card.setId(rs.getInt(2));
					card.setName(rs.getString(3));
					card.setImage(rs.getString(4));
					card.setPrice(rs.getDouble(5));
					card.setCount(rs.getInt(6));
					list.add(card);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public boolean UpdateCardByIdProduct(int count, int idAccount, int idProduct) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (con != null) {
			try {
				String sql = "UPDATE dbo.Card SET count=? WHERE idAccount=? AND IdProduct=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, count);
				ps.setInt(2, idAccount);
				ps.setInt(3, idProduct);
				if (ps.executeUpdate() > 0) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (ps != null) {
						ps.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public boolean DeleteCardByIdProduct(int idAccount, int idProduct) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (con != null) {
			try {
				String sql = "DELETE FROM dbo.Card WHERE idAccount=? AND IdProduct=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, idAccount);
				ps.setInt(2, idProduct);
				if (ps.executeUpdate() > 0) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (ps != null) {
						ps.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public void DeleteCard(int idAccount) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (con != null) {
			try {
				String sql = "DELETE FROM dbo.Card WHERE idAccount=? ";
				ps = con.prepareStatement(sql);
				ps.setInt(1, idAccount);
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (ps != null) {
						ps.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

	}
}
