package ntnu.fullstack.no.calculatorbackend.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import ntnu.fullstack.no.calculatorbackend.model.ExpressionEntry;
import ntnu.fullstack.no.calculatorbackend.repositories.ExpressionRepository;
import ntnu.fullstack.no.calculatorbackend.util.DivideByZeroException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
  private final ExpressionRepository expressionRepository;

  @Autowired
  public CalculatorService(ExpressionRepository expressionRepository) {
    this.expressionRepository = expressionRepository;
  }

  public String evaluateExpression(ExpressionEntry req) {
    String result = req.getExpression() + " = " + evaluate(req.getExpression());
    req.setExpression(result);
    expressionRepository.save(req);
    return result;
  }

  public String[] getHistory(String username) {
    return expressionRepository.findByUsername(username)
        .stream()
        .map(ExpressionEntry::getExpression)
        .toArray(String[]::new);
  }

  private double evaluate(String expression) {
    Queue<String> tokens = tokenize(expression);
    Queue<String> outputQueue = toOutput(tokens);

    Stack<Double> rvpStack = new Stack<>();
    while (!outputQueue.isEmpty()) {
      String next = outputQueue.poll();
      if (isOperator(next)) {
        double secondNumber = rvpStack.pop();
        double firstNumber = rvpStack.pop();
        double result;
        switch (next) {
          case "+" -> result = firstNumber + secondNumber;
          case "-" -> result = firstNumber - secondNumber;
          case "*" -> result = firstNumber * secondNumber;
          case "/" -> {
            result = firstNumber / secondNumber;
            if (result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY) {
              throw new DivideByZeroException("Cannot divide by zero");
            }
          }
          default -> throw new IllegalArgumentException("Invalid operator");
        }
        rvpStack.add(result);
      } else {
        rvpStack.add(Double.parseDouble(next));
      }
    }

    return rvpStack.pop();
  }

  private Queue<String> tokenize(String expression) {
    Queue<String> output = new LinkedList<>();
    int[] index = {0};

    //Handle unary operators at the start of the expression.
    if (expression.charAt(0) == '+' || expression.charAt(0) == '-') {
      String s = getNextNumber(expression, index);
      String next = expression.charAt(0) == '+' ? s : "-" + s;
      output.add(next);
    }

    //Adds numbers and operators as strings to output.
    while(index[0] < expression.length()) {
      output.add(getNextNumber(expression, index));
      if (index[0] < expression.length()) output.add( Character.toString(expression.charAt(index[0]++)) );
    }

    return output;
  }

  private String getNextNumber(String expression, int[] index) {
    StringBuilder number = new StringBuilder();
    while(index[0] < expression.length() && !isOperator( Character.toString(expression.charAt(index[0])) )) {
      number.append(expression.charAt(index[0]++));
    }
    return number.toString();
  }

  private boolean isOperator(String c) {
    return Objects.equals(c, "*")
           || Objects.equals(c, "/")
           || Objects.equals(c, "+")
           || Objects.equals(c, "-");
  }

  private Queue<String> toOutput(Queue<String> tokens) {
    Queue<String> outputQueue = new LinkedList<>();
    Stack<Character> operatorStack = new Stack<>();
    Map<Character, Integer> priorities = new HashMap<>();
    priorities.put('+', 0);
    priorities.put('-', 0);
    priorities.put('*', 1);
    priorities.put('/', 1);

    while(!tokens.isEmpty()) {
      String token = tokens.poll();
      if (!isOperator(token)) {
        outputQueue.add(token);
      } else {
        while (!operatorStack.isEmpty() && (priorities.get(operatorStack.peek()) >= priorities.get(token.charAt(0)))) {
          outputQueue.add(Character.toString(operatorStack.pop()));
        }
        operatorStack.push(token.charAt(0));
      }
    }
    while(!operatorStack.isEmpty()) {
      outputQueue.add(Character.toString(operatorStack.pop()));
    }
    return outputQueue;
  }

}
