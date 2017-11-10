package ppic.model.algorithms;

import java.util.Random;

import ppic.model.*;
import ppic.model.operators.PPConstant;


public class Mutate extends Algorithm
{
  
	
    /*
     * Pseudocode for mutating:
     * apply (ex, rate):
     *   rate% of the time return a copy of ex with no change. 
     *   else:
     *      if ex has no children, choose randomly (evenly) between the following choices:
     *           - return completely new random expression (use your Randomize class, and the same rate as input) 
     *           - return result of mutateConstant method.  This method takes an expression, and if it represents a 
     *             Constant, it changes the R, G, B values by a little bit. 
     *      if ex has one child, choose randomly (evenly) between the following choices:
     *           - return a copy of the expression with no change
     *           - return a copy of the child of the expression
     *           - return a new 1-arg Expression with the base of ex and an entirely new child
     *           - return a new 1-arg Expression with a random base and ex as a child
     *           - return a new 2-arg Expression with a random base and right child and ex as a left child
     *           - return a new 2-arg Expression with a random base and left child and ex as a right child
     *      if ex has 2 children, choose randomly (evenly) between the following choices:
     *           - return a copy of the expression with no change
     *           - return a copy of the left child with no change
     *           - return a copy of the right child with no change
     *           - return Randomize.replaceBase() with the left then right child
     *           - return Randomize.replaceBase() with the right then left child
     */
   
    public Expression apply (Expression expr, double rate)
    {
    	Random randomInt = new Random();
    
    	Randomize randomize = new Randomize();
    	if(random.nextDouble() <= rate){
    		Expression copy = expr.copy();
        	return copy;
    	}
    	//if no children
    	else{
    		if(expr.getLeft() == null && expr.getRight() == null){
    			int nextInt = randomInt.nextInt(2);
    			if(nextInt == 1){
    				Expression random = randomize.apply(rate);
    				return random;
    			}
    			else{
    				Expression mutateConstant = mutateConstant(expr);
    				return mutateConstant;
    		}
    		}
    		//if 2 children	
    		if(expr.getLeft()!=null && expr.getRight()!=null){
    			int nextInt = randomInt.nextInt(5);
    			if(nextInt == 1){
    			expr.copy();
    			}
    			if(nextInt == 2){
    			expr.getLeft().copy();
    			}
    			if(nextInt == 3){
    			expr.getRight().copy();
    			}
    			if(nextInt == 4){
    			randomize.randomReplaceBase(expr.getLeft(),expr.getRight(),rate);
    			}
    			if(nextInt == 5){
    			randomize.randomReplaceBase(expr.getRight(), expr.getLeft(), rate);
    			}
    		}
    		//if 1 child
    		else{
    			int nextInt = randomInt.nextInt(6);
    			if(nextInt == 1){
    			return expr.copy();
    			}
    			if(nextInt == 2){
    			//return a copy of the child of the expression
    				if(expr.getLeft() != null){
    					return expr.getLeft().copy();
    				}
    				if(expr.getRight() != null){
    					return expr.getRight().copy();
    				}
    			}
    			if(nextInt == 3){
    			//base of ex and entirely new child
    			Expression randomChild = randomize.apply(rate);
    			expr.setLeft(randomChild);
    			return expr;
    			}
    			if(nextInt == 4){
    			//random base and ex as child
    			randomize.makeOneArgumentExpression(expr);
    			}
    			if(nextInt == 5){
    			//2 arg expression with random base and right child and expr as left child
    			randomize.makeTwoArgumentExpression(expr, randomize.apply(rate));
    			}
    			if(nextInt == 6){
    			//2 arg expression with random base and left child and ex as right child
    			randomize.makeTwoArgumentExpression(randomize.apply(rate), expr);
    			}
    		}
    	}
       return null;
    }
    

    /*
     *  If the input expression is a constant, it returns a new Constant with one of its r, g, or b 
     *  values altered by a small amount.
     *  Otherwise, it returns a copy of the input expression.    
     */
    public Expression mutateConstant(Expression tfunc) {
    		if (tfunc.getClass().equals(ppic.model.operators.PPConstant.class)) {
    			RGBColor rgb = tfunc.evaluate(0, 0, 0);
    			double change = random.nextDouble()*40 - 20;
    			int chooser = random.nextInt(2);
    			double[] color = {rgb.getR(), rgb.getG(), rgb.getB()};
    			color[chooser]+= change;
    			color[chooser] = Math.min(color[chooser], 255);
    			color[chooser] = Math.max(color[chooser], 0);
    			return new PPConstant(color[0], color[1], color[2]);
    		
    		}
    
    		else return tfunc.copy();
    }
    
}
