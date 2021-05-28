# IginX 安装使用教程

[TOC]

IginX 是清华大学大数据系统软件工程国家实验室，为满足工业互联网场景推出的新一代高可扩展时序数据库分布式中间件，目前支持 IoTDB，InfluxDB 作为数据后端。

## 安装

基于 IginX 的分布式时序数据库系统由三部分构成，一是 ZooKeeper，用于存储整个集群的原信息，二是 IginX 中间件，用于管理整个集群的拓扑结构，转发处理写入查询请求，并对外提供数据访问接口，三是数据存储后端，用于存储时序数据，本教程中使用 IoTDB 作为数据后端。

### 安装 JDK8

JDK 是 Java 程序的开发的运行环境，由于 ZooKeeper、IginX 以及 IoTDB 都是使用 Java 开发的，因此首先需要安装 Java。如果本地已经安装了 JDK8，直接跳过此步骤。

安装 JDK8 具体方式如下：

1. 首先访问 [Java官方网站](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)下载适用于当前系统的 JDK 包。

2. 安装

   ```shell
   $ cd ~/Downloads
   $ tar -zxf jdk-8u181-linux-x64.gz # 解压文件
   $ mkdir /opt/jdk
   $ mv jdk-1.8.0_181 /opt/jdk/
   ```

3. 设置路径

   编辑 ~/.bashrc 文件，在文件末端加入如下的两行：

   ```shell
   export JAVA_HOME = /usr/jdk/jdk-1.8.0_181
   export PATH=$PATH:$JAVA_HOME/bin
   ```

   加载更改后的配置文件：

   ```shell
   $ source ~/.bashrc
   ```

4. 使用 java -version 判断 JDK 是否安装成功

   ```shell
   $ java -version
   java version "1.8.0_181"
   Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
   Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
   ```

   如果显示出如上的字样，则表示安装成功。

### 安装 ZooKeeper

ZooKeeper 是 Apache 推出的开源的分布式应用程序协调服务。具体安装方式如下：

1. 访问[官网](https://zookeeper.apache.org/releases.html)下载并解压 ZooKeeper

   ```shell
   $ cd ~
   $ wget https://mirrors.bfsu.edu.cn/apache/zookeeper/zookeeper-3.7.0/apache-zookeeper-3.7.0-bin.tar.gz
   $ tar -zxvf apache-zookeeper-3.7.0-bin.tar.gz
   ```

2. 修改 ZooKeeper 默认配置文件

   ```shell
   $ cd apache-zookeeper-3.7.0-bin/
   $ mkdir data
   $ cp conf/zoo_sample.cfg conf/zoo.cfg
   ```

   然后编辑 conf/zoo.cfg 文件，将

   ```shell
   dataDir=/tmp/zookeeper
   ```

   修改为

   ```shell
   dataDir=data
   ```

### 安装 IginX 

IginX 为系统的主体部分，直接访问 [IginX 项目](https://github.com/thulab/IginX/)下载 [IginX 项目发布包](https://github.com/thulab/IginX/releases/download/rc%2Fv0.2.0/IginX-release-v0.2.0-bin.tar.gz) 即可。

```shell
$ cd ~
$ wget https://github.com/thulab/IginX/releases/download/rc%2Fv0.2.0/IginX-release-v0.2.0-bin.tar.gz
$ tar -zxvf IginX-release-v0.2.0-bin.tar.gz
```

### 安装 IoTDB

IoTDB 是 Apache 推出的时序数据库，具体安装方式如下：

```shell
$ cd ~
$ wget https://mirrors.bfsu.edu.cn/apache/iotdb/0.12.0/apache-iotdb-0.12.0-server-bin.zip
$ unzip apache-iotdb-0.12.0-server-bin.zip
```

## 启动

### IoTDB

首先需要启动 IoTDB。

```shell
$ cd ~
$ cd apache-iotdb-0.12.0-server-bin/
$ ./sbin/start-server.sh
```

显示出如下字样，表示 IoTDB 启动成功：

```shell
2021-05-27 08:21:07,440 [main] INFO  o.a.i.d.s.t.ThriftService:125 - IoTDB: start RPC ServerService successfully, listening on ip 0.0.0.0 port 6667
2021-05-27 08:21:07,440 [main] INFO  o.a.i.db.service.IoTDB:129 - IoTDB is set up, now may some sgs are not ready, please wait several seconds...
2021-05-27 08:21:07,448 [main] INFO  o.a.i.d.s.UpgradeSevice:109 - finish counting upgrading files, total num:0
2021-05-27 08:21:07,449 [main] INFO  o.a.i.d.s.UpgradeSevice:74 - Waiting for upgrade task pool to shut down
2021-05-27 08:21:07,449 [main] INFO  o.a.i.d.s.UpgradeSevice:76 - Upgrade service stopped
2021-05-27 08:21:07,449 [main] INFO  o.a.i.db.service.IoTDB:146 - Congratulation, IoTDB is set up successfully. Now, enjoy yourself!
2021-05-27 08:21:07,450 [main] INFO  o.a.i.db.service.IoTDB:93 - IoTDB has started.
```

### ZooKeeper

随后启动 ZooKeeper：

```shell
$ cd ~
$ cd apache-zookeeper-3.7.0-bin/
$ ./bin/zkServer.sh start
```

显示出如下字样，表示 ZooKeeper 启动成功：

```shell
ZooKeeper JMX enabled by default
Using config: /home/root/apache-zookeeper-3.7.0-bin/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
```

### IginX

最后启动 IginX：

```shell
$ cd ~
$ cd IginX-release-v0.2.0-bin
$ chmod +x start.sh # 为启动脚本添加启动权限
$ ./start.sh 
```

显示出如下字样，表示 IginX 启动成功：

```shell
May 27, 2021 8:32:19 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [127.0.0.1:6666]
May 27, 2021 8:32:19 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer] Started.
08:32:19.446 [Thread-0] INFO cn.edu.tsinghua.iginx.rest.RestServer - Iginx REST server has been available at http://127.0.0.1:6666/.
```

## 访问 IginX

### RESTful 接口

启动完成后，可以便捷地使用 RESTful 接口向 IginX 中写入并查询数据。

创建文件 insert.json，并向其中添加如下的内容：

```json
[
  {
    "name": "archive_file_tracked",
    "datapoints": [
        [1359788400000, 123.3],
        [1359788300000, 13.2 ],
        [1359788410000, 23.1 ]
    ],
    "tags": {
        "host": "server1",
        "data_center": "DC1"
    }
  },
  {
      "name": "archive_file_search",
      "timestamp": 1359786400000,
      "value": 321,
      "tags": {
          "host": "server2"
      }
  }
]
```

使用如下的命令即可向数据库中插入数据：

```shell
$ curl -XPOST -H'Content-Type: application/json' -d @insert.json http://127.0.0.1:6666/api/v1/datapoints
```

在插入数据后，还可以使用 RESTful 接口查询刚刚写入的数据。

创建文件 query.json，并向其中写入如下的数据：

```json
{
	"start_absolute" : 1,
	"end_relative": {
		"value": "5",
		"unit": "days"
	},
	"time_zone": "Asia/Kabul",
	"metrics": [
		{
		"name": "archive_file_tracked"
		},
		{
		"name": "archive_file_search"
		}
	]
}
```

使用如下的命令查询数据：

```shell
$ curl -XPOST -H'Content-Type: application/json' -d @query.json http://127.0.0.1:6666/api/v1/datapoints/query
```

命令会返回刚刚插入的数据点信息：

```json
{
    "queries": [
        {
            "sample_size": 3,
            "results": [
                {
                    "name": "archive_file_tracked",
                    "group_by": [
                        {
                            "name": "type",
                            "type": "number"
                        }
                    ],
                    "tags": {
                        "data_center": [
                            "DC1"
                        ],
                        "host": [
                            "server1"
                        ]
                    },
                    "values": [
                        [
                            1359788300000,
                            13.2
                        ],
                        [
                            1359788400000,
                            123.3
                        ],
                        [
                            1359788410000,
                            23.1
                        ]
                    ]
                }
            ]
        },
        {
            "sample_size": 1,
            "results": [
                {
                    "name": "archive_file_search",
                    "group_by": [
                        {
                            "name": "type",
                            "type": "number"
                        }
                    ],
                    "tags": {
                        "host": [
                            "server2"
                        ]
                    },
                    "values": [
                        [
                            1359786400000,
                            321.0
                        ]
                    ]
                }
            ]
        }
    ]
}
```

更多接口可以参考 [IginX 官方手册](https://github.com/thulab/IginX/blob/main/docs/pdf/userManualC.pdf) 。

### RPC 接口

除了 RESTful 接口外，IginX 还提供了 RPC 的数据访问接口，具体接口参考 [IginX 官方手册](https://github.com/thulab/IginX/blob/main/docs/pdf/userManualC.pdf)，同时 IginX 还提供了部分[官方 example](https://github.com/thulab/IginX/tree/main/example/src/main/java/cn/edu/tsinghua/iginx/session)，展示了 RPC 接口最常见的用法。
