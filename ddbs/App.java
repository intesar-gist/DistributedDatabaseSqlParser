package ddbs;

import ddbs.fjdbc.FJDBCConstants;
import ddbs.fjdbc.FedConnection;
import ddbs.fjdbc.FedException;
import ddbs.utilities.Logger;

import java.io.IOException;

/**
 * Created by Intesar
 */
public class App implements FJDBCConstants
{
    public static void main(String[] args) {

        String usernameTest = "XXXXXXX";
        String passwordTest = "XXXXXXX";

        String usernameValidation = "XXXXXXX";
        String passwordValidation = "XXXXXXX";

		/*
		 * Test schema
		 */
        try {


            // Connect to database
            FedConnection fedConnection = new FedConnection();
            fedConnection.startConnection(MTSTHELENS_DB3);

            FedTestEnvironment fedTestEvironment = new FedTestEnvironment(fedConnection);


//            fedTestEvironment.run("./ddbs/Test/CREPARTABS.SQL", false);

//            fedTestEvironment.run("./ddbs/Test/DRPTABS.SQL", false);
//            fedTestEvironment.run("./ddbs/Test/INSERTAIRPORTS.SQL", false);
//            fedTestEvironment.run("./ddbs/Test/INSERTAIRLINES.SQL", false);
//            fedTestEvironment.run("./ddbs/Test/INSERTPASSENGERS.SQL", false);
//            fedTestEvironment.run("./ddbs/Test/INSERTFLIGHTS.SQL", false);
//            fedTestEvironment.run("./ddbs/Test/INSERTBOOKINGS.SQL", false);
//            fedTestEvironment.run("./ddbs/Test/PARSELCNTSTAR.SQL", true);
//            fedTestEvironment.run("./ddbs/Test/PARSELS1T.SQL", true);
//            fedTestEvironment.run("./ddbs/Test/PARSELS1OR.SQL", true);
//            fedTestEvironment.run("./ddbs/Test/PARSELSJOIN1.SQL", true);
//            fedTestEvironment.run("./ddbs/Test/PARSELS1TGP.SQL", true);
////            fedTestEvironment.run("Test/PARSELS1TWGP.SQL", true);   //OPTIONAL
////            fedTestEvironment.run("Test/PARSELS1TGHAV.SQL", true);  //OPTIONAL
//            fedTestEvironment.run("./ddbs/Test/PARUPDS.SQL", true);
//            fedTestEvironment.run("./ddbs/Test/PARINSERTS.SQL", true);
//            fedTestEvironment.run("./ddbs/Test/PARDELS.SQL", true);
//            fedTestEvironment.run("./ddbs/Test/PARSELCNTSTAR.SQL", true);

            System.out.println("All Done Successfully !! ");
        } catch (FedException fedException) {
            fedException.printStackTrace();

        }


    }
}

