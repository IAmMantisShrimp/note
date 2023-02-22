# Java 常用方法(自定义)

## 1.将类字段名取出并置为数组

F:\Java\PracticeProject\220502Vue2Boot\Vue\back_end\src\main\java\com\example\back_end\utils\MyUtil.java

```
String[] fieldsName = MyUtil.getFieldsName(SysFile.class);
```

```
//获取类名
System.out.println("getName:  "+SysFile.class.getName());
//获取所有完整字段名
System.out.println("CanonicalName:  "+SysFile.class.getCanonicalName());
```

完整方法:

```
Field[] declaredFields = SysFile.class.getDeclaredFields();
String[] sysFields= (String[]) (Arrays.stream(declaredFields)
        .map(Field::getName)//获取简化字段名
        .filter(name -> !name.equals("serialVersionUID"))//筛选,不需要UID
        .collect(Collectors.toList()))//转为列表
        .toArray(new String[0]);//转为字符数组
for (String sysField : sysFields) {
    System.out.println("sysField: "+sysField);
}
```



## 2.字段名中英文转换

F:\Java\PracticeProject\220502Vue2Boot\Vue\back_end\src\main\java\com\example\back_end\utils\MyUtil.java

```
String[] convertFieldsName = MyUtil.convertFieldsName(fieldsName);
```

### 2.1 字段

```
// english convert to chinese
Map<String,String> convertMap=new TreeMap<>();
convertMap.put("id","编号");
convertMap.put("name","姓名");
convertMap.put("type","类型");
convertMap.put("size","大小");
convertMap.put("url","网址");
convertMap.put("md5","加密");
convertMap.put("isDelete","已删除");
convertMap.put("enable","可用");
System.out.println(convertMap.get("name"));
```

### 2.2 方法

```
Field[] declaredFields = SysFile.class.getDeclaredFields();
// english convert to chinese
Map<String,String> convertMap=new TreeMap<>();
convertMap.put("id","编号");
convertMap.put("name","姓名");
convertMap.put("type","类型");
convertMap.put("size","大小");
convertMap.put("url","网址");
convertMap.put("md5","加密");
convertMap.put("isDelete","已删除");
convertMap.put("enable","可用");
System.out.println(convertMap.get("name"));

String[] sysFields= (String[]) (Arrays.stream(declaredFields)
        .map(Field::getName)//获取简化字段名
        .filter(name -> !name.equals("serialVersionUID"))//筛选,不需要UID
        .collect(Collectors.toList()))//转为列表
        .toArray(new String[0]);//转为字符数组
for (String sysField : sysFields) {
    System.out.println("sysField: "+sysField);
}
//将英文数组转为中文数组
String[] sysFieldsC = (String[]) (Arrays.stream(sysFields)
        .map(c -> convertMap.get(c))
        .collect(Collectors.toList())).
        toArray(new String[0]);;
for (String s : sysFieldsC) {
    System.out.println(s);
}
```



## 3.获取包下所有java类

F:\Java\PracticeProject\220502Vue2Boot\Vue\back_end\src\main\java\com\example\back_end\utils\MyUtil.java

```
for (Class aClass : MyUtil.getClassByPackage("com.example.back_end.entity")) {
            System.out.println(aClass.getName());
        }
```

```
    /**
     *     //获取一个包下所有的类
     *     // getClassName("top.lingkang.demohibernate.entity")
     * @param packageName:包路径
     * @return :类数组
     */
    public static Class<?>[] getClassByPackage(String packageName) {
        try {
            Enumeration<URL> resources = ClassUtils.class.getClassLoader().getResources(packageName.replaceAll("\\.", "/"));
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String[] file = new File(url.getFile()).list();
                Class<?>[] classList = new Class[file.length];
                for (int i = 0; i < file.length; i++) {
                    classList[i] = Class.forName(packageName + "." + file[i].replaceAll("\\.class", ""));
                }
                return classList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
```



## 4.根据类名获取类对象

```
/**输入类名,返回类对象
 *         String packageName="com.example.back_end.entity";
 *         System.out.println(MyUtil.getClassByName("SysFile", packageName));
 * @param className:类名
 * @param packageName:包路径
 * @return 类
 */
public static Class<?> getClassByName(String className, String packageName){
    try {
        Enumeration<URL> resources = ClassUtils.class.getClassLoader().getResources(packageName.replaceAll("\\.", "/"));
        while (resources.hasMoreElements()) {
            // url=com/example/back_end/entity
            URL url = resources.nextElement();

            // file[i] =  Article.class
            String[] file = new File(url.getFile()).list();

            for (String s : file) {
                //System.out.println(s);
                if (s.contains(className)){
                    return Class.forName(packageName + "." +s.replaceAll("\\.class", ""));
                }

            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
```

## 5.随机生成名字



```java
package com.example.backstage.util;


import java.nio.charset.Charset;
import java.util.Random;

/**
 * 汉字工具
 * 随机生成名字
 * @author xzy
 * @date 2021/10/2113:49
 */
public class ChineseUtil {
    /**
     * 中华姓氏（按照使用人数由多到少排序）
     */
    public static final String[] LAST_NAME = new String[]{
            "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤",
            "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水",
            "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞",
            "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕",
            "郝", "邬", "安", "常", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄",
            "和", "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成",
            "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季",
            "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭", "梅", "盛", "林", "刁", "钟", "徐", "邱", "骆", "高",
            "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝", "管", "卢", "莫", "经", "房", "裘", "缪",
            "干", "解", "应", "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪", "包", "诸", "左", "石", "崔", "吉", "钮",
            "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀", "羊", "於", "惠", "甄", "曲", "家", "封", "芮", "羿",
            "储", "靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山", "谷", "车",
            "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎",
            "祖", "武", "符", "刘", "景", "詹", "束", "龙", "叶", "幸", "司", "韶", "郜", "黎", "蓟", "薄", "印", "宿", "白",
            "怀", "蒲", "台", "从", "鄂", "索", "咸", "籍", "赖", "卓", "蔺", "屠", "蒙", "池", "乔", "阴", "欎", "胥", "能",
            "苍", "双", "闻", "莘", "党", "翟", "谭", "贡", "劳", "逄", "姬", "申", "扶", "堵", "冉", "宰", "郦", "雍", "郤",
            "璩", "桑", "桂", "濮", "牛", "寿", "通", "边", "扈", "燕", "冀", "郏", "浦", "尚", "农", "温", "别", "庄", "晏",
            "柴", "瞿", "阎", "充", "慕", "连", "茹", "习", "宦", "艾", "鱼", "容", "向", "古", "易", "慎", "戈", "廖", "庾",
            "终", "暨", "居", "衡", "步", "都", "耿", "满", "弘", "匡", "国", "文", "寇", "广", "禄", "阙", "东", "殴", "殳",
            "沃", "利", "蔚", "越", "夔", "隆", "师", "巩", "厍", "聂", "晁", "勾", "敖", "融", "冷", "訾", "辛", "阚", "那",
            "简", "饶", "空", "曾", "毋", "沙", "乜", "养", "鞠", "须", "丰", "巢", "关", "蒯", "相", "查", "后", "荆", "红",
            "游", "竺", "权", "逯", "盖", "益", "桓", "公", "万俟", "司马", "上官", "欧阳", "夏侯", "诸葛", "闻人", "东方", "赫连",
            "皇甫", "尉迟", "公羊", "澹台", "公冶", "宗政", "濮阳", "淳于", "单于", "太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐",
            "钟离", "宇文", "长孙", "慕容", "鲜于", "闾丘", "司徒", "司空", "亓官", "司寇", "仉", "督", "子车", "颛孙", "端木", "巫马",
            "公西", "漆雕", "乐正", "壤驷", "公良", "拓跋", "夹谷", "宰父", "谷梁", "晋", "楚", "闫", "法", "汝", "鄢", "涂", "钦",
            "段干", "百里", "东郭", "南", "门", "呼延", "归海", "羊舌", "微生", "岳", "帅", "缑", "亢", "况", "郈", "有", "琴", "梁丘",
            "左丘", "东门", "西门", "商", "牟", "佘", "佴", "伯", "赏", "南宫", "墨", "哈", "谯", "笪", "年", "爱", "阳", "佟", "第五",
            "言", "福", "百", "姓"
    };

    /**
     * 各姓氏的权重（数值越大表明使用频率越高）
     * A                  B             C       D    E  F
     * |--------------------|---------------|----------|-----|---|-|
     */
    public static final int[] LAST_NAME_WEIGHTS = new int[]{
            505, 504, 503, 502, 501, 500, 499, 498, 497, 496, 495, 494, 493, 492, 491, 490, 489, 488, 487, 486, 485,
            484, 483, 482, 481, 480, 479, 478, 477, 476, 475, 474, 473, 472, 471, 470, 469, 468, 467, 466, 465, 464,
            463, 462, 461, 460, 459, 458, 457, 456, 455, 454, 453, 452, 451, 450, 449, 448, 447, 446, 445, 444, 443,
            442, 441, 440, 439, 438, 437, 436, 435, 434, 433, 432, 431, 430, 429, 428, 427, 426, 425, 424, 423, 422,
            421, 420, 419, 418, 417, 416, 415, 414, 413, 412, 411, 410, 409, 408, 407, 406, 405, 404, 403, 402, 401,
            400, 399, 398, 397, 396, 395, 394, 393, 392, 391, 390, 389, 388, 387, 386, 385, 384, 383, 382, 381, 380,
            379, 378, 377, 376, 375, 374, 373, 372, 371, 370, 369, 368, 367, 366, 365, 364, 363, 362, 361, 360, 359,
            358, 357, 356, 355, 354, 353, 352, 351, 350, 349, 348, 347, 346, 345, 344, 343, 342, 341, 340, 339, 338,
            337, 336, 335, 334, 333, 332, 331, 330, 329, 328, 327, 326, 325, 324, 323, 322, 321, 320, 319, 318, 317,
            316, 315, 314, 313, 312, 311, 310, 309, 308, 307, 306, 305, 304, 303, 302, 301, 300, 299, 298, 297, 296,
            295, 294, 293, 292, 291, 290, 289, 288, 287, 286, 285, 284, 283, 282, 281, 280, 279, 278, 277, 276, 275,
            274, 273, 272, 271, 270, 269, 268, 267, 266, 265, 264, 263, 262, 261, 260, 259, 258, 257, 256, 255, 254,
            253, 252, 251, 250, 249, 248, 247, 246, 245, 244, 243, 242, 241, 240, 239, 238, 237, 236, 235, 234, 233,
            232, 231, 230, 229, 228, 227, 226, 225, 224, 223, 222, 221, 220, 219, 218, 217, 216, 215, 214, 213, 212,
            211, 210, 209, 208, 207, 206, 205, 204, 203, 202, 201, 200, 199, 198, 197, 196, 195, 194, 193, 192, 191,
            190, 189, 188, 187, 186, 185, 184, 183, 182, 181, 180, 179, 178, 177, 176, 175, 174, 173, 172, 171, 170,
            169, 168, 167, 166, 165, 164, 163, 162, 161, 160, 159, 158, 157, 156, 155, 154, 153, 152, 151, 150, 149,
            148, 147, 146, 145, 144, 143, 142, 141, 140, 139, 138, 137, 136, 135, 134, 133, 132, 131, 130, 129, 128,
            127, 126, 125, 124, 123, 122, 121, 120, 119, 118, 117, 116, 115, 114, 113, 112, 111, 110, 109, 108, 107,
            106, 105, 104, 103, 102, 101, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82,
            81, 80, 79, 78, 77, 76, 75, 74, 73, 72, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 58, 57, 56, 55,
            54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44, 43, 42, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28,
            27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1
    };

    /**
     * 获取随机生成的汉字
     *
     * @return - 随机生成的汉字
     */
    public static String getRandomChineseChar() {
        /*
         *  汉字以两个字节存储，称为“区位码”，高位叫区码，低位叫位码。假设有一张汉字表，横竖都是 94列，那么区码就相当于行，位码就相当于列，根据行列就可
         *  以确定一个汉字了，这有点像二位数组。GB2312大致就是按照这种方式实现的，1-9区存放特殊字符，16-55区存放一级汉字，56-87区存放二级汉字，其余
         *  暂时空余。为了区别中文与西文字母，在中文字符首位以1开头区分以0开头的ASCII码，GB2312给每个中文字符加上0xA0。
         *
         *  因此，汉字的区码范围0xB0-0xF7，位码范围0xA0-0xFE。
         */

        // 随机生成区码、位码
        Random random = new Random();
        int highPos = (176 + Math.abs(random.nextInt(39)));
        int lowPos = (161 + Math.abs(random.nextInt(93)));

        // 准备字节码
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(highPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        // 生成汉字
        return new String(b, Charset.forName("GBK"));
    }

    /**
     * 获取随机生成的中国姓氏
     *
     * @param absolutelyFair true：绝对公平 false：使用频率高的姓氏抽取的概率高
     * @return - 随机生成的中国姓氏
     */
    public static String getRandomChineseLastName(boolean absolutelyFair) {
        if (absolutelyFair) {
            // 完全随机
            return LAST_NAME[new Random().nextInt(LAST_NAME.length - 1)];
        } else {
            // 基于权重的随机：使用频率高的姓氏被抽中的概率高
            return RandomUtils.randomDecisionWithWeight(LAST_NAME, LAST_NAME_WEIGHTS);
        }
    }

    /**
     * 获取随机生成的中文姓名
     *
     * @return - 随机生成的中文姓名
     */
    public static String getRandomChineseName() {
        // 随机选取姓氏
        String lastName = getRandomChineseLastName(false);

        // 随机生成名字（控制8/10的人名字长度为2）
        int firstNameLength = new Random().nextInt(10);
        String firstName = firstNameLength < 8 ? getRandomChineseChar() + getRandomChineseChar() : getRandomChineseChar();

        // 返回姓名：姓氏 + 名字
        return lastName + firstName;
    }

    //public static void main(String[] args) {
    //    for (int i = 0; i < 1000; i++) {
    //        System.out.println(getRandomChineseName());
    //    }
    //}
}
```

## 6.随机生成电话号码

```java
package com.example.backstage.util;

import com.example.backstage.entity.BigDataUser;

import java.util.Scanner;

/**
 * @auther:：9527
 * @Description: 随机生成电话号码
 * @program: shi_yong
 * @create: 2019-07-30 10:22
 */
public class RandomPhoneNum {
    static Scanner sc = new Scanner(System.in);

    public static <lenPhone> void main(String[] args) {
        //System.out.println("getPhoneNum: " + getPhoneNum());
        //System.out.println("getProductNum: " + getProductNum());
        System.out.println("getUser"+getUser());
        ////询问需要多少个随机号码
        //boolean boo = true;
        //int num = 0;
        //do {
        //    //如果用户输入的不是一个整数，就循环要求用户输入一个整数
        //    System.out.println("你需要多少组电话号码，请输入一个整数");
        //    String answer = sc.next();
        //    try {
        //        //将用户的输入转化为整数
        //        num = Integer.parseInt(answer);
        //        //如果转换成功，boo就设置为false使其可以跳出循环
        //        boo = false;
        //    } catch (Exception e) {
        //        //如果用户输入的不是一个整数，就抛出异常，要求用户重新输入
        //        System.out.println("你输入的不是一个整数，请重新输入");
        //    }
        //
        //} while (boo);
        //
        //System.out.println("你要的手机号码如下：");
        ////将循环次数设置为用户需要的号码的数量
        //for (int i = 0; i < num; i++) {
        //    //调用静态方法生成手机号码
        //    System.out.println(getPhoneNum());
        //}
    }

    //定一个静态方法，专门生成单个的号码
    public static String getPhoneNum() {
        //给予真实的初始号段，号段是在百度上面查找的真实号段
        String[] start = {"133", "149", "153", "173", "177",
                "180", "181", "189", "199", "130", "131", "132",
                "145", "155", "156", "166", "171", "175", "176", "185", "186", "166", "134", "135",
                "136", "137", "138", "139", "147", "150", "151", "152", "157", "158", "159", "172",
                "178", "182", "183", "184", "187", "188", "198", "170", "171"};

        //随机出真实号段  使用数组的length属性，获得数组长度，
        //通过Math.random（）*数组长度获得数组下标，从而随机出前三位的号段
        String phoneFirstNum = start[(int) (Math.random() * start.length)];
        //随机出剩下的8位数
        String phoneLastNum = "";
        //定义尾号，尾号是8位
        final int LENPHONE = 8;
        //循环剩下的位数
        for (int i = 0; i < LENPHONE; i++) {
            //每次循环都从0~9挑选一个随机数
            phoneLastNum += (int) (Math.random() * 10);
        }
        //最终将号段和尾数连接起来
        String phoneNum = phoneFirstNum + phoneLastNum;
        //System.out.println(phoneNum);
        return phoneNum;
    }

    /**
     * 随机产生一个产品id
     */
    public static String getProductNum() {
        String productNum = "1";
        for (int i = 0; i < 9; i++) {
            //每次循环都从0~9挑选一个随机数
            productNum += (int) (Math.random() * 10);
        }
        return productNum;
    }
    /**
     * 随机产生性别,1为男0为女
     */
    public static Integer getSex() {
        return Math.random() * 10 > 5 ? 1 : 0;
    }

    /**
     * 随机生成一个用户
     * @return
     */
    public static BigDataUser getUser() {
        BigDataUser bigDataUser=new BigDataUser();
        bigDataUser.setName(ChineseUtil.getRandomChineseName());
        bigDataUser.setParentPhone(getPhoneNum());
        bigDataUser.setSex(getSex());
        bigDataUser.setProductId(getProductNum());
        return bigDataUser;
    }
}

```

## 7.数据库转Excel

F:\Java\PracticeProject\20220515个人运动管理平台\code\backstage\src\test\java\com\example\backstage\mapper\DrownedManMapperTest.java

插入依赖

```java
        <!--EasyExcel相关依赖-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>easyexcel</artifactId>
            <version>3.0.5</version>
        </dependency>
```

直接使用即可:

drownedMEN为实体类序列:

```java
        List<DrownedMan> drownedMEN = drownedManMapper.selectList();
        System.out.println(drownedMEN.size());
        //System.out.println(drownedMEN.get(1));
        //for (int i = 1; i < 10; i++) {
        //    System.out.println(drownedMEN.get(i));
        //}
        //定义文件输出位置
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File("E:/GOOGLE_download/user1.xlsx"));
            EasyExcel.write(outputStream, DrownedMan.class).sheet("用户信息").doWrite(drownedMEN);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
```





















