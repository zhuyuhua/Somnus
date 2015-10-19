
# 如何使用avro #

----------

## 一、准备项目需要的jar包

- avro-1.7.1.jar
- avro-tools-1.7.4.jar
- jackson-core-asl-1.8.8.jar
- jackson-mapper-asl-1.8.8.jar



## 二、定义模式(Schema)

在avro中，它是用Json格式来定义模式的。模式可以由基础类型（null, boolean, int, long, float, double, bytes, and string）和复制类型(record, enum, array, map, union, and fixed)的数据组成。

本文只是定义了一个简单的模式user.avsc：

    {

	 "namespace": "example.avro",
	 "type": "record",
	 "name": "User",
	 "fields": [
	    {"name": "name","type": "string"},
	    {"name": "favorite_number",      "type": [
	          "int",
	          "null"
	       ]
	    },
	    {
	       "name": "favorite_color",
	       "type": [
	          "string",
	          "null"
	       ]
	    }
	 ]
	}


## 三、使用

### 一） 通过生成代码来序列化和反序列化

#### 1、编译模式

Avro可以允许我们根据模式的定义而生成相应的类，一旦我们定义好相关的类，我们程序中就不需要直接使用模式了。可以用avro-tools jar包来生成代码，语法如下：
	
	java -jar $HIVE_HOME/lib/avro-tools-1.7.4.jar   compile schema   <schema file> <destination>

示例：

	java -jar $ARVO_HOME/lib/avro-tools-1.7.4.jar compile schema user.avsc .

#### 2、创建user

		// 1. create three user :user1,user2,user3
		User user1 = new User();
		user1.setName("Alyssa");
		user1.setFavoriteNumber(256);
		// Leave favorite color null

		// Alternate constructor
		User user2 = new User("Ben", 7, "red");

		// Construct via builder
		User user3 = User.newBuilder()
		             .setName("Charlie")
		             .setFavoriteColor("blue")
		             .setFavoriteNumber(null)
		             .build();
		

#### 3、序列化

序列化user1，user2，user3

		//2、Serialize user1, user2 and user3 to disk
        File file = new File("users.avro");
        DatumWriter<User> userDatumWriter = 
                   new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = 
                   new DataFileWriter<User>(userDatumWriter);
        try {
            dataFileWriter.create(user1.getSchema(), new File("users.avro"));
            dataFileWriter.append(user1);
            dataFileWriter.append(user2);
            dataFileWriter.append(user3);
            dataFileWriter.close();
        } catch (IOException e) {
        }
	

此时在磁盘可以看到通过user.avro序列化后的文件


#### 4、 反序列化
	
		//3、Deserialize Users from dist
        DatumReader<User> userDatumReader = 
                             new SpecificDatumReader<User>(User.class);
        DataFileReader<User> dataFileReader = null;
        try {
            dataFileReader = new DataFileReader<User>(file, userDatumReader);
        } catch (IOException e) {
        }
        User user = null;
        try {
            while (dataFileReader.hasNext()) {
                // Reuse user object by passing it to next(). This saves
                // us from allocating and garbage collecting many objects for
                // files with many items.
                user = dataFileReader.next(user);
                System.out.println(user);
            }
        } catch (IOException e) {
        }

#### 5、运行
略

### 二） 不通过生成代码来序列化和反序列化 

#### 1、创建用户

    // 1、使用schema parser
		Schema schema = new Schema.Parser().parse(new File(GlobalConfigConstant.CLASS_PATH + "avro/user.avsc"));

		// 2、Using this schema, let's create some users.

		GenericRecord user1 = new GenericData.Record(schema);
		user1.put("name", "Alyssa");
		user1.put("favorite_number", 256);
		// Leave favorite color null

		GenericRecord user2 = new GenericData.Record(schema);
		user2.put("name", "Ben");
		user2.put("favorite_number", 7);
		user2.put("favorite_color", "red");

		// 3、 Serialize user1 and user2 to disk
		File file = new File("users.avro");
		DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
		DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
		dataFileWriter.create(schema, file);
		dataFileWriter.append(user1);
		dataFileWriter.append(user2);
		dataFileWriter.close();
		
		// 4、Deserialize users from disk
		DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
		DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
		GenericRecord user = null;
		while (dataFileReader.hasNext()) {
		// Reuse user object by passing it to next(). This saves us from
		// allocating and garbage collecting many objects for files with
		// many items.
		user = dataFileReader.next(user);
		System.out.println(user);
		}
	}