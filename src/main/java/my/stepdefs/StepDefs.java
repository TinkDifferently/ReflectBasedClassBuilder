package my.stepdefs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cucumber.api.java.bg.И;

public class StepDefs {

    public StepDefs(){}

    @И("{string} выходит на поле")
    public void team(String country){
        Logger logger=LoggerFactory.getLogger(StepDefs.class);
        logger.warn("УИИИИ "+country);
    }
}
