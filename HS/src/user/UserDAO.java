package user;

import common.DAO;

public class UserDAO extends DAO{
	private static UserDAO userDao = new UserDAO();

	private UserDAO() {};
	
	//싱글톤
	public static UserDAO getInstance() {
		if(userDao == null) {
			userDao = new UserDAO();
		}
		return userDao;
	}
	
	
	//로그인
	public UserDTO login(String id) {
		UserDTO user = null;

		try {
			conn();
			String sql = "SELECT * FROM hs_user WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new UserDTO();
				user.setUserId(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pw"));
				user.setUserName(rs.getString("user_name"));
				user.setUserLocation(rs.getString("user_location"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return user;
	}
	
	
	//회원 등록
	public int UserAdd(UserDTO user) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO hs_user VALUES (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserLocation());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		
		return result;
	}
	
	//회원조회
	public UserDTO userInfo(String userId) {
		UserDTO user = null;
		try {
			conn();
			String sql = "SELECT * FROM hs_user WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new UserDTO();
				
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserLocation(rs.getString("user_location"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return user;
	}
	
	//회원 수정
	public int UserPwUpdate(UserDTO user) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE hs_user SET user_pw = ? WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserPw());
			pstmt.setString(2, user.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	
	public int UserNameUpdate(UserDTO user) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE hs_user SET user_name = ? WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	
	public int UserLocationUpdate(UserDTO user) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE hs_user SET user_location = ? WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserLocation());
			pstmt.setString(2, user.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	
}
