package runner;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import steps.CucumberCepTest;

/**
 * @author Natália Bruno Rabelo
 * Classe responsável por configurar e executar classes de steps para testes automatizados em Cucumber
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CucumberCepTest.class)
public class TestRunner {
}