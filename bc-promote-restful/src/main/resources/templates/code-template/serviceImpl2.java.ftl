package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bc.promote.common.base.model.PageReqDTO;
import com.bc.promote.common.utils.CommonUtils;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* ${table.comment!} 服务实现类
*
* @author 万爷
* @since ${date}
*/
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

  /**
  * 查询list
  * @param ${entity?uncap_first}
  * @return
  */
  @Override
  public List<${entity}> queryList(${entity} ${entity?uncap_first}) {
    LambdaQueryWrapper<${entity}> queryWrapper = Wrappers.lambdaQuery(${entity?uncap_first});
    return this.list(queryWrapper);
  }

  /**
  * 分页查询
  * @param pageReqDTO
  * @return
  */
  @Override
  public IPage<${entity}> queryListPage(PageReqDTO<${entity}> pageReqDTO) {
    if(null != pageReqDTO.getEntity()){
      ${entity} entity = pageReqDTO.getEntity();
      Page<${entity}> page = new Page<>(pageReqDTO.getPageNo(),pageReqDTO.getPageSize());
      LambdaQueryWrapper<${entity}> queryWrapper = Wrappers.lambdaQuery(entity);
      return this.page(page, queryWrapper);
    }
    return null;
  }
}
