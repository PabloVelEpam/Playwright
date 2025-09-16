package testData.dataproviders;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvDataProvider
{
	@DataProvider(name = "csvLoginUserDataProvider")
	public static Object[][] provideData() {
		String csvFilePath = "src/main/java/testData/csvFiles/loginUsers.csv";
		List<Object[]> data = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				String username = values.length > 0 ? values[0] : "default@User";
				String password = values.length > 1 ? values[1] : "defaultPassword";
				String expectedResult = values.length > 2 ? values[2] : "false";
				data.add(new Object[]{username, password, expectedResult});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data.toArray(new Object[0][0]);
	}

}
