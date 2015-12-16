package com.csoft.resource.cflow.constants;

public enum ProcessColumnTypeConstant implements Constant{
    Date {
        public Integer code() {
            return 11;
        }

        public String desc() {
            return "Date";
        }
    },
    Number {
        public Integer code() {
            return 12;
        }

        public String desc() {
            return "Number";
        }
    },
    List {
        public Integer code() {
            return 13;
        }

        public String desc() {
            return "List";
        }
    },
    String {
        public Integer code() {
            return 14;
        }

        public String desc() {
            return "String";
        }
    },
    Currency{
        public Integer code() {
            return 15;
        }

        public String desc() {
            return "Currency";
        }
    };

    public static ProcessColumnTypeConstant constantByCode(Object code) {
        for (ProcessColumnTypeConstant processColumnTypeConstant :ProcessColumnTypeConstant.values()) {
            if(processColumnTypeConstant.code().equals(code)){
                return processColumnTypeConstant ;
            }
        }
        return null;
    }

    public static ProcessColumnTypeConstant constantByCode(String desc) {
        for (ProcessColumnTypeConstant processColumnTypeConstant :ProcessColumnTypeConstant.values()) {
            if(processColumnTypeConstant.desc().equals(desc)){
                return processColumnTypeConstant ;
            }
        }
        return null;
    }
}
