import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.util.JsonContentTypeResponseFilter;

@RunWith(JUnit4.class)
public class RestEasyClientTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException,
			IOException {
//		testTranslatable();

	}

	private void testTranslatable() throws MalformedURLException, IOException,
			JsonParseException, JsonMappingException {
		
		System.out.println(">>>>>>>> testTranslatable");
		
		String caminho = "http://localhost:8080/translatable/rest/coest";
		URL endereco;

		endereco = new URL(caminho);

		Client client = ClientBuilder.newClient();
		client.register(JsonContentTypeResponseFilter.class);

		WebTarget target;
		target = client.target(URI.create(endereco.toExternalForm()));
		Builder builder = target.request();
		Response response = builder.get();
		
		System.out.println(">>>>>>>> testTranslatable: "+response.getStatus());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatus());
		
		String responseBody = response.readEntity(String.class);

		TraceLinkList tracelinkList = new ObjectMapper().readValue(
				responseBody, TraceLinkList.class);
		this.testIndexable(tracelinkList);
	}

	private void testIndexable(TraceLinkList tracelinkList)
			throws MalformedURLException, IOException, JsonParseException,
			JsonMappingException {
		System.out.println(">>>>>>>> testIndexable: ");
		String caminho = "http://localhost:8080/storable/rest/index";
		URL endereco;
		endereco = new URL(caminho);
		Client client = ClientBuilder.newClient();
		client.register(JsonContentTypeResponseFilter.class);

		WebTarget target;
		target = client.target(URI.create(endereco.toExternalForm()));
		Builder builder = target.request();
		Entity<TraceLinkList> entity = Entity.json(tracelinkList);
		Response response = builder.post(entity);
		System.out.println(">>>>>>>> testIndexable: "+response.getStatus());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatus());
	}
}
