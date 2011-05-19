package equationsolver2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sajuuk
 */
public class EquationSolver2Test {

    public EquationSolver2Test() {
    }

  /**
   * Test of solve method, of class EquationSolver2.
   */
  @Test
  public void testSolve() {
    System.out.println("solve for 2+2");
    String equation = "2+2";
    EquationSolver2 instance = new EquationSolver2();
    double expResult = 4.0;
    double result = instance.solve(equation);
    assertEquals(expResult, result, 0.0);

    System.out.println("solve for 4*2");
    equation = "4*2";
    expResult = 8.0;
    result = instance.solve(equation);
    assertEquals(expResult, result, 0.0);

    System.out.println("solve for 4-2");
    equation = "4-2";
    expResult = 2.0;
    result = instance.solve(equation);
    assertEquals(expResult, result, 0.0);

    System.out.println("solve for 4/2");
    equation = "4/2";
    expResult = 2.0;
    result = instance.solve(equation);
    assertEquals(expResult, result, 0.0);

    System.out.println("solve for 8/2");
    equation = "8/2";
    expResult = 4.0;
    result = instance.solve(equation);
    assertEquals(expResult, result, 0.0);

    System.out.println("solve for 8-2+1");
    equation = "8-2+1";
    expResult = 5.0;
    result = instance.solve(equation);
    assertEquals(expResult, result, 0.0);

    System.out.println("solve for 8*3/4");
    equation = "8*3/4";
    expResult = 6.0;
    result = instance.solve(equation);
    assertEquals(expResult, result, 0.0);

    System.out.println("solve for 6+8*3/4");
    equation = "6+8*3/4";
    expResult = 12.0;
    result = instance.solve(equation);
    assertEquals(expResult, result, 0.0);

    System.out.println("solve for (2+2)+2");
    equation = "(2+2)+2";
    expResult = 6.0;
    result = instance.solve(equation);
    assertEquals(expResult, result, 0.0);

    System.out.println("solve for (2+4)+2");
    equation = "(2+4)+2";
    expResult = 8.0;
    result = instance.solve(equation);
    assertEquals(expResult, result, 0.0);

    System.out.println("solve for (2+4)*2");
    equation = "(2+4)*2";
    expResult = 12.0;
    result = instance.solve(equation);
    assertEquals(expResult, result, 0.0);

    System.out.println("solve for (4/2)/2");
    equation = "(4/2)/2";
    expResult = 1.0;
    result = instance.solve(equation);
    assertEquals(expResult, result, 0.0);

    System.out.println("solve for (12/2)/2*3");
    equation = "(12/2)/2*3";
    expResult = 1.0;
    result = instance.solve(equation);
    assertEquals(expResult, result, 0.0);

    System.out.println("solve for (400/1000*4)*30");
    equation = "(400/1000*4)*30";
    expResult = 3.0;
    result = instance.solve(equation);
    assertEquals(expResult, result, 0.0);

  }

  /**
   * Test of parseInfixToPostfix method, of class EquationSolver2.
   */
  @Test
  public void testParseInfixToPostfix() {
    System.out.println("parseInfixToPostfix");
    String equation = "2+2";
    EquationSolver2 instance = new EquationSolver2();
    double expResult = 4.0;
    LinkedQueue<Object> postfixQueue = instance.parseInfixToPostfix(equation);
    double result = instance.evaluatePostfix(postfixQueue);
    assertEquals(expResult, result,0.0);
  }

    /**
   * Test of precedenceOf method, of class EquationSolver2.
   */
  @Test
  public void testPrecedenceOf() {
    EquationSolver2 instance = new EquationSolver2();
    System.out.println("precedenceOf for (");
    String operator = "(";
    int expResult = 6;
    int result = instance.precedenceOf(operator);
    assertEquals(expResult, result);

    System.out.println("precedenceOf for )");
    operator = ")";
    expResult = 6;
    result = instance.precedenceOf(operator);
    assertEquals(expResult, result);

    System.out.println("precedenceOf for *");
    operator = "*";
    expResult = 2;
    result = instance.precedenceOf(operator);
    assertEquals(expResult, result);

    System.out.println("precedenceOf for /");
    operator = "/";
    expResult = 3;
    result = instance.precedenceOf(operator);
    assertEquals(expResult, result);

    System.out.println("precedenceOf for +");
    operator = "+)";
    expResult = 4;
    result = instance.precedenceOf(operator);
    assertEquals(expResult, result);

    System.out.println("precedenceOf for -");
    operator = "-";
    expResult = 5;
    result = instance.precedenceOf(operator);
    assertEquals(expResult, result);

  }

  /**
   * Test of evaluatePostfix method, of class EquationSolver2.
   */
  @Test
  public void testEvaluatePostfix() {
    System.out.println("evaluatePostfix for result 12.0");
      LinkedQueue<Object> postfixQueue = new LinkedQueue<Object>();
      Double first  = 2.0;
      Double second = 4.0;
      String third  = "+";
      Double fourth = 2.0;
      String fifth = "*";
      postfixQueue.enqueue(first);
      postfixQueue.enqueue(second);
      postfixQueue.enqueue(third);
      postfixQueue.enqueue(fourth);
      postfixQueue.enqueue(fifth);
    EquationSolver2 instance = new EquationSolver2();
    double expResult = 12.0;
    double result = instance.evaluatePostfix(postfixQueue);
    assertEquals(expResult, result, 0.0);

    System.out.println("evaluatePostfix for result 2400.0");
      LinkedQueue<Object> postfixQueue2 = new LinkedQueue<Object>();
      first  = 2.0;
      second = 4.0;
      third  = "*";
      fourth = 300.0;
      fifth = "*";
      postfixQueue2.enqueue(first);
      postfixQueue2.enqueue(second);
      postfixQueue2.enqueue(third);
      postfixQueue2.enqueue(fourth);
      postfixQueue2.enqueue(fifth);
    expResult = 2400.0;
    result = instance.evaluatePostfix(postfixQueue2);
    assertEquals(expResult, result, 0.0);

    System.out.println("evaluatePostfix for result 2.0");
     Double one = 56.0;
      Double two = 20.0;
      Double three = 8.0;
      String four = "+";
      String five = "/";
      LinkedQueue<Object> postfixQueue3 = new LinkedQueue<Object>();
      postfixQueue3.enqueue(one);
      postfixQueue3.enqueue(two);
      postfixQueue3.enqueue(three);
      postfixQueue3.enqueue(four);
      postfixQueue3.enqueue(five);
    expResult = 2.0;
    result = instance.evaluatePostfix(postfixQueue3);
    assertEquals(expResult, result, 0.0);

    System.out.println("evaluatePostfix for parse result");
    one = 2.0;
    two = 2.0;
    four = "+";
    LinkedQueue<Object> postfixQueue4 = new LinkedQueue<Object>();
    postfixQueue4.enqueue(one);
    postfixQueue4.enqueue(two);
    postfixQueue4.enqueue(four);
    expResult = instance.evaluatePostfix(postfixQueue4);
    String equation = "2+2";
    LinkedQueue<Object> parsedQueue = instance.parseInfixToPostfix(equation);
    result = instance.evaluatePostfix(parsedQueue);
    assertEquals(expResult, result, 0.0);
  }

  @Test
  public void testIsOperator() {
    System.out.println("isOperator for *");
    EquationSolver2 instance = new EquationSolver2();
    String operator = "*";
    boolean expResult = true;
    boolean result = instance.isOperator(operator);
    assertEquals(expResult, result);
    System.out.println("isOperator for /");
    operator = "/";
    result = instance.isOperator(operator);
    assertEquals(expResult, result);
    System.out.println("isOperator for +");
    operator = "+";
    result = instance.isOperator(operator);
    assertEquals(expResult, result);
    System.out.println("isOperator for -");
    operator = "-";
    result = instance.isOperator(operator);
    assertEquals(expResult, result);
  }

  @Test
  public void testExecuteOperation() {
    System.out.println("ExecuteOperation for *");
    EquationSolver2 instance = new EquationSolver2();
    String operator = "*";
    Double operand1 = 2.0;
    Double operand2 = 4.0;
    double expResult = 8.0;
    double result = instance.executeOperation(operator, operand1, operand2);
    assertEquals(expResult, result,0.0);
    System.out.println("ExecuteOperation for /");
    operator = "/";
    expResult = 0.5;
    result = instance.executeOperation(operator, operand1, operand2);
    assertEquals(expResult, result,0.0);
    System.out.println("ExecuteOperation for +");
    operator = "+";
    expResult = 6.0;
    result = instance.executeOperation(operator, operand1, operand2);
    assertEquals(expResult, result,0.0);
    System.out.println("ExecuteOperation for -");
    operator = "-";
    expResult = -2.0;
    result = instance.executeOperation(operator, operand1, operand2);
    assertEquals(expResult, result,0.0);
  }
}