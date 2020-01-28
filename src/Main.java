import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		File file = new File("C:\\Users\\Minh\\Desktop\\EFT.txt");
		
		 //BufferedReader br = new BufferedReader(new FileReader(file)); 
//		Scanner sc = new Scanner(file); 
//		if(sc.hasNextLine())
//		{
//			//System.out.println(sc.nextLine());
//			char[] ch =  (sc.nextLine()).toCharArray();
//			
//		}
		
		
		List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Mindy\\Desktop\\input.txt"));
		
		for(String c : lines)
			System.out.println(c);
		
		System.out.println("HELLO WORLD");
		System.out.println((lines.get(4)).toCharArray()[1]);
		
		//Finding length of Row and Column for the 2D Matrix
		System.out.println("ROWS: "+lines.size());
		int biggest = 0;
		for(int i = 0 ;i<lines.size(); i++)
		{
			int lengthOfRow = ((lines.get(i)).toCharArray()).length;
			if(lengthOfRow > biggest)
			{
				biggest = lengthOfRow;
			}
		}
		System.out.println(biggest);
		
		//2D matrix
		char[][] matrix = new char[lines.size()][biggest];
		
		for(int i = 0 ; i< matrix.length ; i++)
		{
			for(int j = 0 ; j <matrix[0].length-1 ;j++)
			{
				matrix[i][j] = ((lines.get(i)).toCharArray())[j];
			}
		}
		
		printMatrix(matrix);
		
		//find first dot and check the surrounding and do it over and over again.
		int biggestRow = 0;
		int biggestColumn = 0;
		
		//finding biggest row
		int dotindex = 0;
		for(int i = 0 ; i< matrix.length ; i++)
		{
			for(int j = 0 ; j <matrix[0].length-1 ;j++)
			{
				if(matrix[i][j]=='*')
				{
//					System.out.print(i);
//					System.out.print("/");
//					System.out.println(j);
			        Vector2D v = new Vector2D(i,j);
			        v.print();
					dotindex++;
				}
				else
				{
					dotindex = 0;
				}
				if(dotindex>biggestRow)
				{
					biggestRow=dotindex;
				}
				
				
			}
			
		}
		System.out.println(biggestRow);
		
		
		
		Set<Set <String>> coll = new TreeSet<Set<String>>();
		
		
		Set<Vector2D> set = new HashSet<Vector2D>();
		
		Vector2D vect;
				
		vect = new Vector2D(2,1);
		set.add(vect);
		set.add(vect);
		
		
	}
	public static void printMatrix(char matrix[][])
	{
		for(int i = 0 ; i< matrix.length ; i++)
		{
			for(int j = 0 ; j <matrix[0].length-1 ;j++)
			{
				System.out.print(matrix[i][j]);
//				System.out.print('a');

			}
			System.out.println();
		}
	}
//	public static void surr()
//	{
//		if(matrix[i][j]!='*')
//		{
//			return null;
//		}
//	}
	
}


