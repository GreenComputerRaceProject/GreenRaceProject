package ohs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.security.KeyStore.Entry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.Keymap;


public class ServerGameScreen extends JPanel{

   ArrayList<HorseClass> entrys = new ArrayList<HorseClass>();
   ArrayList<JLabel> hos = new ArrayList<JLabel>();
   ArrayList<Integer> running = new ArrayList<Integer>();
   ArrayList<Integer> grading = new ArrayList<Integer>();
   Map<Integer, String> grading2 = new HashMap<Integer, String>();
   
   

   ImageIcon goal = new ImageIcon("img/goalline.png");
   ImageIcon icon = new ImageIcon("img/horse_unscreen.gif");
   Font f1 = new Font("휴먼둥근체", Font.BOLD, 14);
   
   int x;
   boolean start = true;
   boolean finish = false;
   JLabel hs, goalline;
   JPanel gs;

   int [] grade = {1,2,3,4,5,6,7,8,9}; 
   int a;
   int max = 0;

   public ServerGameScreen() {
      
      
      goalline = new JLabel(goal);
      goalline.setBounds(1400, 0, 20, 500);
      
      setBounds(0, 70, 1585, 500);
      setBackground(Color.green);
      setLayout(null);

      
      add(goalline);
      
     

      new runing();
      
      

   }

   class runing extends Thread{

      public runing() {

         start();
      }

      @Override
      public void run() {
    	  
    while(start) { // 전체 게임 반복문
    	
    	x = 0; // 말의 x좌표 초기화
		a = 1; // 등수 초기화
    	
    	try {
    		
    		
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                  "jdbc:mariadb://localhost:3306/race_db",
                  "root",
                  "123456"
                  );

            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("select * from horse order by rand() limit 8");
            
            while(rs.next()) { // db 데이터 가져오는 반복문
           	 
               entrys.add(new HorseClass(rs.getString("hid"),rs.getString("hname"), // 엔트리 리스트에 말 정보넣기
                     rs.getString("speed"), rs.getString("firstspeed"), rs.getString("lastspeed"),
                     rs.getString("stamina"), rs.getString("state")));      

            
               JLabel hs = new JLabel(icon); 
               hs.setText(rs.getString("hname"));
               hs.setHorizontalTextPosition(JLabel.RIGHT);
               hs.setForeground(Color.black);
               hos.add(hs); // hs라벨로 hos 리스트에 말 추가

               running.add(x);
            }
            stmt.close();
            con.close();
            
            
            
         } catch (Exception e) {

         }
    	
    	
    	  
    	
    	System.out.println("경기 준비중!");
    		
    	for (int i = 0; i < hos.size(); i++) {
            hos.get(i).setSize(120,60);
            hos.get(i).setLocation(0, 60 * i);
            add(hos.get(i));
         }
    	
    	repaint(); // 패널 초기화
    	
    	  try {
			Thread.sleep(3000);
			start = false;
			finish = true;
			System.out.println("경기 시작!");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	  
         while(finish) { // 말들이 달리는 반복문
         
            
            for (int i = 0; i < hos.size(); i++) {

               if(running.get(i) >= 1335 && running.get(i) < 1430) { // 골인 구간
                  
                  running.set(i, 1460);
                  hos.get(i).setIcon(null);
                  hos.get(i).setLocation(running.get(i), 60 * i);
               //   hos.get(i).setFont(f1);
                  hos.get(i).setForeground(Color.black);
                  hos.get(i).setText(entrys.get(i).hname +"-" + a + "등");
                  hos.get(i).setHorizontalTextPosition(JLabel.CENTER);
                    
                  a++;
               }
               
               if( running.get(i) > 1200 && running.get(i) < 1430) { // lastspeed 구간
                  running.set(i, (int) (running.get(i) - entrys.get(i).speed));
                  running.set(i, (int) (running.get(i) + entrys.get(i).lastspeed));   
               }
               
               if (running.get(i) > 700 && running.get(i) < 1430) { // speed 구간
                  running.set(i, (int) (running.get(i) - entrys.get(i).fristspeed));
                  running.set(i, (int) (running.get(i) + entrys.get(i).speed));
               } 
               
               if(running.get(i) < 1430) { // 시작 -  firstspeed 구간
               running.set(i, (int) (running.get(i) + entrys.get(i).fristspeed));
               hos.get(i).setLocation((int)(running.get(i)), 60 * i);
               }
            }
            
                for (int i = 0; i < hos.size(); i++) { // 레이스 중계
                  grading.add(hos.get(i).getX());
                   grading2.put(hos.get(i).getX(), hos.get(i).getText());
               }
                
                 Collections.sort(grading, Collections.reverseOrder());
               Object [] mapkey = grading2.keySet().toArray();
               Arrays.sort(mapkey);
				
				  for (Integer nkey : grading2.keySet()) { System.out.print(grading2.get(nkey)
				  + ", "); }
				  
                grading2.clear();
                
                System.out.println();
                
               
                
                for (int i = 0; i < hos.size(); i++) { // 8등 말이 들어왔을시 경기종료 반복문
					if(hos.get(i).getText().equals(entrys.get(i).hname +"-" + 8 + "등")) {
						
						System.out.println("경기종료!");
					
						try {
							Thread.sleep(3000);
							for (int j = 0; j < 8; j++) {
								hos.get(0).setText(null);
								hos.remove(0);
								entrys.remove(0);
								running.remove(0);
							}
							
							start = true;
							finish = false;
							
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
            
            try {
               Thread.sleep(30);
            } catch (Exception e) {
               e.printStackTrace();
            } 
         }
      }
    
      }

   }

}