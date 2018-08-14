package curry;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DBcon {
	private static String url;
	private static String id;
	private static String pwd;
	private static String driver;
	private static Connection con;

// propertie 가져오기
	public static void load(String path) {
		InputStream is = DBcon.class.getResourceAsStream(path);
		Properties prop = new Properties();
		try {
			prop.load(is);
			is.close();
			url = prop.getProperty("url");
			id = prop.getProperty("id");
			pwd = prop.getProperty("pwd");
			driver = prop.getProperty("driver");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Connection 관련 메서드
	public static void openCon() {
		load("/config/db.properties");
		try {
			if (DBcon.con == null || DBcon.con.isClosed()) {
				try {
					Class.forName(driver);
					DBcon.con = DriverManager.getConnection(url, id, pwd);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getCon() {
		if (DBcon.con == null) {
			openCon();
		}
		return DBcon.con;
	}

	public static boolean closeCon() {
		if (DBcon.con != null) {
			try {
				DBcon.con.close();
				DBcon.con = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;

	}

	// SQL 메서드 시작
	public static HashMap<String, String> getInfo(String id, String pwd) {
		if (DBcon.con == null) {
			openCon();
		}
		try {
			if (DBcon.con.isClosed()) {
				DBcon.con = null;
				DBcon.openCon();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HashMap<String, String> memInfo = new HashMap<String, String>();
		// String sql = "select * from member_info where miId='" + id +"'AND miPwd='" +
		// pwd + "'";
		String sql = "select * from member_info where miId=? AND miPwd=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				memInfo.put("miName", rs.getString("miName"));
				memInfo.put("miEail", rs.getString("miEmail"));
				memInfo.put("miId", rs.getString("miId"));
				memInfo.put("diNo", rs.getString("diNo"));
				memInfo.put("miNo", rs.getString("miNo"));
				memInfo.put("lvl", rs.getString("lvl"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.closeCon();
		}
		return memInfo;

	}

	public static List<HashMap<String, String>> getInfo() {
		if (DBcon.con == null) {
			openCon();
		}
		try {
			if (DBcon.con.isClosed()) {
				DBcon.con = null;
				DBcon.openCon();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<HashMap<String, String>> memInfo = new ArrayList<HashMap<String, String>>();
		// String sql = "select * from member_info where miId='" + id +"'AND miPwd='" +
		// pwd + "'";
		String sql = "select * from member_info";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HashMap<String, String> hm = new HashMap<String, String>();
				hm.put("miName", rs.getString("miName"));
				hm.put("miEail", rs.getString("miEmail"));
				hm.put("miId", rs.getString("miId"));
				hm.put("diNo", rs.getString("diNo"));
				hm.put("miNo", rs.getString("miNo"));
				hm.put("lvl", rs.getString("lvl"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.closeCon();
		}
		return memInfo;

	}

	public static int checkInfo(String id) {
		if (DBcon.con == null) {
			openCon();
		}
		try {
			if (DBcon.con.isClosed()) {
				DBcon.con = null;
				DBcon.openCon();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int cnt = 0;
		// String sql = "select * from member_info where miId='" + id +"'AND miPwd='" +
		// pwd + "'";
		String sql = "select count(1) from member_info where miId=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.closeCon();
		}
		return cnt;

	}

	public static int instInfo(HashMap<String, String> infoMap) {
		if (DBcon.con == null) {
			openCon();
		}
		try {
			if (DBcon.con.isClosed()) {
				DBcon.con = null;
				DBcon.openCon();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int cnt = 0;
		// String sql = "select * from member_info where miId='" + id +"'AND miPwd='" +
		// pwd + "'";
		String sql = "insert into member_info values(seq_mino.nextval,?,?,1,?,?,to_char(sysdate,'YYYYMMDD'),to_char(sysdate,'HH24miss'),?,to_char(sysdate,'YYYYMMDD'),to_char(sysdate,'HH24miss'),?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, infoMap.get("miId"));
			ps.setString(2, infoMap.get("miPwd"));
			ps.setString(3, infoMap.get("miEmail"));
			ps.setString(4, infoMap.get("miEtc"));
			ps.setString(5, infoMap.get("miNo"));
			ps.setString(6, infoMap.get("miNo"));
			ps.setString(7, infoMap.get("lvl"));
			ps.setString(8, infoMap.get("miName"));
			cnt = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.closeCon();
		}
		return cnt;
	}

	public static List<Map<String, String>> getList() throws SQLException {
		if (DBcon.con == null) {
			openCon();
		}
		String sql = "select mino, miname, miid, miemail,"
				+ "(select miid from member_info mi2 where mi2.mino=mi.creusr) "
				+ "creusr,(select diname from depart_info di where di.dino=mi.dino) diname "
				+ "from member_info mi";
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		PreparedStatement ps = DBcon.con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ResultSetMetaData rsmd = rs.getMetaData();
		int colCnt = rsmd.getColumnCount();
		List<String> colList = new ArrayList<String>();
		for (int i = 1; i <= colCnt; i++) {
			String col = rsmd.getColumnLabel(i);
			colList.add(col);
		}

		Map<String, String> map = null;
		while (rs.next()) {
			map = new HashMap<String, String>();
			for (String col : colList) {
				map.put(col, rs.getString(col));
			}
			list.add(map);
		}
		rs.close();
		rs = null;
		ps.close();
		ps = null;
		return list;
	}

	// SQL 메서드 끝

	public static void main(String[] args) {
		DBcon.openCon();
		System.out.println(DBcon.con != null);
		try {
			getList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
