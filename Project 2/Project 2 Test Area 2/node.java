public class node<T> {

	// Data members
	// 1. Storing value of node
	T data;
	// 2. Storing address of next node
	node<T> next;

	// Parameterized constructor to assign value
	node(T data)
	{

		// This keyword refers to current object itself
		this.data = data;
		this.next = null;
	}
}
