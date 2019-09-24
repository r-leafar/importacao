package importacao.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import importacao.controller.uniplus.ProdutoControllerUniplus;

public class ConnectionFactory {

	private static Properties CONFIGINFO;
	
	
	public ConnectionFactory() {
		this.CONFIGINFO =initProperties();
	}
	public static String getConfiginfo(String key) {
		return CONFIGINFO.getProperty(key);
	}
	private Properties initProperties() {
		try  {
			InputStream input = ProdutoControllerUniplus.class.getClassLoader().getResourceAsStream("config.properties");
            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return null;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
//            System.out.println(prop.getProperty("user"));
//            System.out.println(prop.getProperty("pass"));
//            System.out.println(prop.getProperty("db"));
//            System.out.println(prop.getProperty("ip"));
//            System.out.println(prop.getProperty("porta"));
            return prop;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
		return null;
				
	}
}
