package profile;

import java.util.ArrayList;
import java.util.List;

import com.sforce.soap.metadata.Metadata;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.metadata.Profile;
import com.sforce.soap.metadata.ProfileLoginIpRange;
import com.sforce.soap.metadata.ReadResult;
import com.sforce.soap.metadata.UpsertResult;
import com.sforce.ws.ConnectionException;

public class UpdateIPRangeInProfile {
	public static void main(String[] args) throws ConnectionException {

		String[][] ranges = { { "9.0.0.0", "9.255.255.255" }, { "10.0.0.0", "10.255.255.255" } };
		String profileName = "TestIPRangeUpdates";
		MetadataConnection metadataConnection = MetadataLoginUtility.login();
		ProfileLoginIpRange[] existingIPRanges = new ProfileLoginIpRange[40];
		
		try {
	        ReadResult readResult = metadataConnection
	                .readMetadata("Profile", new String[] {
	                        "TestIPRangeUpdates" });
	        Metadata[] metadataRecords = readResult.getRecords();
	        System.out.println("Number of component info returned: "
	                + metadataRecords.length);
	        for (Metadata eachRecord : metadataRecords) {
	            if (eachRecord != null) {
	                Profile obj = (Profile) eachRecord;
	                System.out.println("Custom object full name: "
	                        + obj.getFullName() + obj.getLoginIpRanges());
	                existingIPRanges = obj.getLoginIpRanges();
	                
	            } else {
	                System.out.println("Empty metadata.");
	            }
	        }
	    } catch (ConnectionException ce) {
	        ce.printStackTrace();
	    }
		
		try {
			List<ProfileLoginIpRange> rangeList = new ArrayList<ProfileLoginIpRange>();
			

			for (int i = 0; i < ranges.length; i++) {
				ProfileLoginIpRange newRange = new ProfileLoginIpRange();
				newRange.setStartAddress(ranges[i][0]);
				newRange.setEndAddress(ranges[i][1]);
				rangeList.add(newRange);
			}
			for(ProfileLoginIpRange eachIPRange : existingIPRanges) {
				rangeList.add(eachIPRange);
			}
			ProfileLoginIpRange[] loginIpRanges = new ProfileLoginIpRange[rangeList.size()];
			loginIpRanges = (rangeList.toArray(loginIpRanges));
			
			Profile profile = new Profile();
			profile.setLoginIpRanges(loginIpRanges);
			profile.setFullName(profileName);
			
			UpsertResult[] saveResults = metadataConnection
					.upsertMetadata(new Metadata[] { profile });


			for (com.sforce.soap.metadata.UpsertResult result : saveResults) {
				if (result.isSuccess()) {
					System.out.println("Updated profile: " + result.getFullName());
				} else {
					System.out.println("Errors encountered while updating profile " + result.getFullName());
					for (com.sforce.soap.metadata.Error eachError : result.getErrors()) {
						System.out.println("Error message: " + eachError.getMessage());
						System.out.println("Status code: " + eachError.getStatusCode());
					}
				}

			}
		} catch (ConnectionException ce) {
			ce.printStackTrace();
		}
	}
}