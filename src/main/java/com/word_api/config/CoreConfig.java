package com.word_api.config;

import com.word_api.domains.Lemma;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableBatchProcessing
public class CoreConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    Job job(JobBuilderFactory factory,
            StepBuilderFactory stepBuilderFactory,
            ItemReader<Lemma> itemReader,
            ItemProcessor<Lemma, Lemma> itemProcessor,
            ItemWriter<Lemma> itemWriter) {

        Step step = stepBuilderFactory.get("Lemma-file-load")
                .<Lemma, Lemma>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();


        return jobBuilderFactory.get("Lemma-load")
                .incrementer(new RunIdIncrementer())
                .start(step) //use flow if having multiple steps as bellow
                //.flow(step)
                //.next(step)
                .build();

    }

    @Bean
 //   public FlatFileItemReader<Lemma> itemReader(@Value("${input}") Resource resource
    public FlatFileItemReader<Lemma> itemReader() {
        FlatFileItemReader<Lemma> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new PathResource("C:/Users/39334/IdeaProjects/WordsApi/src/main/resources/lemmasCsv.csv"));
        flatFileItemReader.setName("Csv-Reader");
        flatFileItemReader.setLinesToSkip(0);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }
    @Bean
    LineMapper<Lemma> lineMapper() {

        DefaultLineMapper<Lemma> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(Lemma.fields());

        BeanWrapperFieldSetMapper<Lemma> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Lemma.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

}


/*    @Bean
    FlatFileItemReader<Lemma> readFromCsv(){
 *//*       FlatFileItemReader<Lemma> reader = new FlatFileItemReader<Lemma>();
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
        return reader;*//*

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
    }*/

