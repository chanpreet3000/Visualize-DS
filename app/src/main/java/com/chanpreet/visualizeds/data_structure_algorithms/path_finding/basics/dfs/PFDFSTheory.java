package com.chanpreet.visualizeds.data_structure_algorithms.path_finding.basics.dfs;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class PFDFSTheory extends AlgorithmTheory {
    public PFDFSTheory() {
        super.theory = TextBuilder.makeBulletList(
                "DFS (Depth-First Search) is an algorithm used to traverse or search a graph or tree data structure.",
                "It starts at the root node and explores as far as possible along each branch before backtracking.",
                "DFS uses a stack data structure to keep track of the nodes to be visited next.",
                "It is used for finding connected components, solving puzzles, and detecting cycles in a graph.");

        super.algorithm =
                TextBuilder.makeOrderedList("Initialize a stack and push the starting node to it.",
                        "Mark the starting node as visited.",
                        "While the stack is not empty:<br>" +
                                "\ta. Pop a node from the stack and set it as the current node.<br>" +
                                "\tb. For each unvisited neighbor of the current node:<br>" +
                                "\t\ti. Mark the neighbor as visited.<br>" +
                                "\t\tii. Add the neighbor to the stack.",
                        "Repeat steps 3a to 3b until all nodes have been visited.");
        super.code = "#include <bits/stdc++.h>\n" +
                "using namespace std;\n" +
                "\n" +
                "const int N = 100005;\n" +
                "vector<int> adj[N];\n" +
                "bool vis[N];\n" +
                "\n" +
                "void dfs(int u) {\n" +
                "  vis[u] = true;\n" +
                "\n" +
                "  for (int v : adj[u]) {\n" +
                "    if (!vis[v]) {\n" +
                "      dfs(v);\n" +
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
                "  dfs(s);\n" +
                "\n" +
                "  return 0;\n" +
                "}";
        super.worstCase = "V + E";
        super.averageCase = "V + E";
        super.bestCase = "V + E";
    }
}
