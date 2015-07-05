package com.zhuyuhua.storm.example.ch2.countword.bolts;

import java.util.HashMap;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

/**
 * 第2个bolts：负责为单词计数。这个拓扑结束时（cleanup()方法被调用时），我们将显示每个单词的数量
 * 
 * @author zhuyh
 *
 */
public class WordCounter extends BaseBasicBolt {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;

	private Map<String, Integer> counters;

	private OutputCollector collector;

	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		this.counters = new HashMap<String, Integer>();
		this.name = context.getThisComponentId();
		this.id = context.getThisTaskId();
	}

	/**
	 * 为每个单词计数
	 */
	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		String str = input.getString(0);
		/**
		 * 如果单词不存在map，则创建，如果存在，则+1
		 */
		if (!counters.containsKey(str)) {
			counters.put(str, 1);
		} else {
			Integer c = counters.get(str) + 1;
			counters.put(str, c);
		}

	}

	/**
	 * 这个spout结束时（集群关闭的时候），我们会显示单词数量
	 */
	@Override
	public void cleanup() {
		System.out.println("单词数【" + name + "-" + id + "】");
		for (Map.Entry<String, Integer> entry : counters.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub

	}

}
