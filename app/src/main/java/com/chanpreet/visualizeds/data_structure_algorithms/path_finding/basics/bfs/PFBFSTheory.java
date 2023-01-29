package com.chanpreet.visualizeds.data_structure_algorithms.path_finding.basics.bfs;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class PFBFSTheory extends AlgorithmTheory {
    public PFBFSTheory() {
        super.theory = TextBuilder.makeBulletList(
                "BFS (Breadth-First Search) is an algorithm used to traverse or search a graph or tree data structure.",
                "It starts at the root node and explores all the neighboring nodes before moving to the next level.",
                "BFS visits all nodes in a breadth-wise manner and uses a queue data structure to keep track of the nodes to be visited next.",
                "It is used for finding the shortest path between two nodes, solving puzzles and finding connected components in a graph.");

        super.algorithm =
                TextBuilder.makeOrderedList("Initialize a queue and add the starting node to it.",
                        "Mark the starting node as visited.",
                        "While the queue is not empty:<br>"+
                        "\ta. Dequeue a node from the queue and set it as the current node.<br>"+
                        "\tb. For each unvisited neighbor of the current node:<br>"+
                        "\t\ti. Mark the neighbor as visited.<br>"+
                        "\t\tii. Add the neighbor to the queue.",
                        "Repeat steps 3a to 3b until all nodes have been visited.");
        super.code = "#include <bits/stdc++.h>\n" +
                "using namespace std;\n" +
                "\n" +
                "const int N = 100005;\n" +
                "vector<int> adj[N];\n" +
                "bool vis[N];\n" +
                "\n" +
                "void bfs(int s) {\n" +
                "  queue<int> q;\n" +
                "  q.push(s);\n" +
                "  vis[s] = true;\n" +
                "\n" +
                "  while (!q.empty()) {\n" +
                "    int u = q.front();\n" +
                "    q.pop();\n" +
                "\n" +
                "    for (int v : adj[u]) {\n" +
                "      if (!vis[v]) {\n" +
                "        vis[v] = true;\n" +
                "        q.push(v);\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}\n" +
                "\n" +
                "int main() {\n" +
                "  int n, m, s;\n" +
                "  cin >> n >> m >> s;\n" +
                "\n" +
                "  for (int i = 0; i < m; i++) {\n" +
                "    int u, v;\n" +
                "    cin >> u >> v;\n" +
                "    adj[u].push_back(v);\n" +
                "    adj[v].push_back(u);\n" +
                "  }\n" +
                "\n" +
                "  bfs(s);\n" +
                "\n" +
                "  return 0;\n" +
                "}";
        super.worstCase = "V + E";
        super.averageCase = "V + E";
        super.bestCase = "V + E";
    }
}
