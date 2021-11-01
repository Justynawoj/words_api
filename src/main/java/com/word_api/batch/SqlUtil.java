package com.word_api.batch;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

@Component
public class SqlUtil {
  //  @Autowired private ApplicationContext context;
    @Autowired private DataSource datasource;

    // SQLFileName could be your schema-all.sql
    // inject this class somewhere and call sqlUtil.runSqlFile("schema-all.sql");
    public void runSqlFile(String SQLFileName) {
        Resource resource = new PathResource("C:\\Users\\39334\\IdeaProjects\\WordsApi\\src\\main\\resources\\schema.sql");
        EncodedResource encodedResource = new EncodedResource(resource, StandardCharsets.UTF_8);
        try {
            ScriptUtils.executeSqlScript(datasource.getConnection(), encodedResource);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}