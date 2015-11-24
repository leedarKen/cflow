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
        TaskStatusConstants[] types = TaskStatusConstants.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].code().equals(code)) {
                return types[i];
            }
        }

        return null;
    }
}
