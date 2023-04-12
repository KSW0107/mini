package Event;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import user.UserDTO;
import user.UserService;

public class EventService {
	Scanner sc = new Scanner(System.in);

	// 행사 등록
	public void eventAdd() {
		EventDTO event = new EventDTO();
		System.out.println("추가할 행사 이름을 입력하시오 > ");
		String eventName = sc.nextLine();
		event.setEventName(eventName);

		if (EventDAO.getInstance().getEventInfo(eventName) == null) {

			System.out.println("추가할 행사 장소를 입력하시오 > ");
			event.setEventPlace(sc.nextLine());
			System.out.println("추가할 행사 날짜를 입력하시오 > ");
			event.setEventDate(Date.valueOf(sc.nextLine()));
			System.out.println("추가할 행사 시간을 입력하시오 > ");
			event.setEventTime(sc.nextLine());
			System.out.println("추가할 행사 카테고리를 입력하시오 > ");
			event.setEventCategory(sc.nextLine());
			System.out.println("추가할 행사 수용인원을 입력하시오 > ");
			event.setEventmaxPer(Integer.parseInt(sc.nextLine()));
			event.setEventOrg(UserService.userInfo.getUserId());

			int result = EventDAO.getInstance().EventAdd(event);

			if (result == 1) {
				System.out.println("행사 등록 성공했습니다");
			} else {
				System.out.println("행사 등록 실패했습니다");
			}
		} else {
			System.out.println("이미 존재하는 행사명입니다");
		}

	}

	// 행사 전체 조회
	public void getAllEventInfo() {
		List<EventDTO> list = EventDAO.getInstance().getAllEventInfo();

		for (EventDTO evnet : list) {
			System.out.print("행사 이름 : " + evnet.getEventName());
			System.out.print(" | 행사 장소 : " + evnet.getEventPlace());
			System.out.print(" | 행사 날짜 : " + evnet.getEventDate());
			System.out.print(" | 카테고리 : " + evnet.getEventCategory());
			System.out.println("\n-------------------------------------------------------------------");
		}
	}

	// 행사 상세 조회
	public void getEventInfo() {
		getAllEventInfoSimple();
		System.out.println("조회하려는 행사 이름 입력하시오 > ");
		String eventName = sc.nextLine();
		EventDTO event = EventDAO.getInstance().getEventInfo(eventName);

		if (event == null) {
			System.out.println("존재하지 않는 행사 이름입니다");
		} else {
			System.out.println(" ● 행사 이름 : " + event.getEventName());
			System.out.println(" ● 장소 : " + event.getEventPlace());
			System.out.println(" ● 날짜 : " + event.getEventDate());
			System.out.println(" ● 시간 : " + event.getEventTime());
			System.out.println(" ● 카테고리 : " + event.getEventCategory());
			System.out.println(" ● 수용인원 : " + event.getEventmaxPer());
			System.out.println(" ● 주최자 아이디 : " + event.getEventOrg());
		}
	}

	// 시스템 행사조회
	public void getEventInfo(String eventName) {
		EventDTO event = EventDAO.getInstance().getEventInfo(eventName);

		if (event == null) {
			System.out.println("존재하지 않는 행사 이름입니다");
		} else {
			System.out.println("행사 이름 : " + event.getEventName());
			System.out.println("장소 : " + event.getEventPlace());
			System.out.println("날짜 : " + event.getEventDate());
			System.out.println("시간 : " + event.getEventTime());
			System.out.println("카테고리 : " + event.getEventCategory());
			System.out.println("수용인원 : " + event.getEventmaxPer());
			System.out.println("주최자 아이디 : " + event.getEventOrg());
		}
	}

	// 조건 조회
	// 장소별
	public void getPlaceInfo() {
		System.out.println("조회하려는 지역을 입력하시오 > ");
		String place = sc.nextLine();
		List<EventDTO> list = EventDAO.getInstance().getPlaceInfo(place);

		for (EventDTO event : list) {
			System.out.println("행사 이름 : " + event.getEventName());
			System.out.println("장소 : " + event.getEventPlace());
			System.out.println("날짜 : " + event.getEventDate());
			System.out.println("시간 : " + event.getEventTime());
			System.out.println("카테고리 : " + event.getEventCategory());
			System.out.println("수용인원 : " + event.getEventmaxPer());
			System.out.println("주최자 아이디 : " + event.getEventOrg());
			System.out.println("-------------------------");
		}
	}

	// 날짜별
	public void getDateInfo() {
		System.out.println("조회하려는 날짜를 입력하시오 > ");
		Date date = Date.valueOf(sc.nextLine());
		List<EventDTO> list = EventDAO.getInstance().getDateInfo(date);

		for (EventDTO event : list) {
			System.out.println("행사 이름 : " + event.getEventName());
			System.out.println("장소 : " + event.getEventPlace());
			System.out.println("날짜 : " + event.getEventDate());
			System.out.println("시간 : " + event.getEventTime());
			System.out.println("카테고리 : " + event.getEventCategory());
			System.out.println("수용인원 : " + event.getEventmaxPer());
			System.out.println("주최자 아이디 : " + event.getEventOrg());
			System.out.println("-------------------------");
		}
	}

	// 카테고리별
	public void getCategoryInfo() {
		System.out.println("조회하려는 카테고리를 입력하시오 > ");
		String catrgory = sc.nextLine();
		List<EventDTO> list = EventDAO.getInstance().getCategoryInfo(catrgory);

		for (EventDTO event : list) {
			System.out.println("행사 이름 : " + event.getEventName());
			System.out.println("장소 : " + event.getEventPlace());
			System.out.println("날짜 : " + event.getEventDate());
			System.out.println("시간 : " + event.getEventTime());
			System.out.println("카테고리 : " + event.getEventCategory());
			System.out.println("수용인원 : " + event.getEventmaxPer());
			System.out.println("주최자 아이디 : " + event.getEventOrg());
			System.out.println("-------------------------");
		}
	}

	// 행사 수정
	// 장소
	public void EventPlaceUpdate() {
		getAllEventInfoSimple();
		System.out.println("장소를 수정할 행사이름을 입력하시오 > ");
		String eventName = sc.nextLine();
		if (EventDAO.getInstance().getEventInfo(eventName) != null) {
			EventDTO event = EventDAO.getInstance().getEventInfo(eventName);

			if (UserService.userInfo.getUserId().equals(event.getEventOrg())) {
				System.out.println("바꿀 장소 > ");
				event.setEventPlace(sc.nextLine());

				int result = EventDAO.getInstance().EventPlaceUpdate(event);
				if (result == 1) {
					System.out.println("장소 변경 완료했습니다");
				} else {
					System.out.println("존재하지 않는 행사 이름입니다");
				}
			} else {
				System.out.println("수정 권한이 없는 행사입니다");
			}
		} else {
			System.out.println("존재하지않는 행사 이름입니다");
		}
	}

	// 날짜
	public void EventDateUpdate() {
		getAllEventInfoSimple();
		System.out.println("날짜를 수정할 행사 이름을 입력하시오 > ");
		String eventName = sc.nextLine();
		if (EventDAO.getInstance().getEventInfo(eventName) != null) {
			EventDTO event = EventDAO.getInstance().getEventInfo(eventName);

			if (UserService.userInfo.getUserId().equals(event.getEventOrg())) {
				System.out.println("바꿀 날짜를 입력하시오 > ");
				event.setEventDate(Date.valueOf(sc.nextLine()));

				int result = EventDAO.getInstance().EventDateUpdate(event);
				if (result == 1) {
					System.out.println("날짜 변경 완료했습니다");
				} else {
					System.out.println("존재하지않는 행사명 입니다");
				}
			} else {
				System.out.println("수정 권한이 없는 행사입니다");
			}
		} else {
			System.out.println("존재하지않는 행사명 입니다");
		}
	}

	// 시간
	public void EventTimeUpdate() {
		getAllEventInfoSimple();
		System.out.println("시간을 수정할 행사이름을 입력하시오 > ");
		String eventName = sc.nextLine();

		if (EventDAO.getInstance().getEventInfo(eventName) != null) {
			EventDTO event = EventDAO.getInstance().getEventInfo(eventName);

			if (UserService.userInfo.getUserId().equals(event.getEventOrg())) {
				System.out.println("바꿀 시간을 입력하시오 > ");
				event.setEventTime(sc.nextLine());

				int result = EventDAO.getInstance().EventTimeUpdate(event);
				if (result == 1) {
					System.out.println("시간 변경 완료했습니다");
				} else {
					System.out.println("존재하지않는 행사명입니다");
				}
			} else {
				System.out.println("수정 권한이 없는 행사입니다");
			}
		} else {
			System.out.println("존재하지않는 행사명 입니다");
		}
	}

	// 카테고리
	public void EventCategoryUpdate() {
		getAllEventInfoSimple();
		System.out.println("카테고리를 수정할 행사이름을 입력하시오 > ");
		String eventName = sc.nextLine();

		if (EventDAO.getInstance().getEventInfo(eventName) != null) {
			EventDTO event = EventDAO.getInstance().getEventInfo(eventName);

			if (UserService.userInfo.getUserId().equals(event.getEventOrg())) {
				System.out.println("바꿀 카테고리를 입력하시오 > ");
				event.setEventCategory(sc.nextLine());

				int result = EventDAO.getInstance().EventCategoryUpdate(event);
				if (result == 1) {
					System.out.println("카테고리 변경 완료했습니다");
				} else {
					System.out.println("존재하지않는 행사명 입니다");
				}
			} else {
				System.out.println("수정 권한이 없는 행사입니다");
			}
		} else {
			System.out.println("존재하지않는 행사명 입니다");
		}
	}

	// 인원
	public void EventMaxPerUpdate() {
		getAllEventInfoSimple();
		System.out.println("수용인원을 수정할 행사이름을 입력하시오 > ");
		String eventName = sc.nextLine();

		if (EventDAO.getInstance().getEventInfo(eventName) != null) {
			EventDTO event = EventDAO.getInstance().getEventInfo(eventName);

			if (UserService.userInfo.getUserId().equals(event.getEventOrg())) {
				System.out.println("바꿀 수용인원 > ");
				event.setEventmaxPer(Integer.parseInt(sc.nextLine()));

				int result = EventDAO.getInstance().EventMaxPerUpdate(event);
				if (result == 1) {
					System.out.println("수용인원 변경 완료했습니다");
				} else {
					System.out.println("존재하지않는 행사명 입니다");
				}
			} else {
				System.out.println("수정 권한이 없는 행사입니다");
			}
		} else {
			System.out.println("존재하지않는 행사명 입니다");
		}
	}

	// 행사 삭제
	public void EventDelete() {
		getAllEventInfoSimple();
		System.out.println("삭제할 행사 이름을 입력하시오 > ");
		String eventName = sc.nextLine();

		if (EventDAO.getInstance().getEventInfo(eventName) != null) {
			EventDTO event = EventDAO.getInstance().getEventInfo(eventName);

			if (UserService.userInfo.getUserId().equals(event.getEventOrg())) {
				int result = EventDAO.getInstance().EventDelete(eventName);

				if (result == 1) {
					System.out.println("삭제 완료했습니다");
				} else {
					System.out.println("삭제 실패했습니다");
				}
			} else {
				System.out.println("수정 권한이 없는 행사입니다");
			}
		} else {
			System.out.println("존재하지않는 행사명 입니다");
		}
	}

	// 예약자 등록
	public void reservationAdd() {
		getAllEventInfoSimple();
		System.out.println("예약하려는 행사 이름을 입력하시오 > ");
		String eventName = sc.nextLine();
		if (EventDAO.getInstance().getEventInfo(eventName) != null) {
			if (EventDAO.getInstance().eventInReservation1(eventName, UserService.userInfo.getUserId()) == null) {

				UserDTO user = UserService.userInfo;

				int result = EventDAO.getInstance().resevationAdd(user, eventName);

				if (result == 1) {
					System.out.println("예약 성공했습니다");
				} else {
					System.out.println("예약 실패했습니다");
				}
			} else {
				System.out.println("이미 예약이 완료된 행사입니다");
			}
		} else {
			System.out.println("존재하지않는 행사명 입니다");
		}
	}

	// 예약자 조회
	public void eventInReservation() {
		getAllEventInfoSimple();
		System.out.println("예약목록을 조회하려는 행사를 입력하시오 > ");
		String eventName = sc.nextLine();
		if (EventDAO.getInstance().getEventInfo(eventName) != null) {
			EventDTO event = EventDAO.getInstance().getEventInfo(eventName);

			List<UserDTO> list = EventDAO.getInstance().eventInReservation(eventName);
			for (UserDTO user : list) {
				System.out.println("예약자 아이디 : " + user.getUserId());
				System.out.println("예약자 이름 : " + user.getUserName());
				System.out.println("예약자 거주지 : " + user.getUserLocation());
				System.out.println("-------------------------");
			}
		} else {
			System.out.println("존재하지않는 행사명 입니다");
		}
	}

	// 행사 간단 조회
	public void getAllEventInfoSimple() {
		List<EventDTO> list = EventDAO.getInstance().getAllEventInfo();
		System.out.println("----행사목록----");
		for (EventDTO evnet : list) {
			System.out.println("● " + evnet.getEventName());

		}
		System.out.println("--------------");
	}

	// 예약 삭제
	public void reservationDelete() {
		getAllEventInfoSimple();
		System.out.println("예약취소할 행사 이름을 입력하시오 > ");
		String eventName = sc.nextLine();
		if (EventDAO.getInstance().getEventInfo(eventName) != null) {
			UserDTO user = UserService.userInfo;

			int result = EventDAO.getInstance().reservationDelete(eventName, user.getUserId());

			if (result == 1) {
				System.out.println("예약 취소 완료했습니다");
			} else {
				System.out.println("예약 정보가 존재하지 않습니다");
			}
		} else {
			System.out.println("존재하지않는 행사명 입니다");
		}
	}

}
