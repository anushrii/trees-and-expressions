package tree;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class for representing simple arithmetic expressions.
 * @author Anushree Singh
 * @version Feb 10, 2015
 */
public class Expression {
    Tree<String> expressionTree;
    
    /**
     * Constructs a Tree<String> representing the given arithmetic expression,
     * then verifies that the newly created Tree is valid as an expression.
     * If the Tree is invalid, throws an IllegalArgumentException.<br>
     * Here are the validity rules:<ul>
     * <li>The value of each node must be one of "+", "-", "*", "/",
     *     or a String representing an unsigned integer.</li>
     * <li>If a node has value "+" or "*", it must have two or more children.</li>
     * <li>If a node has value "-" or "/", it must have exactly two children.</li>
     * <li>If a node contains a numeric string, it must be a leaf.</li></ul>
     * Note that the input parameter uses prefix notation, for example:
     * "+ (5 10 -( *(15 20) 25) 30)"
     * @param expression The String representation of the expression to be constructed.
     */
    public Expression(String expression) {
        expressionTree = Tree.parse(expression);
        if (!valid(expressionTree)) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }
    }

    /**
     * Tests whether the given Tree represents a valid Expression.
     * @param tree The input tree.
     * @return <code>true</code> iff the Tree is a valid Expression.
     */
    private boolean valid(Tree<String> tree) {
    	if(tree.getNumberOfChildren()==0){
    		int val = -1;
    		try{
    			val = Integer.parseInt(tree.getValue());
    		}catch(Exception e){
    			return false;
    		}
    		if(val >= 0) return true;
    		else return false;
    	}
    	else{
    		String val = tree.getValue();
    		if(val.equals("+")|| val.equals("*")){
    			if(tree.getNumberOfChildren() >= 2){
    				boolean b = true;
    				for(int i=0;i<tree.getNumberOfChildren();i++){
    					b = b && valid(tree.getChild(i));
    				}
    				return b;
    			}
    		}else if(val.equals("-")|| val.equals("/")){
    			if(tree.getNumberOfChildren() == 2){
    				boolean b = true;
    				for(int i=0;i<tree.getNumberOfChildren();i++){
    					b = b && valid(tree.getChild(i));
    				}
    				return b;
    			}
    		}
    			
    		return false;
    	}
    }
    
    /**
     * Evaluates this Expression.
     * @return The value of this Expression.
     */
    public int evaluate() {
    	
        return evaluate(expressionTree);
    }
    
    /**
     * Evaluates the given Tree, which must represent a valid Expression.
     * @return The value of this Expression.
     */
    private int evaluate(Tree<String> tree) {
        if(tree.getNumberOfChildren()==0){
        	return Integer.parseInt(tree.getValue());
        }
        else{
        	int ret = -1;
        	String val = tree.getValue();
        	if(val.equals("-")){
        		
        		 ret =  evaluate(tree.getChild(0)) 
        				 - evaluate(tree.getChild(1));
        	}
        	else if(val.equals("+")){
        		int sum = 0;
        		for(Tree<String> i:tree){
        			sum = sum + evaluate(i);
        		}
        		ret = sum;
        	}
        	else if(val.equals("*")){
        		int prd = 1;
        		for(Tree<String> i:tree){
        			prd = prd*evaluate(i);
        		}
        		ret = prd;
        	}
        	else if(val.equals("/")){
        		
       		 ret =  evaluate(tree.getChild(0)) 
       				 /evaluate(tree.getChild(1));
        	}
        	return ret;
       	}
		
       
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return toString(expressionTree);
    }
    
    private static String toString(Tree<String> tree) {
        // Helper method for toString()
    	if (tree.getNumberOfChildren()==0){
    		return tree.getValue();
    	}
    	else{
        	String ret = null;
        	String val = tree.getValue();
        	if(val.equals("-")){
        		
        		 ret =  toString(tree.getChild(0)) + 
        				 " - " +  toString(tree.getChild(1));
        	}
        	else if(val.equals("+")){
        		String sum = "";
        		for(Tree<String> i:tree){
        			sum = sum + " + " + toString(i);
        		}
        		ret = sum.substring(3);
        	}
        	else if(val.equals("*")){
        		String prd = "";
        		for(Tree<String> i:tree){
        			prd = prd + " * " + toString(i);
        		}
        		ret = prd.substring(3);
        	}
        	else if(val.equals("/")){
        		
       		 ret =  toString(tree.getChild(0)) 
       				 + " / " +  toString(tree.getChild(1));
        	}
        	return "("+ret+")";
       	}
             
    }
}
