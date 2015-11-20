package java.com.csoft.resource.cflow.mapper;

import com.csoft.resource.cflow.pojo.RmService;
import com.csoft.resource.cflow.pojo.RmServiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RmServiceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rm_service
     *
     * @mbggenerated
     */
    int countByExample(RmServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rm_service
     *
     * @mbggenerated
     */
    int deleteByExample(RmServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rm_service
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rm_service
     *
     * @mbggenerated
     */
    int insert(RmService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rm_service
     *
     * @mbggenerated
     */
    int insertSelective(RmService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rm_service
     *
     * @mbggenerated
     */
    List<RmService> selectByExample(RmServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rm_service
     *
     * @mbggenerated
     */
    RmService selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rm_service
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") RmService record, @Param("example") RmServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rm_service
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") RmService record, @Param("example") RmServiceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rm_service
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RmService record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rm_service
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RmService record);
}