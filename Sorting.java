import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Sorting {
	final int MAX_SIZE = 10000000;

	// Set this to true if you wish the arrays to be printed.
	final static boolean OUTPUT_DATA = false;
	
	public static String sortAlg= null;
	public static  int size = 0;
	
	public static void main(String[] args) {
		readInput();
		int [] data = new int[size];
		GenerateSortedData(data, size);
		Sort(data, size,"Sorted Data");

		GenerateNearlySortedData(data, size);
		Sort(data, size,"Nearly Sorted Data");
		
		GenerateReverselySortedData(data, size);
		Sort(data, size,"Reversely Sorted Data");
		
		GenerateRandomData(data, size);
		Sort(data, size,"Random Data");
		
		System.out.println("\nProgram Completed Successfully.");
		
	}
	
	@SuppressWarnings("resource")
	public static void readInput(){
		System.out.println("  I:\tInsertion Sort");
		System.out.println("  M:\tMergeSort");
		System.out.println("  Q:\tQuickSort");
		System.out.println("  S:\tSTLSort");
	    System.out.println("Enter sorting algorithm:");
	    Scanner reader = new Scanner(System.in);
	    sortAlg = reader.next();
	    System.out.println(sortAlg);
		String sortAlgName = "";
		
		if(sortAlg.equals("I"))
			sortAlgName = "Insertion Sort";
		else if(sortAlg.equals("M"))
			sortAlgName = "MergeSort";
		else if(sortAlg.equals("Q"))
			sortAlgName = "QuickSort";
		else if(sortAlg.equals("S"))
			sortAlgName = "STLSort";
		else {
			System.out.println("Unrecognized sorting algorithm Code:"+sortAlg);
			System.exit(1);
		}
		System.out.println("Enter input size: ");
	    size = reader.nextInt();
		System.out.println("\nSorting Algorithm: " + sortAlgName);
        System.out.println("\nInput Size = "  + size);
	}
	
	/******************************************************************************/

	public static void GenerateSortedData(int data[], int size)
	{
		int i;
		
		for(i=0; i<size; i++)
			data[i] = i * 3 + 5;
	}
	/*****************************************************************************/
	public static void GenerateNearlySortedData(int data[], int size)
	{
		int i;
		
		GenerateSortedData(data, size);
		
		for(i=0; i<size; i++)
			if(i % 10 == 0)
				if(i+1 < size)
					data[i] = data[i+1] + 7;
	}
	/*****************************************************************************/

	public static void GenerateReverselySortedData(int data[], int size)
	{
		int i;
		
		for(i = 0; i < size; i++)
			data[i] = (size-i) * 2 + 3;
	}
	/*****************************************************************************/

	public static void GenerateRandomData(int data[], int size)
	{
		int i;
		for(i = 0; i < size; i++)
			data[i] = new Random().nextInt(10000000);
	}
	/*****************************************************************************/

	public static int depth=0;

	public static void Sort(int[] data, int size,  String string)
	{
		int parentDepth=0;		
		System.out.print("\n"+string+":");
		if (OUTPUT_DATA)
		{
			printData(data, size, "Data before sorting:");
		}

		// Sorting is about to begin ... start the timer!
		long start_time = System.nanoTime();
			if (sortAlg.equals("I"))
			{
			InsertionSort(data, 0,size-1);
			}
			else if (sortAlg.equals("M"))
			{
			MergeSort(data, 0, size-1);
			}
			else if (sortAlg.equals("Q"))
			{
			depth=QuickSort(data, 0, size-1,parentDepth);
			System.out.print("\n Recursion Depth: ");
			System.out.print(depth);
			}
			else if (sortAlg.equals("S"))
			{
			STLSort(data, size);
			}
		else
		{
			System.out.print("Invalid sorting algorithm!");
			System.out.print("\n");
			System.exit(1);
		}

		// Sorting has finished ... stop the timer!
		
		double elapsed = System.nanoTime()-start_time;
		elapsed=elapsed/1000000;


		if (OUTPUT_DATA)
		{
			printData(data, size, "Data after sorting:");
		}


		if (IsSorted(data, size))
		{
			System.out.print("\nCorrectly sorted ");
			System.out.print(size);
			System.out.print(" elements in ");
			System.out.print(elapsed);
			System.out.print("ms");
		}
		else
		{
			System.out.print("ERROR!: INCORRECT SORTING!");
			System.out.print("\n");
		}
		System.out.print("\n-------------------------------------------------------------\n");
	}
	
	/*****************************************************************************/

	public static boolean IsSorted(int data[], int size)
	{
		int i;
		
		for(i=0; i<(size-1); i++)
		{
			if(data[i] > data[i+1])
				return false;
		}
		return true;
	}
	
	/*****************************************************************************/

	public static void InsertionSort(int data[], int lo, int hi)
	{
		//Write your code here		
		for(int i=lo+1;i<=hi;i++)
		{
			int j = i-1;
			int temp = data[i];
			while(j>=lo && data[j]>temp)
			{
				data[j+1]=data[j];
				j=j-1;
			}
			data[j+1]=temp;
		}		
	}

	/*****************************************************************************/
	public static void Merge(int data[],int lo,int mid,int hi){
		
		int n1=mid-lo+1;
		int n2=hi-mid;
		int[] left_arr = new int[n1];
		int[] right_arr=new int[n2];

		for (int a = 0; a < n1; a++){
        left_arr[a] = data[lo + a];
		}

    	for (int b = 0; b < n2; b++){
        right_arr[b] = data[mid + 1 + b];
		}

		int i=0;
		int j=0;

		for(int k=lo;k<=hi;k++){
			if(i==n1){
				data[k]=right_arr[j];
				j++;
			}
			else if(j==n2){
				data[k]=left_arr[i];
				i++;
			}
			else if(left_arr[i]<right_arr[j]){
				data[k]=left_arr[i];
				i++;
			}
			else{
				data[k]=right_arr[j];
				j++;
			}
		}
	}

	public static void MergeSort(int data[], int lo, int hi)
	{
		//Write your code here
		//You may create other functions if needed 
		//System.out.println("MergeSort");
		
		int mid;
		if (lo==hi){
			return;
		}
		mid=(lo+hi)/2;
		MergeSort(data, lo, mid);
		MergeSort(data, mid+1, hi);
		Merge(data,lo,mid,hi);
		
	}
	/*****************************************************************************/
	public static void random(int data[],int lo,int hi)
    {
		Random rand= new Random();

        // Code for selecting one random pivot
        int rand_pivot = rand.nextInt(hi-lo)+lo;
        
        //Code for selecting median of three pivots
        // int[] randompivots = new int[3];
        // for (int i = 0; i < 3; i++) {
        //     randompivots[i] = rand.nextInt(hi-lo)+lo;
        // }
        // Arrays.sort(randompivots);
        // int rand_pivot=randompivots[1];    
         
        int temp=data[rand_pivot]; 
        data[rand_pivot]=data[hi];
        data[hi]=temp;  
    }

	public static int Partition(int data[], int lo, int hi){
			
		//Code for random pivot
		random(data, lo, hi);
		int pivot=data[hi];	

		int i=lo-1;
		for(int j=lo;j<=hi-1;j++){
			if(data[j]<pivot){
				swap(j,i+1,data);
				i++;
			}
		}
		swap(i+1,hi,data);		
		return i+1;
	}

	public static int QuickSort(int data[], int lo, int hi, int parentDepth)
	{
		//Write your code here
		//You may create other functions if needed 
		//System.out.println("QuickSort");
		int childDepth;		
		childDepth = parentDepth + 1;	
		if(lo>=hi){	
			return;
		}	
		if(hi-lo+1<=40){
			InsertionSort(data,lo,hi);
			return childDepth-1;
		}
		else{
			int mid = Partition(data,lo,hi);		
			int left=QuickSort(data, lo, mid-1,childDepth);
			int right=QuickSort(data, mid+1, hi,childDepth);	
			return Math.max(left,right);
		}	
	}
	/*****************************************************************************/

	public static void STLSort(int data[], int size)
	{
		//Write your code here
		//Your code should simply call the STL sorting function  
		//System.out.println("STLSort");
		Arrays.sort(data, 0, size);
	}
	/*****************************************************************************/
	
	public static void swap(int x , int y ,int data[])
	{
		int temp = data[x];
		data[x] = data[y];
	    data[y] = temp;
	}
	
	/*****************************************************************************/
	
	public static void printData(int[] data, int size, String title)
	{
		int i;

		System.out.print("\n");
		System.out.print(title);
		System.out.print("\n");
		for (i = 0; i < size; i++)
		{
			System.out.print(data[i]);
			System.out.print(" ");
			if (i % 10 == 9 && size > 10)
			{
				System.out.print("\n");
			}
		}
	}
}
