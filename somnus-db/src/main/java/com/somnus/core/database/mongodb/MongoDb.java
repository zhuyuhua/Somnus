package com.somnus.core.database.mongodb;

import java.net.UnknownHostException;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDb {
	// 1.建立一个Mongo的数据库连接对象
	static Mongo connection = null;
	// 2.创建相关数据库的连接
	static DB db = null;

	public MongoDb(String dbName) throws UnknownHostException, MongoException {
		connection = new Mongo("127.0.0.1:27017");
		db = connection.getDB(dbName);
	}

	public static void main(String[] args) throws UnknownHostException,
			MongoException {
		// 实例化
		MongoDb mongoDb = new MongoDb("foobar");
		/**
		 * 1.创建一个名字叫javadb的数据库
		 */
		// mongoDb.createCollection("javadb3");
		/**
		 * 2.为集合javadb添加一条数据
		 */
		// DBObject dbs = new BasicDBObject();
		// dbs.put("name", "uspcat.com");
		// dbs.put("age", 2);
		// List<String> books = new ArrayList<String>();
		// books.add("EXTJS");
		// books.add("MONGODB");
		// dbs.put("books", books);
		// mongoDb.insert(dbs, "javadb");
		/**
		 * 3.批量插入数据
		 */
		// List<DBObject> dbObjects = new ArrayList<DBObject>();
		// DBObject jim = new BasicDBObject("name", "jim");
		// DBObject lisi = new BasicDBObject("name", "lisi");
		// dbObjects.add(jim);
		// dbObjects.add(lisi);
		// mongoDb.insertBatch(dbObjects, "javadb");
		/**
		 * 4.根据ID删除数据
		 */
		// mongoDb.deleteById("52791521420a7936754521ab", "javadb");
		/**
		 * 5.根据条件删除数据
		 */
		// DBObject lisi = new BasicDBObject();
		// lisi.put("name", "jim");
		// int count = mongoDb.deleteByDbs(lisi, "javadb");
		// System.out.println("删除数据的条数是: " + count);
		/**
		 * 6.更新操作,为集合增加email属性
		 */
		// DBObject update = new BasicDBObject();
		// update.put("$set", new BasicDBObject("eamil", "uspcat@126.com"));
		// mongoDb.update(new BasicDBObject(), update, false, true, "javadb");
		/**
		 * 7.查询出persons集合中的name和age
		 */
		// DBObject keys = new BasicDBObject();
		// keys.put("_id", false);
		// keys.put("name", true);
		// keys.put("age", true);
		// DBCursor cursor = mongoDb.find(null, keys, "persons");
		// while (cursor.hasNext()) {
		// DBObject object = cursor.next();
		// System.out.println("name:" + object.get("name") + ",age:"
		// + object.get("age"));
		// }
		/**
		 * 7.查询出年龄大于26岁并且英语成绩小于80分
		 */
		// DBObject ref = new BasicDBObject();
		// ref.put("age", new BasicDBObject("$gte", 26));
		// ref.put("e", new BasicDBObject("$lte", 80));
		// DBCursor cursor = mongoDb.find(ref, null, "persons");
		// while (cursor.hasNext()) {
		// DBObject object = cursor.next();
		// System.out.print(object.get("name") + "-->");
		// System.out.print(object.get("age") + "-->");
		// System.out.println(object.get("e"));
		// }
		/**
		 * 8.分页例子
		 */
		DBCursor cursor = mongoDb.find(null, null, 0, 3, "persons");
		while (cursor.hasNext()) {
			DBObject object = cursor.next();
			System.out.print(object.get("name") + "-->");
			System.out.print(object.get("age") + "-->");
			System.out.println(object.get("e"));
		}
		// 关闭连接对象
		connection.close();
	}

	/**
	 * 穿件一个数据库集合
	 * 
	 * @param collName
	 *            集合名称
	 * @param db
	 *            数据库实例
	 */
	public void createCollection(String collName) {
		DBObject dbs = new BasicDBObject();
		db.createCollection(collName, dbs);
	}

	/**
	 * 为相应的集合添加数据
	 * 
	 * @param dbs
	 * @param collName
	 */
	public void insert(DBObject dbs, String collName) {
		// 1.得到集合
		DBCollection coll = db.getCollection(collName);
		// 2.插入操作
		coll.insert(dbs);
	}

	/**
	 * 为集合批量插入数据
	 * 
	 * @param dbses
	 * @param collName
	 */
	public void insertBatch(List<DBObject> dbses, String collName) {
		// 1.得到集合
		DBCollection coll = db.getCollection(collName);
		// 2.插入操作
		coll.insert(dbses);
	}

	/**
	 * 根据id删除数据
	 * 
	 * @param id
	 * @param collName
	 * @return 返回影响的数据条数
	 */
	public int deleteById(String id, String collName) {
		// 1.得到集合
		DBCollection coll = db.getCollection(collName);
		DBObject dbs = new BasicDBObject("_id", new ObjectId(id));
		int count = coll.remove(dbs).getN();
		return count;
	}

	/**
	 * 根据条件删除数据
	 * 
	 * @param id
	 * @param collName
	 * @return 返回影响的数据条数
	 */
	public int deleteByDbs(DBObject dbs, String collName) {
		// 1.得到集合
		DBCollection coll = db.getCollection(collName);
		int count = coll.remove(dbs).getN();
		return count;
	}

	/**
	 * 更新数据
	 * 
	 * @param find
	 *            查询器
	 * @param update
	 *            更新器
	 * @param upsert
	 *            更新或插入
	 * @param multi
	 *            是否批量更新
	 * @param collName
	 *            集合名称
	 * @return 返回影响的数据条数
	 */
	public int update(DBObject find, DBObject update, boolean upsert,
			boolean multi, String collName) {
		// 1.得到集合
		DBCollection coll = db.getCollection(collName);
		int count = coll.update(find, update, upsert, multi).getN();
		return count;
	}

	/**
	 * 查询器(分页)
	 * 
	 * @param ref
	 * @param keys
	 * @param start
	 * @param limit
	 * @return
	 */
	public DBCursor find(DBObject ref, DBObject keys, int start, int limit,
			String collName) {
		DBCursor cur = find(ref, keys, collName);
		return cur.limit(limit).skip(start);
	}

	/**
	 * 查询器(不分页)
	 * 
	 * @param ref
	 * @param keys
	 * @param start
	 * @param limit
	 * @param collName
	 * @return
	 */
	public DBCursor find(DBObject ref, DBObject keys, String collName) {
		// 1.得到集合
		DBCollection coll = db.getCollection(collName);
		DBCursor cur = coll.find(ref, keys);
		return cur;
	}
}
