import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.*;
 
public class NamHaloTest{
     
    public static void main(String[] args){
		int rows, k = 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("So Hang: ");
		rows=sc.nextInt();
		System.out.println("\n");

        for(int i = 1; i <= rows; ++i, k = 0) {
            for(int space = 1; space <= rows - i; ++space) {
                System.out.print("  ");
            }

            while(k != 2 * i - 1) {
                System.out.print("* ");
                ++k;
            }

            System.out.println();
        }
		System.out.println("\n\n");
    }
}