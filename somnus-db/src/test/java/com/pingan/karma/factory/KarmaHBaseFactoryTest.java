package com.pingan.karma.factory;

import static org.junit.Assert.*;

import org.apache.hadoop.hbase.client.HTableInterface;
import org.junit.Test;

public class KarmaHBaseFactoryTest {

	private HBaseFactory karmaHBaseFactory;
	private HTableInterface hTable;
	
	@Test
	public void testKarmaHBaseFactory() {
		karmaHBaseFactory=new KarmaHBaseFactory();
	}

	@Test
	public void testGetHTable() {
		karmaHBaseFactory=new KarmaHBaseFactory();
	    hTable = karmaHBaseFactory.getHTable("testa");
		
	}

//	@Test
//	public void testCloseHTable() {
//		karmaHBaseFactory=new KarmaHBaseFactory();
//		karmaHBaseFactory.closeHTable(hTable);
//	}
//
//	@Test
//	public void testDeleteTable() {
//		karmaHBaseFactory=new KarmaHBaseFactory();
//		karmaHBaseFactory.deleteTable("testa");
//		karmaHBaseFactory.destroy();
//	}
//
//	@Test
//	public void testCreateTable() {
////		karmaHBaseFactory=new KarmaHBaseFactory();
////		karmaHBaseFactory.createTable("hahaha", "t1", 1);
//	}

	@Test
	public void testGetHBaseAdmin() {
		fail("Not yet implemented");
	}

}
