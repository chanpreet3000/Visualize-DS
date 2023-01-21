package com.chanpreet.visualizeds.data_structure_algorithms.stack.basics.push_pop;

import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class StackPushPopTheory extends AlgorithmTheory {
    public StackPushPopTheory() {
        super.theory = "The stack data structure is a linear data structure that accompanies a principle known as LIFO (Last In First Out) or FILO (First In Last Out). Real-life examples of a stack are a deck of cards, piles of books, piles of money, and many more." +
                "\n" +
                "\nStack can be used using STL libraries." +
                "\n" +
                "\nIn Stack an element can be inserted using PUSH Operation and an element can be removed from the top of the stack using the POP operation";

        super.algorithm = "1) Element can be inserted using .push(element) function" +
                "\n" +
                "\n2) Top element can be removed using .pop() function";

        super.code = "#include <bits/stdc++.h>\n" +
                "using namespace std;\n" +
                "\n" +
                "int main()\n" +
                "{\n" +
                "    stack<int>  st;\n" +
                "\n" +
                "    //pushing into the stack \n" +
                "    st.push(10);\n" +
                "    st.push(20);\n" +
                "    \n" +
                "    // poping using st.pop();\n" +
                "    st.pop();\n" +
                "    return 0;\n" +
                "}";
        super.worstCase = "1";
        super.averageCase = "1";
        super.bestCase = "1";
    }
}
