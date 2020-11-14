import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Implementation{
	
	private ImageIcon backdrop;
	private ImageIcon grayblock;
	private ImageIcon longgrayblock;
	private JLabel[][] numbers = new JLabel[9][9];
	private JButton[] inputnum = new JButton[9];
	private JButton[][] button = new JButton[9][9];
	
	public int[][] p = new int[9][9]; //given puzzle
	public String [][] spotstaken = new String[9][9];
	public int index1;
	public int index2;
	public int gameover = 0;
	
	public int[] region1 = new int[9];
	public int[] region2 = new int[9];
	public int[] region3 = new int[9];
	public int[] region4 = new int[9];
	public int[] region5 = new int[9];
	public int[] region6 = new int[9];
	public int[] region7 = new int[9];
	public int[] region8 = new int[9];
	public int[] region9 = new int[9];
	

	public void gui(JFrame f) {
		
		//input puzzle
		p[0][8]= 4; p[1][8]= 9; p[2][8]= 0;   p[3][8]= 1; p[4][8]= 0; p[5][8]= 3;   p[6][8]= 6; p[7][8]= 0; p[8][8]= 0;
		p[0][7]= 0; p[1][7]= 2; p[2][7]= 0;   p[3][7]= 0; p[4][7]= 0; p[5][7]= 9;   p[6][7]= 0; p[7][7]= 0; p[8][7]= 3;
		p[0][6]= 5; p[1][6]= 0; p[2][6]= 0;   p[3][6]= 0; p[4][6]= 4; p[5][6]= 6;   p[6][6]= 0; p[7][6]= 0; p[8][6]= 0;
	
		p[0][5]= 0; p[1][5]= 0; p[2][5]= 2;   p[3][5]= 3; p[4][5]= 0; p[5][5]= 0;   p[6][5]= 8; p[7][5]= 1; p[8][5]= 0;
		p[0][4]= 0; p[1][4]= 5; p[2][4]= 0;   p[3][4]= 0; p[4][4]= 0; p[5][4]= 7;   p[6][4]= 9; p[7][4]= 6; p[8][4]= 2;
		p[0][3]= 0; p[1][3]= 1; p[2][3]= 9;   p[3][3]= 2; p[4][3]= 0; p[5][3]= 5;   p[6][3]= 0; p[7][3]= 0; p[8][3]= 7;
		
		p[0][2]= 0; p[1][2]= 0; p[2][2]= 0;   p[3][2]= 6; p[4][2]= 0; p[5][2]= 8;   p[6][2]= 0; p[7][2]= 7; p[8][2]= 0;
		p[0][1]= 0; p[1][1]= 7; p[2][1]= 0;   p[3][1]= 9; p[4][1]= 5; p[5][1]= 0;   p[6][1]= 3; p[7][1]= 2; p[8][1]= 0;
		p[0][0]= 0; p[1][0]= 3; p[2][0]= 1;   p[3][0]= 4; p[4][0]= 0; p[5][0]= 0;   p[6][0]= 5; p[7][0]= 8; p[8][0]= 0;
		
		updatespotstaken();
		
		backdrop = new ImageIcon(this.getClass().getResource("/background.png"));
		grayblock = new ImageIcon(this.getClass().getResource("/grayblock.png"));
		longgrayblock = new ImageIcon(this.getClass().getResource("/longgrayblock.png"));
		
		JLabel sudokutitle = new JLabel("sudoku");
		sudokutitle.setFont(new Font("Futura", Font.PLAIN, 40));
		sudokutitle.setForeground(new Color(102, 205, 170));
		sudokutitle.setHorizontalAlignment(SwingConstants.CENTER);
		sudokutitle.setBounds(259, 6, 188, 70);
		f.getContentPane().add(sudokutitle);
		
		JButton sudokubutton = new JButton(longgrayblock);
		sudokubutton.setBounds(275, 16, 150, 50);
		sudokubutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				execute();
			}
		});
		f.getContentPane().add(sudokubutton);
		
		for (int a = 0; a < 9; a++) {
			for (int b = 0; b < 9; b++) {
				numbers[a][b] = new JLabel("0");
				numbers[a][b].setBounds(((a*62)+78), (587-(b*62)), 50, 50);
				numbers[a][b].setFont(new Font("Futura", Font.PLAIN, 30));
				numbers[a][b].setForeground(Color.WHITE);
				numbers[a][b].setHorizontalAlignment(SwingConstants.CENTER);
				f.getContentPane().add(numbers[a][b]);
			}
		}
		setup();
		
		for (int a = 0; a < 9; a++) {
			for (int b = 0; b < 9; b++) {
				button[a][b] = new JButton(grayblock);
				button[a][b].setBounds(((a*62)+78), (587-(b*62)), 50, 50);
				f.getContentPane().add(button[a][b]);
			}
		}
		for (int a = 0; a < 9; a++) {
			for (int b = 0; b < 9; b++) {
				int x = a;
				int y = b;
				button[a][b].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int a = 0; a < 9; a++) {
							inputnum[a].setText(String.valueOf(a+1));
						}
						if (spotstaken[x][y] != "." && gameover == 0) {
							if (numbers[index1][index2].getText().equals("n")) {
								numbers[index1][index2].setText(" ");
							}
							index1 = x;
							index2 = y;	
							numbers[index1][index2].setText("n");
							numbers[index1][index2].setFont(new Font("Webdings", Font.PLAIN, 13));
							numbers[index1][index2].setForeground(new Color(102, 205, 170));
							p[index1][index2] = 0;
						}
					}
				});
			}
		}
		for (int a = 0; a < 9; a++) {
			inputnum[a] = new JButton(String.valueOf(a+1));
			inputnum[a].setBounds(((a*62)+75), 670, 60, 60);
			inputnum[a].setFont(new Font("Futura", Font.PLAIN, 40));
			inputnum[a].setForeground(new Color(102, 205, 170));
			f.getContentPane().add(inputnum[a]);
		}
		for (int b = 0; b < 9; b++) {
			int y = b;
			inputnum[b].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {	
					p[index1][index2] = y+1;
					numbers[index1][index2].setText(String.valueOf(p[index1][index2]));
					numbers[index1][index2].setFont(new Font("Futura", Font.PLAIN, 30));
					numbers[index1][index2].setForeground(new Color(175, 238, 238));
					updateregions();
					winchecker();
				}
			});
		}
		
		JLabel background = new JLabel(backdrop);
		background.setBounds(0, 0, 700, 700);
		f.getContentPane().add(background);
		
	}
	
	public void setup() {
		for (int a = 0; a < 9; a++) {
			for (int b = 0; b < 9; b++){
				if (p[a][b] == 0) {
					numbers[a][b].setText(" ");
				} else {
					numbers[a][b].setText(String.valueOf(p[a][b]));
				}
			}
		}
	}
	public void winchecker() {
		int test1count = 0;
		int test2count = 0;
		int test3count = 0;
		for (int a = 0; a < 9; a++) {
			for (int b = 0; b < 9; b++) {
				
				//checks updown
				if (b == 0) {
					int[] count1 = new int[] {0, 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0};
					for(int c = 0; c < 9; c++) {
						count1[p[a][c]] = p[a][c];
					}
					for (int z = 0; z < 10; z++) {
						if (count1[z] == 0) {
							test1count++;
						}
					}
				}
				//checks leftright
				if (a == 0) {
					int[] count2 = new int[] {0, 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0};
					for(int c = 0; c < 9; c++) {
						count2[p[c][b]] = p[c][b];
					}
					for (int z = 0; z < 10; z++) {
						if (count2[z] == 0) {
							test2count++;
						}
					}
				}
				
			}
		}
		for (int a = 0; a < 9; a++) {
			if (region1[a] == 0 || region2[a] == 0 || region3[a] == 0 || region4[a] == 0 || region5[a] == 0 || region6[a] == 0 || region7[a] == 0 || region8[a] == 0 || region9[a] == 0) {
				test3count++;
			}
		}
		
		//count tally
		if (test1count+test2count+test3count == 18) { // ideal-> 9 + 9 + 0
			gameover = 1;
			String win = " WINNER! ";
			for (int i = 0; i < 9; i++) {
				if (i != 9) {
					inputnum[i].setText(win.substring(i, i+1));
				} else {
					inputnum[i].setText(win.substring(i));
				}
			}
		}
		
		
	}
	
	public void updateregions() {
		region7[6] = p[0][8]; region7[7] = p[1][8]; region7[8] = p[2][8];   region8[6] = p[3][8]; region8[7] = p[4][8]; region8[8] = p[5][8];   region9[6] = p[6][8]; region9[7] = p[7][8]; region9[8] = p[8][8];
		region7[3] = p[0][7]; region7[4] = p[1][7]; region7[5] = p[2][7];   region8[3] = p[3][7]; region8[4] = p[4][7]; region8[5] = p[5][7];   region9[3] = p[6][7]; region9[4] = p[7][7]; region9[5] = p[8][7];
		region7[0] = p[0][6]; region7[1] = p[1][6]; region7[2] = p[2][6];   region8[0] = p[3][6]; region8[1] = p[4][6]; region8[2] = p[5][6];   region9[0] = p[6][6]; region9[1] = p[7][6]; region9[2] = p[8][6];

		region4[6] = p[0][5]; region4[7] = p[1][5]; region4[8] = p[2][5];   region5[6] = p[3][5]; region5[7] = p[4][5]; region5[8] = p[5][5];   region6[6] = p[6][5]; region6[7] = p[7][5]; region6[8] = p[8][5];
		region4[3] = p[0][4]; region4[4] = p[1][4]; region4[5] = p[2][4];   region5[3] = p[3][4]; region5[4] = p[4][4]; region5[5] = p[5][4];   region6[3] = p[6][4]; region6[4] = p[7][4]; region6[5] = p[8][4];
		region4[0] = p[0][3]; region4[1] = p[1][3]; region4[2] = p[2][3];   region5[0] = p[3][3]; region5[1] = p[4][3]; region5[2] = p[5][3];   region6[0] = p[6][3]; region6[1] = p[7][3]; region6[2] = p[8][3];
		
		region1[6] = p[0][2]; region1[7] = p[1][2]; region1[8] = p[2][2];   region2[6] = p[3][2]; region2[7] = p[4][2]; region2[8] = p[5][2];   region3[6] = p[6][2]; region3[7] = p[7][2]; region3[8] = p[8][2];
		region1[3] = p[0][1]; region1[4] = p[1][1]; region1[5] = p[2][1];   region2[3] = p[3][1]; region2[4] = p[4][1]; region2[5] = p[5][1];   region3[3] = p[6][1]; region3[4] = p[7][1]; region3[5] = p[8][1];
		region1[0] = p[0][0]; region1[1] = p[1][0]; region1[2] = p[2][0];   region2[0] = p[3][0]; region2[1] = p[4][0]; region2[2] = p[5][0];   region3[0] = p[6][0]; region3[1] = p[7][0]; region3[2] = p[8][0];
	}
	public void updatespotstaken() {
		for (int a = 0; a < 9; a++) {
			for (int b = 0; b < 9; b++) {
				if (p[a][b] > 0) {
					spotstaken[a][b] = ".";
				}
			}
		}
	}
	
	/*
	 * 
	 * AI SECTION
	 * 
	 */
	
	public boolean AI() {

		for (int b = 0; b < 9; b++) {
			for (int a = 0; a < 9; a++) {
				if (p[a][b] == 0) {
			
					
					for (int z = p[a][b]+1; z <= 9; z++) {
						possiblemove(z, a, b); 
						if (possiblemove(z, a , b) == true) {
							p[a][b] = z;
							for (int v = 8; v >= 0; v--) {
								for (int t = 0; t < 9; t ++) {
									System.out.print(p[t][v]);
								} System.out.println(" ");
							} 
							System.out.println(" ");
							System.out.println(" ");
							updateregions();
							
							if (AI()) {
								return true;
							} else {
								p[a][b] = 0;
								for (int v = 8; v >= 0; v--) {
									for (int t = 0; t < 9; t ++) {
										System.out.print(p[t][v]);
									} System.out.println(" ");
								} 
								System.out.println(" ");
								System.out.println(" ");
								updateregions();
							}
							
						} 
					}
					
					
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean possiblemove(int z, int index1, int index2) {
		
		int ispossible = 1;
		//checks up
		for (int c = index2; c < 9; c++) {
			if (p[index1][c] == z) {
				ispossible = 0;
			}
		}
		//checks down
		for (int c = index2; c >= 0; c--) {
			if (p[index1][c] == z) {
				ispossible = 0;
			}
		}
		//checks right
		for (int c = index1; c < 9; c++) {
			if (p[c][index2] == z) {
				ispossible = 0;
			}
		}
		//checks left
		for (int c = index1; c >= 0; c--) {
			if (p[c][index2] == z) {
				ispossible = 0;
			}
		}
		
		//checks boxes
		if (index1 <= 2 && index2 <= 2) {
			for (int a = 0; a < 9; a++) {
				if (region1[a] == z) {
					ispossible = 0;
				}
			}
		}
		if ((index1 <= 5 && index1 >= 3) && (index2 <= 2)) {
			for (int a = 0; a < 9; a++) {
				if (region2[a] == z) {
					ispossible = 0;
				}
			}
		}
		if ((index1 <= 8 && index1 >= 6) && (index2 <= 2)) {
			for (int a = 0; a < 9; a++) {
				if (region3[a] == z) {
					ispossible = 0;
				}
			}
		}
		if ((index1 <= 2) && (index2 <= 5 && index2 >= 3)) {
			for (int a = 0; a < 9; a++) {
				if (region4[a] == z) {
					ispossible = 0;
				}
			}
		}
		if ((index1 <= 5 && index1 >= 3) && (index2 <= 5 && index2 >= 3)) {
			for (int a = 0; a < 9; a++) {
				if (region5[a] == z) {
					ispossible = 0;
				}
			}
		}
		if ((index1 <= 8 && index1 >= 6) && (index2 <= 5 && index2 >= 3)) {
			for (int a = 0; a < 9; a++) {
				if (region6[a] == z) {
					ispossible = 0;
				}
			}
		}
		if ((index1 <= 2) && (index2 <= 8 && index2 >= 6)) {
			for (int a = 0; a < 9; a++) {
				if (region7[a] == z) {
					ispossible = 0;
				}
			}
		}
		if ((index1 <= 5 && index1 >= 3) && (index2 <= 8 && index2 >= 6)) {
			for (int a = 0; a < 9; a++) {
				if (region8[a] == z) {
					ispossible = 0;
				}
			}
		}
		if ((index1 <= 8 && index1 >= 6) && (index2 <= 8 && index2 >= 6)) {
			for (int a = 0; a < 9; a++) {
				if (region9[a] == z) {
					ispossible = 0;
				}
			}
		}
		
	
		if (ispossible == 1) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public void execute() {
		AI();
		if (AI() == true) {
			winchecker();
			for (int a = 0; a < 9; a++) {
				for (int b = 0; b < 9; b++) {
					if (spotstaken[a][b] != ".") {
						numbers[a][b].setText(String.valueOf(p[a][b]));
						numbers[a][b].setFont(new Font("Futura", Font.PLAIN, 30));
						numbers[a][b].setForeground(new Color(175, 238, 238));
					}
				}
			}
		} else {
			String unsolvable = "CantSolve";
			for (int i = 0; i < 9; i++) {
				if (i != 9) {
					inputnum[i].setText(unsolvable.substring(i, i+1));
				} else {
					inputnum[i].setText(unsolvable.substring(i));
				}
			}
		}
	}
	
}
