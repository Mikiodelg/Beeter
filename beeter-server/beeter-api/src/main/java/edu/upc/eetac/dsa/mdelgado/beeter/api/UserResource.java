package edu.upc.eetac.dsa.mdelgado.beeter.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import edu.upc.eetac.dsa.mdelgado.beeter.api.model.Sting;
import edu.upc.eetac.dsa.mdelgado.beeter.api.model.StingCollection;
import edu.upc.eetac.dsa.mdelgado.beeter.api.model.User;

@Path("/users/")
public class UserResource {

	private DataSource ds = DataSourceSPA.getInstance().getDataSource();

	@Context
	private SecurityContext security;

	@GET
	@Path("/{user}")
	@Produces(MediaType.BEETER_API_STING_COLLECTION)
	public User getUser(@PathParam("user") String user) {
		User usuario = new User();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(buildSearchUsersQuery());
			stmt.setString(1, user);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				usuario.setUsername(rs.getString("username"));
				usuario.setName(rs.getString("name"));
				usuario.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
		return usuario;
	}

	private String buildSearchUsersQuery() {

		return "select * from users where username = ?";
		
	}
		
		  @GET
		  
		  @Path("/{user}/stings")
		  
		  @Produces(MediaType.BEETER_API_STING_COLLECTION)
		  
		  public StingCollection UserCollection(@PathParam("user") String user,
		  
		  @Context Request request){
		  
		  StingCollection stings = new StingCollection();
		  
		  Connection conn = null; try { conn = ds.getConnection(); } catch
		  (SQLException e) { throw new
		  ServerErrorException("Could not connect to the database",
		  Response.Status.SERVICE_UNAVAILABLE); }
		  
		  PreparedStatement stmt = null; 
		  
		  //try { 
			//  stmt =conn.prepareStatement(buildSearchUserStingsQuery()); stmt.setString(1, user);
		  try {
			  long after;
			  long before;
			  int length=5;
	//			boolean updateFromLast = after > 0;
				stmt = conn.prepareStatement(buildSearchUserStingsQuery());
	//			 (updateFromLast) {
	//				stmt.setTimestamp(1, new Timestamp(after));
	//			} else {
	//				if (before > 0)
//						stmt.setTimestamp(1, new Timestamp(before));
				//	else
						stmt.setTimestamp(1, null);
					length = (length <= 0) ? 20 : length;
					stmt.setInt(2, length);
		//		}
		  
		  ResultSet rs = stmt.executeQuery();
		 
		  
		  boolean first = true; long oldestTimestamp = 0;
		  
		  while (rs.next()) { 
			  Sting sting = new Sting();
			  sting.setId(rs.getString("stingid"));
			  sting.setUsername(rs.getString("username"));
			  sting.setSubject(rs.getString("subject"));
			  sting.setContent(rs.getString("content")); oldestTimestamp =
			  rs.getTimestamp("last_modified").getTime();
			  sting.setLastModified(oldestTimestamp); if (first) { first = false;
			  stings.setNewestTimestamp(sting.getLastModified());
			  }
		  stings.addSting(sting);
		  } stings.setOldestTimestamp(oldestTimestamp);
		  
		  } catch (SQLException e) { throw new
		  ServerErrorException(e.getMessage(),
		  Response.Status.INTERNAL_SERVER_ERROR); 
		  } 
		  finally { 
			  try { 
				  if (stmt != null) stmt.close(); conn.close(); 
				  } catch (SQLException e) { } 
			  }
		  
		  return stings; 
		  
		  }	 
	


private String buildSearchUserStingsQuery() {

	return "select * from stings where username = ?";
	
	}

}

