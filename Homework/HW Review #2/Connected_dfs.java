import java.util.*;
import java.io.*;
// Creating class
public class Connected_dfs {
// Creating variables
int adjacency_matrix[][];
int max_size;
int cur_size;
Stack<Integer> stack; // Creating stack
// Creating constructors
Connected_dfs(){
max_size = 10;
adjacency_matrix = new int[10+1][10+1]; //I am Not using 0th position that's why i increase it to 1
stack = new Stack<Integer>();
}
// Overloading constructor
Connected_dfs(int size){
max_size = size;
adjacency_matrix = new int[size+1][size+1];
stack = new Stack<Integer>();
}
// Creating graph i.e adjacency matrix
void createGraph() throws Exception {
BufferedReader br = new BufferedReader ( new FileReader("sample3.txt")); // Reading graph file
int size = Integer.parseInt(br.readLine());
max_size = size;
String s;
String list[];
cur_size = 0;
while((s = br.readLine()) !=null){
list = s.split(" "); // spliting line based on space
for (int i=0;i<list.length;i++){
adjacency_matrix[cur_size+1][Integer.parseInt(list[i])] = 1; // Making path
adjacency_matrix[Integer.parseInt(list[i])][cur_size+1] = 1; // Making path
}
cur_size++;
}
}
// Checking if a graph is connected or not
void isConnected(int source){
int i, element;
int[] visited_list = new int[ max_size + 1]; // visited list
visited_list[source] = 1;
stack.push(source);
while (!stack.isEmpty())
{
element = stack.pop();
i = 1;
while (i <= max_size)
{
if (adjacency_matrix[element][i] == 1 && visited_list[i] == 0)
{
stack.push(i);
visited_list[i] = 1;
}
i++;
}
}
int count=0;
System.out.println("visited_Nodes :"); // Printing visited nodes
for (i = 1; i <= max_size; i++){
if (visited_list[i] == 1)
{
System.out.print(i +"\t");
count++;
}
}
// Checking whether a graph is connected or not
if(count == max_size){
System.out.println("\nGraph is Connected.");
}
else{
System.out.println("\nGraph is not Connected.");
}
}
public static void main(String[] args) throws Exception{
Connected_dfs graph = new Connected_dfs(); // crating object of class
// calling functions
graph.createGraph();
graph.isConnected(1); // Passing source vertex as 1
}
}