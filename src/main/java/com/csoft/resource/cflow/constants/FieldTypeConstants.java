package com.csoft.resource.cflow.constants;

public enum FieldTypeConstants implements Constant {
	Date {
		 public Integer code() {return 1;}
	     public String desc() {return "Date";}
	},
	Number {
		public Integer code() {return 2;}
	     public String desc() {return "Number";}
	},
	Varchar {
		public Integer code() {return 3;}
	     public String desc() {return "Varchar";}
	},
	CheckBox {
		public Integer code() {return 4;}
	     public String desc() {return "CheckBox";}
	} ;
	
	@Override
    public String toString() {
    	return desc();
    }
    
    public static FieldTypeConstants constantByCode(Object code) {
		FieldTypeConstants[] types = FieldTypeConstants.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].code().equals(code)) {
                return types[i];
            }
        }
        
        return null;
    }
}
