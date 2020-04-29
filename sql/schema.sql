drop table if exists t_article;
drop table if exists t_category;
drop table if exists t_tags;
drop table if exists t_article_tag;
drop table if exists t_file;


create table t_article(
	article_id int(11) not null auto_increment comment '博文 ID',
	title varchar(32) not null comment '标题',
	context text  comment '内容',
	cate_id int(11) comment '分类ID',
	state char(16) not null comment '状态',
	update_time timestamp comment 'hack mysql auto update timestamp',
	create_time timestamp default now() comment '创建时间',
	primary key(article_id)
) engine=InnoDB default charset=utf8 comment '博文表';
create unique index up_article_id on t_article(article_id);


create table t_category(
	cate_id int(11) not null auto_increment comment '类别 ID',
	cate_name varchar(32) not null comment '类别名',
	update_time timestamp comment 'hack mysql auto update timestamp',
	create_time timestamp default now() comment '创建时间',
	primary key(cate_id)
) engine=InnoDB default charset=utf8 comment '类别表';
create unique index up_cate_id on t_category(cate_id);
create unique index up_cate_name on t_category(cate_name);


create table t_tags (
	tag_id int(11) not null auto_increment comment '标签 ID',
	tag_name varchar(32) not null comment '标签名',
	update_time timestamp comment 'hack mysql auto update timestamp',
	create_time timestamp default now() comment '创建时间',
	primary key(tag_id)
) engine=InnoDB default charset=utf8 comment '标签表';
create unique index up_tag_id on t_tags(tag_id);
create unique index up_tag_name on t_tags(tag_name);

create table t_article_tag (
	article_id int(11) not null comment '博文 ID',
	tag_id int(11) not null comment '标签 ID',
	update_time timestamp comment 'hack mysql auto update timestamp',
	create_time timestamp default now() comment '创建时间'
) engine=InnoDB default charset=utf8 comment '博文标签表';

create table t_file (
	file_id int(11) not null comment '文件 ID',
	path varchar(128) not null comment '真实文件路径',
	img varchar(128) not null comment '网络路径',
	`name` varchar(32) comment '文件名',
	quote int(6) comment '引用次数',
	update_time timestamp comment 'hack mysql auto update timestamp',
	create_time timestamp default now() comment '创建时间',
	primary key(file_id)
) engine=InnoDB default charset=utf8 comment '文件表';
create unique index up_file_id on t_file(file_id);


