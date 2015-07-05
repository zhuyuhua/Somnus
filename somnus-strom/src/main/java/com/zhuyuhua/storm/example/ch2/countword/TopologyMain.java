package com.zhuyuhua.storm.example.ch2.countword;

import com.zhuyuhua.storm.example.StormConstants;
import com.zhuyuhua.storm.example.ch2.countword.bolts.WordCounter;
import com.zhuyuhua.storm.example.ch2.countword.bolts.WordNormalizer;
import com.zhuyuhua.storm.example.ch2.countword.spouts.WordReader;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class TopologyMain {

	public static void main(String[] args) throws Exception {

		// 定义拓扑
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader", new WordReader());
		builder.setBolt("word-normalizer", new WordNormalizer()).shuffleGrouping("word-reader");
		builder.setBolt("word-counter", new WordCounter(), 1).fieldsGrouping("word-normalizer", new Fields("word"));

		// // 配置
		Config conf = new Config();
		conf.put("wordsFile", StormConstants.FILE_PATH);
		conf.setDebug(false);

		// 运行拓扑
		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Getting-Started-Toplogie", conf, builder.createTopology());
		Thread.sleep(5000);

		cluster.shutdown();

	}

}
