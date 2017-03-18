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
    static int c = 1;

    
    public static void listall(File projectDir)
    {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> { 
        	int x = new Random().nextInt(100);
        	
        	try {
        		if (file.toString().equals("/home/vagrant/itrust/iTrust/src/main/edu/ncsu/csc/itrust/controller/labtechnician/LabTechnicianController.java")){
        			return;
        		}
        		if(x %2 == 0)
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
    File projectDir = new File("/home/vagrant/itrust/iTrust/src/main");
	new InitializationOfOperators();
    listall(projectDir);
    
   
}
	
    public static Node visitString(StringLiteralExpr declarator, Object args) {
/*        String expression = declarator.getValue();
        if(expression.equals("<"))
        	declarator.setValue(">");
        if(expression.equals(">"))
        	declarator.setValue("<");
        for(char c : expression.toCharArray()) {
            if(Character.isLetter(c) && Character.isLowerCase(c)) 
            	declarator.setValue(expression.toUpperCase());
            if(Character.isLetter(c) && Character.isUpperCase(c)) 
            	declarator.setValue(expression.toLowerCase());
        }*/
        return declarator;
    }
    public static Node visitBinary(BinaryExpr declarator, Object args) {

        BinaryExpr.Operator expression = declarator.getOperator();
        HashSet<BinaryExpr.Operator> relational = InitializationOfOperators.relational;
        HashSet<BinaryExpr.Operator> logical = InitializationOfOperators.logical;
        HashSet<BinaryExpr.Operator> arithmetic = InitializationOfOperators.arithmetic;
        /*
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
        */
        
    
        if (c == 1){
        	if(BinaryExpr.Operator.equals == expression)
        		declarator.setOperator(BinaryExpr.Operator.notEquals);
        	c++;
        }
        else if (c == 2){
            if(BinaryExpr.Operator.lessEquals == expression)
            	declarator.setOperator(BinaryExpr.Operator.greaterEquals);
            c++;
        }
        else{
            if(BinaryExpr.Operator.greaterEquals == expression)
            	declarator.setOperator(BinaryExpr.Operator.lessEquals);
            c = 1;
        }

 /*       if(BinaryExpr.Operator.and == expression)
        	declarator.setOperator(BinaryExpr.Operator.or);
        
        
       if(BinaryExpr.Operator.less == expression)
        	declarator.setOperator(BinaryExpr.Operator.greater);
        	
        if(BinaryExpr.Operator.lessEquals == expression)
        	declarator.setOperator(BinaryExpr.Operator.greaterEquals);
        if(BinaryExpr.Operator.minus == expression)
        	declarator.setOperator(BinaryExpr.Operator.plus);
        if(BinaryExpr.Operator.notEquals == expression)
        	declarator.setOperator(BinaryExpr.Operator.equals);
        if(BinaryExpr.Operator.divide == expression)
        	declarator.setOperator(BinaryExpr.Operator.remainder);
        if(BinaryExpr.Operator.and == expression)
        	declarator.setOperator(BinaryExpr.Operator.or);*/
        
        return declarator;
        
    }

	private static Operator getRandomExpression(HashSet<Operator> setOfExpressions, Operator expression) {
		// TODO Auto-generated method stub
		Operator result = null;
		int size = setOfExpressions.size();
		
		
		boolean flag = true;
		while (flag){
			int c = 0;
			int randomNo = new Random().nextInt(size);

			for (Operator e : setOfExpressions){
				c++;

				if (c == randomNo && e != expression){
					// return this expression 
					result = e;
					flag = false;
					return result;
				}
			}
		}
		return result;
	}
 }