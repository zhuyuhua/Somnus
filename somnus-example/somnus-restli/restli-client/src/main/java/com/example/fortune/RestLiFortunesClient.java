/*
   Copyright (c) 2012 LinkedIn Corp.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.example.fortune;


import java.util.Collections;
import java.util.EnumSet;

import com.linkedin.common.callback.FutureCallback;
import com.linkedin.common.util.None;
import com.linkedin.data.template.DynamicRecordMetadata;
import com.linkedin.r2.transport.common.Client;
import com.linkedin.r2.transport.common.bridge.client.TransportClientAdapter;
import com.linkedin.r2.transport.http.client.HttpClientFactory;
import com.linkedin.restli.client.GetRequestBuilder;
import com.linkedin.restli.client.Request;
import com.linkedin.restli.client.Response;
import com.linkedin.restli.client.ResponseFuture;
import com.linkedin.restli.client.RestClient;
import com.linkedin.restli.common.ResourceMethod;
import com.linkedin.restli.common.ResourceSpec;
import com.linkedin.restli.common.ResourceSpecImpl;

/**
 * @author Doug Young
 */
public class RestLiFortunesClient
{
	/**
     * This stand-alone app demos the client-side Rest.li API.
     * To see the demo, run RestLiFortunesServer, then start the client
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception
    {
        // Create an HttpClient and wrap it in an abstraction layer
        final HttpClientFactory http = new HttpClientFactory();
        final Client r2Client = new TransportClientAdapter(
                http.getClient(Collections.<String, String>emptyMap()));

        // Create a RestClient to talk to localhost:8080
        RestClient restClient = new RestClient(r2Client, "http://localhost:8080/server/");

        // Generate a random ID for a fortune cookie, in the range 0-5
        long fortuneId = (long) (Math.random() * 5);

        // Construct a request for the specified fortune
//        FortunesGetBuilder getBuilder = _fortuneBuilder.get();
        ResourceSpec sourceSpec      =
                new ResourceSpecImpl(EnumSet.allOf(ResourceMethod.class),
                        Collections.<String, DynamicRecordMetadata> emptyMap(),
                        Collections.<String, DynamicRecordMetadata> emptyMap(),
                        Long.class,
                        null,
                        null,
                        Fortune.class,
                        Collections.<String, Class<?>> emptyMap());
        GetRequestBuilder getBuilder = new GetRequestBuilder("fortunes", Fortune.class, sourceSpec, null);
        Request<Fortune> getReq = getBuilder.id(fortuneId).build();

        // Send the request and wait for a response
        final ResponseFuture<Fortune> getFuture = restClient.sendRequest(getReq);
        final Response<Fortune> resp = getFuture.getResponse();

        // Print the response
        System.out.println(resp.getEntity().getFortune());

        // shutdown
        restClient.shutdown(new FutureCallback<None>());
        http.shutdown(new FutureCallback<None>());
    }
//    private static final FortunesBuilders _fortuneBuilder = new FortunesBuilders();
}

