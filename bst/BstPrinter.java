package bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Thuy Nguyen
 * This project prints out a binary tree of any size
 * However, the console might not display it due to the console's size limitations
 * July  8, 2014
 */
public class BstPrinter
{

	public static void main(String[] args)
	{
		int[] numbers1 = {55, 55, -1, 25, 75, 15, 35, 65, 95, 12, 18, 32, 38, 62, 68, 92, 98, 8, 14, 16, 20, 61, 63, 73, 97, 99, 7, 9, 8};
		printTree("Big Binary Search Tree", numbers1);

		int[] numbers2 = {50, 60, 70, 80, 75, 90, 74, 73};
		printTree("Right Linked Tree", numbers2);

		int[] numbers3 = {10, 9, 8, 7, 6, 5};
		printTree("Left Linked Tree", numbers3);
		
		int[] numbers4 = {9};
		printTree("One Node Tree", numbers4);

	}

	private static void printTree(String treeName, int[] numbers)
	{
		System.out.printf("%s:\n\n", treeName);
		printTree(numbers);
		
	}

	private static void printTree(int[] numbers)
	{
		System.out.println(Arrays.toString(numbers));
		
		// make a new binary search tree and put in the numbers from the array
		BST<Integer, Integer> tree = new BST<>();
		for (int i = 0; i < numbers.length; i++)
		{
			tree.put(numbers[i], i);
		}

		printBST(tree);
		System.out.printf("%n%n%n%n");
		
	}
	
	public static void printBST(BST<Integer, ?> tree)
	{
		if (tree.isEmpty())
		{
			return;
		}

		// put elements into array by level order
		int maxSlots = (int) Math.pow(2, tree.size());
		Integer[] levelOrder = new Integer[maxSlots];  // store each node and its two child nodes beginning at index 1
		putElementsIntoArray(tree, levelOrder);

		// calculate height of tree
		int maxIndex = lastIndexOfTree(levelOrder);
		final int height = calculateHeight(maxIndex);

		// group elements into their respective levels
		List<Integer[]> levelGroup = new ArrayList<>();
		int totalElements = tree.size();
		groupsElementByLevel(totalElements, height, levelOrder, levelGroup);

		// print each group level in binary tree format
		printNumbersByLevel(height, levelGroup);

	}

	private static void putElementsIntoArray(BST<Integer, ?> tree,
			Integer[] levelOrder)
	{
		int[] treeNumArray = new int[tree.size()];  // put all of numbers into an array by level order
		int index = 0;

		for (int num : tree.levelOrder())
		{
			treeNumArray[index] = num;
			index++;
		}

		levelOrder[1] = treeNumArray[0];  // put the root number into levelOrder array - keeping track of each number's appropriate place/index
		for (int i = 1; i < treeNumArray.length; i++)  // put each number of tree into its appropriate index
		{
			int number = treeNumArray[i];
			boolean putIn = false;
			int root = 1;  // start comparison at top of tree
			while (!putIn)
			{
				if (number < levelOrder[root])  // if number is smaller than root
				{
					if (levelOrder[2*root] == null)  // if the root doesn't have a left child
					{
						levelOrder[2*root] = number;  // put number into root's left child index
						putIn = true;
					}
					else   // if root does have a left child, then the new root comparison is that left child
					{
						root = 2*root;
					}
				}
				else  // else if number is greater than root
				{
					if (levelOrder[2*root+1] == null)  // if root does not have right child
					{
						levelOrder[2*root+1] = number;  // put number into root's right child index
						putIn = true;
					}
					else  // if root does have a right child
					{
						root = 2*root+1;   // the right child becomes the new root comparison
					}
				}
			}
		}

	}

	private static int lastIndexOfTree(Integer[] levelOrder)
	{
		// find where the last number is in the levelOrder array
		// this is how we can find the height of the tree later on
		int maxIndex = 0;
		for (int i = levelOrder.length-1; i >= 0; i--)  // start looking at the end of the array
		{
			if (levelOrder[i] != null)   // the first array slot that is not null is where the last number is
			{
				maxIndex = i;
				break;
			}
		}
		return maxIndex;

	}

	private static int calculateHeight(int maxIndex)
	{
		int level = maxIndex;  // maxIndex is where the last number is in the levelOrder array
		int height = 0;
		
		while (level > 1)
		{
			level = level >> 1;   // take half each time to get to the upper level of the tree
			height++;                // each level increases the height of the tree
		}

		return height;

	}

	private static void groupsElementByLevel(int totalElements, int height,
			Integer[] levelOrder, List<Integer[]> levelGroup)
	{
		// each number is grouped with other numbers in its same level
		for (int level = 0; level <= height; level++)
		{
			int startingAt = (int) Math.pow(2, level);
			int nextLevel = (int) Math.pow(2, level+1);
			Integer[] group = Arrays.copyOfRange(levelOrder, startingAt, nextLevel);
			levelGroup.add(group);
		}

	}

	private static void printNumbersByLevel(final int height,
			List<Integer[]> levelGroup)
	{
		for (int level = height, group = 0; level >= 0; level--, group++)  // print each group level
		{
			int spaces = (int) Math.pow(2, level)*2;  // space the tree out to the right
			for (int i = 0; i < levelGroup.get(group).length; i++)  // print each number in group.
			{
				String number = "";
				if (levelGroup.get(group)[i] != null) // only print out the number, if null, leave blank
				{
					number = String.format("%02d", levelGroup.get(group)[i]);  // format number so each number has 2 digits (8 --> 08)
				}

				System.out.print(String.format("%" + spaces + "s", number));  // print the number
				spaces = (int) Math.pow(2, level)*2*2;   // space out the next level order number
			}
			
			System.out.println();

			if (group != height)
			{
				printEdges(levelGroup, level, group);  // print edges according to level
			}

		}

	}

	private static void printEdges(List<Integer[]> levelGroup, int level, int group)
	{
		int edgeHeight = (int) Math.pow(2, level);  // the spaces needed for all the edges
		int rightChildAt = 3;  // right child edge at 3 spaces after left child edge initially
		int spacesBetwnTrees = (int) Math.pow(2, level)*2-1;  // spaces between level order subtrees

		for (int i = 2; i <= edgeHeight; i += 2)  // iterate through for all edge height
		{
			int leftChildAt = (int) Math.pow(2, level)*2 - i;  // start edge one space before character on upper line

			for (int currentNumberAt = 0, childAt = 0; currentNumberAt < levelGroup.get(group).length; currentNumberAt++, childAt += 2) //  for each number in the group, print out its edges if it has children
			{
				System.out.print(String.format("%" + leftChildAt + "s",
						levelGroup.get(group+1)[childAt] == null? "" : "/"));  //  if there is a left child, print left edge

				System.out.print(String.format("%" + rightChildAt + "s",
						levelGroup.get(group+1)[childAt+1] == null? "" :	"\\" ));  // if there is a right child, print right edge

				System.out.print(String.format("%" + spacesBetwnTrees + "s", ""));  // print spaces for next level order sub tree; to the right of current tree
			}

			System.out.println();
			rightChildAt += 4;  // increase spacing of stems between children
			spacesBetwnTrees -= 2;  // decrease spacing of stems between level order sub trees
		}
		
	}

}
