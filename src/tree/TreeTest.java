package tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TreeTest {

    @Before
    public void setUp() throws Exception {}

    @Test
    public final void testHashCode() {
    	  Tree<Integer> root = new Tree<Integer>(1);
    	  Tree<Integer> c1 = new Tree<Integer>(2);
    	  Tree<Integer> c2 = new Tree<Integer>(3);
    	  Tree<Integer> c3 = new Tree<Integer>(4);
          root.addChild(c1);
          root.addChild(c2);
          c1.addChild(c3);
          
    	  Tree<Integer> root1 = new Tree<Integer>(1);
    	  Tree<Integer> c1_ = new Tree<Integer>(2);
    	  Tree<Integer> c2_ = new Tree<Integer>(3);
    	  Tree<Integer> c3_ = new Tree<Integer>(4);
          root1.addChild(c1_);
          root1.addChild(c2_);
          c1_.addChild(c3_);
          

          assertEquals(root1.hashCode(), root.hashCode());
    }

    @Test
    public final void testTree() {
    	  
           Tree<String> c1 = new Tree<String>("A");
           
           Tree<String> root = new Tree<String>("K",c1);
           
           assertEquals(root.getValue(),"K");
           assertEquals(c1.getValue(),"A");
           assertEquals(root.getChild(0),c1);

    }
    
    @Test
    public final void testTreeCyclic() {
    	try{
    		 Tree<String>root = new Tree<String>("A");
    	        Tree<String> c1 = new Tree<String>("B");
    	        Tree<String> c2 = new Tree<String>("C");
    	        Tree<String> c3 = new Tree<String>("D");
    	        Tree<String> c4 = new Tree<String>("E");
    	    	
    	        root.addChild(c1);
    	        root.addChild(c2);
    	        c1.addChild(c3);
    	        c1.addChild(c4);  
    	        root.addChild(c3);
       
        assertTrue(false);
        }catch(IllegalArgumentException e1){
        	assertTrue(true);
        }
    }

    @Test
    public final void testSetValue() {
    	Tree<String> c1 = new Tree<String>("");
    	c1.setValue("A");
    	assertEquals(c1.getValue(),"A");
    }

    @Test
    public final void testGetValue() {
    	Tree<String> c1 = new Tree<String>("A");
    	assertEquals(c1.getValue(),"A");
    }

    @Test
    public final void testAddChildIntTreeOfV() {
    	Tree<String> c1 = new Tree<String>("A");
    	Tree<String> c2 = new Tree<String>("B");
    	Tree<String> root1 = new Tree<String>("C",c1);
    	root1.addChild(1,c2);
//    	root1.print();
//    	root1.getChild(0).print();
        assertTrue(root1.getChild(1).equals(c2));
    }

    @Test
    public final void testAddChildTreeOfV() {
    	Tree<String> c1 = new Tree<String>("A");
    	Tree<String> c2 = new Tree<String>("B");
    	Tree<String> root1 = new Tree<String>("C",c1);
    	root1.addChild(c2);
    	assertTrue(root1.getChild(1).equals(c2));
 
    }

    @Test
    public final void testAddChildren() {
    	Tree<String> c1 = new Tree<String>("A");
    	Tree<String> c2 = new Tree<String>("B");
    	Tree<String> root1 = new Tree<String>("C");
    	root1.addChildren(c1,c2);
    	assertTrue(root1.getChild(1).equals(c2)&&root1.getChild(0).equals(c1));
    }

    @Test
    public final void testGetNumberOfChildren() {
    	Tree<String> c1 = new Tree<String>("A");
    	Tree<String> c2 = new Tree<String>("B");
    	Tree<String> root1 = new Tree<String>("C");
    	root1.addChildren(c1,c2);
    	assertTrue(root1.getNumberOfChildren()==2);
    }

    @Test
    public final void testGetChild() {
     	Tree<String> c2 = new Tree<String>("B");
    	Tree<String> root1 = new Tree<String>("C",c2);
    	assertTrue(root1.getChild(0).equals(c2));
    }

    @Test
    public final void testIterator() {
    	Tree<String> c1 = new Tree<String>("A");
    	Tree<String> c2 = new Tree<String>("B");
    	Tree<String> root1 = new Tree<String>("C");
    	root1.addChildren(c1,c2);
    	int idx=0;
    	for(Tree<String> i:root1){
    		assertTrue(root1.getChild(idx).equals(i));
    		idx++;
    	}
    }

    @Test
    public final void testContains() {
        Tree<String>root = new Tree<String>("A");
        Tree<String> c1 = new Tree<String>("B");
        Tree<String> c2 = new Tree<String>("C");
        Tree<String> c3 = new Tree<String>("D");
        Tree<String> c4 = new Tree<String>("F");
    	
        root.addChild(c1);
        root.addChild(c2);
        c1.addChild(c3);
        c1.addChild(c4);        
//        root.print();
        assertTrue(root.contains(c4));        
        
    }

    @Test
    public final void testToString() {
        Tree<String> root = new Tree<String>("K");
        Tree<String> c1 = new Tree<String>("A");
        Tree<String> c2 = new Tree<String>("S");
        Tree<String> c3 = new Tree<String>("B");
        
        root.addChild(c1);
        root.addChild(c2);
        c1.addChild(c3);
        String s = "K( A( B) S)";
        assertTrue(root.toString().equals(s));

    }

    @Test
    public final void testEqualsObject() {
        Tree<String>root = new Tree<String>("A");
        Tree<String> c1 = new Tree<String>("B");
        Tree<String> c2 = new Tree<String>("C");
        Tree<String> c3 = new Tree<String>("D");
        Tree<String> c4 = new Tree<String>("E");
        
 
        Tree<String>root2 = new Tree<String>("A");
        Tree<String> c1_ = new Tree<String>("B");
        Tree<String> c2_= new Tree<String>("C");
        Tree<String> c3_ = new Tree<String>("D");
        Tree<String> c4_ = new Tree<String>("E");
        
        
        root.addChild(c1);
        root.addChild(c2);
        c1.addChild(c3);
        c2.addChild(c4);
        
        root2.addChild(c1_);
        root2.addChild(c2_);
        c1_.addChild(c3_);
        c2_.addChild(c4_);
        
//        root.print();
//        root2.print();
        assertTrue(root.equals(root2));
        
        
    }

    @Test
    public final void testParseString() {
       String s = "K( A( B) S)";
       Tree<String> tree = Tree.parse(s);
       Tree<String>root = new Tree<String>("K");
       Tree<String> c1 = new Tree<String>("A");
       Tree<String> c2 = new Tree<String>("B");
       Tree<String> c3 = new Tree<String>("S");
       root.addChild(c1);
       root.addChild(c3);
       c1.addChild(c2);
       assertEquals(tree,root);
    }

}
