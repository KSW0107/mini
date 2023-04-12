package assignment;

import java.util.List;
import java.util.Scanner;

import Event.EventDAO;
import Event.EventService;
import user.UserDAO;
import user.UserDTO;
import user.UserService;

public class assService {
	Scanner sc = new Scanner(System.in);
	EventService es = new EventService();

	// 양도하기 등록
	public void assAdd() {
		es.getAllEventInfoSimple();
		assDTO ass = new assDTO();
		UserDTO user = UserService.userInfo;

		System.out.println("양도등록할 행사이름을 입력하시오 > ");
		String eventName = sc.nextLine();
		if (EventDAO.getInstance().getEventInfo(eventName) != null) {
			if (EventDAO.getInstance().eventInReservation1(eventName, user.getUserId()) != null) {
				// 양도테이블에 내 아이디가 없을때만 추가
				if (assDAO.getInstance().getAssInfo1(eventName, user.getUserId()) == null) {
					ass.setEventName(eventName);
					ass.setUserId(user.getUserId()); // 양도 등록한 아이디
					System.out.println("양도 글 제목을 입력하시오 > ");
					ass.setTitle(sc.nextLine());

					int result = assDAO.getInstance().assAdd(ass);

					if (result == 1) {
						System.out.println("양도하기 등록 성공했습니다");
					} else {
						System.out.println("양도하기 등록 실패했습니다");
					}

				} else {
					System.out.println("이미 양도하기가 등록되어 있습니다");
				}

			} else {
				System.out.println("양도할 예약 정보가 존재하지 않습니다");
			}
		} else {
			System.out.println("존재하지않는 행사명 입니다");
		}
	}

	// 양도 조회
	public void getAllAssInfo() {
		List<assDTO> list = assDAO.getInstance().getAllAssInfo();

		for (assDTO ass : list) {
			System.out.println("양도 글 제목 : " + ass.getTitle());
			System.out.println("양도 행사 : " + ass.getEventName());
			System.out.println("양도자 : " + ass.getUserId());
			System.out.println("----------------------------");
		}
	}

	// 조건양도조회
	public void getAssInfo() {
		es.getAllEventInfoSimple();
		System.out.println("양도하기 조회하려는 행사 이름을 입력하시오 > ");
		String eventName = sc.nextLine();
		if (EventDAO.getInstance().getEventInfo(eventName) != null) {
			List<assDTO> list = assDAO.getInstance().getAssInfo(eventName);
			if (list != null) {
				for (assDTO ass : list) {
					System.out.println("양도 글 제목 : " + ass.getTitle());
					System.out.println("행사 이름 : " + ass.getEventName());
					System.out.println("양도자 아이디 : " + ass.getUserId());
					System.out.println("---------------------------");
				}
			} else {
				System.out.println("해당 행사는 등록된 양도하기가 존재하지 않습니다");
			}
		} else {
			System.out.println("존재하지않는 행사명 입니다");
		}
	}

	// 양도하기 삭제
	public void deleteAss() {
		getAllAssInfoSimple();
		System.out.println("삭제하려는 양도하기 글 이름을 입력하시오 > ");
		String title = sc.nextLine();

		if (assDAO.getInstance().getAssInfoTitle(title) != null) {
			if (UserService.userInfo.getUserId().equals(assDAO.getInstance().getAssInfoTitle(title).getUserId())) {

				if (assDAO.getInstance().deleteAss(title) != 0) {
					System.out.println("삭제를 완료했습니다");
					assDAO.getInstance().tableDelete(title);
				} else {
					System.out.println("삭제를 실패했습니다");
				}

			} else {
				System.out.println("권한이 없는 양도하기 입니다");
			}
		} else {
			System.out.println("존재하지 않는 게시글입니다");
		}
	}

	// 양도받기 신청자 조회
	public void getAssAppInfo() {
		getAllAssInfoSimple();
		System.out.println("양도신청자를 조회하려는 게시글 제목을 입력하시오 > ");
		String title = sc.nextLine();

		if (assDAO.getInstance().getAssInfoTitle(title) != null) {
			List<assAppDTO> list = assDAO.getInstance().getAssAppInfo(title);
			if (list != null) {
				for (assAppDTO assApp : list) {
					System.out.println("신청자 아이디 : " + assApp.getAppUserId());
					System.out.println("신청자 이름 : " + assApp.getUserName());
					System.out.println("신청자 지역 : " + assApp.getUserLocaation());
					System.out.println("신청자 작성내용 : " + assApp.getApptext());
					System.out.println("----------------------------");
				}
			} else {
				System.out.println("양도받기가 존재하지않는 게시글입니다");
			}
		} else {
			System.out.println("존재하지않는 게시글 입니다");
		}
	}

	// 양도받기 신청
	public void assAppAdd() {
		getAllAssInfoSimple();
		assDTO ass = new assDTO();
		UserDTO user = UserService.userInfo;
		System.out.println("양도받기를 등록할 게시글 제목을 입력하시오 > ");
		String title = sc.nextLine();
		if (assDAO.getInstance().getAssInfoTitle(title) != null) {
			if (assDAO.getInstance().assAppIdInfo(user.getUserId(), title) == null) {
				System.out.println("신청 내용을 입력하시오 > ");
				String appText = sc.nextLine();

				int result = assDAO.getInstance().assAppAdd(title, appText, user);

				if (result == 1) {
					System.out.println("양도받기 신청 성공했습니다");
				} else {
					System.out.println("양도받기 신청 실패했습니다");
				}
			} else {
				System.out.println("이미 양도받기 신청이 완료된 상태입니다");
			}
		} else {
			System.out.println("존재하지않는 게시글 입니다");
		}
	}

	// 양도 신청 시 간단 정보
	public void getAllAssInfoSimple() {
		List<assDTO> list = assDAO.getInstance().getAllAssInfo();

		for (assDTO ass : list) {
			System.out.print("제목:" + ass.getTitle());
			System.out.print(" | 행사명:" + ass.getEventName());
			System.out.print(" | 양도자:" + ass.getUserId());
			System.out.println("\n---------------------------------");
		}
	}

	// 양도 수락
	public void changeReservation() {
		getAllAssInfoSimple();
		assDTO ass = new assDTO();
		UserDTO user = UserService.userInfo;
		System.out.println("양도하기 할 게시글 제목을 입력하시오 >");
		String title = sc.nextLine();
		if (assDAO.getInstance().getAssInfoTitle(title) != null) {
			getAssAppInfoSimple(title);
			System.out.println("양도할 회원 아이디를 입력하시오 > ");
			String appUserId = sc.nextLine();
			String eventName = assDAO.getInstance().getAssInfoTitle(title).getEventName();

			if (assDAO.getInstance().assAppIdInfo(appUserId, title) != null) {

				if (assDAO.getInstance().getAssInfo1(eventName, user.getUserId()) != null) {
					assAppDTO assApp = assDAO.getInstance().assAppIdInfo(appUserId, title);

					int result = assDAO.getInstance().changeReservation(eventName, user.getUserId(),
							UserDAO.getInstance().userInfo(appUserId));

					if (result == 0) {
						System.out.println("양도수락 실패했습니다");
					} else {
						System.out.println("양도수락 성공했습니다");
						assDAO.getInstance().tableDelete(title);
						assDAO.getInstance().deletePage(eventName, UserService.userInfo.getUserId());
					}
				} else {
					System.out.println("나의 양도하기가 아닙니다");
				}
			} else {
				System.out.println("선택 유저는 양도받기 신청자 명단에 없는 유저입니다");
			}
		} else {
			System.out.println("존재하지 않는 게시글입니다");
		}
	}

	// 양도 수락시 신청자 간단 정보
	public void getAssAppInfoSimple(String title) {
		List<assAppDTO> list = assDAO.getInstance().getAssAppInfo(title);
		System.out.println("-----------양도받기 신청자 명단------------");
		for (assAppDTO assApp : list) {
			System.out.print("아이디 : " + assApp.getAppUserId());
			System.out.print(" 이름 : " + assApp.getUserName());
			System.out.print(" 작성내용 : " + assApp.getApptext());
			System.out.println("\n----------------------------");
		}
	}
}
