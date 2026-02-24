package com.portfolio.dsa.postfix;

// Class responsible for evaluating postfix expressions
class PostfixEvaluator {

    // Method to evaluate a given postfix expression
    public static int evaluate(String expression) {
        Stack<Integer> stack = new LinkedStack<>(); // Create a stack to store operands
        String[] tokens = expression.split(" "); // Split the expression

        // Iterate through each token in the expression
        for (String token : tokens) {

            // If the token is a number, push it onto the stack
            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));

            } else { // If the token is an operator, perform the corresponding operation

                // Check if there are enough operands in the stack
                if (stack.isEmpty())
                    throw new IllegalArgumentException("Not enough operands");
                int b = stack.pop(); // Pop the first operand

                if (stack.isEmpty())
                    throw new IllegalArgumentException("Not enough operands");
                int a = stack.pop(); // Pop the second operand

                int result; // Variable to store the operation result

                // Perform the operation based on the operator token
                switch (token) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        if (b == 0)
                            throw new ArithmeticException("Division by zero"); // Prevent division by zero
                        result = a / b;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + token); // Invalid operator error
                }

                stack.push(result); // Push the result back onto the stack
            }
        }

        // Final validation: ensure only one result is left in the stack
        if (!stack.isEmpty()) {
            int result = stack.pop(); // Get the final result

            if (!stack.isEmpty()) { // If more values exist, the expression was incorrect
                throw new IllegalArgumentException("Too many operands");
            }
            return result; // Return the final computed result
        }

        return stack.pop(); // Return the result

    }
}
