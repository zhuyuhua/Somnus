package com.somnus.core.database.couchbase.factory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.couchbase.client.CouchbaseClient;
import com.somnus.utils.conf.SystemConfigUtil;

public class CouchBaseFactory {

	public static final String COUCHBASE_HOST = ".couchbase.host";
	public static final String COUCHBASE_BUCKET = ".couchbase.bucket";
	public static final String COUCHBASE_PWD = ".couchbase.passwd";

	public static final String URL_PATTERN = "http://%s/pools";

	private static Map<String, CouchbaseClient> couchbaseClientMap = new HashMap<String, CouchbaseClient>();

	private static CouchbaseClient initCouchbaseClient(String couchbaseName) {

		CouchbaseClient client = null;
		String serverAddress = SystemConfigUtil.getProperty(couchbaseName + COUCHBASE_HOST);
		String bucket = SystemConfigUtil.getProperty(couchbaseName + COUCHBASE_BUCKET);
		String pwd = SystemConfigUtil.getProperty(couchbaseName + COUCHBASE_PWD);

		String[] serverNames = serverAddress.split(",");
		ArrayList<URI> serverList = new ArrayList<URI>();
		for (String serverName : serverNames) {
			URI base = null;
			base = URI.create(String.format("http://%s/pools", serverName));
			serverList.add(base);
		}
		try {
			client = new CouchbaseClient(serverList, bucket, pwd);
		} catch (IOException e) {
			System.err.print(String.format("get CouchbaseClient Exception,host[%s],bucket[%s],pwd[%s].", serverAddress,
					bucket, pwd));
		}
		couchbaseClientMap.put(couchbaseName, client);
		return client;
	}

	public synchronized static CouchbaseClient getInstance(String couchbaseName) {
		if (couchbaseClientMap.containsKey(couchbaseName)) {
			return couchbaseClientMap.get(couchbaseName);
		}
		return initCouchbaseClient(couchbaseName);
	}

	public static void shutdown(String couchbaseName) {
		couchbaseClientMap.get(couchbaseName).shutdown(5, TimeUnit.SECONDS);
	}

	public static void main(String[] args)
			throws URISyntaxException, IOException, InterruptedException, ExecutionException {
		CouchbaseClient cli = CouchBaseFactory.getInstance("zhuyuhua");

		System.out.println(cli.get("key1"));
		System.out.println(cli.get("key2"));
		System.out.println(cli.get("key3"));
		System.out.println(cli.get("testkey2"));
	}

}
