package kkj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import kkj.BattingDTO;

public class BattingDataMain extends JFrame implements ActionListener {
	// 프레임, 레이블, 버튼을 만들고 레이블이랑 버튼을 프레임에 붙인다 
	//jTextField에 값을 입력하고 엔터키를 햇을시 레이블에 값이 반영
	
	int[] horse_num = {1,2,3,4,5,6,7,8};
	ArrayList<String> nickname = new ArrayList<String>();
	ArrayList<Long> gambling_money, tot_money = new ArrayList<Long>();
	ArrayList<BattingDTO> data = new ArrayList<BattingDTO>();
	
	Connection con = null;	
	Statement stmt = null;	
	ResultSet rs = null;	
	
	String sql = null;
	
	JButton btn;	
	JTextField field;
	JLabel label; 
	
	BattingDataMain(){
//		super();
//		Dimension dms = new Dimension(400,100);
//		setLocation(750, 500);
//		setPreferredSize(dms);
//		pack();
//		
//		field = new JTextField();
//		label = new JLabel("배팅금액");
//		label.setHorizontalAlignment(SwingConstants.CENTER);
//		label.setVerticalAlignment(SwingConstants.CENTER);
//		
//		btn = new JButton("확인");
//		btn.addActionListener(this);
//		
//		add(btn, BorderLayout.SOUTH);
//		add(label, BorderLayout.NORTH);
//		add(field, BorderLayout.CENTER);
//		
//		setResizable(false);
//		setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
	/*	
		for (int i = 0; i < 27; i++) {
			GridLayout gl = new GridLayout(3,8,0,0);
			setLayout(gl);
			JLabel label = new JLabel("1");
			add(label);
			label.setHorizontalAlignment(JLabel.CENTER);
		}
			레이블번호를 하나하나바꿀수있는 방법
	*/	
	}
	
	
	public ArrayList<BattingDTO> BattingDataMain() {

		try {

			Class.forName("org.mariadb.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db", "race", "123456");
			
			stmt = con.createStatement();
			
			 ResultSet rs = stmt.executeQuery("select * from dansik");
			 
				while(rs.next()) {
					
					BattingDTO dto = new BattingDTO();
					dto.horse_num = rs.getInt("horse_num");
					dto.nickname = rs.getString("nickname");
					dto.gambling_money = rs.getLong(field.getText());
					dto.tot_money = rs.getLong("tot_money");
					
					data.add(dto);
					
				}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return data;
	
	}

	 void close() {
		 if(rs!=null) try {rs.close();} catch (SQLException e) {}
		 if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		 if(con!=null) try {con.close();} catch (SQLException e) {}		
	}

	public static void main(String[] args) {

		new BattingDataMain();
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(field.getText()); 
		
	}

}
