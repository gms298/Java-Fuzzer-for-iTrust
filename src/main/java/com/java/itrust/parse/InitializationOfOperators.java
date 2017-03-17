package com.java.itrust.parse;

import java.util.HashSet;

import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.UnaryExpr;

public class InitializationOfOperators {
	  public static HashSet<BinaryExpr.Operator> relational = new HashSet<>();
	  public static HashSet<BinaryExpr.Operator> arithmetic = new HashSet<>();
	  public static HashSet<BinaryExpr.Operator> logical = new HashSet<>();
	  
	  public InitializationOfOperators(){
		  initializeRelationalOperators();
		  initializeArithmeticOperators();
		  initializeLogicalOperators();
		  
	  }

	private void initializeLogicalOperators() {
		// TODO Auto-generated method stub
		logical.add(BinaryExpr.Operator.and);
		logical.add(BinaryExpr.Operator.or);
	}



	private void initializeArithmeticOperators() {
		// TODO Auto-generated method stub
		arithmetic.add(BinaryExpr.Operator.plus);
		arithmetic.add(BinaryExpr.Operator.minus);
		arithmetic.add(BinaryExpr.Operator.times);
		arithmetic.add(BinaryExpr.Operator.divide);
	}



	private void initializeRelationalOperators() {
		// TODO Auto-generated method stub
		
		relational.add(BinaryExpr.Operator.less);
		relational.add(BinaryExpr.Operator.greater);
		relational.add(BinaryExpr.Operator.lessEquals);
		relational.add(BinaryExpr.Operator.greaterEquals);
		relational.add(BinaryExpr.Operator.equals);
		relational.add(BinaryExpr.Operator.notEquals);
		
	}
}
