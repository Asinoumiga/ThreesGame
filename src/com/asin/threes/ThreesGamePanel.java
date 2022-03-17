package com.asin.threes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ThreesGamePanel extends JPanel implements MouseMotionListener , MouseListener, KeyListener, ActionListener{

	CreateNext createNext = new CreateNext();
	
	int gameWindowx;
	int gameWindowy;
	int imageWidth;
	int imageHeight;
	int gap;

	int[][] nums;
	int[][] standingNums;
	int[][] movingNums;
	int[][] changingNums;
	int numX;
	int numY;
	int maxNum;
	int num1;
	int num2;

	int[] mouseX;
	int[] mouseY;
	int dragingX;
	int dragingY;
	Integer direction;
	int dragTime;
	
	int[] createNum;
	ArrayList<Integer> ok;
	Timer timer = new Timer(50, this);
	boolean isEnd=false;
	
	public ThreesGamePanel() {
		init();
		this.setFocusable(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
	}
	
	void init() {
		gameWindowx = 320;
		gameWindowy = 400;
		imageWidth = 80;
		imageHeight = 100;
		gap = 40;
		
		nums = new int[4][4];
		standingNums = new int[4][4];
		movingNums = new int[4][4];
		changingNums = new int[4][4];
		num1=0;
		num2=0;
		maxNum=1;
		
		mouseX=new int[2];
		mouseY=new int[2];
		dragTime=0;
		dragingX=0;
		dragingY=0;
		createNum = new int[2];
		ok = new ArrayList<>();
		
		boolean isNotOk;
		for (int i = 0; i < 10; i++) {
			do {
				isNotOk = false;
				numX = (int)(Math.random()*4);
				numY = (int)(Math.random()*4);
				createNum[0] = (int)(Math.random()*3)+1;
				if(nums[numY][numX]!=0) {
					isNotOk = true;
				}
				if(num1>=4&&createNum[0]==1) {
					isNotOk=true;
				}else if(num2>=4&&createNum[0]==2) {
					isNotOk=true;
				}
			}while(isNotOk);
			if(createNum[0]==1) {
				num1++;
			}else if(createNum[0]==2) {
				num2++;
			}
			if(createNum[0]>maxNum) {
				maxNum = createNum[0];
			}
			nums[numY][numX]=createNum[0];
		}
		createNum[1]=createNext.createNextNum(maxNum,num1,num2);
	}

	void doChangeNum(){
		int blank;
		int numValue;
		boolean isChange;
		if(direction==0){
			for (int y = 0; y < nums.length; y++) {
				blank = 0;
				numValue = 0;
				isChange = false;
				for (int x = 0; x < nums[0].length; x++) {
					if(nums[y][x]==0){
						blank=1;
					}else if(nums[y][x]!=0){
						if(numValue>=3&&numValue==nums[y][x]&&blank==0){
							isChange = true;
							nums[y][x-1]+=1;
							nums[y][x]=0;
							blank=1;
							changingNums[y][x-1]=1;
							if(nums[y][x-1]>maxNum){
								maxNum=nums[y][x-1];
							}
						}else if(numValue>=3&&numValue!=nums[y][x]&&blank==0){
							numValue = nums[y][x];
						}else if((numValue==1&&nums[y][x]==2||numValue==2&&nums[y][x]==1)&&blank==0){
							num1--;
							num2--;
							isChange=true;
							nums[y][x-1]=3;
							nums[y][x]=0;
							blank=1;
							changingNums[y][x-1]=1;
						}else if(numValue<3&&blank==0){
							numValue=nums[y][x];
						}else if(blank == 1){
							isChange=true;
							nums[y][x-1]= nums[y][x];
							nums[y][x]=0;
						}
					}
				}
				if(isChange){
					ok.add(y);
				}
			}
		}else if(direction==1){
			for (int x = 0; x < nums.length; x++) {
				blank = 0;
				numValue = 0;
				isChange = false;
				for (int y = 0; y < nums[0].length; y++) {
					if(nums[y][x]==0){
						blank=1;
					}else if(nums[y][x]!=0){
						if(numValue>=3&&numValue==nums[y][x]&&blank==0){
							isChange = true;
							nums[y-1][x]+=1;
							nums[y][x]=0;
							blank=1;
							changingNums[y-1][x]=1;
							if(nums[y-1][x]>maxNum){
								maxNum=nums[y-1][x];
							}
						}else if(numValue>=3&&numValue!=nums[y][x]&&blank==0){
							numValue = nums[y][x];
						}else if((numValue==1&&nums[y][x]==2||numValue==2&&nums[y][x]==1)&&blank==0){
							num1--;
							num2--;
							isChange=true;
							nums[y-1][x]=3;
							nums[y][x]=0;
							blank=1;
							changingNums[y-1][x]=1;
						}else if(numValue<3&&blank==0){
							numValue=nums[y][x];
						}else if(blank == 1){
							isChange=true;
							nums[y-1][x]= nums[y][x];
							nums[y][x]=0;
						}
					}
				}
				if(isChange){
					ok.add(x);
				}
			}
		}else if(direction==2){
			for (int y = 0; y < nums.length; y++) {
				blank = 0;
				numValue = 0;
				isChange = false;
				for (int x = nums[0].length-1; x >=0; x--) {
					if(nums[y][x]==0){
						blank=1;
					}else if(nums[y][x]!=0){
						if(numValue>=3&&numValue==nums[y][x]&&blank==0){
							isChange = true;
							nums[y][x+1]+=1;
							nums[y][x]=0;
							blank=1;
							changingNums[y][x+1]=1;
							if(nums[y][x+1]>maxNum){
								maxNum=nums[y][x+1];
							}
						}else if(numValue>=3&&numValue!=nums[y][x]&&blank==0){
							numValue = nums[y][x];
						}else if((numValue==1&&nums[y][x]==2||numValue==2&&nums[y][x]==1)&&blank==0){
							num1--;
							num2--;
							isChange=true;
							nums[y][x+1]=3;
							nums[y][x]=0;
							blank=1;
							changingNums[y][x+1]=1;
						}else if(numValue<3&&blank==0){
							numValue=nums[y][x];
						}else if(blank == 1){
							isChange=true;
							nums[y][x+1]= nums[y][x];
							nums[y][x]=0;
						}
					}
				}
				if(isChange){
					ok.add(y);
				}
			}
		}else if(direction==3){
			for (int x = 0; x < nums.length; x++) {
				blank = 0;
				numValue = 0;
				isChange = false;
				for (int y = nums[0].length-1; y >=0 ; y--) {
					if(nums[y][x]==0){
						blank=1;
					}else if(nums[y][x]!=0){
						if(numValue>=3&&numValue==nums[y][x]&&blank==0){
							isChange = true;
							nums[y+1][x]+=1;
							nums[y][x]=0;
							blank=1;
							changingNums[y+1][x]=1;
							if(nums[y+1][x]>maxNum){
								maxNum=nums[y+1][x];
							}
						}else if(numValue>=3&&numValue!=nums[y][x]&&blank==0){
							numValue = nums[y][x];
						}else if((numValue==1&&nums[y][x]==2||numValue==2&&nums[y][x]==1)&&blank==0){
							num1--;
							num2--;
							isChange=true;
							nums[y+1][x]=3;
							nums[y][x]=0;
							blank=1;
							changingNums[y+1][x]=1;
						}else if(numValue<3&&blank==0){
							numValue=nums[y][x];
						}else if(blank == 1){
							isChange=true;
							nums[y+1][x]= nums[y][x];
							nums[y][x]=0;
						}
					}
				}
				if(isChange){
					ok.add(x);
				}
			}
		}
		if(ok.size()!=0){
			addBlock(direction,ok);
			ok.removeAll(ok);
		}
	}

	void addBlock(int direction , ArrayList<Integer> okList){
		int createLoc;
		createLoc = (int)(Math.random()*okList.size());
		if(direction==0){
			nums[okList.get(createLoc)][3]=createNum[1];
		}else if(direction==1){
			nums[3][okList.get(createLoc)]=createNum[1];
		}else if(direction==2){
			nums[okList.get(createLoc)][0]=createNum[1];
		}else if(direction==3){
			nums[0][okList.get(createLoc)]=createNum[1];
		}
		if(okList.size()==1){
			if(findIsEnd()){
				System.out.println("end");
				isEnd=true;
			}
		}
		createNum[1]=createNext.createNextNum(maxNum,num1,num2);
		if(createNum[1]==1){
			num1++;
		}else if(createNum[1]==2){
			num2++;
		}
	}

	boolean findIsEnd(){
		int numValue;
		for(int x=0;x<nums[0].length;x++){
			numValue=0;
			for(int y=0;y<nums.length;y++){
				if(nums[y][x]==0){
					return false;
				}else if(nums[y][x]==numValue&&nums[y][x]>=3){
					return false;
				}else if(nums[y][x]==1&&numValue==2||nums[y][x]==2&&numValue==1){
					return false;
				}else {
					numValue=nums[y][x];
				}
			}
		}
		for(int y=0;y<nums.length;y++){
			numValue=0;
			for(int x=0;x<nums[0].length;x++){
				if(nums[y][x]==0){
					return false;
				}else if(nums[y][x]==numValue&&nums[y][x]>=3){
					return false;
				}else if(nums[y][x]==1&&numValue==2||nums[y][x]==2&&numValue==1){
					return false;
				}else {
					numValue=nums[y][x];
				}
			}
		}
		return true;
	}

	void movingDirection(){
		int blank;
		int numValue;
		if(direction==0){
			for(int y=0;y<nums.length;y++){
				blank=0;
				numValue=0;
				for(int x=0;x<nums[0].length;x++){
					if(nums[y][x]==0){
						blank=1;
					}else if(nums[y][x]!=0){
						if(numValue>=3&&numValue==nums[y][x]&&blank==0){
							movingNums[y][x]=nums[y][x];
							blank=1;
						}else if(numValue>=3&&numValue!=nums[y][x]&&blank==0){
							numValue=nums[y][x];
							standingNums[y][x]=nums[y][x];
						}else if((numValue==1&&nums[y][x]==2||numValue==2&&nums[y][x]==1)&&blank==0){
							movingNums[y][x]=nums[y][x];
							blank=1;
						}else if(numValue<3&&blank==0){
							numValue=nums[y][x];
							standingNums[y][x]=nums[y][x];
						}else if(blank==1){
							movingNums[y][x]=nums[y][x];
						}
					}
				}
			}
		}else if(direction==1){
			for(int x=0;x<nums[0].length;x++){
				blank=0;
				numValue=0;
				for(int y=0;y<nums.length;y++){
					if(nums[y][x]==0){
						blank=1;
					}else if(nums[y][x]!=0){
						if(numValue>=3&&numValue==nums[y][x]&&blank==0){
							movingNums[y][x]=nums[y][x];
							blank=1;
						}else if(numValue>=3&&numValue!=nums[y][x]&&blank==0){
							numValue=nums[y][x];
							standingNums[y][x]=nums[y][x];
						}else if((numValue==1&&nums[y][x]==2||numValue==2&&nums[y][x]==1)&&blank==0){
							movingNums[y][x]=nums[y][x];
							blank=1;
						}else if(numValue<3&&blank==0){
							numValue=nums[y][x];
							standingNums[y][x]=nums[y][x];
						}else if(blank==1){
							movingNums[y][x]=nums[y][x];
						}
					}
				}
			}
		}else if(direction==2){
			for(int y=0;y<nums.length;y++){
				blank=0;
				numValue=0;
				for(int x=nums[0].length-1;x>=0;x--){
					if(nums[y][x]==0){
						blank=1;
					}else if(nums[y][x]!=0){
						if(numValue>=3&&numValue==nums[y][x]&&blank==0){
							movingNums[y][x]=nums[y][x];
							blank=1;
						}else if(numValue>=3&&numValue!=nums[y][x]&&blank==0){
							numValue=nums[y][x];
							standingNums[y][x]=nums[y][x];
						}else if((numValue==1&&nums[y][x]==2||numValue==2&&nums[y][x]==1)&&blank==0){
							movingNums[y][x]=nums[y][x];
							blank=1;
						}else if(numValue<3&&blank==0){
							numValue=nums[y][x];
							standingNums[y][x]=nums[y][x];
						}else if(blank==1){
							movingNums[y][x]=nums[y][x];
						}
					}
				}
			}
		}else if(direction==3){
			for(int x=0;x<nums[0].length;x++){
				blank=0;
				numValue=0;
				for(int y=nums.length-1;y>=0;y--){
					if(nums[y][x]==0){
						blank=1;
					}else if(nums[y][x]!=0){
						if(numValue>=3&&numValue==nums[y][x]&&blank==0){
							movingNums[y][x]=nums[y][x];
							blank=1;
						}else if(numValue>=3&&numValue!=nums[y][x]&&blank==0){
							numValue=nums[y][x];
							standingNums[y][x]=nums[y][x];
						}else if((numValue==1&&nums[y][x]==2||numValue==2&&nums[y][x]==1)&&blank==0){
							movingNums[y][x]=nums[y][x];
							blank=1;
						}else if(numValue<3&&blank==0){
							numValue=nums[y][x];
							standingNums[y][x]=nums[y][x];
						}else if(blank==1){
							movingNums[y][x]=nums[y][x];
						}
					}
				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.white);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(gap,3*gap,gameWindowx,gameWindowy);
		if(direction==null){
			for(int y=0;y< nums.length;y++){
				for(int x=0;x<nums.length;x++){
					if(changingNums[y][x]!=0){
						if(! timer.isRunning()){
							timer.start();
						}
						ThreesPhotoData.getTurn(changingNums[y][x]).paintIcon(this,g,x*imageWidth+gap,y*imageHeight+gap*3);
						if(changingNums[y][x]>=3&&timer.isRunning()){
							timer.stop();
							changingNums = new int[4][4];
							ThreesPhotoData.getImage(nums[y][x]).paintIcon(this,g,x*imageWidth+gap,y*imageHeight+gap*3);
						}
					}else{
						ThreesPhotoData.getImage(nums[y][x]).paintIcon(this,g,x*imageWidth+gap,y*imageHeight+gap*3);
					}
				}
			}
		}else{
			for(int y=0;y<nums.length;y++){
				for(int x=0;x<nums.length;x++){
					ThreesPhotoData.getImage(standingNums[y][x]).paintIcon(this,g,x*imageWidth+gap,y*imageHeight+gap*3);
				}
			}
			for(int y=0;y<nums.length;y++){
				for(int x=0;x<nums.length;x++){
					if(movingNums[y][x]!=0){
						ThreesPhotoData.getImage(movingNums[y][x]).paintIcon(this,g,x*imageWidth+gap+dragingX,y*imageHeight+gap*3+dragingY);
					}
				}
			}
		}
		ThreesPhotoData.getImage(createNum[1]).paintIcon(this,g,gameWindowx/2-imageWidth/2+gap,10);
		if(isEnd){
			ThreesPhotoData.getPig().paintIcon(this,g,0,gameWindowy/2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int y=0;y<nums.length;y++){
			for (int x = 0; x < nums[0].length; x++) {
				if(changingNums[y][x]!=0){
					changingNums[y][x]++;
				}
			}
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if(keyCode==KeyEvent.VK_SHIFT){
			if(isEnd){
				System.out.println("restart");
				isEnd=false;
				init();
				repaint();
			}
		}
		if(keyCode==KeyEvent.VK_ESCAPE){
			System.out.println("×÷±×");
			for(int y=0;y<nums.length;y++){
				for (int x = 0; x < nums[0].length; x++) {
					if(nums[y][x]==1){
						num1--;
						nums[y][x]=0;
						isEnd=false;
					}if(nums[y][x]==2){
						num2--;
						nums[y][x]=0;
						isEnd=false;
					}
				}
			}
			repaint();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!isEnd){
			mouseX[0]=e.getX();
			mouseY[0]=e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!isEnd){
			if(Math.abs(dragingX)>=imageWidth/2||Math.abs(dragingY)>=imageHeight/2){
				doChangeNum();
			}
			direction = null;
			dragTime = 0;
			dragingX = 0;
			dragingY = 0;
			movingNums = new int[4][4];
			standingNums = new int[4][4];
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!isEnd){
			dragTime++;
			mouseX[1]=e.getX();
			mouseY[1]=e.getY();
			int x=mouseX[1]-mouseX[0];
			int y=mouseY[1]-mouseY[0];
			if(direction==null&&dragTime>10){
				if(Math.abs(x)>=Math.abs(y)&&x<0){
					direction=0;
					movingDirection();
					if(x<=-imageWidth){
						dragingX=-imageWidth;
					}else if(x>=0){
						dragingX=0;
					}else{
						dragingX=x;
					}
					dragingY=0;
				}else if(Math.abs(x)<Math.abs(y)&&y<0){
					direction=1;
					movingDirection();
					if(x<=-imageHeight){
						dragingY=-imageHeight;
					}else if(y>=0){
						dragingY=0;
					}else{
						dragingY=y;
					}
					dragingX=0;
				}else if(Math.abs(x)>=Math.abs(y)&&x>=0){
					direction=2;
					movingDirection();
					if(x>=imageWidth){
						dragingX=-imageWidth;
					}else if(x<=0){
						dragingX=0;
					}else{
						dragingX=x;
					}
					dragingY=0;
				}else if(Math.abs(x)<Math.abs(y)&&y>=0){
					direction=3;
					movingDirection();
					if(x>=imageHeight){
						dragingY=-imageHeight;
					}else if(y<=0){
						dragingY=0;
					}else{
						dragingY=y;
					}
					dragingX=0;
				}
			}else if(direction==null&&dragTime<=10){
			}else if(direction==0){
				if(x<=-imageWidth){
					dragingX=-imageWidth;
				}else if(x>=0){
					dragingX=0;
				}else{
					dragingX=x;
				}
				dragingY=0;
			}else if(direction==1){
				if(y<=-imageHeight){
					dragingY=-imageHeight;
				}else if(y>=0){
					dragingY=0;
				}else{
					dragingY=y;
				}
				dragingX=0;
			}else if(direction==2){
				if(x>=imageWidth){
					dragingX=imageWidth;
				}else if(x<=0){
					dragingX=0;
				}else{
					dragingX=x;
				}
				dragingY=0;
			}else if(direction==3){
				if(y>=imageHeight){
					dragingY=imageHeight;
				}else if(y<=0){
					dragingY=0;
				}else{
					dragingY=y;
				}
				dragingX=0;
			}
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
