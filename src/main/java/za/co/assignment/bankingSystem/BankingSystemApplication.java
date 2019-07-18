package za.co.assignment.bankingSystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan("za.co.assignment.bankingSystem.mapper")
public class BankingSystemApplication
 {
	public static void main(String[] args) {
        SpringApplication.run(BankingSystemApplication.class, args);
	}
}
