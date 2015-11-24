package com.csoft.resource.cflow.constants;

/**
 * Created by ken.kang on 2015/11/24.
 */
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

    public static ProcessActionConstamts constantByCode(Object code) {
        ProcessActionConstamts[] types = ProcessActionConstamts.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].code().equals(code)) {
                return types[i];
            }
        }

        return null;
    }
}
