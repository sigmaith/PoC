package com.example;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        System.out.println();

        String input = "type Storage {    \n" +
                "  todos: Todo[]    \n" +
                "}    \n" +
                "\n" +
                "type Todo {    \n" +
                "  title: string    \n" +
                "  completed: boolean    \n" +
                "}  ";
        CharStream inputStream = CharStreams.fromString(input);
        com.example.syntaxLexer lexer = new com.example.syntaxLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        com.example.syntaxParser parser = new com.example.syntaxParser(tokens);

        ParseTree tree = parser.start();
        System.out.println(tree.toStringTree(parser));

        // TreeViewer를 사용하여 파싱 트리 시각화
        JFrame frame = new JFrame("Parse Tree Viewer");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
        viewer.setScale(1.5); // 트리 크기 조정
        panel.add(viewer);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}