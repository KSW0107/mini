package user;

import java.util.Scanner;

public class UserService {
	public static UserDTO userInfo = null;
	Scanner sc = new Scanner(System.in);

	// 로그인
	public void login() {
		UserDTO user = new UserDTO();

		System.out.println("ID > ");
		String id = sc.nextLine();
		System.out.println("PW > ");
		String pw = sc.nextLine();

		user = UserDAO.getInstance().login(id);

		if (user != null) {
			if (pw.equals(user.getUserPw())) {
				System.out.println("로그인 성공");
				userInfo = user;

			} else {
				System.out.println("비밀번호가 틀렸습니다");
			}
		} else {
			System.out.println("아이디가 존재하지 않습니다");
		}

	}

	// 회원가입
	public void UserAdd() {
		UserDTO user = new UserDTO();

		System.out.println("가입할 회원 아이디 > ");
		user.setUserId(sc.nextLine());
		System.out.println("가입할 회원 비밀번호 > ");
		user.setUserPw(sc.nextLine());
		System.out.println("가입할 회원 이름 > ");
		user.setUserName(sc.nextLine());
		System.out.println("가입할 회원 거주지역 > ");
		user.setUserLocation(sc.nextLine());

		int result = UserDAO.getInstance().UserAdd(user);

		if (result == 1) {
			System.out.println("가입성공");
		} else {
			System.out.println("가입실패");
		}
	}

	// 회원수정
	public void UserPwUpdate() {
		UserDTO user = new UserDTO();

		System.out.println("비밀번호를 수정할 아이디 > ");
		user.setUserId(sc.nextLine());
		System.out.println("바꿀 비밀번호 > ");
		String pw = sc.nextLine();
		System.out.println("바꿀 비밀 번호 재입력");
		user.setUserPw(sc.nextLine());
		if (pw.equals(user.getUserPw())) {
			int result = UserDAO.getInstance().UserPwUpdate(user);
			if (result == 1) {
				System.out.println("비밀번호 변경 완료");
			}
		} else {
			System.out.println("입력된 비밀번호가 다릅니다");
		}
	}

	public void UserNameUpdate() {
		UserDTO user = new UserDTO();

		System.out.println("이름을 수정할 아이디 > ");
		user.setUserId(sc.nextLine());
		System.out.println("바꿀 이름 > ");
		user.setUserName(sc.nextLine());

		int result = UserDAO.getInstance().UserNameUpdate(user);
		
		if (result == 1) {
			System.out.println("이름 변경 완료");
		} else {
			System.out.println("이름 변경 실패");
		}
	}
	
	public void UserLocationUpdate() {
		UserDTO user = new UserDTO();

		System.out.println("거주지역을 수정할 아이디 > ");
		user.setUserId(sc.nextLine());
		System.out.println("바꿀 거주지역 > ");
		user.setUserLocation(sc.nextLine());

		int result = UserDAO.getInstance().UserLocationUpdate(user);
		
		if (result == 1) {
			System.out.println("거주지역 변경 완료");
		} else {
			System.out.println("거주지역 변경 실패");
		}
	}

}
