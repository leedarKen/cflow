package com.csoft.resource.cflow.constants;

/**
 * Created by ken.kang on 2015/11/24.
 */
public enum ProcessColumnTypeConstant implements Constant{
    Data {
        public Integer code() {
            return 11;
        }

        public String desc() {
            return "Data";
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
    };

    public static ProcessColumnTypeConstant constantByCode(Object code) {
        ProcessColumnTypeConstant[] types = ProcessColumnTypeConstant.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].code().equals(code)) {
                return types[i];
            }
        }

        return null;
    }
}
