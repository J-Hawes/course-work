package infix2postfix;

public class InfixToPostfix 
{
    private StackADT stack;
    private QueueADT infix;
    private QueueADT postfix;
    public boolean valid;

    public InfixToPostfix()
    {
       
    }
    
    // Method that converts the infix expression to postfix notation
    private String convertToPostfix(String exp)
    {
        valid = true;
        stack = new StackADT();
        infix = new QueueADT();
        postfix = new QueueADT();
        String result = "Expresion is ";

        // Read the infix expression
        for (int i = 0; i < exp.length(); i++)
        {
            // Remove any whitespace
            if(Character.isWhitespace(exp.charAt(i))) 
            {
                continue;
            }
            else
            {
                // Validation check for 2 or more operators side by side
                if((i> 1) && (isOperator(exp.charAt(i))) && (isOperator(exp.charAt(i-2))))
                {
                    valid = false;
                }
                // Store each character into a queue infix
                String st = Character.toString(exp.charAt(i));
                infix.enqueue(st);
            }
        }
        // Push a left parenthesis '(' onto the stack
        stack.push("("); 
        // Append a right parenthesis ')' to the back of infix queue
        infix.enqueue(")"); 

        while(!stack.isEmpty())
        {
            char temp = infix.queueFront().charAt(0);

            // If the current character in infix is a digit, enqueue it into postfix
            if(Character.isDigit(temp))
            {
                postfix.enqueue(infix.dequeue());
            }

            // If the current character in infix is a left parenthesis, push it onto the stack
            else if(temp == '(')
            {
                stack.push(infix.dequeue());
            }

            // If the current character in infix is an operator
            else if(isOperator(temp))
            {
                if(!stack.isEmpty())
                {
                    char temp2 = stack.stackTop().charAt(0);
                    
                    /* Pop operators (if there are any) at the top of the stack while they have 
                    equal or higher precedence than the current operator and enqueue the popped 
                    operators into postfix */
                    if(isOperator(temp2))
                    {
                        while(precedence(temp2) >= precedence(temp))
                        {
                            postfix.enqueue(stack.pop());
                            temp2 = stack.stackTop().charAt(0);
                        }
                        stack.push(infix.dequeue());
                    }
                    
                    // Push the current character in infix onto the stack
                    else
                    {
                        stack.push(infix.dequeue());
                    } 
                }
            }

            // If the current character in infix is a right parenthesis
            else if(temp == ')')
            {
                /* Pop operators from the top of the stack and enqueue them in postfix 
                until a left parenthesis is at the top of the stack. */
                char temp3 = stack.stackTop().charAt(0);
                while((!stack.isEmpty()) && (temp3 != '(') )
                {
                    postfix.enqueue(stack.pop()); 
                    temp3 = stack.stackTop().charAt(0);
                }
                // Pop (and discard) one left parenthesis from the stack
                stack.pop();
                infix.dequeue();
            }

            // If encountering an invalid character used in expression (letter / symbol)
            else 
            {
                break; // exit conversion
            }
        }
        
        // Evaluate if the expression processed in full 
        if((!infix.isEmpty()) || (!stack.isEmpty()) || (!valid))
        {
            result += "Invalid!";
            valid = false;
        }
        else
        {
            result += "Valid! \nPostfix: ";
            
            // Dequeue postfix and add result to string
            while(!postfix.isEmpty())
            {
                result += postfix.dequeue(); 
            }
        }
        return result;   
    }

    // Method that determines whether a character is an operator
    private boolean isOperator(char ch)
    {
        return precedence(ch) > 0;
    }

    /* Method that determines whether the precedence of operator1 is less than
    equal to or greater than the precedence of operator2 */
    private int precedence(char ch)
    {
        int prec = 0;
        switch(ch)
        {
            case '+':
            case '-':
            prec = 1;
            break;
            case '*':
            case '/':
            case '%':
            prec = 2;
            break;
            case '^':
            prec = 3;
            break;
        }
        return prec;
    }
    
    public static void main(String[] args)
    {
        InfixToPostfix test = new InfixToPostfix();
        String expression1 = "(6 + + 4 / 8 % 6 + ( 7 + ( 9 * 8 ) ) % 2 ^ 4 - 7)"; //bad example
        String expression2 = "2 * 3 (4 / 1) + 9 ^ 5 + (6 % 2) - 7"; //good example
        String expression3 = "( (4 - 2 * 3 ) % 3 + 9 ) / 4 ^ 7 * 2 % 8 + (4) )"; //bad example
        String expression4 = "1 + 2 - 3 * 4 / 5 ^ 6 % 7 + (8 * 9) - 1"; //good example
        System.out.println("Expression 1: " + expression1);
        System.out.println(test.convertToPostfix(expression1));
        System.out.println();
        System.out.println("Expression 2: " + expression2);
        System.out.println(test.convertToPostfix(expression2));
        System.out.println();
        System.out.println("Expression 3: " + expression3);
        System.out.println(test.convertToPostfix(expression3));
        System.out.println();
        System.out.println("Expression 4: " + expression4);
        System.out.println(test.convertToPostfix(expression4));
    }

}
