package khs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RaceListMain {
	
	
	ArrayList<Integer> al_rank = new ArrayList<Integer>();
	
	 public RaceListMain() {
		
			//1.DB driver 로딩
					try {
						Class.forName("org.mariadb.jdbc.Driver");
						
						//2.connection
						Connection con = DriverManager.getConnection(
								"jdbc:mariadb://localhost:3306/race_db", //URL  
								"race",                             	 //계정
								"123456"                                 //암호
								);
						
						//3.sql 구문 실행 객체 
						Statement stmt = con.createStatement();
						
						//4.sql 실행 결과 객체
						ResultSet rs = stmt.executeQuery("select * from user");
						
						//5.결과 실행
						while(rs.next()) { // rs.next() --> 실행 결과를 record단위로 한 행씩 커서가 넘어간다.
							System.out.println(rs.getString("name")); // 자료형에 맞춰 컬럼의 값을 가져온다.
							System.out.println(rs.getString("nickname")); 
							System.out.println(rs.getString("money")); 
							System.out.println(rs.getString("totgame")); 
							System.out.println(rs.getString("win")); 
							System.out.println(rs.getString("lose")); 
							System.out.println(rs.getString("rank")); 
							System.out.println("-------------------------"); 
//							raceListMain.rank = Integer.parseInt(rs.getString("rank"));
							
							
							al_rank.add(Integer.parseInt(rs.getString("rank")));
						}
							
						for (Integer i : al_rank) {
							System.out.println(i);
						}

						System.out.println(al_rank);
						
						
						//6.실행객체 해제
						rs.close();
						stmt.close();
						
						//7.연결객체 해제
						con.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	}
	
	public static void main(String[] args) {
		
		new RaceListMain();
	}

}
