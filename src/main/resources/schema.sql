-- 任务表
create table cloud_crawler_task(
	task_id BIGINT not null AUTO_INCREMENT COMMENT 'id',
	task_name VARCHAR(120) not null COMMENT '任务名称',
	create_time	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '运行时间',
	run_time TIMESTAMP COMMENT '最近一次运行时间',
	run_status INT	COMMENT '运行状态',
	PRIMARY KEY(id),
	key idx_run_time(run_time),
	key idx_create_time(create_time)
) ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET utf8 COMMENT '任务表';

CREATE TABLE cloud_crawler_task_config(
  task_id BIGINT comment 'taskId',
  script LONGTEXT not null comment '脚本',
  seed_url VARCHAR(65532) not null comment '种子链接',
  thread_num int not null comment '线程数',
  accept_code VARCHAR(120) not null comment '允许通过的httpCode',
  table_name VARCHAR(120) not null comment '爬取下来的数据所存放的表的名字'
) ENGINE=INNODB DEFAULT CHARSET utf8 COMMENT '任务配置表';

CREATE TABLE cloud_crawler_schedule(
  schedule_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '调度id',
  task_id BIGINT NOT NULL COMMENT '任务id',
  start_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '调度开始时间',
  end_time TIMESTAMP COMMENT '调度结束时间',
  dt VARCHAR(120) NOT NULL COMMENT '分区',
  PRIMARY KEY(schedule_id)
) ENGINE=INNODB DEFAULT CHARSET utf8 COMMENT '任务调度表';

ALTER TABLE cloud_crawler_schedule ADD success_num BIGINT DEFAULT 0 COMMENT '成功页面数';
ALTER TABLE cloud_crawler_schedule ADD failed_num BIGINT DEFAULT 0 COMMENT '失败页面数';
ALTER TABLE cloud_crawler_schedule ADD request_num BIGINT DEFAULT 0 COMMENT '请求量';
ALTER TABLE cloud_crawler_schedule ADD data_num BIGINT DEFAULT 0 COMMENT '爬取数据量';
ALTER TABLE cloud_crawler_schedule ADD surplus_number BIGINT DEFAULT 0 COMMENT '剩余队列数';

ALTER TABLE cloud_crawler_task_config ADD user_agent INT DEFAULT 0 COMMENT 'UA规则';
