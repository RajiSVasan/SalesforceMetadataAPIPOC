package profile;

import com.sforce.soap.metadata.CustomField;
import com.sforce.soap.metadata.CustomObject;
import com.sforce.soap.metadata.DeploymentStatus;
import com.sforce.soap.metadata.FieldType;
import com.sforce.soap.metadata.Metadata;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.metadata.SharingModel;
import com.sforce.ws.ConnectionException;

public class CreateCustomObject {
	  public static void main(String[] args) throws ConnectionException {
		  
		   
		    MetadataConnection metadataConnection = MetadataLoginUtility.login();
		   // AsyncResult[] ars = metadataConnection.create(new CustomObject[] { co });
		    
		    try {
		        CustomObject co = new CustomObject();
		        String name = "MyCustomObject2";
		        co.setFullName(name + "__c");
		        co.setDeploymentStatus(DeploymentStatus.Deployed);
		        co.setDescription("Created by the Metadata API");
		        co.setEnableActivities(true);
		        co.setLabel(name + " Object");
		        co.setPluralLabel(co.getLabel() + "s");
		        co.setSharingModel(SharingModel.ReadWrite);

		        CustomField nf = new CustomField();
		        nf.setType(FieldType.Text);
		        nf.setLabel(co.getFullName() + " Name");
		        co.setNameField(nf);

		        com.sforce.soap.metadata.SaveResult[] results = metadataConnection.createMetadata(new Metadata[] { co });
		                //.createMetadata(new Metadata[] { co });

		        for (com.sforce.soap.metadata.SaveResult r : results) {
		            if (r.isSuccess()) {
		                System.out.println("Created component: " + r.getFullName());
		            } else {
		                System.out
		                        .println("Errors were encountered while creating "
		                                + r.getFullName());
		                for (com.sforce.soap.metadata.Error e : r.getErrors()) {
		                    System.out.println("Error message: " + e.getMessage());
		                    System.out.println("Status code: " + e.getStatusCode());
		                }
		            }
		        }
		    } catch (ConnectionException ce) {
		        ce.printStackTrace();
		    }

	  }

}
