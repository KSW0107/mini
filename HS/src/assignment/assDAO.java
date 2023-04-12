package assignment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.DAO;
import user.UserDTO;

public class assDAO extends DAO{
	private static assDAO assDao = new assDAO();
	Scanner sc = new Scanner(System.in);
	
	private assDAO() {};
	
	public static assDAO getInstance() {
		if( assDao ==null) {
			assDao = new assDAO();
		}
		return assDao;
	}
	
	//양도 등록
	public int assAdd(assDTO ass) {
		int result = 0 ;
		try {
			conn();
			String sql = "INSERT INTO assignment VALUES (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ass.getEventName());
			pstmt.setString(2, ass.getUserId());
			pstmt.setString(3, ass.getTitle());
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		assTableAdd(ass);
		return result;
	}
	
	//양도 등록 시 양도 신청 테이블 생성
	public void assTableAdd(assDTO ass) {
		try {
			conn();
			String assName = ass.getTitle();
			String sql = "CREATE TABLE "+assName+"_app ("
					+ "title varchar2(30) REFERENCES assignment (title) ON DELETE CASCADE,"
					+ "user_id varchar2(30) REFERENCES hs_user (user_id) ON DELETE CASCADE,"
					+ "user_name varchar2(15),"
					+ "user_location varchar2(20),"
					+ "apptext varchar2(100))";
			
			stmt = conn.createStatement();
			boolean result = stmt.execute(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
	}
	
	//등록시 중복 확인 (아이디+행사명)
		public assDTO getAssInfo1 (String eventName, String userId) {
			assDTO ass = null;
			try {
				conn();
				String sql = "SELECT * FROM assignment WHERE event_name = ? AND user_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, eventName);
				pstmt.setString(2, userId);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					ass = new assDTO();
					
					ass.setEventName(rs.getString("event_name"));
					ass.setUserId(rs.getString("user_id"));
					ass.setTitle(rs.getString("title"));
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				disconn();
			}
			
			return ass;
		}
		
	//양도 조회
	public List<assDTO> getAllAssInfo() {
		List<assDTO> list = new ArrayList<>();
		assDTO ass = null;
		
		try {
			conn();
			String sql = "SELECT * FROM assignment";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ass = new assDTO();
				
				ass.setEventName(rs.getString("event_name"));
				ass.setUserId(rs.getString("user_id"));
				ass.setTitle(rs.getNString("title"));
				
				list.add(ass);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return list;
	}
	
	//조건 별 양도 조회
	public List<assDTO> getAssInfo (String eventName) {
		List<assDTO> list = null;
		assDTO ass = null;
		try {
			conn();
			String sql = "SELECT * FROM assignment WHERE event_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eventName);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<assDTO>();
			}
			
			while (rs.next()) {
				ass = new assDTO();
				
				ass.setEventName(rs.getString("event_name"));
				ass.setUserId(rs.getString("user_id"));
				ass.setTitle(rs.getString("title"));
				
				list.add(ass);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		
		return list;
	}
	
	//조건 별 양도 조회 (제목)
	public assDTO getAssInfoTitle (String title) {
		assDTO ass = null;
		try {
			conn();
			String sql = "SELECT * FROM assignment WHERE title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				ass = new assDTO();
				
				ass.setEventName(rs.getString("event_name"));
				ass.setUserId(rs.getString("user_id"));
				ass.setTitle(rs.getString("title"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return ass;
	}
	
	//양도하기 삭제
	public int deleteAss(String title) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE assignment WHERE title = ?"; // 예약회원 시 삭제
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);


			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}
	
	//양도신청자 조회
	public List<assAppDTO> getAssAppInfo (String title) {
		List<assAppDTO> list = null;
		assAppDTO assApp = null;
		try {
			conn();
			String sql = "SELECT * FROM "+title+"_app";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList<assAppDTO>();
			}
			while (rs.next()) {
				assApp = new assAppDTO();
				
				assApp.setAppUserId(rs.getString("user_id"));
				assApp.setUserLocaation(rs.getString("user_location"));
				assApp.setUserName(rs.getString("user_name"));
				assApp.setApptext(rs.getString("apptext"));
				
				list.add(assApp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		
		return list;
	}
	
	//양도신청자 (아이디 + 타이틀)
	public assAppDTO assAppIdInfo(String appUserId, String title) {
		assAppDTO assApp = null;
		try {
			conn();
			String sql = "SELECT * FROM "+title+"_app WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, appUserId);
			rs  = pstmt.executeQuery();
			
			if(rs.next()) {
				assApp = new assAppDTO();
				assApp.setAppUserId(rs.getString("user_id"));
				assApp.setUserName(rs.getString("user_name"));
				assApp.setUserLocaation(rs.getString("user_location"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return assApp;
	}
	
	//양도 신청하기
	public int assAppAdd(String title,String appText, UserDTO user) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO "+title+"_app VALUES (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserLocation());
			pstmt.setString(5, appText);

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	
	
	//양도 수락하기
	public int changeReservation(String eventName ,String userId, UserDTO user) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE "+eventName+"_rsv SET user_id=?, user_name=?, user_location=?"
					+ "WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getUserLocation());
			pstmt.setString(4, userId);
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
	}
	
	//양도 성공 시 양도신청 테이블 자동 삭제
	public void tableDelete(String title) {
		try {
			conn();
			String sql = "DROP TABLE "+title+"_app"; // 예약회원 시 삭제
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
	}
	
	//양도 성공 시 양도 게시글 자동 삭제
	public void deletePage(String eventName, String userId) {
		try {
			conn();
			String sql = "DELETE assignment WHERE event_name = ? AND user_id = ?"; // 예약회원 시 삭제
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eventName);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		
		
	}
	
}
