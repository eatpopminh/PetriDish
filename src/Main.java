import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		//Reading the text.
		List<String> lines = Files.readAllLines(Paths.get("input.txt"));
		
		//System.out.println((lines.get(4)).toCharArray()[1]);
		
		//Finding length of Row and Column for the 2D Matrix
		//System.out.println("ROWS: "+lines.size());
		//Get each input into a matrix.
		int biggest = 0;
		for(int i = 0 ;i<lines.size(); i++)
		{
			int lengthOfRow = ((lines.get(i)).toCharArray()).length;
			if(lengthOfRow > biggest)
			{
				biggest = lengthOfRow;
			}
		}
		//System.out.println("Colunm: "+biggest);
		
		//2D matrix
		char[][] matrix = new char[lines.size()+2][biggest+2];
//		System.out.println("HELLO"+ lines.size());
//		System.out.println("HELLO"+ biggest);
//		System.out.println("matrix length: "+matrix.length);
//		System.out.println("matrix column length: " +matrix[1].length);
//		System.out.println("TESTING");
		
//		for(int i =0;i<biggest;i++)
//		{
//			System.out.println(i);
//			System.out.println((lines.get(i)));
//		}
		for(int i = 0 ; i< matrix.length-2 ; i++)
		{
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
		}
		
		//printMatrix(matrix);
		
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
			        //System.out.println(v.toString());
			        vecOfVec.add(v);
					dotindex++;
				}

			}
		}
		//System.out.println(biggestRow);
		
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
		
		//System.out.println(list);
		
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
			//System.out.println(smallestX+"COMEON"+ smallestY);
			for(Vector2D v2 : orderedVector)
			{
				if(v2.x <= smallestX )//&& v2.y<=smallestY)
				{
					smallestX = v2.x;
					smallestY = v2.y;
				}
			}
			//System.out.println("smallestX"+smallestX);
			for(Vector2D v2 : orderedVector)
			{
				if((v2.x==smallestX) && v2.y<= smallestY )//&& v2.y<=smallestY)
				{
					smallestY = v2.y;
				}
			}
			//add it to Set with translated orgin is 0,0.
//			System.out.println(smallestX + "/" + smallestY);
//			System.out.println(orderedVector);
			setTranslated = new HashSet<Vector2D>();
			for(Vector2D v2 : orderedVector)
			{
				int orginX = v2.x - smallestX;
				int orginY = v2.y - smallestY;
				
				Vector2D vecs = new Vector2D(orginX,orginY);
				//System.out.println(vecs.toString());
				setTranslated.add(vecs);
				
			}
			translated.add(setTranslated);
			
		}
		System.out.println("ME");
		//Everything in the OG matrix.
		System.out.println("OG points on matrix(list): "+list);
		//Everything translated to the orgin.
		System.out.println("Points moved to orgin(translated): "+translated);
				
		
		//List[] my_CellRot = new List[26]; 
		List<List<Set<Vector2D>>> my_CellRot = new ArrayList<List<Set<Vector2D>>>();
		
		List<Set<Vector2D>> setOfCell; 
		int alphabet = 0;
		char[][] newFile = new char[lines.size()+2][biggest+2];
		for(int i = 0 ; i<lines.size()+2 ; i++)
		{
			for(int j = 0 ; j<biggest+2 ;j++)
			{
				newFile[i][j] = ' ';
			}
		}

		System.out.println("BOB");
			
		
		//putting non-dups into an array. find dups and write it to file.
		for(int h = 0;h<translated.size();h++)//6
		{
			boolean flag = false;
			for(int j = 0 ; j<my_CellRot.size();j++)//26
			{
					for(int i = 0 ; i<my_CellRot.get(j).size() ; i++)
					{
						//System.out.println(translated.get(h)+"//" +my_CellRot[j].get(i));
						if(translated.get(h).equals((my_CellRot.get(j)).get(i)))
						{
							flag=true;
							//System.out.println("EQUALS");
//							System.out.println(list.get(h));
							//System.out.println(j);
							printToText(newFile,list.get(h),lines.size(),biggest,j);
							i=my_CellRot.get(j).size();
							j=my_CellRot.size()-1;
						}	
				}	
			}	
			if(flag==false)
			{
				setOfCell = new ArrayList<Set<Vector2D>>();
				setOfCell.add(translated.get(h));
				
				//Rotation and Mirrors
				DEGREE(setOfCell,0);
				DEGREE2(setOfCell,0);
				DEGREE3(setOfCell,0);
				 
				mirrorY(setOfCell);
				DEGREE(setOfCell,setOfCell.size()-1);
				DEGREE2(setOfCell,setOfCell.size()-1);
				DEGREE3(setOfCell,setOfCell.size()-1);
				
				mirrorX(setOfCell);
				DEGREE(setOfCell,setOfCell.size()-1);
				DEGREE2(setOfCell,setOfCell.size()-1);
				DEGREE3(setOfCell,setOfCell.size()-1);
				
				printToText(newFile,list.get(h),lines.size(),biggest,alphabet);
				my_CellRot.add(setOfCell);
				alphabet++;
			}
		}
		
		System.out.println("END");
		for(List a : my_CellRot)
		{
			System.out.println(a);
		}
		FileWriter write = new FileWriter("output.txt");

		String string="";
		for(int i = 0;i<lines.size();i++)
		{
			for(int l = 0;l<biggest;l++)
			{
				//System.out.print(newFile[i][l]);
				string+=newFile[i][l];
			}
			if(i<=lines.size()-2)
			{
				string+="\n";
			}
			System.out.print(string);
			write.write(string);
			string="";
		}
		write.close();
	}
	public static void shiftingToPos(Set<Vector2D> oneObject)
	{
		int smallestX = oneObject.iterator().next().x;
		int smallestY = oneObject.iterator().next().y;
		for(Vector2D eachVector : oneObject)
		{
			if(eachVector.x<=smallestX && eachVector.y<=smallestY)
			{
				smallestX = eachVector.x;
				smallestY = eachVector.y;
			}
		}

		//System.out.println(smallestX + " and "+ smallestY);
		for(Vector2D eachVector : oneObject)
		{
			eachVector.x = smallestX - eachVector.x;// - (smallestX);
			eachVector.y = smallestY - eachVector.y;// - (smallestY);
		}
		
	}
	public static void mirrorX(List<Set<Vector2D>> a)
	{
		Set<Vector2D> Vector = new HashSet<Vector2D>();
		Vector2D v ;
		for(Vector2D eachV:a.get(0))
		{
			v = new Vector2D((eachV.x),-(eachV.y));
			Vector.add(v);
		}
		Vector = makeTopLeftOrgin(Vector);
		a.add(Vector);
	}
	public static void mirrorY(List<Set<Vector2D>> a)
	{
		Set<Vector2D> Vector = new HashSet<Vector2D>();
		Vector2D v ;
		for(Vector2D eachV:a.get(0))
		{
			v = new Vector2D(-(eachV.x),eachV.y);
			Vector.add(v);
		}
		Vector = makeTopLeftOrgin(Vector);
		a.add(Vector);
	}
	public static List<Set<Vector2D>> DEGREE(List<Set<Vector2D>> a, int index)
	{
		Set<Vector2D> oneObject;
		oneObject = new HashSet<Vector2D>();
		
		for(Vector2D eachVector : a.get(index))
		{
			int temp = eachVector.x;
			Vector2D v = new Vector2D(-(eachVector.y),temp);
			oneObject.add(v);
		}
		
		//Set<Vector2D> oneObjectTemp = oneObject;
		oneObject = makeTopLeftOrgin(oneObject);
		//shiftingToPos(oneObject);
//		oneObject = makeTopLeftOrgin(oneObject);
		a.add(oneObject);
		//System.out.println("PUSSY"+a);
		return a;
	}
	public static List<Set<Vector2D>> DEGREE2(List<Set<Vector2D>> a,int index)
	{
		Set<Vector2D> oneObject;
		oneObject = new HashSet<Vector2D>();
		for(Vector2D eachVector : a.get(index))
		{
			int temp = eachVector.x;
			Vector2D v = new Vector2D(-(eachVector.y),temp);
			oneObject.add(v);
		}
		Set<Vector2D> oneObject2;
		oneObject2 = new HashSet<Vector2D>();
		for(Vector2D eachVector : oneObject)
		{
			int temp = eachVector.x;
			Vector2D v = new Vector2D(-(eachVector.y),temp);
			oneObject2.add(v);
		}

		oneObject = makeTopLeftOrgin(oneObject2);
		a.add(oneObject);
		return a;
	}
	public static List<Set<Vector2D>> DEGREE3(List<Set<Vector2D>> a,int index)
	{
		Set<Vector2D> oneObject;
		oneObject = new HashSet<Vector2D>();
		for(Vector2D eachVector : a.get(index))
		{
			int temp = eachVector.x;
			Vector2D v = new Vector2D(-(eachVector.y),temp);
			oneObject.add(v);
		}
		Set<Vector2D> oneObject2;
		oneObject2 = new HashSet<Vector2D>();
		for(Vector2D eachVector : oneObject)
		{
			int temp = eachVector.x;
			Vector2D v = new Vector2D(-(eachVector.y),temp);
			oneObject2.add(v);
		}
		Set<Vector2D> oneObject3;
		oneObject3 = new HashSet<Vector2D>();
		for(Vector2D eachVector : oneObject2)
		{
			int temp = eachVector.x;
			Vector2D v = new Vector2D(-(eachVector.y),temp);
			oneObject3.add(v);
		}

		oneObject3 = makeTopLeftOrgin(oneObject3);
		a.add(oneObject3);
		return a;
	}
	
	public static Set<Vector2D> makeTopLeftOrgin(Set<Vector2D> a)
	{
		int smallestX = a.iterator().next().x;
		int smallestY = a.iterator().next().y;
		for(Vector2D v : a)
		{
			if(v.x<=smallestX )//&& v.y<=smallestY)
			{
				smallestX = v.x;
				smallestY = v.y;
			}
		}
		for(Vector2D v: a)
		{
			if(v.x == smallestX && v.y<=smallestY)
			{
				smallestY = v.y;
			}
		}
		for(Vector2D v : a)
		{
			v.x = v.x - (smallestX);
			v.y = v.y - (smallestY);
		}
		return a;
		
	}
	public static void printToText(char[][] c, Set<Vector2D> a,int row, int colunm,int index)
	{
		char[] ch = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r'
				,'s','t','u','v','w','x','y','z'};
//		try {
			//FileWriter writer = new FileWriter("output.txt",true);
			for(Vector2D b : a)
			{
				//System.out.println(b.x);
				for(int i = 0 ; i<row+2 ; i++)
				{
					for(int j = 0 ; j<colunm+2 ;j++)
					{
					
						if(i==b.x && j==b.y)
						{
							c[i][j] = ch[index];
						}
					}
					
				}
			}
			
			//writer.close();
//		}catch(IOException e)
//		{
//			e.printStackTrace();
//		}
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

	
	//BFS
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


