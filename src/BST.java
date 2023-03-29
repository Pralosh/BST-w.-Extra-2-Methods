import java.util.Scanner;

public class BST<Key extends Comparable<Key>, Value>
{
	Node root;

	class Node
	{
		Key key;
		Value value;
		Node left;
		Node right;

		public Node(Key key, Value value)
		{
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString()
		{
			return "[" + key + ", " + value + "]";
		}

	}

	public void put(Key key, Value value)
	{
		root = put(root, key, value);
	}

	private Node put(Node x, Key key, Value value)
	{
		if (x == null)
			return new Node(key, value);

		int cmp = key.compareTo(x.key);

		if (cmp < 0)
			x.left = put(x.left, key, value);
		else
			if (cmp > 0)
				x.right = put(x.right, key, value);
			else
				x.value = value;
		return x;
	}

	public String toString()
	{
		return toString(root);
	}

	public String toString(Node root)
	{
		if (root == null)
			return "";

		return toString(root.left) + " " + root.toString() + toString(root.right);
	}

	public Value get(Key key)
	{
		return get(root, key);
	}

	public Value get(Node root, Key key)
	{
		if (root == null)
			return null;

		if (key.compareTo(root.key) < 0)
			return get(root.left, key);
		else
			if (key.compareTo(root.key) > 0)
				return get(root.right, key);
			else
				return root.value;
	}

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		BST<Character, Integer> bst = new BST();

		while (true)
		{
			System.out.println("1. put");
			System.out.println("2. get");
			System.out.println("3. put multiple");
			System.out.println("9. toString");

			switch (in.nextLine())
			{
			case "1":
				System.out.println("Please enter key and value");
				bst.put(in.nextLine().charAt(0), Integer.valueOf(in.nextLine()));
				break;
			case "2":
				System.out.println("Please enter key");
				System.out.println("Value: " + bst.get(in.nextLine().charAt(0)));
				break;
			case "3":
				System.out.println("Please enter multiple keys");
				String keys = in.nextLine();
				int i = 0;
				for (char c : keys.toCharArray())
					bst.put(c, i++);
				break;
			case "9":
				System.out.println(bst);
				break;
			case "99":
				break;
			}
		}
	}
}