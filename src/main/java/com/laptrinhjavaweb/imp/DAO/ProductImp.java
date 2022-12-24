package com.laptrinhjavaweb.imp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.DAO.IProduct;
import com.laptrinhjavaweb.model.Product;

public class ProductImp implements IProduct {
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
	public List<Product> getAllByCateID(String cateID) {
		List<Product> list = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				String sql = "SELECT*FROM dbo.product WHERE cateID=? and  [SLHangTon]>0";
				ps = con.prepareStatement(sql);
				ps.setString(1, cateID);
				rs = ps.executeQuery();
				while (rs.next()) {
					Product pr = new Product();
					pr.setId(rs.getInt("id"));
					pr.setCateID(rs.getInt("cateID"));
					pr.setName(rs.getString("name"));
					pr.setTitle(rs.getString("title"));
					pr.setImage(rs.getString("image"));
					pr.setPrice(rs.getDouble("price"));
					pr.setDescription(rs.getString("description"));
					pr.setSLHangTon(rs.getInt("SLHangTon"));
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

	public static void main(String[] args) {
		ProductImp p = new ProductImp();
		System.out.println(p.countByImageAndId("19130234_Buithanhtin_quanlinhanvien.png", 510));
	}

	@Override
	public List<Product> getOneOrMoreSpecialDeals(int top, String cateID) {
		List<Product> list = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				String sql = "SELECT TOP(?)* FROM dbo.product WHERE cateID=? and  [SLHangTon]>0 ORDER BY price ASC  ";
				ps = con.prepareStatement(sql);
				ps.setInt(1, top);
				ps.setString(2, cateID);
				rs = ps.executeQuery();
				while (rs.next()) {
					Product pr = new Product();
					pr.setId(rs.getInt("id"));
					pr.setCateID(rs.getInt("cateID"));
					pr.setName(rs.getString("name"));
					pr.setTitle(rs.getString("title"));
					pr.setImage(rs.getString("image"));
					pr.setPrice(rs.getDouble("price"));
					pr.setDescription(rs.getString("description"));
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
	public List<Product> getAllBySearch(String searchTitle) {
		List<Product> list = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				String sql = "SELECT * FROM dbo.product WHERE  [SLHangTon]>0 and title LIKE ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, "%" + searchTitle + "%");
				rs = ps.executeQuery();
				while (rs.next()) {
					Product pr = new Product();
					pr.setId(rs.getInt("id"));
					pr.setCateID(rs.getInt("cateID"));
					pr.setName(rs.getString("name"));
					pr.setTitle(rs.getString("title"));
					pr.setImage(rs.getString("image"));
					pr.setPrice(rs.getDouble("price"));
					pr.setDescription(rs.getString("description"));
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
	public Product getOneById(String id) {
		Product pr = new Product();
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				String sql = "SELECT*FROM dbo.product WHERE id=? and  [SLHangTon]>0";
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					pr = new Product();
					pr.setCateID(rs.getInt("cateID"));
					pr.setName(rs.getString("name"));
					pr.setTitle(rs.getString("title"));
					pr.setImage(rs.getString("image"));
					pr.setPrice(rs.getDouble("price"));
					pr.setDescription(rs.getString("description"));
					pr.setId(rs.getInt("id"));
					pr.setSLHangTon(rs.getInt("SLHangTon"));
				}
				return pr;
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
		} // TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAll() {
		List<Product> list = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				String sql = "SELECT *FROM dbo.product WHERE [SLHangTon]>0 ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					Product pr = new Product();
					pr.setCateID(rs.getInt("cateID"));
					pr.setName(rs.getString("name"));
					pr.setTitle(rs.getString("title"));
					pr.setImage(rs.getString("image"));
					pr.setPrice(rs.getDouble("price"));
					pr.setDescription(rs.getString("description"));
					pr.setId(rs.getInt("id"));
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
	public int countgetAllProduct() {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		if (con != null) {
			try {
				String sql = "SELECT COUNT(*) FROM dbo.product WHERE [SLHangTon]>0";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}
				return count;
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
		return 0;
	}

	@Override
	public int countgetAllProduct(String cateID) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		if (con != null) {
			try {
				String sql = " SELECT COUNT(*) FROM dbo.product WHERE cateID=? and  [SLHangTon]>0";
				ps = con.prepareStatement(sql);
				ps.setString(1, cateID);
				rs = ps.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}
				return count;
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
		return 0;
	}

	@Override
	public List<Product> getProducByPaging(int input, int param, String cateID) {
		List<Product> list = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				String sql = "SELECT*FROM dbo.product WHERE [SLHangTon]>0 and cateID=?  ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROW ONLY ";
				ps = con.prepareStatement(sql);
				ps.setString(1, cateID);
				ps.setInt(2, (input - 1) * param);
				ps.setInt(3, param);

				rs = ps.executeQuery();
				while (rs.next()) {
					Product pr = new Product();
					pr.setCateID(rs.getInt("cateID"));
					pr.setName(rs.getString("name"));
					pr.setTitle(rs.getString("title"));
					pr.setImage(rs.getString("image"));
					pr.setPrice(rs.getDouble("price"));
					pr.setDescription(rs.getString("description"));
					pr.setId(rs.getInt("id"));
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
	public boolean DeleteProductById(int idProduct) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (con != null) {
			try {
				String sql = "DELETE FROM dbo.product WHERE id=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, idProduct);
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
	public boolean updateProductById(Product p) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (con != null) {
			try {
				String sql = "update dbo.product set name=?,image=?,price=? ,description=?,cateID= ?,SLHangTon=? ,title=? where id=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, p.getName());
				ps.setString(2, p.getImage());
				ps.setDouble(3, p.getPrice());
				ps.setString(4, p.getDescription());
				ps.setInt(5, p.getCateID());
				ps.setInt(6, p.getSLHangTon());
				ps.setString(7, p.getTitle());
				ps.setInt(8, p.getId());
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
	public boolean addOneProduct(Product p) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		if (con != null) {
			try {
				String sql = "INSERT into product VALUES(?,?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, p.getName());
				ps.setString(2, p.getImage());
				ps.setDouble(3, p.getPrice());
				ps.setString(4, p.getTitle());
				ps.setString(5, p.getDescription());
				ps.setInt(6, p.getCateID());
				ps.setInt(7, p.getSLHangTon());

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
	public int countImage(String image) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		if (con != null) {
			try {
				String sql = "select count(id) from [product] where image=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, image);
				rs = ps.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}
				return count;
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
		return 0;
	}

	@Override
	public int countByImageAndId(String image, int id) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		if (con != null) {
			try {
				String sql = "select count(id) from [product] where image=? and id=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, image);
				ps.setInt(2, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}
				return count;
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
		return 0;
	}

}
