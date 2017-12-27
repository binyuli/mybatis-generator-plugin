package bz.sunlight.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

public class BaseMapperGeneratorPlugin extends PluginAdapter {

  public boolean validate(List<String> warnings) {
    return true;
  }

  /**
   * 生成dao.
   */
  @Override
  public boolean clientGenerated(Interface interfaze,
                                 TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    // 主键默认采用java.lang.String
    FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("BaseMapper<"
        + introspectedTable.getBaseRecordType() + ","
        + introspectedTable.getExampleType() + ","
        + "java.lang.String" + ">");

    // 添加 extends MybatisBaseMapper
    interfaze.addSuperInterface(fqjt);

    // 添加 bz.sunlight.BaseMapper.
    FullyQualifiedJavaType imp = new FullyQualifiedJavaType(
        "bz.sunlight.BaseMapper");
    interfaze.addImportedType(imp);

    /*
    // 移除不必要依赖 failed
    FullyQualifiedJavaType impList = new FullyQualifiedJavaType(
        "java.util.List");
    FullyQualifiedJavaType impParam = new FullyQualifiedJavaType(
        "org.apache.ibatis.annotations.Param");
    interfaze.getImportedTypes().remove(impList);
    interfaze.getImportedTypes().remove(impParam);
    */

    // 去方法和注释
    interfaze.getMethods().clear();
    interfaze.getAnnotations().clear();
    return true;
  }

}
