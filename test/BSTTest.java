package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import bst.BST;

/**
 * Thuy Nguyen This class test the preOrder, inOrder, and postOrder methods that I wrote for BST.java -
 *  a class from Sedgewick July 08, 2014
 */
public class BSTTest
{
	private static BST<Integer, Integer> numTree;
	private static BST<String, Integer> stringTree;
	private static BST<Character, Integer> charTree;
	private static BST<Boolean, Integer> oneNodeTree;
	private static BST<Integer, String> emptyTree = new BST<>();

	@BeforeClass
	public static void setUp() throws Exception
	{
		// complete tree
		numTree = new BST<>();
		int[] array =
			{55, 25, 75, 15, 35, 65, 95, 12, 18, 32, 38, 62, 68, 92, 98, 8, 14, 16, 20 };

		for (int i = 0; i < array.length; i++)
		{
			numTree.put(array[i], i);
		}

		// tree missing right sub tree
		stringTree = new BST<>();
		String[] wordsArray =
			{"Math", "Is", "Beautiful" };

		for (int i = 0; i < wordsArray.length; i++)
		{
			stringTree.put(wordsArray[i], i);
		}

		// tree missing left sub tree
		charTree = new BST<>();
		char[] charArray =
			{'M', 'N', 'O', 'Q', 'P', 'Z' };

		for (int i = 0; i < charArray.length; i++)
		{
			charTree.put(charArray[i], i);
		}

		// tree with only root node
		oneNodeTree = new BST<>();
		oneNodeTree.put(true, 1);

	}

	@Test
	public void testPreOrderCompleteTree()
	{
		int[] expected =
			{55, 25, 15, 12, 8, 14, 18, 16, 20, 35, 32, 38, 75, 65, 62, 68, 95, 92, 98 };
		int key = 0;

		for (int actualKey : numTree.preOrder())
		{
			assertEquals(expected[key], actualKey);
			key++;
		}

	}

	@Test
	public void testPreOrderLeftLinkedTree()
	{
		String[] expected =
			{"Math", "Is", "Beautiful" };
		int key = 0;
	
		for (String actualKey : stringTree.preOrder())
		{
			assertEquals(expected[key], actualKey);
			key++;
		}
		
	}

	@Test
	public void testPreOrderRightLinked()
	{
		char[] expected =
			{'M', 'N', 'O', 'Q', 'P', 'Z' };
		int key = 0;

		for (char actualKey : charTree.preOrder())
		{
			assertEquals(expected[key], actualKey);
			key++;
		}

	}

	@Test
	public void testPreOrderOneNode()
	{
		boolean[] expected = {true};
		int key = 0;

		for (boolean actualKey : oneNodeTree.preOrder())
		{
			assertEquals(expected[key], actualKey);
			key++;
		}

	}

	@Test
	public void testPreOrderEmptyTree()
	{
		StringBuilder sb = new StringBuilder();

		for (int actualKey : emptyTree.preOrder())
		{
			sb.append(actualKey);
		}

		assertTrue(sb.length() == 0);

	}

	@Test
	public void testInOrderCompleteTree()
	{
		int[] expected =
			{8, 12, 14, 15, 16, 18, 20, 25, 32, 35, 38, 55, 62, 65, 68, 75, 92, 95, 98 };
		int key = 0;

		for (int actualKey : numTree.inOrder())
		{
			assertEquals(expected[key], actualKey);
			key++;
		}

	}

	@Test
	public void testInOrderLeftLinkedTree()
	{
		String[] expected =
			{"Beautiful", "Is", "Math" };
		int key = 0;

		for (String actualKey : stringTree.inOrder())
		{
			assertEquals(expected[key], actualKey);
			key++;
		}

	}

	@Test
	public void testInOrderRightLinkedTree()
	{
		char[] expected =
			{'M', 'N', 'O', 'P', 'Q', 'Z' };
		int key = 0;

		for (char actualKey : charTree.inOrder())
		{
			assertEquals(expected[key], actualKey);
			key++;
		}

	}

	@Test
	public void testInOrderOneNode()
	{
		boolean[] expected = {true};
		int key = 0;

		for (boolean actualKey : oneNodeTree.inOrder())
		{
			assertEquals(expected[key], actualKey);
			key++;
		}

	}

	@Test
	public void testInOrderEmptyTree()
	{
		StringBuilder sb = new StringBuilder();

		for (int actualKey : emptyTree.inOrder())
		{
			sb.append(actualKey);
		}

		assertTrue(sb.length() == 0);
		
	}

	@Test
	public void testPostOrderCompleteTree()
	{
		int[] expected =
			{8, 14, 12, 16, 20, 18, 15, 32, 38, 35, 25, 62, 68, 65, 92, 98, 95, 75, 55 };
		int key = 0;

		for (int actualKey : numTree.postOrder())
		{
			assertEquals(expected[key], actualKey);
			key++;
		}

	}

	@Test
	public void testPostOrderLeftLinkedTree()
	{
		String[] expected =
			{"Beautiful", "Is", "Math" };
		int key = 0;

		for (String actualKey : stringTree.postOrder())
		{
			assertEquals(expected[key], actualKey);
			key++;
		}

	}

	@Test
	public void testPostOrderRightLinkedTree()
	{
		char[] expected =
			{'P', 'Z', 'Q', 'O', 'N', 'M' };
		int key = 0;

		for (char actualKey : charTree.postOrder())
		{
			assertEquals(expected[key], actualKey);
			key++;
		}

	}

	@Test
	public void testPostOrderOneNode()
	{
		boolean[] expected = {true};
		int key = 0;

		for (boolean actualKey : oneNodeTree.postOrder())
		{
			assertEquals(expected[key], actualKey);
		}

	}

	@Test
	public void testPostOrderEmptyTree()
	{
		StringBuilder sb = new StringBuilder();

		for (int actualKey : emptyTree.postOrder())
		{
			sb.append(actualKey);
		}

		assertTrue(sb.length() == 0);
	}

}
