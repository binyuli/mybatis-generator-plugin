package bz.sunlight.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    try {
      // 迭代器移除
      for (Iterator<FullyQualifiedJavaType> it = interfaze.getImportedTypes().iterator(); it.hasNext(); ) {
        FullyQualifiedJavaType importedType = it.next();
        if (importedType.getFullyQualifiedName().equals("java.util.List") ||
            importedType.getFullyQualifiedName().equals("org.apache.ibatis.annotations.Param")) {
          it.remove();
        }
      }
      // 加强 for 循环移除
      Set<FullyQualifiedJavaType> imps = interfaze.getImportedTypes();
      for (FullyQualifiedJavaType importedType : imps) {
        if (importedType.getFullyQualifiedName().equals("java.util.List") ||
            importedType.getFullyQualifiedName().equals("org.apache.ibatis.annotations.Param")) {
          imps.remove(importedType);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    */

    // 去方法和注释
    interfaze.getMethods().clear();
    interfaze.getAnnotations().clear();
    return true;
  }

}
