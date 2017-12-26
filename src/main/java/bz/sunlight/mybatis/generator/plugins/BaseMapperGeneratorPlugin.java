package bz.sunlight.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

public class BaseMapperGeneratorPlugin extends PluginAdapter {

    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * 生成dao
     */
    @Override
    public boolean clientGenerated(Interface interfaze,
                                   TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        /**
         * 主键默认采用java.lang.String
         */
        FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("BaseMapper<"
                + introspectedTable.getBaseRecordType() + ","
                + introspectedTable.getExampleType() + ","
                + "java.lang.String" + ">");
        FullyQualifiedJavaType imp = new FullyQualifiedJavaType(
                "bz.sunlight.BaseMapper");
        /**
         * 添加 extends MybatisBaseMapper
         */
        interfaze.addSuperInterface(fqjt);

        /**
         * 添加import bz.sunlight.BaseMapper.
         */
        interfaze.addImportedType(imp);

        List<Method> methods = interfaze.getMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }
        /**
         * 方法不需要
         */
        interfaze.getMethods().clear();
        interfaze.getAnnotations().clear();
        return true;
    }

}
