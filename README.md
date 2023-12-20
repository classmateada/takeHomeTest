# Take Home Test

## 项目设计

使用 Nginx 作为负载均衡器，运行在 `9090` 端口上，并采用轮询的策略进行负载均衡，相关配置位于 `nginx.conf` 中。Spring Boot 服务、Nginx 负载均衡器、MySQL 数据库均以 Docker 容器的形式使用 docker-compose 进行部署。

MySQL 主从架构的实现方式为主节点（Master）向从节点（Slave）同步 binlog 以此实现数据同步。相关配置位于 `mysql` 目录下。Master 可读可写，Slave 只可读。Master 和 Slave 除了常规的数据查询用户之外，均有一个单独的用户用于同步数据。

在本系统中，`spring-boot-master` 容器运行在 `8080` 端口上，连接 MySQL 主节点，`spring-boot-slave` 容器运行在 `8081` 端口上，连接 MySQL 从节点。

系统采用 `docker-compose` 进行编排和一键启动。

架构图如下所示：

![](https://cdn.jsdelivr.net/gh/classmateada/site-pictures/img/20231220223851.png)

## 部署方式

### 环境需求

- Java 17
- Docker
- docker-compose

### 部署步骤

1. 拉取本仓库。
2. `cd takeHomeTest`
3. `./mvnw -DskipTest` (这里因为不跳过测试会因为无法连接数据库导致构建失败，所以需要跳过测试)
4. `docker-compose build` 构建所需镜像。
5. `docker-compose up -d` 启动全部五个容器。
6. `curl http://localhost:9090` 即可看到“Hello world”和返回该信息的 Spring Boot 实例所运行的端口。

## 更好的方案

使用 Kubernetes。由于 docker-compose 若要将服务实例部署在多台服务器上必须一台一台地手动启动，而 Kubernetes 则可以做到一键部署多个实例，同时还自带负载均衡器和网关 Ingress，所以若有将服务实例部署在多台服务器上的需求，使用单独的服务器安装 MySQL 并使用 Kubernetes 部署服务实例是更好的方式。本次因受限于时间，所以暂时没有实现该方案，仅仅绘制了架构图。架构图如下所示：

![](https://cdn.jsdelivr.net/gh/classmateada/site-pictures/img/20231220223302.png)
