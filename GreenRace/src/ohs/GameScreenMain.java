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


public class GameScreenMain extends JPanel{

   ArrayList<HorseClass> entrys = new ArrayList<HorseClass>();
   ArrayList<JLabel> hos = new ArrayList<JLabel>();
   ArrayList<Integer> running = new ArrayList<Integer>();
   ArrayList<Integer> grading = new ArrayList<Integer>();
   Map<Integer, String> grading2 = new HashMap<Integer, String>();
   
   Font f1 = new Font("휴먼둥근체", Font.BOLD, 14);
   
   int x = 0;
   JLabel hs, goalline;
   JPanel gs;

   int [] grade = {1,2,3,4,5,6,7,8,9}; 
   int a = 1;
   int max = 0;

   public GameScreenMain() {
      
      ImageIcon goal = new ImageIcon("img/goalline.png");
      ImageIcon icon = new ImageIcon("img/horse_unscreen.gif");
      
      goalline = new JLabel(goal);
      goalline.setBounds(1400, 0, 20, 500);
      
      try {
         Class.forName("org.mariadb.jdbc.Driver");

         Connection con = DriverManager.getConnection(
               "jdbc:mariadb://localhost:3306/race_db",
               "root",
               "123456"
               );

         Statement stmt = con.createStatement();
         
         ResultSet rs = stmt.executeQuery("select * from horse order by rand() limit 8");
         
         while(rs.next()) {
        	 
            entrys.add(new HorseClass(rs.getString("hid"),rs.getString("hname"), 
                  rs.getString("speed"), rs.getString("firstspeed"), rs.getString("lastspeed"),
                  rs.getString("stamina"), rs.getString("state")));      

         
            JLabel hs = new JLabel(icon);
            hs.setText(rs.getString("hname"));
            hs.setHorizontalTextPosition(JLabel.RIGHT);
            hs.setForeground(Color.black);
            hos.add(hs);

            running.add(x);
         }
         stmt.close();
         con.close();
      } catch (Exception e) {

      }
      
     
      setBounds(0, 70, 1585, 500);
      setBackground(Color.red);
      setLayout(null);

      
      add(goalline);
      
      for (int i = 0; i < hos.size(); i++) {
         hos.get(i).setSize(120,60);
         hos.get(i).setLocation(0, 60 * i);
         add(hos.get(i));
      }

      new runing();
      
      

   }

   class runing extends Thread{

      public runing() {

         start();
      }

      @Override
      public void run() {
         
         while(true) {
         
            
            for (int i = 0; i < hos.size(); i++) {

               if(running.get(i) >= 1305 && running.get(i) < 1430) {
                  
                  running.set(i, 1460);
                  hos.get(i).setIcon(null);
                  hos.get(i).setLocation(running.get(i), 60 * i);
               //   hos.get(i).setFont(f1);
                  hos.get(i).setForeground(Color.black);
                  hos.get(i).setText(entrys.get(i).hname +"-" + a + "등");
                  hos.get(i).setHorizontalTextPosition(JLabel.CENTER);
                    
                  a++;
               }
               
               if( running.get(i) > 1200 && running.get(i) < 1430) {
                  running.set(i, (int) (running.get(i) - entrys.get(i).speed));
                  running.set(i, (int) (running.get(i) + entrys.get(i).lastspeed));   
               }
               
               if (running.get(i) > 700 && running.get(i) < 1430) {
                  running.set(i, (int) (running.get(i) - entrys.get(i).fristspeed));
                  running.set(i, (int) (running.get(i) + entrys.get(i).speed));
               } 
               
               if(running.get(i) < 1430) {
               running.set(i, (int) (running.get(i) + entrys.get(i).fristspeed));
               hos.get(i).setLocation((int)(running.get(i)), 60 * i);
               }
            }
            
                for (int i = 0; i < hos.size(); i++) {
               //   grading.add(hos.get(i).getX());
                   grading2.put(hos.get(i).getX(), hos.get(i).getText());
               }
                
               //  Collections.sort(grading, Collections.reverseOrder());
               Object [] mapkey = grading2.keySet().toArray();
               Arrays.sort(mapkey);
				/*
				 * for (Integer nkey : grading2.keySet()) { System.out.print(grading2.get(nkey)
				 * + ", "); }
				 */ 
                grading2.clear();
                
              //  System.out.println();
            
            try {
               Thread.sleep(30);
            } catch (Exception e) {
               e.printStackTrace();
            } 
         }
      }

   }



   public static void main(String[] args) {


      new GameScreenMain();



   }

}