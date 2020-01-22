import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;



/**
 * This class makes a simple card game.
 * 
 * @author Lin Yizhou
 *
 */
public class GUICardGame {
	ArrayList<Integer> stack=new ArrayList<Integer>();
	ArrayList<Integer> player=new ArrayList<Integer>();
	ArrayList<Integer> dealer=new ArrayList<Integer>();
	
	int userbet;
	int total=100;
	double input;
	boolean started=false, ended=false;
	int chance=2;
	boolean chance1=false, chance2=false, chance3=false;
	
	JMenuBar menuBar = new JMenuBar();
	JMenu control = new JMenu("Control");
	JMenu Help = new JMenu("Help");
	JMenuItem help = new JMenuItem("Instruction");
	JMenuItem menuItem = new JMenuItem("Exit");
	
	JLabel label_Image1 = new JLabel();
	JLabel label_Image2 = new JLabel();
	JLabel label_Image3 = new JLabel();
	JLabel label_Image4 = new JLabel();
	JLabel label_Image5 = new JLabel();
	JLabel label_Image6 = new JLabel();
	
	JButton btn_rpcard1 = new JButton ("Replace Card 1");
	JButton btn_rpcard2 = new JButton ("Replace Card 2");
	JButton btn_rpcard3 = new JButton ("Replace Card 3");
	JButton btn_start = new JButton ("Start");
	JButton btn_result = new JButton ("Result");
	
	JLabel label_bet = new JLabel("Bet: $");
	JLabel label_info = new JLabel("Please place your bet!");
	JLabel label_money = new JLabel("Amount of money you have: $"+total);
	
	JTextField txt_inputbet = new JTextField (10);
	
	ImageIcon Image1 = new ImageIcon("card_back.gif");
	ImageIcon Image2 = new ImageIcon("card_back.gif");
	ImageIcon Image3 = new ImageIcon("card_back.gif");
	ImageIcon Image4 = new ImageIcon("card_back.gif");
	ImageIcon Image5 = new ImageIcon("card_back.gif");
	ImageIcon Image6 = new ImageIcon("card_back.gif");
	
	
	public static void main(String[] args) {
		GUICardGame t=new GUICardGame();
		t.go();

	}
	
	/**
	 * This method initializes the card game.
	 * 
	 */
	
	public void go() {
		
		for (int i=11;i<20;i++) {
			stack.add(i);
		}
		for (int i=21;i<30;i++) {
			stack.add(i);
		}
		for (int i=31;i<40;i++) {
			stack.add(i);
		}
		for (int i=41;i<50;i++) {
			stack.add(i);
		}
		for (int i=110;i<114;i++) {
			stack.add(i);
		}
		for (int i=210;i<214;i++) {
			stack.add(i);
		}
		for (int i=310;i<314;i++) {
			stack.add(i);
		}
		for (int i=410;i<414;i++) {
			stack.add(i);
		}
		
		control.add(menuItem);
		menuBar.add(control);
		Help.add(help);
		menuBar.add(Help);
		
		JPanel MainPanel = new JPanel();
		JPanel DealerPanel = new JPanel();
		JPanel PlayerPanel = new JPanel();
		JPanel RpCardBtnPanel = new JPanel();
		JPanel ButtonPanel = new JPanel();
		JPanel InfoPanel = new JPanel();
		
		MainPanel.setLayout(new GridLayout(5,1));
		MainPanel.add(DealerPanel);
		MainPanel.add(PlayerPanel);
		MainPanel.add(RpCardBtnPanel);
		MainPanel.add(ButtonPanel);
		MainPanel.add(InfoPanel);
		
		label_Image1.setIcon(Image1);
		label_Image2.setIcon(Image2);
		label_Image3.setIcon(Image3);
		label_Image4.setIcon(Image4);
		label_Image5.setIcon(Image5);
		label_Image6.setIcon(Image6);
		DealerPanel.add(label_Image1);
		DealerPanel.add(label_Image2);
		DealerPanel.add(label_Image3);
		PlayerPanel.add(label_Image4);
		PlayerPanel.add(label_Image5);
		PlayerPanel.add(label_Image6);
		
		
		RpCardBtnPanel.add(btn_rpcard1);
		RpCardBtnPanel.add(btn_rpcard2);
		RpCardBtnPanel.add(btn_rpcard3);
		
		ButtonPanel.add(label_bet);
		ButtonPanel.add(txt_inputbet);
		ButtonPanel.add(btn_start);
		ButtonPanel.add(btn_result);
		
		InfoPanel.add(label_info);
		InfoPanel.add(label_money); 
		
		DealerPanel.setBackground(Color.green);
		PlayerPanel.setBackground(Color.green);
	 	RpCardBtnPanel.setBackground(Color.green);
		JFrame frame = new JFrame();
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(MainPanel);
		frame.setTitle("A Simple Card Game");
		frame.setSize(400, 700);
		frame.setVisible(true);
		
		btn_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!started && total!=0 && !ended) {
					input = Double.parseDouble(txt_inputbet.getText());
					if(input>0 && input-Math.floor(input)==0) {
						userbet=(int)input;
						if(userbet<=total) {
							Collections.shuffle(stack);
							for (int i=0;i<3;i++) {
								dealer.add(stack.get(0));
								stack.remove(0);
							}
							for (int i=0;i<3;i++) {
								player.add(stack.get(0));
								stack.remove(0);
							}
							PlayerPanel.removeAll();
							Image4= new ImageIcon("card_"+player.get(0)+".gif");
							Image5= new ImageIcon("card_"+player.get(1)+".gif");
							Image6= new ImageIcon("card_"+player.get(2)+".gif");
							label_Image4.setIcon(Image4);
							label_Image5.setIcon(Image5);
							label_Image6.setIcon(Image6);
							PlayerPanel.add(label_Image4);
							PlayerPanel.add(label_Image5);
							PlayerPanel.add(label_Image6);
							label_info.setText("Your current bet is: $"+userbet);
							frame.repaint();
							started=true;
						}else {
							JOptionPane.showMessageDialog(MainPanel, "WARNING: Please input an amount smaller than your money", "Message", JOptionPane.INFORMATION_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(MainPanel, "WARNING: The bet you place must be a positive integer!", "Message", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		 });
		
		btn_rpcard1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chance>0 && started && !chance1) {
					stack.add(player.get(0));
					player.remove(0);
					player.add(0, stack.get(0));
					stack.remove(0);
					PlayerPanel.removeAll();;
					Image4= new ImageIcon("card_"+player.get(0)+".gif");
					label_Image4.setIcon(Image4);
					PlayerPanel.add(label_Image4);
					PlayerPanel.add(label_Image5);
					PlayerPanel.add(label_Image6);
					frame.repaint();
					chance-=1;
					chance1=true;
				}
			}
		});
		
		btn_rpcard2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chance>0 && started && !chance2) {
					stack.add(player.get(1));
					player.remove(1);
					player.add(1, stack.get(0));
					stack.remove(0);
					PlayerPanel.removeAll();
					Image5= new ImageIcon("card_"+player.get(1)+".gif");
					label_Image5.setIcon(Image5);
					PlayerPanel.add(label_Image4);
					PlayerPanel.add(label_Image5);
					PlayerPanel.add(label_Image6);
					frame.repaint();
					chance-=1;
					chance2=true;
				}
			}
		});
		
		btn_rpcard3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chance>0 && started && !chance3) {
					stack.add(player.get(2));
					player.remove(2);
					player.add(stack.get(0));
					stack.remove(0);
					PlayerPanel.removeAll();
					Image6= new ImageIcon("card_"+player.get(2)+".gif");
					label_Image6.setIcon(Image6);
					PlayerPanel.add(label_Image4);
					PlayerPanel.add(label_Image5);
					PlayerPanel.add(label_Image6);
					frame.repaint();
					chance-=1;
					chance3=true;
				}
			}
		});
		
		btn_result.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(started) {
					int playerspecial=0, dealerspecial=0;
					int playermark=0, dealermark=0;
					boolean playerwin=false;
					
					DealerPanel.removeAll();
					Image1= new ImageIcon("card_"+dealer.get(0)+".gif");
					Image2= new ImageIcon("card_"+dealer.get(1)+".gif");
					Image3= new ImageIcon("card_"+dealer.get(2)+".gif");
					label_Image1.setIcon(Image1);
					label_Image2.setIcon(Image2);
					label_Image3.setIcon(Image3);
					DealerPanel.add(label_Image1);
					DealerPanel.add(label_Image2);
					DealerPanel.add(label_Image3);
					frame.repaint();
					
					for(int i=0;i<3;i++) {
						if(player.get(i)>100 && player.get(i)%10>0) {
							playerspecial++;
						}else {
							playermark+=player.get(i)%10;
						}
					}
					playermark=playermark%10;
					
					for(int i=0;i<3;i++) {
						if(dealer.get(i)>100 && dealer.get(i)%10>0) {
							dealerspecial++;
						}else {
							dealermark+=dealer.get(i)%10;
						}
					}
					dealermark=dealermark%10;
					
					if(playerspecial>dealerspecial) {
						playerwin=true;
					}else if(playerspecial==dealerspecial && playermark>dealermark) {
						playerwin=true;
					}
					
					if(playerwin) {
						total+=userbet;
						started=false;
						chance=2;
						chance1=false;
						chance2=false;
						chance3=false;
						JOptionPane.showMessageDialog(MainPanel, "Congratulations! You win this round!", "Message", JOptionPane.INFORMATION_MESSAGE);
						label_info.setText("Please place your bet!");
						label_money.setText("Amount of money you have: $"+total);
						PlayerPanel.removeAll();
						Image4= new ImageIcon("card_back.gif");
						Image5= new ImageIcon("card_back.gif");
						Image6= new ImageIcon("card_back.gif");
						label_Image4.setIcon(Image4);
						label_Image5.setIcon(Image5);
						label_Image6.setIcon(Image6);
						PlayerPanel.add(label_Image4);
						PlayerPanel.add(label_Image5);
						PlayerPanel.add(label_Image6);
						DealerPanel.removeAll();
						Image1= new ImageIcon("card_back.gif");
						Image2= new ImageIcon("card_back.gif");
						Image3= new ImageIcon("card_back.gif");
						label_Image1.setIcon(Image1);
						label_Image2.setIcon(Image2);
						label_Image3.setIcon(Image3);
						DealerPanel.add(label_Image1);
						DealerPanel.add(label_Image2);
						DealerPanel.add(label_Image3);
						frame.repaint();
						
						for(int i=0;i<3;i++) {
							stack.add(dealer.get(i));
							stack.add(player.get(i));
						}
						player.clear();
						dealer.clear();
					}else {
						total-=userbet;
						if(total==0) {
							JOptionPane.showMessageDialog(MainPanel, "Sorry! The Dealer wins this round!", "Message", JOptionPane.INFORMATION_MESSAGE);
							ended=true;
							started=false;
							label_info.setText("You have no more money!");
							label_money.setText("Please start a new game!");
							frame.repaint();
							JOptionPane.showMessageDialog(MainPanel, "Game over!\nYou have no more money!\nPlease start a new game!", "Message", JOptionPane.INFORMATION_MESSAGE);
						}else {
							started=false;
							chance=2;
							chance1=false;
							chance2=false;
							chance3=false;
							
							JOptionPane.showMessageDialog(MainPanel, "Sorry! The Dealer wins this round!", "Message", JOptionPane.INFORMATION_MESSAGE);
							label_info.setText("Please place your bet!");
							label_money.setText("Amount of money you have: $"+total);
							PlayerPanel.removeAll();
							Image4= new ImageIcon("card_back.gif");
							Image5= new ImageIcon("card_back.gif");
							Image6= new ImageIcon("card_back.gif");
							label_Image4.setIcon(Image4);
							label_Image5.setIcon(Image5);
							label_Image6.setIcon(Image6);
							PlayerPanel.add(label_Image4);
							PlayerPanel.add(label_Image5);
							PlayerPanel.add(label_Image6);
							DealerPanel.removeAll();
							Image1= new ImageIcon("card_back.gif");
							Image2= new ImageIcon("card_back.gif");
							Image3= new ImageIcon("card_back.gif");
							label_Image1.setIcon(Image1);
							label_Image2.setIcon(Image2);
							label_Image3.setIcon(Image3);
							DealerPanel.add(label_Image1);
							DealerPanel.add(label_Image2);
							DealerPanel.add(label_Image3);
							frame.repaint();
							
							for(int i=0;i<3;i++) {
								stack.add(dealer.get(i));
								stack.add(player.get(i));
							}
							player.clear();
							dealer.clear();
						}
					}
				}
			}
		});
		
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String b="Rules to determine who has better cards:\r\n" + 
						"J, Q, K are regarded as special cards.\r\n" + 
						"Rule 1: The one with more special cards wins.\r\n" + 
						"Rule 2: If both have the same number of special cards, add the face values of the other card(s) and take the remainder after dividing the sum by 10. The one with a bigger remainder wins. (Note: Ace = 1).\r\n" + 
						"Rule 3: The dealer wins if both rule 1 and rule 2 cannot distinguish the winner.";
				JOptionPane.showMessageDialog(MainPanel, b, "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
	}
}
