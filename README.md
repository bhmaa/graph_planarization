# graph_planarization

A little helper to complete **homework #4 "Graph planarization"** in *discrete mathematics* at ITMO University in the second semester.
An example of the work performed using this program is included.
The program doesn't draw graphs, but maybe later this feature will be added.

## How to use it

In the main method of the Main class, you must set the adjacency matrix for your graph as a variable named graph. Weights can be different from 1. 
After launching the program, a veeeery long message will be displayed in the console, which, in fact, contains the complete solution.
You should pay attention to strings like this:

`max value of alpha = 11 and it gets by pair of psi(3)={1, 8, 10, 11, 12, 16} and psi(8)={2, 3, 4, 6, 7}`

Unfortunately, you will have to build the graph yourself. The edges included in the Hamiltonian cycle should be located in a circle (the first line of the output). 
Edges from the first set should be drawn inside the circle, from the second set outside. 

Also, if you happen to receive the message: `remained one set...`, then you should draw a graph with edges included in the Hamiltonian 
cycle and edges listed in the message.

To build a graph, you can use online resources, 
I recommend [this site](https://graphonline.ru/create_graph_by_matrix).
So, you should get m (graph thickness) images similar to this:

![yeah, like this](https://sun9-87.userapi.com/s/v1/ig2/HZonyL6-dHZn-WFiFgL3ru_Tq2A-83aCuATj4WdvfGsK02NYMDOG5wQlAEniFdxoYiGUW5V8QEraKCC9uP2uA8fD.jpg?size=604x412&quality=96&type=album)

And that's the end of your work.
