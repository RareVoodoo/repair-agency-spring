package ua.testing.repairagency;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.testing.repairagency.region.currency.CurrencyConversion;
import ua.testing.repairagency.region.transliteration.NameTransliteration;
import ua.testing.repairagency.util.Constants;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RepairAgencyApplicationTests {

	@Autowired
	MockMvc mockMvc;


	@Test
	 public void contextLoads() throws Exception {
		this.mockMvc.perform(get("/registration"))
				.andDo(print())
				.andExpect(status().isOk());
	}


	@Test
	 public void whenCallConvertUsdToUah(){
		CurrencyConversion currencyConversion = new CurrencyConversion();
		int price= 100;
		int expectedResult = 3000;
		assertEquals(expectedResult, currencyConversion.convert(Constants.UA_LOCALE, price));
	}

	@Test
	 public void whenCallTransliterateNameFromCyrillicToLatin(){
		NameTransliteration nameTransliteration = new NameTransliteration();
		String name = "Артур Диванов";
		String expectedString = "Artur Dyvanov";
		assertEquals(expectedString,nameTransliteration.transliterate(Constants.EN_LOCALE, name));
	}


}
