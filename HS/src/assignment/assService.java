package assignment;

import java.util.List;
import java.util.Scanner;

public class assService {
	Scanner sc = new Scanner(System.in);
	
	public void assAdd() {
		assDTO ass = new assDTO();
		
		System.out.println("양도등록할 행사이름 입력 > ");
		ass.setEventName(sc.nextLine());
		System.out.println("양도하는 사용자 아이디 입력 > ");
		ass.setUserId(sc.nextLine());
		
		int result = assDAO.getInstance().assAdd(ass);
		
		if(result == 1) {
			System.out.println("양도 등록 성공");
		}else {
			System.out.println("양도 등록 실패");
		}
	}
	
	//양도 조회
	public void getAllAssInfo() {
		List<assDTO> list = assDAO.getInstance().getAllAssInfo();
		
		for(assDTO ass : list) {
			System.out.println("양도 행사 : "+ ass.getEventName());
			System.out.println("양도자 : "+ass.getUserId());
			System.out.println("----------------------------");
		}
	}
	
	public void getAssInfo() {
		System.out.println("양도 조회하려는 행사 이름 입력 > " );
		String eventName = sc.nextLine();
		List<assDTO> list = assDAO.getInstance().getAssInfo(eventName);
		
		for (assDTO ass : list) {
			System.out.println("행사 이름 : " + ass.getEventName());
			System.out.println("양도자 아이디 : " + ass.getUserId());
			System.out.println("---------------------------");
		}
	}
	
}
