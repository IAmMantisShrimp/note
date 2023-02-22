# Excel操作

## 1.从数据库写入到Excel

实体类要先标记好列名:

```java
package com.example.backstage.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author HuaRunSheng
 * @date 2022/10/26 16:10
 * @description :
 */
@Data
public class DrownedMan {
    @ExcelProperty(value = "序号")
    private Integer id;
    @ExcelProperty(value = "姓名")
    private String name;
    @ExcelProperty(value = "性别")
    private Integer sex;
    @ExcelProperty(value = "年龄")
    private Integer age;
    @ExcelProperty(value = "溺水时间")
    private Date drowningTime;
    @ExcelProperty(value = "环境")
    private String surrounding;
    @ExcelProperty(value = "是否会游泳")
    private Integer canSwim;
    @ExcelProperty(value = "是否死亡")
    private Integer death;
}
```

使用

F:\Java\PracticeProject\20220515个人运动管理平台\code\backstage\src\test\java\com\example\backstage\mapper\DrownedManMapperTest.java

```jAVA
        // 从数据库读取数据
        List<DrownedMan> drownedMEN = drownedManMapper.selectList();
        System.out.println(drownedMEN.size());
        //System.out.println(drownedMEN.get(1));
        //for (int i = 1; i < 10; i++) {
        //    System.out.println(drownedMEN.get(i));
        //}
        //定义文件输出位置
        FileOutputStream outputStream = null;
        try {
            // 新建一个xlsx文件
            outputStream = new FileOutputStream(new File("E:/GOOGLE_download/user1.xlsx"));
            // 将数据库数据写入到文件中
            EasyExcel.write(outputStream, DrownedMan.class).sheet("用户信息").doWrite(drownedMEN);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //EasyExcel.write(outputStream, DrownedMan.class).sheet("用户信息").doWrite(drownedMEN);
```



## 2.从Excel写入到数据库



实体类

```java
package com.example.backstage.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 食物详情类
 * ExcelIgnore: 在 导入Excel文件是,排除该属性,即不读取该属性的列
 * ExcelProperty: 在导入时读取excel对应的列信息 value(default): excel列名, convert: 类型转换(如1转男0转女)
 * @author ajie
 * @createTime 2021年06月14日 18:42:00
 */
@Data
public class Food {

    @ExcelIgnore
    @ApiModelProperty("主键")
    private Long id;

    @ExcelProperty(value = "食物名称")
    @ApiModelProperty("食物名称")
    private String title;

    @ExcelIgnore
    @ApiModelProperty("食物类别")
    private Long typeId;

    @ExcelIgnore
    @ApiModelProperty("图片地址")
    private String imageUrls;

    @ExcelProperty(value = "营养元素")
    @ApiModelProperty("营养元素")
    private String nutrient;

    @ExcelProperty(value = "热量")
    @ApiModelProperty("热量")
    private Float heat;

    @ExcelProperty(value = "蛋白质")
    @ApiModelProperty("蛋白质")
    private Float protein;

    @ExcelProperty(value = "脂肪")
    @ApiModelProperty("脂肪")
    private Float fat;

    @ExcelProperty(value = "碳水化合物")
    @ApiModelProperty("碳水化合物")
    private Float carbonWater;

    @ExcelProperty(value = "膳食纤维")
    @ApiModelProperty("膳食纤维")
    private Float dietaryFiber;

    @ExcelProperty(value = "维生素A")
    @ApiModelProperty("维生素A")
    private Float vitaminA;

    @ExcelProperty(value = "维生素C")
    @ApiModelProperty("维生素C")
    private Float vitaminC;

    @ExcelProperty(value = "维生素E")
    @ApiModelProperty("维生素E")
    private Float vitaminE;

    @ExcelProperty(value = "胡萝卜素")
    @ApiModelProperty("胡萝卜素")
    private Float carrot;

    @ExcelProperty(value = "维生素B1")
    @ApiModelProperty("维生素B1")
    private Float vitaminB1;

    @ExcelProperty(value = "维生素B2")
    @ApiModelProperty("维生素B2")
    private Float vitaminB2;

    @ExcelProperty(value = "烟酸")
    @ApiModelProperty("烟酸")
    private Float niacin;

    @ExcelProperty(value = "胆固醇")
    @ApiModelProperty("胆固醇")
    private Float cholesterol;

    @ExcelProperty(value = "镁")
    @ApiModelProperty("镁")
    private Float magnesium;

    @ExcelProperty(value = "铁")
    @ApiModelProperty("铁")
    private Float iron;

    @ExcelProperty(value = "钙")
    @ApiModelProperty("钙")
    private Float calcium;

    @ExcelProperty(value = "锌")
    @ApiModelProperty("锌")
    private Float zinc;

    @ExcelProperty(value = "铜")
    @ApiModelProperty("铜")
    private Float copper;

    @ExcelProperty(value = "锰")
    @ApiModelProperty("锰")
    private Float manganese;

    @ExcelProperty(value = "钾")
    @ApiModelProperty("钾")
    private Float potassium;

    @ExcelProperty(value = "磷")
    @ApiModelProperty("磷")
    private Float phosphorus;

    @ExcelProperty(value = "钠")
    @ApiModelProperty("钠")
    private Float sodium;

    @ExcelProperty(value = "硒")
    @ApiModelProperty("硒")
    private Float selenium;

    @JsonIgnore
    @ExcelProperty(value = "食物类别")
    private String typeTitle;

}

```

使用;

F:\Java\PracticeProject\20220515个人运动管理平台\code\backstage\src\test\java\com\example\backstage\BackstageApplicationTests.java

```java
List<FoodType> foodTypes = foodTypeMapper.typeAll();
        System.out.println("foodTypes: " + foodTypes);
        try {
            FileInputStream file = new FileInputStream("F:\\Java\\PracticeProject\\20220515个人运动管理平台\\code\\backstage\\src\\main\\resources\\static\\food.xlsx");
            //ExcelReaderSheetBuilder sheet = EasyExcel.read(file).sheet(0);
            //System.out.println(sheet);
            for (Food food : EasyExcelUtil.readExcel(file, Food.class)) {
                Long foodTypeId = getFoodTypeId(food.getTypeTitle(), foodTypes);
                food.setTypeId(foodTypeId);
                System.out.println("foodTypeId: " + foodTypeId);
                System.out.println("food: "+food);
                foodMapper.insert(food);
                //return;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
```













