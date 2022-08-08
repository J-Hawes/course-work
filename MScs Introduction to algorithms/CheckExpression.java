import java.util.*;

public class CheckExpression
{
    private Stack<Character> stack;

    public CheckExpression()
    {
        stack = new Stack<Character>();
    }

    public void check(String exp)
    {
        if(valid(exp))
        {
            if(validate(exp))
            {
                System.out.println("Expression is valid");
            }
            else
            {
                System.out.println("Expression is not valid");
            }
        }
        else
        {
            System.out.println("Incorrect values entered");
        }
    }

    public void display()
    {
        for (Character c : stack) {
            System.out.println(c);
        }
    }
    
    @SuppressWarnings("resource") // Surpress unclosed scanner warning
    public static void main(String[] args)
    {
        CheckExpression test = new CheckExpression();
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter a mathematical expression to check :");
        String exp = console.nextLine();
        test.check(exp);
    }

    public boolean valid(String exp)
    {
        boolean valid = false;
        for(int i = 0; i < exp.length(); i++)
        {
            char val = exp.charAt(i);
            if((val >= '0') && (val <= '9') || (val == '+') || (val == '-')
            ||(val == '*') || (val == '/') || (val == '(') || (val == ')'))
            {
                valid = true;
            }
        }
        return valid;
    }

    public boolean validate(String exp)
    {
        boolean valid = true;
        boolean num = false;
        boolean operator = false;

        for(int i = 0; i < exp.length(); i++)
        {
            char val = exp.charAt(i);
            if((val == ')') && (stack.peek() != '(') && (num == false))
            {
                valid = false;
            }
            else if((val >= 0) && (val <=9))
            {
                if(operator)
                {
                    if((stack.peek() == '+') || (stack.peek() == '-') || (stack.peek() == '*') || (stack.peek() == '/'))
                    {
                        stack.pop();
                        operator = false;
                    }
                }
                num = true;
            }
            else if((val == '+') || (val == '-') || (val == '*') || (val == '/'))
            {
                if((i > 0) && (num))
                {
                    if((num) && (operator))
                    {
                        if((stack.peek() == '+') || (stack.peek() == '-') || (stack.peek() == '*') || (stack.peek() == '/'))
                        {
                            stack.pop();
                            num = false;
                        }
                    }
                    stack.push(val);
                    operator = true;
                    continue;
                }
                else
                {
                    break;
                }    
            }
            else if(val == '(')
            {
                stack.push(val);
                continue;
            }
            else
            {
                if(stack.isEmpty())
                {
                    valid = false;
                }
                if(!stack.isEmpty())
                {
                    if((stack.peek() == '+') || (stack.peek() == '-') || (stack.peek() == '*') || (stack.peek() == '/'))
                    {
                        stack.pop();
                        num = false;
                    }
                }
            }
        }
        return valid;
    }
}