package com.csoft.resource.cflow.constants;

public enum DepartmentDimensionEnum implements Constant
{
    Operation
    {
        public Integer code()
        {
            return 300;
        }
        
        public String desc()
        {
            return "Operation Dimension";
        }
    },
    Organization
    {
        public Integer code()
        {
            return 301;
        }
        
        public String desc()
        {
            return "Organization Dimension";
        }
    };
    
    @Override
    public String toString()
    {
        return desc();
    }
    
    public static DepartmentDimensionEnum constantByCode(Object code)
    {
        DepartmentDimensionEnum[] types = DepartmentDimensionEnum.values();
        for (int i = 0; i < types.length; i++)
        {
            if (types[i].code().equals(code))
            {
                return types[i];
            }
        }
        return null;
    }
}
