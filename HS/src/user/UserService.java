package user;

import java.util.Scanner;

public class UserService {
	public static UserDTO userInfo = null; // 로그인 정보
	Scanner sc = new Scanner(System.in);

	// 로그인
	public void login() {
		UserDTO user = new UserDTO();

		System.out.println("ID 를 입력하시오 > ");
		String id = sc.nextLine();
		System.out.println("PW 를 입력하시오 > ");
		String pw = sc.nextLine();

		user = UserDAO.getInstance().login(id);

		if (user != null) {
			if (pw.equals(user.getUserPw())) {
				System.out.println("로그인 성공");
				System.out.println("환영합니다! "+user.getUserName()+"님");
				userInfo = user;

			} else {
				System.out.println("비밀번호가 틀렸습니다");
			}
		} else {
			System.out.println("존재하지 않는 아이디입니다");
		}
	}

	//로그아웃
	public void logout() {
		if(userInfo != null) {
			userInfo = null;
			System.out.println("로그아웃 되었습니다.");
		}
	}
	
	// 회원가입
	public void UserAdd() {
		UserDTO user = new UserDTO();

		System.out.println("가입할 회원 아이디를 입력하시오 > ");
		String id = sc.nextLine();
		
		if(UserDAO.getInstance().userInfo(id) == null) {
			user.setUserId(id);
			
			System.out.println("가입할 회원 비밀번호를 입력하시오 > ");
			user.setUserPw(sc.nextLine());
			System.out.println("가입할 회원 이름을 입력하시오 > ");
			user.setUserName(sc.nextLine());
			System.out.println("가입할 회원 거주지역을 입력하시오 > ");
			user.setUserLocation(sc.nextLine());
			
			
			int result = UserDAO.getInstance().UserAdd(user);			
			if (result == 1) {
				System.out.println("가입이 완료되었습니다!");
			}else {
				System.out.println("가입이 실패하였습니다");
			}
			
		}else {
			System.out.println("이미 존재하는 아이디 입니다");
		}
		

	}

	// 내 정보 조회
	public void myInfo() {
		System.out.println("비밀번호를 입력하시오 >");
		String pw = sc.nextLine();

		if (userInfo.getUserPw().equals(pw)) {
			System.out.println("내 아이디 : " + userInfo.getUserId());
			System.out.println("내 비밀번호 : " + userInfo.getUserPw());
			System.out.println("내 이름 : " + userInfo.getUserName());
			System.out.println("내 거주지 : " + userInfo.getUserLocation());
		} else {
			System.out.println("비밀번호가 틀렸습니다.");
		}
	}

	// 회원수정
	//비밀번호 수정
	public void UserPwUpdate() {
		UserDTO user = new UserDTO();

		System.out.println("현재 비밀번호를 입력하시오 > ");
		String oldpw = sc.nextLine();

		if (userInfo.getUserPw().equals(oldpw)) {
			user.setUserId(userInfo.getUserId());
			System.out.println("바꿀 비밀번호를 입력하시오 > ");
			String newpw = sc.nextLine();
			System.out.println("바꿀 비밀 번호 재입력하시오 >");
			String newpw1 = sc.nextLine();
			if (newpw.equals(newpw1)) {
				user.setUserPw(newpw);
				int result = UserDAO.getInstance().UserPwUpdate(user);
				if (result == 1) {
					System.out.println("비밀번호 변경 완료했습니다");
					userInfo.setUserPw(newpw);
				}
			} else {
				System.out.println("입력된 비밀번호가 다릅니다");
			}
		} else {
			System.out.println("비밀번호가 클렸습니다");
		}

	}

	//이름 수정
	public void UserNameUpdate() {
		UserDTO user = new UserDTO();

		System.out.println("현재 비밀번호를 입력하시오 > ");
		String oldpw = sc.nextLine();

		if (userInfo.getUserPw().equals(oldpw)) {
			user.setUserId(userInfo.getUserId());
			System.out.println("바꿀 이름을 입력하시오 > ");
			String newName = sc.nextLine();
			user.setUserName(newName);

			int result = UserDAO.getInstance().UserNameUpdate(user);

			if (result == 1) {
				System.out.println("이름 변경 완료했습니다");
				userInfo.setUserName(newName);
			} else {
				System.out.println("이름 변경 실패했습니다");
			}
		} else {
			System.out.println("비밀번호가 틀렸습니다");
		}
	}

	//거주지 수정
	public void UserLocationUpdate() {
		UserDTO user = new UserDTO();

		System.out.println("현재 비밀번호를 입력하시오 > ");
		String oldpw = sc.nextLine();

		if (userInfo.getUserPw().equals(oldpw)) {
			user.setUserId(userInfo.getUserId());
			System.out.println("바꿀 거주지역을 입력하시오 > ");
			String newLocation = sc.nextLine();
			user.setUserLocation(newLocation);

			int result = UserDAO.getInstance().UserLocationUpdate(user);

			if (result == 1) {
				System.out.println("거주지역 변경 완료했습니다");
				userInfo.setUserLocation(newLocation);
			} else {
				System.out.println("거주지역 변경 실패했습니다");
			}
		} else {
			System.out.println("비밀번호가 틀렸습니다");
		}
	}

}
