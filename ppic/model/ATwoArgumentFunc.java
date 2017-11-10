package ppic.model;


public abstract class ATwoArgumentFunc extends Expression
{
    protected ATwoArgumentFunc (String type, Expression l, Expression r)
    {
        super(type, l, r);
    }


    protected ATwoArgumentFunc (String type, String expr)
        throws Exception
    {
        super(type, 2);

        int leftEnd = markSubExpression(expr, 0);
        _lhs = Parser.singleton.parse(expr.substring(0, leftEnd));
        leftEnd += (expr.charAt(leftEnd) == ' ' ? 1 : 0);
        int rightEnd = markSubExpression(expr, leftEnd);
        _rhs = Parser.singleton.parse(expr.substring(leftEnd, rightEnd));
    }


    public RGBColor evaluate (float x, float y, float z)
    {
        return evaluate(getLeft().evaluate(x, y, z),
                        getRight().evaluate(x, y, z));
    }


    public abstract RGBColor evaluate (RGBColor rgbcolor, RGBColor rgbcolor1);


    private int markSubExpression (String expr, int start)
    {
        int depth = 0;
        for (int k = start; k < expr.length(); k++)
        {
            char c = expr.charAt(k);
            if (c == '(')
            {
                depth++;
            }
            else if (c == ')')
            {
                depth--;
                if (depth == 0)
                {
                    return k + 1;
                }
            }
        }
        return expr.length();
    }
}
