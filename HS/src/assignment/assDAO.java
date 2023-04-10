package assignment;

import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class assDAO extends DAO{
	private static assDAO assDao = new assDAO();
	
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
			String sql = "INSERT INTO assignment VALUES (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ass.getEventName());
			pstmt.setString(2, ass.getUserId());
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return result;
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
				
				list.add(ass);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disconn();
		}
		return list;
	}
	

}
