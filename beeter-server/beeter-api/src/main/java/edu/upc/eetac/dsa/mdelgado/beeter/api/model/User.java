package edu.upc.eetac.dsa.mdelgado.beeter.api.model;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.linking.InjectLink.Style;

import edu.upc.eetac.dsa.mdelgado.beeter.api.MediaType;
import edu.upc.eetac.dsa.mdelgado.beeter.api.StingResource;

public class User {
	
//	@InjectLinks({
//		@InjectLink(resource = StingResource.class, style = Style.ABSOLUTE, rel = "stings", title = "Latest stings", type = MediaType.BEETER_API_STING_COLLECTION),
//		@InjectLink(resource = StingResource.class, style = Style.ABSOLUTE, rel = "self edit", title = "Sting", type = MediaType.BEETER_API_STING, method = "getSting", bindings = @Binding(name = "stingid", value = "${instance.id}")) })

	private String Username;
	private String Name;
	private String Email;

	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
}
