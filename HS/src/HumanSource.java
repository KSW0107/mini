import java.util.Scanner;

import Event.EventService;
import assignment.assService;
import user.UserService;

public class HumanSource {
	Scanner sc = new Scanner(System.in);
	int menu = 0;
	int infoMenu = 0;
	int udtmenu = 0;
	UserService user = new UserService();
	EventService es = new EventService();

	public HumanSource() {
		Job();
	}

	private void Job() {
		while (true) {
			menu();
			if (UserService.userInfo == null) {
				if (menu == 1) {
					user.login();
				} else if (menu == 2) {
					user.UserAdd();
				} else if (menu == 3) {
					System.out.println("프로그램 종료");
					break;
				} else {
					System.out.println("잘못된 입력입니다");
				}
			} else {
				if (menu == 1) {
					eventMenu();
				} else if (menu == 2) {
					reservationMenu();
				} else if (menu == 3) {
					assignmentMenu();
				} else if (menu == 4) {	
					System.out.println("프로그램 종료");
					break;
				}
			}
		}
	}

	private void menu() {
		if (UserService.userInfo == null) {
			System.out.println("1. 로그인 | 2. 회원가입 | 3. 종료 ");
			System.out.println("입력 > ");
			menu = Integer.parseInt(sc.nextLine());
		} else {
			System.out.println("1. 행사 관리 | 2. 예약관리 | 3. 양도 관리 | 4. 종료");
			System.out.println("입력 > ");
			menu = Integer.parseInt(sc.nextLine());
		}
	}

	private void infoMenu() {
		System.out.println("1. 전체 행사 조회 | 2. 행사 상세 조회 | 3. 조건 별 행사 조회");
		System.out.println("입력 > ");
		infoMenu = Integer.parseInt(sc.nextLine());

		if (infoMenu == 1) {
			es.getAllEventInfo();
		} else if (infoMenu == 2) {
			es.getEventInfo();
		} else if (infoMenu == 3) {
			searchMenu();
		} else if (infoMenu == 4) {

		} else {
			System.out.println("잘못된 메뉴 선택입니다.");
		}

	}

	private void updateMenu() {
		System.out.println("1. 장소 수정 | 2. 날짜 수정 | 3. 시간 수정 | 4.카테고리 수정 | 5. 수용인원 수정 | 6. 뒤로가기");
		System.out.println("입력");
		udtmenu = Integer.parseInt(sc.nextLine());
		// 장소 날짜 시간 카테고리 수용인원

		switch (udtmenu) {
		case 1:
			es.EventPlaceUpdate();
			break;
		case 2:
			es.EventDateUpdate();
			break;
		case 3:
			es.EventTimeUpdate();
			break;
		case 4:
			es.EventCategoryUpdate();
			break;
		case 5:
			es.EventMaxPerUpdate();
			break;
		case 6:
			break;
		default:
			System.out.println("입력 오류");

		}
	}

	private void searchMenu() {
		System.out.println("1. 장소별 | 2. 날짜별 | 3. 카테고리별 | 4. 뒤로가기");
		System.out.println("입력 > ");
		int search = Integer.parseInt(sc.nextLine());

		switch (search) {
		case 1:
			es.getPlaceInfo();
			break;
		case 2:
			es.getDateInfo();
			break;
		case 3:
			es.getCategoryInfo();
			break;
		case 4:
			break;
		}
	}
	
	
	private void reservationMenu() {
		System.out.println("1. 예약하기 | 2. 예약취소 | 3. 행사 별 참여 인원 조회 | 4. 뒤로가기");
		int reservation = Integer.parseInt(sc.nextLine());
		
		switch (reservation) {
		case 1:
			es.reservationAdd();
			break;
		case 2:
			es.reservationDelete();
			break;
		case 3:
			es.eventInReservation();
			break;
		case 4:
			break;
		}
		
	}
	private void eventMenu() {
		System.out.println("1.행사등록 | 2. 행사조회 | 3.행사수정 | 4. 행사취소 | 5.뒤로가기");
		int userMenu = Integer.parseInt(sc.nextLine());
		
		switch(userMenu) {
		case 1:
			es.eventAdd();
			break;
		case 2:
			infoMenu();
			break;
		case 3:
			updateMenu();
			break;
		case 4:
			es.EventDelete();
			break;
		case 5:
			break;
		}
	}
	
	private void assignmentMenu() {
		System.out.println("1. 양도등록 | 2.전체양도조회 | 3.조건양도조회 | 4.양도신청 | 5. 양도수락 | 6. 뒤로가기");
		int assMenu = Integer.parseInt(sc.nextLine());
		assService as = new assService(); 
		
		switch(assMenu) {
		case 1:
			as.assAdd();
			break;
		case 2:
			as.getAllAssInfo();
			break;
		case 3:
			as.getAssInfo();
			break;
		case 4:
			
			break;
		case 5:
			
			break;
		case 6:
			break;
		}
	}
}
