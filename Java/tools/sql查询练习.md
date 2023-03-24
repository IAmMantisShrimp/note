```mysql

# 总结,分组是要在查询结果中有的,
join后是结果集,可以将表看成一个大结果集,当也可以是select查询出来的结果集.
right,left join 以及 outter join 
交集 join, 

use partjob;
# 查询Student表中的所有记录的Sname、Ssex和Class列。
select sname,ssex,class from students;

# 查询教师所有的单位即不重复的Depart列。
# 有重复
select depart from teachers;
# 没有重复 distinct
select distinct depart from teachers;
# 查询Score表中成绩在60到80之间的所有记录。
select * from scores where degree>60 and degree < 80;
# 查询Score表中成绩为85，86或88的记录。
select * from scores where degree in (85,86,88);
# 查询Student表中“95031”班或性别为“女”的同学记录。
select * from students where class='95031' or ssex='女';
# 以Class降序查询Student表的所有记录。 升序--order by 字段, 降序--order by 字段 desc
select *
from students order by class desc;
# 以Cno升序、Degree降序查询Score表的所有记录。 两个以上排序条件时,order by 字段1,字段2 desc,字段3
select * from scores order by cno, degree desc;
# 查询“95031”班的学生人数。
select count(1) StuNum from students where class='95031';
# 查询Score表中的最高分的学生学号和课程号。
select sno,cno from scores order by degree desc limit 1;
# 查询‘3-105’号课程的平均分。
select avg(degree) 平均分 from scores where cno='3-105';
# 查询Score表中至少有5名学生选修的并以3开头的课程的平均分数。
# 以3开头,就是用like,至少有5名就是用count()
# HAVING语句通常与GROUP BY语句联合使用，用来过滤由GROUP BY语句返回的记录集。having 条件;
select cno, avg(degree) from scores where cno like '3%'
GROUP BY Cno having count(sno)>=5;
# 查询最低分大于70，最高分小于90的Sno列。
# 分析:有最大值和最小值,要么就是排序,取一个,要么就是分组,每个租取1,这里要的是分组;找一个人的最高分与最低分
select sno from scores group by sno HAVING max(degree)<90 and min(degree)>70;
# 询所有学生的Sname、Cno和Degree列。
# 分析: sname在student表中,cno,degree在scores表中, 两个表相连的是sno; 所以用join 或inner join
select sname,cno,degree from students join scores s on students.sno = s.sno;
# 查询所有学生的Sno、Cname和Degree列。
# cno在scores中,cname在courses中,用cno联系
select sno,degree,cname from scores
    inner join courses c
        on scores.cno=c.cno order by sno;



# 查询所有学生的Sname、Cname和Degree列。
# 三表查询
select sname,degree,cname from students
join scores s on students.sno = s.sno
    join courses c on s.cno = c.cno;


# 查询“95033”班所选课程的平均分。
# 先通过class找出对应的sno, 再通过sno找出degree
select cno,degree from scores
where sno in (select sno from students where class='95033');
# 然后分组就可以计算平均值
select cno,avg(degree) from scores
where sno in (select sno from students where class='95033') group by cno;
# 也可以
SELECT AVG(Degree) 平均值
FROM Students INNER JOIN Scores
ON(Students.Sno=Scores.Sno)
WHERE Class='95033'
GROUP BY scores.Cno;
# 加上课程名,在courses中
SELECT cname,avg(degree) 平均值
FROM scores join courses c on scores.cno = c.cno
where scores.cno in (
    select cno from scores
    where sno in (select sno from students
    where class='95033')
    group by cno
    )
group by cname;


# 假设使用如下命令建立了一个grade表：
CREATE TABLE grade(low TINYINT,upp TINYINT,rank CHAR(1));
INSERT INTO grade VALUES(90,100,'A');
INSERT INTO grade VALUES(80,89,'B');
INSERT INTO grade VALUES(70,79,'C');
INSERT INTO grade VALUES(60,69,'D');
INSERT INTO grade VALUES(0,59,'E');

# 现查询所有同学的Sno、Cno和rank列。
# sno 和 cno在scores中 , grand只是分级的用于分类
select sno,cno,`rank` 等级 from scores
    inner join grade on (scores.degree>=grade.low and scores.degree<=grade.upp)
order by sno;


# 查询选修“3-105”课程的成绩高于“109”号同学成绩的所有同学的记录。
select sno, degree from scores
where cno='3-105'
  and degree > (select degree from scores where sno='109' and cno='3-105');

# 查询score中选学一门以上课程的同学中分数为非最高分成绩的记录。
# 1.选学一门以上课程的同学
select sno from scores
group by sno having count(cno)>1;
# 2,分数非最高分成绩
 (select max(degree) from scores group by cno);
# 组合
select sno,cno,degree from scores
where sno in
      (select sno from scores group by sno having count(cno)>1)
  and degree not in
      (select max(degree) from scores group by cno);

# 查询成绩高于学号为“109”、课程号为“3-105”的成绩的所有记录。
select s1.sno,s1.degree from scores as s1
inner join scores as s2 on (s1.cno=s2.cno and s1.degree>s2.degree)
# 这里s1.cno查到的是课程为3-105的数据,然后s2在此数据下继续查找学号为109的数据,数据只有一个
where s1.cno='3-105' and s2.sno='109'
order by s1.sno;

# 查询和学号为108的同学同年出生的所有学生的Sno、Sname和Sbirthday列。
select s1.sno,s1.sname,s1.sbirthday from students as s1
inner join students as s2 on(year(s1.sbirthday)=year(s2.sbirthday))
where s2.sno='108';

# 查询“张旭“教师任课的学生成绩。
# 先查找老师的id
select tno from teachers where tname='张旭';
# 再根据老师id查找老师所交的课程id
select cno from courses where tno in
                              (select tno from teachers where tname='张旭');
# 再根据课程id,找到对应的学生id
select sno,degree from scores where cno
                    in (select cno from courses where tno in
                              (select tno from teachers where tname='张旭'));
# 加入姓名
select scores.sno,degree,sname from scores
inner join students s on scores.sno = s.sno
where cno
       in (select cno from courses where tno in
                  (select tno from teachers where tname='张旭'));
# 还能联表查询
select scores.sno, scores.degree from scores
inner join courses c on scores.cno = c.cno
inner join teachers t on c.tno = t.tno
where tname='张旭';

# 查询选修某课程的同学人数多于5人的教师姓名。
# 先查找处课程人数大于5的课程
select cno,count(sno) from scores group by cno having count(sno)>2;
# 查找老师的学号
select c.tno,c.cname from courses as c inner join
    scores s on c.cno = s.cno
    group by s.cno having count(s.sno)>2;
# 查找老师姓名
select c.tno,c.cname,t.tname from teachers as t
    inner join
        courses as c on t.tno=c.tno
    where c.cno in
          (select cno from scores group by cno having count(sno)>2);

# 查询95033班和95031班全体学生的记录。
select *  from students where class='95033' or class='95031';

# 查询存在有85分以上成绩的课程Cno.
select cno from scores group by cno having max(degree)>85;
SELECT DISTINCT Cno
FROM Scores
WHERE Degree>85;

# 查询出“计算机系“教师所教课程的成绩表。
# 1.查询出对应的老师的tno
select tno from teachers where depart='计算机系';
# 根据tno从课程中找到cno
select s.sno,s.cno,s.degree from scores as s
    inner join courses c on s.cno = c.cno
    inner join teachers t on c.tno = t.tno
where depart='计算机系';

# 查询“计算机系”与“电子工程系“不同职称的教师的Tname和Prof。
SELECT Tname,Prof
FROM Teachers
WHERE Depart='计算机系' AND Prof NOT IN(
    SELECT DISTINCT Prof
    FROM Teachers
    WHERE Depart='电子工程系');

# 查询选修编号为“3-105“课程且成绩至少高于任意选修编号为“3-245”的同学的成绩的Cno、Sno和Degree,并按Degree从高到低次序排序。
# 任何一个用any(),所有用all()

select Cno,Sno,Degree from scores where cno='3-105'
        and degree > any(select degree from scores where cno='3-245')
order by degree desc;

# 查询选修编号为“3-105”且成绩高于所有选修编号为“3-245”课程的同学的Cno、Sno和Degree.
select Cno,Sno,Degree from scores where cno='3-105'
        and degree > all(select degree from scores where cno='3-245')
order by degree desc;

# 查询所有教师和同学的name、sex和birthday.
select sname,ssex,sbirthday from students
union
select tname,tsex,tbirthday from teachers;

# 查询所有“女”教师和“女”同学的name、sex和birthday.
select sname,ssex,sbirthday from students where ssex='女'
union
select tname,tsex,tbirthday from teachers where tsex='女';

# 查询成绩比该课程平均成绩低的同学的成绩表。
# join 后是一个数据集,变量名称可以自己定义,如avg(degree) 如果不定义变量名,则无法调用
select avg(degree) from scores group by cno;
select s1.* from scores as s1
    inner join (select cno,avg(degree) adegree from scores group by cno ) as s2
on (s1.cno=s2.cno and s1.degree < s2.adegree)

# 查询所有任课教师的Tname和Depart.
# 就是从courses中的到得tno,然后根据tno找对对应老师得信息
select tname,depart from teachers as t
    inner join courses c on t.tno = c.tno;
select tname,depart from teachers where
                tno in (select tno from courses);

# 查询所有未讲课的教师的Tname和Depart. 即差集
select tname,depart from teachers where
                tno not in (select tno from courses);
# 差集,左边查出来全部得,右边查出来会少一点,取差集,即右边少的为空, is null
select tname,depart from teachers t
    left join courses c on t.tno = c.tno
where c.tno is null;

# 查询至少有2名男生的班号。
select class from students group by class having count(ssex='男')>2;
# 查询Student表中不姓“王”的同学记录。
select * from students where sname not like '王%';

# 查询Student表中每个学生的姓名和年龄。
select sname 姓名,YEAR(now()) - YEAR(sbirthday) 年龄 from students;
# 查询Student表中最大和最小的Sbirthday日期值。
select max(sbirthday),min(sbirthday) from students;
# 以班号和年龄从大到小的顺序查询Student表中的全部记录。
select * from students order by class,sbirthday desc;

# 查询“男”教师及其所上的课程。
select t.tname,c.cname from teachers as t
    left join courses c on t.tno = c.tno
where t.tsex='男';

# 查询和“李军”同性别的所有同学的Sname.
select * from students
where ssex in (select ssex from students where sname='李军');

select * from students as s1
    inner join students as s2
on s1.ssex != s2.ssex
where s2.sname='李军';

# 查询和“李军”同性别并同班的同学Sname.
select * from students as s1
    inner join students as s2
        on (s1.ssex = s2.ssex and s1.class != s2.class)
where s2.sname='李军';

# 查询所有选修“计算机导论”课程的“男”同学的成绩表
select s.sname,sc.cno,sc.degree, c.cname from scores as sc
join courses c on sc.cno = c.cno
join students s on sc.sno = s.sno
where c.cname='计算机导论' and s.ssex='男';
```

# 1.交集、并集、差集

![image-20221014103443416](https://img-blog.csdnimg.cn/img_convert/40cc63c0a88e62da630e9f09393f316e.png)







## (1)[内连接](https://so.csdn.net/so/search?q=内连接&spm=1001.2101.3001.7020)(inner join 可简写为join)



![image-20221014103635616](https://img-blog.csdnimg.cn/img_convert/72639e3d20e4fbec8f92afc961b0d1b8.png)

从图可知:就是交集,也就是两张表的共同数据;

用sql语句表示为:

```sql
select * from a [inner] join b on a.key = b.key
```

## (2)左外连接

从集合上看就是A,B的交集加上A的私有

![image-20221014103623631](https://img-blog.csdnimg.cn/img_convert/31458a6ef0ed8dfd190209d0e2b9a23d.png)

从集合上看就是A,B的交集加上A的私有

用sql语句表示:

```sql
select * from a left join b on a.key = b.key
```

## (3)右外连接

![image-20221014103747309](https://img-blog.csdnimg.cn/img_convert/2b08bad95db1f9319f64f7c937f682f1.png)

从集合上看就是A,B的交集加上B的私有

用sql语句表示:

```sql
select * from a right join b on a.key = b.key
```

## (4)全外连接

![image-20221014103825483](https://img-blog.csdnimg.cn/img_convert/bd75ef38f34f94d59c9559bb7ff13676.png)

从集合上看:就是A和B的共有+A的私有+B的私有 (AB全有)

对应的sql为:

```sql
select * from a full outer join b on a.key = b.key;
```

```txt
注意:上面的sql只在Oracle中适用,MYSQL中不支持,因此在MYSQL中想要实现全外连接,得用联合查询(union)实现
什么是联合查询(union)?
简单来说就是把两个sql语句的结果取并集(也就是去重复值)
sql语句如下:
```

```sql
select * from a left join b on a.key = b.key 
union
select * from a right join b on a.key = b.key
```

**剩下的3种只需要在上面4中join的基础上加where条件即可**

## (5)A的独有

![image-20221014103936052](https://img-blog.csdnimg.cn/img_convert/500baefad95bb5ad0cf8476e260682d9.png)

相当于在(2)的基础上加上where条件:

```sql
select * from a left join b on a.key = b.key  where b.key is null
```

## (6)B的独有

![image-20221014104007411](https://img-blog.csdnimg.cn/img_convert/f2a77247658f377a9354854789dc560e.png)

相当于在(3)的基础上加上where条件:

```sql
select * from a right join b on a.key = b.key  where a.key is null
或者
select * from b left join a on a.key = b.key  where a.key is null
```

## (7)相当于A的独有+B的独有

![image-20221014104108044](https://img-blog.csdnimg.cn/img_convert/02cdbb9896cb910be5ba5348dda06b52.png)

相当于在(4)的基础上加上where条件:

```sql
select * from a full outer join b on a.key = b.key 
where a.key is null or b.key is null 
```

同样,此写法只在oracle中适用,在mysql中得用union

```sql
select  * from a  left join b on a.key = b.key where b.key is null
union
select  * from a right join b on a.key = b.key where a.key is null

```









