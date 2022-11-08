import java.io.File;

import java.util.Scanner;

import java.util.Stack;

public class DFS {

private Stack<Integer> stack;

private int adjacency_matrixs[][];

int currentSize;

int maxSize;

public DFS() {

stack = new Stack<Integer>();

adjacency_matrixs = new int[10][];

}

public DFS(int maxSize) {

stack = new Stack<Integer>();

adjacency_matrixs = new int[maxSize][];

}

boolean isEmpty() {

return adjacency_matrixs.length > 0 ? false : true;

}

public void dfs(int adjacency_matrix[][], int source) {

int number_of_nodes = adjacency_matrix[source].length - 1;

int visited[] = new int[number_of_nodes];

int element = source;

int i = source;

System.out.print(element + "\t");

visited[source] = 1;

stack.push(source);

while (!stack.isEmpty()) {

element = stack.peek();

i = element;

while (i <= number_of_nodes) {

if (adjacency_matrix[element][i] == 1 && visited[i] == 0) {

stack.push(i);

visited[i] = 1;

element = i;

i = 1;

System.out.print(element + "\t");

continue;

}

i++;

}

stack.pop();

}

boolean connected = false;

for (int vertex = 1; vertex <= number_of_nodes; vertex++) {

if (visited[vertex] == 1) {

connected = true;

} else {

connected = false;

break;

}

}

if (connected) {

System.out.println("The graph is connected");

} else {

System.out.println("The graph is disconnected");

}

}

public static void main(String... arg) {

int adjacency_matrix[][] = null;

int number_of_nodes, source;

File file = new File("sample2.txt");

Scanner sc;

try {

sc = new Scanner(file);

number_of_nodes = sc.nextInt();

adjacency_matrix = new int[number_of_nodes][number_of_nodes];

// initialise the array with default value as 0.

for (int i = 0; i < number_of_nodes; i++)

for (int j = 0; j < number_of_nodes; j++)

adjacency_matrix[i][j] = 0;

System.out.println("sc.nextLine()" + sc.nextLine());

int i = 0;

while (sc.hasNextLine()) {

String[] elements = sc.nextLine().split(" ");

for (int j = 0; j < elements.length; j++) {

adjacency_matrix[i][j] = Integer.parseInt(elements[j]);

}

i++;

}

} catch (Exception e) {

e.printStackTrace();

}

// lets take the adjacency_matrix[0][0] as the source vertex as nothing is

// mentioned in question.

source = 1;// adjacency_matrix[0][0];

System.out.println("The DFS Traversal for the graph is given by ");

DFS dfs = new DFS();

dfs.dfs(adjacency_matrix, source);

}

}