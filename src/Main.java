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
		System.out.println("Lines size: "+lines.size());
//		for(String c : lines)
//			System.out.println(c);
		
		//System.out.println((lines.get(4)).toCharArray()[1]);
		
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
		System.out.println("Colunm: "+biggest);
		
		//2D matrix
		char[][] matrix = new char[lines.size()+2][biggest+2];
		System.out.println("HELLO"+ lines.size());
		System.out.println("HELLO"+ biggest);
		System.out.println("matrix length: "+matrix.length);
		System.out.println("matrix column length: " +matrix[1].length);
		System.out.println("TESTING");
		
//		for(int i =0;i<biggest;i++)
//		{
//			System.out.println(i);
//			System.out.println((lines.get(i)));
//		}
		for(int i = 0 ; i< matrix.length-2 ; i++)
		{
			//System.out.println(lines.get(i).toCharArray()[0]);
//			for(int j = 0 ; j <matrix[i].length+1 ;j++)
//			{
				String temp = lines.get(i);
				char[] chartemparray = temp.toCharArray();
				int j=0;
				for(char a : chartemparray)
				{
					
					matrix[i][j] = a;
					j++;
				}
				//char chartemp = chartemparray[1];
//				matrix[i][0] = chartemp;
//				matrix[i][j] = ((lines.get(i)).toCharArray()[j]);
//			}
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
		//set of each cell.
		Set<Vector2D> set;
		//list of all cells(cells in a set).
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
		
		//Translation all points to the orgin.
		List<Set<Vector2D>> translated = new ArrayList<Set<Vector2D>>();
		Set<Vector2D> setTranslated = null;
		
		for(int i = 0 ; i<list.size() ; i++)
		{
			//elements are random in a set.
			Set<Vector2D> mySet = list.get(i);
			
			//put the cells in this so its in order.
			Vector<Vector2D> orderedVector = new Vector<Vector2D>();
			
			for(Vector2D vec : mySet)
			{
				orderedVector.add(vec);
			}
			//System.out.println(orderedVector);
			//Finding the most left top dot
			int smallestX = orderedVector.get(0).x;
			int smallestY = orderedVector.get(0).y;
			for(Vector2D v2 : orderedVector)
			{
				if(v2.x <= smallestX && v2.y<=smallestY)
				{
					smallestX = v2.x;
					smallestY = v2.y;
				}
			}
			//add it to Set with translated orgin is 0,0.
			//System.out.println(smallestX + "/" + smallestY);
			
			setTranslated = new HashSet<Vector2D>();
			for(Vector2D v2 : orderedVector)
			{
//				int nX = -(smallestX);
//				int nY = -(smallestY);
				
				int orginX = (v2.x) - smallestX;
				int orginY = (v2.y) - smallestY;
				
				Vector2D vecs = new Vector2D(orginX,orginY);
				setTranslated.add(vecs);
				
			}
			translated.add(setTranslated);
			
		}
		System.out.println("ME");
		//Everything in the OG matrix.
		System.out.println("OG points on matrix(list): "+list);
		//Everything translated to the orgin.
		System.out.println("Points moved to orgin(translated): "+translated);
		
		
		//Hashtable<Integer,Set<List<Set<Vector2D>>>>  my_CellRot = new Hashtable<Integer,Set<List<Set<Vector2D>>>>();
		
		
		//List<Set<Vector2D>> translated = new ArrayList<Set<Vector2D>>();
		List<List> my_CellRot = new ArrayList<List>();
		List<Set<Vector2D>> setOfCell; //= new ArrayList<Set<Vector2D>>();
		int alphabet = 1;
		
		setOfCell = new ArrayList<Set<Vector2D>>();
		setOfCell.add(translated.get(0));
		my_CellRot.add(0,setOfCell);		
		//first
//		setOfCell.add(translated.get(0));
//		System.out.println(setOfCell);
//		//rot of first
//		setOfCell.add(translated.get(2));
//		System.out.println(setOfCell);
//		
//		my_CellRot.add(0,setOfCell);
		
//		setOfCell = new ArrayList<Set<Vector2D>>();
//		setOfCell.add(translated.get(3));
//		my_CellRot.add(1,setOfCell);
		
//		System.out.println(my_CellRot);
//		System.out.println(my_CellRot.get(0).get(0).equals(my_CellRot.get(0).get()));
//
//		System.out.println(my_CellRot.get(0).get(1));
		System.out.println("BOB");
		
		//System.out.println(my_CellRot[0].get);
		
		System.out.println(my_CellRot.size());
		for(Set a : translated)
		{
			for(List b : my_CellRot)
			{
				//System.out.println(b);

				for(int i = 0 ; i<b.size() ; i++)
				{
					//System.out.println(b.get(i));
					System.out.println("Comparing "+a+" with "+b.get(i));
					
					if(a.equals(b.get(i)))
					{
						System.out.println("YES");
						System.out.println(my_CellRot.indexOf(b));
					}
					else
					{
						System.out.println("NO");
						setOfCell = new ArrayList<Set<Vector2D>>();
						setOfCell.add(a);
						my_CellRot.add(alphabet,setOfCell);
						alphabet++;
						
						
					}
				}
			}
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


