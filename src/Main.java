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
		char[][] matrix = new char[lines.size()+1][biggest+1];
		
		for(int i = 0 ; i< matrix.length-1 ; i++)
		{
			for(int j = 0 ; j <matrix[0].length-2 ;j++)
			{
				matrix[i][j] = ((lines.get(i)).toCharArray())[j];
			}
		}
		
		printMatrix(matrix);
		
		Vector<Vector2D> vecOfVec = new Vector<Vector2D>();
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
			        Vector2D v = new Vector2D(i,j);
			        System.out.println(v.toString());
			        vecOfVec.add(v);
					dotindex++;
				}

			}
		}
		System.out.println(biggestRow);
		
		//recursion
		//set of cells
		Set<Vector2D> set;
		//list of all cells
		List<Set<Vector2D>> list = new ArrayList<Set<Vector2D>>();
		for(int i = 0; i<vecOfVec.size() ; i++)
		{
			set = new HashSet<Vector2D>();

			surr(vecOfVec.get(i), set, matrix);
			if(!set.isEmpty())
			{
				list.add(set);
			}
		}
		
		System.out.println(list);
		
		List<Set<Vector2D>> tranlated = new ArrayList<Set<Vector2D>>();
		
		for(int i = 0 ; i<list.size() ; i++)
		{
			Set<Vector2D> mySet = list.get(i);
			
			Vector<Vector2D> v = new Vector<Vector2D>();
			for(Vector2D vec : mySet)
			{
				v.add(vec);
			}
			int smallestX = v.get(1).x;
			int smallestY = v.get(1).y;
			for(Vector2D v2 : v)
			{
				if(v2.x <= smallestX && v2.y <= smallestY)
				{
					smallestX = v2.x;
					smallestY = v2.y;
				}
			}
			System.out.println(smallestX + "/" + smallestY);
			
			
		}
		
		
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

	
	//DFS
	public static void surr(Vector2D v, Set<Vector2D> h, char[][] matrix)
	{
		if((v.x)<0 || (v.y)<0)
			return;
		if(matrix[v.x][v.y]!='*')
			return;
		h.add(v);
		matrix[v.x][v.y] = ' ';
		
		Vector2D vector;
		surr(new Vector2D(v.x,(v.y)+1),h,matrix);
		surr(new Vector2D(v.x,(v.y)-1),h,matrix);
		surr(new Vector2D((v.x)+1,(v.y)),h,matrix);
		surr(new Vector2D((v.x)-1,(v.y)),h,matrix);
		surr(new Vector2D((v.x)-1,(v.y)+1),h,matrix);
		surr(new Vector2D((v.x)+1,(v.y)+1),h,matrix);
		surr(new Vector2D((v.x)-1,(v.y)-1),h,matrix);
		surr(new Vector2D((v.x)+1,(v.y)-1),h,matrix);
	}
	
}


