package com.laptrinhjavaweb.imp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.DAO.IBill;
import com.laptrinhjavaweb.model.Bill;

public class BillImp implements IBill {
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
	public int insertBill(Bill bill) {
		int id = 0;
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO dbo.Bill VALUES(?,?,?,?,?,?,?,GETDATE(),?)";
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
			ps.setInt(1, bill.getIdAccount());
			ps.setString(2, bill.getUserName());
			ps.setString(3, bill.getPhone());
			ps.setString(4, bill.getSumPrice());
			ps.setInt(5, bill.getSumCount());
			ps.setString(6, bill.getAddress());
			ps.setString(7, bill.getNote());
			ps.setInt(8, bill.getStatus());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getInt(1);
			}
			con.commit();
			return id;
		} catch (Exception e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return 0;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public List<Bill> getAllByStatusID(String cateID) {
		List<Bill> list = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				String sql = "SELECT*FROM dbo.Bill WHERE status=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, cateID);
				rs = ps.executeQuery();
				while (rs.next()) {
					Bill pr = new Bill();
					pr.setId(rs.getInt("id"));
					pr.setIdAccount(rs.getInt("idAccount"));
					pr.setUserName(rs.getString("name"));
					pr.setPhone(rs.getString("phone"));
					pr.setSumPrice(rs.getString("price"));
					pr.setSumCount(rs.getInt("sumcount"));
					pr.setAddress(rs.getString("address"));
					pr.setNote(rs.getString("note"));
					pr.setDate(rs.getString("date"));
					pr.setStatus(rs.getInt("status"));
					list.add(pr);
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
	public boolean DeleteBillById(int id) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (con != null) {
			try {
				String sql = "DELETE FROM dbo.Bill WHERE id=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
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
	public boolean updateBillById(int idBill, int idStatus) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (con != null) {
			try {
				String sql = "update Bill set status=? where id=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, idStatus);
				ps.setInt(2, idBill);

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
	public List<Bill> getAllByIdBill(int ID) {
		List<Bill> list = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				String sql = "SELECT*FROM dbo.Bill WHERE id=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1,ID);
				rs = ps.executeQuery();
				while (rs.next()) {
					Bill pr = new Bill();
					pr.setId(rs.getInt("id"));
					pr.setIdAccount(rs.getInt("idAccount"));
					pr.setUserName(rs.getString("name"));
					pr.setPhone(rs.getString("phone"));
					pr.setSumPrice(rs.getString("price"));
					pr.setSumCount(rs.getInt("sumcount"));
					pr.setAddress(rs.getString("address"));
					pr.setNote(rs.getString("note"));
					pr.setDate(rs.getString("date"));
					pr.setStatus(rs.getInt("status"));
					list.add(pr);
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

}
