package com.somnus.core.db.mongodb;

import java.util.List;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class DataBase {

	public static void main(String[] args) {
		try {
			Mongo mongo = new Mongo("127.0.0.1:27017");

			List<String> dbName = mongo.getDatabaseNames();
			for (String name : dbName) {
				System.out.println(name);
			}

			DB db = mongo.getDB("foobar");
			Set<String> collNames = db.getCollectionNames();
			for (String col : collNames) {
				System.out.println("�������ƣ�" + col);
			}

			DBCollection users = db.getCollection("persons");
			DBCursor cursor = users.find();

			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				System.out.println("������" + object.get("name"));
			}

			System.out.println(cursor.count());

			System.out.println(JSON.serialize(cursor));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
