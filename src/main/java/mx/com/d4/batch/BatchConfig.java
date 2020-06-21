package mx.com.d4.batch;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import mx.com.d4.models.Client;



@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;

	
	private final Log log = LogFactory.getLog(this.getClass());
	
//	@Value("classPath:/tmpuploads/base.csv")
//	private Resource inputResource;
	

	/**
	 * JobBuilderFactory(JobRepository jobRepository) Convenient factory for a
	 * JobBuilder which sets the JobRepository automatically
	 */
	@Bean
	public Job readCSVFileJob() {
		return jobBuilderFactory.get("readCSVFileJob").incrementer(new RunIdIncrementer()).start(step()).build();
	}

	/**
	 * StepBuilder which sets the JobRepository and PlatformTransactionManager
	 * automatically
	 */

	@Bean
	public Step step() {
		return stepBuilderFactory.get("step").<Client, Client>chunk(8).reader(reader(null)).processor(processor())
				.writer(writer()).build();
	}

	/**
	 * Prints the Logs in the console.
	 * 
	 * @return
	 */

	@Bean
	public ItemProcessor<Client, Client> processor() {
		return new DBLogProcessor();
	}

	/**
	 * FlatFileItemReader<T> Restartable ItemReader that reads lines from input
	 * setResource(Resource).
	 * 
	 * @return
	 */

	@Bean
	@Scope(value = "step", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public FlatFileItemReader<Client> reader(@Value("#{jobParameters[fullPathFileName]}") String pathToFile) {
		FlatFileItemReader<Client> itemReader = new FlatFileItemReader<Client>();
		log.info("LE PATH: " + pathToFile);
			
		itemReader.setLineMapper(lineMapper());
		itemReader.setLinesToSkip(1);
		//itemReader.setResource(inputResource);		
		itemReader.setResource(new FileSystemResource(pathToFile));
		return itemReader;
	}

	/**
	 * The data source object is defined and created depending upon the type of
	 * database we use. In this example we are using H2 databse so we
	 * 'schema-h2.sql' for example if we are using mysql database we will be using
	 * 'schema-mysql.sql'
	 *
	 * schema-drop-h2.sql will drop the batch related jobs
	 *
	 * schema-h2.sql is responsible for creating a schema related to the batch job
	 * instances in h2 database. BATCH_STEP_EXECUTION_CONTEXT
	 * BATCH_JOB_EXECUTION_CONTEXT BATCH_STEP_EXECUTION BATCH_JOB_EXECUTION_PARAMS
	 * BATCH_JOB_EXECUTION BATCH_JOB_INSTANCE
	 *
	 * Client.sql will create HOTLE table in h2 database
	 *
	 */

//	@Bean
//	public DataSource dataSource() {
//
//		EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
//
//		return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
//				.addScript("classpath:org/springframework/batch/core/schema-h2.sql").addScript("classpath:Client.sql")
//				.setType(EmbeddedDatabaseType.H2).build();
//	}

	/**
	 * The itemWriter object will set JDBC connection and sql statement is prepared
	 * for the batch action we want to perform in the database. A convenient
	 * implementation for providing BeanPropertySqlParameterSource when the item has
	 * JavaBean properties that correspond to names used for parameters in the SQL
	 * statement.
	 *
	 */

	@Bean
	public JdbcBatchItemWriter<Client> writer() {
		

		JdbcBatchItemWriter<Client> itemWriter = new JdbcBatchItemWriter<Client>();

		itemWriter.setDataSource(dataSource);
		itemWriter.setSql(
				"INSERT INTO client(id_client, hdr_chargue, contact, status, name, tag, mail, audith_user , audith_time)"
						+ " VALUES(:id, :chargeHdr.id, :contact, :status, :name, :tag, :mail, :audithUsr, :audithTime)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Client>());
		return itemWriter;
	}

	/**
	 * the lineMapper for mapping lines (strings) to domain objects typically used
	 * to map lines read from a file to domain objects on a per line basis.
	 * lineTokenizer to split string obtained typically from a file into tokens. In
	 * our example we are using DelimitedLineTokenizer that is because we are using
	 * csv file. fieldSetMapper to map data obtained from a FieldSet into an object.
	 *
	 */

	@Bean
	public LineMapper<Client> lineMapper() {
		DefaultLineMapper<Client> lineMapper = new DefaultLineMapper<Client>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		BeanWrapperFieldSetMapper<Client> fieldSetMapper = new BeanWrapperFieldSetMapper<Client>();

		lineTokenizer.setNames(new String[] { "id", "chargeHdr.id", "contact", "status", "name", "tag",	"mail" , "audithUsr", "audithTime"});
		lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 });
		fieldSetMapper.setTargetType(Client.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		return lineMapper;
	}

}