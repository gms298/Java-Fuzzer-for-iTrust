package com.java.itrust.parse;

import com.github.javaparser.JavaParser;
import java.util.function.*;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.java.itrust.parse.*;
import com.google.common.base.Strings;
import com.google.common.io.Files;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Random;

import com.github.javaparser.ast.visitor.*;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.*;
import com.github.javaparser.*;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BinaryExpr.Operator;
import com.github.javaparser.ast.expr.CharLiteralExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//Iterating over the classes and changing strings
import java.nio.charset.Charset;

public class modifysymbol extends ModifierVisitorAdapter<Void> {
 

    
    public static void listall(File projectDir)
    {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> { 
        	int x = new Random().nextInt(100);
        	try {
        		if(x >= 0)
        		{
        		System.out.println(file);
        		CompilationUnit compUnit = JavaParser.parse(file);
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(BinaryExpr n, Object arg) {
                        visitBinary(n, arg);
                    }
                    
                    @Override
                    public void visit(StringLiteralExpr n, Object arg) {
                    	visitString(n, arg);
                    }
                    
        	}.visit(compUnit, null);
        	
            String newJavaFile = compUnit.toString();
            
            writeFile(file,newJavaFile);
        		}
           
            } catch (IOException e) {
                new RuntimeException(e);
            }
        	catch(ParseException e){
        		new RuntimeException(e);
        	}
        }).explore(projectDir);
    }

private static void writeFile(File file, String newJavaFile) {
		// TODO Auto-generated method stub
	BufferedWriter writer = null;
	try
	{
	    writer = new BufferedWriter( new FileWriter( file));
	    writer.write( newJavaFile);

	}
	catch ( IOException e)
	{
	}
	finally
	{
	    try
	    {
	        if ( writer != null)
	        writer.close( );
	    }
	    catch ( IOException e)
	    {
	    }
	}
	}

public static void main(String[] args) {
	//Fill in the itrust directory to parse
	System.out.println("start");
    File projectDir = new File("/home/vagrant/itrust/iTrust/src/main");
	new InitializationOfOperators();
    listall(projectDir);
    System.out.println("end");
    
   
}
	
    public static Node visitString(StringLiteralExpr declarator, Object args) {
        String expression = declarator.getValue();
        if(expression.equals("<"))
        	declarator.setValue(">");
        if(expression.equals(">"))
        	declarator.setValue("<");
        for(char c : expression.toCharArray()) {
            if(Character.isLetter(c) && Character.isLowerCase(c)) 
            	declarator.setValue(expression.toUpperCase());
            if(Character.isLetter(c) && Character.isUpperCase(c)) 
            	declarator.setValue(expression.toLowerCase());
        }
        return declarator;
    }
    public static Node visitBinary(BinaryExpr declarator, Object args) {
    	
        BinaryExpr.Operator expression = declarator.getOperator();
        HashSet<BinaryExpr.Operator> relational = InitializationOfOperators.relational;
        HashSet<BinaryExpr.Operator> logical = InitializationOfOperators.logical;
        HashSet<BinaryExpr.Operator> arithmetic = InitializationOfOperators.arithmetic;
        
        System.out.println("1");
        System.out.println(declarator);
        // randomizing the relational operations
        if (relational.contains(expression)){
        	// its a relational operator
        	BinaryExpr.Operator newExp = getRandomExpression(relational, expression);
        	declarator.setOperator(newExp);
        }
        
     // randomizing the arithmetic operations
        if (arithmetic.contains(expression)){
        	// its a relational operator
        	BinaryExpr.Operator newExp = getRandomExpression(arithmetic, expression);
        	declarator.setOperator(newExp);
        }
       
        
        // randomizing the logical operations
        if (logical.contains(expression)){
        	// its a logical operator
        	BinaryExpr.Operator newExp = getRandomExpression(logical, expression);
        	declarator.setOperator(newExp);
        }
             
        return declarator;
        
    }

	private static Operator getRandomExpression(HashSet<Operator> setOfExpressions, Operator expression) {
		// TODO Auto-generated method stub
		
		int size = setOfExpressions.size();
		System.out.println("2");
		System.out.println(expression);
		boolean flag = true;
		
		
		while (flag){
			// select between 0 (inclusive) and size (exclusive)
			int randomNo = new Random().nextInt(size);
			int c = 0;
			System.out.println(randomNo);
			for (Operator e : setOfExpressions){
				
				//if (c == randomNo && e != expression){
				if (e == expression && c == randomNo){
					System.out.println("inside e == expression");
					break;
				}
				if (c == randomNo){
					// return this expression 
					flag = false;
					System.out.println("3");
					System.out.println(e);
					return e;
				}
				c++;
			}
		}
		return null;
	}
 }