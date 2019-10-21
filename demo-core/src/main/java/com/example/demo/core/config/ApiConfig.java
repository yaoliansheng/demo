/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，1995-2017，所有权利保留。
 * 项目名：parent
 * 文件名：ApiConfig
 * 模块说明：
 * 修改历史：
 * 2017-07-04 - suizhe - 创建
 */
package com.example.demo.core.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ApiConfig
 *
 * @author suizhe
 */
@Configuration
@EnableSwagger2
public class ApiConfig {

  @Value("${swagger.show: true}")
  private boolean swaggerShow;

  @Bean
  public Docket adminApi() {
    return (new Docket(DocumentationType.SWAGGER_2)).forCodeGeneration(true).directModelSubstitute(LocalDate.class,
            Date.class).directModelSubstitute(LocalDateTime.class, java.util.Date.class).select().paths(paths()).build().apiInfo(this.apiInfo()).enable(swaggerShow);
  }

  private Predicate<String> paths(){
    return Predicates.and(PathSelectors.regex("/demo/.*"),
                          Predicates.not(PathSelectors.regex("/error")));
  }

  private ApiInfo apiInfo() {
    ApiInfo apiInfo = new ApiInfoBuilder().title("测试").description("demo").license(" ").version
            ("1.0").build();
    return apiInfo;
  }
}
