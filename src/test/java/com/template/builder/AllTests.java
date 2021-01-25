package com.template.builder;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_19;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.template.builder.controller.CouchDBRestControllerTests;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.MysqldConfig;

//import com.staffportal.amanda.BusinessProcessManagmentControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	//Controller test cases
	CouchDBRestControllerTests.class,
	
	
	
	//Service test cases
	
	
	//Strategy test case

	
	
	//Utility test case
	//MultiCityEnabledTest.class,
	
})

public class AllTests {
    static Logger logger = LoggerFactory.getLogger(AllTests.class);
    private static String PROFILE = System.getProperty("profile");

    private static EmbeddedMysql embeddedMysql;

    /*
    For running test (embedded MySQL)
    mvn clean test -Dprofile=test -Dtest=AllTests -Ptest
    
    For running Dev profile (local MySQL)
    mvn clean test -Dprofile=dev -Dtest=AllTests -Pdev
    
    For running prod profile (embedded MySQL)
    mvn clean test -Dprofile=prod -Dtest=AllTests -Pprod 
	*/
    
    @BeforeClass
    public static void _setupBeforeClass() {

        if (PROFILE.equals("test")) {
            MysqldConfig config = aMysqldConfig(v5_7_19).withCharset(UTF8).withPort(3307).withUser("test", "test")
                    .withTimeout(2, TimeUnit.MINUTES)
                    // .withServerVariable("max_connect_errors", 666)
                    .withServerVariable("bind-address", "localhost").build();

            EmbeddedMysql mysqld = anEmbeddedMysql(config)
                    .addSchema("aschema", ScriptResolver.classPathScript("db/schema.sql")).start();

            logger.info("Embedded MySQL Username: " + mysqld.getConfig().getUsername() + " Password: "
                    + mysqld.getConfig().getPassword() + " Port: " + mysqld.getConfig().getPort() + " Temp Dir: "
                    + mysqld.getConfig().getTempDir() + " TimeZone: " + mysqld.getConfig().getTimeZone() + " Version: "
                    + mysqld.getConfig().getVersion());
        }
    }

    @AfterClass
    public static void _tearDownAfterClass() {

        if (null != embeddedMysql && PROFILE.equals("test")) {
            embeddedMysql.stop();
        }
    }

}
