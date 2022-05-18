Q1. How does your program find the shortest paths from the source cryptocurrency to others? Explain your algorithm.

Using bfs algorithm.
As we are finding the shortest path in terms of the number of edges between two node bfs will work.

Q2. What is the time and space complexity of the shortest-path algorithm in your answer above? Explain your answer. 

The complexity of BFS implemented using an Adjacency List is O(|V| + |E|).

This is mainly because every time we want to find what are the edges adjacent to a given vertex 'U', we would have to traverse the whole array AdjacencyMatrix[U], which is ofcourse of length |V|.

Q3. How does your program sort the (cryptocurrency, net trades count) pairs? Explain your algorithm.

After storing net trades count in a string list with the (node: weight) format, I have used List.sort() method with a comparator which splits the weight out and compares two string in terms of weight.

Q4. What is the time and space complexity of the sorting algorithm in your answer above? Explain your answer. 

Since java List.sort() is a merge sort algorithm, time complexity is O(n*log(n)) and space complexity us O(n).
