package com.csoft.resource.cflow.pojo;

import java.util.ArrayList;
import java.util.List;

public class CfHistoryInstanceInputValueExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    public CfHistoryInstanceInputValueExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTaskInstanceIdIsNull() {
            addCriterion("task_instance_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskInstanceIdIsNotNull() {
            addCriterion("task_instance_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskInstanceIdEqualTo(Long value) {
            addCriterion("task_instance_id =", value, "taskInstanceId");
            return (Criteria) this;
        }

        public Criteria andTaskInstanceIdNotEqualTo(Long value) {
            addCriterion("task_instance_id <>", value, "taskInstanceId");
            return (Criteria) this;
        }

        public Criteria andTaskInstanceIdGreaterThan(Long value) {
            addCriterion("task_instance_id >", value, "taskInstanceId");
            return (Criteria) this;
        }

        public Criteria andTaskInstanceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("task_instance_id >=", value, "taskInstanceId");
            return (Criteria) this;
        }

        public Criteria andTaskInstanceIdLessThan(Long value) {
            addCriterion("task_instance_id <", value, "taskInstanceId");
            return (Criteria) this;
        }

        public Criteria andTaskInstanceIdLessThanOrEqualTo(Long value) {
            addCriterion("task_instance_id <=", value, "taskInstanceId");
            return (Criteria) this;
        }

        public Criteria andTaskInstanceIdIn(List<Long> values) {
            addCriterion("task_instance_id in", values, "taskInstanceId");
            return (Criteria) this;
        }

        public Criteria andTaskInstanceIdNotIn(List<Long> values) {
            addCriterion("task_instance_id not in", values, "taskInstanceId");
            return (Criteria) this;
        }

        public Criteria andTaskInstanceIdBetween(Long value1, Long value2) {
            addCriterion("task_instance_id between", value1, value2, "taskInstanceId");
            return (Criteria) this;
        }

        public Criteria andTaskInstanceIdNotBetween(Long value1, Long value2) {
            addCriterion("task_instance_id not between", value1, value2, "taskInstanceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstaceIdIsNull() {
            addCriterion("process_instace_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessInstaceIdIsNotNull() {
            addCriterion("process_instace_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessInstaceIdEqualTo(Long value) {
            addCriterion("process_instace_id =", value, "processInstaceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstaceIdNotEqualTo(Long value) {
            addCriterion("process_instace_id <>", value, "processInstaceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstaceIdGreaterThan(Long value) {
            addCriterion("process_instace_id >", value, "processInstaceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstaceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("process_instace_id >=", value, "processInstaceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstaceIdLessThan(Long value) {
            addCriterion("process_instace_id <", value, "processInstaceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstaceIdLessThanOrEqualTo(Long value) {
            addCriterion("process_instace_id <=", value, "processInstaceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstaceIdIn(List<Long> values) {
            addCriterion("process_instace_id in", values, "processInstaceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstaceIdNotIn(List<Long> values) {
            addCriterion("process_instace_id not in", values, "processInstaceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstaceIdBetween(Long value1, Long value2) {
            addCriterion("process_instace_id between", value1, value2, "processInstaceId");
            return (Criteria) this;
        }

        public Criteria andProcessInstaceIdNotBetween(Long value1, Long value2) {
            addCriterion("process_instace_id not between", value1, value2, "processInstaceId");
            return (Criteria) this;
        }

        public Criteria andProcessIdIsNull() {
            addCriterion("process_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessIdIsNotNull() {
            addCriterion("process_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessIdEqualTo(String value) {
            addCriterion("process_id =", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotEqualTo(String value) {
            addCriterion("process_id <>", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdGreaterThan(String value) {
            addCriterion("process_id >", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_id >=", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLessThan(String value) {
            addCriterion("process_id <", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLessThanOrEqualTo(String value) {
            addCriterion("process_id <=", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLike(String value) {
            addCriterion("process_id like", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotLike(String value) {
            addCriterion("process_id not like", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdIn(List<String> values) {
            addCriterion("process_id in", values, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotIn(List<String> values) {
            addCriterion("process_id not in", values, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdBetween(String value1, String value2) {
            addCriterion("process_id between", value1, value2, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotBetween(String value1, String value2) {
            addCriterion("process_id not between", value1, value2, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessNameIsNull() {
            addCriterion("process_name is null");
            return (Criteria) this;
        }

        public Criteria andProcessNameIsNotNull() {
            addCriterion("process_name is not null");
            return (Criteria) this;
        }

        public Criteria andProcessNameEqualTo(String value) {
            addCriterion("process_name =", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotEqualTo(String value) {
            addCriterion("process_name <>", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameGreaterThan(String value) {
            addCriterion("process_name >", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameGreaterThanOrEqualTo(String value) {
            addCriterion("process_name >=", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLessThan(String value) {
            addCriterion("process_name <", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLessThanOrEqualTo(String value) {
            addCriterion("process_name <=", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLike(String value) {
            addCriterion("process_name like", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotLike(String value) {
            addCriterion("process_name not like", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameIn(List<String> values) {
            addCriterion("process_name in", values, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotIn(List<String> values) {
            addCriterion("process_name not in", values, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameBetween(String value1, String value2) {
            addCriterion("process_name between", value1, value2, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotBetween(String value1, String value2) {
            addCriterion("process_name not between", value1, value2, "processName");
            return (Criteria) this;
        }

        public Criteria andCfKeyIsNull() {
            addCriterion("cf_key is null");
            return (Criteria) this;
        }

        public Criteria andCfKeyIsNotNull() {
            addCriterion("cf_key is not null");
            return (Criteria) this;
        }

        public Criteria andCfKeyEqualTo(String value) {
            addCriterion("cf_key =", value, "cfKey");
            return (Criteria) this;
        }

        public Criteria andCfKeyNotEqualTo(String value) {
            addCriterion("cf_key <>", value, "cfKey");
            return (Criteria) this;
        }

        public Criteria andCfKeyGreaterThan(String value) {
            addCriterion("cf_key >", value, "cfKey");
            return (Criteria) this;
        }

        public Criteria andCfKeyGreaterThanOrEqualTo(String value) {
            addCriterion("cf_key >=", value, "cfKey");
            return (Criteria) this;
        }

        public Criteria andCfKeyLessThan(String value) {
            addCriterion("cf_key <", value, "cfKey");
            return (Criteria) this;
        }

        public Criteria andCfKeyLessThanOrEqualTo(String value) {
            addCriterion("cf_key <=", value, "cfKey");
            return (Criteria) this;
        }

        public Criteria andCfKeyLike(String value) {
            addCriterion("cf_key like", value, "cfKey");
            return (Criteria) this;
        }

        public Criteria andCfKeyNotLike(String value) {
            addCriterion("cf_key not like", value, "cfKey");
            return (Criteria) this;
        }

        public Criteria andCfKeyIn(List<String> values) {
            addCriterion("cf_key in", values, "cfKey");
            return (Criteria) this;
        }

        public Criteria andCfKeyNotIn(List<String> values) {
            addCriterion("cf_key not in", values, "cfKey");
            return (Criteria) this;
        }

        public Criteria andCfKeyBetween(String value1, String value2) {
            addCriterion("cf_key between", value1, value2, "cfKey");
            return (Criteria) this;
        }

        public Criteria andCfKeyNotBetween(String value1, String value2) {
            addCriterion("cf_key not between", value1, value2, "cfKey");
            return (Criteria) this;
        }

        public Criteria andCfKeyTypeIsNull() {
            addCriterion("cf_key_type is null");
            return (Criteria) this;
        }

        public Criteria andCfKeyTypeIsNotNull() {
            addCriterion("cf_key_type is not null");
            return (Criteria) this;
        }

        public Criteria andCfKeyTypeEqualTo(String value) {
            addCriterion("cf_key_type =", value, "cfKeyType");
            return (Criteria) this;
        }

        public Criteria andCfKeyTypeNotEqualTo(String value) {
            addCriterion("cf_key_type <>", value, "cfKeyType");
            return (Criteria) this;
        }

        public Criteria andCfKeyTypeGreaterThan(String value) {
            addCriterion("cf_key_type >", value, "cfKeyType");
            return (Criteria) this;
        }

        public Criteria andCfKeyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cf_key_type >=", value, "cfKeyType");
            return (Criteria) this;
        }

        public Criteria andCfKeyTypeLessThan(String value) {
            addCriterion("cf_key_type <", value, "cfKeyType");
            return (Criteria) this;
        }

        public Criteria andCfKeyTypeLessThanOrEqualTo(String value) {
            addCriterion("cf_key_type <=", value, "cfKeyType");
            return (Criteria) this;
        }

        public Criteria andCfKeyTypeLike(String value) {
            addCriterion("cf_key_type like", value, "cfKeyType");
            return (Criteria) this;
        }

        public Criteria andCfKeyTypeNotLike(String value) {
            addCriterion("cf_key_type not like", value, "cfKeyType");
            return (Criteria) this;
        }

        public Criteria andCfKeyTypeIn(List<String> values) {
            addCriterion("cf_key_type in", values, "cfKeyType");
            return (Criteria) this;
        }

        public Criteria andCfKeyTypeNotIn(List<String> values) {
            addCriterion("cf_key_type not in", values, "cfKeyType");
            return (Criteria) this;
        }

        public Criteria andCfKeyTypeBetween(String value1, String value2) {
            addCriterion("cf_key_type between", value1, value2, "cfKeyType");
            return (Criteria) this;
        }

        public Criteria andCfKeyTypeNotBetween(String value1, String value2) {
            addCriterion("cf_key_type not between", value1, value2, "cfKeyType");
            return (Criteria) this;
        }

        public Criteria andCfValueIsNull() {
            addCriterion("cf_value is null");
            return (Criteria) this;
        }

        public Criteria andCfValueIsNotNull() {
            addCriterion("cf_value is not null");
            return (Criteria) this;
        }

        public Criteria andCfValueEqualTo(String value) {
            addCriterion("cf_value =", value, "cfValue");
            return (Criteria) this;
        }

        public Criteria andCfValueNotEqualTo(String value) {
            addCriterion("cf_value <>", value, "cfValue");
            return (Criteria) this;
        }

        public Criteria andCfValueGreaterThan(String value) {
            addCriterion("cf_value >", value, "cfValue");
            return (Criteria) this;
        }

        public Criteria andCfValueGreaterThanOrEqualTo(String value) {
            addCriterion("cf_value >=", value, "cfValue");
            return (Criteria) this;
        }

        public Criteria andCfValueLessThan(String value) {
            addCriterion("cf_value <", value, "cfValue");
            return (Criteria) this;
        }

        public Criteria andCfValueLessThanOrEqualTo(String value) {
            addCriterion("cf_value <=", value, "cfValue");
            return (Criteria) this;
        }

        public Criteria andCfValueLike(String value) {
            addCriterion("cf_value like", value, "cfValue");
            return (Criteria) this;
        }

        public Criteria andCfValueNotLike(String value) {
            addCriterion("cf_value not like", value, "cfValue");
            return (Criteria) this;
        }

        public Criteria andCfValueIn(List<String> values) {
            addCriterion("cf_value in", values, "cfValue");
            return (Criteria) this;
        }

        public Criteria andCfValueNotIn(List<String> values) {
            addCriterion("cf_value not in", values, "cfValue");
            return (Criteria) this;
        }

        public Criteria andCfValueBetween(String value1, String value2) {
            addCriterion("cf_value between", value1, value2, "cfValue");
            return (Criteria) this;
        }

        public Criteria andCfValueNotBetween(String value1, String value2) {
            addCriterion("cf_value not between", value1, value2, "cfValue");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table cf_history_instance_input_value
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}