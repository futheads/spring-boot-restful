# spring-boot-restful简单介绍

## 框架整合目标
	用简单优雅的方式实现各常用功能的整合

## 为什是restful
	前后端分离，实现一套service，多个前端

## 程序入口
	com.futhead.restful.Application.java

## API接口定义
    Controller类加注解@RestController
    方法上加注解：
		@GetMapping("/")
		@PostMapping("/")
		@PutMapping("/{id}")
		@DeleteMapping("/{id}")

## 使用Swagger2构建restful API文档
	maven依赖：
		<dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.2.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.2.2</version>
        </dependency>

        <dependency>
            <groupId>com.github.caspar-chen</groupId>
            <artifactId>swagger-ui-layer</artifactId>
            <version>0.0.3</version>
        </dependency>
	@Api：用在类上，说明该类的作用
	@ApiOperation：用在方法上，说明方法的作用
	@ApiImplicitParams：用在方法上包含一组参数说明
	@ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
		paramType：参数放在哪个地方
			header-->请求参数的获取：@RequestHeader
			query-->请求参数的获取：@RequestParam
			path（用于restful接口）-->请求参数的获取：@PathVariable
			body（不常用）
			form（不常用）
		name：参数名
		dataType：参数类型
		required：参数是否必须传
		value：参数的意思
		defaultValue：参数的默认值
	@ApiResponses：用于表示一组响应
	@ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
		code：数字，例如400
		message：信息，例如"请求参数没填好"
		response：抛出异常的类
	@ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，请求参数无法使用@ApiImplicitParam注解进行描述的时候）
	@ApiModelProperty：描述一个model的属性

## ORM
	该框架使用了两个ORM框架：Spring-data-jpa + mybatis，复杂的（分页）查询、选择性的更新等使用mybatis（使用注解，放弃了配置文件），其他的使用spring-data-jpa。
	JPA
		po层：类上面加@Entity注解，id上加@Id，@GeneratedValue，其它属性上加@Column(nullable = false)等，配置启动时对表的处理：spring.jpa.properties.hibernate.hbm2ddl.auto=update，项目启动时即可自动更新(创建)表。
		dao层：extends JpaRepository<T, ID>，然后就可以直接使用默认的增删改查以及强大的根据某个字段查询。
	Mybatis
		可以构造这样的查询（更新）SQL语句
		public String buildSysUserUpdateSelective(final SysUser sysUser) {
            return new SQL() {{
                UPDATE("sys_user");
                if (sysUser.getUsername() != null) {
                    SET("username = #{username,jdbcType=VARCHAR}");
                }
                if (sysUser.getPassword() != null) {
                    SET("password = #{password,jdbcType=VARCHAR}");
                }
                if (sysUser.getPickname() != null) {
                    SET("pickname = #{pickname,jdbcType=VARCHAR}");
                }
                if (sysUser.getAge() != 0) {
                    SET("age = " + sysUser.getAge());
                }
                WHERE("id = " + sysUser.getId());
            }}.toString();
        }
	Ps.笔者既放不下Spring-data-jpa的简单优雅，又放不下mybatis的灵活性╮(╯▽╰)╭

## 分页
	使用分页插件
	Maven依赖：
		<dependency>
			<groupId>com.github.pagehelper</groupId>
			<artifactId>pagehelper-spring-boot-starter</artifactId>
			<version>1.1.1</version>
		</dependency>
	配置：
		pagehelper.helperDialect=mysql
		pagehelper.reasonable=true
		pagehelper.supportMethodsArguments=true
		pagehelper.params=count=countSql
	使用：
		public Page<SysUserQueryVo> queryByConditions(SysUserQueryVo queryVo, int pageNum, int pageSize) {
			PageHelper.startPage(pageNum, pageSize);
			return sysUserMapper.queryByConditions(queryVo);
		}

## 数据源druid
	可以实现项目的监控功能(包括数据源、SQL监控、SQL防火墙、Web应用、URI监控、Session监控等)：http://localhost:8080/druid/index.html
	maven依赖：
		<dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.2</version>
        </dependency>
	配置：
		spring.datasource.url=jdbc:mysql://localhost:3306/test
		spring.datasource.username=futhead
		spring.datasource.password=futhead
		其他配置请参考：https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter

## 日志
	slf4j, 默认实现logback
	配置：
		设置log文件，可以是绝对路径，也可以是相对路径
		logging.file=log/my.log
		级别
		logging.level.com.futhead.spring.api=DEBUG
		也可以使用logback-spring.xml进行具体配置
	使用：
		import org.slf4j.Logger;
		import org.slf4j.LoggerFactory;
		private static final Logger LOG = LoggerFactory.getLogger(XXX.class);

## 使用AOP统一处理Web请求日志
	Maven依赖：
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>
	配置：
		类上面加@Aspect，@Order(5)（优先级），@Component
		定义切点：@Pointcut("execution(public * com.futhead.restful.api..*.*(..))")
		在切入点开始处切入内容：@Before("webLog()")
		在切入点return内容之后切入内容：@AfterReturning(returning = "ret", pointcut = "webLog()")
	Ps.记录方法调用时间，需要用到ThreadLocal设计模式。

## 异常处理
	异常处理类上面加@ControllerAdvice注解
	具体方法处理方法上加@ExceptionHandler(value = MyException.class)，@ResponseBody

## 事务管理
	只需要在使用事务的方法上加@Transactional注解即可。

## 使用EhCache做缓存
	Maven依赖：
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-cache</artifactId>
		</dependency>
		<dependency>
			<groupId>net.sf.ehcache</groupId>
			<artifactId>ehcache</artifactId>
		</dependency>
	配置：
		在主文件@EnableCaching
		dao层接口上加@CacheConfig(cacheNames = "xxx")
		查询后加入缓存：
			@Cacheable(key = "#p0")
			SysUser findOne(int id);
		删除时从缓存中删除
			CacheEvict(key = "#p0")
			void delete(Integer integer);
		修改时在缓存中更新
			@CachePut(key = "#p0.id")
			SysUser save(SysUser sysUser);

## 自定义Banner
	只需要在工程的/src/main/resources目录下创建一个banner.txt文件，把字符画log放进去，就可以了
	几个可以生成字符画的网站：
		http://patorjk.com/software/taag
		http://www.network-science.de/ascii/
		http://www.degraeve.com/img2txt.php

## 安全策略：
	正在学习，持续更新
