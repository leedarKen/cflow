package com.csoft.resource.cflow.constants;

public enum ExecuteResultConstant implements Constant{
    Success {
        public Integer code() {
            return 11;
        }

        public String desc() {
            return "Success";
        }
    },
    Approve {
        public Integer code() {
            return 12;
        }

        public String desc() {
            return "Approve";
        }
    },
    Reject {
        public Integer code() {
            return 13;
        }

        public String desc() {
            return "Reject";
        }
    },
    Finish {
        public Integer code() {
            return 14;
        }

        public String desc() {
            return "Finish";
        }
    };

    public static ExecuteResultConstant constantByCode(Object code) {
        for(ExecuteResultConstant executeResultConstant : ExecuteResultConstant.values()){
            if(executeResultConstant.code().equals(code)){
                return executeResultConstant ;
            }
        }
        return null;
    }

    public static ExecuteResultConstant constantByCode(String desc) {
        for(ExecuteResultConstant executeResultConstant : ExecuteResultConstant.values()){
            if(executeResultConstant.desc().equals(desc)){
                return executeResultConstant ;
            }
        }
        return null;
    }
}
