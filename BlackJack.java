import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class BlackJack implements Runnable, MouseListener {

	final int WIDTH = 1500;
	final int HEIGHT = 805;

	JFrame frame;
	Canvas canvas;
	BufferStrategy bufferStrategy;

	public card deck[];
	public Image Ideck[];
	public Image deckOfCards;
	public Image background;
	public int n;

	public chip tenChip;
	public Image ItenChip;
	public chip hundredChip;
	public Image IhundredChip;
	public chip fiveHundredChip;
	public Image IfiveHundredChip;
	public chip kChip;
	public Image IkChip;
	public int tenBet ;
	public int hundredBet;
	public int fiveHundredBet;
	public int kBet;
	public int BET$;
	public int total;
	public int dealerTotal;

	public int fontSize;
	public int cash = 5000;
	public boolean deal = false;
	public int mouseX = 0;
	public int mouseY = 0;

	public int card1;
	public int card2;
	public int card3;
	public int card4;
	public int sCard[];
	public boolean bcards;
	public int moveCount = 0;

	public boolean hit = false;
	public boolean stand = false;
	public boolean Double = false;
	public boolean split = false;
	public boolean bhit = false;
	public boolean bstand = false;
	public boolean bDouble = false;
	public boolean bsplit = false;
	public int nHits;

	public boolean bust = false;
	public fontN size[];
	public int count;
	public int count2;
	public int dealerHand;
	public boolean dealerBust = false;
	public boolean exception = false;
	public boolean dealerWin = false;
	public boolean blackJack = false;
	public boolean youWin = false;
	public boolean push = false;

	public int splitHand1;
	public int splitHand2;
	public boolean split1;
	public boolean split2;
	
	public static void main(String[] args) {
		BlackJack ex = new BlackJack();
		new Thread(ex).start();
	}

	public BlackJack() {

		frame = new JFrame("Black Jack");

		deck = new card[52];
		Ideck = new Image[54];
		sCard = new int[21];
		size = new fontN[100];
		loadImages();

		for (int i = 0; i < deck.length; i++) {
			deck[i].isAlive = false;
		}

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);

		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);

		canvas.addMouseListener(this);

		panel.add(canvas);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		canvas.requestFocus();
	}

	public void run() {

		while (true) {
			render();

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {

			}
		}
	}

	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
		if (cash+BET$>0) {
			g.drawImage(deckOfCards, 10, 10, 250, 250, null);
		}

		if (deal == false && cash>0) {
			tenChip.xpos = 450;
			tenChip.ypos = 700;
			g.drawImage(ItenChip, tenChip.xpos, tenChip.ypos, 100, 100, null);
			hundredChip.xpos = 565;
			hundredChip.ypos = 700;
			g.drawImage(IhundredChip, hundredChip.xpos, hundredChip.ypos, 100, 100, null);
			fiveHundredChip.xpos = 680;
			fiveHundredChip.ypos = 700;
			g.drawImage(IfiveHundredChip, fiveHundredChip.xpos, fiveHundredChip.ypos, 100, 100, null);
			kChip.xpos = 795;
			kChip.ypos = 700;
			g.drawImage(IkChip, kChip.xpos, kChip.ypos, 100, 100, null);
			fontSize = 45;
			g.setFont(new Font("Arial", Font.PLAIN, fontSize));
			g.drawString("Reset Bet", 920, 720);
			fontSize = 43;
			g.setFont(new Font("Arial", Font.PLAIN, fontSize));
			g.drawString("DEAL", 273, 250);
			fontSize = 45;
			g.setFont(new Font("Arial", Font.PLAIN, fontSize));
			g.drawString("YOUR BET$$", 890, 65);
			g.drawString("$" + BET$, 910, 105);
			fontSize = 35;
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, fontSize));
			g.drawString("Your Cash", 910, 155);
			if (cash < 500) {
				g.setColor(Color.RED);
			}
			g.drawString("$" + cash, 925, 190);
			g.setColor(Color.BLACK);
			fontSize = 63;
			g.setFont(new Font("Arial", Font.PLAIN, fontSize));
			g.drawString("ALL IN",200,675);

				if (mouseX > 450 && mouseX < 550 && mouseY > 700 && mouseY < 800) {
					if (BET$ + 10 <= cash) {
						BET$ = BET$ + 10;
						tenBet++;
					}
					mouseX = 0;
					mouseY = 0;
				}
				for (int x = 390; x < 75 * tenBet + 390; x = x + 75) {
						tenChip.xpos = x;
						tenChip.ypos = 510;
						g.drawImage(ItenChip, tenChip.xpos, tenChip.ypos, 100, 100, null);
				}
			if (mouseX > 565 && mouseX < 665 && mouseY > 700 && mouseY < 800) {
				if (BET$ + 100 <= cash) {
					BET$ = BET$ + 100;
					hundredBet++;
				}
				mouseX = 0;
				mouseY = 0;
			}
			for (int x = 430; x < 75 * hundredBet + 430; x = x + 75) {
					hundredChip.xpos = x;
					hundredChip.ypos = 395;
					g.drawImage(IhundredChip, hundredChip.xpos, hundredChip.ypos, 100, 100, null);
			}
			if (mouseX > 680 && mouseX < 780 && mouseY > 700 && mouseY < 800) {
				if (BET$ + 500 <= cash) {
					BET$ = BET$ + 500;
					fiveHundredBet++;
				}
				mouseX = 0;
				mouseY = 0;
			}
			for (int x = 490; x < 75 * fiveHundredBet + 490; x = x + 75) {
					fiveHundredChip.xpos = x;
					fiveHundredChip.ypos = 280;
					g.drawImage(IfiveHundredChip, fiveHundredChip.xpos, fiveHundredChip.ypos, 100, 100, null);
			}
			if (mouseX > 785 && mouseX < 885 && mouseY > 700 && mouseY < 800) {
				if (BET$ + 1000 <= cash) {
					BET$ = BET$ + 1000;
					kBet++;
				}
				mouseX = 0;
				mouseY = 0;
			}
			for (int x = 500; x < 75 * kBet + 500; x = x + 75) {
					kChip.xpos = x;
					kChip.ypos = 165;
					g.drawImage(IkChip, kChip.xpos, kChip.ypos, 100, 100, null);
			}
			if(mouseX>200 && mouseX<380 && mouseY>600 && mouseY<675){
				BET$ = cash;
			}
			if (mouseX > 920 && mouseX < 1115 && mouseY > 690 && mouseY < 720) {
				tenBet = 0;
				hundredBet = 0;
				fiveHundredBet = 0;
				kBet = 0;
				BET$ = 0;
			}
			if (mouseX > 273 && mouseX < 370 && mouseY > 220 && mouseY < 250) {
				deal = true;
				if (BET$ == 0){
					deal = false;
					g.drawString("YOU MUST BET FIRST",500,375);
				}
			}
			bcards = true;
		}

		if (deal == true) {
			count2 = 0;
			if (cash < 500) {
				g.setColor(Color.RED);
			}
			fontSize = 50;
			g.setFont(new Font("Arial", Font.PLAIN, fontSize));
			g.drawString("Your Hand:", 200, 460);
			g.drawString("Dealer's Hand", 580, 125);
			fontSize = 35;
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, fontSize));
			g.drawString("Your Cash", 1140, 410);
			g.drawString("Your Bet",1140,470);
			fontSize = 27;
			g.setFont(new Font("Arial", Font.PLAIN, fontSize));
			if (BET$ + cash < 500) {
				g.setColor(Color.RED);
			}
			g.drawString("$" + cash, 1245, 440);
			g.setColor(Color.BLACK);
			g.drawString("$"+BET$,1245,495);

			if (bcards == true){
				card1 = (int)(Math.random()*51);
				deck[card1].isAlive = true;
				total = total + deck[card1].number;
				deck[card1].playerHand1 = true;
				card2 = (int)(Math.random()*51);
				while(deck[card2].isAlive==true){
					card2 = (int)(Math.random()*51);
				}
				deck[card2].isAlive = true;
				total = total + deck[card2].number;
				deck[card2].playerHand1 = true;
				if (total == 21){
					blackJack = true;
				}
				card3 = (int)(Math.random()*51);
				while(deck[card3].isAlive==true){
					card3 = (int)(Math.random()*51);
				}
				deck[card3].isAlive = true;
				dealerTotal = dealerTotal + deck[card3].number;
				deck[card3].dealer = true;
				card4 = (int)(Math.random()*51);
				while(deck[card4].isAlive==true){
					card4 = (int)(Math.random()*51);
				}
				deck[card4].isAlive = true;
				dealerTotal = dealerTotal + deck[card4].number;
				deck[card4].dealer = true;
				bcards = false;
			}
			if (blackJack == true && dealerTotal!=21){
				g.setColor(Color.BLACK);
				if (count<49) {
					fontSize = count + 100;
				}
				g.setFont(new Font("Arial", Font.PLAIN, fontSize));
				if (count<50) {
					if (size[count].isAlive == true) {
						g.drawString("BLACK JACK", 320, 600);
						size[count].isAlive = false;
					}
				}
				else{
					fontSize = 150;
					g.setFont(new Font("Arial", Font.PLAIN, fontSize));
					g.drawString("BLACK JACK", 320, 600);
				}
				count++;
				mouseX = 0;
				mouseY = 0;
				if (mouseX>0 && mouseX<1500 && mouseY>0 && mouseY<805 && count == 48){
					deal = false;
					cash = (int)(cash+(BET$*1.5));
					reset();
				}
			}

			fontSize = 37;
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.PLAIN, fontSize));
			g.drawString("HIT  ,  STAND  ,  DOUBLE  ,  SPLIT",470,463);
			if (mouseX>470 && mouseX<531 && mouseY>440 && mouseY<463 && stand == false && Double == false && bust == false){
				hit = true;
				bhit = true;
				nHits++;
				mouseX = 0;
				mouseY = 0;
			}
			if (mouseX>584 && mouseX<705 && mouseY>440 && mouseY<463 && stand == false && Double == false && bust == false){
				stand = true;
				bstand = true;
				mouseX = 0;
				mouseY = 0;
			}
			if (mouseX>762 && mouseX<908 && mouseY>440 && mouseY<463 && nHits == 0 && stand == false && Double == false && bust == false){
				Double = true;
				bDouble = true;
				mouseX = 0;
				mouseY = 0;
			}
			if (mouseX>964 && mouseX<1055 && mouseY>442 && mouseY<464 && split == false){ //&& deck[card1].number == deck[card2].number){
				split = true;
				bsplit = true;
				mouseX = 0;
				mouseY = 0;
			}
			if (hit == true && bhit == true) {
				sCard[nHits-1] = (int) (Math.random() * 51);
				while (deck[sCard[nHits-1]].isAlive == true) {
					sCard[nHits-1] = (int) (Math.random() * 51);
				}
				deck[sCard[nHits-1]].isAlive = true;
				total = total + deck[sCard[nHits-1]].number;
				deck[sCard[nHits-1]].playerHand1 = true;
					if (total>21){
						for (int a=0;a<52;a++){
							if (deck[a].number == 11 && deck[a].isAlive == true && deck[a].playerHand1 == true){
								total -=10;
								deck[a].number = 1;
							}
						}
						if (total>21) {
							bust = true;
						}
					}
				bhit = false;
			}
			if (Double == true && nHits == 0 && split == false && bDouble == true && BET$*2<=cash){
				BET$=BET$*2;
				sCard[0] = (int) (Math.random() * 51);
				while (deck[sCard[0]].isAlive == true) {
					sCard[0] = (int) (Math.random() * 51);
				}
				deck[sCard[0]].isAlive = true;
				total = total + deck[sCard[0]].number;
				deck[sCard[0]].playerHand1 = true;
				if (total>21){
					for (int a=0;a<52;a++){
						if (deck[a].number == 11 && deck[a].isAlive == true && deck[sCard[0]].playerHand1 == true){
							total -=10;
							deck[a].number = 1;
						}
					}
					if (total>21) {
						bust = true;
					}
				} else{
					stand = true;
					bstand = true;
				}
				bDouble = false;
			}

			//Not fully functional yet (splitting)
			if (split == true) {
				splitHand1 = deck[card1].number;
				splitHand2 = deck[card2].number;
				if (deck[card1].ypos > 468) {
					deck[card1].ypos--;
					for (int a = 0; a < 51; a++) {
						deck[a].width = 123;
						deck[a].height = 142;
					}
				}
				if (deck[card1].xpos < 207) {
					deck[card1].xpos++;
				}
				if (deck[card2].ypos < 621) {
					deck[card2].ypos++;
				}
				if (deck[card2].xpos > 207) {
					deck[card2].xpos--;
				}
				g.drawImage(Ideck[53], 63, 490, 140, 115, null);
				if (splitHand1 < 21 && stand == false){
					split1 = true;
				}
				if (bsplit == true) {
					BET$ = BET$*2;
					deck[card1].aSplitHand1 = true;
					deck[card2].aSplitHand2 = true;
					sCard[0] = (int) (Math.random() * 51);
					while (deck[sCard[0]].isAlive == true) {
						sCard[0] = (int) (Math.random() * 51);
					}
					deck[sCard[0]].isAlive = true;
					splitHand1 += deck[sCard[0]].number;
					deck[sCard[0]].aSplitHand1 = true;
					sCard[1] = (int) (Math.random() * 51);
					while (deck[sCard[1]].isAlive == true) {
						sCard[1] = (int) (Math.random() * 51);
					}
					deck[sCard[1]].isAlive = true;
					splitHand2 += deck[sCard[1]].number;
					deck[sCard[1]].aSplitHand2 = true;
					bsplit = false;
				}
				if (hit == true && bhit == true) {
					if (split1 == true) {
						sCard[nHits + 1] = (int) (Math.random() * 51);
						while (deck[sCard[nHits + 1]].isAlive == true) {
							sCard[nHits + 1] = (int) (Math.random() * 51);
						}
						deck[sCard[nHits + 1]].isAlive = true;
						splitHand1 += deck[sCard[nHits + 1]].number;
						deck[sCard[nHits + 1]].aSplitHand1 = true;
						if (total > 21) {
							for (int a = 0; a < 52; a++) {
								if (deck[a].number == 11 && deck[a].isAlive == true && deck[a].aSplitHand1 == true) {
									splitHand1 -= 10;
									deck[a].number = 1;
								}
							}
							if (splitHand1 > 21) {
								bust = true;
							}
						}
						bhit = false;
					}
				}
			}
			if (stand == true){
				if(dealerTotal == 21 && nHits == 0){
					if (mouseX>0 && mouseX<1500 && mouseY>0 && mouseY<805){
						cash = (int)(cash-(BET$*1.5));
						deal = false;
						reset();
					}
				}
				if (dealerTotal<17 && bstand == true) {
						dealerHand++;
						sCard[dealerHand + 5] = (int) (Math.random() * 51);
						while (deck[sCard[dealerHand + 5]].isAlive == true) {
							sCard[dealerHand + 5] = (int) (Math.random() * 51);
						}
						deck[sCard[dealerHand + 5]].isAlive = true;
						dealerTotal += deck[sCard[dealerHand + 5]].number;
						deck[sCard[dealerHand + 5]].dealer = true;
						exception = true;
						bstand = false;
				}
				if (dealerTotal>21){
					for (int a=0;a<52;a++){
						if (deck[a].number == 11 && deck[a].isAlive == true && deck[sCard[dealerHand + 5]].dealer == true){
							dealerTotal -=10;
							deck[a].number = 1;
						}
					}
					if (dealerTotal>21) {
						dealerBust = true;
					}
				}
				if (dealerTotal<=21 && dealerTotal>=17){
					if (dealerTotal > total){
						dealerWin = true;
					}
					if (total>dealerTotal){
						youWin = true;
					}
				}
				if (dealerTotal == total){
					push = true;
				}
			}
			if (deck[card1].isAlive == true) {
				if (split == false) {
					moveCount++;
					if (moveCount == 2) {
						if (deck[card1].ypos < 500) {
							deck[card1].ypos += 10;
							deck[card1].xpos += 1;
						}
						if (deck[card2].ypos < 500) {
							deck[card2].xpos += 5;
							deck[card2].ypos += 12;
						}
						if (deck[card3].ypos < 200 && deck[card3].xpos < 470) {
							deck[card3].xpos += 6;
							deck[card3].ypos += 2;
						}
						if (deck[card4].ypos < 160 && deck[card4].xpos < 645) {
							deck[card4].xpos += 7;
							deck[card4].ypos += 2;
						}
						if (hit == true && deck[sCard[nHits - 1]].isAlive == true) {
							if (deck[sCard[nHits - 1]].ypos < 500 && deck[sCard[nHits - 1]].xpos < 375) {
								deck[sCard[nHits - 1]].xpos += 6;
								deck[sCard[nHits - 1]].ypos += 8;
							} else {
								if (total > 21) {
									cash = cash - BET$;
									BET$ = 0;
								}
								if (nHits > 1) {
									deck[sCard[nHits - 1]].xpos += 150 * (nHits - 1) + (4 * (nHits - 1));
								}
								hit = false;
							}
						}
						if (bDouble == false && Double == true) {
							if (deck[sCard[0]].ypos < 500 && deck[sCard[0]].xpos < 375) {
								deck[sCard[0]].xpos += 6;
								deck[sCard[0]].ypos += 8;
							} else {
								if (total > 21) {
									cash = cash - BET$;
									BET$ = 0;
								}
							}
						}
						if (deck[sCard[dealerHand + 5]].isAlive == true && deck[card4].xpos == 632) {
							if (deck[sCard[dealerHand + 5]].xpos < 795) {
								deck[sCard[dealerHand + 5]].xpos += 10;
								deck[sCard[dealerHand + 5]].ypos += 2;
							} else {
								if (deck[sCard[dealerHand + 5]].ypos > 165) {
									deck[sCard[dealerHand + 5]].ypos -= 1;
									bstand = true;
								}
							}
						}
						moveCount = 0;
					}
				}
				if (split == true && bsplit == false) {
					moveCount++;
					if (moveCount == 2) {
						if (deck[sCard[0]].ypos<465){
							deck[sCard[0]].ypos += 8;
						}
						if (deck[sCard[0]].xpos<340){
							deck[sCard[0]].xpos += 4;
						}
						if (deck[sCard[1]].ypos<620){
							deck[sCard[1]].ypos += 6;
						}
						if (deck[sCard[1]].xpos<340){
							deck[sCard[1]].xpos += 3;
						}
						moveCount = 0;
					}
					if (hit == true && bhit == false && split1 == true){
						//if (nHits > 1) {
							deck[sCard[nHits - 1]].xpos += 150 * (nHits - 1) + (4 * (nHits - 1));
						//}
					}
				}
				if (split == true && bsplit == false) {
					g.drawImage(Ideck[sCard[0]], deck[sCard[0]].xpos, deck[sCard[0]].ypos, deck[sCard[0]].width, deck[sCard[0]].height, null);
					g.drawImage(Ideck[sCard[1]], deck[sCard[1]].xpos, deck[sCard[1]].ypos, deck[sCard[1]].width, deck[sCard[1]].height, null);
				}

				g.drawImage(Ideck[card1], deck[card1].xpos, deck[card1].ypos, deck[card1].width, deck[card1].height, null);
				g.drawImage(Ideck[card2], deck[card2].xpos, deck[card2].ypos, deck[card2].width, deck[card2].height, null);
				g.drawImage(Ideck[card3], deck[card3].xpos, deck[card3].ypos, deck[card3].width, deck[card3].height, null);
				g.drawImage(Ideck[card4], deck[card4].xpos, deck[card4].ypos, deck[card4].width, deck[card4].height, null);
				if (stand == false) {
					g.drawImage(Ideck[52], deck[card4].xpos, deck[card4].ypos, deck[card4].width, deck[card4].height, null);
				}
				if (stand == true && bust == false) {
					if (deck[card4].xpos<632) {
						deck[card4].xpos += 1;
					}
					if (deck[card4].ypos<165){
						deck[card4].ypos += 1;
					}
				}
				for (int a=0;a<nHits;a++) {
					if (deck[sCard[a]].isAlive == true) {
						g.drawImage(Ideck[sCard[a]], deck[sCard[a]].xpos, deck[sCard[a]].ypos, deck[sCard[a]].width, deck[sCard[a]].height, null);
					}
				}
				if (bDouble == false && Double == true){
					g.drawImage(Ideck[sCard[0]], deck[sCard[0]].xpos, deck[sCard[0]].ypos, deck[sCard[0]].width, deck[sCard[0]].height, null);
				}
				if (stand == true && exception == true) {
					g.drawImage(Ideck[sCard[6]], deck[sCard[6]].xpos, deck[sCard[6]].ypos, deck[sCard[6]].width, deck[sCard[6]].height, null);
				}
				for (int a = 2; a <= dealerHand; a++) {
					g.drawImage(Ideck[sCard[a + 5]], deck[sCard[a + 5]].xpos+(90*a), deck[sCard[a + 5]].ypos, deck[sCard[a + 5]].width, deck[sCard[a + 5]].height, null);
				}
			}
			if (bust == true && cash+BET$>0){
				g.setColor(Color.BLACK);
				if (count<49) {
					fontSize = count + 100;
				}
				g.setFont(new Font("Arial", Font.PLAIN, fontSize));
				if (count<50) {
					if (size[count].isAlive == true) {
						g.drawString("YOU BUST", 320, 600);
						size[count].isAlive = false;
					}
				}
				else{
					fontSize = 150;
					g.setFont(new Font("Arial", Font.PLAIN, fontSize));
					g.drawString("YOU BUST", 320, 600);
				}
				count++;
				if (count == 3){
					cash = cash - BET$;
					BET$ = 0;
				}
				if (mouseX>0 && mouseX<1500 && mouseY>0 && mouseY<805){
					deal = false;
					reset();
				}
			}
			if (dealerBust == true){
				g.setColor(Color.BLACK);
				if (count<49) {
					fontSize = count + 80;
				}
				g.setFont(new Font("Arial", Font.PLAIN, fontSize));
				if (count<50) {
					if (size[count].isAlive == true) {
						g.drawString("DEALER BUSTS", 350, 300);
						size[count].isAlive = false;
					}
				}
				else{
					fontSize = 130;
					g.setFont(new Font("Arial", Font.PLAIN, fontSize));
					g.drawString("DEALER BUSTS", 300, 300);
				}
				count++;
				if (mouseX>0 && mouseX<1500 && mouseY>0 && mouseY<805){
					deal = false;
					reset();
				}
				if (count == 3){
					cash = cash+BET$;
					BET$ = 0;
				}
			}
			if (dealerWin == true){
				g.setColor(Color.BLACK);
				if (count<49) {
					fontSize = count + 80;
				}
				g.setFont(new Font("Arial", Font.PLAIN, fontSize));
				if (count<50) {
					if (size[count].isAlive == true) {
						g.drawString("DEALER WINS", 350, 300);
						size[count].isAlive = false;
					}
				} else{
					fontSize = 130;
					g.setFont(new Font("Arial", Font.PLAIN, fontSize));
					g.drawString("DEALER WINS", 320, 300);
				}
				count++;
				if (count == 3){
					cash = cash-BET$;
					BET$ = 0;
				}
				if (mouseX>0 && mouseX<1500 && mouseY>0 && mouseY<805) {
					deal = false;
					reset();
				}
			}
			if (youWin == true){
				g.setColor(Color.BLACK);
				if (count<49) {
					fontSize = count + 80;
				}
				g.setFont(new Font("Arial", Font.PLAIN, fontSize));
				if (count<50) {
					if (size[count].isAlive == true) {
						g.drawString("YOU WIN", 320, 600);
						size[count].isAlive = false;
					}
				}else{
					fontSize = 130;
					g.setFont(new Font("Arial", Font.PLAIN, fontSize));
					g.drawString("YOU WIN", 320, 600);
				}
				count++;
				if (count == 3){
					cash = cash+BET$;
					BET$ = 0;
				}
				if (mouseX>0 && mouseX<1500 && mouseY>0 && mouseY<805) {
					deal = false;
					reset();
				}
			}
		}
		if (push == true){
			g.setColor(Color.BLACK);
			if (count<49) {
				fontSize = count + 80;
			}
			g.setFont(new Font("Arial", Font.PLAIN, fontSize));
			if (count<50) {
				if (size[count].isAlive == true) {
					g.drawString("PUSH", 320, 400);
					size[count].isAlive = false;
				}
			}else{
				fontSize = 130;
				g.setFont(new Font("Arial", Font.PLAIN, fontSize));
				g.drawString("PUSH", 320, 400);
			}
			count++;
			if (count == 3){
				cash = cash+BET$;
				BET$ = 0;
			}
			if (mouseX>0 && mouseX<1500 && mouseY>0 && mouseY<805) {
				deal = false;
				reset();
			}
		}
		if (cash+BET$<=0){
			deal = false;
			g.setColor(Color.BLACK);
			if (count2<101) {
				fontSize = count2 + 100;
			}
			g.setFont(new Font("Arial", Font.PLAIN, fontSize));
			if (count2<100) {
				if (size[count2].isAlive == true) {
					g.drawString("YOU LOSE", 300, 420);
					size[count2].isAlive = false;
				}
			} else{
				fontSize = 200;
				g.setFont(new Font("Arial", Font.PLAIN, fontSize));
				g.drawString("YOU LOSE", 300, 420);
			}
			count2++;
			reset2();
		}
		g.dispose();
		bufferStrategy.show();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		//System.out.println(mouseX);
		//System.out.println(mouseY);

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void reset(){
		tenBet = 0;
		hundredBet = 0;
		fiveHundredBet = 0;
		kBet = 0;
		BET$ = 0;
		total = 0;
		dealerTotal = 0;
		hit = false;
		stand = false;
		Double = false;
		split = false;
		bsplit = false;
		nHits = 0;
		bust = false;
		dealerBust = false;
		count = 0;
		count2 = 0;
		dealerHand = 0;
		exception = false;
		dealerWin = false;
		blackJack = false;
		youWin = false;
		push = false;
		for (int a=0;a<50;a++){
			size[a].isAlive = true;
		}
		mouseX = 0;
		mouseY = 0;
		for (int a=0;a<52;a++){
			deck[a].isAlive = false;
			deck[a].xpos = 10;
			deck[a].ypos = 10;
		}
		for (int a=0;a<52;a++){
			if (deck[a].number == 1){
				deck[a].number = 11;
			}
		}
		for (int a=0;a<52;a++){
			deck[a].playerHand1 = false;
			deck[a].dealer = false;
		}
		for (int a=0;a<52;a++){
			deck[a].aSplitHand1 = false;
			deck[a].aSplitHand2 = false;
		}
	}
	public void reset2(){
		tenBet = 0;
		hundredBet = 0;
		fiveHundredBet = 0;
		kBet = 0;
		push = false;
		BET$ = 0;
		total = 0;
		dealerTotal = 0;
		hit = false;
		stand = false;
		Double = false;
		split = false;
		bsplit = false;
		dealerHand = 0;
		nHits = 0;
		bust = false;
		count = 0;
		dealerBust = false;
		exception = false;
		dealerWin = false;
		blackJack = false;
		youWin = false;
		for (int a=0;a<50;a++){
			size[a].isAlive = true;
		}
		mouseX = 0;
		mouseY = 0;
		for (int a=0;a<52;a++){
			deck[a].isAlive = false;
			deck[a].xpos = 10;
			deck[a].ypos = 10;
		}
		for (int a=0;a<52;a++){
			if (deck[a].number == 1){
				deck[a].number = 11;
			}
		}
		for (int a=0;a<52;a++){
			deck[a].playerHand1 = false;
			deck[a].dealer = false;
		}
		for (int a=0;a<52;a++){
			deck[a].aSplitHand1 = false;
			deck[a].aSplitHand2 = false;
		}
	}

	public void loadImages() {
		for (int b = 1; b <= 4; b++) {
			for (int a = 1; a < 14; a++){
				deck[n] = new card(a);
				if (deck[n].number>10){
					deck[n].number = 10;
				}
				if (deck[n].number == 1){
					deck[n].number = 11;
				}
				n = n+1;
			}
		}
		//Ihearts
		Ideck[0] = Toolkit.getDefaultToolkit().getImage("1200px-Playing_card_heart_A.svg.png");
		Ideck[1] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_heart_2.svg.png");
		Ideck[2] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_heart_3.svg.png");
		Ideck[3] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_heart_4.svg.png");
		Ideck[4] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_heart_5.svg.png");
		Ideck[5] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_heart_6.svg.png");
		Ideck[6] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_heart_7.svg.png");
		Ideck[7] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_heart_8.svg.png");
		Ideck[8] = Toolkit.getDefaultToolkit().getImage("Playing_card_heart_9.svg.png");
		Ideck[9] = Toolkit.getDefaultToolkit().getImage("200px-Playing_card_heart_10.jpg");
		Ideck[10] = Toolkit.getDefaultToolkit().getImage("Poker-sm-224-Jh.png");
		Ideck[11] = Toolkit.getDefaultToolkit().getImage("cod-qh.jpg");
		Ideck[12] = Toolkit.getDefaultToolkit().getImage("king-of-hearts.jpg");
		//Idiamonds
		Ideck[13] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_diamond_A.svg.png");
		Ideck[14] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_diamond_2.svg.png");
		Ideck[15] = Toolkit.getDefaultToolkit().getImage("200px-Playing_card_diamond_3.svg_.png");
		Ideck[16] = Toolkit.getDefaultToolkit().getImage("819px-Playing_card_diamond_4.svg.png");
		Ideck[17] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_diamond_5.svg.png");
		Ideck[18] = Toolkit.getDefaultToolkit().getImage("Playing_card_diamond_6.svg.png");
		Ideck[19] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_diamond_7.svg.png");
		Ideck[20] = Toolkit.getDefaultToolkit().getImage("Playing_card_diamond_8.svg.png");
		Ideck[21] = Toolkit.getDefaultToolkit().getImage("1000px-Playing_card_diamond_9.svg.png");
		Ideck[22] = Toolkit.getDefaultToolkit().getImage("10-of-Diamonds.jpg");
		Ideck[23] = Toolkit.getDefaultToolkit().getImage("163052036.jpg");
		Ideck[24] = Toolkit.getDefaultToolkit().getImage("queen-of-diamonds.jpg");
		Ideck[25] = Toolkit.getDefaultToolkit().getImage("king_of_diamonds1.jpg");
		//Ispades
		Ideck[26] = Toolkit.getDefaultToolkit().getImage("Playing_card_spade_A.svg.png");
		Ideck[27] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_spade_2.svg.png");
		Ideck[28] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_spade_3.svg.png");
		Ideck[29] = Toolkit.getDefaultToolkit().getImage("Playing_card_spade_4.svg.png");
		Ideck[30] = Toolkit.getDefaultToolkit().getImage("Playing_card_spade_5.svg.png");
		Ideck[31] = Toolkit.getDefaultToolkit().getImage("Playing_card_spade_6.svg.png");
		Ideck[32] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_spade_7.svg.png");
		Ideck[33] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_spade_8.svg.png");
		Ideck[34] = Toolkit.getDefaultToolkit().getImage("Playing_card_spade_9.svg.png");
		Ideck[35] = Toolkit.getDefaultToolkit().getImage("1396450711.png");
		Ideck[36] = Toolkit.getDefaultToolkit().getImage("4879386-Jack-of-spades-playing-card-Stock-Photo.jpg");
		Ideck[37] = Toolkit.getDefaultToolkit().getImage("Poker-sm-213-Qs.png");
		Ideck[38] = Toolkit.getDefaultToolkit().getImage("4879344-King-of-spades-playing-card-Stock-Photo.jpg");
		//Iclubs
		Ideck[39] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_club_A.svg.png");
		Ideck[40] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_club_2.svg.png");
		Ideck[41] = Toolkit.getDefaultToolkit().getImage("819px-Playing_card_club_3.svg.png");
		Ideck[42] = Toolkit.getDefaultToolkit().getImage("819px-Playing_card_club_4.svg.png");
		Ideck[43] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_club_5.svg.png");
		Ideck[44] = Toolkit.getDefaultToolkit().getImage("819px-Playing_card_club_6.svg.png");
		Ideck[45] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_club_7.svg.png");
		Ideck[46] = Toolkit.getDefaultToolkit().getImage("unnamed.png");
		Ideck[47] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_club_9.svg.png");
		Ideck[48] = Toolkit.getDefaultToolkit().getImage("2000px-Playing_card_club_10.svg.png");
		Ideck[49] = Toolkit.getDefaultToolkit().getImage("4879345-Jack-of-clubs-playing-card-Stock-Photo.jpg");
		Ideck[50] = Toolkit.getDefaultToolkit().getImage("Poker-sm-243-Qc.png");
		Ideck[51] = Toolkit.getDefaultToolkit().getImage("Poker-sm-242-Kc.png");
		Ideck[52] = Toolkit.getDefaultToolkit().getImage("playing_card.jpg");
		Ideck[53] = Toolkit.getDefaultToolkit().getImage("forward-512.png");

		deckOfCards = Toolkit.getDefaultToolkit().getImage("bigstock-playing-cards-27305468.png");

		tenChip = new chip(10);
		ItenChip = Toolkit.getDefaultToolkit().getImage("5507681-10-Poker-chip-Stock-Photo.png");
		hundredChip = new chip(100);
		IhundredChip = Toolkit.getDefaultToolkit().getImage("casino_100_poker_chips_black_white_poker_chip_set-r71f6dd622d2048b396831b4217358fc0_zraqn_8byvr_540.png");
		fiveHundredChip = new chip(500);
		IfiveHundredChip = Toolkit.getDefaultToolkit().getImage("fbb22ff9a3e85049703fb73b70259324.png");
		kChip = new chip(1000);
		IkChip = Toolkit.getDefaultToolkit().getImage("depositphotos_154384394-stock-illustration-poker-chip-with-1000-dollars.png");
		background = Toolkit.getDefaultToolkit().getImage("8347900_stock-vector-casino-winner-background-falling-explosion-gambling-poker-chips-illustration-jackpot-prize-design.jpg");

		for (int a=0;a<20;a++){
			sCard[a] = 0;
		}

		for (int a=0;a<100;a++){
			size[a] = new fontN();
		}
	}
}