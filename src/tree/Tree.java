package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Tree API assignment for CIT594, Spring 2015.
 * 
 * @author Anushree Singh
 * @param <V> The type of value that can be held in each Tree node.
 */
public class Tree<V> implements Iterable<Tree<V>> {
    private V value;
    private ArrayList<Tree<V>> children;
    
    
    /**
     * Constructs a Tree with the given value in the root node,
     * having the given children.e given value
     * 
     * @param value The value to be put in the root.
     * @param children The immediate children of the root.
     * @throws IllegalArgumentException
     *         If the operation would create a circular Tree.
     */
    @SafeVarargs
	public Tree(V value, Tree<V>... children) {
        this.value = value;
        this.children = new ArrayList<Tree<V>>();
        for(Tree<V> i:children){ 
        	if(this.contains(i)){
        		throw new IllegalArgumentException();
        	}
        	this.children.add(i);
        }

    }
    
    /**
     * Sets the value in this node.
     * 
     * @param value The value to be stored in this node.
     */
    public void setValue(V value) {
     this.value = value;
    }
    
    /**
     * Returns the value in this node.
     * 
     * @return The value in this node.
     */
    public V getValue() {
    	
        return value; 
    }
    
    /**
     * Adds the child as the new <code>index</code>'th child of this Tree;
     * subsequent nodes are "moved over" as necessary to make room for the
     * new child.
     * 
     * @param index The position in which to put the new child.
     * @param child The child to be added to this node.	if(){
    		return true;
    	}        
     * @throws IllegalArgumentException
     *         If the operation would create a circular given value Tree.
     */
    public void addChild(int index, Tree<V> child) {
       
       
        	if(this.contains(child)){
        		throw new IllegalArgumentException();
        	}
        	else{
        	 children.add(index,child);
        	}
    }
    
    /**
     * Adds the child as the new last child of this node.
     * @param child The child to be added to this node.
     */
    public void addChild(Tree<V> child) {
       
        if(this.contains(child)){
    		throw new IllegalArgumentException();
    	}
    	else{
    	 children.add(child);
    	}
    }

    /**
     * Adds the children to this node, after the current children.
     * 
     * @param children The nodes to be added as children of this node.
     * @throws IllegalArgumentException
     *         If the operation would create a circular Tree.
     */
    @SuppressWarnings("unchecked")
	public void addChildren(Tree<V>... children) {
    
    	 for(Tree<V> i:children){ 
         	if(this.contains(i)){
         		throw new IllegalArgumentException();
         	}
         	this.children.add(i);
         }
    }
    
    /**
     * Returns the number of children that this node has.
     * 
     * @return A count of this node's immediate children.
     */
    public int getNumberOfChildren() {
    	
        return children.size(); 
    }
    
    /**
     * Returns the <code>index</code>'th child of this node.
     *  
     * @param index The position of the child that is to be returned.
     * @return The child at that position.
     * @throws IndexOutOfBoundsException If <code>index</code> is negative or
     *     is greater than or equal to the current number of children of this node.
     */
    public Tree<V> getChild(int index) {
        return children.get(index);
    }
    
    /**
     * Returns an iterator for the children of this node. 
     * 
     * @return An iterator for this node's immediate children.
     */
    @Override
    public Iterator<Tree<V>> iterator() {
        return children.iterator();
    }
    
    /**
     * Searches this Tree for a node that is == to <code>node</code>,
     * and returns <code>true</code> if found, <code>false</code> otherwise.
     * a
     * @param node aThe node to be searched for.
     * @return <code>true</code> iff the node is found.
     */
    boolean contains(Tree<V> node) {
    	return containsThis(this,node);
    }
    
    
    private boolean containsThis(Tree<?> node1, Tree<?> node2){
    	if(node1.children.size()==0){
    		return (node1==node2);
   	    }
    	else{
    		boolean b = false;
    		for(Tree<?> i:node1.children){
    			if(i==node2) return true;
    			else{
    			b = b||(containsThis(i, node2));
    			}
    		}
    		return b;
    	}
		
    }
    	
    
    /**
     * Returns a one-line string representing this tree.
     * The form of the output is:<br>
     * <code>value(child, child, ..., child)</code>.
     * 
     * @see java.lang.Object#toString()
     */
    	
    @Override
    public String toString() {
    	return toStringHelper(this);
    }
    private String toStringHelper(Tree<?> node){
        
    	if (node.children.size()==0){
    		return node.value.toString();
    	}
    	else{
    		String s = node.value.toString() +   "(";
    		for(Tree<?> i:node.children){
    			s = s + " "+ toStringHelper(i);
    		}
    		s = s + ")";
    		return s;
    	}
    	
    }
    
    /**
     * Prints this tree as an indented structure.
     */
    public void print() {
        print(this, "");
    }   
    
    /**
     * Prints the given tree as an indented structure, with the
     * given node indented by the given amount.
     * @param node The root of the tree or subtree to be printed.
     * @param indent The amount to indent the root.
     */
    private static void print(Tree<?> node, String indent) {

    	Stack<Tree<?>> stack = new Stack<Tree<?>>();
    	String s="" ;
    	StringBuilder count = new StringBuilder();
    	stack.push(node);
    	
    	while(!stack.empty()){
    		
    	
    		if(stack.peek().value.toString().equals("open")){
    			
    			stack.pop();
    			count.append(" ");
    			continue;
    		}
    		if(stack.peek().value.toString().equals("close")){
    			
    			stack.pop();
    			count.setLength(count.length()-1);
    			continue;
    		}
    		
			Tree<?> t = stack.pop();
    		s = s + count.toString()+ t.value.toString()+"\n";
    		if(t.children.size() != 0){
	    		stack.push(new Tree<String>("close"));
	    		for(Tree<?> i:t.children){
	    			stack.push(i);
	    			
	    			
	    		}
	    		stack.push(new Tree<String>("open"));
    		}
    	}
        System.out.println(s);
    
    }
    
    /**
     * Tests whether the input argument is a Tree having the same shape
     * and containing the same values as this Tree.
     * 
     * @param obj The object to be compared to this Tree.
     * @return <code>true</code> if the object is equals to this Tree,
     *         <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
    	if(this==null && obj==null){
    		return true;
    	}
    	if(this==null || obj==null){
    		return false;
    	}
    	if(!(obj instanceof Tree)){
    		return false;
    	}
  
    	Tree<?> obj1 = this;
    	Tree<?> obj2 = (Tree<?>)obj;
    	return equals(obj1,obj2);
    }
 
    
    /**
     * Tests whether two values are equal (either == or <code>equals(obj)</code>),
     * when one or both values may be <code>null</code>.
     * 
     * @param object1 The first object to be tested.
     * @param object2 The second object to be tested.
     * @return <code>true</code> iff the objects are equal.
     */
    private boolean equals(Tree<?> object1, Tree<?> object2) {

    	if(object1.children.size()==0 && object2.children.size()==0){
    		return object1.value.equals(object2.value);
    		}
        else{
    		if(object1.children.size()!=object2.children.size()) return false;
    			
    		boolean b = true;		
    		for(int i=0;i<object1.children.size();i++){
    				b = b && equals(object1.children.get(i),object2.children.get(i));
    			}
    		return b;
    		}
    
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return hashCodeHelper(this); 
    }
    
    private int hashCodeHelper(Tree<?> node){
    	if(node.children.size()==0){
    		return node.value.hashCode();
    		}
        else{
    		
    			
    		int b = 0;		
    		for(int i=0;i<node.children.size();i++){
    				b = 10*b + hashCodeHelper(node.children.get(i));
    			}
    		return b;
    		}
    }
    
    /**
     * Creates a Tree of Strings from the input argument, which must have the
     * same form as that produced by the <code>toString()</code> method of
     * this class, namely, <code>value(child, child, ..., child)</code>.
     * children.getChild
     * @param input A representation of a Tree.
     * @return The Tree represented by the input string.
     * @throws IllegalArgumentException If the input string is malformed.
     */
    public static Tree<String> parse(String input) {
        PushbackStringTokenizer tokenizer = new PushbackStringTokenizer(input);
        Tree<String> tree = parse(tokenizer);
        if (tokenizer.hasNext()) {
            throw new IllegalArgumentException("Tokenizer error at: " + tokenizer.next());
        }
        return tree;
    }
    
    /**
     * Uses the input <code>tokenizer</code> to read and return a single Tree.
     * Additional tokens are ignored.
     * 
     * @param tokenizer The source of tokens from which to build a Tree.
     * @return A Tree built from the string being tokenized.
     * @throws IllegalArgumentException If the tokenized string is malformed.
     */
    static Tree<String> parse(PushbackStringTokenizer tokenizer)
            throws IllegalArgumentException {
    	Tree<String> tree = new Tree<String>("");
    	
    	
    	String next = tokenizer.next(); 
        if(next==null||")".equals(next)) return null;
        else{
        	tree.setValue(next);
        }
        String tok = tokenizer.next();
        if(("(").equals(tok)){
        Tree<String> subTree = null;
        while((subTree = parse(tokenizer)) != null)	{
        	tree.addChild(subTree);
//        	if(")".equals(tokenizer.next())) return tree;
        }
        //if((")").equals(tokenizer.next())) throw new IllegalArgumentException();
    	}else{
    		tokenizer.pushBack(tok);
    	}
        return tree;
    }
    
    //---------------------------------------------------------------------
    
    /**
     * A Tokenizer that returns one of four things: a left parenthesis, a
     * right parenthesis, a sequence of non-whitespace, non-parenthesis
     * characters, or <code>null</code> if there are no more tokens.
     * 
     * @author David Matuszek
     */

    
    static class PushbackStringTokenizer {
        private StringTokenizer tokenizer;
        private String pushedValue = null;
        
        /**
         * Constructs a tokenizer for the input that uses whitespace and
         * parentheses as delimiters.
         * 
         * @param input The string to be tokenized.
         */
        PushbackStringTokenizer(String input) {
            tokenizer = new StringTokenizer(input, " \t\n\r\f()", true);
            pushedValue = null;
        }
        
        /**
         * Tests if there are more tokens in the input string.
         * 
         * @return <code>true</code> if there are more tokens,
         *         <code>false</code> otherwise.         
         */
        boolean hasNext() {
            return pushedValue != null || tokenizer.hasMoreTokens();
        }
        
        /**
         * Returns the next token (or a pushed back token, if there is
         * one.) A token may be a left parenthesis, a right parenthesis,
         * or any sequence of other, non-whitespace characters.
         * <p>
         * Unlike most tokenizers, this tokenizer will return
         * <code>null</code> if there are no remaining tokens.
         * 
         * @return The next token, or <code>null</code> if there are no more.
         */
        String next() { 
        	
            String temp = pushedValue;
            if (temp == null && tokenizer.hasMoreTokens()) {
                temp = tokenizer.nextToken().trim();
            }
            pushedValue = null;
            // skip whitespace tokens
            if (temp != null && temp.length() == 0) {
                temp = next();
            }
            return temp;
        }
        
        /**
         * Returns a token to this tokenizer so that it will be returned by
         * the next call to the <code>next()</code> method.
         * 
         * @param token The token to be reused.
         */
        void pushBack(String token) {
            pushedValue = token;
        }
    }
    
//    public static void main(String arg[]){
//    	
//    	String s= "one (two three (four five(six seven eight)) )";
//    	
//    }
}

       