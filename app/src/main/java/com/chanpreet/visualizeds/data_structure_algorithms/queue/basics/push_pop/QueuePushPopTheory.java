package com.chanpreet.visualizeds.data_structure_algorithms.queue.basics.push_pop;

import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class QueuePushPopTheory extends AlgorithmTheory {
    public QueuePushPopTheory() {
        super.theory = "A Queue is defined as a linear data structure that is open at both ends and the operations are performed in First In First Out (FIFO) order. We define a queue to be a list in which all additions to the list are made at one end, and all deletions from the list are made at the other end.";
        super.algorithm = "1) Element can be inserted using .push(element) function" +
                "\n" +
                "\n2) Front element can be removed using .pop() function";

        super.code = "#include <bits/stdc++.h>\n" +
                "using namespace std;\n" +
                "\n" +
                "int main()\n" +
                "{\n" +
                "    queue<int> q;\n" +
                "\n" +
                "    // pushing into the queue\n" +
                "    q.push(10);\n" +
                "    q.push(20);\n" +
                "\n" +
                "    // poping using .pop();\n" +
                "    q.pop();\n" +
                "\n" +
                "    // Accessing front element\n" +
                "    q.front();\n" +
                "\n" +
                "    return 0;\n" +
                "}";
        super.worstCase = "1";
        super.averageCase = "1";
        super.bestCase = "1";
    }
}
