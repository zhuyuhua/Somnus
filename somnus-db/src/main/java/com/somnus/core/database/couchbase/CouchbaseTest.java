package com.somnus.core.database.couchbase;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.internal.OperationFuture;

import com.couchbase.client.CouchbaseClient;

public class CouchbaseTest {

	public static final String KEY = "testkey2";

	// ����ʱ��(��λ���� 0 ����)
	public static final int EXP_TIME = 0;

	public static final String VALUE = "{\"myname\":\"zhuyuhua\","
			+ "\"updateed\":\"2014-05-27 18:00:00\","
			+ "\"description\":\"Just a simple test\","
			+ "\"myblog\":\"http://baidu.com\"}";

	public static void main(String[] args) {

		List<URI> uris = new LinkedList<URI>();
		uris.add(URI.create("http://127.0.0.1:8091/pools"));
		CouchbaseClient client = null;
		try {
			client = new CouchbaseClient(uris, "default", "");
		} catch (Exception e) {
			System.exit(1);
		}

		OperationFuture<Boolean> setOp = client.set(KEY, EXP_TIME, VALUE);

		try {
			// //����Ƿ����óɹ�
			if (setOp.get().booleanValue()) {
				System.out.println("Set successed");
			} else {
				System.err.println("Set failed:"
						+ setOp.getStatus().getMessage());
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		client.shutdown(3, TimeUnit.SECONDS);
		System.exit(0);
	}

}
