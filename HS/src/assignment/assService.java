package assignment;

import java.util.List;
import java.util.Scanner;

import Event.EventDAO;
import user.UserDAO;
import user.UserDTO;
import user.UserService;

public class assService {
	Scanner sc = new Scanner(System.in);

	// 양도 등록
	public void assAdd() {
		assDTO ass = new assDTO();
		UserDTO user = UserService.userInfo;

		System.out.println("양도등록할 행사이름 입력 > ");
		String eventName = sc.nextLine();

		if (EventDAO.getInstance().eventInReservation1(eventName, user.getUserId()) != null) {
			// 양도테이블에 내 아이디가 없을때만 추가
			if (assDAO.getInstance().getAssInfo1(eventName, user.getUserId()) == null) {
				ass.setEventName(eventName);
				ass.setUserId(user.getUserId()); // 양도 등록한 아이디
				System.out.println("양도 글 제목 입력 > ");
				ass.setTitle(sc.nextLine());

				int result = assDAO.getInstance().assAdd(ass);

				if (result == 1) {
					System.out.println("양도 등록 성공");
				} else {
					System.out.println("양도 등록 실패");
				}

			} else {
				System.out.println("이미 양도가 등록되어 있습니다");
			}

		} else {
			System.out.println("양도할 예약 정보가 존재하지 않습니다");
		}

	}

	// 양도 조회
	public void getAllAssInfo() {
		List<assDTO> list = assDAO.getInstance().getAllAssInfo();

		for (assDTO ass : list) {
			System.out.println("양도 글 제목 " + ass.getTitle());
			System.out.println("양도 행사 : " + ass.getEventName());
			System.out.println("양도자 : " + ass.getUserId());
			System.out.println("----------------------------");
		}
	}

	// 조건양도조회
	public void getAssInfo() {
		System.out.println("양도 조회하려는 행사 이름 입력 > ");
		String eventName = sc.nextLine();
		List<assDTO> list = assDAO.getInstance().getAssInfo(eventName);

		for (assDTO ass : list) {
			System.out.println("양도 글 제목 : " + ass.getTitle());
			System.out.println("행사 이름 : " + ass.getEventName());
			System.out.println("양도자 아이디 : " + ass.getUserId());
			System.out.println("---------------------------");
		}
	}

	// 양도신청자 조회
	public void getAssAppInfo() {
		System.out.println("양도신청자를 조회하려는 게시글 제목 > ");
		String title = sc.nextLine();

		List<assAppDTO> list = assDAO.getInstance().getAssAppInfo(title);

		for (assAppDTO assApp : list) {
			System.out.println("신청자 아이디 : " + assApp.getAppUserId());
			System.out.println("신청자 이름 : " + assApp.getUserName());
			System.out.println("신청자 지역 : " + assApp.getUserLocaation());
			System.out.println("신청자 작성내용 : " + assApp.getApptext());
			System.out.println("----------------------------");
		}
	}

//양도 신청
	public void assAppAdd() {
		assDTO ass = new assDTO();
		UserDTO user = UserService.userInfo;
		System.out.println("양도등록할 게시글 제목 입력 > ");
		String title = sc.nextLine();

		if (assDAO.getInstance().assAppIdInfo(user.getUserId(), title) == null) {
			System.out.println("신청 내용 입력 > ");
			String appText = sc.nextLine();

			int result = assDAO.getInstance().assAppAdd(title, appText, user);

			if (result == 1) {
				System.out.println("양도 신청 성공");
			} else {
				System.out.println("양도 신청 실패");
			}
		} else {
			System.out.println("이미 양도 신청이 완료된 상태입니다");
		}
	}

	public void changeReservation() {
		assDTO ass = new assDTO();
		UserDTO user = UserService.userInfo;
		System.out.println("양도할 행사 이름 > ");
		String eventName = sc.nextLine();
		System.out.println("양도 할 글 제목 입력 >");
		String title = sc.nextLine();
		System.out.println("양도할 회원 아이디 입력 > ");
		String appUserId = sc.nextLine();
		
		if (assDAO.getInstance().getAssInfo1(eventName, user.getUserId()) != null) {
			assAppDTO assApp = assDAO.getInstance().assAppIdInfo(appUserId, title);

			int result = assDAO.getInstance().changeReservation(eventName, user.getUserId(), UserDAO.getInstance().userInfo(appUserId));

			if (result == 0) {
				System.out.println("양도 실패");
			} else {
				System.out.println("양도 성공");
				assDAO.getInstance().tableDelete(title);
				assDAO.getInstance().deletePage(eventName, UserService.userInfo.getUserId());
			}
		}else {
			System.out.println("등록된 양도가 없습니다");
		}
	}
}
