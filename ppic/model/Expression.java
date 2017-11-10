package ppic.model;


public abstract class Expression implements Cloneable
{
    private String _type;
    private int _numArgs;
    protected Expression _lhs;
    protected Expression _rhs;


    protected Expression (String type, Expression ... exp)
    {
        _type = type;
        _numArgs = exp.length;
        _lhs = (_numArgs > 0) ? exp[0] : null;
        _rhs = (_numArgs > 1) ? exp[1] : null;
    }


    protected Expression (String type, int numArgs)
    {
        _type = type;
        _numArgs = numArgs;
        _lhs = null;
        _rhs = null;
    }


    public String getType ()
    {
        return _type;
    }


    public int getDegree ()
    {
        return _numArgs;
    }


    public Expression getLeft ()
    {
        return _lhs;
    }

    
    public Expression getRight ()
    {
        return _rhs;
    }
    
    public void setLeft(Expression lft) {
    		_lhs = lft;
    }
    
    public void setRight(Expression rt) {
		_rhs = rt;
    }


    public boolean equals (Object other)
    {
        return other.getClass().getName().equals(getClass().getName());
    }


    public Expression copy ()
    {
        try
        {
            Expression result = (Expression)super.clone();
            result._lhs = (_numArgs > 0) ? _lhs.copy() : null;
            result._rhs = (_numArgs > 1) ? _rhs.copy() : null;
            return result;
        }
        catch (CloneNotSupportedException e)
        {
            return null;
        }
    }


    public String toString ()
    {
        String result = "(" + getType();
        if (getDegree() > 0)
            result += " " + getLeft();
        if (getDegree() > 1)
            result += " " + getRight();
        return result + ")";
    }

    
    public abstract RGBColor evaluate (float x, float y, float z);
}
