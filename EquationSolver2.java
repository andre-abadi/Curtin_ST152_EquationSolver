package equationsolver2;

import java.util.Iterator;
import java.util.StringTokenizer;

/**
 *
 * @author sajuuk
 */
public class EquationSolver2 {

  /**
   * Default constructor; initializes instance.
   */
  public EquationSolver2() {
    //nothing
  }

  /**
   * Coordination method
   * @param equation A String of numbers and operators without whitespace.
   * @return A double representing the fully solved equation; the result.
   */
 public double solve(String equation) {
   if (equation.equals("") == true) {
     throw new IllegalArgumentException("No equation supplied, exiting.");
   }
   LinkedQueue<Object> postfixQueue;
   postfixQueue = parseInfixToPostfix(equation);
   return evaluatePostfix(postfixQueue);
 }

 /**
  * Converts an infix equation String into a postfix equation linked-queue.
  * @param equation An equation-String in infix notation.
  * @return A LinkedQueue of objects containing the equation in postfix form.
  */
 public LinkedQueue<Object> parseInfixToPostfix(String equation) {
   LinkedStack<Object> operators    = new LinkedStack<Object>();
   LinkedQueue<Object> postfixQueue = new LinkedQueue<Object>();
   StringTokenizer strTok = new StringTokenizer(equation, "+-*/()", true);

   while (strTok.hasMoreTokens() == true) {
     String curTok = strTok.nextToken();
     if (isOperator(curTok) == true) {
       //if the operator stack is not empty, there is a chance
       //that the top operator may have a higher precedence than the current
       //token, in which case it will have to be popped onto the postfixQueue
       //before we can push the current operator onto the stack
       if (operators.isEmpty() == false) {
         int topPrec = precedenceOf((String)operators.peek().getValue());
         int curPrec = precedenceOf(curTok);
         if (topPrec<curPrec) {
           System.out.println("Top of op. stack found to have higher prec.");
           postfixQueue.enqueue(operators.pop().getValue());
         }
       }
       //otherwise if the operator stack is empty then precedence doesn't matter
       //so we can go ahead and push onto the stack regardless
       operators.push(curTok);
       System.out.println("Pushed " + curTok + " onto operator stack.");
     }
     else if(curTok.equals("(") == true) {
       //if the current token is an open bracket, add it to the operator stack
       System.out.println("Found an open bracket, added to operator stack");
       operators.push(curTok);
       System.out.println("Top of operators is now: " + operators.peek().getValue());
     }
     else if (curTok.equals(")") == true) {
       System.out.println("Found a close bracket, popping until open bracket found");
       //if the current token is a close bracket
       while (operators.isEmpty() == false &&
               operators.peek().getValue().equals("(") == false) {
         //pop off all operators until we hit the open bracket
         String popped = (String)operators.pop().getValue();
         System.out.println("Popped " + popped);
         postfixQueue.enqueue(popped);
         System.out.println("Top of operators is now: " + operators.peek().getValue());
       }
       //then pop once more to get rid of the open bracket
       operators.pop();
     }
     else {
       System.out.println("Enqueued " + curTok + " onto postfixQueue.");
       Double adding = Double.parseDouble(curTok);
       postfixQueue.enqueue(adding);
     }
   }
   System.out.println("Beginning final pop off operators and enqueueing");
   while (operators.isEmpty() == false) {
     System.out.println("Going to pop: " + operators.peek().getValue());
     postfixQueue.enqueue(operators.pop().getValue());
   }
   System.out.println("Displaying final postfixQueue");
   Iterator<Object> itty = postfixQueue.iterator();
   Object current = itty.next();
   while (itty.hasNext() == true) {
     System.out.println(current);
     current = itty.next();
   }
   System.out.println(current);
   return postfixQueue;
 }

 /**
  * Calculates the precedence of the supplied operator.
  * @param theOperator Operator whose precedence is to be calculated
  * @return The precedence numerically descending (1 is highest precedence)
  */
 public int precedenceOf(String theOperator) {
   char operator = theOperator.charAt(0);
   int precedence = 0;
   switch (operator) {
     //Brackets have the highest precedence
     case '(':   precedence = 6; break;
     case ')':   precedence = 6; break;
     //Multiply
     case '*':   precedence = 2; break;
     //Divide
     case '/':   precedence = 3; break;
     //Add
     case '+':   precedence = 4; break;
     //Divide
     case '-':   precedence = 5; break;
     //Otherwise it must not be an operator and so we throw an error.
     default:    throw new
             IllegalArgumentException ("Operator: " + theOperator + " is not"
             + " one of ()*/+-.");
   }
   return precedence;
 }
 /**
  * Evaluates a linked-queue-based postfix equation.
  * @param postfixQueue A linked-queue containing an equation in postfix form.
  * @return The final result of all operations in the equation.
  */
 public double evaluatePostfix(LinkedQueue<Object> postfixQueue) {
   //create a stack to play with the values in the queue
   LinkedStack<Object> evaluationStack = new LinkedStack<Object>();
   //dequeue onto a stack while you can
   while (postfixQueue.isEmpty() == false) {
     //start by dequeuing and placing the dequeued item onto the eval-stack
     evaluationStack.push(postfixQueue.dequeue().getValue());
     //either the item we just pushed was an operator
     //i.e. check whether or not the top of the stack (that we just pushed)
     //is an operator
     if (isOperator(evaluationStack.peek().getValue()) == true) {
       //if it is indeed an operator, we can compute it and the two
       //operands that must appear under it on the stack
       String operator = (String)evaluationStack.pop().getValue();
       Double denominator = (Double)evaluationStack.pop().getValue();
       Double numerator = (Double)evaluationStack.pop().getValue();
       Double executed = executeOperation(operator, numerator, denominator);
       evaluationStack.push(executed);
     }
     //or it was an operand, in which case we just keep going in our while loop
   }
   //now that the queue is empty, pop stack because it should only contain
   //the final result of the equation
   return (Double)evaluationStack.pop().getValue();

 }
 
  /**
   * Checks whether or not an object(String) is an operator.
   * Operators are *,/,+,-.
   * @param input a String-Object
   * @return true if the String is an operator, false otherwise
   */
  public boolean isOperator(Object input) {
    boolean result = false;
    if (       input.equals("*")
            || input.equals("/")
            || input.equals("+")
            || input.equals("-")
            ) {result = true;}
    return result;
  }

    /**
   * Evaluates the single-operator equation passed to it.
   * @param op The operator to apply to the other parameters
   * @param op1 The first operand, the numerator if dividing
   * @param op2 The second operand, denominator or subtracted from first
   * @return The result of the evaluated equation
   */
  public double executeOperation(Object op, Object op1, Object op2) {
    //create a variable to store the operation's result
    double result;
    String operatorString = (String)op;
    Character operator = operatorString.charAt(0);
    Double numerator = (Double)op1;
    Double denominator = (Double)op2;
    //first we need to parse the operator and figure out what we need to do
    switch (operator) {
      //divide the first operand by the second operand
      case '*':   result = numerator * denominator; break;
      //multiply the operands
      case '/':   result = numerator / denominator; break;
      //add the operands
      case '+':   result = numerator + denominator; break;
      //subtract the second operand from the first
      case '-':   result = numerator - denominator; break;
      //if it isn't one of the above operators then throw an error
      default:
        throw new IllegalArgumentException("Unknown Operator: " + op);
    }
    //then return the result
    return result;
  }
}