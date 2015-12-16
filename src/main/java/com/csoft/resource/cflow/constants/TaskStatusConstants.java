package com.csoft.resource.cflow.constants;

public enum TaskStatusConstants implements Constant {
    Sleeping {
        public Integer code() {
            return 11;
        }

        public String desc() {
            return "Sleeping";
        }
    },
    Going {
        public Integer code() {
            return 12;
        }

        public String desc() {
            return "Going";
        }
    },
    Done {
        public Integer code() {
            return 13;
        }

        public String desc() {
            return "Done";
        }
    };

    public static TaskStatusConstants constantByCode(Object code) {
        for(TaskStatusConstants type : TaskStatusConstants.values()){
            if (type.code().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
