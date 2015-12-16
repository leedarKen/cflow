package com.csoft.resource.cflow.constants;

public enum ProcessActionConstamts implements Constant {
    Input {
        public Integer code() {
            return 11;
        }

        public String desc() {
            return "Input";
        }
    },
    Approve {
        public Integer code() {
            return 12;
        }

        public String desc() {  return "Approve";   }
    },
    notice {
        public Integer code() {
            return 13;
        }

        public String desc() {
            return "notice";
        }
    };

    public static ProcessActionConstamts  constantByCode(Integer code) {
        for (ProcessActionConstamts processActionConstamts :ProcessActionConstamts.values()) {
            if(processActionConstamts.code().equals(code)){
                return processActionConstamts ;
            }
        }
        return null;
    }
    public static ProcessActionConstamts constantByCode(String desc) {
        for (ProcessActionConstamts processActionConstamts :ProcessActionConstamts.values()) {
            if(processActionConstamts.desc().equals(desc)){
                return processActionConstamts ;
            }
        }
        return null;
    }
}
