package com.csoft.resource.cflow.constants;


public enum  ColumnValueTypeConstant implements Constant {
	Equal {
		public Integer code() {
			return 11;
		}

		public String desc() {
			return "Equal";
		}
	},
	Smaller {
		public Integer code() {
			return 12;
		}

		public String desc() {
			return "Smaller";
		}
	},
	Greater {
		public Integer code() {
			return 13;
		}

		public String desc() {
			return "Greater";
		}
	};

	public static ColumnValueTypeConstant constantByCode(Object code) {
		for (ColumnValueTypeConstant columnValueTypeConstant : ColumnValueTypeConstant.values()) {
			if(columnValueTypeConstant.code().equals(code)){
				return columnValueTypeConstant ;
			}
		}
		return null;
	}

	public static ColumnValueTypeConstant constantByCode(String desc) {
		for (ColumnValueTypeConstant columnValueTypeConstant : ColumnValueTypeConstant.values()) {
			if(columnValueTypeConstant.desc().equals(desc)){
				return columnValueTypeConstant ;
			}
		}
		return null;
	}
}
