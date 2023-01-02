package com.laptrinhjavaweb.imp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.DAO.IAccount;
import com.laptrinhjavaweb.model.Account;

public class AccountImp implements IAccount {
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

	

	public List<Account> getOneAccount(String user, String pass) {
		List<Account> list = new ArrayList<>();
		Account account = null;
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				String sql = "SELECT*FROM dbo.Account WHERE [user]=? AND pass=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, user);
				ps.setString(2, pass);
				rs = ps.executeQuery();
				while (rs.next()) {
					account = new Account();
					account.setId(rs.getInt(1));
					account.setUsername(rs.getString(2));
					account.setUser(rs.getString(3));
					account.setPass(rs.getString(4));
					account.setIsSell(rs.getInt(5));
					account.setIsAdmin(rs.getInt(6));
					list.add(account);
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
	public String UsernameExist(String username) {
		String re = null;
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				String sql = "SELECT*FROM dbo.Account WHERE username=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, username);
				rs = ps.executeQuery();
				while (rs.next()) {

					re = rs.getString(2);
				}
				return re;
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
		return null;
	}

	@Override
	public long InsertAccount(Account account) {
		long id = 0;
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO dbo.Account(username,[user],pass,isSell,isAdmin,publickey)VALUES(?,?,?,0,?,?)";
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
			ps.setString(1, account.getUsername());
			ps.setString(2, account.getUser());
			ps.setString(3, account.getPass());
			ps.setInt(4, account.getIsAdmin());
			ps.setString(5, account.getPublicKey());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getLong(1);
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
	public List<Account> listAccount(int isAdmin) {
		List<Account> list = new ArrayList<>();
		Account account = null;
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				String sql = "SELECT*FROM dbo.Account WHERE isAdmin=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, isAdmin);
				rs = ps.executeQuery();
				while (rs.next()) {
					account = new Account();
					account.setId(rs.getInt(1));
					account.setUsername(rs.getString(2));
					account.setUser(rs.getString(3));
					account.setPass(rs.getString(4));
					account.setIsSell(rs.getInt(5));
					account.setIsAdmin(rs.getInt(6));
					list.add(account);
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
	public boolean deleteAccount(int id) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (con != null) {
			try {
				String sql = "delete from dbo.Account where [uID]=? ";
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
	public boolean updateAccount(Account account) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (con != null) {
			try {
				String sql = "update Account set username=? ,[user]=?,pass=? where [uID]=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, account.getUsername());
				ps.setString(2, account.getUser());
				ps.setString(3, account.getPass());
				ps.setInt(4, account.getId());
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
	public String UserExist(String user) {
		String re = null;
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				String sql = "SELECT*FROM dbo.Account WHERE [user]=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, user);
				rs = ps.executeQuery();
				while (rs.next()) {

					re = rs.getString(2);
				}
				return re;
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
		return null;
	}

	@Override
	public boolean updatePass(String user, String pass) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (con != null) {
			try {
				String sql = "update Account set pass=? where [user]=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, pass);
				ps.setString(2, user);

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
	public Account findbyId(int id) {
		Account ac = null;
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				String sql = "SELECT*FROM dbo.Account WHERE [uID]=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					ac = new Account();
					ac.setPublicKey(rs.getString("publickey"));
				}
				return ac;
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
		return null;
	}
	
}
