import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Desafio {
	
	
	private static final String T = "T";
	private List<String> cardeais = new ArrayList<String>();
	
	public Desafio() {
		
		cardeais.add("N");
		cardeais.add("E");
		cardeais.add("S");
		cardeais.add("W");
	}
	
	
	
	
	/***
	 * Incompleto, eu não consegui entender a definição de SubCadeia
	 */
	public void jobSubCadeia() {
		
		System.out.println("Entre com o conjunto com o seguinte formato [2, -4, 6, 8, -10, 100, -6, 5] ");
		
		Scanner subSet   = new Scanner(System.in);
		
		String subSetbrackets = subSet.next();
		
		String subSetStr = subSetbrackets.replace("[", "");
		
		subSetStr = subSetStr.replace("]", "");
		
		subSetStr = subSetStr.trim();
		
		System.out.println("Sequencia digitada " + subSetStr);
		
		List<Integer> nums = transform(subSetStr);
		
		for (int i = 0; i<nums.size(); i++) {
			
		}
	}
	
	public void jobConjecturaCollatz() {
		
		System.out.println("Digite n ");
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		
		Calendar startDate = Calendar.getInstance();
		
		System.out.println("Tempo 1 =>" + startDate.get(Calendar.HOUR_OF_DAY) + ":" + startDate.get(Calendar.MINUTE) + ":" + startDate.get(Calendar.SECOND) + "." + startDate.getTimeInMillis());
		
		do {
			
			System.out.print(n);
			System.out.print(" ");
			
			if (isPair(n)) {
				
				n = calcForPair(n);
				
			} else {
				
				n = calcForImpa(n);
			}
			
		} while (n!=1);
		
		System.out.println(n);
		
		Calendar stopDate = Calendar.getInstance();
		
		System.out.println("Tempo 2 =>" + stopDate.get(Calendar.HOUR_OF_DAY) + ":" + stopDate.get(Calendar.MINUTE) + ":" + stopDate.get(Calendar.SECOND) + "." + stopDate.getTimeInMillis());
		
		long startDateS = startDate.get(Calendar.SECOND);
		long stopDateS  = stopDate.get(Calendar.SECOND);
		
		System.out.println("time spent " + (stopDateS - startDateS));
		
		
	}
	
	public void jobRobo() {
		
		System.out.println("Para executar o Robot digite 'E' ");
		
		Robot robot         = new Robot();
		List<String> cmds   = loadCommads();
		
		String space              = cmds.get(0);
		String firstPositionRobot = cmds.get(1);
		
		//setting
		settingMaxDimensionWorkRobot(robot, space);
		settingFirstPosition(robot, firstPositionRobot);
		
		for (int i=2; i<cmds.size(); i++) {
			
			String cmd = cmds.get(i);
			
			if (cmd.indexOf(T)>-1) {
				
				//teletransport
				teleport(robot, cmd);
				
			} else {
				
				newPosition(robot, cmd);
			}
		}
		
		System.out.println(robot);
	}
	
	public void newPosition(Robot robot, String cmd) {
		
		String[] cmds   = cmd.split("");
		String direcion = null;
		
		for (String c:cmds) {
			
			direcion = robot.getSide();
			
			int index = this.cardeais.indexOf(direcion);
			
			if (index==-1) {
				
			} else {
				
				if (c.equalsIgnoreCase("L")) {
					
					index = index - 1;
					if (index==-1)index=3;
					direcion = this.cardeais.get(index);
					robot.setSide(direcion);
					
				} else if (c.equalsIgnoreCase("R")) {
					
					index = index + 1;
					if (index==4)index=0;
					direcion = this.cardeais.get(index);
					robot.setSide(direcion);
					
				} else if (c.equalsIgnoreCase("M")) {
					
					char c2 = robot.getSide().charAt(0);
					
					switch (c2) {
					case 'N':
						modifyNorth(robot);
						
						break;
					case 'S':
						
						modifySuth(robot);
						
						break;
					case 'E':
						
						modifyEster(robot);
						
						break;
					case 'W':
						
						modifyWest(robot);
						
						break;

					}
				}
			}
		}
	}
	
	public void modifyNorth(Robot robot) {
		
		int r = robot.getY()+1;
		
		if (r>robot.getMaxY())return;
		
		robot.setY(r);
	}
	
	public void modifySuth(Robot robot) {
		
		int r = robot.getY()-1;
		
		if (r>robot.getMaxY())return;
		
		robot.setY(r);
	}
	
	public void modifyEster(Robot robot) {
		
		int r = robot.getX()+1;
		
		if (r>robot.getMaxX())return;
		
		robot.setX(r);
	}
	
	public void modifyWest(Robot robot) {
		
		int r = robot.getX()-1;
		
		if (r>robot.getMaxX())return;
		
		robot.setX(r);
	}
	
	public void teleport(Robot robot, String cmd) {
		
		String[] cmds = cmd.split(" ");
		
		int x = Integer.parseInt(cmds[1]);
		int y = Integer.parseInt(cmds[2]);
		
		robot.setX(x);
		robot.setY(y);
	
	}
	
	public void settingMaxDimensionWorkRobot(Robot robot, String line) {
		
		if (line==null || line.length()==0)return;
		
		String[] spaces = line.split(" ");
		
		int x = Integer.parseInt(spaces[0]);
		int y = Integer.parseInt(spaces[1]);
		
		robot.setMaxX(x);
		robot.setMaxY(y);
	}
	
	public void settingFirstPosition(Robot robot, String line) {
		
		if (line==null || line.length()==0)return;
		
		String[] spaces = line.split(" ");
		
		int x = Integer.parseInt(spaces[0]);
		int y = Integer.parseInt(spaces[1]);
		String d = spaces[2];
		
		robot.setSide(d);
		robot.setX(x);
		robot.setY(y);
	}
	
	
	public List<String> loadCommads() {
		
		List<String> cmds = new ArrayList<String>();
		
		Scanner in = new Scanner(System.in);
		
		while (in.hasNext()) {
			
			String cmd = in.nextLine();
			
			if (cmd.equalsIgnoreCase("E")) break;
			
			cmds.add(cmd);
		}
		
		return cmds;
	}
	
	
	public boolean isPair(int n) {
		return (n%2==0) ? true : false;
	}
	
	public int calcForPair(int n) {
		return n / 2;
	}
	
	public int calcForImpa(int n) {
		return  (3 * n) + 1;
	}
	
	public List<Integer> transform(String str) {
		
		List<Integer> nums = new ArrayList<Integer>();
		
		String[] numsSplit = str.split(",");
		
		for (String numStr : numsSplit) {
			
			nums.add(Integer.parseInt(numStr));
		}
		
		return nums;
	}
	
	public void showOptions() {
		
		while (true) {
			
		
			System.out.println("Escolha uma opção ");
			System.out.println("1 - Subcadeia de soma máxima. ");
			System.out.println("2 - Conjectura de Collatz. ");
			System.out.println("3 - Sistema Robo");
			System.out.println("q - Para sair ");
			
			Scanner in = new Scanner(System.in);
			
			String q = null;
			
			try {
			
				int op = in.nextInt();
				
				switch (op) {
				
					case 1:
						
						jobSubCadeia();
						
					break;
					
					case 2:
						
						jobConjecturaCollatz();
						
					break;
					
					case 3:
						
						jobRobo();
						
					break;
	
				}
				
			} catch (InputMismatchException e) {
				
				q = in.next();
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			if (q!=null && q.equalsIgnoreCase("q")) {
				break;
			}
		}
	}
	
	//inner class for Robot
	class Robot {
		
		private int x;
		private int y;
		
		private int maxX = 0;
		private int maxY = 0;
		
		private String side;
		
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public String getSide() {
			return side;
		}
		public void setSide(String side) {
			this.side = side;
		}
		public int getMaxX() {
			return maxX;
		}
		public void setMaxX(int maxX) {
			this.maxX = maxX;
		}
		public int getMaxY() {
			return maxY;
		}
		public void setMaxY(int maxY) {
			this.maxY = maxY;
		}
		
		public String toString() {
			return "X="+ this.x +" Y=" + this.y + " D=" +this.side;
		}
		
		
	}
	
	
	public static void main(String[] args) {
		Desafio d = new Desafio();
		d.showOptions();
	}
}
