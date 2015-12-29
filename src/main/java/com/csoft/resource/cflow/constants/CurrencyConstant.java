package com.csoft.resource.cflow.constants;

public enum CurrencyConstant implements Constant{
    USD {
        public Integer code() {
            return 1;
        }

        public String desc() {
            return "USD";
        }
    },
    EUR {
        public Integer code() {
            return 2;
        }

        public String desc() {
            return "EUR";
        }
    },
    CNY {
        public Integer code() {
            return 3;
        }

        public String desc() {
            return "CNY";
        }
    },
    GBP {
        public Integer code() {
            return 4;
        }

        public String desc() {
            return "GBP";
        }
    },
    CAD {
        public Integer code() {
            return 5;
        }

        public String desc() {
            return "CAD";
        }
    },
    HKD {
        public Integer code() {
            return 6;
        }

    public String desc() {
        return "HKD";
    }
    };

    public static CurrencyConstant constantByCode(Object code) {
        for(CurrencyConstant executeResultConstant : CurrencyConstant.values()){
            if(executeResultConstant.code().intValue() == Integer.parseInt(code.toString())){
                return executeResultConstant ;
            }
        }


        return null;
    }

    public static CurrencyConstant constantByDesc(String desc) {
        for(CurrencyConstant executeResultConstant : CurrencyConstant.values()){
            if(executeResultConstant.desc().equals(desc)){
                return executeResultConstant ;
            }
        }
        return null;
    }
}
