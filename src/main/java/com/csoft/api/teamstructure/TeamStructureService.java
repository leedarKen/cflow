/*SMC1.0 auto generator java code*/
package com.csoft.api.teamstructure;

/**
 * application : resource_core
 * protocols : hessian
 * version : 1.0.0
 * remark : teamStructureService
 */
public interface TeamStructureService
{
    java.util.List<com.csoft.api.teamstructure.vo.UserVo> findImmediateSuperiorsBasedOnCrossDimension(int staffId);
    java.util.List<com.csoft.api.teamstructure.vo.UserVo> findImmediateSuperiorsBasedOnDimension(int staffId, int dimensionId);
}
