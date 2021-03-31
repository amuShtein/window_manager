import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



class AddListener implements ActionListener{

	@Override
	
	
	public void actionPerformed(ActionEvent e) {
		
	  if(e.getActionCommand().equals("Panel")) {
		  UserPanel p=new UserPanel(FinalGraphics.x,100,200,200);
		  ((UserPanel)FinalGraphics.SelectedComp).AddC(p);
	  
	  }
	  if(e.getActionCommand().equals("Label")) {
		  JLabel l=new JLabel("NONE");
		  l.setFont(new Font("Arial",Font.BOLD,25));
		  l.setHorizontalAlignment(JLabel.CENTER);
		  l.setBounds(FinalGraphics.x,50,100,50);
		  l.setBorder(BorderFactory.createEtchedBorder());
		  ToolListeners.RelocateListener RL=new ToolListeners.RelocateListener();
		  ToolListeners.ResizeListener SL=new ToolListeners.ResizeListener();
		  
		  l.addMouseListener(RL);
		  l.addMouseMotionListener(RL);
		  
		  l.addMouseListener(SL);
		  l.addMouseMotionListener(SL);
		  
		  l.addMouseListener(new SelectionListener());
		  
		  ((UserPanel)FinalGraphics.SelectedComp).AddC(l);
	  
	  }
	  
	  ((JFrame)FinalGraphics.frame).setSize(((JFrame)FinalGraphics.frame).getWidth()+1,((JFrame)FinalGraphics.frame).getHeight()+1);
	  ((JFrame)FinalGraphics.frame).setSize(((JFrame)FinalGraphics.frame).getWidth()-1,((JFrame)FinalGraphics.frame).getHeight()-1);
	}
	
}

class LabelListener extends MouseAdapter{
	public void mousePressed(MouseEvent e) {
		System.out.println("hello!");
	}
}
class ToolListeners{
	static int StartXpos,StartYpos;
	static class ResizeListener extends MouseAdapter implements MouseMotionListener{
		static int StartWidth,StartHeight;
		 static boolean resize=false;
		public void mousePressed(MouseEvent e) {
			if ((e.getX()>=((Component)(e.getSource())).getWidth()-8) && (e.getY()>=((Component)(e.getSource())).getHeight()-8))
			{
				resize=true;
			    StartXpos=e.getX();
			    StartYpos=e.getY();
			    StartWidth=((Component)e.getSource()).getWidth();
			    StartHeight=((Component)e.getSource()).getHeight();
			}
		}
        public void mouseReleased(MouseEvent e) {
        	resize=false;
        }
    	@Override
    	public void mouseDragged(MouseEvent e) {
    		if (resize) {
    			FinalGraphics.SelectedComp.setSize(StartWidth+e.getX()-StartXpos,StartHeight+e.getY()-StartYpos);
    			SelectionListener.setSizeTools(FinalGraphics.SelectedComp);
    		}
    	}
        public void mouseMoved(MouseEvent e) {
    		
    	}
	}
	static class RelocateListener extends MouseAdapter implements MouseMotionListener{
		static int StartX,StartY;
		 static boolean relocated=false;
		public void mousePressed(MouseEvent e) {
			if ((e.getX()<((Component)(e.getSource())).getWidth()-8) || (e.getY()<((Component)(e.getSource())).getHeight()-8))
			{
				relocated=true;
			    StartXpos=e.getX();
			    StartYpos=e.getY();
			    StartX=((Component)e.getSource()).getX();
			    StartY=((Component)e.getSource()).getY();
			}
		}
        public void mouseReleased(MouseEvent e) {
        	relocated=false;
        }
    	@Override
    	public void mouseDragged(MouseEvent e) {
    		if (relocated) {
    			FinalGraphics.SelectedComp.setLocation(FinalGraphics.SelectedComp.getX()-StartXpos+e.getX(),FinalGraphics.SelectedComp.getY()-StartYpos+e.getY());
    			SelectionListener.setSizeTools(FinalGraphics.SelectedComp);
    		}
    	}
        public void mouseMoved(MouseEvent e) {
    		
    	}
	}
	
}



class SelectionListener implements MouseListener{
	static Class Lbl=new JLabel().getClass();
	static Border Red=BorderFactory.createLineBorder(Color.RED,1),Gray=BorderFactory.createLineBorder(Color.LIGHT_GRAY,1);
	static void setSizeTools(Component c) {
		FinalGraphics.xTool.SetText(String.valueOf(c.getX()));
		FinalGraphics.yTool.SetText(String.valueOf(c.getY()));
		FinalGraphics.widthTool.SetText(String.valueOf(c.getWidth()));
		FinalGraphics.heightTool.SetText(String.valueOf(c.getHeight()));
	}
	static void setTextTool(Component c) {
		FinalGraphics.textTool.SetText(c.getClass()==Lbl?((JLabel)c).getText():"NO");
		FinalGraphics.textTool.setEditable(c.getClass()==Lbl);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		//	((JPanel)FinalGraphics.SelectedComp).setBorder(BorderFactory.createEtchedBorder(new JPanel().getBackground(),new JPanel().getBackground()));
		if(e.getSource()!=FinalGraphics.SelectedComp) {
		((JComponent)FinalGraphics.SelectedComp).setBorder(Gray);
	FinalGraphics.SelectedComp=(Component)e.getSource();
		((JComponent)FinalGraphics.SelectedComp).setBorder(Red);
		setSizeTools(FinalGraphics.SelectedComp);
		setTextTool(FinalGraphics.SelectedComp);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

class UserPanel extends JPanel{
	Component[] sons= {};
	UserPanel(int x,int y,int w,int h){
		ToolListeners.RelocateListener RelocList=new ToolListeners.RelocateListener();
		ToolListeners.ResizeListener ResizList=new ToolListeners.ResizeListener();
		SelectionListener SelectListen=new SelectionListener();
		
		addMouseListener(RelocList);
		addMouseMotionListener(RelocList);
		
		addMouseListener(ResizList);
		addMouseMotionListener(ResizList);
		
		addMouseListener(SelectListen);
		 
		 setBounds(x,y,w,h);
	     setLayout(null);
	     setVisible(true);
	     setBorder(SelectionListener.Gray);
	}
	
	void AddC(Component c){
	   	 Component[] t=new  Component[sons.length+1];
	   	 for(int i=0;i<sons.length;i++)
	   	  t[i]=sons[i];
	   	 t[t.length-1]=c;
	   	sons=t;
	        AddLast();
	    }
	void AddLast() {
			 this.add(sons[sons.length-1]);
	     }
	//@Override
	//public void setLocation(int x,int y) {
	//	super.setLocation(x,y);
	//}
	/*
	knit k;
	UserPanel(int x,int y,int w,int h){
		 setBounds(x,y,w,h);
	     k=new knit(this);
	     setLayout(null);
	}
	knit getKnit(){
		return k;
	}
	*/
	
	
}

/*class knit{
	UserPanel Parent;
	Component[] sons= {};
	knit(UserPanel p){
		Parent=p;
	}
	void AddC(Component c){
   	 Component[] t=new  Component[sons.length+1];
   	 for(int i=0;i<sons.length;i++)
   	  t[i]=sons[i];
   	 t[t.length-1]=c;
   	sons=t;
        AddLast();
    }
	 void AddLast() {
		 Parent.add(sons[sons.length-1]);
     }
	
}
*/

class rt implements KeyListener{

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
class MyFinalFrame extends JFrame{
	UserPanel FrameComp;
	//Component[] FrameComps= {new JPanel()};
	
     MyFinalFrame(int x,int y,int w,int h){
	 super("User Frame");
	 setBounds(x,y,w,h);
	 setLayout(null);
	 UserPanel pnl=new UserPanel(0,44,w,h);
// pnl.setBounds();
	 add(pnl);
	 FrameComp =pnl;
	 FrameComp.setBorder(SelectionListener.Red);
    }
     
     /*void AddC(Component c){
    	 Component[] t=new  Component[FrameComps.length+1];
    	 for(int i=0;i<FrameComps.length;i++)
    	  t[i]=FrameComps[i];
    	 t[t.length-1]=c;
    	 FrameComps=t;
         AddLast();
     }
     void AddLast() {
    	 add(FrameComps[FrameComps.length-1]);
     }
     
	   */
}





public class FinalGraphics {
	
	static MyFinalFrame frame;
	static int x=50,SubMenuInterval=5;
	public static Component SelectedComp;
	static JTextField xT,yT,wT,hT;
	static String UserImage="C:/JProjectDlc/JMenu.jpg";
	static Font UserFont=new Font("Arial",Font.BOLD,15);
	 static UserMenuBar Bar;
	 static SubMenuBar SubBar;
	 static UserMenuItem AddLbl,AddPnl;
	 static UserMenu Add;
    static SizeTool xTool,yTool,widthTool,heightTool,textTool;
	
	
	private static class UserMenuItem extends JPanel{
		 JButton btn;
		 JLabel lbl=new JLabel();
		 int Width, Height;
		 Font UserMenuItemFont=UserFont;
		 String Background=UserImage;
		 UserMenuItem(int w,int h,String s){
				super();
				Width=w;
				Height=h;
				setSize(Width,Height);
			    setLayout(null); 
			    setBorder(BorderFactory.createEtchedBorder());
			    setBtn(s);
			    setBackground(Background);
			}
		 UserMenuItem(int w,String s){
				this(w,20,s);
				}
		 JButton getBtn(){
			 return btn;
		 }
		 JLabel getlbl(){
			 return lbl;
		 }
		 void setBtn(String f){
			 btn=new JButton(f);
			 btn.setOpaque(true);
			 btn.setContentAreaFilled(false);
			 btn.setFocusPainted(false);
			 btn.setBounds(0,0,Width,Height);
			 btn.setFont(UserMenuItemFont);
			 add(btn);
		 }
	     void setBackground(String folder){
			 
			 lbl=new JLabel();
			 lbl.setLayout(null);
			 lbl.setIcon(new ImageIcon(folder));
			// lbl.setBorder(BorderFactory.createEtchedBorder());
			 lbl.setBounds(0, 0,Width,Height);
			 add(lbl);
		 }
	     void setActionCommand(String s) {
	    	 btn.setActionCommand(s);
	     }
	     void getActionCommand() {
	    	 btn.getActionCommand();
	     }
	     void addActionListener(ActionListener a) {
	    	 btn.addActionListener(a);
	     }
	     
		 
		}
	private static class UserMenu extends JMenu{
		UserMenu(String s){
			super(s);
			setFont(UserFont);
			setContentAreaFilled(false);
		}
		void addMyItem(UserMenuItem g){
			//UserMenuItem g=new UserMenuItem(w,h);
		    //    g.setBtn("AaBbCc123");
		    //    g.setBackground(UserImage);
			this.getPopupMenu().add(g);
		}
		void addNewMyItem(int w,int h){
			UserMenuItem g=new UserMenuItem(w,h,"AaBbCc123");
			this.getPopupMenu().add(g);
		}
		void setMBackground(Color c) {
			this.getPopupMenu().setBackground(c);
		}
		
	}
	private static class UserMenuBar extends JMenuBar{
		@Override
		public void paintComponent(Graphics g) {
		  g.drawImage(Toolkit.getDefaultToolkit().getImage(UserImage),0,0,this);
		  }
		UserMenuBar(){
			super();
			setSize(1920,20);
			setBorderPainted(false);
		}
		
	}
    private static class SubMenuBar extends JPanel{
    	SubMenuBar(int x,int y,int w,int h,Color bg){
    		super();
    		setBounds(x,y,w,h);
    		setBackground(bg);
    		setLayout(null);
    	}
    	SubMenuBar(int x,int y,int w,int h,Color bg,Border b){
    		this(x,y,w,h,bg);
    		setBorder(b);
    	}
    	
    }
    static class SizeTool extends JPanel{
    	JTextField Field;
    	JLabel name;
    	Color bg;
    	SizeTool(int x,int y,int textWidth,String n,Color bg){
    		super();
    		
    		
    		setLocation(x,y);
    		setBackground(this.bg=bg);
    		setLayout(null);
    		name=new JLabel(n);
    		name.setFont(UserFont);
    		name.setLocation(2,2);
    		name.setSize(textWidth,16);
    		name.setHorizontalAlignment(JLabel.RIGHT);
    		Field=new JTextField();
    		Field.setBounds(name.getWidth()+name.getX(),2,40,16);
    		Field.setFont(UserFont);
    		Field.setEditable(true);
    		setSize(Field.getX()+Field.getWidth()+2,20);
    		add(name);
    		add(Field);
    		
    	}
    	SizeTool(int x,int y,int textWidth,String n,Color bg,Border b){
    		this(x,y,textWidth,n,bg);
    		setBorder(b);
    	}
    	void SetText(String s){
    		Field.setText(s);
    	}
    	public void addKeyListener(KeyListener k) {
    		Field.addKeyListener(k);
    	}
    	public void setEditable(boolean b){
    		Field.setEditable(b);
    		if (b) 
    			setBackground(bg);
    		else
    			setBackground(Color.WHITE);
    			
    	}
    	
    	
    }
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	frame=new MyFinalFrame(500,300,800,500);
    SelectedComp=frame.FrameComp;
    
    Bar=new UserMenuBar();
    
    Add=new UserMenu("Add component");
    
    AddPnl=new UserMenuItem(100,"Panel");
    AddPnl.setActionCommand("Panel");
    AddPnl.addActionListener(new AddListener());   
    
    AddLbl=new UserMenuItem(100,"Label");
    AddLbl.setActionCommand("Label");
    AddLbl.addActionListener(new AddListener()); 
    
    Add.addMyItem(AddPnl);
    Add.addMyItem(AddLbl);
    
    Bar.add(Add);
    
    SubBar=new SubMenuBar(0,20,1920,24,Color.WHITE);
   // SubBar.add(new SizeTool(20,2,"X:",Color.LIGHT_GRAY,BorderFactory.createEtchedBorder()));
    xTool=new SizeTool(SubMenuInterval,2,20-5,"X:",Color.LIGHT_GRAY,BorderFactory.createEtchedBorder());
    yTool=new SizeTool(xTool.getX()+xTool.getWidth()+SubMenuInterval,2,20-5,"Y:",Color.LIGHT_GRAY,BorderFactory.createEtchedBorder());
    widthTool=new SizeTool(yTool.getX()+yTool.getWidth()+SubMenuInterval,2,50-4,"Width:",Color.LIGHT_GRAY,BorderFactory.createEtchedBorder());
    heightTool=new SizeTool(widthTool.getX()+widthTool.getWidth()+SubMenuInterval,2,60-7,"Height:",Color.LIGHT_GRAY,BorderFactory.createEtchedBorder());
    textTool=new SizeTool(heightTool.getX()+heightTool.getWidth()+SubMenuInterval,2,43-7,"Text:",Color.LIGHT_GRAY,BorderFactory.createEtchedBorder());
    textTool.Field.setSize(textTool.Field.getWidth()+40,textTool.Field.getHeight());
    textTool.setSize(textTool.getWidth()+40,textTool.getHeight());
    SelectionListener.setSizeTools(SelectedComp);
    SelectionListener.setTextTool(SelectedComp);
    xTool.addKeyListener(new KeyAdapter(){
        @Override
    	public void keyReleased(KeyEvent e) {
    		try {
    		String s;
    		SelectedComp.setLocation(Integer.parseInt((s=xTool.Field.getText()).equals("")?"0":s),SelectedComp.getY());
    		}
    		catch(Exception ex) {}
    	} 
        });
    yTool.addKeyListener(new KeyAdapter(){
        @Override
    	public void keyReleased(KeyEvent e) {
    		try {
    		String s;
    		SelectedComp.setLocation(SelectedComp.getX(),Integer.parseInt((s=yTool.Field.getText()).equals("")?"0":s));
    		}
    		catch(Exception ex) {}
    	} 
        });
    widthTool.addKeyListener(new KeyAdapter(){
        @Override
    	public void keyReleased(KeyEvent e) {
    		try {
    		String s;
    		SelectedComp.setSize(Integer.parseInt((s=widthTool.Field.getText()).equals("")?"0":s),SelectedComp.getHeight());
    		}
    		catch(Exception ex) {}
    	} 
        });
    heightTool.addKeyListener(new KeyAdapter(){
        @Override
    	public void keyReleased(KeyEvent e) {
    		try {
    		String s;
    		SelectedComp.setSize(SelectedComp.getWidth(),Integer.parseInt((s=heightTool.Field.getText()).equals("")?"0":s));
    		}
    		catch(Exception ex) {}
    	} 
        });
    textTool.addKeyListener(new KeyAdapter(){
        @Override
    	public void keyReleased(KeyEvent e) {
        	textTool.SetText(textTool.Field.getText().toUpperCase());
    		((JLabel)SelectedComp).setText(textTool.Field.getText().toUpperCase());
    	} 
        });
    
    SubBar.add(xTool);
    SubBar.add(yTool);
    SubBar.add(widthTool);
    SubBar.add(heightTool);
    SubBar.add(textTool);
    
    Bar.setBounds(0, 0, 1920, 20);
    frame.add(Bar);
    frame.add(SubBar);
    frame.setVisible(true);
	
	}

}
