package com.csoft.resource.cflow.constants;

public enum TaskExecutorStrategy implements Constant {
	Role {
		public Integer code() {
			return 11;
		}

		public String desc() {
			return "Role";
		}
	},
	Leader {
		public Integer code() {
			return 12;
		}

		public String desc() {  return "Leader";   }
	},
	Constant {
		public Integer code() {
			return 13;
		}

		public String desc() {
			return "Constant";
		}
	},
	Assign {
		public Integer code() {
			return 13;
		}

		public String desc() {
			return "Assign";
		}
	};

	public static TaskExecutorStrategy constantByCode(Integer code) {
		for (TaskExecutorStrategy taskExecutorStrategy : TaskExecutorStrategy.values()) {
			if(taskExecutorStrategy.code().equals(code)){
				return taskExecutorStrategy ;
			}
		}
		return null;
	}
	public static TaskExecutorStrategy constantByCode(String desc) {
		for (TaskExecutorStrategy taskExecutorStrategy : TaskExecutorStrategy.values()) {
			if(taskExecutorStrategy.desc().equals(desc)){
				return taskExecutorStrategy ;
			}
		}
		return null;
	}
}
