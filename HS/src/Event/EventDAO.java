package Event;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import common.DAO;
import user.UserDTO;

public class EventDAO extends DAO {
	private static EventDAO eventDao = new EventDAO();

	private EventDAO() {
	};

	// 싱글톤
	public static EventDAO getInstance() {
		if (eventDao == null) {
			eventDao = new EventDAO();
		}
		return eventDao;
	}

	// 행사 등록
	public int EventAdd(EventDTO event) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO event VALUES (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, event.getEventName());
			pstmt.setString(2, event.getEventPlace());
			pstmt.setDate(3, event.getEventDate());
			pstmt.setString(4, event.getEventTime());
			pstmt.setString(5, event.getEventCategory());
			pstmt.setInt(6, event.getEventmaxPer());
			pstmt.setString(7, event.getEventOrg());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		addEventTable(event);

		return result;
	}

	// 행사 등록 시 행사예약자 테이블 생성
	public void addEventTable(EventDTO event) {
		try {
			conn();
			String name = event.getEventName();
			String sql = "CREATE TABLE " + name + "_rsv ("
					+ "event_name varchar2(30)REFERENCES event (event_name) ON DELETE CASCADE,"
					+ "user_id varchar2(30) REFERENCES hs_user(user_id)," + "user_name varchar2(15),"
					+ "user_location varchar2(20))";
			stmt = conn.createStatement();
			boolean result = stmt.execute(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
	}

	// 행사 전체 조회
	public List<EventDTO> getAllEventInfo() {
		List<EventDTO> list = new ArrayList<EventDTO>();
		EventDTO event = null;

		try {
			conn();
			String sql = "SELECT * FROM event";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				event = new EventDTO();

				event.setEventName(rs.getString("event_name"));
				event.setEventPlace(rs.getString("event_place"));
				event.setEventDate(rs.getDate("event_date"));
				event.setEventTime(rs.getString("event_time"));
				event.setEventCategory(rs.getString("event_category"));
				event.setEventmaxPer(rs.getInt("event_maxper"));
				event.setEventOrg(rs.getString("event_org"));

				list.add(event);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}

		return list;
	}

	// 행사 상세 조회
	public EventDTO getEventInfo(String eventName) {
		EventDTO event = null;
		try {
			conn();
			String sql = "SELECT * FROM event WHERE event_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eventName);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				event = new EventDTO();

				event.setEventName(rs.getString("event_name"));
				event.setEventPlace(rs.getString("event_place"));
				event.setEventDate(rs.getDate("event_date"));
				event.setEventTime(rs.getString("event_time"));
				event.setEventCategory(rs.getString("event_category"));
				event.setEventmaxPer(rs.getInt("event_maxper"));
				event.setEventOrg(rs.getString("event_org"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return event;
	}
	

	// 행사 조건 조회
	// 장소별
	public List<EventDTO> getPlaceInfo(String eventPlace) {
		List<EventDTO> list = new ArrayList<EventDTO>();
		EventDTO event = null;
		try {
			conn();
			String sql = "SELECT * FROM event WHERE event_place = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eventPlace);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				event = new EventDTO();

				event.setEventName(rs.getString("event_name"));
				event.setEventPlace(rs.getString("event_place"));
				event.setEventDate(rs.getDate("event_date"));
				event.setEventTime(rs.getString("event_time"));
				event.setEventCategory(rs.getString("event_category"));
				event.setEventmaxPer(rs.getInt("event_maxper"));
				event.setEventOrg(rs.getString("event_org"));

				list.add(event);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}

	// 날짜별
	public List<EventDTO> getDateInfo(Date eventDate) {
		List<EventDTO> list = new ArrayList<EventDTO>();
		EventDTO event = null;
		try {
			conn();
			String sql = "SELECT * FROM event WHERE event_date = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, eventDate);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				event = new EventDTO();

				event.setEventName(rs.getString("event_name"));
				event.setEventPlace(rs.getString("event_place"));
				event.setEventDate(rs.getDate("event_date"));
				event.setEventTime(rs.getString("event_time"));
				event.setEventCategory(rs.getString("event_category"));
				event.setEventmaxPer(rs.getInt("event_maxper"));
				event.setEventOrg(rs.getString("event_org"));

				list.add(event);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}

	// 카테고리별
	public List<EventDTO> getCategoryInfo(String eventCategory) {
		List<EventDTO> list = new ArrayList<EventDTO>();
		EventDTO event = null;
		try {
			conn();
			String sql = "SELECT * FROM event WHERE event_category = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eventCategory);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				event = new EventDTO();

				event.setEventName(rs.getString("event_name"));
				event.setEventPlace(rs.getString("event_place"));
				event.setEventDate(rs.getDate("event_date"));
				event.setEventTime(rs.getString("event_time"));
				event.setEventCategory(rs.getString("event_category"));
				event.setEventmaxPer(rs.getInt("event_maxper"));
				event.setEventOrg(rs.getString("event_org"));

				list.add(event);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}

	// 행사 수정
	// 장소
	public int EventPlaceUpdate(EventDTO event) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE event SET event_place = ? WHERE event_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, event.getEventPlace());
			pstmt.setString(2, event.getEventName());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}

	// 날짜
	public int EventDateUpdate(EventDTO event) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE event SET event_date = ? WHERE event_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, event.getEventDate());
			pstmt.setString(2, event.getEventName());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}

	// 시간
	public int EventTimeUpdate(EventDTO event) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE event SET event_time = ? WHERE event_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, event.getEventTime());
			pstmt.setString(2, event.getEventName());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}

	// 카테고리
	public int EventCategoryUpdate(EventDTO event) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE event SET event_category = ? WHERE event_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, event.getEventCategory());
			pstmt.setString(2, event.getEventName());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}

	// 수용인원
	public int EventMaxPerUpdate(EventDTO event) {
		int result = 0;
		try {
			conn();
			String sql = "UPDATE event SET event_maxper = ? WHERE event_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, event.getEventmaxPer());
			pstmt.setString(2, event.getEventName());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}

	// 행사 삭제
	public int EventDelete(String eventName) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE FROM event WHERE event_name = ?"; // 예약회원 시 삭제
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eventName);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		tableDelete(eventName);
		return result;
	}

	// 행사삭제 시 하위 테이블 삭제
	public void tableDelete(String eventName) {
		try {
			conn();
			String sql = "DROP TABLE " + eventName + "_rsv"; // 예약회원 시 삭제
			pstmt = conn.prepareStatement(sql);
			int result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
	}

	// 행사 예약 등록
	public int resevationAdd(UserDTO user, String eventName) {
		int result = 0;
		try {
			conn();
			String sql = "INSERT INTO " + eventName + "_rsv VALUES (?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eventName);
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserLocation());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return result;
	}

	//예약자 조회
	public List<UserDTO> eventInReservation(String eventName) {
		List<UserDTO> list = new ArrayList<UserDTO>();
		UserDTO user = null;
		try {
			conn();
			String sql = "SELECT * FROM " + eventName + "_rsv";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new UserDTO();

				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserLocation(rs.getString("user_location"));

				list.add(user);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return list;
	}
	
	// 예약 삭제
	public int reservationDelete(String eventName, String userId) {
		int result = 0;
		try {
			conn();
			String sql = "DELETE FROM " + eventName + "_rsv WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}

		return result;
	}
	// 양도 등록 시 아이디+행사명 조회
	public UserDTO eventInReservation1(String eventName, String userId) {
		UserDTO user = null;
		try {
			conn();
			String sql = "SELECT * FROM " + eventName + "_rsv WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new UserDTO();
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserLocation(rs.getString("user_location"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return user;
	}
}
