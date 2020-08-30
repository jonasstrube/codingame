import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
	public static int height;
	public static int width;
	public static String[] alphabet;
	public static String[] output;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        width = Integer.parseInt(in.nextLine());
        height = Integer.parseInt(in.nextLine());
        /*if (in.hasNextLine()) {
            in.nextLine();
        }*/
        String text = in.nextLine();
        
        System.err.println(width + " " + height);
        System.err.println(text);
        
        alphabet = new String[height];
        for (int lineOfInput = 0; lineOfInput < height; lineOfInput++) {
        	alphabet[lineOfInput] = in.nextLine();
        }
        
        for (int line = 0; line < height; line++) {
			for (int letter = 0; letter < text.length(); letter++) {
				switch (Character.toLowerCase(text.charAt(letter))) {
				case 'a':
					printFromLine(0*width, line);
					break;
				case 'b':
					printFromLine(1*width, line);
					break;
				case 'c':
					printFromLine(2*width, line);
					break;
				case 'd':
					printFromLine(3*width, line);
					break;
				case 'e':
					printFromLine(4*width, line);
					break;
				case 'f':
					printFromLine(5*width, line);
					break;
				case 'g':
					printFromLine(6*width, line);
					break;
				case 'h':
					printFromLine(7*width, line);
					break;
				case 'i':
					printFromLine(8*width, line);
					break;
				case 'j':
					printFromLine(9*width, line);
					break;
				case 'k':
					printFromLine(10*width, line);
					break;
				case 'l':
					printFromLine(11*width, line);
					break;
				case 'm':
					printFromLine(12*width, line);
					break;
				case 'n':
					printFromLine(13*width, line);
					break;
				case 'o':
					printFromLine(14*width, line);
					break;
				case 'p':
					printFromLine(15*width, line);
					break;
				case 'q':
					printFromLine(16*width, line);
					break;
				case 'r':
					printFromLine(17*width, line);
					break;
				case 's':
					printFromLine(18*width, line);
					break;
				case 't':
					printFromLine(19*width, line);
					break;
				case 'u':
					printFromLine(20*width, line);
					break;
				case 'v':
					printFromLine(21*width, line);
					break;
				case 'w':
					printFromLine(22*width, line);
					break;
				case 'x':
					printFromLine(23*width, line);
					break;
				case 'y':
					printFromLine(24*width, line);
					break;
				case 'z':
					printFromLine(25*width, line);
					break;
				case '?':
				default:
					printFromLine(26*width, line);
					break;
				}
			}
			System.out.println();
        }
        in.close();
    }
    
    public static void printFromLine(int startColumn, int line) {
		for (int i = startColumn; i < startColumn + width; i++)
			System.out.print(alphabet[line].charAt(i));
    }
}