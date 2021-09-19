package com.word_api.config;

import com.word_api.domains.Lemma;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class CoreConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
     private DataSource dataSource;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    FlatFileItemReader<Lemma> readFromCsv(){
 /*       FlatFileItemReader<Lemma> reader = new FlatFileItemReader<Lemma>();
        reader.setResource(new FileSystemResource("C://Users/39334/IdeaProjects/WordsApi/src/main/resources/lemmasCsv.csv"));
      //  reader.setResource(new ClassPathResource("lemmasCsv.csv"));
        reader.setLineMapper(new DefaultLineMapper<Lemma>(){
            {
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames(Lemma.fields());
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Lemma>(){
                    {
                        setTargetType(Lemma.class);
                    }
                });
            }
        });
        return reader;*/

        try {


            return new FlatFileItemReaderBuilder<Lemma>().name("lemmasCsv.csv")
                    .resource(new ClassPathResource("lemmasCsv.csv"))
                    .delimited()
                    .delimiter(";")
                    .names(Lemma.fields())
                    .targetType(Lemma.class)
                    .strict(false)
                    .build();

        }catch(Exception e) {
            System.out.println("----------- Exception reader() --------------");
            return null ;
        }
    }
    @Bean
    public JdbcBatchItemWriter<Lemma>writerIntoDB(){
        JdbcBatchItemWriter<Lemma> writer = new JdbcBatchItemWriter<Lemma>();
        writer.setDataSource(dataSource);
        writer.setSql("INSERT INTO LEMMA (rankNumber,lemma,poS,freq) VALUES (:rankNumber,:lemma,:poS,:freq)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Lemma>());
        return writer;
    }
    @Bean
    public Step step(){
        return stepBuilderFactory.get("step").<Lemma,Lemma>chunk(10)
                .reader(readFromCsv()).writer(writerIntoDB()).build();
    }
    @Bean
    public Job job(){
        return jobBuilderFactory.get("job").flow(step()).end().build();
    }
}
