package curry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test {
	public static void instInfo() {
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
		String sql = "insert into user_info values(?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setString(2, "홍길동");
			ps.setInt(3, 30);
			ps.setString(4, "hong");
			ps.setString(5, "test!@");
			cnt = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBcon.closeCon();
		}
	}

}
