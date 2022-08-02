package ${package.Service};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bc.promote.common.base.model.PageReqDTO;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};

import java.util.List;

/**
* ${table.comment!} 服务类
*
* @author 万爷
* @since ${date}
*/
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

  /**
  * 查询list
  * @param ${entity?uncap_first}
  * @return
  */
  List<${entity}> queryList(${entity} ${entity?uncap_first});

  /**
  * 分页查询
  * @param pageReqDTO
  * @return
  */
  IPage<${entity}> queryListPage(PageReqDTO<${entity}> pageReqDTO);
}
