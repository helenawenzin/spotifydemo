package se.nackademin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.nackademin.spotifydemo.SpotifydemoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpotifydemoApplication.class)
@WebAppConfiguration
public class SpotifydemoApplicationTests {

	@Test
	public void contextLoads() {
	}

}
