package com.csoft.resource.cflow.constants;

public enum ProcessTasktypeConstamts implements Constant {
	Begin {
		public Integer code() {
			return 11;
		}

		public String desc() {
			return "Begin";
		}
	},
	End {
		public Integer code() {
			return 12;
		}

		public String desc() {  return "End";   }
	};

	public static ProcessTasktypeConstamts constantByCode(Integer code) {
		for(ProcessTasktypeConstamts type : ProcessTasktypeConstamts.values()){
			if (type.code().equals(code)) {
				return type;
			}
		}
		return null;
	}
	public static ProcessTasktypeConstamts constantByCode(String desc) {
		for(ProcessTasktypeConstamts type : ProcessTasktypeConstamts.values()){
			if (type.desc().equals(desc)) {
				return type;
			}
		}
		return null;
	}
}
